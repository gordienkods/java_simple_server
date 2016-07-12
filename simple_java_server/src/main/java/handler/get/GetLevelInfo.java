package handler.get;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import core.Messages;
import core.Parser;
import core.Sorter;
import entity.UserEntity;
import handler.BasicHttpHandler;
import org.json.JSONArray;
import org.json.JSONObject;
import service.DataStorage;

import java.util.Map;

public class GetLevelInfo extends BasicHttpHandler implements HttpHandler {

    public GetLevelInfo (DataStorage dataStorage){
        super(dataStorage);
    }

    public void handle(HttpExchange httpExchange){
        super.httpExchange = httpExchange;
        try {
            requestHandler();
        }catch (Throwable t) {
            sendResponse(Messages._500());
            t.printStackTrace();
        }

    }

    protected void requestHandler(){
        if(!isRequestMethod("GET")) { return; }
        Integer levelId = Integer.parseInt(httpExchange.getRequestURI().toString().replace("/levelinfo/", ""));
        System.err.println("LEVEL: " + levelId);
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
        sendResponse(jsonObject.toString());
    }
}
