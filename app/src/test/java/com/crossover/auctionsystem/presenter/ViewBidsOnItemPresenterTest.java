package com.crossover.auctionsystem.presenter;

import com.crossover.auctionsystem.interactor.LauncherInteractor;
import com.crossover.auctionsystem.interactor.ViewBidsOnItemInteractor;
import com.crossover.auctionsystem.model.Bid;
import com.crossover.auctionsystem.model.Item;
import com.crossover.auctionsystem.view.LauncherView;
import com.crossover.auctionsystem.view.ViewBidsOnItemView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by suraj on 28/9/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class ViewBidsOnItemPresenterTest {

    @Mock
    private ViewBidsOnItemView mView;

    private ViewBidsOnItemPresenter mPresenter;

    Item mItem = mock(Item.class);

    @Mock
    private ViewBidsOnItemInteractor mInteractor;

    @Before
    public void setUp() throws Exception {
        mPresenter = new ViewBidsOnItemPresenter(mView, mInteractor);
        mPresenter.setItem(mItem);
    }

    @Test
    public void shouldShowNoItemAvailableForBid_WhenItemsAreNotAvailableForBid() {
        int dummyItemId = 0;
        when(mInteractor.getAllBids(dummyItemId)).thenReturn(new ArrayList<Bid>());

        mPresenter.listAllBidsOnItem();

        verify(mView).showNoItemAvailableForBid();
    }

    @Test
    public void shouldShowBids_WhenItemsAreAvailableForBid() {
        int dummyItemId = 0;
        boolean isItemSold = false;
        ArrayList<Bid> bids = new ArrayList<>();

        bids.add(new Bid());

        when(mItem.isItemSold()).thenReturn(isItemSold);
        when(mInteractor.getAllBids(dummyItemId)).thenReturn(bids);

        mPresenter.listAllBidsOnItem();

        verify(mView).showBids(bids, isItemSold);
    }
}