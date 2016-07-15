package handlers.put;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import core.Messages;
import entity.UserEntity;
import service.DataStorage;
import static core.Responser.getRequestBodyAsString;
import static core.Responser.sendResponse;

public class PutSetInfo implements HttpHandler   {

    private DataStorage dataStorage;

    public PutSetInfo(DataStorage dataStorage){
        this.dataStorage = dataStorage;
    }

    public void handle (HttpExchange exchange){
        try {
            requestHandler(exchange);
        }catch (Throwable t) {
            sendResponse(Messages._500(), exchange);
            t.printStackTrace();
        }
    }

    private void requestHandler(HttpExchange exchange){
        UserEntity userEntity = new UserEntity(getRequestBodyAsString(exchange));
        dataStorage.updateUserEntity(userEntity.getUserId(), userEntity);
    }

}
