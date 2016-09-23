package com.crossover.auctionsystem.view;

import com.crossover.auctionsystem.model.Item;

import java.util.ArrayList;

/**
 * Created by suraj on 23/9/16.
 */

public interface ItemsInAuctionView {

    void showNoItemsAvailableForAuctionView();

    void showItemForAuction(ArrayList<Item> items);
}
