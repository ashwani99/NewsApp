package com.newsful5.android.list;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "funkynewz.db";
    public static String TABLE_NAME;
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "link_add";
    private static final String KEY = "key_value";
    private SQLiteDatabase db;


    public MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public void onCreate(SQLiteDatabase db) {
        this.db = db;
    }

    public void createTable(String[] arr) {
        if (!tableExist(TABLE_NAME)) {
            String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                    + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                    + KEY + " INTEGER" + ")";
            db.execSQL(CREATE_CONTACTS_TABLE);
            for (int i = 0; i < arr.length; i++)
                addLink(new Product(i, arr[i], 0));
        }
    }

    public boolean tableExist(String tableName) {

        if (db == null || !db.isOpen()) {
            db = getReadableDatabase();
        }

        if (!db.isReadOnly()) {
            db.close();
            db = getReadableDatabase();
        }
        Cursor cursor = db.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '" + tableName + "'", null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.close();
                return true;
            }
            cursor.close();
        }
        return false;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addLink(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, product.getLinkName());
        values.put(KEY, product.getKey());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    //get link
    public int getLink(int id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{KEY_ID,
                        KEY_NAME, KEY}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        int value = cursor.getInt(2);
        cursor.close();
        return value;
    }

    //update one link
    public int updateLink(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, product.getLinkName());
        values.put(KEY, product.getKey());

        return db.update(TABLE_NAME, values, KEY_ID + " = ?",
                new String[]{String.valueOf(product.getID())});
    }

}
