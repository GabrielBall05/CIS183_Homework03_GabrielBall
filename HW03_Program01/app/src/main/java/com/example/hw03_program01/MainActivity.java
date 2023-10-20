//===================================================
//Name: Gabriel Ball
//Date: 10-18-23
//Desc: Full CRUD program for employees in a database
//===================================================
package com.example.hw03_program01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    Button btn_j_add;
    Button btn_j_update;
    ListView lv_j_employees;

    DatabaseHelper dbHelper;

    ArrayList<Employee> listOfEmployees;

    //Intent stuff
    Intent addIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Connect to GUI
        btn_j_add = findViewById(R.id.btn_v_add);
        btn_j_update = findViewById(R.id.btn_v_update);
        lv_j_employees = findViewById(R.id.lv_v_employees);

        //Database Helper stuff
        dbHelper = new DatabaseHelper(this);

        //Make arraylist
        listOfEmployees = new ArrayList<Employee>();
        //Fill data from database into arraylist
        listOfEmployees = dbHelper.getAllRows();

        //Intent stuff
        addIntent = new Intent(MainActivity.this, Add.class);


        //Functions
        addEmployeeButtonCLick();
        deleteUserEvent();
        updateUserEvent();
        detailedViewEvent();
        logEmployees();
    }

    public void addEmployeeButtonCLick()
    {
        btn_j_add.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //Log.d("Button Pressed: ", "Add Employee Button Click");
                //Take to add employee intent
                addIntent.putExtra("EmployeeList", listOfEmployees);

                startActivity(addIntent);
            }
        });
    }

    public void updateUserEvent()
    {
        btn_j_update.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Log.d("Button Pressed: ", "Update Employee Button Click");
                //Take to update employee intent
            }
        });
    }

    public void deleteUserEvent()
    {
        lv_j_employees.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                Log.d("ListView Item Long Click: ", "Will delete employee #" + i + " in ListView");
                //Deletes employee

                return false;
            }
        });
    }

    public void detailedViewEvent()
    {
        lv_j_employees.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                Log.d("ListView Item Selected: ", "Clicked on employee #" + i + " in ListView");
                //Take to detailed view intent
            }
        });
    }

    public void logEmployees()
    {
        for (int i = 0; i < listOfEmployees.size(); i++)
        {
            Log.d("Employee #" + i, listOfEmployees.get(i).getUname() + ": " + listOfEmployees.get(i).getLname() + ", " + listOfEmployees.get(i).getFname());
        }
    }
}