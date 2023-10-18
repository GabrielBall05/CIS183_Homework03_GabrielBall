//===================================================
//Name: Gabriel Ball
//Date: 10-18-23
//Desc: Full CRUD program for employees in a database
//===================================================
package com.example.hw03_program01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    Button btn_j_add;
    ListView lv_j_employees;


    ArrayList<Employee> listOfEmployees;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Connect to GUI
        btn_j_add = findViewById(R.id.btn_v_add);
        lv_j_employees = findViewById(R.id.lv_v_employees);

        //Make arraylist
        listOfEmployees = new ArrayList<Employee>();


        //Functions
        addEmployeeButtonCLick();
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
            }
        });
    }
}