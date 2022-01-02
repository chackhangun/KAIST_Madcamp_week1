package com.camp.project1;

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

import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    public GalleryAdapter galleryAdapter;
    public ArrayList<String> imageList = new ArrayList<>();
    public Context ct = getActivity();
    public Activity act;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View fragmentgallery = inflater.inflate(R.layout.fragment_gallery, container, false);
        act = getActivity();

        RecyclerView recyclerView = fragmentgallery.findViewById(R.id.rv_images);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(ct, 2);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        ContentResolver cr = getContext().getContentResolver();


        imageList = getImages(cr, ct);

        galleryAdapter = new GalleryAdapter(ct, imageList);
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
            while(!permissionSupport.checkEachPermission(permissions)){
                Toast.makeText(getContext(), "기능 사용을 위한 권한 동의가 필요합니다.", Toast.LENGTH_SHORT).show();
                permissionSupport.requestPermission();
            }
            Uri imageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

            String[] projection = new String[]{MediaStore.MediaColumns.DATA, MediaStore.MediaColumns.DISPLAY_NAME};
            //MediaStore.MediaColumns.DATA - 파일의 절대경로

            Cursor cursor = cr.query(imageUri, projection, null, null, MediaStore.MediaColumns.DATE_ADDED + " desc");
            /* getContentResolver 리졸버가 쿼리문을 날리는 메소드이며 uri과 스토리지 접근 권한이 필요
            uri - 데이터를 가져오는 경로 projection - 가져올 컬럼의 이름 배열
             */

            if (cursor != null) {
                int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA); // getColumnlndexOrThrow - projection에 추가했던 원하는 컬럼값의 인덱스를 받음, 인자값으로 인덱스를 구하고 싶은 컬럼명 기입
                int columnDisplayname = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DISPLAY_NAME);

                while (cursor.moveToNext()) {
                    //int lastIndex;
                    String absolutePathOfImage = cursor.getString(columnIndex);
                    String nameOfFile = cursor.getString(columnDisplayname);
                    /*
                    lastIndex = absolutePathOfImage.lastIndexOf(nameOfFile);
                    lastIndex = lastIndex >= 0 ? lastIndex : nameOfFile.length() - 1;
                    */

                    if (!TextUtils.isEmpty(absolutePathOfImage)) {
                        imageList.add(absolutePathOfImage);
                        System.out.println(absolutePathOfImage);
                    }
                }
            } else {
                System.out.println("NULL");
            }
            cursor.close();
            return imageList;


        } catch (NullPointerException e) {
            imageList.add("/storage/emulated/0/DCIM/Camera/IMG_20220101_102247.jpg");
            imageList.add("/storage/emulated/0/DCIM/Camera/IMG_20220101_102247.jpg");
            imageList.add("/storage/emulated/0/DCIM/Camera/IMG_20220101_102247.jpg");
            imageList.add("/storage/emulated/0/DCIM/Camera/IMG_20220101_102247.jpg");
            imageList.add("/storage/emulated/0/DCIM/Camera/IMG_20220101_102247.jpg");
            imageList.add("/storage/emulated/0/DCIM/Camera/IMG_20220101_102247.jpg");

        }
        return imageList;
    }
}