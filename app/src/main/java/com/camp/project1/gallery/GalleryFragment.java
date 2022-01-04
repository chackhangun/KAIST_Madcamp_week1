package com.camp.project1.gallery;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.camp.project1.PermissionSupport;
import com.camp.project1.R;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    public com.camp.project1.gallery.GalleryAdapter galleryAdapter;
    public ArrayList<String> imageList = new ArrayList<>();
    //public Context ct = getActivity();
    public Context ct = getContext();
    public Activity act;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View fragmentgallery = inflater.inflate(R.layout.fragment_gallery, container, false);
        act = getActivity();

        RecyclerView recyclerView = fragmentgallery.findViewById(R.id.rv_images);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(ct, 2);

        recyclerView.setHasFixedSize(true); // item 크기가 변경되지 않음을 명시
        recyclerView.setLayoutManager(layoutManager);

        ContentResolver cr = getContext().getContentResolver();

        imageList = getImages(cr, ct);

        galleryAdapter = new com.camp.project1.gallery.GalleryAdapter(ct, imageList);
        galleryAdapter.addList(imageList);

        recyclerView.setAdapter(galleryAdapter);


        return fragmentgallery;
    }

    public ArrayList<String> getImages(ContentResolver cr, Context context) {
        ArrayList<String> imageList = new ArrayList<>();
        PermissionSupport permissionSupport = new PermissionSupport(getActivity(), getContext());
        String[] permissions = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE};

        try {
            while (!permissionSupport.checkEachPermission(permissions)) {
                Toast.makeText(getContext(), "기능 사용을 위한 권한 동의가 필요합니다.", Toast.LENGTH_LONG).show();
                permissionSupport.requestPermission();
            }
            Uri imageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

            String[] projection = new String[]{MediaStore.MediaColumns.DATA, MediaStore.MediaColumns.DISPLAY_NAME};
            //MediaStore.MediaColumns.DATA - 파일의 절대경로

            Cursor cursor = cr.query(imageUri, projection, null, null, MediaStore.MediaColumns.DATE_ADDED + " desc");

            if (cursor != null) {
                int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);

                while (cursor.moveToNext()) {

                    String absolutePathOfImage = cursor.getString(columnIndex);

                    if (!TextUtils.isEmpty(absolutePathOfImage)) {
                        imageList.add(absolutePathOfImage);
                    }
                }
            } else {
                System.out.println("NULL");
            }
            cursor.close();
            return imageList;

        } catch (NullPointerException e) {
            System.out.println("imageListNULL");

        }
        return imageList;
    }
}