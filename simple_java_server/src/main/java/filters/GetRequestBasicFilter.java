package filters;

import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpExchange;
import core.Messages;
import java.io.IOException;
import java.io.OutputStream;

public class GetRequestBasicFilter extends Filter {

    private static final String FILTER_DESC = "BASIC 'GET' REQUESTS FILTER";

    public String description(){
        return FILTER_DESC;
    }

    public void doFilter(HttpExchange exchange, Chain chain){
        if(isRequestMethod("GET", exchange)){
            try {
                chain.doFilter(exchange);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private Boolean isRequestMethod(String expectedRequestMethod, HttpExchange httpExchange){
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
