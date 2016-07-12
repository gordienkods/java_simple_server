package entity;
import core.Sorter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.*;

public class UserEntity implements Serializable {

    private Integer userId = 0;
    private Integer levelResult = 0;
    private Map<Integer, Integer> levelsAndResults = new HashMap<>();
    private Map<Integer, Integer> sortedTopLevelsAndResults = new LinkedHashMap<>();


    public UserEntity (Integer userId){
        this.userId = userId;
    }

    public UserEntity (String json){
        fromJson(json);
    }

    public Integer getUserId() {
        return userId;
    }

    public void addLevelAndResult(Integer level, Integer result){
        levelsAndResults.put(level, result);
    }

    public void setSpecificLevelResult(int level){
        levelResult = levelsAndResults.get(level);
        System.err.println("USER ID: " + userId + "  RESULT FOR LEVEL [" + level + "]: " + levelResult);
    }

    public void buildDescTop(int levelDeep){
        Map<Integer, Integer> sortedResultsOnAllLevels = Sorter.sortResultsOnAllLevelsByDescOrder(levelsAndResults);
        Set<Map.Entry<Integer, Integer>> entrySet = sortedResultsOnAllLevels.entrySet();
        Iterator<Map.Entry<Integer,Integer>> iterator = entrySet.iterator();

        if (levelDeep < sortedResultsOnAllLevels.size()){
            for (int i = 0; i < levelDeep; i++){
                Map.Entry<Integer, Integer> entry = iterator.next();
                sortedTopLevelsAndResults.put(entry.getKey(), entry.getValue());
            }
        } else {
            while (iterator.hasNext()){
                Map.Entry<Integer, Integer> entry = iterator.next();
                sortedTopLevelsAndResults.put(entry.getKey(), entry.getValue());
            }
        }
    }

    public Integer getLevelResult() {
        return levelResult;
    }

    public String toJson(){
        JSONObject json = new JSONObject();
        json.put("userId", userId);
        json.put("top", mapToJsonArray(sortedTopLevelsAndResults));
        json.put("levels_and_results", mapToJsonArray(levelsAndResults));
        return json.toString();
    }

    private String fromJson(String jsonString){
        JSONObject json = new JSONObject(jsonString);
        this.userId = Integer.parseInt(json.get("userId").toString());
        this.levelsAndResults = jsonArrayToMap(json.getJSONArray("levels_and_results"));
        return null;
    }

    private JSONArray mapToJsonArray (Map<Integer, Integer> map){
        Set<Map.Entry<Integer,Integer>> entrySet = map.entrySet();
        Iterator<Map.Entry<Integer,Integer>> iterator = entrySet.iterator();
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < map.size(); i++){
            Map.Entry<Integer,Integer> entry = iterator.next();
            jsonArray.put(i, new JSONObject().put(entry.getKey().toString(), entry.getValue()));
        }
        return jsonArray;
    }

    private Map<Integer, Integer> jsonArrayToMap(JSONArray jsonArray ){
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (!this.getUserId().equals(that.getUserId())) return false;
        return levelsAndResults.equals(that.levelsAndResults);
    }

    @Override
    public int hashCode() {
        int result = 15;
        result = 31 * result + levelResult.hashCode();
        result = 31 * result + levelsAndResults.hashCode();
        result = 31 * result + userId.hashCode();
        return result;
    }

    @Override
    public String toString(){
        return toJson();
    }

}
