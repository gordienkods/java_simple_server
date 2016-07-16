package core;

import com.sun.net.httpserver.HttpExchange;
import entity.UserEntity;
import exceptions.DataParsingError;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.*;

public class Parser {

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

    public static String parseIntParamFromUriPath(URI uri, String constExpression){
        String fullPath = uri.getPath();
        String parsedParam = "undefined";
        try {
            parsedParam = fullPath.replace(constExpression, "");
        } catch (Exception e1) {
            throw new DataParsingError(e1.getMessage());
        }
        try {
            Integer intParam = Integer.parseInt(parsedParam);
        } catch (NumberFormatException e2) {
            throw new DataParsingError(e2.getMessage());
        }
        return parsedParam;
    }

    public static Boolean parseJsonFromPutBodyRequestToUserEntity(String json){
        try {
            new UserEntity(json);
            return true;
        }catch (JSONException e){
            throw new DataParsingError("CAN'T PARSE JSON [ " + json + " ]");
        }
    }

   public static String getRequestBodyAsString(HttpExchange exchange){
        try (InputStream is = exchange.getRequestBody()){
            Scanner scanner = new Scanner(is);
            return scanner.next();
        } catch (IOException e) {
           throw new DataParsingError( e.getMessage());
        }
    }

}
