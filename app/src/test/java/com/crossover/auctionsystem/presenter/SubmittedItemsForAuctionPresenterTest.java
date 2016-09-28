package com.crossover.auctionsystem.presenter;

import com.crossover.auctionsystem.interactor.SubmittedItemsForAuctionInteractor;
import com.crossover.auctionsystem.model.Item;
import com.crossover.auctionsystem.view.SubmittedItemsForAuctionView;
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
public class SubmittedItemsForAuctionPresenterTest {
    @Mock
    private SubmittedItemsForAuctionView mView;

    private SubmittedItemsForAuctionPresenter mPresenter;

    @Mock
    private SubmittedItemsForAuctionInteractor mInteractor;

    @Before
    public void setup() {
        mPresenter = new SubmittedItemsForAuctionPresenter(mView, mInteractor);
    }

    @Test
    public void shouldShowNoItemSubmitted_WhenItemNotAvailable() {
        when(mInteractor.listAllSubmittedItems()).thenReturn(new ArrayList<Item>());

        mPresenter.listAllSubmittedItems();

        verify(mView).showNoItemSubmitted();
    }

    @Test
    public void shouldShowNoItemSubmitted_WhenItemAreAvailable() {
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item());

        when(mInteractor.listAllSubmittedItems()).thenReturn(items);

        mPresenter.listAllSubmittedItems();

        verify(mView).setSubmittedItems(items);
    }
}