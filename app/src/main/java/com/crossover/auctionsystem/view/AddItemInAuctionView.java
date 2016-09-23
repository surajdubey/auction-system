package com.crossover.auctionsystem.view;

/**
 * Created by suraj on 23/9/16.
 */

public interface AddItemInAuctionView {
    void showTargetAmountInvalidError();

    void showMinimumAmountInvalidError();

    void showDescriptionLengthInvalidError();

    void showNameLengthInvalidError();

    String getTargetAmount();

    String getMinimumAmount();

    String getDescription();

    String getName();
}
