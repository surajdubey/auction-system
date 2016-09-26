package com.crossover.auctionsystem.interactor;

import android.content.Context;

import com.crossover.auctionsystem.db.BidDataSource;
import com.crossover.auctionsystem.db.ItemDataSource;
import com.crossover.auctionsystem.model.Bid;
import com.crossover.auctionsystem.model.Item;
import com.crossover.auctionsystem.utils.DateTimeUtil;
import com.crossover.auctionsystem.utils.PreferencesManager;

/**
 * Created by suraj on 23/9/16.
 */

public class BidOnItemInteractor {
    private PreferencesManager mPreferencesManager;
    private BidDataSource mBidDataSource;
    private ItemDataSource mItemDataSource;

    public BidOnItemInteractor(Context context) {
        mPreferencesManager = PreferencesManager.initializeInstance(context);
        mBidDataSource = new BidDataSource(context);
        mItemDataSource = new ItemDataSource(context);
    }


    public void addBid(int bidAmount, int itemId) {
        mBidDataSource.open();

        /**
         * get userId from PreferencesManager
         */

        int userId = mPreferencesManager.getUserId();
        String currentTime = new DateTimeUtil().getCurrentDateTime();

        Bid bid = new Bid();
        bid.setUserId(userId);
        bid.setItemId(itemId);
        bid.setBidAmount(bidAmount);

        /**
         * for bid status, check if this bid is more than or equal to target amount.
         */
        mItemDataSource.open();
        Item item = mItemDataSource.getItemDetails(itemId);
        int targetAmountForItem = item.getTargetBidAmount();

        int bidStatus;
        if(bidAmount >= targetAmountForItem) {
            bidStatus = Bid.BID_WINNER;

            /**
             * mark item as sold
             */

            mItemDataSource.updateItemAsSold(itemId);
        } else {
            bidStatus = Bid.BID_NO_RESULT;
        }

        bid.setBidStatus(bidStatus);
        bid.setBidTime(currentTime);

        mBidDataSource.addBid(bid);

        mBidDataSource.close();
        mItemDataSource.close();
    }
}
