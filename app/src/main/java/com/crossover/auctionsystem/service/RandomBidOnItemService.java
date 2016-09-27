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

/**
 * Created by suraj on 27/9/16.
 */

public class RandomBidOnItemService extends Service {

    private Context mContext = this;
    private Timer mTimer = new Timer();

    private Handler mRandomBidHandler = new Handler();
    private RandomBidOnItemInteractor mInteractor;

    @Override
    public void onCreate() {
        super.onCreate();
        startService();
    }

    private void startService() {
        long delayInSeconds = 0;

        long millisInSeconds = 1000;
        long secondsInMinutes = 10;
        long periodInMinutes  = 1;
        long periodInMillis = periodInMinutes * secondsInMinutes * millisInSeconds;

        RandomBidTimerTask randomBidTimerTask = new RandomBidTimerTask();

        mInteractor = new RandomBidOnItemInteractor(mContext);
        mTimer.scheduleAtFixedRate(randomBidTimerTask, delayInSeconds, periodInMillis);
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
