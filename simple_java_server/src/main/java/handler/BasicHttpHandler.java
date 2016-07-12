package handler;

import com.sun.net.httpserver.HttpExchange;
import core.Messages;
import service.DataStorage;

import java.io.IOException;
import java.io.OutputStream;

public class BasicHttpHandler {

    protected DataStorage dataStorage = null;
    protected HttpExchange httpExchange = null;

    public BasicHttpHandler(DataStorage dataStorage){
        this.dataStorage = dataStorage;
    }

    protected Boolean isRequestMethod(String expectedRequestMethod){
        String actualRequestMethod = httpExchange.getRequestMethod();
        if ( !expectedRequestMethod.equalsIgnoreCase(actualRequestMethod)) {
            try (OutputStream os = httpExchange.getResponseBody() ) {
                httpExchange.sendResponseHeaders(200, Messages._400().getBytes().length);
                os.write(Messages._400().getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
        return true;
    }

    protected void sendResponse(String response) {
        try (OutputStream os = httpExchange.getResponseBody() ) {
            httpExchange.sendResponseHeaders(200, response.getBytes().length);
            os.write(response.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
