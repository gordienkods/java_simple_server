package core;

import com.sun.net.httpserver.HttpExchange;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class Responser {

    private static final Logger LOG = Logger.getLogger(Messages.class);


    public static void sendResponse(String msg, HttpExchange exchange){
        try (OutputStream os = exchange.getResponseBody() ) {
            exchange.sendResponseHeaders(200, msg.getBytes().length);
            os.write(msg.getBytes());
            LOG.info("SEND RESPONSE: ");
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

    public static String getRequestBodyAsString(HttpExchange exchange){
        try (InputStream is = exchange.getRequestBody()){
            Scanner scanner = new Scanner(is);
            return scanner.next();
        } catch (IOException e) {
            sendResponse(Messages._500(), exchange);
            e.printStackTrace();
        }
        return null;
    }



}
