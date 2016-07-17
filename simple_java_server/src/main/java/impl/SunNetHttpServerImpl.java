package impl;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.spi.HttpServerProvider;
import filters.get.GetLevelInfoParamFilter;
import filters.get.GetRequestBasicFilter;
import filters.get.GetUserInGoParamFilter;
import filters.put.PutRequestBasicFilter;
import filters.put.PutSetInfoFilter;
import handlers.get.GetLevelInfo;
import handlers.get.GetUserInGo;
import handlers.put.PutSetInfo;
import org.apache.log4j.Logger;
import service.DataStorage;
import service.Server;
import sun.net.httpserver.DefaultHttpServerProvider;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class SunNetHttpServerImpl implements Server {

    private final Logger LOG = Logger.getLogger(SunNetHttpServerImpl.class);
    final int backlog;
    final InetSocketAddress serverPort;
    private HttpServer server = null;
    private DataStorage dataStorage;

    public SunNetHttpServerImpl(DataStorage dataStorage, Integer port, Integer backlog) {
        this.dataStorage = dataStorage;
        serverPort =  new InetSocketAddress(port);
        this.backlog = backlog;
    }

    public void start() {
        try {
            HttpServerProvider httpServerProvider = DefaultHttpServerProvider.provider();
            server = httpServerProvider.createHttpServer(serverPort, backlog);
            server.setExecutor(Executors.newCachedThreadPool());

            HttpContext getUserInGoContext = server.createContext("/useringo", new GetUserInGo(dataStorage));
            getUserInGoContext.getFilters().add(new GetRequestBasicFilter());
            getUserInGoContext.getFilters().add(new GetUserInGoParamFilter());


            HttpContext getLevelInfoContext = server.createContext("/levelinfo", new GetLevelInfo(dataStorage));
            getLevelInfoContext.getFilters().add(new GetRequestBasicFilter());
            getLevelInfoContext.getFilters().add(new GetLevelInfoParamFilter());

            HttpContext putSetInfo = server.createContext("/setinfo", new PutSetInfo(dataStorage));
            putSetInfo.getFilters().add(new PutRequestBasicFilter());
            putSetInfo.getFilters().add(new PutSetInfoFilter());

        } catch (Throwable t) {
            t.printStackTrace();
        }
        server.start();
        LOG.info("Server has been started on port [" + serverPort.toString() + "] and back log size [" + backlog +"]");
    }

}
