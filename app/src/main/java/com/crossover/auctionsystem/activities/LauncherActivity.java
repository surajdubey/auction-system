package com.crossover.auctionsystem.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.crossover.auctionsystem.R;
import com.crossover.auctionsystem.interactor.LauncherInteractor;
import com.crossover.auctionsystem.presenter.LauncherPresenter;
import com.crossover.auctionsystem.view.LauncherView;

public class LauncherActivity extends AppCompatActivity implements LauncherView {

    private Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        LauncherView launcherView = this;
        LauncherInteractor launcherInteractor = new LauncherInteractor(mContext);

        LauncherPresenter launcherPresenter = new LauncherPresenter(launcherView, launcherInteractor);
        launcherPresenter.startInitialActivity();
    }


    @Override
    public void startSignupActivity() {
        Intent intent = new Intent(mContext, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void startItemsAvailableForBidActivity() {
        Intent intent = new Intent(mContext, ItemsAvailableForBidActivity.class);
        startActivity(intent);
        finish();
    }
}
