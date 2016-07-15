package handlers.get;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import core.Messages;
import entity.UserEntity;
import service.DataStorage;
import static core.Responser.isRequestMethod;
import static core.Responser.sendResponse;

public class GetUserInGo implements HttpHandler {

    private DataStorage dataStorage;

    public GetUserInGo (DataStorage dataStorage){
        this.dataStorage = dataStorage;
    }

    public void handle(HttpExchange exchange){
        try {
            requestHandler(exchange);
        } catch (Throwable t) {
            sendResponse(Messages._500(), exchange);
            t.printStackTrace();
        }
    }

    private void requestHandler(HttpExchange exchange){
        if(!isRequestMethod("GET", exchange)) { return; }
        Integer userId = Integer.parseInt(exchange.getRequestURI().toString().replace("/useringo/", ""));
        UserEntity userEntity = dataStorage.getUser(userId);
        if (userEntity != null){
            userEntity.buildDescTop(20);
            String response = dataStorage.getUser(userId).toJson();
            sendResponse(response, exchange);
        } else {
            sendResponse(Messages._404(), exchange);
        }
    }

}
