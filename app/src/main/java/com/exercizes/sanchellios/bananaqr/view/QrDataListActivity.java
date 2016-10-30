package com.exercizes.sanchellios.bananaqr.view;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.exercizes.sanchellios.bananaqr.App;
import com.exercizes.sanchellios.bananaqr.QrItem;
import com.exercizes.sanchellios.bananaqr.R;
import com.exercizes.sanchellios.bananaqr.model.DbHelper;
import com.exercizes.sanchellios.bananaqr.model.QrDbContract;
import com.exercizes.sanchellios.bananaqr.view.CaptureActivity;
import com.google.zxing.integration.android.IntentIntegrator;

import java.util.ArrayList;
import java.util.TreeSet;

import javax.inject.Inject;

public class QrDataListActivity extends AppCompatActivity implements Responsive<QrItem> {
    private final String LOG_TAG = getClass().getSimpleName();
    private QrListFragment currentFragment;
    private ArrayList<QrItem> mItems;

    @Inject
    DbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_list);

        App.getAppComponent().inject(this);

        mItems = getItemsFromDb();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final Activity activity = this;

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view -> new IntentIntegrator(activity).setCaptureActivity(CaptureActivity.class).initiateScan());

        putFragmentIntoRecycler();

    }

    private void putFragmentIntoRecycler() {
        currentFragment = new QrListFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_lists_container, currentFragment)
                .addToBackStack(String.valueOf(currentFragment.getClass().getSimpleName())).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_code_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_clean_db){
            mDbHelper.cleanDb(mDbHelper.getWritableDatabase());
            currentFragment.updateDataSet();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public ArrayList<QrItem> getData() {
        return mItems;
    }

    private ArrayList<QrItem> getItemsFromDb(){
        ArrayList<QrItem> items = new ArrayList<>();
        Cursor cursor;
        String[] projection = new String[]{QrDbContract.QrTable.URL_COL, QrDbContract.QrTable.STATUS_CODES_COL};
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
            QrItem item = new QrItem(cursor.getString(cursor.getColumnIndex(QrDbContract.QrTable.URL_COL)));
            item.setStatusCode(cursor.getInt(cursor.getColumnIndex(QrDbContract.QrTable.STATUS_CODES_COL)));
            Log.d(LOG_TAG, item.toString());
            items.add(item);
        }
        cursor.close();

        return items;
    }

    @Override
    public void openWebView() {

    }
}
