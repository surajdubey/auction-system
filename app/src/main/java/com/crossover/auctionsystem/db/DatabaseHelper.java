package com.crossover.auctionsystem.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MedicalConferenceManager.db";

    private static final String SQL_CREATE_USERS_TABLE = "CREATE TABLE " +
            AuctionContract.User.TABLE_NAME + "(" +
            AuctionContract.User._ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
            AuctionContract.User.COLUMN_NAME_DISPLAY_NAME + " TEXT, " +
            AuctionContract.User.COLUMN_NAME_USERNAME + " TEXT, " +
            AuctionContract.User.COLUMN_NAME_PASSWORD + " TEXT, " +
            AuctionContract.User.COLUMN_NAME_CREATED_TIME + " TEXT )";


    private static final String SQL_CREATE_ITEMS_TABLE = "CREATE TABLE " +
            AuctionContract.Item.TABLE_NAME + "(" +
            AuctionContract.Item._ID + " INTEGER PRIMARY AUTOINCREMENT , " +
            AuctionContract.Item.COLUMN_NAME_ITEM_NAME + " TEXT, " +
            AuctionContract.Item.COLUMN_NAME_DESCRIPTION + " TEXT , " +
            AuctionContract.Item.COLUMN_NAME_MINIMUM_BID_AMOUNT + " INTEGER , " +
            AuctionContract.Item.COLUMN_NAME_TARGET_BID_AMOUNT + " INTEGER )";

    private static final String SQL_CREATE_BIDS_TABLE = "CREATE TABLE " +
            AuctionContract.Bid.TABLE_NAME + "(" +
            AuctionContract.Bid._ID + " INTEGER PRIMARY AUTOINCREMENT, " +
            AuctionContract.Bid.COLUMN_NAME_USER_ID + " INTEGER, " +
            AuctionContract.Bid.COLUMN_NAME_ITEM_ID + " INTEGER, "+
            AuctionContract.Bid.COLUMN_NAME_BID_AMOUNT + " INTEGER, " +
            AuctionContract.Bid.COLUMN_NAME_BID_STATUS + " TEXT " +
            AuctionContract.Bid.COLUMN_NAME_BID_TIME + " TEXT )";

    private static final String SQL_CREATE_SELLERS_TABLE = "CREATE TABLE " +
            AuctionContract.Seller.TABLE_NAME + "(" +
            AuctionContract.Seller._ID + " INTEGER PRIMARY AUTOINCREMENT , " +
            AuctionContract.Seller.COLUMN_NAME_USER_ID + " INTEGER , " +
            AuctionContract.Seller.COLUMN_NAME_ITEM_ID + " INTEGER )";

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_USERS_TABLE);
        db.execSQL(SQL_CREATE_ITEMS_TABLE);
        db.execSQL(SQL_CREATE_BIDS_TABLE);
        db.execSQL(SQL_CREATE_SELLERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS " + AuctionContract.User.TABLE_NAME);
//        onCreate(db);
    }
}
