package com.example.hw03_program01;

import java.io.Serializable;

public class Employee implements Serializable
{
    //==========ORDER: Username, First Name, Last Name, Password, Email, Age ===========
    private String uname;
    private String fname;
    private String lname;
    private String password;
    private String email;
    //Can always cast to an int when needed
    private String age;

    public Employee()
    {

    }

    public Employee(String u, String f, String l, String p, String e, String a)
    {
        //==========ORDER: Username, First Name, Last Name, Password, Email, Age ===========
        uname = u;
        fname = f;
        lname = l;
        password = p;
        email = e;
        age = a;
    }

    //=====Getters=====
    public String getUname()
    {
        return uname;
    }

    public String getFname()
    {
        return fname;
    }

    public String getLname()
    {
        return lname;
    }

    public String getPassword()
    {
        return password;
    }

    public String getEmail()
    {
        return email;
    }

    public String getAge()
    {
        return age;
    }
    //=====End Getters=====

    //=====Setters=====
    public void setUname(String u)
    {
        uname = u;
    }
    public void setFname(String f)
    {
        fname = f;
    }

    public void setLname(String l)
    {
        lname = l;
    }

    public void setPassword(String p)
    {
        password = p;
    }

    public void setEmail(String e)
    {
        email = e;
    }

    public void setAge(String a)
    {
        age = a;
    }
    //=====End Setters=====
}
