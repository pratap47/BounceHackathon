package test.com.apnigaadi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class image_adaptor extends RecyclerView.Adapter< image_adaptor.ImageViewHolder> {

    private Context mcontext;
    private List<Upload> mUploads;

    public image_adaptor(Context mcontext, List <Upload> mUploads) {
        this.mcontext = mcontext;
        this.mUploads = mUploads;
    }


    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
View v= LayoutInflater.from(mcontext).inflate(R.layout.image_item,viewGroup,false);
return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder imageViewHolder, int i) {
     Upload uploadcur=mUploads.get(i);
     imageViewHolder.view_odometer.setText(uploadcur.getImageodometer());
     imageViewHolder.view_price.setText(uploadcur.getImageprice());
     imageViewHolder.view_location.setText(uploadcur.getImagelocation());
        Picasso.with(mcontext)
                .load(uploadcur.getImageUri())
                .placeholder(R.drawable.photo)
                .fit()
                .into(imageViewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
       ImageView imageView;
       TextView view_odometer;
       TextView view_location;
       TextView view_price;


        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);

          imageView=itemView.findViewById(R.id.image_View);
          view_odometer=itemView.findViewById(R.id.view_odomenter);
          view_location=itemView.findViewById(R.id.view_location);
          view_price=itemView.findViewById(R.id.view_price);
        }
    }
}
