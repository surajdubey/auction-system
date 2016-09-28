package com.crossover.auctionsystem.interactor;

import android.content.Context;

import com.crossover.auctionsystem.db.ItemDataSource;
import com.crossover.auctionsystem.db.SellerDataSource;
import com.crossover.auctionsystem.model.Item;
import com.crossover.auctionsystem.utils.PreferencesManager;

import java.util.ArrayList;

/**
 * Created by suraj on 24/9/16.
 */

public class SubmittedItemsForAuctionInteractor {
    private PreferencesManager mPreferencesManager;
    private SellerDataSource mSellerDataSource;
    private ItemDataSource mItemDataSource;

    public SubmittedItemsForAuctionInteractor(Context context) {
        mPreferencesManager = PreferencesManager.initializeInstance(context);
        mSellerDataSource = new SellerDataSource(context);
        mItemDataSource = new ItemDataSource(context);
    }

    public ArrayList<Item> listAllSubmittedItems() {

        mSellerDataSource.open();

        int userId = mPreferencesManager.getUserId();
        ArrayList<Item> items = mSellerDataSource.fetchAllItemsSubmittedByUser(userId);

        /**
         * set item name,description and available status for each of these items
         */

        if (!items.isEmpty()) {
            mItemDataSource.open();
            for (int index = 0; index < items.size(); index++) {
                Item oldItem = items.get(index);
                Item item = mItemDataSource.getItemDetails(oldItem.getItemId());
                items.set(index, item);
            }
        }

        //close datasources
        mSellerDataSource.close();
        mItemDataSource.close();

        return items;
    }
}
