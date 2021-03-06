package com.exercizes.sanchellios.bananaqr.network;

import com.exercizes.sanchellios.bananaqr.network.UrlHandler;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alex on 30.10.16.
 */

@Module
public class UrlHandlerModule {
    @Provides
    @Singleton
    public UrlHandler provideUrlHandler(){
        return new UrlHandler();
    }
}
