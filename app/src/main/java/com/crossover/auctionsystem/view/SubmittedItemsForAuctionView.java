package com.crossover.auctionsystem.view;

import com.crossover.auctionsystem.model.Item;

import java.util.ArrayList;

/**
 * Created by suraj on 24/9/16.
 */

public interface SubmittedItemsForAuctionView {
    void setSubmittedItems(ArrayList<Item> items);

    void showNoItemSubmitted();
}
