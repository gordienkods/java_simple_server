package service;

import entity.UserEntity;

import java.util.Map;

public interface DataStorage {

    public void  connectToMasterStorage (String ip);

    public void startMasterStorage(int port);

    public UserEntity getUser (int userId);

    public Map<Integer, UserEntity> getUsers ();

    public void updateUserEntity(Integer key, UserEntity userEntity);

    public void buildDescTopUsersByLevelResult(int topSize, int level);

    public Map<Integer, UserEntity> getSortedTopUsersByLevelResult();

}
