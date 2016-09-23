package com.crossover.auctionsystem.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.crossover.auctionsystem.R;

public class ViewItemsInAuctionActivity extends AppCompatActivity {

    private Context mContext = this;
    private RecyclerView mBidWinnersRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_items_in_auction);

        mBidWinnersRecyclerView = (RecyclerView) findViewById(R.id.bid_winners_recycler_view);

        setBidWinnersRecyclerView();

        setNavigationView();
    }

    private void setBidWinnersRecyclerView() {
        
    }

    private void setNavigationView() {

    }


}
