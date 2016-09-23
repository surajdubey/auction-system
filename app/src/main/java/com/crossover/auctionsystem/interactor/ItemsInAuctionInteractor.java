package com.crossover.auctionsystem.interactor;

import android.content.Context;

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

    public ItemsInAuctionInteractor(Context context) {
        mItemDataSource = new ItemDataSource(context);
        mSellerDataSource = new SellerDataSource(context);
        mUserDataSource = new UserDataSource(context);
    }

    public ArrayList<Item> fetchAllItems() {
        mItemDataSource.open();

        ArrayList<Item> items = mItemDataSource.fetchAllItems();

        /**
         * for each of these items set seller name
         */

        mSellerDataSource.open();
        if(!items.isEmpty()) {
            mUserDataSource.open();
            for(Item item: items) {
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

    }
}
