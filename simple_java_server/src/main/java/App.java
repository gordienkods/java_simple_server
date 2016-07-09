import entity.UserEntity;
import impl.SunNetHttpServerImpl;
import service.Server;

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
        userEntity.addLevelAndResult(5, 2);
        userEntity.addLevelAndResult(6, 3);
        userEntity.addLevelAndResult(7, 1);

        userEntity.sortResultsOnAllLevels();
        userEntity.getResultsOnLevels();
    }

}
