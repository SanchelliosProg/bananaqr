package com.exercizes.sanchellios.bananaqr;


import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.exercizes.sanchellios.bananaqr.model.QrDbContract;
import com.exercizes.sanchellios.bananaqr.network.UrlHandler;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
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
                .subscribe(this::setStatusCode,
                        throwable -> {mStatusCode = 0;});
        saveToTheDatabase();
    }

    private void setStatusCode(int statusCode){
        mStatusCode = statusCode;
        Log.d(TAG, "Status Code: " + mStatusCode);
    }

    private void saveToTheDatabase(){
        if(mStatusCode == 0){
            return;
        }
        ContentValues values = new ContentValues();
        values.put(QrDbContract.QrTable.URL_COL, mUrl);
        values.put(QrDbContract.QrTable.STATUS_CODES_COL, mStatusCode);
        Observable.create(s -> mContext.getContentResolver().insert(QrDbContract.QrTable.CONTENT_URI, values))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

}
