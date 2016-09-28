package com.crossover.auctionsystem.presenter;

import com.crossover.auctionsystem.interactor.MyBidsInteractor;
import com.crossover.auctionsystem.model.Bid;
import com.crossover.auctionsystem.model.Item;
import com.crossover.auctionsystem.view.MyBidsView;

import java.util.ArrayList;

/**
 * Created by suraj on 27/9/16.
 */

public class MyBidsPresenter {

    private MyBidsView mView;
    private MyBidsInteractor mInteractor;

    public MyBidsPresenter(MyBidsView myBidsView, MyBidsInteractor myBidsInteractor) {
        this.mView = myBidsView;
        this.mInteractor = myBidsInteractor;
    }

    public void listAllBids() {
        ArrayList<Bid> bids = mInteractor.fetchAllBids();
        ArrayList<Item> items = mInteractor.fetchAllItemsFromBids(bids);
        if (bids.isEmpty()) {
            mView.showNoBidsPlacedView();
        } else {
            mView.showBids(bids, items);
        }
    }
}
