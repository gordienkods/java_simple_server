package service;

import entity.UserEntity;

public interface DataStorage {

    public void  connectToMasterStorage (String ip);

    public void startMasterStorage(int port);

    public UserEntity getUser (int userId);

    public void updateUserEntity(Integer key, UserEntity userEntity);

}
