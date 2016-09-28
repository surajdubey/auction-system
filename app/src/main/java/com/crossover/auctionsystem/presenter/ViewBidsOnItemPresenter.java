package com.crossover.auctionsystem.presenter;

import com.crossover.auctionsystem.interactor.ViewBidsOnItemInteractor;
import com.crossover.auctionsystem.model.Bid;
import com.crossover.auctionsystem.model.Item;
import com.crossover.auctionsystem.view.ViewBidsOnItemView;

import java.util.ArrayList;

/**
 * Created by suraj on 24/9/16.
 */

public class ViewBidsOnItemPresenter {
    private ViewBidsOnItemView mView;
    private ViewBidsOnItemInteractor mInteractor;
    private Item mItem;

    public ViewBidsOnItemPresenter(ViewBidsOnItemView viewBidsOnItemView, ViewBidsOnItemInteractor viewBidsOnItemInteractor) {
        this.mView = viewBidsOnItemView;
        this.mInteractor = viewBidsOnItemInteractor;
    }

    public void declareBidAsWinner(Bid bid) {
        mInteractor.declareBidAsWinner(bid);
    }

    public void setItem(Item item) {
        this.mItem = item;
    }

    public void listAllBidsOnItem() {
        int itemId = mItem.getItemId();
        boolean isItemWon = mItem.isItemSold();
        ArrayList<Bid> bids = mInteractor.getAllBids(itemId);

        if (bids.isEmpty()) {
            mView.showNoItemAvailableForBid();
        } else {
            mView.showBids(bids, isItemWon);
        }
    }
}
