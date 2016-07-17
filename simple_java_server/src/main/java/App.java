import impl.HazelcastDataStorageImp;
import impl.SunNetHttpServerImpl;
import service.DataStorage;
import service.Server;

public class App {

    public static void main(String[] args) {
        DataStorage dataStorage = new HazelcastDataStorageImp(getTrustedInterfaces(args));
        dataStorage.startStorage();
        Server httpServer = new SunNetHttpServerImpl(dataStorage, getServerPort(args), getBackLog(args));
        httpServer.start();
    }

    private static Integer  getServerPort(String [] args) {
        return Integer.parseInt(args[0]);
    }

    private static Integer getBackLog(String [] args) {
        return Integer.parseInt(args[1]);
    }

    private static String [] getTrustedInterfaces(String [] args) {
        String lineWithIp = args[2];
        return lineWithIp.split(",");
    }



}
