package com.example.hw03_program01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class Add extends AppCompatActivity
{
    TextView tv_j_a_unameError;
    TextView tv_j_a_emptyError;
    EditText et_j_a_uname;
    EditText et_j_a_fname;
    EditText et_j_a_lname;
    EditText et_j_a_pass;
    EditText et_j_a_email;
    EditText et_j_a_age;
    Button btn_j_a_back;
    Button btn_j_a_add;

    DatabaseHelper dbHelper;

    ArrayList<Employee> referenceEmployeeList;

    //Intent stuff
    Intent mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        tv_j_a_unameError = findViewById(R.id.tv_v_a_unameError);
        tv_j_a_emptyError = findViewById(R.id.tv_v_a_emptyError);
        et_j_a_uname = findViewById(R.id.et_v_a_uname);
        et_j_a_fname = findViewById(R.id.et_v_a_fname);
        et_j_a_lname = findViewById(R.id.et_v_a_lname);
        et_j_a_pass = findViewById(R.id.et_v_a_pass);
        et_j_a_email = findViewById(R.id.et_v_a_email);
        et_j_a_age = findViewById(R.id.et_v_a_age);
        btn_j_a_back = findViewById(R.id.btn_v_a_back);
        btn_j_a_add = findViewById(R.id.btn_v_a_add);

        //Database Helper stuff
        dbHelper = new DatabaseHelper(this);

        //Intent stuff
        mainActivity = new Intent(Add.this, MainActivity.class);
        Intent cameFrom = getIntent();
        referenceEmployeeList = (ArrayList<Employee>) cameFrom.getSerializableExtra("EmployeeList");


        backButtonClickEvent();
        addButtonClickEvent();
    }

    public void addButtonClickEvent()
    {
        btn_j_a_add.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Log.d("Button Pressed: ", "Add Button Press (Add Intent)");
                //Strings to store the information from the text boxes
                String u = et_j_a_uname.getText().toString();
                String f = et_j_a_fname.getText().toString();
                String l = et_j_a_lname.getText().toString();
                String p = et_j_a_pass.getText().toString();
                String e = et_j_a_email.getText().toString();
                String a = et_j_a_age.getText().toString();


                //Make sure none of the text boxes are empty
                if (u == null || f == null || l == null || p == null || e == null || a == null)
                {
                    //If any text box is empty, show error
                    tv_j_a_emptyError.setVisibility(View.VISIBLE);
                }
                else //If all the text boxes are filled, get rid of error and start running code
                {
                    tv_j_a_emptyError.setVisibility(View.INVISIBLE);

                    //Make a boolean to check for unique usernames
                    boolean isUnique = true;
                    //Cycle through all employees in the employee list
                    for (int i = 0; i < referenceEmployeeList.size(); i++)
                    {
                        if(u == referenceEmployeeList.get(i).getUname())
                        {
                            //If the username text box equals any of the usernames in the list, it is no longer unique
                            isUnique = false;
                        }
                    }

                    //If the username is still unique, do all this
                    if (isUnique)
                    {
                        tv_j_a_unameError.setVisibility(View.INVISIBLE);

                        //Make new employee
                        Employee employeeToAdd = new Employee(u, f, l, p, e, a);
                        //Add to database
                        dbHelper.addNewEmployee(employeeToAdd);
                        //When we return to Main Activity, it will read all rows in database in onCreate and store this new employee

                        //Load Main Activity
                        startActivity(mainActivity);
                        //No need to clear text boxes since unless I decide to change it to not load main activity after adding one
                        //If I change it, it would allow you to add multiple employees before clicking "back" to go back to main intent
                    }
                    else
                    {
                        //Show "username not unique" error
                        tv_j_a_unameError.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }


    public void backButtonClickEvent()
    {
        btn_j_a_back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Log.d("Button Pressed: ", "Back Button Press (Add Intent)");
                startActivity(mainActivity);

            }
        });
    }
}