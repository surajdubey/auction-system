package com.crossover.auctionsystem.presenter;

import com.crossover.auctionsystem.interactor.SubmittedItemsForAuctionInteractor;
import com.crossover.auctionsystem.model.Item;
import com.crossover.auctionsystem.view.SubmittedItemsForAuctionView;

import java.util.ArrayList;

/**
 * Created by suraj on 24/9/16.
 */

public class SubmittedItemsForAuctionPresenter {
    private SubmittedItemsForAuctionView mView;
    private SubmittedItemsForAuctionInteractor mInteractor;
    public SubmittedItemsForAuctionPresenter(SubmittedItemsForAuctionView submittedItemsForAuctionView,
                                             SubmittedItemsForAuctionInteractor submittedItemsForAuctionInteractor) {
        this.mView = submittedItemsForAuctionView;
        this.mInteractor = submittedItemsForAuctionInteractor;
    }

    public void listAllSubmittedItems() {
        ArrayList<Item> items = mInteractor.listAllSubmittedItems();

        mView.setSubmittedItems(items);
    }
}
