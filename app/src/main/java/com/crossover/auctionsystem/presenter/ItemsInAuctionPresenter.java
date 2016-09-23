package com.crossover.auctionsystem.presenter;

import com.crossover.auctionsystem.interactor.ItemsInAuctionInteractor;
import com.crossover.auctionsystem.view.ItemsInAuctionView;

/**
 * Created by suraj on 23/9/16.
 */

public class ItemsInAuctionPresenter {
    private ItemsInAuctionView mView;
    private ItemsInAuctionInteractor mInteractor;

    public ItemsInAuctionPresenter(ItemsInAuctionView itemsInAuctionView, ItemsInAuctionInteractor itemsInAuctionInteractor) {
        this.mView = itemsInAuctionView;
        this.mInteractor = itemsInAuctionInteractor;
    }

    public void listAllItems() {
        mInteractor.fetchAllItems();
    }
}
