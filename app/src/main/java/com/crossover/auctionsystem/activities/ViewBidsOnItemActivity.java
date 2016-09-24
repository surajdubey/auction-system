package com.crossover.auctionsystem.activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.crossover.auctionsystem.R;
import com.crossover.auctionsystem.view.ViewBidsOnItemView;

public class ViewBidsOnItemActivity extends AppCompatActivity implements ViewBidsOnItemView {

    private Context mContext = this;
    private RecyclerView mBidOnItemRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_bids_on_item);

        setBidsOnItemRecyclerView();



    }

    private void setBidsOnItemRecyclerView() {
        mBidOnItemRecyclerView = (RecyclerView) findViewById(R.id.bids_on_item_recyclerview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        mBidOnItemRecyclerView.setLayoutManager(linearLayoutManager);

    }


}
