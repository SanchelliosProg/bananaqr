package com.exercizes.sanchellios.bananaqr;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alex on 30.10.16.
 */
@Module
public class DbModule {
    @Provides
    @Singleton
    public DbHelper provideDbHelper(Context context) {
        return new DbHelper(context);
    }
}
