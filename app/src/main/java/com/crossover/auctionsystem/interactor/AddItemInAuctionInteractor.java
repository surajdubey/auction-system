package com.crossover.auctionsystem.interactor;

import android.content.Context;

import com.crossover.auctionsystem.db.ItemDataSource;
import com.crossover.auctionsystem.db.SellerDataSource;
import com.crossover.auctionsystem.model.Item;
import com.crossover.auctionsystem.model.Seller;
import com.crossover.auctionsystem.utils.PreferencesManager;

/**
 * Created by suraj on 23/9/16.
 */

public class AddItemInAuctionInteractor {

    private ItemDataSource mItemDataSource;
    private SellerDataSource mSellerDataSource;
    private PreferencesManager mPreferencesManager;

    public AddItemInAuctionInteractor(Context context) {
        mItemDataSource = new ItemDataSource(context);
        mSellerDataSource = new SellerDataSource(context);
        mPreferencesManager = PreferencesManager.initializeInstance(context);

    }

    public void addItem(String name, String description, int minimumBidAmount, int targetBidAmount) {
        Item item = new Item();
        item.setItemName(name);
        item.setItemDescription(description);
        item.setMinimumBidAmount(minimumBidAmount);
        item.setTargetBidAmount(targetBidAmount);

        long itemId = mItemDataSource.addItem(item);
        int userId = mPreferencesManager.getUserId();

        Seller seller = new Seller();

        int itemIdInt = (int) itemId;
        seller.setItemId(itemIdInt);
        seller.setUserId(userId);
        mSellerDataSource.addSeller(seller);

        mItemDataSource.close();
        mSellerDataSource.close();
    }
}
