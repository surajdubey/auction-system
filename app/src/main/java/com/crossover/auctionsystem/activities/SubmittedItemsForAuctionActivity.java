
package com.crossover.auctionsystem.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.crossover.auctionsystem.R;
import com.crossover.auctionsystem.adapter.SubmittedItemsRecyclerViewAdapter;
import com.crossover.auctionsystem.interactor.SubmittedItemsForAuctionInteractor;
import com.crossover.auctionsystem.model.Item;
import com.crossover.auctionsystem.presenter.SubmittedItemsForAuctionPresenter;
import com.crossover.auctionsystem.view.SubmittedItemsForAuctionView;

import java.util.ArrayList;

public class SubmittedItemsForAuctionActivity extends AppCompatActivity implements SubmittedItemsForAuctionView {

    private Context mContext = this;
    private RecyclerView mSubmittedItemsRecyclerView;
    private SubmittedItemsForAuctionPresenter mSubmittedItemsForAuctionPresenter;
    private SubmittedItemsRecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submitted_items_for_auction);

        SubmittedItemsForAuctionView submittedItemsForAuctionView = this;
        SubmittedItemsForAuctionInteractor submittedItemsForAuctionInteractor = new SubmittedItemsForAuctionInteractor(mContext);
        mSubmittedItemsForAuctionPresenter = new SubmittedItemsForAuctionPresenter(submittedItemsForAuctionView, submittedItemsForAuctionInteractor);
        setSubmittedItemsRecyclerView();
    }

    private void setSubmittedItemsRecyclerView() {
        mSubmittedItemsRecyclerView = (RecyclerView) findViewById(R.id.submitted_item_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        mSubmittedItemsRecyclerView.setLayoutManager(linearLayoutManager);

        mSubmittedItemsForAuctionPresenter.listAllSubmittedItems();
    }

    @Override
    public void setSubmittedItems(ArrayList<Item> items) {
        mAdapter = new SubmittedItemsRecyclerViewAdapter(mContext, items);
        mSubmittedItemsRecyclerView.setAdapter(mAdapter);
    }
}
