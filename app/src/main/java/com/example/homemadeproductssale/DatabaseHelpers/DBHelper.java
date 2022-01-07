package com.example.homemadeproductssale.DatabaseHelpers;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.homemadeproductssale.Model.Product;
import com.example.homemadeproductssale.R;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final int VERSION = 2;
    public static final String DB_NAME = "product.db";

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateMyDatabase(db, 0, VERSION);    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDatabase(db, oldVersion, newVersion);

    }

    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 1) {
            db.execSQL("CREATE TABLE PRODUCT (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAME TEXT, "
                    + "PRICE TEXT, "
                    + "IMAGE_RESOURCE_ID INTEGER);");

            addProduct(db, "Chia Aloe Soap", 50, R.drawable.chia_aloe_exfoliating_soap);
            addProduct(db, "Corneal Soap", 50,
                    R.drawable.cornmeal_exfoliating_soap);
            addProduct(db, "Glycerin Soap", 50, R.drawable.glycerin_holiday_soap);
        }
        if (oldVersion < 2) {
            db.execSQL("ALTER TABLE PRODUCT ADD COLUMN FAVORITE NUMERIC;");
        }
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

    private static void addProduct(SQLiteDatabase db, String name, int price, int resourceId) {
        ContentValues values = new ContentValues();
        values.put(Product.NAME, name);
        values.put(Product.PRICE, price);
        values.put(Product.IMAGE, resourceId);
        db.insert(Product.tableName, null, values);
    }

    public void deleteProduct(){

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Product.tableName,null,null);
        db.execSQL("delete from "+ Product.tableName);
        db.close();
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + Product.tableName;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

}
