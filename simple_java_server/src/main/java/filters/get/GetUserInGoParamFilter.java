package filters.get;

import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpExchange;
import static core.Parser.parseIntParamFromUriPath;
import java.io.IOException;

public class GetUserInGoParamFilter extends Filter {

    private static final String FILTER_DESC = "'/useringo' PARAM PARSER";

    public String description(){
        return FILTER_DESC;
    }

    public void doFilter(HttpExchange exchange, Chain chain){
        try {
            parseIntParamFromUriPath("/useringo/", "userId", exchange);
            chain.doFilter(exchange);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
