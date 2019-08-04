package test.com.apnigaadi.Vehicle_list;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;

public class Listitem {
    private String mBrand ,mModel, mId, mFuel,mSeats;
    int mPricePerKm,mBasePrice;
    private URI mFrontImage;
    public Listitem(JSONObject object) {
        try {
            mBrand = object.getString("manufacturer");
            mModel=object.getString("model");
            mId=object.getString("_id");
            mFuel = object.getString("fueltype");
            mSeats = object.getString("seats");
            mPricePerKm = object.getInt("priceperkm");
            mBasePrice=object.getInt("price");



        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getmPricePerKm() {
        return mPricePerKm;
    }

    public void setmPricePerKm(int mPricePerKm) {
        this.mPricePerKm = mPricePerKm;
    }

    public int getmBasePrice() {
        return mBasePrice;
    }

    public void setmBasePrice(int mBasePrice) {
        this.mBasePrice = mBasePrice;
    }

    public String getmBrand() {
        return mBrand;
    }
    public void setmBrand(String mBrand) {
        this.mBrand = mBrand;
    }

    public String getmModel() {
        return mModel;
    }

    public void setmModel(String mModel) {
        this.mModel = mModel;
    }

    public String getmId() {
        return mId;
    }

    public String getmFuel() {
        return mFuel;
    }

    public void setmFuel(String mFuel) {
        this.mFuel = mFuel;
    }

    public String getmSeats() {
        return mSeats;
    }

    public void setmSeats(String mSeats) {
        this.mSeats = mSeats;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public URI getmFrontImage() {
        return mFrontImage;
    }

    public void setmFrontImage(URI mFrontImage) {
        this.mFrontImage = mFrontImage;
    }
}
