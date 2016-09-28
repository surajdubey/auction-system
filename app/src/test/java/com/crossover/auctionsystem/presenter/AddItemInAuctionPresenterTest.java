package com.crossover.auctionsystem.presenter;

import com.crossover.auctionsystem.interactor.AddItemInAuctionInteractor;
import com.crossover.auctionsystem.view.AddItemInAuctionView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by suraj on 28/9/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class AddItemInAuctionPresenterTest {
    @Mock
    AddItemInAuctionView mView;

    AddItemInAuctionPresenter mPresenter;

    @Mock
    AddItemInAuctionInteractor mInteractor;

    @Before
    public void setup() throws Exception {
        mPresenter = new AddItemInAuctionPresenter(mView, mInteractor);
    }

    @Test
    public void shouldShowNameLengthInvalidErrorWhenNameLengthIsInvalid() {
        when(mView.getName()).thenReturn("ab");
        when(mView.getDescription()).thenReturn("abcdef");
        when(mView.getMinimumAmount()).thenReturn("1000");
        when(mView.getTargetAmount()).thenReturn("10000");

        mPresenter.submitItem();

        verify(mView).showNameLengthInvalidError();
    }

    @Test
    public void shouldShowDescriptionLengthInvalidErrorWhenNameLengthIsInvalid() {
        when(mView.getName()).thenReturn("abcef");
        when(mView.getDescription()).thenReturn("abcd");
        when(mView.getMinimumAmount()).thenReturn("1000");
        when(mView.getTargetAmount()).thenReturn("10000");

        mPresenter.submitItem();

        verify(mView).showDescriptionLengthInvalidError();
    }

    @Test
    public void shouldShowMinimumAmountInvalidErrorWhenMinimumAmountIsInvalid() {
        when(mView.getName()).thenReturn("abcdef");
        when(mView.getDescription()).thenReturn("abcdef");
        when(mView.getMinimumAmount()).thenReturn("abcdef");
        when(mView.getTargetAmount()).thenReturn("1");

        mPresenter.submitItem();

        verify(mView).showMinimumAmountInvalidError();
    }

    @Test
    public void shouldShowTargetAmountInvalidErrorWhenMinimumAmountIsInvalid() {
        when(mView.getName()).thenReturn("abcdef");
        when(mView.getDescription()).thenReturn("abcdef");
        when(mView.getMinimumAmount()).thenReturn("123");
        when(mView.getTargetAmount()).thenReturn("abcdef");

        mPresenter.submitItem();

        verify(mView).showTargetAmountInvalidError();
    }

    @Test
    public void shouldShowTargetAmountLessThanMinimumAmountErrorErrorWhenTargetAmountIsLessThanMinimumAmount() {
        when(mView.getName()).thenReturn("abcdef");
        when(mView.getDescription()).thenReturn("abcdef");
        when(mView.getMinimumAmount()).thenReturn("123");
        when(mView.getTargetAmount()).thenReturn("12");

        mPresenter.submitItem();

        verify(mView).showTargetAmountLessThanMinimumAmountError();
    }

    @Test
    public void shouldShowItemAddedSuccessMessageDataIsValid() {
        when(mView.getName()).thenReturn("abcdef");
        when(mView.getDescription()).thenReturn("abcdef");
        when(mView.getMinimumAmount()).thenReturn("123");
        when(mView.getTargetAmount()).thenReturn("1234");

        mPresenter.submitItem();

        verify(mView).showItemAddedSuccessMessage();
    }

}