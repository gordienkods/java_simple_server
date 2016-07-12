package core;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by Димон on 12.07.2016.
 */
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

}
