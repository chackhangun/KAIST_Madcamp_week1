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
<<<<<<< HEAD
    
=======

>>>>>>> cc0739fcc50dee3a4ddb85fc04dea0fb4b9ad6cf
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_detail);
<<<<<<< HEAD
        
        imageView = (ImageView) findViewById(R.id.image);
        String imagePath = getIntent().getStringExtra(EXTRA_PHOTO);
        
=======

        imageView = (ImageView) findViewById(R.id.image);
        // String imagePath = getIntent().getParcelableExtra(EXTRA_PHOTO);
        String imagePath = getIntent().getStringExtra(EXTRA_PHOTO);

>>>>>>> cc0739fcc50dee3a4ddb85fc04dea0fb4b9ad6cf
        Glide.with(this)
                .asBitmap()
                .load("file://"+Uri.parse(imagePath))
                .error(R.drawable.ic_launcher_foreground)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(imageView);
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> cc0739fcc50dee3a4ddb85fc04dea0fb4b9ad6cf
