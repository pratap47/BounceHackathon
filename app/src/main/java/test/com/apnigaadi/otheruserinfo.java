package test.com.apnigaadi;

import org.json.JSONException;
import org.json.JSONObject;

public class otheruserinfo {

    String mManufacturer , mModel,id;
    public otheruserinfo(JSONObject object) {
        try {
            id=object.getString("_id");
            mModel = object.getString("model");
            mManufacturer = object.getString("manufacturer");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
