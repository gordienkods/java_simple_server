package filters.put;

import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpExchange;
import core.Messages;
import exceptions.DataParsingError;
import exceptions.InternalServerError;

import static core.Parser.parseJsonFromPutBodyRequestToUserEntity;
import static core.Responser.sendResponse;

public class PutSetInfoFilter extends Filter {

    private static final String FILTER_DESC = "BASIC 'GET' REQUESTS FILTER";

    public String description(){
        return FILTER_DESC;
    }

    public void doFilter(HttpExchange exchange, Chain chain){
        try {
            if (!parseJsonFromPutBodyRequestToUserEntity(exchange)) {
                sendResponse(Messages._404(), exchange);
                return;
            }
            chain.doFilter(exchange);
        } catch (DataParsingError e) {
            throw e;
        }
        catch (Throwable t){
           throw new InternalServerError();
        }
    }


}
