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

import com.exercizes.sanchellios.bananaqr.QrItem;
import com.exercizes.sanchellios.bananaqr.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class QrListFragment extends Fragment {
    private RecyclerView mUrlRecycler;
    private LinearLayout mEmptyView;
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
        mEmptyView = (LinearLayout)rootView.findViewById(R.id.empty_view);
        mAdapter = new RecyclerAdapter(listener.getData());
        if(mAdapter.getItemCount() == 0){
            notifyDataSetIsEmpty();
        }else {
            notifyDataSetIsNotEmpty();
        }
        mUrlRecycler.setAdapter(mAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mUrlRecycler.setLayoutManager(layoutManager);
        return rootView;
    }


    public void notifyDataSetIsEmpty() {
        mUrlRecycler.setVisibility(View.GONE);
        mEmptyView.setVisibility(View.VISIBLE);
        mAdapter.notifyDataSetChanged();
    }

    public void notifyDataSetIsNotEmpty() {
        mUrlRecycler.setVisibility(View.VISIBLE);
        mEmptyView.setVisibility(View.GONE);
        mAdapter.notifyDataSetChanged();
    }

    public void updateDataSet(){
        mAdapter = new RecyclerAdapter(listener.getData());
        mAdapter.notifyDataSetChanged();
    }



}
