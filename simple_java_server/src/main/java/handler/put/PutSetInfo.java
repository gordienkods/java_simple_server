package handler.put;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class PutSetInfo implements HttpHandler {

    public void handle (HttpExchange httpExchange){
        System.err.println("/setinfo");
        if("GET".equals(httpExchange.getRequestMethod())) {

            httpExchange.getResponseHeaders().add("400", "bad request");
//            httpExchange.sendResponseHeaders(400, "asd");
        }
    }

}
