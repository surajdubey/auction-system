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
        if (mDatabase != null) {
            mDatabase.close();
        }
    }

    public long addItem(Item item) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(AuctionContract.Item.COLUMN_NAME_ITEM_NAME, item.getItemName());
        contentValues.put(AuctionContract.Item.COLUMN_NAME_DESCRIPTION, item.getItemDescription());
        contentValues.put(AuctionContract.Item.COLUMN_NAME_IS_ITEM_SOLD, Item.ITEM_NOT_SOLD);
        contentValues.put(AuctionContract.Item.COLUMN_NAME_MINIMUM_BID_AMOUNT, item.getMinimumBidAmount());
        contentValues.put(AuctionContract.Item.COLUMN_NAME_TARGET_BID_AMOUNT, item.getTargetBidAmount());

        return mDatabase.insert(AuctionContract.Item.TABLE_NAME, null, contentValues);
    }

    public ArrayList<Item> fetchAllItems() {
        ArrayList<Item> items = new ArrayList<>();

        String findQuery = "SELECT * FROM " + AuctionContract.Item.TABLE_NAME + " WHERE " +
                AuctionContract.Item.COLUMN_NAME_IS_ITEM_SOLD + " = " + Item.ITEM_SOLD;

        Cursor cursor = mDatabase.rawQuery(findQuery, null);

        if (cursor.moveToFirst()) {

            int itemNameIndex = cursor.getColumnIndex(AuctionContract.Item.COLUMN_NAME_ITEM_NAME);
            int itemDescriptionIndex = cursor.getColumnIndex(AuctionContract.Item.COLUMN_NAME_DESCRIPTION);
            int itemSoldIndex = cursor.getColumnIndex(AuctionContract.Item.COLUMN_NAME_IS_ITEM_SOLD);
            int itemMinimumBidAmountIndex = cursor.getColumnIndex(AuctionContract.Item.COLUMN_NAME_MINIMUM_BID_AMOUNT);
            int targetBidAmountIndex = cursor.getColumnIndex(AuctionContract.Item.COLUMN_NAME_TARGET_BID_AMOUNT);

            while (cursor.moveToNext()) {
                Item item = new Item();

                item.setItemName(cursor.getString(itemNameIndex));

                item.setItemDescription(cursor.getString(itemDescriptionIndex));

                item.setItemSold(cursor.getInt(itemSoldIndex));

                item.setMinimumBidAmount(cursor.getInt(itemMinimumBidAmountIndex));

                item.setTargetBidAmount(cursor.getInt(targetBidAmountIndex));

                items.add(item);
            }
        }

        cursor.close();
        return items;
    }

    public Item getItemDetails(int itemId) {
        String findQuery = "SELECT * FROM " + AuctionContract.Item.TABLE_NAME + " WHERE " +
                AuctionContract.Item.COLUMN_NAME_IS_ITEM_SOLD + " = " + Item.ITEM_SOLD;

        Cursor cursor = mDatabase.rawQuery(findQuery, null);

        Item item = new Item();

        if (cursor.moveToFirst()) {

            int itemNameIndex = cursor.getColumnIndex(AuctionContract.Item.COLUMN_NAME_ITEM_NAME);
            int itemDescriptionIndex = cursor.getColumnIndex(AuctionContract.Item.COLUMN_NAME_DESCRIPTION);
            int itemSoldIndex = cursor.getColumnIndex(AuctionContract.Item.COLUMN_NAME_IS_ITEM_SOLD);
            int itemMinimumBidAmountIndex = cursor.getColumnIndex(AuctionContract.Item.COLUMN_NAME_MINIMUM_BID_AMOUNT);
            int targetBidAmountIndex = cursor.getColumnIndex(AuctionContract.Item.COLUMN_NAME_TARGET_BID_AMOUNT);

            item.setItemName(cursor.getString(itemNameIndex));

            item.setItemDescription(cursor.getString(itemDescriptionIndex));

            item.setItemSold(cursor.getInt(itemSoldIndex));

            item.setMinimumBidAmount(cursor.getInt(itemMinimumBidAmountIndex));

            item.setTargetBidAmount(cursor.getInt(targetBidAmountIndex));

            item.setItemId(itemId);

        }

        cursor.close();
        return item;
    }

    public void updateItemAsSold(int itemId) {
        ContentValues values = new ContentValues();
        values.put(AuctionContract.Item.COLUMN_NAME_IS_ITEM_SOLD, Item.ITEM_SOLD);
        mDatabase.update(AuctionContract.Item.TABLE_NAME, values, AuctionContract.Item._ID + " = " + itemId, null);
    }
}
