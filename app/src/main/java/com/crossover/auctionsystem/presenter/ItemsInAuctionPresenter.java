package com.crossover.auctionsystem.presenter;

import com.crossover.auctionsystem.interactor.ItemsInAuctionInteractor;
import com.crossover.auctionsystem.model.Item;
import com.crossover.auctionsystem.view.ItemsInAuctionView;

import java.util.ArrayList;

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
        ArrayList<Item> items = mInteractor.fetchAllItems();

        if(items.isEmpty()) {
            mView.showNoItemsAvailableForAuctionView();
        } else {
            mView.showItemForAuction(items);
        }
    }

    public void logout() {
        mInteractor.clearPreferences();
        mView.startSignupActivity();
    }
}
