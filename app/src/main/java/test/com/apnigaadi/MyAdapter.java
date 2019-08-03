package test.com.apnigaadi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.Viewholder> {
    List <otheruserinfo> list;
    Context context;
    private static final String TABLE_NAME = "INFO";

    public MyAdapter(List <otheruserinfo> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adaptor, parent, false);
        return new Viewholder(v);
    }
    public otheruserinfo l;
    @Override
    public void onBindViewHolder(@NonNull MyAdapter.Viewholder holder, int position) {
         l = list.get(position);
         holder.txtModel.setText(l.getmModel());
         holder.txtBrand.setText(l.getmManufacturer());
    }
    @Override
    public int getItemCount()
    {
        return list.size();
    }


    public class Viewholder extends RecyclerView.ViewHolder{
        public TextView txtBrand;
        public TextView txtModel ;

        public Viewholder(final View itemView) {
            super(itemView);
           txtBrand = itemView.findViewById(R.id.txtBrand);
           txtModel = itemView.findViewById(R.id.txtModel);
        }
    }
}
