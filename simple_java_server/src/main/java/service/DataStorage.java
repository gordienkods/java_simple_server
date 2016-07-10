package service;

public interface DataStorage {

    public void  connectToMasterStorage (String ip);

    public void startMasterStorage(int port);

}
