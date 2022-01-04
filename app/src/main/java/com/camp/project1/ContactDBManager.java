package com.camp.project1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ContactDBManager extends SQLiteOpenHelper {

    static final String DB_CONTACTS = "Contacts.db";
    static final String TABLE_CONTACT = "Contacts";
    static final int DB_VERSION = 2;
    Context context = null;
    private static ContactDBManager dbManager = null;

    public static ContactDBManager getInstance(Context context){
        if(dbManager == null){
            dbManager = new ContactDBManager(context, DB_CONTACTS, null, DB_VERSION);
        }
        return dbManager;
    }

    private ContactDBManager(Context context, String dbName, SQLiteDatabase.CursorFactory factory, int version){
        super(context, dbName, factory, version);
        this.context = context;
    }

    @Override
    public void onOpen(SQLiteDatabase db){
        super.onOpen(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_CONTACT + "(" + "_id INTEGER PRIMARY KEY AUTOINCREMENT," + "name TEXT," + "phone TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion){
        if(oldversion < newversion){
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACT);
        }
    }

    public long insert(ContentValues addContact){
        return getWritableDatabase().insert(TABLE_CONTACT,null,addContact);
    }

    public Cursor query(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy){
        return getReadableDatabase().query(TABLE_CONTACT,columns,selection,selectionArgs,groupBy,having,orderBy);
    }

    public int update(ContentValues addContact,String whereClause, String[] whereArgs){
        return getWritableDatabase().update(TABLE_CONTACT, addContact,whereClause,whereArgs);
    }

    public int delete(String whereClause, String[] whereArgs){
        return getWritableDatabase().delete(TABLE_CONTACT,whereClause,whereArgs);
    }

    public int deleteData(String id){
        return getWritableDatabase().delete(TABLE_CONTACT, "_id = ?", new String[] {id});
    }
}
