
package com.crossover.auctionsystem.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.crossover.auctionsystem.R;
import com.crossover.auctionsystem.adapter.SubmittedItemsRecyclerViewAdapter;
import com.crossover.auctionsystem.interactor.SubmittedItemsForAuctionInteractor;
import com.crossover.auctionsystem.model.Item;
import com.crossover.auctionsystem.presenter.SubmittedItemsForAuctionPresenter;
import com.crossover.auctionsystem.utils.ToolbarUtil;
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

        setToolbar();

        SubmittedItemsForAuctionView submittedItemsForAuctionView = this;
        SubmittedItemsForAuctionInteractor submittedItemsForAuctionInteractor = new SubmittedItemsForAuctionInteractor(mContext);
        mSubmittedItemsForAuctionPresenter = new SubmittedItemsForAuctionPresenter(submittedItemsForAuctionView, submittedItemsForAuctionInteractor);
        setSubmittedItemsRecyclerView();
    }

    private void setToolbar() {
        ToolbarUtil toolbarUtil = new ToolbarUtil(this);
        toolbarUtil.showToolbarWithBackButton(getString(R.string.submitted_items_in_auction));
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

    @Override
    public void showNoItemSubmitted() {

        mSubmittedItemsRecyclerView.setVisibility(View.GONE);

        TextView noItemSubmittedTextView = (TextView) findViewById(R.id.no_item_submitted_for_auction_textview);
        noItemSubmittedTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_submitted_items_for_auction, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;

            case R.id.itemAddItem: startAddItemInAuctionActivity();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void startAddItemInAuctionActivity() {
        Intent intent = new Intent(mContext, AddItemInAuctionActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        mSubmittedItemsForAuctionPresenter.listAllSubmittedItems();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mSubmittedItemsForAuctionPresenter.listAllSubmittedItems();
    }
}
