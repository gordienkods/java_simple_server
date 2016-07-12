package handler.get;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import core.Messages;
import entity.UserEntity;
import handler.BasicHttpHandler;
import service.DataStorage;

public class GetUserInGo extends BasicHttpHandler implements HttpHandler {

    public GetUserInGo (DataStorage dataStorage){
        super(dataStorage);
    }

    public void handle(HttpExchange httpExchange){
        super.httpExchange = httpExchange;
        try {
            requestHandler();
        } catch (Throwable t) {
            sendResponse(Messages._500());
            t.printStackTrace();
        }
    }

    protected void requestHandler(){
        if(!isRequestMethod("GET")) { return; }
        Integer userId = Integer.parseInt(httpExchange.getRequestURI().toString().replace("/useringo/", ""));
        UserEntity userEntity = dataStorage.getUser(userId);
        if (userEntity != null){
            userEntity.buildDescTop(20);
            String response = dataStorage.getUser(userId).toJson();
            sendResponse(response);
        } else {
            sendResponse(Messages._404());
        }
    }

}
