package com.diary.superdiary;

public class getsetinfo {
    
    //private variables
    int id;
    String date;
    String note;
     
    // Empty constructor
    public getsetinfo(){
         
    }
    // constructor
    public getsetinfo(int id, String date, String note){
        this.id = id;
        this.date = date;
        this.note = note;
    }
     
    // constructor
    public getsetinfo(String date, String note){
        this.date = date;
        this.note = note;
    }
    // getting ID
    public int getID(){
        return this.id;
    }
     
    // setting id
    public void setID(int id){
        this.id = id;
    }
     
    // getting name
    public String getDate(){
        return this.date;
    }
     
    // setting name
    public void setDate(String name){
        this.date = name;
    }
     
    // getting phone number
    public String getnote(){
        return this.note;
    }
     
    // setting phone number
    public void setnote(String phone_number){
        this.note = phone_number;
    }
}
