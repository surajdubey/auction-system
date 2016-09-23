package com.crossover.auctionsystem.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MedicalConferenceManager.db";
    private static final String SQL_CREATE_USERS_TABLE = "CREATE TABLE " + ConferenceManagerContract.User.TABLE_NAME + "(" +
            ConferenceManagerContract.User._ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
            ConferenceManagerContract.User.COLUMN_NAME_USER_DISPLAY_NAME + " TEXT, " +
            ConferenceManagerContract.User.COLUMN_NAME_USERNAME + " TEXT, " +
            ConferenceManagerContract.User.COLUMN_NAME_USER_PASSWORD + " TEXT, " +
            ConferenceManagerContract.User.COLUMN_NAME_USER_CREATED_AT + " TEXT, " +
            ConferenceManagerContract.User.COLUMN_NAME_USER_TYPE + " INTEGER" + ")";

    private static final String SQL_CREATE_TOPIC_TABLE = "CREATE TABLE " +
            ConferenceManagerContract.Topic.TABLE_NAME + "(" +
            ConferenceManagerContract.Topic._ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
            ConferenceManagerContract.Topic.COLUMN_NAME_TOPIC_SUBJECT + " TEXT, " +
            ConferenceManagerContract.Topic.COLUMN_NAME_TOPIC_CONTENT + " TEXT, " +
            ConferenceManagerContract.Topic.COLUMN_NAME_TOPIC_CREATED_AT + " TEXT, " +
            ConferenceManagerContract.Topic.COLUMN_NAME_TOPIC_AUTHOR_ID + " TEXT" +
            ")";

    private static final String SQL_CREATE_CONFERENCE_TABLE = "CREATE TABLE " +
            ConferenceManagerContract.Conference.TABLE_NAME + "(" +
            ConferenceManagerContract.Conference._ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
            ConferenceManagerContract.Conference.COLUMN_NAME_CONFERENCE_AUTHOR_ID + " INTEGER , " +
            ConferenceManagerContract.Conference.COLUMN_NAME_CONFERENCE_SUBJECT + " TEXT , " +
            ConferenceManagerContract.Conference.COLUMN_NAME_CONFERENCE_CREATED_AT + " TEXT , " +
            ConferenceManagerContract.Conference.COLUMN_NAME_CONFERENCE_FROM_TIME + " TEXT , " +
            ConferenceManagerContract.Conference.CONFERENCE_TO_TIME + " TEXT" +
            ")";

    private static final String SQL_CREATE_INVITE_TABLE = "CREATE TABLE " +
            ConferenceManagerContract.Invite.TABLE_NAME + "(" +
            ConferenceManagerContract.Invite._ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
            ConferenceManagerContract.Invite.COLUMN_NAME_INVITE_CONFERENCE_ID + " INTEGER , " +
            ConferenceManagerContract.Invite.COLUMN_NAME_INVITE_FROM_USER_ID + " INTEGER , " +
            ConferenceManagerContract.Invite.COLUMN_NAME_INVITE_TO_USER_ID + " INTEGER , " +
            ConferenceManagerContract.Invite.COLUMN_NAME_INVITE_CREATED_AT + " INTEGER , " +
            ConferenceManagerContract.Invite.COLUMN_NAME_INVITE_STATUS + " TEXT" +
            ")";

    private static final String SQL_CREATE_TOPIC_READ_BY_USER_TABLE = "CREATE TABLE " +
            ConferenceManagerContract.TopicReadByUser.TABLE_NAME + "(" +
            ConferenceManagerContract.TopicReadByUser._ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
            ConferenceManagerContract.TopicReadByUser.COLUMN_NAME_TOPIC_ID + " INTEGER , " +
            ConferenceManagerContract.TopicReadByUser.COLUMN_NAME_USER_ID + " INTEGER" +
            ")";

    public DatabaseHelper(Context context) {
        //CursorFactory is set to null
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_USERS_TABLE);
        db.execSQL(SQL_CREATE_TOPIC_TABLE);
        db.execSQL(SQL_CREATE_TOPIC_READ_BY_USER_TABLE);
        db.execSQL(SQL_CREATE_CONFERENCE_TABLE);
        db.execSQL(SQL_CREATE_INVITE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ConferenceManagerContract.User.TABLE_NAME);
        onCreate(db);
    }
}
