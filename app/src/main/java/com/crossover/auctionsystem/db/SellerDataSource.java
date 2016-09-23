package com.crossover.auctionsystem.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.crossover.auctionsystem.model.Seller;
import com.crossover.auctionsystem.model.User;

/**
 * Created by suraj on 23/9/16.
 */

public class SellerDataSource {
    private DatabaseHelper mDatabaseHelper;
    private SQLiteDatabase mDatabase;

    public SellerDataSource(Context context) {
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

    public void addSeller(Seller seller) {
        ContentValues values = new ContentValues();
        values.put(AuctionContract.Seller.COLUMN_NAME_USER_ID, seller.getUserId());
        values.put(AuctionContract.Seller.COLUMN_NAME_ITEM_ID, seller.getItemId());

        mDatabase.insert(AuctionContract.Seller.TABLE_NAME, null, values);
    }

    public int getSellerUserIdForItem(int itemId) {
        String findQuery = "SELECT " + AuctionContract.Seller.COLUMN_NAME_USER_ID + " WHERE " +
                AuctionContract.Seller.COLUMN_NAME_ITEM_ID + " = " + itemId;

        Cursor cursor = mDatabase.rawQuery(findQuery, null);

        int userId = User.INVALID_USER_ID;

        if(cursor.moveToFirst()) {
            int userIdIndex = cursor.getColumnIndex(AuctionContract.Seller.COLUMN_NAME_USER_ID);
            userId = cursor.getInt(cursor.getInt(userIdIndex));
        }

        cursor.close();

        return userId;
    }

}
