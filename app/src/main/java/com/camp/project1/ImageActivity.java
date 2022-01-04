package com.camp.project1;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class ImageActivity extends AppCompatActivity {
    public static final String EXTRA_PHOTO ="ImageActivity.EXTRA_PHOTO";
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_detail);

        imageView = (ImageView) findViewById(R.id.image);
<<<<<<< HEAD
        // String imagePath = getIntent().getParcelableExtra(EXTRA_PHOTO);
=======
       // String imagePath = getIntent().getParcelableExtra(EXTRA_PHOTO);
>>>>>>> parent of 9f42a28... add image zoom
        String imagePath = getIntent().getStringExtra(EXTRA_PHOTO);

        Glide.with(this)
                .asBitmap()
                .load("file://"+Uri.parse(imagePath))
                .error(R.drawable.ic_launcher_foreground)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(imageView);
    }
}