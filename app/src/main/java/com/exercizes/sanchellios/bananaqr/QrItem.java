package com.exercizes.sanchellios.bananaqr;


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
                        throwable -> {setStatusCode(0);});
    }

    private void setStatusCode(int statusCode){
        mStatusCode = statusCode;
        Log.d(TAG, "Status Code: " + mStatusCode);
    }

}
