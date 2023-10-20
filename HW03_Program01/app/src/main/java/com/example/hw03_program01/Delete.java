package com.example.hw03_program01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Delete extends AppCompatActivity
{
    TextView tv_j_d_areYouSure;
    Button btn_j_d_yes;
    Button btn_j_d_no;
    Employee emp;

    Intent mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        tv_j_d_areYouSure = findViewById(R.id.tv_v_d_areYouSure);
        btn_j_d_yes = findViewById(R.id.btn_v_d_yes);
        btn_j_d_no = findViewById(R.id.btn_v_d_no);

        //Intent stuff
        mainActivity = new Intent(Delete.this, MainActivity.class);
        Intent cameFrom = getIntent();
        emp = (Employee) cameFrom.getSerializableExtra("Employee");

        //Set Are You Sure? text to be more specific
        tv_j_d_areYouSure.setText("Are you sure you want to delete the Employee: '" + emp.getFname() + " " + emp.getLname() + "' " + "with the username: " + emp.getUname() + "?");

        yesButtonClickEvent();
        noButtonClickEvent();
    }

    public void yesButtonClickEvent()
    {
        btn_j_d_yes.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Log.d("Button Pressed: ", "'Yes' Button Pressed (From Delete Intent)");

                mainActivity.putExtra("Answer", true);
                mainActivity.putExtra("EmployeeToDelete", emp);

                startActivity(mainActivity);
            }
        });
    }

    public void noButtonClickEvent()
    {
        btn_j_d_no.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Log.d("Button Pressed: ", "'No' Button Pressed (From Delete Intent)");

                //mainActivity.putExtra("Answer", false);
                startActivity(mainActivity);
            }
        });
    }
}