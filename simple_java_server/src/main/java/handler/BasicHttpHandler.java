package handler;

import com.sun.net.httpserver.HttpExchange;
import core.Messages;
import service.DataStorage;

import java.io.IOException;
import java.io.OutputStream;

public class BasicHttpHandler {

    protected DataStorage dataStorage;
    protected HttpExchange httpExchange;

    public BasicHttpHandler(DataStorage dataStorage, HttpExchange httpExchange){
        this.dataStorage = dataStorage;
        this.httpExchange = httpExchange;
    }

    public Boolean isRequestMethodGET(HttpExchange httpExchange){
        if ( !"GET".equals(httpExchange.getRequestMethod())) {
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

    public void sendResponse(String response, HttpExchange httpExchange) {
        try (OutputStream os = httpExchange.getResponseBody() ) {
            httpExchange.sendResponseHeaders(200, response.getBytes().length);
            os.write(response.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Boolean isRequestMethodPUT(HttpExchange httpExchange){
        if ( !"PUT".equalsIgnoreCase(httpExchange.getRequestMethod())) {
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

}
