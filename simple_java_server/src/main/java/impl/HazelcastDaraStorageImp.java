package impl;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;


import java.util.Map;

public class HazelcastDaraStorageImp {

    private Map<Integer, String> hazelcastMap;
    private String storageName;



    public void startMasterStorage(int port, String storageName) {

        Config config = new Config();
        config.getNetworkConfig().setPort(port);

            HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(config);
            hazelcastMap = hazelcastInstance.getMap(storageName);
            hazelcastMap.put(1,"a");
            hazelcastMap.put(2,"b");
            hazelcastMap.put(3,"c");

            System.err.println("-------------- server map --------------");
            for (Map.Entry<Integer, String> entry : hazelcastMap.entrySet()){

                System.err.println("  KEY : " + entry.getKey());
                System.err.println("VALUE : " + entry.getValue());
                System.err.println("");
            }

        }

        public void connectToMasterStorage (String ip, String storageName) {
            ClientConfig clientConfig = new ClientConfig();
            clientConfig.addAddress(ip);

            HazelcastInstance client = HazelcastClient.newHazelcastClient( clientConfig );
            Map<Integer, String> hazelcastMap = client.getMap(storageName);
            System.err.println("-------------- client map --------------");
            for (Map.Entry<Integer, String> entry : hazelcastMap.entrySet()){
                System.err.println("  KEY : " + entry.getKey());
                System.err.println("VALUE : " + entry.getValue());
                System.err.println("");
            }
        }

}
