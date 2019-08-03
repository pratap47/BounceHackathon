package test.com.apnigaadi;

import org.json.JSONException;
import org.json.JSONObject;

public class otheruserinfo {

    String mManufacturer , mModel;
    public otheruserinfo(JSONObject object) {
        try {
            mModel = object.getString("model");
            mManufacturer = object.getString("manufacturer");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getmManufacturer() {
        return mManufacturer;
    }

    public void setmManufacturer(String mManufacturer) {
        this.mManufacturer = mManufacturer;
    }

    public String getmModel() {
        return mModel;
    }

    public void setmModel(String mModel) {
        this.mModel = mModel;
    }
}
