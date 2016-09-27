package com.crossover.auctionsystem.interactor;

import android.content.Context;

import com.crossover.auctionsystem.db.BidDataSource;
import com.crossover.auctionsystem.db.ItemDataSource;
import com.crossover.auctionsystem.db.UserDataSource;
import com.crossover.auctionsystem.model.Bid;

import java.util.ArrayList;

/**
 * Created by suraj on 24/9/16.
 */

public class ViewBidsOnItemInteractor {
    private BidDataSource mBidDataSource;
    private UserDataSource  mUserDataSource;
    private ItemDataSource mItemDataSource;

    public ViewBidsOnItemInteractor(Context context) {
        mBidDataSource = new BidDataSource(context);
        mUserDataSource = new UserDataSource(context);
        mItemDataSource = new ItemDataSource(context);
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

    public void declareBidAsWinner(Bid bid) {

        /**
         * mark bid status as winner
         */
        mBidDataSource.open();

        mBidDataSource.updateBidStatus(bid, Bid.BID_WINNER);

        /**
         * mark item as item sold
         */
        mItemDataSource.open();
        int itemId = bid.getItemId();
        mItemDataSource.updateItemAsSold(itemId);
    }
}
