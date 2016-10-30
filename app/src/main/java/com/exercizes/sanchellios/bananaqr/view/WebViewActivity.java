package com.exercizes.sanchellios.bananaqr.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.exercizes.sanchellios.bananaqr.R;

public class WebViewActivity extends AppCompatActivity {
    public static String URL = "url";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.webViewToolbar);
        toolbar.setTitle("WEB");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        WebView webView = (WebView)findViewById(R.id.webview);
        webView.setWebViewClient(new MyBrowser());
        webView.loadUrl(getIntent().getExtras().getString(URL));
        toolbar.setTitle(webView.getTitle());
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }
    }
}
