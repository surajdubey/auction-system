package com.crossover.auctionsystem.interactor;

import android.content.Context;

import com.crossover.auctionsystem.db.BidDataSource;
import com.crossover.auctionsystem.model.Bid;
import com.crossover.auctionsystem.utils.DateTimeUtil;
import com.crossover.auctionsystem.utils.PreferencesManager;

/**
 * Created by suraj on 23/9/16.
 */

public class BidOnItemInteractor {
    private PreferencesManager mPreferencesManager;
    private BidDataSource mBidDataSource;

    public BidOnItemInteractor(Context context) {
        mPreferencesManager = PreferencesManager.initializeInstance(context);
        mBidDataSource = new BidDataSource(context);
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
        bid.setBidStatus(Bid.BID_NO_RESULT);


        bid.setBidTime(currentTime);
    }
}
