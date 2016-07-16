package handlers.put;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import core.Messages;
import entity.UserEntity;
import org.apache.log4j.Logger;
import service.DataStorage;
import static core.Responser.sendResponse;

public class PutSetInfo implements HttpHandler   {

    private static final Logger LOG = Logger.getLogger(PutSetInfo.class);

    private DataStorage dataStorage;

    public PutSetInfo(DataStorage dataStorage){
        this.dataStorage = dataStorage;
    }

    public void handle (HttpExchange exchange){
        try {
            requestHandler(exchange);
            sendResponse(Messages._201(), exchange);
        }catch (Throwable t) {
           throw t;
        }
    }

    private void requestHandler(HttpExchange exchange){
        UserEntity userEntity = new UserEntity(exchange.getAttribute("userEntytJsonInRequestBody").toString());
        dataStorage.updateUserEntity(userEntity.getUserId(), userEntity);
    }

}
