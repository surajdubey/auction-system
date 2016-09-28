package com.crossover.auctionsystem.presenter;

import com.crossover.auctionsystem.interactor.LoginInteractor;
import com.crossover.auctionsystem.interactor.MyBidsInteractor;
import com.crossover.auctionsystem.model.Bid;
import com.crossover.auctionsystem.model.Item;
import com.crossover.auctionsystem.view.LoginView;
import com.crossover.auctionsystem.view.MyBidsView;

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
public class MyBidsPresenterTest {

    @Mock
    private MyBidsView mView;

    private MyBidsPresenter mPresenter;

    @Mock
    private MyBidsInteractor mInteractor;

    @Before
    public void setUp() throws Exception {
        mPresenter = new MyBidsPresenter(mView, mInteractor);
    }

    @Test
    public void shouldShowNoBidsPlacedView_WhenNoBidsArePlaced() {
        ArrayList<Bid> bids = new ArrayList<>();
        ArrayList<Item> items = new ArrayList<>();
        when(mInteractor.fetchAllBids()).thenReturn(bids);
        when(mInteractor.fetchAllItemsFromBids(bids)).thenReturn(items);

        mPresenter.listAllBids();

        verify(mView).showNoBidsPlacedView();
    }

    @Test
    public void shouldShowBids_WhenBidsArePlaced() {
        ArrayList<Bid> bids = new ArrayList<>();
        bids.add(new Bid());

        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item());

        when(mInteractor.fetchAllBids()).thenReturn(bids);
        when(mInteractor.fetchAllItemsFromBids(bids)).thenReturn(items);

        mPresenter.listAllBids();

        verify(mView).showBids(bids, items);
    }
}