package com.example.videogametracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


import androidx.annotation.Nullable;




public class Database extends SQLiteOpenHelper {

    private Context context;

    public static final String DATABASE_NAME = "gamelist.db";

    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_Name = "List";

    public static final String COL_0 = "gameName";

    public static final String COL_1 = "systemName";

    public static final String COL_2 = "completed";

    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create_command = "Create TABLE " + TABLE_Name + "(" + COL_0 + " text, " + COL_1 + " text, " + COL_2 + " text);";
        sqLiteDatabase.execSQL(create_command);
    }

    public void insertData(ContentValues values) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_Name, null, values);
        db.close();

    }

    public Cursor getAlldata() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor C = db.rawQuery("select * from " + TABLE_Name, null);
        return C;
    }


    public void deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        if (db.delete(TABLE_Name, COL_0 + "=?", new String[]{id}) > 0) {
            Toast.makeText(context, "Deletion successful", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "No rows present with the id given", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}
