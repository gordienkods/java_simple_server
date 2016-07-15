package handlers.put;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import core.Messages;
import entity.UserEntity;
import service.DataStorage;
import static core.Parser.parseJsonFromPutBodyToUserEntity;
import static core.Responser.isRequestMethod;
import static core.Responser.sendRequest;

public class PutSetInfo implements HttpHandler   {

    private DataStorage dataStorage;

    public PutSetInfo(DataStorage dataStorage){
        this.dataStorage = dataStorage;
    }

    public void handle (HttpExchange exchange){
        try {
            requestHandler(exchange);
        }catch (Throwable t) {
            sendRequest(Messages._500(), exchange);
            t.printStackTrace();
        }
    }

    private void requestHandler(HttpExchange exchange){
        if(!isRequestMethod("PUT", exchange)) { return; }

        UserEntity userEntity = parseJsonFromPutBodyToUserEntity(exchange);
        if (userEntity != null) {
            dataStorage.updateUserEntity(userEntity.getUserId(), userEntity);
            sendRequest(Messages._201(), exchange);
        }
    }

}
