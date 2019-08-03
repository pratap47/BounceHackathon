package test.com.apnigaadi.Vehicle_list;

import org.json.JSONException;
import org.json.JSONObject;

public class CarData {
    public JSONObject jsonObject;
    String Model, Transmission, Type, Brand, Fueltype, Id;
    int Seats;

    public CarData(String model, String transmission, String type, String brand, String fueltype, String seats, String id) {
        Model = model;
        Transmission = transmission;
        Type = type;
        Brand = brand;
        Fueltype = fueltype;
        Seats = Integer.parseInt(seats);
        Id = id;
    }

    public JSONObject getJsonObject() {
        jsonObject = new JSONObject();

        try {
            jsonObject.put("seats", Seats);
            jsonObject.put("model", Model);
            jsonObject.put("transmission", Transmission);
            jsonObject.put("type", Type);
            jsonObject.put("manufacturer", Brand);
            jsonObject.put("fueltype", Fueltype);
            jsonObject.put("user", Id);
            System.out.println(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
