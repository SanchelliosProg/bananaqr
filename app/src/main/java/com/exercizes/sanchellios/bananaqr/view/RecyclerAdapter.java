package com.exercizes.sanchellios.bananaqr.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
    private Responsive mListener;

    public RecyclerAdapter(ArrayList<QrItem> items, Responsive listener){
        mItems = items;
        mListener = listener;

    }

    @Override
    public QrViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_qr, parent, false);
        return new QrViewHolder(view);
    }

    @Override
    public void onBindViewHolder(QrViewHolder holder, int position) {
        String url = mItems.get(position).getUrl();
        int codeStatus = mItems.get(position).getStatusCode();
        holder.mUrlTextView.setText(url);
        holder.mStatusCodeTextView.setText(String.valueOf(codeStatus));
        holder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(codeStatus == 200){
                    mListener.openWebView(mItems.get(position).getUrl());
                }else {
                    mListener.activateSnackbar();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    static class QrViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout mLayout;
        TextView mStatusCodeTextView;
        TextView mUrlTextView;

        public QrViewHolder(View itemView) {
            super(itemView);
            mLayout = (RelativeLayout)itemView;
            mStatusCodeTextView = (TextView)itemView.findViewById(R.id.statusCodeTextView);
            mUrlTextView = (TextView)itemView.findViewById(R.id.urlTextView);
        }
    }




}
