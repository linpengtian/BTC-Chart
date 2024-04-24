package com.btcbrunch;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";

    private static final String TABLE_NAME = "daily_commodity_table";
    private static final String COL1 = "id";
    private static final String COL2 = "name";
    private static final String COL3 = "value";
    private static final String COL4 = "date";
    private static final String COL5 = "rangeVal";



    public DBHelper(@Nullable Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + "(id INTEGER PRIMARY KEY AUTOINCREMENT, " + COL2 + " TEXT, " + COL3 + " TEXT, " + COL4 + " TEXT, " + COL5 + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void dropTable() {
        System.out.println("I am inside the DBHelper dropTable() function");
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public void createTable() {
        System.out.println("I am inside the DBHelper createTable() function");
        SQLiteDatabase db = this.getWritableDatabase();
        String createTable = "CREATE TABLE " + TABLE_NAME + "(id INTEGER PRIMARY KEY AUTOINCREMENT, " + COL2 + " TEXT, " + COL3 + " TEXT, " + COL4 + " TEXT, " + COL5 + " TEXT)";
        db.execSQL(createTable);
    }

    public boolean addData(String column2, String column3, String column4, String column5) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, column2);
        contentValues.put(COL3, column3);
        contentValues.put(COL4, column4);
        contentValues.put(COL5, column5);

        System.out.println("addData: Adding " + column2 + " to " + TABLE_NAME);
        System.out.println("addData: Adding " + column3 + " to " + TABLE_NAME);
        System.out.println("addData: Adding " + column4 + " to " + TABLE_NAME);
        System.out.println("addData: Adding " + column5 + " to " + TABLE_NAME);


        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean updateData(String column2, String column3, String column4, String column5) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, column2);
        contentValues.put(COL3, column3);
        contentValues.put(COL4, column4);
        contentValues.put(COL5, column5);
        Cursor cursor = db.rawQuery("Select * from " + TABLE_NAME + " where name = ?", new String[]{COL2});
        if (cursor.getCount() > 0) {
//            System.out.println("updateData: Updating " + column2 + " to " + TABLE_NAME);
//            System.out.println("updateData: Updating " + column3 + " to " + TABLE_NAME);
//            System.out.println("updateData: Updating " + column4 + " to " + TABLE_NAME);
//            System.out.println("updateData: Updating " + column5 + " to " + TABLE_NAME);

            long result = db.update(TABLE_NAME, contentValues, "name=?", new String[]{COL2});

            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public boolean deleteData(String column2) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("Select * from " + TABLE_NAME + " where name = ?", new String[]{COL2});
        if (cursor.getCount() > 0) {
//            System.out.println("deleteData: Deleting " + column2 + " from " + TABLE_NAME);

            long result = db.delete(TABLE_NAME,"name=?", new String[]{COL2});

            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Cursor getData() {
        System.out.println("Im in the getData() method inside DBHelper");
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + TABLE_NAME, null);
//        cursor.close();
        return cursor;
    }

    public boolean checkIfEmpty() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + TABLE_NAME, null);
        if (cursor != null && cursor.getCount() == 0) {
            System.out.println("daily_commodity_table is empty" + getClass().getName());
            return true;
        } else {
            System.out.println("daily_commodity_table is not empty" + getClass().getName());
            return false;
        }
    }

    public Cursor checkDate() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("Select * from " + TABLE_NAME, null);
    }

}
