package sorter_tests;

import core.Sorter;
import entity.UserEntity;
import org.apache.log4j.xml.SAXErrorHandler;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;
import java.util.*;

import static core.Sorter.sortUsersByResultsOnLevelByDescOrder;

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

}
