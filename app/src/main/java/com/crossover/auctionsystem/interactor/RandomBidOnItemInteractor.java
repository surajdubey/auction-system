package com.crossover.auctionsystem.interactor;

import android.content.Context;

import com.crossover.auctionsystem.db.BidDataSource;
import com.crossover.auctionsystem.db.ItemDataSource;
import com.crossover.auctionsystem.db.UserDataSource;
import com.crossover.auctionsystem.model.Bid;
import com.crossover.auctionsystem.model.Item;
import com.crossover.auctionsystem.model.User;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by suraj on 27/9/16.
 */

public class RandomBidOnItemInteractor {
    public static final String TAG = RandomBidOnItemInteractor.class.getName();
    private BidDataSource mBidDataSource;
    private UserDataSource mUserDataSource;
    private ItemDataSource mItemDataSource;
    private Random mRandom;

    public RandomBidOnItemInteractor(Context context) {
        mBidDataSource = new BidDataSource(context);
        mUserDataSource = new UserDataSource(context);
        mItemDataSource = new ItemDataSource(context);
        mRandom = new Random();
    }

    public void placeRandomBid() {
        /**
         * get random userId
         */
        int randomUserId = getRandomUserId();

        if (randomUserId == User.INVALID_USER_ID) {
            return;
        }

        /**
         * get list of all items available for auction for this user
         */


        Item randomItem = getRandomItem();
        if (randomItem == null) {
            return;
        }

        int randomItemId = randomItem.getItemId();
        int randomBidAmount = getRandomBidAmount(randomItem);

        Bid bid = new Bid();
        bid.setItemId(randomItemId);
        bid.setUserId(randomUserId);
        bid.setBidAmount(randomBidAmount);

        mBidDataSource.open();
        mBidDataSource.addBid(bid);

        mUserDataSource.close();
        mItemDataSource.close();
        mBidDataSource.close();
    }

    private int getRandomUserId() {
        mUserDataSource.open();
        return mUserDataSource.getRandomUserId();
    }

    private Item getRandomItem() {
        mItemDataSource.open();

        ArrayList<Item> items = mItemDataSource.fetchAllItems();

        if (items.isEmpty()) {
            return null;
        }

        int itemSize = items.size();

        int randomItemIndex = mRandom.nextInt(itemSize);

        return items.get(randomItemIndex);
    }

    private int getRandomBidAmount(Item randomItem) {
        /**
         * get random amount between minimumAmount and targetAmount of item
         */

        int minimumAmount = randomItem.getMinimumBidAmount();
        int targetAmount = randomItem.getTargetBidAmount();

        int differenceAmount = targetAmount - minimumAmount;

        int randomDifferenceAmount = mRandom.nextInt(differenceAmount);

        return (minimumAmount + randomDifferenceAmount);

    }

}
