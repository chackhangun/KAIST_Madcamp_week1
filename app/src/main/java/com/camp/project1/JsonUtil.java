package com.camp.project1;

import org.json.JSONObject;
import org.json.JSONException;

public class JsonUtil {
    public static String toJson(Contact contact){
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", contact.getName());
            jsonObject.put("number",contact.getNumber());

            return jsonObject.toString();
        }catch(JSONException ex){
            ex.printStackTrace();
        }
        return null;
    }
}
