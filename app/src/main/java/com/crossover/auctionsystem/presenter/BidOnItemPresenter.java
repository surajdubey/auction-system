package com.crossover.auctionsystem.presenter;

import com.crossover.auctionsystem.interactor.BidOnItemInteractor;
import com.crossover.auctionsystem.model.Item;
import com.crossover.auctionsystem.utils.NumberUtil;
import com.crossover.auctionsystem.view.BidOnItemView;

/**
 * Created by suraj on 23/9/16.
 */

public class BidOnItemPresenter {

    private BidOnItemView mView;
    private BidOnItemInteractor mInteractor;
    private Item mItem;

    public BidOnItemPresenter(BidOnItemView bidOnItemView, BidOnItemInteractor bidOnItemInteractor) {
        this.mView = bidOnItemView;
        this.mInteractor = bidOnItemInteractor;
    }

    public void setItem(Item item) {
        this.mItem = item;
        mView.showMinimumBidAmount(item.getMinimumBidAmount());
        mView.showName(item.getItemName());
        mView.showDescription(item.getItemDescription());
    }

    public void submitBid() {
        String bidAmountText = mView.getBidAmount();
        if(!NumberUtil.isValidInt(bidAmountText)) {
            mView.showBidAmountInvalidError();
            return;
        }

        int bidAmount = Integer.parseInt(bidAmountText);
        int minimumBidAmount = mItem.getMinimumBidAmount();

        if(bidAmount < minimumBidAmount) {
            mView.showBidAmountLessThanMinimumAmountError();
            return;
        }

        int itemId = mItem.getItemId();
        mInteractor.addBid(bidAmount, itemId);

        mView.showBidAddedSuccessMessage();
        mView.closeCurrentActivity();
    }
}
