import impl.HazelcastDataStorageImp;
import impl.SunNetHttpServerImpl;
import service.DataStorage;

public class App {

    public static void main(String[] args) {
        DataStorage dataStorage = new HazelcastDataStorageImp();
        dataStorage.startStorage();
        SunNetHttpServerImpl server= new SunNetHttpServerImpl(dataStorage);
        server.start();
    }

}
