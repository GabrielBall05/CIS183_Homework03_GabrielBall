package com.example.hw03_program01;

import java.io.Serializable;

public class Employee implements Serializable
{
    private String fname;
    private String lname;
    private String uname;
    private String password;
    private String email;
    private int age;

    public Employee()
    {

    }

    public Employee(String f, String l, String u, String p, String e, int a)
    {
        fname = f;
        lname = l;
        uname = u;
        password = p;
        email = e;
        age = a;
    }

    //=====Getters=====
    public String getFname()
    {
        return fname;
    }

    public String getLname()
    {
        return lname;
    }

    public String getUname()
    {
        return uname;
    }

    public String getPassword()
    {
        return password;
    }

    public String getEmail()
    {
        return email;
    }

    public int getAge()
    {
        return age;
    }
    //=====End Getters=====

    //=====Setters=====
    public void setFname(String f)
    {
        fname = f;
    }

    public void setLname(String l)
    {
        lname = l;
    }

    public void setUname(String u)
    {
        uname = u;
    }

    public void setPassword(String p)
    {
        password = p;
    }

    public void setEmail(String e)
    {
        email = e;
    }

    public void setAge(int a)
    {
        age = a;
    }
    //=====End Setters=====
}
