package http.server.handlers.put;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import tools.JsonMSG;
import entities.UserEntity;
import org.apache.log4j.Logger;
import service.DataStorage;
import static tools.Responser.sendResponse;

public class PutSetInfo implements HttpHandler   {

    private static final Logger LOG = Logger.getLogger(PutSetInfo.class);

    private DataStorage dataStorage;

    public PutSetInfo(DataStorage dataStorage){
        this.dataStorage = dataStorage;
    }

    public void handle (HttpExchange exchange){
        try {
            requestHandler(exchange);
            sendResponse(JsonMSG.successMsgAsJson("user and levels result added/updated"), exchange);
        }catch (Throwable t) {
           throw t;
        }
    }

    private void requestHandler(HttpExchange exchange){
        UserEntity userEntity = new UserEntity(exchange.getAttribute("userEntytJsonInRequestBody").toString());
        dataStorage.updateUserEntity(userEntity.getUserId(), userEntity);
    }

}
