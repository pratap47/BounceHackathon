package test.com.apnigaadi;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import test.com.apnigaadi.Vehicle_list.WantCar;


public class viewimage extends Fragment {


   private RecyclerView mRecycleView;
    private image_adaptor mAdaptor;
    private ProgressBar progressBar;
    private DatabaseReference mDatabaseRef;
    private List<Upload> mUploads;
    FusedLocationProviderClient fusedLocationProviderclient;
    private Location currentlocation;
    private int code =101;
    public static boolean mLocationPermissionGranted = false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_viewimage, container, false);
     Toast.makeText(getActivity(),"We are in viewImage class",Toast.LENGTH_LONG).show();
        getLocationPermission();
        getDeviceLocation();

      mRecycleView=v.findViewById(R.id.recycler_view);
      mRecycleView.setHasFixedSize(true);
      mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
       progressBar=v.findViewById(R.id.uploadprogress);
      mUploads=new ArrayList <>();
       mDatabaseRef= FirebaseDatabase.getInstance().getReference("Upload");
       mDatabaseRef.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               for(DataSnapshot postSnapshot :dataSnapshot.getChildren())
               {
                  Upload upload= postSnapshot.getValue(Upload.class);
                   mUploads.add(upload);
               }

//               mAdaptor =new com.example.bouncehackathon.image_adaptor(getActivity(),mUploads);
              mAdaptor=new image_adaptor(getActivity(),mUploads);
               mRecycleView.setAdapter(mAdaptor);
//               progressBar.setVisibility(View.INVISIBLE);
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {
            progressBar.setVisibility(View.INVISIBLE);
           }
       });
        return  v;
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
