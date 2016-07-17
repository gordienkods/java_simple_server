package filters.get;

import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpExchange;
import tools.JsonMSG;
import org.apache.log4j.Logger;

import static tools.Responser.isRequestMethod;
import static tools.Responser.sendResponse;

public class GetRequestBasicFilter extends Filter {

    private final Logger LOG = Logger.getLogger(GetRequestBasicFilter.class);
    private static final String FILTER_DESC = "BASIC 'GET' REQUESTS FILTER";

    public String description(){
        return FILTER_DESC;
    }

    public void doFilter(HttpExchange exchange, Chain chain){
        if(isRequestMethod("GET", exchange)){
            try {
                chain.doFilter(exchange);
            } catch (Throwable t){
                /*NOP*/
                sendResponse(JsonMSG.errorMsgAsJson(JsonMSG.ITERNAL_SERVER_ERROR), exchange);
                LOG.error(JsonMSG.LOG_ERROR_DECORATION);
                LOG.error("METHOD [GET] ERROR HANDLING: ", t);
                LOG.error(JsonMSG.LOG_ERROR_DECORATION + "\n");
            }
        } else {
            sendResponse(JsonMSG.errorMsgAsJson(JsonMSG.METHOD_NOT_ALLOWED), exchange);
        }
    }
}
