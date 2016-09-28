package com.crossover.auctionsystem.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.crossover.auctionsystem.R;
import com.crossover.auctionsystem.adapter.MyBidsRecyclerViewAdapter;
import com.crossover.auctionsystem.interactor.MyBidsInteractor;
import com.crossover.auctionsystem.model.Bid;
import com.crossover.auctionsystem.model.Item;
import com.crossover.auctionsystem.presenter.MyBidsPresenter;
import com.crossover.auctionsystem.utils.ToolbarUtil;
import com.crossover.auctionsystem.view.MyBidsView;

import java.util.ArrayList;

public class MyBidsActivity extends AppCompatActivity implements MyBidsView {

    private Context mContext = this;
    private MyBidsPresenter mBidsPresenter;
    private RecyclerView mBidsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bids);

        setToolbar();
        setMyBidsPresenter();
        setMyBidsRecyclerView();

        mBidsPresenter.listAllBids();
    }

    private void setMyBidsRecyclerView() {
        mBidsRecyclerView = (RecyclerView) findViewById(R.id.my_bids_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        mBidsRecyclerView.setLayoutManager(linearLayoutManager);
    }

    private void setMyBidsPresenter() {
        MyBidsView myBidsView = this;
        MyBidsInteractor myBidsInteractor = new MyBidsInteractor(mContext);
        mBidsPresenter = new MyBidsPresenter(myBidsView, myBidsInteractor);
    }

    private void setToolbar() {
        ToolbarUtil toolbarUtil = new ToolbarUtil(this);
        toolbarUtil.showToolbarWithBackButton(getString(R.string.my_bids));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }

    @Override
    public void showBids(ArrayList<Bid> bids, ArrayList<Item> items) {
        /**
         * set RecyclerView Adapter
         */

        MyBidsRecyclerViewAdapter adapter = new MyBidsRecyclerViewAdapter(mContext, bids, items);
        mBidsRecyclerView.setAdapter(adapter);
    }

    @Override
    public void showNoBidsPlacedView() {
        mBidsRecyclerView.setVisibility(View.GONE);

        TextView noBidsTextView = (TextView) findViewById(R.id.no_bids_textview);
        noBidsTextView.setVisibility(View.VISIBLE);
    }
}
