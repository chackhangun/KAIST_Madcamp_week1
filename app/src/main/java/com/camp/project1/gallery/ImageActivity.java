package com.camp.project1.gallery;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.camp.project1.R;
import com.github.chrisbanes.photoview.PhotoView;

public class ImageActivity extends AppCompatActivity {
    public static final String EXTRA_PHOTO ="ImageActivity.EXTRA_PHOTO";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_detail);

        PhotoView photoView = findViewById(R.id.image);

        String imagePath = getIntent().getStringExtra(EXTRA_PHOTO);

        Glide.with(this)
                .asBitmap()
                .load("file://"+Uri.parse(imagePath))
                .error(R.drawable.ic_launcher_foreground)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(photoView);
    }
}