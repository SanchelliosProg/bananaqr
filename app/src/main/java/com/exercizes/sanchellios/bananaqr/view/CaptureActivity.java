package com.exercizes.sanchellios.bananaqr.view;

import android.database.Cursor;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.exercizes.sanchellios.bananaqr.App;
import com.exercizes.sanchellios.bananaqr.model.DatabaseInteractor;
import com.exercizes.sanchellios.bananaqr.model.QrDbContract;
import com.exercizes.sanchellios.bananaqr.QrItem;
import com.exercizes.sanchellios.bananaqr.R;
import com.exercizes.sanchellios.bananaqr.model.DbHelper;
import com.google.zxing.ResultPoint;
import com.google.zxing.client.android.BeepManager;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.inject.Inject;

/**
 * Sample Activity extending from ActionBarActivity to display a Toolbar.
 */
public class CaptureActivity extends AppCompatActivity {
    private final String LOG_TAG = getClass().getSimpleName();
    final ToneGenerator tg = new ToneGenerator(AudioManager.STREAM_NOTIFICATION, 100);
    private DecoratedBarcodeView barcodeScannerView;
    private BeepManager beepManager;
    private Set<String> resultStrings;
    private DatabaseInteractor mDatabaseInteractor;

    @Inject
    DbHelper mDbHelper;


    private BarcodeCallback callback = new BarcodeCallback() {
        @Override
        public void barcodeResult(BarcodeResult result) {
            String url = result.getText();
            if (!Patterns.WEB_URL.matcher(url).matches()){
                Snackbar snackbar = Snackbar.make(findViewById(R.id.buttonsLayout), "Invalid url: "+url, Snackbar.LENGTH_SHORT);
                snackbar.show();
                tg.startTone(ToneGenerator.TONE_CDMA_ABBR_ALERT);
                resultStrings.add(url);
                return;
            }
            if(result.getText() == null || resultStrings.contains(result.getText())) {
                Snackbar.make(findViewById(R.id.buttonsLayout), "Already in database: "+url, Snackbar.LENGTH_SHORT).show();
                return;
            }

            resultStrings.add(url);

            QrItem qrItem = new QrItem(url);
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

        mDatabaseInteractor = new DatabaseInteractor();
    }

    private void initResultStringsSet() {
        resultStrings = new TreeSet<>();
        Cursor cursor;
        String[] projection = new String[]{QrDbContract.QrTable.URL_COL};
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            cursor = getContentResolver().query(QrDbContract.QrTable.CONTENT_URI,
                    projection,
                    null, null, null, null);
        } else {
            cursor = mDbHelper.getReadableDatabase().query(QrDbContract.QrTable.TABLE_NAME,
                    projection, null, null, null, null, null);
        }
        cursor.moveToFirst();
        while (cursor.moveToNext()){
            String item = cursor.getString(cursor.getColumnIndex(QrDbContract.QrTable.URL_COL));
            resultStrings.add(item);
            Log.d(LOG_TAG, item);
        }
        cursor.close();
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
