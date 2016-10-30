package com.exercizes.sanchellios.bananaqr;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alex on 30.10.16.
 */
@Module
public class AppModule {
    private Context mContext;

    public AppModule(@NonNull Context context){
        mContext = context;
    }

    @Provides
    @Singleton
    public Context provideContext(){
        return mContext;
    }
}
