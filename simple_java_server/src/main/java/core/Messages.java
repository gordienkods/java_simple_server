package core;

import org.json.JSONObject;

/**
 * Created by Димон on 07.07.2016.
 */
public class  Messages {

    public static String _400(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Status", "error");
        jsonObject.put("Error code", "400");
        jsonObject.put("Reason", "wrong request method");
        return jsonObject.toString();
    }

    public static String _408(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Status", "error");
        jsonObject.put("Error code", "408");
        jsonObject.put("Reason", "no user with sent id");
        return jsonObject.toString();
    }

}
