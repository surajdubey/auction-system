package com.crossover.auctionsystem.presenter;

import com.crossover.auctionsystem.interactor.AddItemInAuctionInteractor;
import com.crossover.auctionsystem.interactor.BidOnItemInteractor;
import com.crossover.auctionsystem.model.Item;
import com.crossover.auctionsystem.view.AddItemInAuctionView;
import com.crossover.auctionsystem.view.BidOnItemView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by suraj on 28/9/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class BidOnItemPresenterTest {
    @Mock
    BidOnItemView mView;

    BidOnItemPresenter mPresenter;

    @Mock
    BidOnItemInteractor mInteractor;

    Item mItem;

    @Before
    public void setup() throws Exception {
        mPresenter = new BidOnItemPresenter(mView, mInteractor);
        mItem = mock(Item.class);
        mPresenter.setItem(mItem);
    }

    @Test
    public void shouldShowBidAmountInvalidError_WhenBidAmountIsInvalid() {
        when(mView.getBidAmount()).thenReturn("abc.");
        mPresenter.submitBid();

        verify(mView).showBidAmountInvalidError();
    }

    @Test
    public void shouldShowBidAmountLessThanMinimumAmountError_WhenBidAmountIsLessThanMinimumAmount() {
        when(mView.getBidAmount()).thenReturn("100");
        when(mItem.getMinimumBidAmount()).thenReturn(1000);
        mPresenter.submitBid();

        verify(mView).showBidAmountLessThanMinimumAmountError();

    }

    @Test
    public void shouldShowBidAddedSuccessMessage_WhenDataIsValid() {
        when(mView.getBidAmount()).thenReturn("1000");
        when(mItem.getMinimumBidAmount()).thenReturn(100);
        mPresenter.submitBid();

        verify(mView).showBidAddedSuccessMessage();

    }
}