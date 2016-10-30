package com.exercizes.sanchellios.bananaqr;


import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import java.io.IOException;

import javax.inject.Inject;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func0;
import rx.schedulers.Schedulers;

/**
 * Created by alex on 30.10.16.
 */

public class QrItem {

    private String TAG = getClass().getSimpleName();
    @Inject
    UrlHandler mUrlHandler;
    @Inject
    Context mContext;

    private String mUrl;
    private int mStatusCode;

    public QrItem(String url){
        App.getAppComponent().inject(this);
        mUrl = url;
    }

    public void retrieveStatusCode() {
        Observable.defer(() -> Observable.just(mUrlHandler.getStatusCode(mUrl))).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setStatusCode);
        saveToTheDatabase();
    }

    private void setStatusCode(int statusCode){
        mStatusCode = statusCode;
        Log.d(TAG, "Status Code: " + mStatusCode);
    }

    private void saveToTheDatabase(){
        ContentValues values = new ContentValues();
        values.put(QrDbContract.QrTable.URL_COL, mUrl);
        values.put(QrDbContract.QrTable.STATUS_CODES_COL, mStatusCode);
        Observable.create(s -> mContext.getContentResolver().insert(QrDbContract.QrTable.CONTENT_URI, values))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

}
