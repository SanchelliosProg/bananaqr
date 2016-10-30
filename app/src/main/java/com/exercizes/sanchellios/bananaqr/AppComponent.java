package com.exercizes.sanchellios.bananaqr;

import android.content.Context;

import com.exercizes.sanchellios.bananaqr.model.DbModule;
import com.exercizes.sanchellios.bananaqr.network.UrlHandlerModule;
import com.exercizes.sanchellios.bananaqr.view.CaptureActivity;
import com.exercizes.sanchellios.bananaqr.view.CodeListActivity;

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
    void inject(CaptureActivity captureActivity);
    void inject(QrItem qrItem);
}
