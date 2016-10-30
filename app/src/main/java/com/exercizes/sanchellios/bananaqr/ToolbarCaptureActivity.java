package com.exercizes.sanchellios.bananaqr;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.ResultPoint;
import com.google.zxing.client.android.BeepManager;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.inject.Inject;

/**
 * Sample Activity extending from ActionBarActivity to display a Toolbar.
 */
public class ToolbarCaptureActivity extends AppCompatActivity {
    private DecoratedBarcodeView barcodeScannerView;
    private BeepManager beepManager;
    private Set<String> resultStrings;

    @Inject
    DbHelper mDbHelper;


    private BarcodeCallback callback = new BarcodeCallback() {
        @Override
        public void barcodeResult(BarcodeResult result) {
            String url = result.getText();
            if (!Patterns.WEB_URL.matcher(url).matches()){
                //TODO: Make a snackbar
                //Toast.makeText(getApplicationContext(), "Invalid url: "+url, Toast.LENGTH_SHORT).show();
                Snackbar snackbar = Snackbar.make(findViewById(R.id.buttonsLayout), "Invalid url: "+url, Snackbar.LENGTH_SHORT);
                snackbar.show();
                beepManager.playBeepSound();
                return;
            }
            if(result.getText() == null || resultStrings.contains(result.getText())) {
                return;
            }

            resultStrings.add(result.getText());

            QrItem qrItem = new QrItem(result.getText());
            qrItem.retrieveStatusCode();


            barcodeScannerView.setStatusText(result.getText());
            beepManager.playBeepSoundAndVibrate();

            ImageView imageView = (ImageView) findViewById(R.id.barcodePreview);
            imageView.setImageBitmap(result.getBitmapWithResultPoints(Color.YELLOW));


        }

        @Override
        public void possibleResultPoints(List<ResultPoint> resultPoints) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initResultStringsSet();

        App.getAppComponent().inject(this);

        setContentView(R.layout.capture_appcompat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
        toolbar.setTitle("Scan Barcode");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        barcodeScannerView = (DecoratedBarcodeView)findViewById(R.id.zxing_barcode_scanner);
        barcodeScannerView.decodeContinuous(callback);

        beepManager = new BeepManager(this);
    }

    private void initResultStringsSet() {
        resultStrings = new TreeSet<>();
        //TODO: Load values from database
    }

    @Override
    protected void onResume() {
        super.onResume();
        barcodeScannerView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        barcodeScannerView.pause();
    }

    public void pause(View view) {
        barcodeScannerView.pause();
    }

    public void resume(View view) {
        barcodeScannerView.resume();
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return barcodeScannerView.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }
}
