package core;

import org.json.JSONObject;

public class  Messages {

    public static String _405(){
        return msgAsJson("error", "405", "Method Not Allowed");
    }

    public static String _404(){
        return msgAsJson("error", "404", "Not found");
    }

    public static String _500(){
        return msgAsJson("error", "500", "Internal server error");
    }

    public static String _201(){
        return msgAsJson("success", "201", "Accepted");
    }

    private static String msgAsJson(String status, String code, String msg){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Status", status);
        jsonObject.put("Code", code);
        jsonObject.put("Reason", msg);
        return jsonObject.toString();
    }

}
