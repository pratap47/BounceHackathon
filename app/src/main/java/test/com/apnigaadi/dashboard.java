package test.com.apnigaadi;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import test.com.apnigaadi.Vehicle_list.Listitem;
import test.com.apnigaadi.Vehicle_list.Mysingleton;
import test.com.apnigaadi.Vehicle_list.RentCarAdapter;

public class dashboard extends Fragment {
    RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<otheruserinfo> listitems;

    public dashboard() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View v= inflater.inflate(R.layout.fragment_dashboard, container, false);
         Toast.makeText(getActivity(),"Dashboard",Toast.LENGTH_LONG).show();
         recyclerView = v.findViewById(R.id.recyclerview);
         listitems = new ArrayList<>();
        String url = "https://bounce-hack.herokuapp.com/api/uservehicles/5d3ff3d66f171c0031226a01";

        JsonObjectRequest ExampleRequest = new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println(response);
                try {
                    int i;
                    JSONArray array = response.getJSONArray("vehicles");
                    for(i=0;i<array.length();i++) {
                        JSONObject object = array.getJSONObject(i);
                        otheruserinfo list  = new otheruserinfo(object);
                        listitems.add(list);                                //2440004
                    }
                    adapter = new MyAdapter(listitems,getActivity());
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
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
        return v;
    }
}
