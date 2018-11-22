package com.diary.superdiary;

public class getsetinfologin {
    
    //private variables
    int id;
    String password;
     
    // Empty constructor
    public getsetinfologin(){
         
    }
    // constructor
    public getsetinfologin(int id, String pass){
        this.id = id;
        this.password = pass;
    }
     
    // constructor
    public getsetinfologin(String pass){
        this.password = pass;
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
    public String getpass(){
        return this.password;
    }
     
    // setting name
    public void setpass(String pass){
        this.password = pass;
    }
     
}
