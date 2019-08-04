package test.com.apnigaadi.Vehicle_list;

import org.json.JSONException;
import org.json.JSONObject;

public class DateString {
    String mDate,  mMonth,  mYear,mdayEnd,mMonthEnd,mYearEnd;
    JSONObject object;
    float lat,lon;

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public String getmMonth() {
        return mMonth;
    }

    public void setmMonth(String mMonth) {
        this.mMonth = mMonth;
    }

    public String getmYear() {
        return mYear;
    }

    public void setmYear(String mYear) {
        this.mYear = mYear;
    }

    public String getMdayEnd() {
        return mdayEnd;
    }

    public void setMdayEnd(String mdayEnd) {
        this.mdayEnd = mdayEnd;
    }

    public String getmMonthEnd() {
        return mMonthEnd;
    }

    public void setmMonthEnd(String mMonthEnd) {
        this.mMonthEnd = mMonthEnd;
    }

    public String getmYearEnd() {
        return mYearEnd;
    }

    public void setmYearEnd(String mYearEnd) {
        this.mYearEnd = mYearEnd;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public JSONObject getDate(){
        try {
            object.put("month",mMonth);
            object.put("year",mYear);
            object.put("date",mDate);
            object.put("latitude",lat);
            object.put("longitude",lon);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }
}
