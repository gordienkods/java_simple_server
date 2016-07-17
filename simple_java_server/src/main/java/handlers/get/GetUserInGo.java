package handlers.get;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import tools.JsonMSG;
import entities.UserEntity;
import service.DataStorage;
import static tools.Responser.sendResponse;

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
            sendResponse(JsonMSG.errorMsgAsJson("no user with id " + exchange.getAttribute("userId")), exchange);
        }
    }

}
