package com.crossover.auctionsystem.interactor;

import android.content.Context;

import com.crossover.auctionsystem.db.BidDataSource;
import com.crossover.auctionsystem.db.ItemDataSource;
import com.crossover.auctionsystem.db.UserDataSource;
import com.crossover.auctionsystem.model.Bid;
import com.crossover.auctionsystem.model.Item;
import com.crossover.auctionsystem.utils.PreferencesManager;

import java.util.ArrayList;

/**
 * Created by suraj on 27/9/16.
 */

public class MyBidsInteractor {
    private BidDataSource mBidDataSource;
    private PreferencesManager mPreferencesManager;
    private ItemDataSource mItemDataSource;
    private UserDataSource mUserDataSource;

    public MyBidsInteractor(Context context) {
        mPreferencesManager = PreferencesManager.initializeInstance(context);
        mBidDataSource = new BidDataSource(context);
        mItemDataSource = new ItemDataSource(context);
        mUserDataSource = new UserDataSource(context);
    }

    public ArrayList<Bid> fetchAllBids() {
        /**
         * get userId from PreferencesManager
         */
        int userId = mPreferencesManager.getUserId();
        mBidDataSource.open();

        return mBidDataSource.getAllBidsByUser(userId);
    }

    public ArrayList<Item> fetchAllItemsFromBids(ArrayList<Bid> bids) {

        ArrayList<Item> items = new ArrayList<>();

        /**
         * for each bid set seller name
         */
        if (!bids.isEmpty()) {

            mItemDataSource.open();
            mUserDataSource.open();

            for (Bid bid : bids) {
                /**
                 * get item details
                 */
                int itemId = bid.getItemId();
                Item item = mItemDataSource.getItemDetails(itemId);

                items.add(item);
            }
        }

        mItemDataSource.close();
        mUserDataSource.close();

        return items;
    }
}
