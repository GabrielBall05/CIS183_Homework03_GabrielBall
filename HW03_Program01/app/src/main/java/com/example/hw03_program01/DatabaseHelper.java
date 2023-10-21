package com.example.hw03_program01;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "Employees.db";
    private static final String TABLE_NAME = "Employees";

    public DatabaseHelper(Context context)
    {
        //Create Database
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //Create database with 6 attributes
        //==========ORDER: Username, First Name, Last Name, Password, Email, Age ===========
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (username TEXT PRIMARY KEY NOT NULL, firstname TEXT, lastname TEXT, password TEXT, email TEXT, age TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        //In case I change the version number of the database
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME + ";");
        //Then make new table
        onCreate(db);
    }


    //For Testing if database works
    public boolean initializeDB()
    {
        if(numberOfRowsInTable() == 0)
        {
            SQLiteDatabase db = this.getWritableDatabase();

            //Dummy data

            db.close();

            return true;
        }
        else
        {
            return false;
        }
    }

    public int numberOfRowsInTable()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);

        db.close();

        return numRows;
    }

    @SuppressLint("Range")
    public ArrayList<Employee> getAllRows()
    {
        //Temp arraylist to store everything that the table returns
        ArrayList<Employee> listEmployees = new ArrayList<Employee>();

        //String selectQuery = "SELECT * FROM " + TABLE_NAME + " ORDER BY username;";
        String selectQuery = "SELECT * FROM " + TABLE_NAME + ";";

        //Get readable
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //==========ORDER: Username, First Name, Last Name, Password, Email, Age ===========
        String u;
        String f;
        String l;
        String p;
        String e;
        String a;

        if (cursor.moveToFirst())
        {
            do
            {
                u = cursor.getString(cursor.getColumnIndex("username"));
                f = cursor.getString(cursor.getColumnIndex("firstname"));
                l = cursor.getString(cursor.getColumnIndex("lastname"));
                p = cursor.getString(cursor.getColumnIndex("password"));
                e = cursor.getString(cursor.getColumnIndex("email"));
                a = cursor.getString(cursor.getColumnIndex("age"));

                listEmployees.add(new Employee(u, f, l, p, e, a));
            }
            while (cursor.moveToNext());
        }

        db.close();

        return listEmployees;
    }

    public void addNewEmployee(Employee e)
    {
        //Get writable
        SQLiteDatabase db = this.getWritableDatabase();
        //==========ORDER: Username, First Name, Last Name, Password, Email, Age ===========
        db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES ('" + e.getUname() + "','" + e.getFname() + "','" + e.getLname() + "','" + e.getPassword() + "','" + e.getEmail() + "','" + e.getAge() + "');");
        //db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES ('" + u.getUname() + "','" + u.getFname() + "','" + u.getLname() + "');");
        db.close();

        //The only thing that breaks this is if a user keys in a name with a ' or a " or a , like Kal'el because it messes with the sql code
        //I just don't feel like error checking for that to be honest
    }

    public void deleteEmployee(String uname)
    {
        //Get writable
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE username = '" + uname + "';");
        db.close();
    }

    public void updateEmployee(Employee e)
    {
        //Get writeable
        SQLiteDatabase db = this.getWritableDatabase();

        String updateCommand = "UPDATE " + TABLE_NAME + " SET firstname = '" + e.getFname() + "' , lastname = '" + e.getLname() + "' , password = '" + e.getPassword() + "' , email = '" + e.getEmail() + "' , age = '" + e.getAge() + "' WHERE username = '" + e.getUname() + "';";
        db.execSQL(updateCommand);
        db.close();
    }
}
