package com.example.rod.exampleit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rod on 6/3/16.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "fitnessTracker";
    private static final String TABLE_USERS = "users";

    private static final String COL_ID = "id";
    private static final String COL_NAME = "name";
    private static final String COL_AGE = "age";
    private static final String COL_HEIGHT = "height";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + COL_NAME + " TEXT NOT NULL UNIQUE,"
                + COL_AGE + " INTEGER,"
                + COL_HEIGHT + " REAL,"
                + ")";

        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public void addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_NAME, user.getName());
        values.put(COL_AGE, user.getAge());
        values.put(COL_HEIGHT, user.getHeight());

        db.insert(TABLE_USERS, null, values);
        db.close();
    }

    public User getUser(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABLE_USERS, new String[] {
                        COL_ID,
                        COL_NAME,
                        COL_AGE,
                        COL_HEIGHT
                }, COL_ID + "=?",
                new String[] {
                        String.valueOf(id)
                }, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        User user = new User(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getInt(2),
                cursor.getFloat(3)
        );

        return user;
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<User>();

        Cursor cursor = this.getWritableDatabase().rawQuery(
                "SELECT * FROM " + TABLE_USERS,
                null
        );

        if (cursor.moveToFirst()) {
            do {
                userList.add(
                        new User(
                            cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getInt(2),
                            cursor.getFloat(2)
                        )
                );
            } while (cursor.moveToNext());
        }

        // return user list
        return userList;
    }

    public int getUsersCount() {
        Cursor cursor = this.getReadableDatabase().rawQuery(
                "SELECT * FROM " + TABLE_USERS,
                null
        );

        cursor.close();

        return cursor.getCount();
    }

    public int updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_NAME, user.getName());
        values.put(COL_AGE, user.getAge());
        values.put(COL_HEIGHT, user.getHeight());

        return db.update(TABLE_USERS, values, COL_ID + " = ?",
                new String[] { String.valueOf(user.getId()) });
    }

    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERS, COL_ID + " = ?",
                new String[] { String.valueOf(user.getId()) });
        db.close();
    }
}
