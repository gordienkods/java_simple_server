package handler.get;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class GetLevelInfo implements HttpHandler {

    public void handle(HttpExchange httpExchange){
        System.err.println("/levelinfo/[level_id] ");
    }
}
