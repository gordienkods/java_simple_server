package filters;

import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpExchange;
import core.Messages;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;


public class GetLevelInfoParamFilter extends Filter {

    private static final String FILTER_DESC = "'/levelinfo' PARAM PARSER";

    public String description(){
        return FILTER_DESC;
    }

    public void doFilter(HttpExchange exchange, Chain chain){
        try {
            parseParam(exchange);
            chain.doFilter(exchange);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void parseParam(HttpExchange exchange){
        URI uri = exchange.getRequestURI();
        String fullPath = uri.getPath();
        String levelId = "undefined";
        try {
            levelId = fullPath.replace("/levelinfo/", "");
        } catch (Exception e1) {
            try (OutputStream os = exchange.getResponseBody() ) {
                exchange.sendResponseHeaders(200, Messages._405().getBytes().length);
                os.write(Messages._405().getBytes());
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        try {
            Integer integerLevelId = Integer.parseInt(levelId);
        } catch (NumberFormatException e) {
            try (OutputStream os = exchange.getResponseBody() ) {
                exchange.sendResponseHeaders(200, Messages._404("param 'level_id' value '" + levelId).getBytes().length);
                os.write(Messages._404("param 'level_id' value '" + levelId).getBytes());
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        exchange.setAttribute("levelId", levelId);
        System.err.println("GET LEVEL INFO: " + exchange.getAttribute("levelId").toString());
    }

}
