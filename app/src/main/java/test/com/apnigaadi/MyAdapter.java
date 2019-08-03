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

        holder.slno.setText("SNo." + String.valueOf(l.get_id()));
        holder.txttitle.setText(l.get_name());
        holder.txtdate.setText(l.get_phone_number());
        holder.txtid.setText(l.getUID());

    }
    @Override
    public int getItemCount()
    {
        return list.size();
    }


    public class Viewholder extends RecyclerView.ViewHolder{
        public TextView txttitle;
        public TextView txtdate ;
        public TextView txtid ;
        public TextView slno;
        public TextView update;
        public TextView delete;
        public Viewholder(final View itemView) {
            super(itemView);
            slno=(TextView) itemView.findViewById(R.id.slno);
            txttitle = (TextView)itemView.findViewById(R.id.txtname);
            txtdate =(TextView)itemView.findViewById(R.id.txtphone);
            txtid =(TextView)itemView.findViewById(R.id.txtuid);
//           update=(TextView)itemView.findViewById(R.id.update);
//           delete=(TextView)itemView.findViewById(R.id.delete);

//
//        delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                db.deleteContact(l.get_id());
//            }
//        });



        }
    }
}
