package com.exercizes.sanchellios.bananaqr.model;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.exercizes.sanchellios.bananaqr.App;
import com.exercizes.sanchellios.bananaqr.QrItem;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class DatabaseInteractor {
    private final String LOG_TAG = getClass().getSimpleName();
    @Inject
    Context mContext;

    public DatabaseInteractor(){
        App.getAppComponent().inject(this);
    }

    public void saveQrItemToDatabase(QrItem item){
        String url = item.getUrl();
        int statusCode = item.getStatusCode();
        if(statusCode == 0)
            return;
        ContentValues values = new ContentValues();
        values.put(QrDbContract.QrTable.URL_COL, url);
        values.put(QrDbContract.QrTable.STATUS_CODES_COL, statusCode);
        Observable.create(subscriber -> mContext.getContentResolver().insert(QrDbContract.QrTable.CONTENT_URI, values))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
        Log.d(LOG_TAG, url + ": " + statusCode);
    }
}
