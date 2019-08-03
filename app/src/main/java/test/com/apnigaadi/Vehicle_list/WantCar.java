package test.com.apnigaadi.Vehicle_list;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import test.com.apnigaadi.R;

public class WantCar extends Fragment {

    Button btnSubmit ;
    static final int DATE_DIALOG_ID = 0;
    EditText mBodyText,mBodyend;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Listitem> listitems;
    private int code =101;
    public static boolean mLocationPermissionGranted = false;
    Intent i;
    FusedLocationProviderClient fusedLocationProviderclient;
    private Location currentlocation;

    public WantCar() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View w= inflater.inflate(R.layout.fragment_want_car, container, false);

        mBodyText= w.findViewById(R.id.mBodyText);
        mBodyend = w.findViewById(R.id.mBodyEnd);
        btnSubmit = w.findViewById(R.id.btnsubmit);

      //  DateString dateObject = new DateString(Integer.parseInt(mDay),Integer.parseInt(mMonth),Integer.parseInt(mYear));

        Toast.makeText(getActivity(),"want car",Toast.LENGTH_LONG).show();
        recyclerView = w.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listitems = new ArrayList<>();
        final DateString dateString = new DateString();
        //requestQueue = Volley.newRequestQueue(getApplicationContext());

        final String url = "https://bounce-hackathon.herokuapp.com/api/sortvehicle?pick="+dateString.getmDate()+"&drop="+dateString.getMdayEnd()+"&lat="+dateString.getLat()+"lon=7"+dateString.getLon();



    getLocationPermission();
    getDeviceLocation();
        SharedPreferences prefs = getActivity().getSharedPreferences("location", getActivity().MODE_PRIVATE);

            float lat = prefs.getFloat("lat",  0);//"No name defined" is the default value.
            float lon = prefs.getFloat("long", 0); //0 is the default value.

        dateString.setLat(lat);
        dateString.setLon(lon);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date = mBodyText.getText().toString();
                String dateend = mBodyend.getText().toString();
                String[] arrend = dateend.split("-");
                String[] arr = date.split("-");
                String mDay=arr[0];
                String mMonth=arr[1];
                String mYear=arr[2];
                dateString.setmDate(mDay);
                dateString.setmMonth(mMonth);
                dateString.setmYear(mYear);
                dateString.setMdayEnd(arrend[0]);
                dateString.setmMonthEnd(arrend[1]);
                JsonArrayRequest ExampleRequest = new JsonArrayRequest(Request.Method.GET, url,null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        System.out.println(response);
                        try {
                            int i;
                            for(i=0;i<response.length();i++) {
                                JSONObject object = response.getJSONObject(i);
                                Listitem list  = new Listitem(object);
                                listitems.add(list);                                //2440004
                            }
                            adapter = new RentCarAdapter(listitems,getActivity());
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //This code is executed if there is an error.
                        System.out.println(error);
                    }
                });
                Mysingleton.getInstance(getActivity()).addToRequestqueue(ExampleRequest);
            }
        });


         return w;

    }



    private void getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(getContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED)
        {
            if (ContextCompat.checkSelfPermission(getContext(),
                    Manifest.permission.ACCESS_COARSE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionGranted = true;

                //If the location permission has been granted, then start the TrackerService//
//                i =  new Intent(getActivity(), TrackingService.class);
//                getActivity().startService(i);


            }
            else {

                //If the app doesn’t currently have access to the user’s location, then request access//

                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        code);
            }
        }
        else
        {

            //If the app doesn’t currently have access to the user’s location, then request access//

            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    code);
        }

    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //Toast.makeText(this,"We are in onRequestPermission",Toast.LENGTH_SHORT).show();
//If the permission has been granted...//

        mLocationPermissionGranted = false;
        switch (requestCode) {
            case 101: {
                if (grantResults.length > 0) {
                    for (int j = 0; j < grantResults.length; j++) {
                        if (grantResults[j] != PackageManager.PERMISSION_GRANTED) {
                            mLocationPermissionGranted = true;
                            //...then start the GPS tracking service//

                            Toast.makeText(getContext(), "onRequestPermissionResult", Toast.LENGTH_SHORT).show();
//                            i =  new Intent(getActivity(), TrackingService.class);
//                            getActivity().startService(i);

                            return;
                        }
                        else
                        {
                            //If the user denies the permission request, then display a toast with some more information//

                            Toast.makeText(getContext(), "Please enable location services to allow GPS tracking", Toast.LENGTH_LONG).show();

                        }
                    }

                }
            }
        }
    }



    private void getDeviceLocation() {
        /*
         * Get the best and most recent location of the device, which may be null in rare
         * cases when a location is not available.
         */


//        mDatabase1 = FirebaseDatabase.getInstance().getReference();
        LocationRequest request = new LocationRequest();
        //request.setInterval(10000);
//        request.setFastestInterval(1000);
        request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        fusedLocationProviderclient = LocationServices.getFusedLocationProviderClient(getActivity());
//        SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
//        uid=sharedPreferences.getString("Uid",Default);
//        phoneno=sharedPreferences.getString("Phone",Default);     //Default value get displayed when no data is entered in main activity
//        name=sharedPreferences.getString("name",Default);

//        mDatabase1=mDatabase1.child(phoneno);
//      map.alert();

        try {
            if (WantCar.mLocationPermissionGranted) {
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
                                != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                fusedLocationProviderclient.requestLocationUpdates(request,
                        new LocationCallback() {
                            @Override
                            public void onLocationResult(LocationResult locationresult) {
//

                                currentlocation= locationresult.getLastLocation();
                                SharedPreferences.Editor editor = getActivity().getSharedPreferences("location", getActivity().MODE_PRIVATE).edit();
                                editor.putFloat("lat", (float) currentlocation.getLatitude());
                                editor.putFloat("long", (float) currentlocation.getLongitude());
                                editor.apply();

           Toast.makeText(getContext(),Double.toString(currentlocation.getLatitude()),Toast.LENGTH_LONG).show();
                                Toast.makeText(getContext(),Double.toString(currentlocation.getLongitude()),Toast.LENGTH_LONG).show();



                            }
                        }, null);
            }
        }
        catch (SecurityException e)
        {
            Toast.makeText(getContext(),"We are in getDeviceLocation>>catch",Toast.LENGTH_SHORT).show();

        }
    }






}
