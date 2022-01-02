package com.example.homemadeproductssale;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.homemadeproductssale.Model.Product;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final int VERSION = 1;
    public static final String DB_NAME = "product.db";

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Product.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Product.tableName);
        onCreate(db);
    }

    @SuppressLint("Range")
    public List<Product> getAllProducts() {

        List<Product> products = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + Product.tableName;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Product product = new Product();
                product.setId(cursor.getInt(cursor.getColumnIndex(Product.ID)));
                product.setImage(cursor.getInt(cursor.getColumnIndex(Product.IMAGE)));
                product.setName(cursor.getString(cursor.getColumnIndex(Product.NAME)));
                product.setPrice((cursor.getInt(cursor.getColumnIndex(Product.PRICE))));


                products.add(product);
            } while (cursor.moveToNext());
        }

        db.close();
        return products;
    }

    public boolean insertProduct(String name, int price, int image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Product.NAME, name);
        values.put(Product.PRICE, price);
        values.put(Product.IMAGE, image);
        long id = db.insert(Product.tableName, null, values);

        if (id == -1) {
            return false;
        } else
            return true;
    }

    public void addProduct(String name, int price, int image){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Product.NAME, name);
        values.put(Product.PRICE, price);
        values.put(Product.IMAGE, image);

        db.insert(Product.tableName,null,values);
        Log.d("Check", name + ", price - "+price+" - . " + values);

    }

    public void swapCursor(Cursor newCursor) {
        String selectQuery = "SELECT * FROM " + Product.tableName;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor != null) {
            cursor.close();
        }

        cursor = newCursor;

        if (newCursor != null) {
            notifyAll();
        }
    }

    public void removeProduct(long id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(Product.tableName, Product.ID + "=" + id, null);
        swapCursor((Cursor) getAllProducts());
    }

    public void deleteProduct(){

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Product.tableName,null,null);
        db.execSQL("delete from "+ Product.tableName);
        db.close();

    }



}
