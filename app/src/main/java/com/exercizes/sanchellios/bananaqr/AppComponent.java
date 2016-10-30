package com.exercizes.sanchellios.bananaqr;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by alex on 30.10.16.
 */
@Component(modules = {AppModule.class, DbModule.class, UrlHandlerModule.class})
@Singleton
public interface AppComponent {
    Context context();

    void inject(CodeListActivity codeListActivity);
    void inject(ToolbarCaptureActivity toolbarCaptureActivity);
    void inject(QrItem qrItem);
}
