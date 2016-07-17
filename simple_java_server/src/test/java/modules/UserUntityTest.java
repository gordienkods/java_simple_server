package modules;

import entities.UserEntity;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;
import java.util.*;

public class UserUntityTest {

    @Test
    public void buildingUserEntityFromJson(){
        JSONException jsonException = null;
        String exceptionMessage = "";
        try {
           UserEntity userEntity = new UserEntity("{\"userId\":1,\"levels_and_results\":[{\"1\":60},{\"2\":659},{\"3\":599},{\"4\":6781},{\"5\":552}]}");
        } catch (JSONException e) {
           jsonException = e;
           exceptionMessage = e.getMessage();
        }
        Assert.assertTrue("Error occurred durin parsing JSON to UserEntity: " + exceptionMessage, jsonException == null);
    }

    @Test
    public void valuesShouldBeCorrectAfterBuildingUserEntityFromJson(){
        UserEntity userEntity = new UserEntity("{\"userId\":1,\"levels_and_results\":[{\"1\":60},{\"2\":659},{\"3\":599},{\"4\":6781},{\"5\":552}]}");
        Map<Integer, Integer> expectedLevelsValues = new HashMap<>();
        Map<Integer, Integer> actualLevelsValues = userEntity.getLevelsAndResults();
        Integer expectedUserId = 1;
        Integer actualUserId = userEntity.getUserId();
        Boolean testResult = true;
        StringBuilder report = new StringBuilder();

        expectedLevelsValues.put(1,60);
        expectedLevelsValues.put(2,659);
        expectedLevelsValues.put(3,599);
        expectedLevelsValues.put(4,6781);
        expectedLevelsValues.put(5,552);

        if ( Integer.compare(expectedUserId, actualUserId) != 0){
            report.append("\n---> USER ID EXP VAL: " + expectedUserId + " USER ID ACT VAL: " + expectedUserId + "\n\n");
        } else {
            report.append("\nUSER ID EXP VAL: " + expectedUserId + " USER ID ACT VAL: " + expectedUserId + "\n\n");
        }

       for (int i = 1; i <= 5; i++) {
            Integer expVal = expectedLevelsValues.get(i);
            Integer actVal = actualLevelsValues.get(i);

            if ( (Integer.compare(actVal, expVal) == 0) && (actVal != null) ){
                report.append("KEY: " + i + " EXP VAL: [" + expVal + "] ACT VAL: [" + actVal + "]\n");
            } else {
                report.append("---> KEY: " + i + " EXP VAL: [" + expVal + "] ACT VAL: [" + actVal + "]\n");
                testResult = false;
            }
        }
        Assert.assertTrue(report.toString(), testResult);
    }

    @Test
    public void sortUsersResultsOnAllLevelsByDecOrder(){
        StringBuilder report = new StringBuilder();
        Boolean testResult = true;
        UserEntity userEntity = new UserEntity("{\"userId\":1,\"levels_and_results\":[{\"1\":60},{\"11\":60},{\"2\":659},{\"3\":599},{\"4\":6781},{\"5\":552},{\"10\":0}]}");
        Map<Integer, Integer> expRes = new LinkedHashMap<>();
        expRes.put(4,6781);
        expRes.put(2,659);
        expRes.put(3,599);
        expRes.put(5,552);
        expRes.put(1,60);
        expRes.put(11,60);
        expRes.put(10,0);

        userEntity.buildDescTop(20);
        Map<Integer,Integer> actRes = userEntity.getSortedTopLevelsAndResults();

        Set<Map.Entry<Integer,Integer>> actualEntry = actRes.entrySet();
        Set<Map.Entry<Integer,Integer>> expectedEntry = expRes.entrySet();
        Iterator<Map.Entry<Integer,Integer>> actualEntryIterator = actualEntry.iterator();
        Iterator<Map.Entry<Integer,Integer>> expectedEntryIterator = expectedEntry.iterator();

        report.append("\n");
        while(expectedEntryIterator.hasNext()){
            String actual = actualEntryIterator.next().toString();
            String expected =  expectedEntryIterator.next().toString();
            if (expected.equals(actual)) {
                report.append("EXPECTED KEY/VALYE: " + expected + "  ACTUAL KEY/VALUE: " + actual + "\n");
            } else {
                report.append("---> EXPECTED KEY/VALYE: " + expected + "  ACTUAL KEY/VALUE: " + actual + "\n");
                testResult = false;
            }
        }

        Assert.assertTrue(report.toString(), testResult);
    }

    @Test
    public void serializeUserEntityToJson(){
        UserEntity userEntity = new UserEntity("{\"userId\":1,\"levels_and_results\":[{\"1\":60},{\"11\":60},{\"2\":659},{\"3\":599},{\"4\":6781},{\"5\":552},{\"10\":0}]}");
        String expRes = "{\"top\":[],\"userId\":1,\"levels_and_results\":[{\"1\":60},{\"2\":659},{\"3\":599},{\"4\":6781},{\"5\":552},{\"10\":0},{\"11\":60}]}";
        String actRes = userEntity.toString();

        Assert.assertEquals(expRes,actRes);
    }
}
