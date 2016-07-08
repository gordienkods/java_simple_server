package handler.get;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class GetUserInGo implements HttpHandler {

    public void handle(HttpExchange httpExchange){
        System.err.println("useringo/[user_id]");
    }

}
