package handler.put;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.xml.internal.stream.Entity;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
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
        String jsonString = "undefined";

        if(isRequestMethod("PUT")) {
            try (InputStream is = httpExchange.getRequestBody()){
                Scanner scanner = new Scanner(is);
                jsonString = scanner.next();
                System.err.println("JSON FROM PUT REQUEST " + jsonString);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
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
                sendResponse("ales gut");
            } catch (Throwable t) {
                sendResponse(Messages._408());
                t.printStackTrace();
            }
        }
    }




}
