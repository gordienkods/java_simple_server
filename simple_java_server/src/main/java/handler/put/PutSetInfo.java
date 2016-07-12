package handler.put;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import core.Messages;
import entity.UserEntity;
import handler.BasicHttpHandler;
import org.json.JSONArray;
import org.json.JSONObject;
import service.DataStorage;
import java.io.*;
import java.util.Iterator;
import java.util.Scanner;

public class PutSetInfo extends BasicHttpHandler implements HttpHandler   {

     public PutSetInfo(DataStorage dataStorage){
        super(dataStorage);
    }

    public void handle (HttpExchange httpExchange){
        super.httpExchange = httpExchange;
        try {
            requestHandler();
        }catch (Throwable t) {
            sendResponse(Messages._500());
            t.printStackTrace();
        }
    }

    protected void requestHandler(){
        if(!isRequestMethod("PUT")) { return; }
        String jsonString = "undefined";

        try (InputStream is = httpExchange.getRequestBody()){
            Scanner scanner = new Scanner(is);
            jsonString = scanner.next();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = new JSONObject(jsonString);
        Integer userId = Integer.parseInt(jsonObject.get("userId").toString());
        UserEntity userEntity = new UserEntity(userId);
        JSONArray jsonArray = jsonObject.getJSONArray("levels_and_results");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jo = jsonArray.getJSONObject(i);
            Iterator<String> iterator = jo.keys();
            String key = iterator.next();
            String value = jo.get(key).toString();
            userEntity.addLevelAndResult(Integer.parseInt(key), Integer.parseInt(value));
        }
        dataStorage.updateUserEntity(userId, userEntity);
        sendResponse(Messages._201());
    }

}
