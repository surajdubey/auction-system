package com.crossover.auctionsystem.view;

import com.crossover.auctionsystem.model.Bid;
import com.crossover.auctionsystem.model.Item;

import java.util.ArrayList;

/**
 * Created by suraj on 27/9/16.
 */

public interface MyBidsView {
    void showBids(ArrayList<Bid> bids, ArrayList<Item> items);

    void showNoBidsPlacedView();
}
