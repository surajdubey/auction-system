package com.crossover.auctionsystem.presenter;

import com.crossover.auctionsystem.interactor.AddItemInAuctionInteractor;
import com.crossover.auctionsystem.view.AddItemInAuctionView;

import static com.crossover.auctionsystem.utils.NumberUtil.isValidInt;

/**
 * Created by suraj on 23/9/16.
 */

public class AddItemInAuctionPresenter {

    private AddItemInAuctionView mView;
    private AddItemInAuctionInteractor mInteractor;
    private static int MINIMUM_BID_AMOUNT_ALLOWED = 1;

    public AddItemInAuctionPresenter(AddItemInAuctionView addItemInAuctionView, AddItemInAuctionInteractor addItemInAuctionInteractor) {
        this.mView = addItemInAuctionView;
        this.mInteractor = addItemInAuctionInteractor;
    }

    public void submitItem() {
        String name = mView.getName();
        String description = mView.getDescription();
        String minimumAmountText = mView.getMinimumAmount();
        String targetAmountText = mView.getTargetAmount();

        if (name.length() < 5) {
            mView.showNameLengthInvalidError();
            return;
        }

        if (description.length() < 5) {
            mView.showDescriptionLengthInvalidError();
            return;
        }

        if (!isValidInt(minimumAmountText)) {
            mView.showMinimumAmountInvalidError();
            return;
        }

        if (!isValidInt(targetAmountText)) {
            mView.showTargetAmountInvalidError();
            return;
        }

        int minimumBidAmount = Integer.parseInt(minimumAmountText);
        int targetBidAmount = Integer.parseInt(targetAmountText);

        if(minimumBidAmount < MINIMUM_BID_AMOUNT_ALLOWED ) {
            mView.showMinimumAmountInvalidError();
        }

        if (targetBidAmount < minimumBidAmount) {
            mView.showTargetAmountLessThanMinimumAmountError();
            return;
        }

        mInteractor.addItem(name, description, minimumBidAmount, targetBidAmount);
        mView.showItemAddedSuccessMessage();

        mView.closeCurrentActivity();
    }

}
