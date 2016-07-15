package core;

import com.sun.net.httpserver.HttpExchange;
import static core.Responser.isRequestMethod;
import java.io.IOException;
import java.io.OutputStream;

public class Responser {

    public static void sendRequest(String msg, HttpExchange exchange){
        try (OutputStream os = exchange.getResponseBody() ) {
            exchange.sendResponseHeaders(200, msg.getBytes().length);
            os.write(msg.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Boolean isRequestMethod(String expectedRequestMethod, HttpExchange httpExchange){
        String actualRequestMethod = httpExchange.getRequestMethod();
        if ( !expectedRequestMethod.equalsIgnoreCase(actualRequestMethod)) {
            try (OutputStream os = httpExchange.getResponseBody() ) {
                httpExchange.sendResponseHeaders(200, Messages._405().getBytes().length);
                os.write(Messages._405().getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
        return true;
    }



}
