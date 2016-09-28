package com.crossover.auctionsystem.interactor;

import android.content.Context;

import com.crossover.auctionsystem.db.BidDataSource;
import com.crossover.auctionsystem.db.ItemDataSource;
import com.crossover.auctionsystem.db.SellerDataSource;
import com.crossover.auctionsystem.db.UserDataSource;
import com.crossover.auctionsystem.model.Item;
import com.crossover.auctionsystem.utils.PreferencesManager;

import java.util.ArrayList;

/**
 * Created by suraj on 23/9/16.
 */

public class ItemsInAuctionInteractor {
    private ItemDataSource mItemDataSource;
    private SellerDataSource mSellerDataSource;
    private UserDataSource mUserDataSource;
    private BidDataSource mBidDataSource;
    private PreferencesManager mPreferencesManager;

    public ItemsInAuctionInteractor(Context context) {
        mItemDataSource = new ItemDataSource(context);
        mSellerDataSource = new SellerDataSource(context);
        mUserDataSource = new UserDataSource(context);
        mBidDataSource = new BidDataSource(context);
        mPreferencesManager = PreferencesManager.initializeInstance(context);
    }

    public ArrayList<Item> fetchAllItems() {
        mItemDataSource.open();

        ArrayList<Item> items = mItemDataSource.fetchAllItems();

        /**
         * for each of these items set seller name
         */

        if (!items.isEmpty()) {

            mSellerDataSource.open();
            mUserDataSource.open();

            for (Item item : items) {
                int userId = mSellerDataSource.getSellerUserIdForItem(item.getItemId());
                String userDisplayName = mUserDataSource.getUserDisplayName(userId);

                item.setSellerName(userDisplayName);
            }
        }

        mItemDataSource.close();
        mUserDataSource.close();
        mSellerDataSource.close();

        return items;
    }

    public String getWinnerDisplayNameForItem(int itemId) {

        mBidDataSource.open();
        int userId = mBidDataSource.getWinnerUserId(itemId);

        mUserDataSource.open();
        String winnerDisplayName = mUserDataSource.getUserDisplayName(userId);

        mBidDataSource.close();
        mUserDataSource.close();
        return winnerDisplayName;
    }

    public int getWinnerBidAmountForItem(int itemId) {
        mBidDataSource.open();

        int winnerBidAmount = mBidDataSource.getWinnerBidAmount(itemId);

        mBidDataSource.close();
        return winnerBidAmount;
    }

    public boolean isUserBiddingOnOwnItsOwnItem(int itemId) {
        int userId = mPreferencesManager.getUserId();
        return userId == itemId;
    }

    public void clearPreferences() {
        mPreferencesManager.clearAllValues();
    }
}
