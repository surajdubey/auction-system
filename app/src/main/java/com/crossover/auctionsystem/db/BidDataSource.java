package com.crossover.auctionsystem.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.crossover.auctionsystem.model.Bid;
import com.crossover.auctionsystem.model.User;

import java.util.ArrayList;

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
        if (mDatabase != null) {
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

    public int getWinnerUserId(int itemId) {
        String findQuery = "SELECT " + AuctionContract.Bid.COLUMN_NAME_USER_ID +
                " FROM " + AuctionContract.Bid.TABLE_NAME +
                " WHERE " + AuctionContract.Bid.COLUMN_NAME_ITEM_ID + " = " +
                itemId + " AND " + AuctionContract.Bid.COLUMN_NAME_BID_STATUS +
                " = " + Bid.BID_WINNER;

        int userId = User.INVALID_USER_ID;

        Cursor cursor = mDatabase.rawQuery(findQuery, null);

        if (cursor.moveToFirst()) {
            int userIdIndex = cursor.getColumnIndex(AuctionContract.Bid.COLUMN_NAME_USER_ID);
            userId = cursor.getInt(userIdIndex);
        }

        cursor.close();
        return userId;
    }

    public int getWinnerBidAmount(int itemId) {
        String findQuery = "SELECT " + AuctionContract.Bid.COLUMN_NAME_BID_AMOUNT +
                " FROM " + AuctionContract.Bid.TABLE_NAME +
                " WHERE " + AuctionContract.Bid.COLUMN_NAME_ITEM_ID + " = " +
                itemId + " AND " + AuctionContract.Bid.COLUMN_NAME_BID_STATUS +
                " = " + Bid.BID_WINNER;

        int bidAmount = 0;

        Cursor cursor = mDatabase.rawQuery(findQuery, null);

        if (cursor.moveToFirst()) {
            int bidAmountIndex = cursor.getColumnIndex(AuctionContract.Bid.COLUMN_NAME_BID_AMOUNT);
            bidAmount = cursor.getInt(bidAmountIndex);
        }

        cursor.close();
        return bidAmount;
    }

    public ArrayList<Bid> getAllBidsOnItem(int itemId) {
        String findQuery = "SELECT * FROM " + AuctionContract.Bid.TABLE_NAME +
                " WHERE " + AuctionContract.Bid.COLUMN_NAME_ITEM_ID + " = " + itemId;

        ArrayList<Bid> bids = new ArrayList<>();

        Cursor cursor = mDatabase.rawQuery(findQuery, null);

        if (cursor.moveToFirst()) {

            int bidIdIndex = cursor.getColumnIndex(AuctionContract.Bid._ID);
            int userIdIndex = cursor.getColumnIndex(AuctionContract.Bid.COLUMN_NAME_USER_ID);
            int bidAmountIndex = cursor.getColumnIndex(AuctionContract.Bid.COLUMN_NAME_BID_AMOUNT);
            int bidStatusIndex = cursor.getColumnIndex(AuctionContract.Bid.COLUMN_NAME_BID_STATUS);

            while (!cursor.isAfterLast()) {
                Bid bid = new Bid();
                bid.setBidId(cursor.getInt(bidIdIndex));
                bid.setUserId(cursor.getInt(userIdIndex));
                bid.setBidAmount(cursor.getInt(bidAmountIndex));
                bid.setBidStatus(cursor.getInt(bidStatusIndex));
                bid.setItemId(itemId);
                bids.add(bid);

                cursor.moveToNext();
            }
        }

        cursor.close();
        return bids;
    }

    public void updateBidStatus(Bid bid, int bidStatus) {
        ContentValues values = new ContentValues();

        values.put(AuctionContract.Bid.COLUMN_NAME_BID_STATUS, bidStatus);

        mDatabase.update(AuctionContract.Bid.TABLE_NAME, values, AuctionContract.Bid._ID + " = " + bid.getBidId(), null);
    }

    public ArrayList<Bid> getAllBidsByUser(int userId) {

        ArrayList<Bid> bids = new ArrayList<>();

        String findQuery = "SELECT * FROM " + AuctionContract.Bid.TABLE_NAME +
                " WHERE " + AuctionContract.Bid.COLUMN_NAME_USER_ID + " = " + userId;

        Cursor cursor = mDatabase.rawQuery(findQuery, null);

        if (cursor.moveToFirst()) {
            int bidIdIndex = cursor.getColumnIndex(AuctionContract.Bid._ID);
            int itemIdIndex = cursor.getColumnIndex(AuctionContract.Bid.COLUMN_NAME_ITEM_ID);
            int bidAmountIndex = cursor.getColumnIndex(AuctionContract.Bid.COLUMN_NAME_BID_AMOUNT);
            int bidStatusIndex = cursor.getColumnIndex(AuctionContract.Bid.COLUMN_NAME_BID_STATUS);

            while (!cursor.isAfterLast()) {
                Bid bid = new Bid();
                int bidId = cursor.getInt(bidIdIndex);
                int bidAmount = cursor.getInt(bidAmountIndex);
                int bidStatus = cursor.getInt(bidStatusIndex);
                int itemId = cursor.getInt(itemIdIndex);

                bid.setBidId(bidId);
                bid.setUserId(userId);
                bid.setItemId(itemId);
                bid.setBidAmount(bidAmount);
                bid.setBidStatus(bidStatus);
                bids.add(bid);

                cursor.moveToNext();
            }
        }

        cursor.close();
        return bids;
    }
}
