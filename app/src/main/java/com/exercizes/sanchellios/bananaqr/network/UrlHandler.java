package com.exercizes.sanchellios.bananaqr.network;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by alex on 30.10.16.
 */

public class UrlHandler {
    private OkHttpClient mClient;

    public UrlHandler(){
        mClient = new OkHttpClient();
    }

    public int getStatusCode(String url){
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = null;
        int code = 0;
        try {
            response = mClient.newCall(request).execute();
            code = response.code();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            response.close();
        }
        return code;
    }
}
