package com.example.hw03_program01;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class EmployeeListAdapter extends BaseAdapter
{
    Context context;
    ArrayList<Employee> listOfEmployees;

    public EmployeeListAdapter(Context c, ArrayList<Employee> ls)
    {
        context = c;
        listOfEmployees = ls;
    }

    @Override
    public int getCount()
    {
        return listOfEmployees.size();
    }

    @Override
    public Object getItem(int i)
    {
        return listOfEmployees.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        //Set custom_cell to be the view
        if (view == null)
        {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = mInflater.inflate(R.layout.custom_cell, null);
        }

        //Connect GUI
        TextView tv_j_cc_lastFirst = view.findViewById(R.id.tv_v_cc_lastFirst);
        TextView tv_j_cc_uname = view.findViewById(R.id.tv_v_cc_uname);

        //Reference to my employee for ease of use
        Employee emp = listOfEmployees.get(i);

        tv_j_cc_lastFirst.setText(emp.getLname() + ", " + emp.getFname());
        tv_j_cc_uname.setText(emp.getUname());


        return view;
    }
}
