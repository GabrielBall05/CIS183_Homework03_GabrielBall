package com.example.hw03_program01;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "Employees.db";
    private static final String TABLE_NAME = "Employees";

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {

    }
}
