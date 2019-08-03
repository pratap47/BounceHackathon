package test.com.apnigaadi;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class dashboard extends Fragment {
    RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    public dashboard() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View v= inflater.inflate(R.layout.fragment_dashboard, container, false);
        recyclerView.setAdapter(adapter);
        adapter = new MyAdapter(db.getAllotheruser(),getContext());
        recyclerView.setAdapter(adapter);


        return v;
    }
}
