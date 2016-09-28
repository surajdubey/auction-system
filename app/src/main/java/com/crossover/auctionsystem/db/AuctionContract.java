package com.crossover.auctionsystem.db;

import android.provider.BaseColumns;

/**
 * Created by suraj on 23/9/16.
 */

class AuctionContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private AuctionContract() {

    }

    static class User implements BaseColumns {
        static final String TABLE_NAME = "users";
        static final String COLUMN_NAME_DISPLAY_NAME = "display_name";
        static final String COLUMN_NAME_USERNAME = "username";
        static final String COLUMN_NAME_PASSWORD = "password";
        static final String COLUMN_NAME_CREATED_TIME = "created_time";
    }

    static class Item implements BaseColumns {
        static final String TABLE_NAME = "items";
        static final String COLUMN_NAME_ITEM_NAME = "item_name";
        static final String COLUMN_NAME_DESCRIPTION = "description";
        static final String COLUMN_NAME_MINIMUM_BID_AMOUNT = "minimum_bid_amount";
        static final String COLUMN_NAME_TARGET_BID_AMOUNT = "target_bid_amount";
        static final String COLUMN_NAME_IS_ITEM_SOLD = "is_item_sold";
    }

    static class Bid implements BaseColumns {
        static final String TABLE_NAME = "bids";
        static final String COLUMN_NAME_USER_ID = "user_id";
        static final String COLUMN_NAME_ITEM_ID = "item_id";
        static final String COLUMN_NAME_BID_AMOUNT = "bid_amount";
        static final String COLUMN_NAME_BID_TIME = "bid_time";
        static final String COLUMN_NAME_BID_STATUS = "bid_status";
    }

    static class Seller implements BaseColumns {
        static final String TABLE_NAME = "sellers";
        static final String COLUMN_NAME_USER_ID = "user_id";
        static final String COLUMN_NAME_ITEM_ID = "item_id";
    }
}
