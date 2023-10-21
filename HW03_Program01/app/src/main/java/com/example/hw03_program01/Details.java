package com.example.hw03_program01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Details extends AppCompatActivity
{
    TextView tv_j_details_uname;
    TextView tv_j_details_fname;
    TextView tv_j_details_lname;
    TextView tv_j_details_password;
    TextView tv_j_details_email;
    TextView tv_j_details_age;
    Button btn_j_details_back;

    Intent mainActivity;
    Employee emp;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        tv_j_details_uname = findViewById(R.id.tv_v_details_uname);
        tv_j_details_fname = findViewById(R.id.tv_v_details_fname);
        tv_j_details_lname = findViewById(R.id.tv_v_details_lname);
        tv_j_details_password = findViewById(R.id.tv_v_details_password);
        tv_j_details_email = findViewById(R.id.tv_v_details_email);
        tv_j_details_age = findViewById(R.id.tv_v_details_age);
        btn_j_details_back = findViewById(R.id.btn_v_details_back);

        mainActivity = new Intent(Details.this, MainActivity.class);

        Intent cameFrom = getIntent();
        emp = (Employee) cameFrom.getSerializableExtra("Employee");

        fillTextBoxes();
        backButtonEvent();
    }

    public void fillTextBoxes()
    {
        tv_j_details_uname.setText("Username: " + emp.getUname());
        tv_j_details_fname.setText("First Name: " + emp.getFname());
        tv_j_details_lname.setText("Last Name: " + emp.getLname());
        tv_j_details_password.setText("Password: " + emp.getPassword());
        tv_j_details_email.setText("Email: " + emp.getEmail());
        tv_j_details_age.setText("Age: " + emp.getAge());
    }

    public void backButtonEvent()
    {
        btn_j_details_back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Log.d("Button Pressed: ", "Back Button Pressed (From Details Intent)");

                startActivity(mainActivity);
            }
        });
    }
}