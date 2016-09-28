package com.crossover.auctionsystem.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.crossover.auctionsystem.model.User;

public class UserDataSource {

    public static final int INVALID_USER_ID = -1;
    private DatabaseHelper mDatabaseHelper;
    private SQLiteDatabase mDatabase;

    public UserDataSource(Context context) {
        mDatabaseHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        mDatabase = mDatabaseHelper.getWritableDatabase();
    }

    public void close() {
        if (mDatabase != null) {
            mDatabase.close();
        }
    }

    //insert user record
    public long addUser(User user) {
        ContentValues values = new ContentValues();
        values.put(AuctionContract.User.COLUMN_NAME_DISPLAY_NAME, user.getName());
        values.put(AuctionContract.User.COLUMN_NAME_USERNAME, user.getUsername());
        values.put(AuctionContract.User.COLUMN_NAME_PASSWORD, user.getPassword());
        values.put(AuctionContract.User.COLUMN_NAME_CREATED_TIME, user.getCreatedAt());
        long userId = mDatabase.insert(AuctionContract.User.TABLE_NAME, null, values);
        return userId;
    }

    public int userLogin(User user) {
        String findQuery = "SELECT * FROM " + AuctionContract.User.TABLE_NAME + " WHERE " +
                AuctionContract.User.COLUMN_NAME_USERNAME + " = '" + user.getUsername() +
                "' AND " + AuctionContract.User.COLUMN_NAME_PASSWORD + " = '" + user.getPassword() + "'";

        Cursor cursor = mDatabase.rawQuery(findQuery, null);

        /**
         * -1 represents INVALID USER ID
         */
        int userId = INVALID_USER_ID;

        if (cursor.moveToFirst()) {
            int userIdIndex = cursor.getColumnIndex(AuctionContract.User._ID);
            userId = cursor.getInt(userIdIndex);
        }

        //close cursor and mDatabase
        cursor.close();
        return userId;

    }

    public int getUserIdIfUserExists(User user) {
        String findQuery = "SELECT * FROM " + AuctionContract.User.TABLE_NAME + " WHERE " +
                AuctionContract.User.COLUMN_NAME_USERNAME + " = '" + user.getUsername() + "'";
        Cursor cursor = mDatabase.rawQuery(findQuery, null);

        /**
         * -1 represents INVALID USER ID
         */
        int userId = INVALID_USER_ID;

        if (cursor.moveToFirst()) {
            int userIdIndex = cursor.getColumnIndex(AuctionContract.User._ID);
            userId = cursor.getInt(userIdIndex);
        }

        //close cursor and mDatabase
        cursor.close();
        return userId;

    }


    public String getUserDisplayName(int userId) {
        String displayName = "";
        String findQuery = "SELECT * FROM " + AuctionContract.User.TABLE_NAME + " WHERE " +
                AuctionContract.User._ID + " = " + userId;
        Cursor cursor = mDatabase.rawQuery(findQuery, null);

        if (cursor.moveToFirst()) {
            int displayNameIndex = cursor.getColumnIndex(AuctionContract.User.COLUMN_NAME_DISPLAY_NAME);
            displayName = cursor.getString(displayNameIndex);
        }

        cursor.close();
        return displayName;
    }

    public int getRandomUserId() {
        String findQuery = "SELECT " + AuctionContract.User._ID + " FROM " +
                AuctionContract.User.TABLE_NAME + " ORDER BY RANDOM() LIMIT 1";

        Cursor cursor = mDatabase.rawQuery(findQuery, null);

        int userId = User.INVALID_USER_ID;
        if (cursor.moveToFirst()) {
            int userIdIndex = cursor.getColumnIndex(AuctionContract.User._ID);
            userId = cursor.getInt(userIdIndex);
        }

        cursor.close();
        return userId;

    }
}
