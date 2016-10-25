package com.exercizes.sanchellios.bananaqr;

import android.provider.BaseColumns;

/**
 * Created by alex on 25.10.16.
 */

public class QrDbContract implements BaseColumns {

    public static final class QrTable {
        public static final String TABLE_NAME = "qr_table";
        public static final String URL_COL = "urls";
        public static final String STATUS_CODES_COL = "status_cols";

        public static String getCreateScript(){
            return "CREATE TABLE " + TABLE_NAME + " " + "(" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    URL_COL + " TEXT UNIQUE NOT NULL,"+
                    STATUS_CODES_COL + " TEXT NOT NULL);";
        }
    }
}
