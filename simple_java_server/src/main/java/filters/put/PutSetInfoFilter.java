package filters.put;

import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpExchange;
import core.Messages;
import org.apache.log4j.Logger;

import java.io.IOException;
import static core.Parser.parseJsonFromPutBodyToUserEntity;
import static core.Responser.sendResponse;

public class PutSetInfoFilter extends Filter {

    private final Logger LOG = Logger.getLogger(PutSetInfoFilter.class);
    private static final String FILTER_DESC = "BASIC 'GET' REQUESTS FILTER";

    public String description(){
        return FILTER_DESC;
    }

    public void doFilter(HttpExchange exchange, Chain chain){
        try {
            if (!parseJsonFromPutBodyToUserEntity(exchange)) {
                sendResponse(Messages._404(), exchange);
                return;
            }
            chain.doFilter(exchange);
        } catch (IOException e){
            sendResponse(Messages._500(), exchange);
            e.printStackTrace();
        }
    }


}
