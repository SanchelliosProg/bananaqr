package com.exercizes.sanchellios.bananaqr.view;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.exercizes.sanchellios.bananaqr.QrItem;
import com.exercizes.sanchellios.bananaqr.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class QrListFragment extends Fragment {
    private RecyclerView mUrlRecycler;
    private RelativeLayout mEmptyView;
    private ProgressBar mSpinner;
    private RecyclerAdapter mAdapter;
    private Responsive<QrItem> listener;
    public QrListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (Responsive)context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_qr_list, container, false);
        mUrlRecycler = (RecyclerView)rootView.findViewById(R.id.qr_recycler);
        mEmptyView = (RelativeLayout) rootView.findViewById(R.id.empty_view);
        mSpinner = (ProgressBar)rootView.findViewById(R.id.qr_list_spinner);
        notifyLoad();
        return rootView;
    }

    private void initAdapter() {
        mAdapter = new RecyclerAdapter(listener.getData(), listener);
        checkItemCount();
        mUrlRecycler.setAdapter(mAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mUrlRecycler.setLayoutManager(layoutManager);
    }

    private void checkItemCount() {
        if(mAdapter.getItemCount() == 0){
            notifyDataSetIsEmpty();
        }else {
            notifyDataSetIsNotEmpty();
        }
    }

    public void notifyLoad(){
        mUrlRecycler.setVisibility(View.GONE);
        mEmptyView.setVisibility(View.GONE);
        mSpinner.setVisibility(View.VISIBLE);
    }

    public void notifyDataSetIsEmpty() {
        mUrlRecycler.setVisibility(View.GONE);
        mEmptyView.setVisibility(View.VISIBLE);
        mSpinner.setVisibility(View.GONE);
    }

    public void notifyDataSetIsNotEmpty() {
        mUrlRecycler.setVisibility(View.VISIBLE);
        mEmptyView.setVisibility(View.GONE);
        mSpinner.setVisibility(View.GONE);
    }

    public void updateDataSet(){
        if(mAdapter == null){
            initAdapter();
        }else {
            mAdapter.setData(listener.getData());
            checkItemCount();
        }

    }



}
