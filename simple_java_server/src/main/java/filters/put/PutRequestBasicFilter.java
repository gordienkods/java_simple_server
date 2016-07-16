package filters.put;

import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpExchange;
import core.Messages;
import org.apache.log4j.Logger;
import static core.Responser.isRequestMethod;
import static core.Responser.sendResponse;

public class PutRequestBasicFilter extends Filter {

    private static final Logger LOG = Logger.getLogger(PutRequestBasicFilter.class);

    private static final String FILTER_DESC = "BASIC 'GET' REQUESTS FILTER";

    public String description(){
        return FILTER_DESC;
    }

    public void doFilter(HttpExchange exchange, Chain chain){
        if(isRequestMethod("PUT", exchange)){
            try {
                chain.doFilter(exchange);
            } catch (Throwable t){
                 /*NOP*/
                sendResponse(Messages._500(), exchange);
                LOG.error(Messages.LOG_ERROR_DECORATION);
                LOG.error("METHOD [PUT] ERROR HANDLING: ", t);
                LOG.error(Messages.LOG_ERROR_DECORATION + "\n");
            }
        } else {
            sendResponse(Messages._405(), exchange);
        }
    }

}
