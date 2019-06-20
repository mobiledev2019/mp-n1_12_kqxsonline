package com.example.ketquaxoso;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DB_NAME = "account";
    private static final int DB_Version = 1;
    private static final String TABLE_Name = "history";
    private static final String KEY_Id = "id";
    private static final String KEY_User = "user";
    private static final String KEY_Date = "pass";
    private static final String KEY_Money = "money";
    public DatabaseHandler(Context context){
        super(context, DB_NAME, null, DB_Version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String creatTable = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY, %s TEXT, %s TEXT, %s TEXT)", TABLE_Name, KEY_Id, KEY_User, KEY_Date, KEY_Money);
        db.execSQL(creatTable);
        Log.i("SQLITE", "DA TAO BANG SQLITE");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTable = String.format("DROP TABLE IF EXISTS %s", TABLE_Name);
        Log.i("SQLITE", "DA XOA BANG SQLITE CU~");
        db.execSQL(dropTable);
        onCreate(db);
    }
    public void addAccount(account acc){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_User, acc.getUser());
        values.put(KEY_Date, acc.getDate());
        values.put(KEY_Money, acc.getMoney());
        db.insert(TABLE_Name, null, values);
        System.out.println("DA TAO THANH CONG ADDACCOUNT!");
        db.close();
    }
    public ArrayList<account> getAccoutByName(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<account> result = new ArrayList<>();
        Cursor cursor = db.query(TABLE_Name, null, KEY_User + " = ?", new String[]{name}, null, null, null);
        if(cursor != null) {

            cursor.moveToFirst();
            while (cursor.isAfterLast() == false) {
                account test = new account(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
                result.add(test);
                cursor.moveToNext();
            }
        }
        return result;

    }
    public ArrayList<account> getAccount(int idAccount){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<account> result = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_Name;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        while(cursor.isAfterLast() == false){
            account test = new account(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
            result.add(test);
            cursor.moveToNext();
        }
        return result;
    }
    public account get1Account(int idAccount){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_Name, null, KEY_Id + " = ?", new String[]{String.valueOf(idAccount)}, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        account result = new account(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
        return result;
    }
    public void DropTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_Name);
        db.close();
    }

}
