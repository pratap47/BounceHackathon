package test.com.apnigaadi.Vehicle_list;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UserData {
    JSONObject jsonObject;
    JSONArray jsonArray;
    public UserData(String name, String phone, String occupation) {
        jsonObject=new JSONObject();
        jsonArray= new JSONArray();
        try {
            jsonObject.put("name",name);
            jsonObject.put("email",phone);
            jsonObject.put("occupation",occupation);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }
}
