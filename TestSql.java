package LMS;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import LMS.Helper.*;

public class TestSql {

    public static void main(String[] args) throws SQLException 
    {
        SqlManager sqlManager = new SqlManager();
        System.out.println(sqlManager.GetAllUsers().get(0).MelliCode);
    }
}