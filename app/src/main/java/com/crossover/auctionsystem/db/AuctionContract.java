package com.crossover.auctionsystem.db;

import android.provider.BaseColumns;

/**
 * Created by suraj on 23/9/16.
 */

public class AuctionContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private AuctionContract() {

    }

    public static class User implements BaseColumns {
        public static final String TABLE_NAME = "users";
        public static final String COLUMN_NAME_DISPLAY_NAME = "display_name";
        public static final String COLUMN_NAME_USERNAME = "username";
        public static final String COLUMN_NAME_PASSWORD = "password";
    }

    public static class Item implements BaseColumns {
        public static final String TABLE_NAME = "items";
        public static final String COLUMN_NAME_ITEM_NAME = "item_name";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_MINIMUM_BID_AMOUNT = "minimum_bid_amount";
    }

    public static class Bid implements BaseColumns {
        public static final String TABLE_NAME = "bids";
        public static final String COLUMN_NAME_USER_ID = "user_id";
        public static final String COLUMN_NAME_ITEM_ID = "item_id";
        public static final String COLUMN_NAME_BID_AMOUNT = "bid_amount";
        public static final String COLUMN_NAME_BID_TIME = "bid_time";
        public static final String COLUMN_NAME_BID_STATUS = "bid_status";
    }

    public static class Seller implements BaseColumns {
        public static final String TABLE_NAME = "sellers";
        public static final String COLUMN_NAME_USER_ID = "user_id";
        public static final String COLUMN_NAME_ITEM_ID = "item_id";
        public static final String COLUMN_NAME_SELL_EXPIRATION_TIME = "sell_expiration_time";
    }
}
