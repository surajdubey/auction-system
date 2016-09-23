package com.crossover.auctionsystem.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.crossover.auctionsystem.R;

public class ViewItemsInAuctionActivity extends AppCompatActivity {

    private Context mContext = this;
    private RecyclerView mItemsInAuctionRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_items_in_auction);

        setItemsInAuctionRecyclerView();

        setNavigationView();
    }

    private void setItemsInAuctionRecyclerView() {
        mItemsInAuctionRecyclerView = (RecyclerView) findViewById(R.id.items_in_auction_recycler_view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        mItemsInAuctionRecyclerView.setLayoutManager(linearLayoutManager);

    }

    private void setNavigationView() {

    }


}
