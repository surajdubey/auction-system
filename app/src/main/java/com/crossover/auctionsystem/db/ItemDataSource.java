package com.crossover.auctionsystem.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.crossover.auctionsystem.model.Item;

import java.util.ArrayList;

/**
 * Created by suraj on 23/9/16.
 */

public class ItemDataSource {
    private DatabaseHelper mDatabaseHelper;
    private SQLiteDatabase mDatabase;

    public ItemDataSource(Context context) {
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

    public void addItem(Item item) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(AuctionContract.Item.COLUMN_NAME_ITEM_NAME, item.getItemName());
        contentValues.put(AuctionContract.Item.COLUMN_NAME_DESCRIPTION, item.getItemDescription());
        contentValues.put(AuctionContract.Item.COLUMN_NAME_IS_ITEM_SOLD, Item.ITEM_NOT_SOLD);
        contentValues.put(AuctionContract.Item.COLUMN_NAME_MINIMUM_BID_AMOUNT, item.getMinimumBidAmount());
        contentValues.put(AuctionContract.Item.COLUMN_NAME_TARGET_BID_AMOUNT, item.getTargetBidAmount());

        mDatabase.insert(AuctionContract.Item.TABLE_NAME, null, contentValues);
    }

    public ArrayList<Item> fetchAllItems() {
        ArrayList<Item> items = new ArrayList<>();

        String findQuery = "SELECT * FROM " + AuctionContract.Item.TABLE_NAME + " WHERE " +
                AuctionContract.Item.COLUMN_NAME_IS_ITEM_SOLD + " = " + Item.ITEM_SOLD;

        Cursor cursor = mDatabase.rawQuery(findQuery, null);

        if(cursor.moveToFirst()) {
            Item item = new Item();

            int itemNameIndex = cursor.getColumnIndex(AuctionContract.Item.COLUMN_NAME_ITEM_NAME);
            item.setItemName(cursor.getString(itemNameIndex));

            int itemDescriptionIndex = cursor.getColumnIndex(AuctionContract.Item.COLUMN_NAME_DESCRIPTION);
            item.setItemDescription(cursor.getString(itemDescriptionIndex));

            int itemSoldIndex = cursor.getColumnIndex(AuctionContract.Item.COLUMN_NAME_IS_ITEM_SOLD);
            item.setItemSold(cursor.getInt(itemSoldIndex));

            int itemMinimumBidAmountIndex = cursor.getColumnIndex(AuctionContract.Item.COLUMN_NAME_MINIMUM_BID_AMOUNT);
            item.setMinimumBidAmount(cursor.getInt(itemMinimumBidAmountIndex));

            int targetBidAmountIndex = cursor.getColumnIndex(AuctionContract.Item.COLUMN_NAME_TARGET_BID_AMOUNT);
            item.setTargetBidAmount(cursor.getInt(targetBidAmountIndex));

            /**
             * query for seller here
             */

            items.add(item);
        }

        cursor.close();
        return items;
    }
}
