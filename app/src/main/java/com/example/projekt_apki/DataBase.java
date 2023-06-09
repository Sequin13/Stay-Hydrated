package com.example.projekt_apki;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBase {
    private static final String DATABASE_NAME = "my.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "pierwszaBaza";
    private static final String TABLE_NAME2="currentData";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "imie";
    private static final String COLUMN_AGE = "wiek";
    private static final String COLUMN_SURNAME = "nazwisko";
    private static final String COLUMN_SEX = "plec";
    private static final String COLUMN_GOAL = "cel";
    private static final String COLUMN_DRANK_WATER = "iww"; //iww - ilosc wypitej wody
    private static final String COLUMN_TIME = "czas";
    private static final String USER = "uzytkownik";
    private static final String PASSWORD = "haslo";

    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public DataBase(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void createTableOfCurrentlyData() {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME2 +" (" +
                COLUMN_ID +" INT, "+
                COLUMN_DRANK_WATER +" INT, " +
                COLUMN_TIME +" DATETIME)";
        database.execSQL(createTableQuery);
    }

    public boolean getAccess(String user, String password) {
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + USER + " = ? AND " + PASSWORD + " = ?";
        String[] selectionArgs = {user, password};
        Cursor cursor = database.rawQuery(query, selectionArgs);
        boolean hasAccess = cursor.getCount() > 0;
        cursor.close();
        return hasAccess;
    }


    public void createTableOfUsers() {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT(200), " +
                COLUMN_SURNAME + " TEXT(200), " +
                COLUMN_SEX + " TEXT(1), " +
                USER + " TEXT(200), " +
                PASSWORD + " TEXT(200), " +
                COLUMN_AGE + " INT, " +
                COLUMN_GOAL + " INT)";
        database.execSQL(createTableQuery);
    }

    public void insertData(String user, String haslo,String imie, String plec, int wiek, int cel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, imie);
        contentValues.put(COLUMN_SEX, plec);
        contentValues.put(COLUMN_AGE, wiek);
        contentValues.put(COLUMN_GOAL, cel);
        contentValues.put(USER, user);
        contentValues.put(PASSWORD,haslo);
        database.insert(TABLE_NAME, null, contentValues);
    }


    public int getUserGoal(int userId) {
        int userGoal = 0;
        String query = "SELECT " + COLUMN_GOAL + " FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(userId)};
        Cursor cursor = database.rawQuery(query, selectionArgs);
        if (cursor.moveToFirst()) {
            userGoal = cursor.getInt(cursor.getColumnIndex(COLUMN_GOAL));
        }
        cursor.close();
        return userGoal;
    }

    public int getUserId(String user) {
        int userId = 0;
        String query = "SELECT " + COLUMN_ID + " FROM " + TABLE_NAME + " WHERE " + USER + " = ?";
        String[] selectionArgs = {user};
        Cursor cursor = database.rawQuery(query, selectionArgs);
        if (cursor.moveToFirst()) {
            userId = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
        }
        cursor.close();
        return userId;
    }

    public Cursor getData() {
        return database.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    public void printData() {
        Cursor cursor = getData();
        while (cursor.moveToNext()) {
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
            @SuppressLint("Range") String imie = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
            @SuppressLint("Range") int wiek = cursor.getInt(cursor.getColumnIndex(COLUMN_AGE));
            Log.d("ID:", String.valueOf(id));
            Log.d("Imie:", imie);
            Log.d("Wiek:", String.valueOf(wiek));
        }
        cursor.close();
    }

    public void deleteDatabase(Context context) {
        context.deleteDatabase(DATABASE_NAME);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            String dropTableQuery = "DROP TABLE IF EXISTS " + TABLE_NAME;
            db.execSQL(dropTableQuery);
            onCreate(db);
        }
    }
}
