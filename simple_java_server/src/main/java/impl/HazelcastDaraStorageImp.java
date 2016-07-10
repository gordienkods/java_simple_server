package impl;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import core.Sorter;
import entity.UserEntity;
import service.DataStorage;


import java.util.*;

public class HazelcastDaraStorageImp implements DataStorage {

    private IMap<Integer, UserEntity> users;
    private Map<Integer, UserEntity> sortedTopUsersByLevelResult = new LinkedHashMap<>();

    public void startMasterStorage(int port) {
        Config config = new Config();
        config.getNetworkConfig().setPort(port);

        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(config);
        users = hazelcastInstance.getMap("users");
    }

    public void connectToMasterStorage (String ip) {
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.addAddress(ip);

        HazelcastInstance client = HazelcastClient.newHazelcastClient( clientConfig );
        users = client.getMap("users");
//        System.err.println("-------------- client map --------------");
//        for (Map.Entry<Integer, String> entry : hazelcastMap.entrySet()){
//            System.err.println("  KEY : " + entry.getKey());
//            System.err.println("VALUE : " + entry.getValue());
//            System.err.println("");
//        }
    }

    public Map<Integer, UserEntity> getUsers() {
        return users;
    }

    public void addUser(UserEntity userEntity){
        users.put(userEntity.getUserId(), userEntity);
    }

    public UserEntity getUser (int userId){
        return users.get(userId);
    }

    public void buildDescTopUsersByLevelResult(int topSize, int level){
        for (Map.Entry<Integer, UserEntity> entry : users.entrySet()){
            entry.getValue().setLevelResult(level);
        }

        Map<Integer, UserEntity> sortedUsersByLevelResult = Sorter.sortUsersByResultsOnLevel(users);
        Set<Map.Entry<Integer, UserEntity>> entrySet = sortedUsersByLevelResult.entrySet();
        Iterator<Map.Entry<Integer,UserEntity>> iterator = entrySet.iterator();

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

    public void print(){
        for(Map.Entry<Integer, UserEntity> entry : sortedTopUsersByLevelResult.entrySet()){
            System.err.println("");
            System.err.println("KEY: " + entry.getKey() + "\n" +
                    "USER ID: " + entry.getValue().getUserId() + "\n" +
            "VALUE ON LEVEL 2: " +  entry.getValue().getLevelResult());
            System.err.println("");
        }
    }

}
