package com.exercizes.sanchellios.bananaqr;


import android.content.Context;
import android.util.Log;

import com.exercizes.sanchellios.bananaqr.model.DatabaseInteractor;
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

    public QrItem(String url) {
        App.getAppComponent().inject(this);
        mUrl = url;
    }

    public void retrieveStatusCode() {
        Observable.defer(() -> Observable.just(mUrlHandler.getStatusCode(mUrl)))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setStatusCode,
                        throwable -> {},
                        this::saveToDb);
    }

    public void setStatusCode(int statusCode) {
        mStatusCode = statusCode;
        Log.d(TAG, "Status Code: " + mStatusCode);
    }

    private void saveToDb() {
        DatabaseInteractor d = new DatabaseInteractor();
        d.saveQrItemToDatabase(this);
    }


    public int getStatusCode() {
        return mStatusCode;
    }

    public String getUrl() {
        return mUrl;
    }

    @Override
    public String toString() {
        return mUrl + ": "+mStatusCode;
    }
}
