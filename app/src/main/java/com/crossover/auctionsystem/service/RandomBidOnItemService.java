package com.crossover.auctionsystem.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.crossover.auctionsystem.interactor.RandomBidOnItemInteractor;

import java.util.Timer;
import java.util.TimerTask;

public class RandomBidOnItemService extends Service {

    private Context mContext = this;
    private Timer mTimer = new Timer();

    private Handler mRandomBidHandler = new Handler();
    private RandomBidOnItemInteractor mInteractor;

    private static final long MILLIS_IN_SECOND = 1000;
    private static final long SECONDS_IN_MINUTE = 60;
    private static final long PERIOD_IN_MINUTE = 2;

    private static final long DELAY_IN_MILLIS = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        startService();
    }

    private void startService() {

        long periodInMillis = PERIOD_IN_MINUTE * SECONDS_IN_MINUTE * MILLIS_IN_SECOND;

        RandomBidTimerTask randomBidTimerTask = new RandomBidTimerTask();

        mInteractor = new RandomBidOnItemInteractor(mContext);
        mTimer.scheduleAtFixedRate(randomBidTimerTask, DELAY_IN_MILLIS, periodInMillis);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private class RandomBidTimerTask extends TimerTask {

        @Override
        public void run() {
            mRandomBidHandler.post(new Runnable() {
                @Override
                public void run() {
                    mInteractor.placeRandomBid();
                }
            });
        }
    }

}
