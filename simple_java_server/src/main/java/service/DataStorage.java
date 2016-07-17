package service;

import entities.UserEntity;
import java.util.Map;

public interface DataStorage {

    public void startStorage();

    public UserEntity getUser (int userId);

    public Map<Integer, UserEntity> getUsers ();

    public void addUser(UserEntity userEntity);

    public void updateUserEntity(Integer key, UserEntity userEntity);

    public void buildDescTopUsersByLevelResult(int topSize, int level);

    public Map<Integer, UserEntity> getSortedTopUsersByLevelResult();

    public  String getSortedTopUsersByLevelResultAsJson();

}
