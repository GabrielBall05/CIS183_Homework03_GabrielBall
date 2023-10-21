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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    //GUI stuff
    Button btn_j_add;
    Button btn_j_update;
    ListView lv_j_employees;

    //Database stuff
    DatabaseHelper dbHelper;

    //Array stuff
    ArrayList<Employee> listOfEmployees;

    //Intent stuff
    Intent addIntent;
    Intent deleteIntent;
    Intent detailsIntent;
    Intent updateIntent;

    //Adapter stuff
    EmployeeListAdapter adapter;

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
        deleteIntent = new Intent(MainActivity.this, Delete.class);
        detailsIntent = new Intent(MainActivity.this, Details.class);
        updateIntent = new Intent(MainActivity.this, Update.class);
        Intent cameFrom = getIntent();

        //For testing
        Log.d("ArrayList size: ", listOfEmployees.size() + "");
        logEmployees();

        //Functions
        addEmployeeButtonCLick();
        deleteEmployeeEvent();
        updateEmployeeEvent();
        detailedViewEvent();
        fillListView();
    }

    public void addEmployeeButtonCLick()
    {
        btn_j_add.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Log.d("Button Pressed: ", "Add Employee Button Click (From MA Intent)");
                //Take to add employee intent
                addIntent.putExtra("EmployeeList", listOfEmployees);

                startActivity(addIntent);
            }
        });
    }

    public void updateEmployeeEvent()
    {
        btn_j_update.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Log.d("Button Pressed: ", "Update Employee Button Click (From MA Intent)");
                //Take to update employee intent
                updateIntent.putExtra("EmployeeList", listOfEmployees);
                startActivity(updateIntent);
            }
        });
    }

    public void deleteEmployeeEvent()
    {
        lv_j_employees.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                Log.d("ListView Item Long Click (From MA Intent): ", "Will delete employee #" + i + " in ListView");

                deleteIntent.putExtra("Employee", listOfEmployees.get(i));
                startActivity(deleteIntent);

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
                Log.d("ListView Item Selected (From MA Intent): ", "Clicked on employee #" + i + " in ListView");
                //Take to detailed view intent
                detailsIntent.putExtra("Employee", listOfEmployees.get(i));
                startActivity(detailsIntent);
            }
        });
    }

    public void fillListView()
    {
        adapter = new EmployeeListAdapter(this, listOfEmployees);
        lv_j_employees.setAdapter(adapter);
    }

    public void logEmployees()
    {
        for (int i = 0; i < listOfEmployees.size(); i++)
        {
            Log.d("Employee #" + i, listOfEmployees.get(i).getUname() + ": " + listOfEmployees.get(i).getLname() + ", " + listOfEmployees.get(i).getFname());
        }
    }
}