package test.com.apnigaadi.Vehicle_list;

import org.json.JSONException;
import org.json.JSONObject;

public class ExtraVehicleDetails {
    String location,odometer,baseprice,priceperkm,uri;
    JSONObject object;

    public ExtraVehicleDetails(String location, String odometer,  String priceperkm ,JSONObject pickup,JSONObject drop,String[] image) {
        object = new JSONObject();
        try {
          //  object.put("price",Integer.parseInt(baseprice));
            object.put("odometer",Integer.parseInt(odometer));
            object.put("location",location);
            object.put("pickup",pickup);
            object.put("drop",drop);
            object.put("image",image);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    public JSONObject getJsonObject() {
        return object;
    }
}
