package com.projectx.androidappdevelopment.ContentProviders;

import java.util.HashMap;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;

import android.database.Cursor;
import android.database.SQLException;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

import android.net.Uri;
import android.text.TextUtils;

import androidx.annotation.NonNull;

public class ContactsProvider extends ContentProvider {
    static final String AUTHORITY = "com.projectx.androidappdevelopment.provider";
    static final String URL = "content://" + AUTHORITY + "/contacts";
    static final Uri CONTENT_URI = Uri.parse(URL);

    static final String _ID = "_id";
    static final String NAME = "name";
    static final String PHONE = "phone";
    static final String EMAIL = "email";

    private static HashMap<String, String> CONTACTS_PROJECTION_MAP;

    static final int CONTACTS = 1;
    static final int CONTACT_ID = 2;

    static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, "contacts", CONTACTS);
        uriMatcher.addURI(AUTHORITY, "contacts/#", CONTACT_ID);
    }

    /**
     * Database specific constant declarations
     */

    private SQLiteDatabase db;
    static final String DATABASE_NAME = "ContactDatabase";
    static final String CONTACTS_TABLE_NAME = "contacts";
    static final int DATABASE_VERSION = 1;
    static final String CREATE_DB_TABLE =
            " CREATE TABLE " + CONTACTS_TABLE_NAME +
                    " (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " " + " name TEXT NOT NULL," +
                    " " + " phone TEXT NOT NULL," +
                    " " + " email TEXT NOT NULL);";

    /*
     Helper class that actually creates and manages
     the provider's underlying data repository.
     */

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_DB_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + CONTACTS_TABLE_NAME);
            onCreate(db);
        }
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        DatabaseHelper dbHelper = new DatabaseHelper(context);

        /**
         * Create a write able database which will trigger its
         * creation if it doesn't already exist.
         */

        db = dbHelper.getWritableDatabase();
        if (db != null)
            return true;
        else return false;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        /**
         * Add a new contact record
         */
        long rowID = db.insert(CONTACTS_TABLE_NAME, null, values);

        /**
         * If record is added successfully
         */
        if (rowID > 0) {
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
            getContext().getContentResolver().notifyChange(_uri, null);
            return _uri;
        }

        throw new SQLException("Failed to add a record into " + uri);
    }

    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(CONTACTS_TABLE_NAME);

        switch (uriMatcher.match(uri)) {
            case CONTACTS:
                qb.setProjectionMap(CONTACTS_PROJECTION_MAP);
                break;

            case CONTACT_ID:
                qb.appendWhere(_ID + "=" + uri.getPathSegments().get(1));
                break;

            default:
        }

        if (sortOrder == null || sortOrder.equals("")) {
            /**
             * By default sort on contact names
             */
            sortOrder = NAME;
        }

        Cursor c = qb.query(db, projection, selection,
                selectionArgs, null, null, sortOrder);
        /**
         * register to watch a content URI for changes
         */
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)) {
            case CONTACTS:
                count = db.delete(CONTACTS_TABLE_NAME, selection, selectionArgs);
                break;

            case CONTACT_ID:
                String id = uri.getPathSegments().get(1);
                count = db.delete(CONTACTS_TABLE_NAME, _ID + " = " + id +
                        (!TextUtils.isEmpty(selection) ? " AND(" + selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)) {
            case CONTACTS:
                count = db.update(CONTACTS_TABLE_NAME, values, selection, selectionArgs);
                break;

            case CONTACT_ID:
                count = db.update(CONTACTS_TABLE_NAME, values,
                        _ID + " = " + uri.getPathSegments().get(1) +
                                (!TextUtils.isEmpty(selection) ? " AND(" + selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            /**
             * Get all contact records
             */
            case CONTACTS:
                return "vnd.android.cursor.dir/vnd.com.projectx.androidappdevelopment.contacts";
            /**
             * Get a particular contact
             */
            case CONTACT_ID:
                return "vnd.android.cursor.item/com.projectx.androidappdevelopment.contacts";
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }
}