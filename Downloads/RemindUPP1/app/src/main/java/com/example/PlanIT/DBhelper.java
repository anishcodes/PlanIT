package com.example.PlanIT;

import java.util.ArrayList;
import java.util.HashMap;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class DBhelper extends SQLiteOpenHelper {

   public static final String DATABASE_NAME = "MyDBName.db";
   public static  String TABLE_NAME = "tb";
   public static final String ROW_ID = "id";
   public static final String PER = "per";
    public static final String NAME= "name";

   private HashMap hp;

   public DBhelper(Context context)
   {
      super(context, DATABASE_NAME , null, 1);

   }
    public static void settablename(String st)
    {
        TABLE_NAME=st;
    }
    public static String gettablename()
    {
        return TABLE_NAME;
    }

   @Override
   public void onCreate(SQLiteDatabase db) {
      // TODO Auto-generated method stub
      db.execSQL(
      "create table tablenames ("+ NAME +" text);"
      );

   }
    public void createTable() {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(NAME, TABLE_NAME);
        db.insert("tablenames", null, contentValues);
        db.execSQL(
                "create table "+ TABLE_NAME +
                        "("+ ROW_ID+" integer, "+ PER +" text);"
        );

    }


   @Override
   public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      // TODO Auto-generated method stub
      db.execSQL("DROP TABLE IF EXISTS contacts");
      onCreate(db);
   }

   public boolean insertSubject(int id, String subject)
   {
      SQLiteDatabase db = this.getWritableDatabase();
      ContentValues contentValues = new ContentValues();

      contentValues.put(ROW_ID, id);
      contentValues.put(PER, subject);


      db.insert(TABLE_NAME, null, contentValues);
      return true;
   }
   public Cursor getData(int id){
      SQLiteDatabase db = this.getReadableDatabase();
      Cursor res =  db.rawQuery( "select * from contacts where id="+id+"", null );
      return res;
   }
   public int numberOfRows(){
      SQLiteDatabase db = this.getReadableDatabase();
      int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
      return numRows;
   }/*
   public boolean updateContact (Integer id, String name, String phone, String email, String street,String place)
   {
      SQLiteDatabase db = this.getWritableDatabase();
      ContentValues contentValues = new ContentValues();
      contentValues.put("name", name);
      contentValues.put("phone", phone);
      contentValues.put("email", email);
      contentValues.put("street", street);
      contentValues.put("place", place);
      db.update("contacts", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
      return true;
   }*/

   public void delete ()
   {
      SQLiteDatabase db = this.getWritableDatabase();
       db.execSQL("drop table " + TABLE_NAME);
       db.delete("tablenames", NAME+"="+TABLE_NAME, null);



   }
   public ArrayList getTableNames()
   {
      ArrayList array_list = new ArrayList();
      //hp = new HashMap();
      SQLiteDatabase db = this.getReadableDatabase();
      Cursor res =  db.rawQuery( "select * from tablenames ", null );
      res.moveToFirst();
      while(res.isAfterLast() == false){
      array_list.add(res.getString(res.getColumnIndex(NAME)));
      res.moveToNext();
      }
   return array_list;
   }
    public ArrayList getRowId()
    {
        ArrayList array_list = new ArrayList();
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+TABLE_NAME+" ", null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(ROW_ID)));
            res.moveToNext();
        }
        return array_list;
    }
    public ArrayList getPer()
    {
        ArrayList array_list = new ArrayList();
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+TABLE_NAME+" ", null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(PER)));
            res.moveToNext();
        }
        return array_list;
    }
}