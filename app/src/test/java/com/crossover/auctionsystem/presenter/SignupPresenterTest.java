package com.crossover.auctionsystem.presenter;

import com.crossover.auctionsystem.interactor.SignupInteractor;
import com.crossover.auctionsystem.view.SignupView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by suraj on 28/9/16.
 */

@RunWith(MockitoJUnitRunner.class)
public class SignupPresenterTest {

    @Mock
    private SignupView mView;

    @Mock
    private SignupPresenter mPresenter;

    @Mock
    private SignupInteractor mInteractor;

    @Before
    public void setup() throws Exception {
        mPresenter = new SignupPresenter(mView, mInteractor);
    }

    @Test
    public void shouldShowErrorMessageWhenUsernameLengthIsInvalid() {
        Mockito.when(mView.getUsername()).thenReturn("abc");
        Mockito.when(mView.getName()).thenReturn("abcde");
        Mockito.when(mView.getPassword()).thenReturn("abcde");
        mPresenter.signup();

        Mockito.verify(mView).showUsernameLengthInvalidError();
    }

    @Test
    public void shouldShowErrorMessageWhenNameLengthIsInvalid() {
        Mockito.when(mView.getUsername()).thenReturn("abcde");
        Mockito.when(mView.getName()).thenReturn("abc");
        Mockito.when(mView.getPassword()).thenReturn("abcde");
        mPresenter.signup();

        Mockito.verify(mView).showNameLengthInvalidError();
    }

    @Test
    public void shouldShowErrorMessageWhenPasswordLengthIsInvalid() {
        Mockito.when(mView.getUsername()).thenReturn("abcde");
        Mockito.when(mView.getName()).thenReturn("abcdef");
        Mockito.when(mView.getPassword()).thenReturn("");
        mPresenter.signup();

        Mockito.verify(mView).showPasswordLengthInvalidError();
    }

    @Test
    public void shouldStartItemsAvailableForBidActivityWhenDataIsValid() {
        Mockito.when(mView.getUsername()).thenReturn("abcde");
        Mockito.when(mView.getName()).thenReturn("abcdef");
        Mockito.when(mView.getPassword()).thenReturn("abcdefg");

        mPresenter.signup();

        Mockito.verify(mView).startItemsAvailableForBidActivity();
    }

    @Test
    public void shouldShowErrorMessageWhenUserExists() {
        Mockito.when(mView.getUsername()).thenReturn("abcde");
        Mockito.when(mView.getName()).thenReturn("abcdef");
        Mockito.when(mView.getPassword()).thenReturn("abcdef");

        Mockito.when(mInteractor.userExists("abcde")).thenReturn(true);
        mPresenter.signup();

        Mockito.verify(mView).showUserExistsError();
    }

}