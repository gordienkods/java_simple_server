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
            LOG.info(" REQUEST" + " [ " + exchange.getRequestMethod() +" ] " +
                    " FROM [ " + exchange.getRequestURI().getHost() + " ] to [ " +
                    exchange.getRequestURI() + " ] BODY [ " +
           exchange.getAttribute("userEntytJsonInRequestBody") + " ] ");
            LOG.info("RESPONSE" + " [ " + exchange.getResponseCode() +" ] " + " [ " + msg + " ]\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Boolean isRequestMethod(String expectedRequestMethod, HttpExchange exchange){
        String actualRequestMethod = exchange.getRequestMethod();
        if ( !expectedRequestMethod.equalsIgnoreCase(actualRequestMethod)) {
            return false;
        }
        return true;
    }


}
