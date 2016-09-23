package com.crossover.auctionsystem.view;

/**
 * Created by suraj on 23/9/16.
 */

public interface BidOnItemView {

    String getBidAmount();

    void showBidAmountInvalidError();

    void showBidAmountLessThanMinimumAmountError();

    void showMinimumBidAmount(int minimumBidAmount);

    void showName(String itemName);

    void showDescription(String itemDescription);
}
