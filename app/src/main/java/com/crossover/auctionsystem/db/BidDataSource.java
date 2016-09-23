package com.crossover.auctionsystem.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.crossover.auctionsystem.model.Bid;

/**
 * Created by suraj on 23/9/16.
 */

public class BidDataSource {
    private DatabaseHelper mDatabaseHelper;
    private SQLiteDatabase mDatabase;

    public BidDataSource(Context context) {
        this.mDatabaseHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLiteException {
        mDatabase = mDatabaseHelper.getWritableDatabase();
    }

    public void close() {
        if(mDatabase!=null) {
            mDatabase.close();
        }
    }

    public void addBid(Bid bid) {
        ContentValues values = new ContentValues();
        values.put(AuctionContract.Bid.COLUMN_NAME_USER_ID, bid.getUserId());
        values.put(AuctionContract.Bid.COLUMN_NAME_ITEM_ID, bid.getItemId());
        values.put(AuctionContract.Bid.COLUMN_NAME_BID_AMOUNT, bid.getBidAmount());
        values.put(AuctionContract.Bid.COLUMN_NAME_BID_TIME, bid.getBidTime());
        values.put(AuctionContract.Bid.COLUMN_NAME_BID_STATUS, bid.getBidStatus());

        mDatabase.insert(AuctionContract.Bid.TABLE_NAME, null, values);
    }

    public void updateBid(Bid bid) {
        ContentValues values = new ContentValues();
        values.put(AuctionContract.Bid.COLUMN_NAME_USER_ID, bid.getUserId());
        values.put(AuctionContract.Bid.COLUMN_NAME_ITEM_ID, bid.getItemId());
        values.put(AuctionContract.Bid.COLUMN_NAME_BID_AMOUNT, bid.getBidAmount());
        values.put(AuctionContract.Bid.COLUMN_NAME_BID_TIME, bid.getBidTime());
        values.put(AuctionContract.Bid.COLUMN_NAME_BID_STATUS, bid.getBidStatus());

        mDatabase.update(AuctionContract.Bid.TABLE_NAME, values, AuctionContract.Bid._ID + " = " + bid.getBidId(), null);
    }

}
