package handlers.get;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import service.DataStorage;
import static tools.Responser.sendResponse;

public class GetLevelInfo implements HttpHandler {

    private DataStorage dataStorage;

    public GetLevelInfo (DataStorage dataStorage){
        this.dataStorage = dataStorage;
    }

    public void handle(HttpExchange exchange){
        try {
            requestHandler(exchange);
        }catch (Throwable t) {
            throw t;
        }
    }

    private void requestHandler(HttpExchange exchange){
        Integer levelId = Integer.parseInt(exchange.getAttribute("levelId").toString());
        dataStorage.buildDescTopUsersByLevelResult(20, levelId);
        sendResponse(dataStorage.getSortedTopUsersByLevelResultAsJson(), exchange);
    }
}
