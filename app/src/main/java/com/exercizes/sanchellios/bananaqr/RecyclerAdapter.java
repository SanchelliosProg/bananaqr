package com.exercizes.sanchellios.bananaqr;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by alex on 25.10.16.
 */

//public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.EmotionsViewHolder> {

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.QrViewHolder> {



    @Override
    public QrViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_qr, parent, false);
        return new QrViewHolder(view);
    }

    @Override
    public void onBindViewHolder(QrViewHolder holder, int position) {
        //TODO: bind data
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class QrViewHolder extends RecyclerView.ViewHolder {
        TextView mStatusCodeTextView;
        TextView mUrlTextView;

        public QrViewHolder(View itemView) {
            super(itemView);
            mStatusCodeTextView = (TextView)itemView.findViewById(R.id.statusCodeTextView);
            mUrlTextView = (TextView)itemView.findViewById(R.id.urlTextView);
        }
    }
}
