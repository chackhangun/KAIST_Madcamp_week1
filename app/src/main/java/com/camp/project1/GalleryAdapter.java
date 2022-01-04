package com.camp.project1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    private ArrayList<String> temp_image = new ArrayList<>();
    private Context context;
    Activity activity;

    public GalleryAdapter(Context mContext, ArrayList<String> mImageList) {
        context = mContext;
        temp_image = mImageList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        context = parent.getContext();
        System.out.println("context : "+ context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View photoView = inflater.inflate(R.layout.item_photo, parent, false);
        ViewHolder viewHolder = new ViewHolder(photoView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        /*SpacePhoto spacePhoto = mSpacePhotos[position];
        ImageView imageView = holder.mPhotoImageView;
<<<<<<< HEAD

=======
>>>>>>> cc0739fcc50dee3a4ddb85fc04dea0fb4b9ad6cf
        Glide.with(imageView)
                .load(spacePhoto.getUrl())
                .placeholder(R.drawable.ic_launcher_background) //loading image
                .into(imageView);*/
        //holder.bindSliderImage(imageList.get(position));
        holder.bindSliderImage(temp_image.get(position));

    }

    @Override
    public int getItemCount() {
        return temp_image.size();
    }

    public void addList(ArrayList<String> imageList) {
        temp_image = null;
        temp_image = imageList;
    }
    public void setActivity(Activity act){
        activity = act;
    }
    //public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView photoView;

        public ViewHolder(View itemView) {
            super(itemView);
            photoView = (ImageView) itemView.findViewById(R.id.iv_photo);
            itemView.setOnClickListener(this::onClick);
        }

        public void bindSliderImage(String imagePath) {
            Glide.with(context)
                    .load(imagePath)
                    //.load(spacePhoto.getUrl())
                    .placeholder(R.drawable.ic_launcher_background) //loading image
                    .into(photoView);
        }

        public void onClick(View view) {
            int position = getAdapterPosition();

            if (position != RecyclerView.NO_POSITION) {
                //SpacePhoto spacePhoto = mSpacePhotos[position];

                Intent intent = new Intent(context, ImageActivity.class); // intent(from, to)
                System.out.println(temp_image.get(position));
                intent.putExtra(ImageActivity.EXTRA_PHOTO, temp_image.get(position));

                context.startActivity(intent);
            }
        }
    }

}