package test.com.apnigaadi.Vehicle_list;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import test.com.apnigaadi.R;

public class Car_Data extends Fragment {
    EditText edtModel,edtTransmission,edtType,edtBrand,edtFueltype,edtSeats;
    Button btnSubmit;
    RequestQueue request;
    public Car_Data() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View w = inflater.inflate(R.layout.fragment_car__data, container, false);
        request = Volley.newRequestQueue(getActivity());

        edtBrand=w.findViewById(R.id.edtBrand);
        edtFueltype=w.findViewById(R.id.edtFueltype);
        edtModel=w.findViewById(R.id.edtModel);
        edtTransmission=w.findViewById(R.id.edtTransmission);
        edtSeats=w.findViewById(R.id.edtSeats);
        edtType=w.findViewById(R.id.edtType);
        btnSubmit=w.findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String  model = edtModel.getText().toString(),
                        transmission= edtTransmission.getText().toString(),
                        fuel =edtFueltype.getText().toString(),
                        type=edtType.getText().toString(),
                        brand=edtBrand.getText().toString(),
                        seats=edtSeats.getText().toString();

                CarData carData = new CarData(model,transmission,type,brand,fuel,seats,"5d3ff3d66f171c0031226a01");
                System.out.println(carData.getJsonObject());
                JsonObjectRequest mJsonObjectRequest = new JsonObjectRequest(Request.Method.POST, "https://bounce-hack.herokuapp.com/api/vehicles", carData.getJsonObject(),
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Toast.makeText(getActivity(), "done", Toast.LENGTH_LONG).show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error);
                    }
                });
                request.add(mJsonObjectRequest);

            }
        });

        return w;
    }


}
