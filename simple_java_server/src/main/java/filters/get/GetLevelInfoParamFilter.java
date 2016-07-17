package filters.get;

import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpExchange;
import errors.DataParsingError;
import errors.InternalServerError;
import org.apache.log4j.Logger;
import static tools.Parser.parseIntParamFromUriPath;
import java.io.IOException;
import java.net.URI;

public class GetLevelInfoParamFilter extends Filter {

    private final Logger LOG = Logger.getLogger(GetLevelInfoParamFilter.class);

    private static final String FILTER_DESC = "'/levelinfo' PARAM PARSER";

    public String description(){
        return FILTER_DESC;
    }

    public void doFilter(HttpExchange exchange, Chain chain){
        try {
            URI uri = exchange.getRequestURI();
            exchange.setAttribute("levelId", parseIntParamFromUriPath(uri, "/levelinfo/") );
            chain.doFilter(exchange);
        } catch (IOException ex){
            throw new InternalServerError(ex.getMessage());
        } catch (DataParsingError er) {
            throw er;
        }
    }

}
