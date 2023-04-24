package com.user_sign_in;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class UserDetails
{
    String name;
    String email;
    String userName;
    String password;

    Scanner sc=new Scanner(System.in);

    public void display()
    {
        System.out.println();
        System.out.println("Welcome to the **JUKEBOX**");
        System.out.println();
        System.out.println("Please enter 1 if you are a New User.");
        System.out.println("Please enter 2 if you are an Existing User.");

        System.out.println();
        System.out.println("===================================================================");
        System.out.println();
    }

    public void newUser() throws SQLException
    {
        Statement st=ConnectionToDatabase.connection();

            System.out.println("Please enter your Name");
            name = sc.nextLine();

            System.out.println("Please enter your email");
            email = sc.nextLine();
        boolean bool=false;
        while(bool!=true)
        {
            System.out.println("Please enter UserName which you want to Use");
            userName = sc.nextLine();

            ResultSet r = st.executeQuery("select * from userdata where userID like'" + userName + "'");
            if(r.next())
            {
                System.out.println("*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*");
                System.out.println("$ This Username already exists please enter another username $");
                System.out.println("*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*");
            }

            else
            {
                System.out.println("Please enter your new password");
                password = sc.nextLine();

                st.executeUpdate("insert into userdata values('" + name + "','" + email + "','" + userName + "','" + password + "')");
                System.out.println();
                System.out.println("----------------------------------------");
                System.out.println("Your logID has been created successfully");
                System.out.println("----------------------------------------");
                System.out.println();
                bool=true;
            }
        }

        display_WelcomePage();
    }

    public void display_WelcomePage()
    {
        System.out.println("Welcome to the **JUKEBOX**");
    }

    public void existingUser() throws SQLException {
        Statement st = ConnectionToDatabase.connection();
        boolean bool=false;
        while (bool!=true)
        {
            System.out.println("Please enter your username");
            userName = sc.next();

            System.out.println("Please enter your password");
            password = sc.next();

            ResultSet r1 = st.executeQuery("select * from userdata where userID like'" + userName + "' and passcode like'" + password + "'");

            if (r1.next()) {
                System.out.println("The provided information is correct");
                System.out.println();
                System.out.println("$$$~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~$$$");
                System.out.println();
                display_WelcomePage();
                bool=true;
            }

            else
            {

                try
                {
                    throw new UserNotFound("User Not Found");
                }

                catch (UserNotFound e)
                {
                    System.out.println("*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*");
                    System.out.println();
                    System.out.println("$$ User Not Found: please Enter correct UserName or Password $$");
                    System.out.println();
                    System.out.println("*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*");
                }
            }
        }
    }
}
