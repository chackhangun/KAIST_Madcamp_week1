package com.camp.project1;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

public class Data {

    private String id;
    private String name;
    private String number;

    public Data(String m_name, String m_number){
        name = m_name;
        number = m_number;
    }
    public Data(){};

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public String getNumber(){
        return this.number;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}


