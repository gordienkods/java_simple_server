package entity;
import com.google.gson.internal.LinkedHashTreeMap;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.*;

public class UserEntity {

    private Integer userId;
    private Integer resultOnLevel;
    private Map<Integer, Integer> levelsAndResults = new TreeMap<>();
    private Map<Integer, Integer> sortedTopLevelsAndResults = new LinkedHashTreeMap<>();


    public Integer getUserId() {
        return userId;
    }

    public UserEntity (Integer userId){
        this.userId = userId;
    }

    public void addLevelAndResult(Integer level, Integer result){
        levelsAndResults.put(level, result);
    }

    public void getResultOnLevel(int level){
        resultOnLevel = levelsAndResults.get(level);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (!getUserId().equals(that.getUserId())) return false;
        if (!resultOnLevel.equals(that.resultOnLevel)) return false;
        return levelsAndResults.equals(that.levelsAndResults);
    }

    @Override
    public int hashCode() {
        int result = getUserId().hashCode();
        result = 31 * result + resultOnLevel.hashCode();
        result = 31 * result + levelsAndResults.hashCode();
        return result;
    }

    public void buildDescTop(int levelDeep){
        Map<Integer, Integer> sortedResultsOnAllLevels = sortResultsOnAllLevelsByDescOrder(levelsAndResults);
        Set<Map.Entry<Integer, Integer>> entrySet = sortedResultsOnAllLevels.entrySet();
        Iterator<Map.Entry<Integer,Integer>> iterator = entrySet.iterator();

        if (levelDeep < sortedResultsOnAllLevels.size()){
            for (int i = 0; i < levelDeep; i++){
                Map.Entry<Integer, Integer> entry = iterator.next();
                sortedTopLevelsAndResults.put(entry.getKey(), entry.getValue());
                System.err.println(entry.getKey() + "  " + entry.getValue());
            }
        } else {
            while (iterator.hasNext()){
                Map.Entry<Integer, Integer> entry = iterator.next();
                sortedTopLevelsAndResults.put(entry.getKey(), entry.getValue());
                System.err.println(entry.getKey() + "  " + entry.getValue());
            }
        }
    }

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

    private Map<Integer, Integer> sortResultsOnAllLevelsByDescOrder(Map<Integer, Integer> unsortedMap){
        List<Map.Entry<Integer,Integer>> list = new LinkedList<>(unsortedMap.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        LinkedHashMap<Integer,Integer> result = new LinkedHashMap<>();
        for (Map.Entry<Integer, Integer> entry : list){
            result.put(entry.getKey(), entry.getValue());
            System.err.println( " " + entry.getKey().toString() + "  " +  entry.getValue());
        }
        return result;
    }

}
