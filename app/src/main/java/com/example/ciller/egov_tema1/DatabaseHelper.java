package com.example.ciller.egov_tema1;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "ElectronicTickets.db";
    public static final String TABLE_NAME = "tickets_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NUME_PRENUME";
    public static final String COL_3 = "EMAIL";
    public static final String COL_4 = "TELEFON";
    public static final String COL_5 = "TIP_BILET";
    public static final String COL_6 = "DISCOUNT";
    public static final String COL_7 = "PRET";
    public static final String COL_8 = "FOTO";
    public static final String COL_9 = "VIDEO";
    public static final String COL_10 = "AUDIO";
    public static final String COL_11 = "TOTAL";
    public static final String COL_12 = "DATA";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, NUME_PRENUME TEXT, EMAIL TEXT, TELEFON INTEGER, TIP_BILET TEXT, DISCOUNT INTEGER, PRET INTEGER, FOTO BOOLEAN, VIDEO BOOLEAN, AUDIO BOOLEAN, TOTAL INTEGER, DATA DATE )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String nume, String email, String telefon, String tip, Integer discount, Double pret, boolean isFoto, boolean isVideo, boolean isAudio, Integer total, Integer zi, Integer luna, Integer an) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_2, nume);
        cv.put(COL_3, email);
        cv.put(COL_4, telefon);
        cv.put(COL_5, tip);
        cv.put(COL_6, discount);
        cv.put(COL_7, pret);
        cv.put(COL_8, isFoto);
        cv.put(COL_9, isVideo);
        cv.put(COL_10, isAudio);
        cv.put(COL_11, total);
        cv.put(COL_12, zi+"-"+luna+"-"+an);

        long rezultat = db.insert(TABLE_NAME, null, cv);
        if(rezultat == -1)
            return false;
        else
            return true;

    }
}
