package core;

import entity.UserEntity;

import java.util.*;

public class Sorter {

    public static Map<Integer, Integer> sortResultsOnAllLevelsByDescOrder(Map<Integer, Integer> unsortedMap){
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

    public static Map<Integer, UserEntity> sortUsersByResultsOnLevel(Map<Integer, UserEntity> unsortedMap, Integer level){
        List<Map.Entry<Integer, UserEntity>> list = new LinkedList<>(unsortedMap.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<Integer, UserEntity>>() {
            @Override
            public int compare(Map.Entry<Integer, UserEntity> o1, Map.Entry<Integer, UserEntity> o2) {
                o1.getValue().setLevelResult(level);
                o2.getValue().setLevelResult(level);
                return (o2.getValue().getLevelResult()).compareTo(o1.getValue().getLevelResult());
            }
        });

        LinkedHashMap<Integer, UserEntity> result = new LinkedHashMap<>();
        for (Map.Entry<Integer, UserEntity> entry : list){
            result.put(entry.getKey(), entry.getValue());
            System.err.println( " " + entry.getKey().toString() + "  " +  entry.getValue().getLevelResult());
        }
        return result;
    }

}
