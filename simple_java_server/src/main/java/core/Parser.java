package core;

import com.sun.net.httpserver.HttpExchange;
import entity.UserEntity;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.*;

import static core.Responser.sendResponse;


public class Parser {

    private final Logger LOG = Logger.getLogger(Messages.class);


    public static JSONArray mapToJsonArray (Map<Integer, Integer> map){
        Set<Map.Entry<Integer,Integer>> entrySet = map.entrySet();
        Iterator<Map.Entry<Integer,Integer>> iterator = entrySet.iterator();
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < map.size(); i++){
            Map.Entry<Integer,Integer> entry = iterator.next();
            jsonArray.put(i, new JSONObject().put(entry.getKey().toString(), entry.getValue()));
        }
        return jsonArray;
    }

    public static Map<Integer, Integer> jsonArrayToMap(JSONArray jsonArray ){
        Map<Integer, Integer> result = new HashMap<>();
        for (int i = 0; i < jsonArray.length(); i++){
            JSONObject jo = jsonArray.getJSONObject(i);
            Iterator<String> iterator = jo.keys();
            String key = iterator.next();
            String value = jo.get(key).toString();
            result.put(Integer.parseInt(key), Integer.parseInt(value));
        }
        return result;
    }

    public static void parseIntParamFromUriPath(String attributeName, String constExpression, HttpExchange exchange){
        URI uri = exchange.getRequestURI();
        String fullPath = uri.getPath();
        String parsedParam = "undefined";
        try {
            parsedParam = fullPath.replace(constExpression, "");
        } catch (Exception e1) {
            sendResponse(Messages._405(), exchange);
        }
        try {
            Integer intParam = Integer.parseInt(parsedParam);
        } catch (NumberFormatException e) {
            sendResponse(Messages._404("param '" + parsedParam + "' value '" + parsedParam), exchange);
        }
        exchange.setAttribute(attributeName, parsedParam);
        System.err.println("GET LEVEL INFO: " + exchange.getAttribute(constExpression).toString());
    }

    public static Boolean parseJsonFromPutBodyToUserEntity(HttpExchange exchange){
        String jsonString = "undefined";
        try {
            new UserEntity(jsonString);
            return true;
        }catch (Throwable t){
            sendResponse(Messages._500(), exchange);
            t.printStackTrace();
        }

        try (InputStream is = exchange.getRequestBody()){
            Scanner scanner = new Scanner(is);
            jsonString = scanner.next();

        } catch (IOException e) {
            sendResponse(Messages._500(), exchange);
            e.printStackTrace();
        }
        return false;
    }

}
