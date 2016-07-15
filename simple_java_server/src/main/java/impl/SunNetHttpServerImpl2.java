package impl;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.spi.HttpServerProvider;
import filters.GetLevelInfoParamFilter;
import filters.GetRequestBasicFilter;
import filters.PutRequestBasicFilter;
import handler.get.GetLevelInfo;
import handler.get.GetUserInGo;
import handler.put.PutSetInfo;
import service.DataStorage;
import service.Server;
import sun.net.httpserver.DefaultHttpServerProvider;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class SunNetHttpServerImpl2 {

    final int backlog = 64;
    final InetSocketAddress serverPort = new InetSocketAddress(85);
    private HttpServer server = null;
    private HttpContext context = null;
    private DataStorage dataStorage;

    public SunNetHttpServerImpl2(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

    public void start() {
        try {
            HttpServerProvider httpServerProvider = DefaultHttpServerProvider.provider();
            server = httpServerProvider.createHttpServer(serverPort, backlog);
            server.setExecutor(Executors.newCachedThreadPool());

            HttpContext getUserInGoContext = server.createContext("/useringo", new GetUserInGo(dataStorage));
            getUserInGoContext.getFilters().add(new GetRequestBasicFilter());

            HttpContext getLevelInfoContext = server.createContext("/levelinfo", new GetLevelInfo(dataStorage));
            getLevelInfoContext.getFilters().add(new GetRequestBasicFilter());
            getLevelInfoContext.getFilters().add(new GetLevelInfoParamFilter());

            HttpContext putSetInfo = server.createContext("/setinfo", new PutSetInfo(dataStorage));
            putSetInfo.getFilters().add(new PutRequestBasicFilter());

        } catch (IOException e) {
            e.printStackTrace();
        }

        server.start();
    }

}
