package com.example.homemadeproductssale.DatabaseHelpers;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.homemadeproductssale.Model.Product;

public class DBHelperComment extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Comments.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "comment";
    static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "comment";

    public DBHelperComment(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT );";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addComment(String comment){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE, comment);

        long result = db.insert(TABLE_NAME, null, cv);

        return result !=-1;
    }

    public Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = null;
        if(db != null)
        {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public boolean deleteRow(long rowId){
        SQLiteDatabase db = getWritableDatabase();
        String where = COLUMN_ID + "=" + rowId;
        return db.delete(TABLE_NAME, where, null) != 0;
    }

    public void deleteAll(){
        Cursor cursor = readAllData();
        long rowId = cursor.getColumnIndexOrThrow(COLUMN_ID);
        if(cursor.moveToFirst()){
            do{
                deleteRow(cursor.getLong((int) rowId));
            }while (cursor.moveToNext());
        }
        cursor.close();
    }


}
