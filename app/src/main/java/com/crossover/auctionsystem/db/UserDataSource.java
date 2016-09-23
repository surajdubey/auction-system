package com.crossover.auctionsystem.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.conferencemanager.android.model.User;
import com.conferencemanager.android.utils.L;

import java.util.ArrayList;

public class UserDataSource {

    public static final int INVALID_USER_ID = -1;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase db;

    public UserDataSource(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        db = databaseHelper.getWritableDatabase();
    }

    public void close() {
        if (db != null) {
            db.close();
        }
    }

    //insert user record
    public long addUser(User user) {
        ContentValues values = new ContentValues();
        values.put(ConferenceManagerContract.User.COLUMN_NAME_USER_DISPLAY_NAME, user.getName());
        values.put(ConferenceManagerContract.User.COLUMN_NAME_USERNAME, user.getUsername());
        values.put(ConferenceManagerContract.User.COLUMN_NAME_USER_PASSWORD, user.getPassword());
        values.put(ConferenceManagerContract.User.COLUMN_NAME_USER_CREATED_AT, user.getCreatedAt());
        long userId = db.insert(ConferenceManagerContract.User.TABLE_NAME, null, values);
        return userId;
    }

    public int userExist(User user) {
        String findQuery = "SELECT * FROM " + ConferenceManagerContract.User.TABLE_NAME + " WHERE " +
                ConferenceManagerContract.User.COLUMN_NAME_USERNAME + " = '" + user.getUsername() +
                "' AND " + ConferenceManagerContract.User.COLUMN_NAME_USER_TYPE + " = " + user.getUserType();
        Cursor cursor = db.rawQuery(findQuery, null);

        /**
         * -1 represents INVALID USER ID
         */
        int userId = INVALID_USER_ID;

        if (cursor.moveToFirst()) {
            userId = cursor.getInt(0);
        }

        //close cursor and db
        cursor.close();
        return userId;

    }

    public int getUserIdIfUserExists(User user) {
        String findQuery = "SELECT * FROM " + ConferenceManagerContract.User.TABLE_NAME + " WHERE " +
                ConferenceManagerContract.User.COLUMN_NAME_USERNAME + " = '" + user.getUsername() +
                "' AND " + ConferenceManagerContract.User.COLUMN_NAME_USER_PASSWORD + " = '" + user.getPassword() +
                "' AND " + ConferenceManagerContract.User.COLUMN_NAME_USER_TYPE + " = " + user.getUserType();
        Cursor cursor = db.rawQuery(findQuery, null);
        int userId = INVALID_USER_ID;

        if (cursor.moveToFirst()) {
            userId = cursor.getInt(0);
        }

        cursor.close();
        return userId;

    }

    public String getUserDisplayName(int userId) {
        String displayName = "";
        String findQuery = "SELECT * FROM " + ConferenceManagerContract.User.TABLE_NAME + " WHERE " +
                ConferenceManagerContract.User._ID + " = " + userId;
        Cursor cursor = db.rawQuery(findQuery, null);
        if (cursor.moveToFirst()) {
            displayName = cursor.getString(cursor.getColumnIndex(ConferenceManagerContract.User.COLUMN_NAME_USER_DISPLAY_NAME));
        }
        cursor.close();
        return displayName;
    }

    public ArrayList<Integer> getTopicReadByUser(int userId) {
        ArrayList<Integer> topicReadByUser = new ArrayList<>();
        String findQuery = "SELECT " + ConferenceManagerContract.TopicReadByUser.COLUMN_NAME_TOPIC_ID + " FROM " +
                ConferenceManagerContract.TopicReadByUser.TABLE_NAME + " WHERE USER_ID = " + userId;
        Cursor cursor = db.rawQuery(findQuery, null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                int topicId = cursor.getInt(cursor.getColumnIndex(ConferenceManagerContract.TopicReadByUser.COLUMN_NAME_TOPIC_ID));
                topicReadByUser.add(topicId);
                cursor.moveToNext();
            }
        }

        cursor.close();
        return topicReadByUser;
    }


    public ArrayList<Integer> getAllDoctorsId() {
        ArrayList<Integer> doctorIds = new ArrayList<>();
        String findQuery = "SELECT " + ConferenceManagerContract.User._ID + " FROM " +
                ConferenceManagerContract.User.TABLE_NAME + " WHERE "+ ConferenceManagerContract.User.COLUMN_NAME_USER_TYPE + " = " +
                User.USER_TYPE_DOCTOR;
        Cursor cursor = db.rawQuery(findQuery, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                L.d(cursor.getInt(0) + " Doctor ID ");

                doctorIds.add(cursor.getInt(0));
                cursor.moveToNext();
            }
        }

        cursor.close();
        return doctorIds;
    }
}
