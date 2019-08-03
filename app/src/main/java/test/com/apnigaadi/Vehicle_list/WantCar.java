package test.com.apnigaadi.Vehicle_list;

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
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import test.com.apnigaadi.R;

public class WantCar extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Listitem> listitems;
    private RequestQueue requestQueue ;

    public WantCar() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View w= inflater.inflate(R.layout.fragment_want_car, container, false);

        Toast.makeText(getActivity(),"want car",Toast.LENGTH_LONG).show();
        recyclerView = w.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listitems = new ArrayList<>();
        //requestQueue = Volley.newRequestQueue(getApplicationContext());

        String url = "https://bounce-hack.herokuapp.com/api/vehicles";

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

         return w;
    }


}
