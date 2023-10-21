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

public class Update extends AppCompatActivity
{
    TextView tv_j_u_emptyError;
    TextView tv_j_u_dne;
    TextView tv_j_u_fname;
    TextView tv_j_u_lname;
    TextView tv_j_u_password;
    TextView tv_j_u_email;
    TextView tv_j_u_age;
    EditText et_j_u_uname;
    EditText et_j_u_fname;
    EditText et_j_u_lname;
    EditText et_j_u_password;
    EditText et_j_u_email;
    EditText et_j_u_age;
    Button btn_j_u_back;
    Button btn_j_u_update;
    Button btn_j_u_check;

    Intent mainActivity;
    ArrayList<Employee> referenceList;
    Employee emp;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        et_j_u_uname = findViewById(R.id.et_v_u_uname);
        et_j_u_fname = findViewById(R.id.et_v_u_fname);
        et_j_u_lname = findViewById(R.id.et_v_u_lname);
        et_j_u_password = findViewById(R.id.et_v_u_password);
        et_j_u_email = findViewById(R.id.et_v_u_email);
        et_j_u_age = findViewById(R.id.et_v_u_age);
        btn_j_u_update = findViewById(R.id.btn_v_u_update);
        btn_j_u_back = findViewById(R.id.btn_v_u_back);
        btn_j_u_check = findViewById(R.id.btn_v_u_check);
        tv_j_u_emptyError = findViewById(R.id.tv_v_u_emptyError);
        tv_j_u_dne = findViewById(R.id.tv_v_u_dne);
        tv_j_u_fname = findViewById(R.id.tv_v_u_fname);
        tv_j_u_lname = findViewById(R.id.tv_v_u_lname);
        tv_j_u_password = findViewById(R.id.tv_v_u_password);
        tv_j_u_email = findViewById(R.id.tv_v_u_email);
        tv_j_u_age = findViewById(R.id.tv_v_u_age);

        dbHelper = new DatabaseHelper(this);


        //All text boxes except username are set to invisible by default
        //I will have the user key in the username of the employee they wish to update
        //User will then click a button to check to make sure that employee exists
        //It will then show the other text boxes with the employee's information already filled in


        //Intent stuff
        mainActivity = new Intent(Update.this, MainActivity.class);
        Intent cameFrom = getIntent();
        referenceList = (ArrayList<Employee>) cameFrom.getSerializableExtra("EmployeeList");

        //


        updateButtonEvent();
        checkButtonEvent();
        backButtonEvent();
    }

    public void updateButtonEvent()
    {
        btn_j_u_update.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Log.d("Button Pressed: ", "Update Button Pressed (From Update Intent)");
                String u = et_j_u_uname.getText().toString();
                String f = et_j_u_fname.getText().toString();
                String l = et_j_u_lname.getText().toString();
                String p = et_j_u_password.getText().toString();
                String e = et_j_u_email.getText().toString();
                String a = et_j_u_age.getText().toString();

                if (u.equals("") || f.equals("") || l.equals("") || p.equals("") || e.equals("") || a.equals(""))
                {
                    //Set visibility of empty error to true
                    tv_j_u_emptyError.setVisibility(View.VISIBLE);
                }
                else //All text boxes have been filled out
                {
                    Employee employeeToUpdate = new Employee(u, f, l, p, e, a);
                    dbHelper.updateEmployee(employeeToUpdate);

                    //When I get back to MainActivity, the listView and ArrayList will be updated because
                    //in the onCreate function of MainActivity, it calls dbHelper in there to fill it in for me
                    startActivity(mainActivity);
                }
            }
        });
    }

    public void checkButtonEvent()
    {
        btn_j_u_check.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Log.d("Button Pressed: ", "Check Button Pressed (From Update Intent)");
                boolean matchFound = false;

                String u = et_j_u_uname.getText().toString();
                //If there IS something in the username text box
                if (!u.equals(""))
                {
                    //Cycle through the arraylist
                    for (int i = 0; i < referenceList.size(); i++)
                    {
                        //Check to see if i find a match
                        if (u.equals(referenceList.get(i).getUname()))
                        {
                            matchFound = true;
                            //Set emp to that employee
                            emp = referenceList.get(i);
                        }
                    }
                }

                if (matchFound)
                {
                    //Set visibility of other text boxes
                    et_j_u_fname.setVisibility(View.VISIBLE);
                    et_j_u_lname.setVisibility(View.VISIBLE);
                    et_j_u_password.setVisibility(View.VISIBLE);
                    et_j_u_email.setVisibility(View.VISIBLE);
                    et_j_u_age.setVisibility(View.VISIBLE);
                    btn_j_u_update.setVisibility(View.VISIBLE);
                    tv_j_u_fname.setVisibility(View.VISIBLE);
                    tv_j_u_lname.setVisibility(View.VISIBLE);
                    tv_j_u_password.setVisibility(View.VISIBLE);
                    tv_j_u_email.setVisibility(View.VISIBLE);
                    tv_j_u_age.setVisibility(View.VISIBLE);

                    tv_j_u_dne.setVisibility(View.INVISIBLE);

                    //Don't let user change the username anymore. If they wanted to, they'd have to go back to main and hit update again
                    btn_j_u_check.setEnabled(false);
                    et_j_u_uname.setEnabled(false);

                    //Fill in the information of the employee matching that username
                    et_j_u_fname.setText(emp.getFname());
                    et_j_u_lname.setText(emp.getLname());
                    et_j_u_password.setText(emp.getPassword());
                    et_j_u_email.setText(emp.getEmail());
                    et_j_u_age.setText(emp.getAge());
                }
                else //Employee does not exist
                {
                    //Set dne error visibility to true
                    tv_j_u_dne.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public void backButtonEvent()
    {
        btn_j_u_back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Log.d("Button Pressed: ", "Back Button Pressed (From Update Intent)");
                startActivity(mainActivity);
            }
        });
    }
}