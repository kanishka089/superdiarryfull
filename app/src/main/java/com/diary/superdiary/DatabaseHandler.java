package com.diary.superdiary;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
 
public class DatabaseHandler extends SQLiteOpenHelper {
 
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "diary";
 
    // Contacts table name
    private static final String TABLE_NAME = "notes";
 // Contacts table name
    private static final String TABLE_NAMElogin = "login";
    
    
    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_DATE = "date";
    private static final String KEY_NOTE = "note";
 // Contacts Table Columns names
    private static final String KEY_IDlogin = "id";
    private static final String KEY_pass = "pass";
    
    
    
    
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_DATE + " TEXT,"
                + KEY_NOTE + " TEXT" + ")";
        String CREATE_CONTACTS_TABLElogin = "CREATE TABLE " + TABLE_NAMElogin + "("
                + KEY_IDlogin + " INTEGER PRIMARY KEY," + KEY_pass + " TEXT" + ")";
        
        db.execSQL(CREATE_CONTACTS_TABLE);
        db.execSQL(CREATE_CONTACTS_TABLElogin);
        
        
    }
 
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAMElogin);
        // Create tables again
        onCreate(db);
    }
 
    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */
 
    // Adding new contact
    void addContact(getsetinfo info) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_DATE, info.getDate()); // Contact Name
        values.put(KEY_NOTE, info.getnote()); // Contact Phone
 
        // Inserting Row
        db.insert(TABLE_NAME, null, values);
        db.close(); // Closing database connection
    }
    
 // Adding new contactlogin
    void addContactlogin(getsetinfologin info) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_pass, info.getpass()); // Contact Name
 
        // Inserting Row
        db.insert(TABLE_NAMElogin, null, values);
        db.close(); // Closing database connection
        
    }
 
 
    // Getting single contact
    getsetinfo getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
 
        Cursor cursor = db.query(TABLE_NAME, new String[] { KEY_ID,
        		KEY_DATE, KEY_NOTE }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
 
        getsetinfo contact = new getsetinfo(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        // return contact
        return contact;
    }
 // Getting single contact
    getsetinfologin getContactlogin(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
 
        Cursor cursor = db.query(TABLE_NAMElogin, new String[] { KEY_IDlogin,
        		KEY_pass}, KEY_IDlogin + "=?",
                new String[] { String.valueOf(id) }, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
 
        getsetinfologin contact = new getsetinfologin(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1));
        // return contact
        return contact;
    }
     
    // Getting All Contacts
    public ArrayList<getsetinfo> getAllContacts() {
    	ArrayList<getsetinfo> contactList = new ArrayList<getsetinfo>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " ORDER BY "+ KEY_ID +" DESC";
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
        	//home.ArrayofName.clear();
            do {
                getsetinfo contact = new getsetinfo();
                contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.setDate(cursor.getString(1));
                contact.setnote(cursor.getString(2));
                
                
                //String name = cursor.getString(2);
                //home.ArrayofName.add(name);
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
 
        // return contact list
        return contactList;
    }
 
    // Updating single contact
    public int updateContact(getsetinfo info) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_DATE, info.getDate());
        values.put(KEY_NOTE, info.getnote());
 
        // updating row
        return db.update(TABLE_NAME, values, KEY_ID + " = ?",
                new String[] { String.valueOf(info.getID()) });
    }
    
 // Updating single contact
    public int updaterow(String id,String note,String date) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_DATE, date);
        values.put(KEY_NOTE, note);
 
        // updating row
        return db.update(TABLE_NAME, values, KEY_ID + " = ?",
                new String[] { id });
    }
 
    // Deleting single contact
    public void deleteContact(getsetinfo contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
        db.close();
    }
 
    public void deleteRow(int rowId){
    	SQLiteDatabase db = this.getWritableDatabase();
        try{
            db.delete(TABLE_NAME, KEY_ID + "="+rowId,null);
        }catch(Exception e){
        }
    }
 
    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
 
        // return count
        return cursor.getCount();
    }
 // Getting contacts Count
    public int getContactsCountlogin() {
        String countQuery = "SELECT  * FROM " + TABLE_NAMElogin;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
 
        // return count
        return cnt;
    }
 
}
