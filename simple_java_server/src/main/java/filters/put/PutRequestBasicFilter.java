package filters.put;

import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpExchange;
import core.Messages;
import java.io.IOException;
import static core.Responser.isRequestMethod;
import static core.Responser.sendResponse;

public class PutRequestBasicFilter extends Filter {

    private static final String FILTER_DESC = "BASIC 'GET' REQUESTS FILTER";

    public String description(){
        return FILTER_DESC;
    }

    public void doFilter(HttpExchange exchange, Chain chain){
        if(isRequestMethod("PUT", exchange)){
            try {
                chain.doFilter(exchange);
            } catch (IOException e){
                e.printStackTrace();
            }
        } else {
            sendResponse(Messages._405(), exchange);
        }
    }

}
