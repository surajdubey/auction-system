package com.crossover.auctionsystem.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.crossover.auctionsystem.R;
import com.crossover.auctionsystem.interactor.ItemsInAuctionInteractor;
import com.crossover.auctionsystem.model.Item;
import com.crossover.auctionsystem.presenter.ItemsInAuctionPresenter;
import com.crossover.auctionsystem.view.ItemsInAuctionView;

import java.util.ArrayList;

public class ViewItemsInAuctionActivity extends AppCompatActivity implements ItemsInAuctionView {

    private Context mContext = this;
    private RecyclerView mItemsInAuctionRecyclerView;

    private ItemsInAuctionPresenter mItemsInAuctionPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_items_in_auction);

        ItemsInAuctionView itemsInAuctionView = this;
        ItemsInAuctionInteractor itemsInAuctionInteractor = new ItemsInAuctionInteractor(mContext);

        mItemsInAuctionPresenter = new ItemsInAuctionPresenter(itemsInAuctionView, itemsInAuctionInteractor);

        setItemsInAuctionRecyclerView();

        setNavigationView();
    }

    private void setItemsInAuctionRecyclerView() {
        mItemsInAuctionRecyclerView = (RecyclerView) findViewById(R.id.items_in_auction_recycler_view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        mItemsInAuctionRecyclerView.setLayoutManager(linearLayoutManager);

        mItemsInAuctionPresenter.listAllItems();
    }

    //TODO
    private void setNavigationView() {

    }

    @Override
    public void showNoItemsAvailableForAuctionView() {

    }

    @Override
    public void showItemForAuction(ArrayList<Item> items) {

    }
}
