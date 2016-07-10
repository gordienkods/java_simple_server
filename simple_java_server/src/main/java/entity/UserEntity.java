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

    public Integer getUserId() {
        return userId;
    }

    public UserEntity (Integer userId){
        this.userId = userId;
    }

    public void addLevelAndResult(Integer level, Integer result){
        levelsAndResults.put(level, result);
    }

    public void setLevelResult(int level){
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

//    public static UserEntity fromJson(String json){
//        JSONObject jsonObject = new JSONObject();
//        JSONArray jsonArray = jsonObject.getJSONArray("top");
//        sortedTopLevelsAndResults = new LinkedHashMap<>();
//
//        for (int i = 0; i < jsonArray.length(); i++){
//            Iterator iterator = jsonArray.getJSONObject(i).keys();
//            Integer key = (Integer) iterator.next();
//            Integer value = (Integer) jsonArray.getJSONObject(i).get(key.toString());
//            sortedTopLevelsAndResults.put(key, value);
//        }
//
//        UserEntity userEntity = new UserEntity(jsonObject.getInt("userId"));
//        userEntity.
//
//    }

    public String toJson(){
        JSONObject json = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        json.put("userId", userId);

        Set<Map.Entry<Integer,Integer>> entrySet = sortedTopLevelsAndResults.entrySet();
        Iterator<Map.Entry<Integer,Integer>> iterator = entrySet.iterator();
        for (int i = 0; i < sortedTopLevelsAndResults.size(); i++){
            Map.Entry<Integer,Integer> entry = iterator.next();
            jsonArray.put(i, new JSONObject().put(entry.getKey().toString(), entry.getValue()));
        }
        json.put("top", jsonArray);
        return json.toString();
    }

//    private Map<Integer, Integer> sortResultsOnAllLevelsByDescOrder(Map<Integer, Integer> unsortedMap){
//        List<Map.Entry<Integer,Integer>> list = new LinkedList<>(unsortedMap.entrySet());
//
//        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
//            @Override
//            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
//                return (o2.getValue()).compareTo(o1.getValue());
//            }
//        });
//
//        LinkedHashMap<Integer,Integer> result = new LinkedHashMap<>();
//        for (Map.Entry<Integer, Integer> entry : list){
//            result.put(entry.getKey(), entry.getValue());
//            System.err.println( " " + entry.getKey().toString() + "  " +  entry.getValue());
//        }
//        return result;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (!getUserId().equals(that.getUserId())) return false;
        if (!levelResult.equals(that.levelResult)) return false;
        return levelsAndResults.equals(that.levelsAndResults);
    }

    @Override
    public int hashCode() {
        int result = 15;
        result = 31 * result + levelResult.hashCode();
        result = 31 * result + levelsAndResults.hashCode();
        return result;
    }

}
