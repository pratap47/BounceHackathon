package test.com.apnigaadi.Vehicle_list;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.service.autofill.UserData;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import test.com.apnigaadi.R;

import static android.content.Context.MODE_PRIVATE;


public class User_Data extends Fragment {
    EditText edtName,edtPhoneNo, edtOccupation;
    Button btnSubmit;
    RequestQueue requestQueue;
    public User_Data() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View w = inflater.inflate(R.layout.fragment_user__data, container, false);
        requestQueue = Volley.newRequestQueue(getActivity());

        edtName =w.findViewById(R.id.edtName);
        edtOccupation=w.findViewById(R.id.edtOccupation);
        edtPhoneNo=w.findViewById(R.id.edtPhoneNo);

        btnSubmit = w.findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name,phone,occupation;
                name = edtName.getText().toString();
                phone = edtPhoneNo.getText().toString();
                occupation=edtOccupation.getText().toString();

                test.com.apnigaadi.Vehicle_list.UserData user = new test.com.apnigaadi.Vehicle_list.UserData(name,phone,occupation);
                // System.out.println(user.getJsonObject());

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, "https://bounce-hack.herokuapp.com/api/users", user.getJsonObject(), new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String uid = response.getString("_id");
                            SharedPreferences.Editor editor = getActivity().getSharedPreferences("mypref", MODE_PRIVATE).edit();
                            editor.putString("id",uid);
                            editor.apply();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println();
                    }
                });
                requestQueue.add(jsonObjectRequest);
            }
        });
        return w;
    }
}
