package com.crossover.auctionsystem.presenter;

import com.crossover.auctionsystem.interactor.ItemsInAuctionInteractor;
import com.crossover.auctionsystem.model.Item;
import com.crossover.auctionsystem.view.ItemsInAuctionView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by suraj on 28/9/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class ItemsInAuctionPresenterTest {

    @Mock
    ItemsInAuctionView mView;

    ItemsInAuctionPresenter mPresenter;

    @Mock
    ItemsInAuctionInteractor mInteractor;

    @Before
    public void setup() throws Exception {
        mPresenter = new ItemsInAuctionPresenter(mView, mInteractor);
    }

    @Test
    public void shouldShowNoItemsAvailableForAuctionView_WhenItemsIsEmpty() {
        when(mInteractor.fetchAllItems()).thenReturn(new ArrayList<Item>());

        mPresenter.listAllItems();
        verify(mView).showNoItemsAvailableForAuctionView();
    }

    @Test
    public void shouldShowItemForAuction_WhenItemsAreAvailable() {
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item());

        when(mInteractor.fetchAllItems()).thenReturn(items);

        mPresenter.listAllItems();
        verify(mView).showItemForAuction(items);
    }
}