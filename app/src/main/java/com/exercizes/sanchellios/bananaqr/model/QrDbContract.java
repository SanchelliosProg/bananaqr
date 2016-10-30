package com.exercizes.sanchellios.bananaqr.model;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by alex on 25.10.16.
 */

public class QrDbContract implements BaseColumns {

    public static final String CONTENT_AUTHORITY = "com.exercizes.sanchellios.bananaqr";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://"+CONTENT_AUTHORITY);
    public static final String PATH_URLS = "urls";


    public static final class QrTable {
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_URLS);

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
