package test.com.apnigaadi.Vehicle_list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import test.com.apnigaadi.R;

public class RentCarAdapter extends RecyclerView.Adapter<RentCarAdapter.Viewholder> {
    List<Listitem> list;
    Context context;

    public RentCarAdapter(List<Listitem> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_rent_vehicle,parent,false);
        return new Viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        Listitem item =list.get(position);
        holder.txtBrand.setText(item.getmBrand());
        holder.txtModel.setText(item.getmModel());
        //holder.txtPricePerKm.setText(item.);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        TextView txtBrand, txtModel , txtPricePerKm, txtLocation;
        TextView txtBasePrice ;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            txtBrand = itemView.findViewById(R.id.txtBrand);
            txtModel= itemView.findViewById(R.id.txtModel);
            txtBasePrice=itemView.findViewById(R.id.txtBase);
            txtLocation=itemView.findViewById(R.id.txtLoction);
            txtPricePerKm = itemView.findViewById(R.id.txtPricePerKm);
        }
    }
}
