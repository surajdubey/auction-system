package com.crossover.auctionsystem.view;

import com.crossover.auctionsystem.model.Bid;

import java.util.ArrayList;

/**
 * Created by suraj on 24/9/16.
 */

public interface ViewBidsOnItemView {
    void showBids(ArrayList<Bid> bids, boolean isItemWon);

    void showNoItemAvailableForBid();
}
