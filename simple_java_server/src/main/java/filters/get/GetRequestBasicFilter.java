package filters.get;

import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import static core.Responser.isRequestMethod;

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
}
