package filters.get;

import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpExchange;
import exceptions.DataParsingError;
import exceptions.InternalServerError;
import org.apache.log4j.Logger;
import static core.Parser.parseIntParamFromUriPath;
import java.io.IOException;
import java.net.URI;

public class GetUserInGoParamFilter extends Filter {

    private final Logger LOG = Logger.getLogger(GetUserInGoParamFilter.class);
    private static final String FILTER_DESC = "'/useringo' PARAM PARSER";

    public String description(){
        return FILTER_DESC;
    }

    public void doFilter(HttpExchange exchange, Chain chain){
        try {
            URI uri = exchange.getRequestURI();
            exchange.setAttribute("userId", parseIntParamFromUriPath(uri, "/useringo/") );
            chain.doFilter(exchange);
        }catch (IOException ex){
            throw new InternalServerError(ex.getMessage());
        } catch (DataParsingError er) {
            throw er;
        }
    }
}
