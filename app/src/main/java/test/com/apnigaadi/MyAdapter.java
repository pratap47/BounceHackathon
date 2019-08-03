package test.com.apnigaadi;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
         holder.btnHost.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
//                 FragmentManager fragmentManager=getSupportFragmentManager();
//
////                 ShareFragment shareFragment=new ShareFragment();
//                 FragmentTransaction ft=getFra.beginTransaction();
//                 ft.replace(R.id.fragment_Container,fragment);
//                 ft.commit();

        /*         AppCompatActivity activity=(AppCompatActivity) view.getContext();
                 Fragment fragment=new rentit();
                 activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_Container,fragment).addToBackStack(null).commit();*/

                 Fragment fragment = new rentit();
                 Bundle bundle = new Bundle();
                 bundle.putInt("viewpager", 2);
                 /*the above value 2 indicates the second fragment*/
                 fragment.setArguments(bundle);
                 FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();
                 FragmentTransaction transaction =
                         manager.beginTransaction();

                 transaction.replace(R.id.fragment_Container, fragment,"SOMETAG").commit();
             }

         });
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }


    public class Viewholder extends RecyclerView.ViewHolder{
        public TextView txtBrand;
        public TextView txtModel ;
        public Button btnHost ;

        public Viewholder(final View itemView) {
            super(itemView);
           txtBrand = itemView.findViewById(R.id.txtBrand);
           txtModel = itemView.findViewById(R.id.txtModel);
           btnHost = itemView.findViewById(R.id.btnHost);
        }
    }
}
