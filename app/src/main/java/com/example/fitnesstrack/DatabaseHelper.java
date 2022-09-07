package com.example.fitnesstrack;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Denise Weinert
 * @version 1.0
 *
 * DatabaseHelper mit der abstrakten Klasse SQLiteOpenHelper zur erstellung der Datenbank für die
 * Speicherung der Werte, die bei Wasseraufnahme eingegeben werden.
 *
 */

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


    /**
     * @param db ist die Datenbank, die beim erstellen generiert wird
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + WASSER_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_WASSER_VALUE + " INTEGER)";

        try {
            db.execSQL(createTableStatement);

        } catch (SQLiteException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * @param db
     * @param i
     * @param i1
     */

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

    //Funktion, um einen Eintrag in die Datenbank Wasser zu machen.

    /**
     * Die Fuktion nimmt die Eingabe aus dem Dialog und fügt einen Eintrag in die Datenbank hinzu
     * @param wasser der Objekt-Klasse Wasser speichert die Eingabe aus dem EditText wassereing
     * @return gibt boolean zurück, also ob erfolg oder nicht bei insert des Content Values
     */
    public boolean addOneW (Wasser wasser) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_WASSER_VALUE, String.valueOf(wasser.getValueeing()));

        long insert = db.insert(WASSER_TABLE, null, cv);

        if (insert == -1) {
            return false;

        }else {

            return true;
        }

    }


    //Funktion getSum() nimmt alle Werte der Column Wasser Value und summiert sie auf.

    /**
     * @return die Summe aller Einträge
     */

    public int getSumW() {

        SQLiteDatabase db = this.getReadableDatabase();
        int total_wasser = 0;
        Cursor c = db.rawQuery("SELECT SUM(" + COLUMN_WASSER_VALUE + ") FROM " + WASSER_TABLE, null);
        if (c.moveToFirst()) {
            total_wasser= c.getInt(c.getColumnIndexOrThrow(COLUMN_WASSER_VALUE));;
        }
        c.close();
        //db.close();

        return total_wasser;

    }


    public boolean deleteOneW (Wasser wasser) {
        //TODO
       return true;

    }

}
