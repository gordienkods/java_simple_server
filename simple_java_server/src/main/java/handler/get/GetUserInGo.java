package handler.get;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import core.Messages;
import entity.UserEntity;
import org.omg.PortableServer.ServantRetentionPolicy;
import service.DataStorage;

import java.io.IOException;
import java.io.OutputStream;

public class GetUserInGo implements HttpHandler {

    DataStorage dataStorage;

    public GetUserInGo (DataStorage dataStorage){
        this.dataStorage = dataStorage;
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

    private Boolean isRequestMethodGET(HttpExchange httpExchange){
        if ( !"GET".equals(httpExchange.getRequestMethod())) {
            try (OutputStream os = httpExchange.getResponseBody() ) {
                httpExchange.sendResponseHeaders(200, Messages._400().getBytes().length);
                os.write(Messages._400().getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
        return true;
    }

    private void sendResponse(String response, HttpExchange httpExchange) {
        try (OutputStream os = httpExchange.getResponseBody() ) {
            httpExchange.sendResponseHeaders(200, response.getBytes().length);
            os.write(response.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
