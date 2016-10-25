package com.exercizes.sanchellios.bananaqr;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by alex on 25.10.16.
 */

public class DbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "qr.db";
    private static final int VERSION = 1;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(QrDbContract.QrTable.getCreateScript());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
