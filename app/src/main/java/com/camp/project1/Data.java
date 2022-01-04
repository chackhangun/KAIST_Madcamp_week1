package com.camp.project1;

public class Data {
    public String name;
    public String number;

    public Data(String m_name, String m_number){
        name = m_name;
        number = m_number;
    }

    public String getName(){
        return this.name;
    }

    public String getNumber(){
        return this.number;
    }
    /*
    public void setName(String name){
        this.name = name;
    }

    public void setNumber(String number){
        this.number = number;
    }
     */
}
