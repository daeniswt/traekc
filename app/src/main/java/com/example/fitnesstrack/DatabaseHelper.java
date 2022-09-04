package com.example.fitnesstrack;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "DatenbankWasser";
    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_TABLE_SYNC = "synctable";
    public static final String WASSER_TABLE = "WASSER_TABLE";

    public static final String COLUMN_WASSER_VALUE = "WASSER_VALUE";
    public static final String COLUMN_ID = "ID";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }




    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTableStatement = "CREATE TABLE " + WASSER_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_WASSER_VALUE + " INT)";

        try {
            db.execSQL(createTableStatement);

        } catch (SQLiteException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        try {

            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_SYNC);
            onCreate(db);

        } catch (SQLiteException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }


    }

    public boolean addOne(Wasser wasser) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_WASSER_VALUE, wasser.getValueeing());

        long insert = db.insert(WASSER_TABLE, null, cv);

        if (insert == -1) {
            return false;

        }else {

            return true;
        }

    }

}
