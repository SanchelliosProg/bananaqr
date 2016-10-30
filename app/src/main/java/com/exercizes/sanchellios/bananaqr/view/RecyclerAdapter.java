package com.exercizes.sanchellios.bananaqr.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.exercizes.sanchellios.bananaqr.QrItem;
import com.exercizes.sanchellios.bananaqr.R;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by alex on 25.10.16.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.QrViewHolder> {

    private ArrayList<QrItem> mItems;

    public RecyclerAdapter(ArrayList<QrItem> items){
        mItems = items;
    }

    @Override
    public QrViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_qr, parent, false);
        return new QrViewHolder(view);
    }

    @Override
    public void onBindViewHolder(QrViewHolder holder, int position) {
        holder.mUrlTextView.setText(mItems.get(position).getUrl());
        holder.mStatusCodeTextView.setText(String.valueOf(mItems.get(position).getStatusCode()));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
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
