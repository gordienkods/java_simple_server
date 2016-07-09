package entity;

import java.util.*;

public class UserEntity {

    private Integer userId;
    private Integer resultOnLevel;
    private Map<Integer, Integer> levelsAndResults = new TreeMap<>();
    private Map<Integer, Integer> sortedResultsOnAllLevels;
    private List<Integer> sortedResults;


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

    public void sortResultsOnAllLevels(){
        sortedResultsOnAllLevels = new TreeMap<Integer, Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return levelsAndResults.get(o1).compareTo(levelsAndResults.get(o2));
            }
        });
        sortedResultsOnAllLevels.putAll(levelsAndResults);
    }

    public void getResultsOnLevels(){
        for (Map.Entry<Integer, Integer> entry : sortedResultsOnAllLevels.entrySet()){
            System.err.println("Level '" + entry.getKey() +"'  RESULT '" + entry.getValue() + "'");
        }
        System.err.println("");
        Set<Map.Entry<Integer, Integer>> entrySet = sortedResultsOnAllLevels.entrySet();
        Iterator<Map.Entry<Integer,Integer>> iterator = entrySet.iterator();
        for (int i = 0; i < 5; i++){
            Map.Entry<Integer, Integer> entry = iterator.next();
            System.err.println(entry.getKey() + " <----> " + entry.getValue());
        }
    }




}
