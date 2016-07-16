package sorter_tests;

import core.Sorter;
import org.apache.log4j.xml.SAXErrorHandler;
import org.junit.Assert;
import org.junit.Test;
import java.util.*;

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

}
