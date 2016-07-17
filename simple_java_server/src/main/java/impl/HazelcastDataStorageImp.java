package impl;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.config.NetworkConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import tools.Sorter;
import entities.UserEntity;
import org.json.JSONArray;
import org.json.JSONObject;
import service.DataStorage;
import java.util.*;

public class HazelcastDataStorageImp implements DataStorage {

    private IMap<Integer, UserEntity> users;
    private Map<Integer, UserEntity> sortedTopUsersByLevelResult = new LinkedHashMap<>();
    private Integer levelId = 0;

    public void startStorage() {
        Config config = new Config();
        NetworkConfig network = config.getNetworkConfig();

        JoinConfig join = network.getJoin();
        join.getMulticastConfig()
                .setEnabled( true )
                .addTrustedInterface("127.0.0.1")
                .addTrustedInterface("192.168.1.219")
                .addTrustedInterface("192.168.1.11");

        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(config);
        users = hazelcastInstance.getMap("users");
    }

    public Map<Integer, UserEntity> getUsers() {
        return users;
    }

    public void addUser(UserEntity userEntity){
        users.set(userEntity.getUserId(), userEntity);
    }

    public UserEntity getUser (int userId){
        return users.get(userId);
    }

    public void updateUserEntity(Integer key, UserEntity userEntity){
        users.set(key, userEntity);
    }

    public void buildDescTopUsersByLevelResult(int topSize, int level){
        levelId = level;
        Map<Integer, UserEntity> sortedUsersByLevelResult = Sorter.sortUsersByResultsOnLevelByDescOrder(users, level);
        Set<Map.Entry<Integer, UserEntity>> entrySet = sortedUsersByLevelResult.entrySet();
        Iterator<Map.Entry<Integer,UserEntity>> iterator = entrySet.iterator();
        sortedTopUsersByLevelResult = new LinkedHashMap<>();
        if (topSize < sortedUsersByLevelResult.size()){
            for (int i = 0; i < topSize; i++){
                Map.Entry<Integer, UserEntity> entry = iterator.next();
                sortedTopUsersByLevelResult.put(entry.getKey(), entry.getValue());
            }
        } else {
            while (iterator.hasNext()){
                Map.Entry<Integer, UserEntity> entry = iterator.next();
                sortedTopUsersByLevelResult.put(entry.getKey(), entry.getValue());
            }
        }
    }

    public Map<Integer, UserEntity> getSortedTopUsersByLevelResult() {
        return sortedTopUsersByLevelResult;
    }

    public String getSortedTopUsersByLevelResultAsJson(){
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        for (Map.Entry<Integer, UserEntity> entry : sortedTopUsersByLevelResult.entrySet() ){
            String key = entry.getValue().getUserId().toString();
            String value = entry.getValue().getSpecificLevelResult().toString();
            jsonArray.put(new JSONObject().put(key, value));
        }
        jsonObject.put("sorted_level", levelId);
        jsonObject.put("top", jsonArray);

        return jsonObject.toString();
    }

}
