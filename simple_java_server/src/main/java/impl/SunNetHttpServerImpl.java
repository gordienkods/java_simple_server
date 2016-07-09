package impl;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.spi.HttpServerProvider;
import handler.get.GetUserInGo;
import handler.get.GetLevelInfo;
import handler.put.PutSetInfo;
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

    public void start() {
        build();
        createContext("/useringo");
        setHandler(new GetUserInGo());

        createContext("/levelinfo");
        setHandler(new GetLevelInfo());

        createContext("/setinfo");
        setHandler(new PutSetInfo());

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

//context.setHandler(new HttpHandler() {
////                    public void handle(HttpExchange httpExchange) throws IOException {
////
////                        System.err.println("httpExchange.getRequestMethod() " + httpExchange.getRequestMethod());
////                        System.err.println("httpExchange.getRequestURI() " + httpExchange.getRequestURI().toString());
////                        System.err.println("httpExchange.getRequestHeaders() " + httpExchange.getRequestHeaders());
////
//////                    for(Map.Entry<String, List<String>> entry : httpExchange.getRequestHeaders().entrySet()){
//////                        System.err.println("KEY");entry.getKey()
//////                    }
//////                    System.err.println("httpExchange.getRequestURI() " + httpExchange.getRequestHeaders() );
////
////                        final OutputStream output = httpExchange.getResponseBody();
////                        Map<String, String> hashmap = new HashMap<String, String>();
////
////                        hashmap.put("key3", "value3");
////                        String responseJson = new JSONObject(hashmap).toString();
////                        httpExchange.sendResponseHeaders(404, responseJson.getBytes().length);
////                        System.err.println(responseJson);
////                        output.write(responseJson.getBytes());
////                        output.flush();
////                        httpExchange.close();
////                        httpExchange.close();
////                    }