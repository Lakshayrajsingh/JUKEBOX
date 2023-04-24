package com.user_sign_in;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionToDatabase
{
    public static Statement connection() throws SQLException
    {
        java.sql.Connection com = DriverManager.getConnection("jdbc:mysql://localhost:3306/userCredentials","root","root@123");
        Statement st=com.createStatement();
        return st;
    }
}
