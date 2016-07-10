package impl;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import core.Sorter;
import entity.UserEntity;


import java.util.*;

public class HazelcastDaraStorageImp {

    private Map<Integer, UserEntity> users;
    private Map<Integer, UserEntity> sorteredTopUsersByLevelResul;

    public void startMasterStorage(int port) {
        Config config = new Config();
        config.getNetworkConfig().setPort(port);

        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(config);
        users = hazelcastInstance.getMap("users");
    }

    public void connectToMasterStorage (String ip, String storageName) {
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
                sorteredTopUsersByLevelResul.put(entry.getKey(), entry.getValue());
            }
        } else {
            while (iterator.hasNext()){
                Map.Entry<Integer, UserEntity> entry = iterator.next();
                sorteredTopUsersByLevelResul.put(entry.getKey(), entry.getValue());
            }
        }
    }

}
