package modules;

import entity.UserEntity;
import impl.HazelcastDataStorageImp;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;

public class HazelcastDataStorageImpTest {

    private UserEntity user1;
    private UserEntity user2;
    private UserEntity user3;
    private UserEntity user4;
    private UserEntity user5;
    private UserEntity user6;
    private UserEntity user7;
    private HazelcastDataStorageImp dataStorage;

    @Before
    public void initUsers(){
        user1 = new UserEntity("{\"userId\":1,\"levels_and_results\":[{\"5\":155}]}");
        user2 = new UserEntity("{\"userId\":2,\"levels_and_results\":[{\"1\":211},{\"3\":233},{\"4\":244},{\"5\":7}]}");
        user3 = new UserEntity("{\"userId\":3,\"levels_and_results\":[{\"1\":311},{\"2\":322},{\"4\":244},{\"5\":99}]}");
        user4 = new UserEntity("{\"userId\":4,\"levels_and_results\":[{\"1\":411},{\"2\":422},{\"3\":433},{\"5\":455}]}");
        user5 = new UserEntity("{\"userId\":5,\"levels_and_results\":[{\"1\":511}]}");
        user6 = new UserEntity("{\"userId\":6,\"levels_and_results\":[{\"1\":611},{\"2\":622},{\"3\":633},{\"4\":644},{\"5\":655}]}");
        user7 = new UserEntity("{\"userId\":7,\"levels_and_results\":[{\"1\":711},{\"2\":622},{\"3\":733},{\"4\":744},{\"5\":755}]}");

        dataStorage = new HazelcastDataStorageImp();
        dataStorage.startMasterStorage(1542);
        dataStorage.addUser(user1);
        dataStorage.addUser(user2);
        dataStorage.addUser(user3);
        dataStorage.addUser(user4);
        dataStorage.addUser(user5);
        dataStorage.addUser(user6);
        dataStorage.addUser(user7);

    }

    @Test
    public void buildTopUsersInDescOrderByLeve1Result(){
        StringBuilder report = new StringBuilder();
        Boolean testResult = true;
        dataStorage.buildDescTopUsersByLevelResult(20,1);
        JSONArray expRes = new JSONArray("[{\"7\":\"711\"},{\"6\":\"611\"},{\"5\":\"511\"},{\"4\":\"411\"},{\"3\":\"311\"},{\"2\":\"211\"},{\"1\":\"0\"}]");
        JSONObject tmpJsonObject = new JSONObject( dataStorage.getSortedTopUsersByLevelResultAsJson());
        JSONArray actRes = new JSONArray(tmpJsonObject.getJSONArray("top").toString());

        report.append("\n");
        for (int i = 0; i < expRes.length(); i++){
            Iterator expIterator = expRes.getJSONObject(i).keys();
            String expKey = expIterator.next().toString();
            String expVal = expRes.getJSONObject(i).get(expKey).toString();

            Iterator actIterator = actRes.getJSONObject(i).keys();
            String actKey = actIterator.next().toString();
            String actVal = actRes.getJSONObject(i).get(actKey).toString();

            try {
                Assert.assertEquals(expVal, actVal);
                report.append("EXP: " + expVal + "  ACT: " + actVal + "\n");
            } catch (AssertionError e) {
                report.append("---> EXP: " + expVal + "  ACT: " + actVal + "\n");
                testResult = false;
            }
        }
        Assert.assertTrue(report.toString(), testResult);
    }

    @Test
    public void buildTopUsersInDescOrderByLeve2Result(){
        StringBuilder report = new StringBuilder();
        Boolean testResult = true;
        dataStorage.buildDescTopUsersByLevelResult(20,2);
        JSONArray expRes = new JSONArray("[{\"7\":\"622\"},{\"6\":\"622\"},{\"4\":\"422\"},{\"3\":\"322\"},{\"2\":\"0\"},{\"1\":\"0\"},{\"5\":\"0\"}]");
        JSONObject tmpJsonObject = new JSONObject( dataStorage.getSortedTopUsersByLevelResultAsJson());
        JSONArray actRes = new JSONArray(tmpJsonObject.getJSONArray("top").toString());

        report.append("\n");
        for (int i = 0; i < expRes.length(); i++){
            Iterator expIterator = expRes.getJSONObject(i).keys();
            String expKey = expIterator.next().toString();
            String expVal = expRes.getJSONObject(i).get(expKey).toString();

            Iterator actIterator = actRes.getJSONObject(i).keys();
            String actKey = actIterator.next().toString();
            String actVal = actRes.getJSONObject(i).get(actKey).toString();

            try {
                Assert.assertEquals(expVal, actVal);
                report.append("EXP: " + expVal + "  ACT: " + actVal + "\n");
            } catch (AssertionError e) {
                report.append("---> EXP: " + expVal + "  ACT: " + actVal + "\n");
                testResult = false;
            }
        }
        Assert.assertTrue(report.toString(), testResult);
    }

    @Test
    public void buildTopUsersInDescOrderByLeve3Result(){
        StringBuilder report = new StringBuilder();
        Boolean testResult = true;
        dataStorage.buildDescTopUsersByLevelResult(20,3);
        JSONArray expRes = new JSONArray("[{\"7\":\"733\"},{\"6\":\"633\"},{\"4\":\"433\"},{\"2\":\"233\"},{\"1\":\"0\"},{\"3\":\"0\"},{\"5\":\"0\"}]");
        JSONObject tmpJsonObject = new JSONObject( dataStorage.getSortedTopUsersByLevelResultAsJson());
        JSONArray actRes = new JSONArray(tmpJsonObject.getJSONArray("top").toString());

        report.append("\n");
        for (int i = 0; i < expRes.length(); i++){
                Iterator expIterator = expRes.getJSONObject(i).keys();
                String expKey = expIterator.next().toString();
                String expVal = expRes.getJSONObject(i).get(expKey).toString();

                Iterator actIterator = actRes.getJSONObject(i).keys();
                String actKey = actIterator.next().toString();
                String actVal = actRes.getJSONObject(i).get(actKey).toString();

            try {
                Assert.assertEquals(expVal, actVal);
                report.append("EXP: " + expVal + "  ACT: " + actVal + "\n");
            } catch (AssertionError e) {
                report.append("---> EXP: " + expVal + "  ACT: " + actVal + "\n");
                testResult = false;
            }
        }
        Assert.assertTrue(report.toString(), testResult);
    }

    @Test
    public void buildTopUsersInDescOrderByLeve10Result(){
        StringBuilder report = new StringBuilder();
        Boolean testResult = true;
        dataStorage.buildDescTopUsersByLevelResult(20,10);
        JSONArray expRes = new JSONArray("[{\"4\":\"0\"},{\"2\":\"0\"},{\"7\":\"0\"},{\"1\":\"0\"},{\"6\":\"0\"},{\"3\":\"0\"},{\"5\":\"0\"}]");

        JSONObject tmpJsonObject = new JSONObject( dataStorage.getSortedTopUsersByLevelResultAsJson());
        JSONArray actRes = new JSONArray(tmpJsonObject.getJSONArray("top").toString());

        report.append("\n");
        for (int i = 0; i < expRes.length(); i++){
            Iterator expIterator = expRes.getJSONObject(i).keys();
            String expKey = expIterator.next().toString();
            String expVal = expRes.getJSONObject(i).get(expKey).toString();

            Iterator actIterator = actRes.getJSONObject(i).keys();
            String actKey = actIterator.next().toString();
            String actVal = actRes.getJSONObject(i).get(actKey).toString();

            try {
                Assert.assertEquals(expVal, actVal);
                report.append("EXP: " + expVal + "  ACT: " + actVal + "\n");
            } catch (AssertionError e) {
                report.append("---> EXP: " + expVal + "  ACT: " + actVal + "\n");
                testResult = false;
            }
        }
        Assert.assertTrue(report.toString(), testResult);
    }

}
