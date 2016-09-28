package com.crossover.auctionsystem.presenter;

import com.crossover.auctionsystem.interactor.LauncherInteractor;
import com.crossover.auctionsystem.view.LauncherView;

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
public class LauncherPresenterTest {

    @Mock
    private LauncherView mView;

    @Mock
    private LauncherPresenter mPresenter;

    @Mock
    private LauncherInteractor mInteractor;

    @Before
    public void setUp() throws Exception {
        mPresenter = new LauncherPresenter(mView, mInteractor);
    }

    @Test
    public void shouldStartItemsAvailableForBidActivity_IfUserIsAlreadyLoggedIn() {
        when(mInteractor.isUserLoggedIn()).thenReturn(true);

        mPresenter.startInitialActivity();
        verify(mView).startItemsAvailableForBidActivity();
    }

}