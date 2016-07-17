package tools;

import org.json.JSONObject;

public class JsonMSG {

    public static final String LOG_ERROR_DECORATION = "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!";

    public static final String ITERNAL_SERVER_ERROR = "internal server error";

    public static final String METHOD_NOT_ALLOWED = "method not allowed";

    public static String errorMsgAsJson(String reason){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", "error");
        jsonObject.put("info", reason);
        return jsonObject.toString();
    }

    public static String successMsgAsJson(String reason){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", "success");
        jsonObject.put("info", reason);
        return jsonObject.toString();
    }

}
