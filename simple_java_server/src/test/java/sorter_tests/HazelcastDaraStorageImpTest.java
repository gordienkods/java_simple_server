package sorter_tests;

import entity.UserEntity;
import org.junit.BeforeClass;
import org.junit.Test;

public class HazelcastDaraStorageImpTest {

    private UserEntity user1;
    private UserEntity user2;
    private UserEntity user3;
    private UserEntity user4;
    private UserEntity user5;
    private UserEntity user6;
    private UserEntity user7;

    @BeforeClass
    public void initUsers(){
        user1 = new UserEntity("{\"userId\":1,\"levels_and_results\":[{\"5\":155}]}");
        user2 = new UserEntity("{\"userId\":2,\"levels_and_results\":[{\"1\":211},{\"3\":233},{\"4\":244},{\"5\":255}]}");
        user3 = new UserEntity("{\"userId\":3,\"levels_and_results\":[{\"1\":311},{\"2\":322},{\"4\":344},{\"5\":355}]}");
        user4 = new UserEntity("{\"userId\":4,\"levels_and_results\":[{\"1\":411},{\"2\":422},{\"3\":433},{\"5\":455}]}");
        user5 = new UserEntity("{\"userId\":5,\"levels_and_results\":[{\"1\":511}]}");
        user6 = new UserEntity("{\"userId\":6,\"levels_and_results\":[{\"1\":611},{\"2\":622},{\"3\":633},{\"4\":644},{\"5\":655}]}");
        user7 = new UserEntity("{\"userId\":7,\"levels_and_results\":[{\"1\":711},{\"2\":722},{\"3\":733},{\"4\":744},{\"5\":755}]}");
    }

    @Test
    public void buildTopUsersInDescOrderByLeve_1_result(){

    }
}
