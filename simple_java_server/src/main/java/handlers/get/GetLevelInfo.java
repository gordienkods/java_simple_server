package handlers.get;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import core.Messages;
import entity.UserEntity;
import org.json.JSONArray;
import org.json.JSONObject;
import service.DataStorage;
import static core.Responser.sendResponse;
import java.util.Map;

public class GetLevelInfo implements HttpHandler {

    private DataStorage dataStorage;

    public GetLevelInfo (DataStorage dataStorage){
        this.dataStorage = dataStorage;
    }

    public void handle(HttpExchange exchange){
        try {
            requestHandler(exchange);
        }catch (Throwable t) {
            sendResponse(Messages._500(), exchange);
            t.printStackTrace();
        }
    }

    private void requestHandler(HttpExchange exchange){
        Integer levelId = Integer.parseInt(exchange.getAttribute("levelId").toString());
        dataStorage.buildDescTopUsersByLevelResult(20, levelId);
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        System.err.println("SIZE DATA" + dataStorage.getSortedTopUsersByLevelResult().size());
        for (Map.Entry<Integer, UserEntity> entry : dataStorage.getSortedTopUsersByLevelResult().entrySet() ){
            String key = entry.getValue().getUserId().toString();
            String value = entry.getValue().getSpecificLevelResult().toString();
            System.err.println("**KEY " + key + "  VALUE" + value);
            jsonArray.put(new JSONObject().put(key, value));
        }
        jsonObject.put("sorted_level", levelId);
        jsonObject.put("top", jsonArray);
        System.err.println(jsonObject.toString());
        sendResponse(jsonObject.toString(), exchange);
    }
}
