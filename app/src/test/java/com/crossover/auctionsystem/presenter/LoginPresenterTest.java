package com.crossover.auctionsystem.presenter;

import com.crossover.auctionsystem.interactor.LoginInteractor;
import com.crossover.auctionsystem.view.LoginView;

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
public class LoginPresenterTest {
    @Mock
    private LoginView mView;

    private LoginPresenter mPresenter;

    @Mock
    private LoginInteractor mInteractor;

    @Before
    public void setUp() throws Exception {
        mPresenter = new LoginPresenter(mView, mInteractor);
    }

    @Test
    public void shouldShowErrorMessageWhenUsernameLengthIsInvalid() {
        Mockito.when(mView.getUsername()).thenReturn("abc");
        Mockito.when(mView.getPassword()).thenReturn("abcde");
        mPresenter.login();

        Mockito.verify(mView).showUsernameLengthInvalidError();
    }

    @Test
    public void shouldShowErrorMessageWhenPasswordLengthIsInvalid() {
        Mockito.when(mView.getUsername()).thenReturn("abcde");
        Mockito.when(mView.getPassword()).thenReturn("");
        mPresenter.login();

        Mockito.verify(mView).showPasswordLengthInvalidError();
    }

    @Test
    public void shouldStartItemsAvailableForBidActivityWhenDataIsValid() {
        Mockito.when(mView.getUsername()).thenReturn("abcde");
        Mockito.when(mView.getPassword()).thenReturn("abcdefg");
        Mockito.when(mInteractor.login("abcde", "abcdefg")).thenReturn(true);

        mPresenter.login();

        Mockito.verify(mView).startItemsAvailableForBidActivity();
    }

    @Test
    public void shouldShowErrorMessageWhenCredentialsAreInvalid() {
        Mockito.when(mView.getUsername()).thenReturn("abcde");
        Mockito.when(mView.getPassword()).thenReturn("abcdef");

        Mockito.when(mInteractor.login("abcde", "abcdef")).thenReturn(false);
        mPresenter.login();

        Mockito.verify(mView).showInvalidCredentialsError();
    }
}