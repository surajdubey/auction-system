package com.crossover.auctionsystem.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.crossover.auctionsystem.R;
import com.crossover.auctionsystem.adapter.ViewBidsOnItemRecyclerViewAdapter;
import com.crossover.auctionsystem.interactor.ViewBidsOnItemInteractor;
import com.crossover.auctionsystem.model.Bid;
import com.crossover.auctionsystem.model.Item;
import com.crossover.auctionsystem.presenter.ViewBidsOnItemPresenter;
import com.crossover.auctionsystem.view.ViewBidsOnItemView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

public class ViewBidsOnItemActivity extends AppCompatActivity implements ViewBidsOnItemView {

    private Context mContext = this;
    private RecyclerView mBidOnItemRecyclerView;
    private ViewBidsOnItemPresenter mPresenter;
    private TextView mNoItemAvailableTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_bids_on_item);

        ViewBidsOnItemView viewBidsOnItemView = this;
        ViewBidsOnItemInteractor viewBidsOnItemInteractor = new ViewBidsOnItemInteractor(mContext);

        mPresenter = new ViewBidsOnItemPresenter(viewBidsOnItemView, viewBidsOnItemInteractor);
        mNoItemAvailableTextView = (TextView) findViewById(R.id.no_bids_textview);

        setItemOnPresenter();

        setBidsOnItemRecyclerView();
    }

    private void setBidsOnItemRecyclerView() {
        mBidOnItemRecyclerView = (RecyclerView) findViewById(R.id.bids_on_item_recyclerview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        mBidOnItemRecyclerView.setLayoutManager(linearLayoutManager);
        mPresenter.listAllBidsOnItem();
    }

    private void setItemOnPresenter() {
        Item item = EventBus.getDefault().getStickyEvent(Item.class);
        mPresenter.setItem(item);
    }

    @Override
    public void showBids(ArrayList<Bid> bids, boolean isItemWon) {
        ViewBidsOnItemRecyclerViewAdapter adapter = new ViewBidsOnItemRecyclerViewAdapter(mContext, mPresenter, bids, isItemWon);
        mBidOnItemRecyclerView.setAdapter(adapter);
    }

    @Override
    public void showNoItemAvailableForBid() {
        mBidOnItemRecyclerView.setVisibility(View.GONE);
        mNoItemAvailableTextView.setVisibility(View.VISIBLE);
    }
}
