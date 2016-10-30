package com.exercizes.sanchellios.bananaqr.view;

import java.util.ArrayList;

/**
 * Created by alex on 30.10.16.
 */

public interface Responsive<T> {
    ArrayList<T> getData();
    void openWebView(String url);
    void activateSnackbar();
}
