package sorter_tests;

import core.Sorter;
import entity.UserEntity;
import org.apache.log4j.xml.SAXErrorHandler;
import org.junit.Assert;
import org.junit.Test;
import java.util.*;

import static core.Sorter.sortUsersByResultsOnLevelByDescOrder;

public class SorterTest {

    @Test
    public void sort_map_by_values_by_desc_order(){

        Map<Integer, Integer> unsortedMap = new HashMap<>();
        unsortedMap.put(1,1);
        unsortedMap.put(2,0);
        unsortedMap.put(3,0);
        unsortedMap.put(4,2);
        unsortedMap.put(5,2);
        unsortedMap.put(6,5);
        unsortedMap.put(7,6);
        unsortedMap.put(8,7);
        unsortedMap.put(9,10);
        unsortedMap.put(10,100);

        Map<Integer, Integer> expectedResult = new LinkedHashMap<>();
        expectedResult.put(10,100);
        expectedResult.put(9,10);
        expectedResult.put(8,7);
        expectedResult.put(7,6);
        expectedResult.put(6,5);
        expectedResult.put(4,2);
        expectedResult.put(5,2);
        expectedResult.put(1,1);
        expectedResult.put(2,0);
        expectedResult.put(3,0);

        Map<Integer, Integer> actualResult = Sorter.sortResultsOnAllLevelsByDescOrder(unsortedMap);

        Set<Map.Entry<Integer,Integer>> actualEntry = actualResult.entrySet();
        Set<Map.Entry<Integer,Integer>> expectedEntry = expectedResult.entrySet();
        Iterator<Map.Entry<Integer,Integer>> actualEntryIterator = actualEntry.iterator();
        Iterator<Map.Entry<Integer,Integer>> expectedEntryIterator = expectedEntry.iterator();

        while(expectedEntryIterator.hasNext()){
            String actual = actualEntryIterator.next().toString();
            String expected =  expectedEntryIterator.next().toString();
            System.err.println("EXPECTED KEY/VALYE: " + expected + "  ACTUAL KEY/VALUE: " + actual );
            Assert.assertEquals(expected,actual);
        }
    }

    @Test
    public void sort_users_by_results_on_level_by_desc_orser(){
        Map<Integer,UserEntity> unsortedMap = new HashMap<>();

        UserEntity user1 = new UserEntity("{\"userId\":1,\"levels_and_results\":[{\"5\":155}]}");
        UserEntity user2 = new UserEntity("{\"userId\":2,\"levels_and_results\":[{\"1\":211},{\"3\":233},{\"4\":244},{\"5\":255}]}");
        UserEntity user3 = new UserEntity("{\"userId\":3,\"levels_and_results\":[{\"1\":311},{\"2\":322},{\"4\":344},{\"5\":355}]}");
        UserEntity user4 = new UserEntity("{\"userId\":4,\"levels_and_results\":[{\"1\":411},{\"2\":422},{\"3\":433},{\"5\":455}]}");
        UserEntity user5 = new UserEntity("{\"userId\":5,\"levels_and_results\":[{\"1\":511}]}");
        UserEntity user6 = new UserEntity("{\"userId\":6,\"levels_and_results\":[{\"1\":611},{\"2\":622},{\"3\":633},{\"4\":644},{\"5\":655}]}");
        UserEntity user7 = new UserEntity("{\"userId\":7,\"levels_and_results\":[{\"1\":711},{\"2\":722},{\"3\":733},{\"4\":744},{\"5\":755}]}");

        unsortedMap.put(user1.getUserId(), user1);
        unsortedMap.put(user2.getUserId(), user2);
        unsortedMap.put(user3.getUserId(), user3);
        unsortedMap.put(user4.getUserId(), user4);
        unsortedMap.put(user5.getUserId(), user5);
        unsortedMap.put(user6.getUserId(), user6);
        unsortedMap.put(user7.getUserId(), user7);

        Map<Integer, UserEntity> actualResult = sortUsersByResultsOnLevelByDescOrder(unsortedMap, 1);

        for (Map.Entry<Integer,UserEntity> entityEntry : actualResult.entrySet()){
            System.err.println("KEY: " + entityEntry.getKey() + "  VALUE: " + entityEntry.getValue());
        }
//        System.err.println(actualResult.toString());




    }

}
