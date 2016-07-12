import entity.UserEntity;

import impl.HazelcastDaraStorageImp;
import impl.SunNetHttpServerImpl;
import service.DataStorage;
import service.Server;

import java.util.Map;

/**
 * Created by Димон on 04.07.2016.
 */
public class App {



//    public static void main(String[] args) {
//        final int backlog = 64;
//        final InetSocketAddress serverPort = new InetSocketAddress(85);
//
//
//        try {
//            HttpServerProvider httpServerProvider = DefaultHttpServerProvider.provider();
//            HttpServer server = httpServerProvider.createHttpServer(serverPort, backlog);
//            server.setExecutor(Executors.newCachedThreadPool());
//
//
//            HttpContext context = server.createContext("/test");
//
//
//            context.setHandler(new HttpHandler() {
//                public void handle(HttpExchange httpExchange) throws IOException {
//
//                    System.err.println("httpExchange.getRequestMethod() " + httpExchange.getRequestMethod() );
//                    System.err.println("httpExchange.getRequestURI() " + httpExchange.getRequestURI().toString() );
//                    System.err.println("httpExchange.getRequestHeaders() " + httpExchange.getRequestHeaders());
//
////                    for(Map.Entry<String, List<String>> entry : httpExchange.getRequestHeaders().entrySet()){
////                        System.err.println("KEY");entry.getKey()
////                    }
////                    System.err.println("httpExchange.getRequestURI() " + httpExchange.getRequestHeaders() );
//
//                    final OutputStream output = httpExchange.getResponseBody();
//                    Map<String,String> hashmap = new HashMap<String, String>();
//
//                    hashmap.put("key3", "value3");
//                    String responseJson = new JSONObject(hashmap).toString();
//                    httpExchange.sendResponseHeaders(404,responseJson.getBytes().length);
//                    System.err.println(responseJson);
//                    output.write(responseJson.getBytes());
//                    output.flush();
//                    httpExchange.close();
//                    httpExchange.close();
//                }
//            });
//
//            server.start();
//
//        } catch (Throwable t) {
//            t.printStackTrace();
//        }
//    }

//    public static void main(String[] args) {
//
//
//        HazelcastDaraStorageImp storage = new HazelcastDaraStorageImp();
//
//        storage.startMasterStorage(5900, "mp");
//        storage.connectToMasterStorage("192.168.1.219:5900", "mp");
//
//
//    }

//    public static void main(String[] args) {
//        Server server = new SunNetHttpServerImpl();
//        server.start();
//    }

    public static void main(String[] args) {
        UserEntity userEntity = new UserEntity(17);
        userEntity.addLevelAndResult(1, 10);
        userEntity.addLevelAndResult(2, 7);
        userEntity.addLevelAndResult(3, 55);
        userEntity.addLevelAndResult(4, 2);
        userEntity.addLevelAndResult(5, 55);
        userEntity.addLevelAndResult(6, 3);
        userEntity.addLevelAndResult(7, 1);

        userEntity.buildDescTop(5);

        String toJson = userEntity.toJson();
        System.err.println("TO JSON: " + toJson);
        UserEntity fromJSon = new UserEntity(toJson);
        System.err.println("FROM JSON: " + fromJSon.toJson());


    }

//    public static void main(String[] args) {
//        HazelcastDaraStorageImp dataStorage = new HazelcastDaraStorageImp();
//        dataStorage.startMasterStorage(65);
//
//        UserEntity user1 = new UserEntity(1);
//        user1.addLevelAndResult(1,11);
//        user1.addLevelAndResult(2,22);
//        user1.addLevelAndResult(3,33);
//
//
//        UserEntity user2 = new UserEntity(2);
//        user2.addLevelAndResult(1,13);
//        user2.addLevelAndResult(2,13);
//        user2.addLevelAndResult(3,13);
//
//        UserEntity user3 = new UserEntity(3);
//        user3.addLevelAndResult(1,11);
//        user3.addLevelAndResult(2,22);
//        user3.addLevelAndResult(3,55);
//
//        dataStorage.addUser(user1);
//        dataStorage.addUser(user2);
//        dataStorage.addUser(user3);
//
//        System.err.println("-------- main before sort ---------");
//        for (Map.Entry<Integer, UserEntity> entityEntry : dataStorage.getUsers().entrySet()){
//
//            System.err.println(entityEntry.getKey() + "  " + entityEntry.getValue().toJson());
//
//        }
//        System.err.println("-----------------------");
//
//
//        dataStorage.buildDescTopUsersByLevelResult(100, 2);
//
////        System.err.println("-------- main after sort ---------");
////        for (Map.Entry<Integer, UserEntity> entityEntry : dataStorage.getUsers().entrySet()){
////            System.err.println("-------- main ---------");
////            System.err.println(entityEntry.getKey() + "  " + entityEntry.getValue().getUserId() + "  " + entityEntry.getValue().getLevelResult());
////            System.err.println("-----------------------");
////        }
//
//        dataStorage.print();
//
//
//    }

//    public static void main(String[] args) {
//        DataStorage dataStorage = new HazelcastDaraStorageImp();
//        dataStorage.startMasterStorage(89);
//        Server server = new SunNetHttpServerImpl();
//        server.setDataStorage(dataStorage);
//        server.start();
//    }


}
