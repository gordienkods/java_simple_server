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
            throw t;
        }
    }

    private void requestHandler(HttpExchange exchange){
        Integer userId =  Integer.parseInt(exchange.getAttribute("userId").toString());
        UserEntity userEntity = dataStorage.getUser(userId);
        if (userEntity != null){
            userEntity.buildDescTop(20);
            String response = userEntity.toJson();
            sendResponse(response, exchange);
        } else {
            sendResponse(Messages._404(), exchange);
        }
    }

}
