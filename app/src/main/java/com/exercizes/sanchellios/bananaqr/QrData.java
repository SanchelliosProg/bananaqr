package com.exercizes.sanchellios.bananaqr;

/**
 * Created by alex on 25.10.16.
 */

public class QrData {

    private String mUrl;
    private String mStatusCode;

    public QrData(String url, String statusCode){
        mUrl = url;
        mStatusCode = statusCode;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getStatusCode() {
        return mStatusCode;
    }

    public void setStatusCode(String statusCode) {
        mStatusCode = statusCode;
    }


}
