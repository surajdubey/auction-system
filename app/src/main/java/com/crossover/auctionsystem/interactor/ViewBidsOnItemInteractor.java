package com.crossover.auctionsystem.interactor;

import android.content.Context;

import com.crossover.auctionsystem.db.BidDataSource;
import com.crossover.auctionsystem.db.UserDataSource;
import com.crossover.auctionsystem.model.Bid;

import java.util.ArrayList;

/**
 * Created by suraj on 24/9/16.
 */

public class ViewBidsOnItemInteractor {
    private BidDataSource mBidDataSource;
    private UserDataSource  mUserDataSource;

    public ViewBidsOnItemInteractor(Context context) {
        mBidDataSource = new BidDataSource(context);
        mUserDataSource = new UserDataSource(context);
    }

    public ArrayList<Bid> getAllBids(int itemId) {

        mBidDataSource.open();
        ArrayList<Bid> bids = mBidDataSource.getAllBidsOnItem(itemId);

        /**
         * set bidder name
         */

        if(!bids.isEmpty()) {
            mUserDataSource.open();
            for (Bid bid : bids) {
                int userId = bid.getUserId();
                String bidderName = mUserDataSource.getUserDisplayName(userId);
                bid.setBidderName(bidderName);
            }
        }

        mUserDataSource.close();
        mBidDataSource.close();

        return bids;
    }
}
