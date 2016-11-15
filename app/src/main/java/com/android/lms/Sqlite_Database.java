package com.android.lms;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Sqlite_Database
{

    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";

    private static final String TAG = "DBAdapter";
    private static final String DATABASE_NAME = "DICV.db";

    private static final String TABLE_NAME = "dicv_data";
    private static final int DATABASE_VERSION = 1;
    public static final int NAME_COLUMN = 1;

    private Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public Sqlite_Database(Context context){
        this.context=context;
        DBHelper = new DatabaseHelper(context);

    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            try {
                db.execSQL("CREATE TABLE " + TABLE_NAME + "( "
                        + KEY_ID + " INTEGER PRIMARY KEY, "
                        + KEY_NAME + " TEXT )" );
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.e(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS" +TABLE_NAME);
            onCreate(db);
        }
    }

    //---open SQLite DB---
    public Sqlite_Database open() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //---close SQLite DB---
    public void close() {
        DBHelper.close();
    }

    //---insert data into SQLite DB---
    public long insert(String id) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NAME, id);
        return db.insert(TABLE_NAME, null, initialValues);
    }

    //---Delete All Data from table in SQLite DB---
    public void deleteAll() {
        db.delete(TABLE_NAME, null, null);
    }

    //---Get All Contacts from table in SQLite DB---
    public Cursor getAllData() {
        return db.query(TABLE_NAME, new String[] {KEY_NAME},
                null, null, null, null, null);
    }
}
