package com.crossover.auctionsystem.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.crossover.auctionsystem.R;
import com.crossover.auctionsystem.adapter.ViewItemsInAuctionRecyclerViewAdapter;
import com.crossover.auctionsystem.interactor.ItemsInAuctionInteractor;
import com.crossover.auctionsystem.model.Item;
import com.crossover.auctionsystem.presenter.ItemsInAuctionPresenter;
import com.crossover.auctionsystem.service.RandomBidOnItemService;
import com.crossover.auctionsystem.utils.ToolbarUtil;
import com.crossover.auctionsystem.view.ItemsInAuctionView;

import java.util.ArrayList;

public class ViewItemsInAuctionActivity extends AppCompatActivity implements ItemsInAuctionView, NavigationView.OnNavigationItemSelectedListener {

    private Context mContext = this;
    private RecyclerView mItemsInAuctionRecyclerView;
    private TextView mNoItemAvailableForAuctionTextView;

    private ItemsInAuctionPresenter mItemsInAuctionPresenter;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_items_in_auction);

        mItemsInAuctionRecyclerView = (RecyclerView) findViewById(R.id.items_in_auction_recycler_view);
        mNoItemAvailableForAuctionTextView = (TextView) findViewById(R.id.no_item_available_textview);


        setToolbar();
        setItemsInAuctionPresenter();
        setItemsInAuctionRecyclerView();

        setNavigationView();

    }

    private void setItemsInAuctionPresenter() {
        ItemsInAuctionView itemsInAuctionView = this;
        ItemsInAuctionInteractor itemsInAuctionInteractor = new ItemsInAuctionInteractor(mContext);

        mItemsInAuctionPresenter = new ItemsInAuctionPresenter(itemsInAuctionView, itemsInAuctionInteractor);
    }

    private void setToolbar() {
        ToolbarUtil toolbarUtil = new ToolbarUtil(this);
        toolbarUtil.showToolbarWithBackButton(getString(R.string.items_available_for_auction));
    }

    private void setItemsInAuctionRecyclerView() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        mItemsInAuctionRecyclerView.setLayoutManager(linearLayoutManager);

        mItemsInAuctionPresenter.listAllItems();
    }

    private void setNavigationView() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, 0, 0) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }

        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void showNoItemsAvailableForAuctionView() {
        mItemsInAuctionRecyclerView.setVisibility(View.GONE);
        mNoItemAvailableForAuctionTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showItemForAuction(ArrayList<Item> items) {
        ViewItemsInAuctionRecyclerViewAdapter adapter = new ViewItemsInAuctionRecyclerViewAdapter(mContext, items);
        mItemsInAuctionRecyclerView.setAdapter(adapter);
    }

    @Override
    public void startSignupActivity() {
        Intent intent = new Intent(mContext, SignupActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void startRandomBidOnItemService() {
        Intent intent = new Intent(mContext, RandomBidOnItemService.class);
        startService(intent);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.itemSubmittedItemsInAuction:
                startSubmittedItemsInAuctionActivity();
                break;

            case R.id.itemViewMyBids:
                startMyBidsActivity();
                break;

            case R.id.itemLogout:
                logout();
                break;

            default:
                break;
        }

        /**
         * close drawer
         */
        mDrawerLayout.closeDrawers();

        return true;
    }

    private void startMyBidsActivity() {
        Intent intent = new Intent(mContext, MyBidsActivity.class);
        startActivity(intent);
    }

    private void startSubmittedItemsInAuctionActivity() {
        Intent intent = new Intent(mContext, SubmittedItemsForAuctionActivity.class);
        startActivity(intent);
    }

    private void logout() {
        stopRandomBidOnItemService();
        mItemsInAuctionPresenter.logout();
    }

    private void stopRandomBidOnItemService() {
        Intent intent = new Intent(mContext, RandomBidOnItemService.class);
        stopService(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mItemsInAuctionPresenter.listAllItems();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mItemsInAuctionPresenter.listAllItems();
    }
}
