package handler.get;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import core.Messages;
import entity.UserEntity;
import handler.BasicHttpHandler;
import service.DataStorage;

public class GetUserInGo extends BasicHttpHandler implements HttpHandler {

    public GetUserInGo (DataStorage dataStorage, HttpExchange httpExchange){
        super(dataStorage, httpExchange);
    }

    public void handle(HttpExchange httpExchange){
        if(isRequestMethodGET(httpExchange)) {
            Integer userId = Integer.parseInt(httpExchange.getRequestURI().toString().replace("/useringo/", ""));
            UserEntity userEntity = dataStorage.getUser(userId);
            if (userEntity != null){
                userEntity.buildDescTop(20);
                String response = dataStorage.getUser(userId).toJson();
                sendResponse(response, httpExchange);
            } else {
                sendResponse(Messages._408(), httpExchange);
            }
        }

    }



}
