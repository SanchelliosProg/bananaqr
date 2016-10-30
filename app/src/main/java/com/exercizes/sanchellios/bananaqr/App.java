package com.exercizes.sanchellios.bananaqr;

import android.app.Application;

/**
 * Created by alex on 30.10.16.
 */

public class App extends Application {
    protected static App sInstance;
    protected static AppComponent sAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        sAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .dbModule(new DbModule())
                .build();
    }

    public static AppComponent getAppComponent(){
        return sAppComponent;
    }
}
