package com.camp.project1;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

public class ContactProvider extends ContentProvider {
    public ContactDBManager dbManager = null;
    @Override
    public boolean onCreate() {
        dbManager = ContactDBManager.getInstance(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return dbManager.query(projection,selection,selectionArgs,null, null,sortOrder);
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long rowid = dbManager.insert(values);
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        System.out.println("delete enter");
        return dbManager.delete(selection,selectionArgs);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return dbManager.update(values,selection,selectionArgs);
    }

    /*
    public int deleteData(String id){
        return dbManager.deleteData(id);
    }*/
}
