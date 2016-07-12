package handler.get;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import handler.BasicHttpHandler;
import service.DataStorage;

public class GetLevelInfo extends BasicHttpHandler implements HttpHandler {

    public GetLevelInfo (DataStorage dataStorage){
        super(dataStorage);
    }

    public void handle(HttpExchange httpExchange){

    }

    protected void requestHandler(){
        
    }
}
