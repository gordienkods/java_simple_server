package impl;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.spi.HttpServerProvider;
import handler.get.GetUserInGo;
import handler.get.GetLevelInfo;
import handler.put.PutSetInfo;
import service.DataStorage;
import service.Server;
import sun.net.httpserver.DefaultHttpServerProvider;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class SunNetHttpServerImpl implements Server {

    final int backlog = 64;
    final InetSocketAddress serverPort = new InetSocketAddress(85);
    private HttpServer server = null;
    private HttpContext context = null;
    private DataStorage dataStorage;

    public void setDataStorage(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

    public void start() {
        build();

        createContext("/useringo");
        setHandler(new GetUserInGo(dataStorage));

        createContext("/levelinfo");
        setHandler(new GetLevelInfo());

        createContext("/setinfo");
        setHandler(new PutSetInfo(dataStorage));

        server.start();
    }

    public void build() {
        try {
            HttpServerProvider httpServerProvider = DefaultHttpServerProvider.provider();
            server = httpServerProvider.createHttpServer(serverPort, backlog);
            server.setExecutor(Executors.newCachedThreadPool());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createContext (String contextName){
        context = server.createContext(contextName);
    }

    public void setHandler(HttpHandler httpHandler){
        context.setHandler(httpHandler);
    }

}
