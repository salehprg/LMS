package LMS.Helper;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import LMS.Model.*;


public class SqlManager {

    Connection conn;
    Statement statement;

    public SqlManager () throws SQLException {
        String connectionUrl = "jdbc:sqlserver://localhost\\SQLSERVER2008R2:1433;databaseName=JavaLMS;integratedSecurity=true";

        conn = DriverManager.getConnection(connectionUrl);
        statement = conn.createStatement();
    }


    public ArrayList<UserModel> GetAllUsers() throws SQLException
    {
        // Create and execute a SELECT SQL statement.
        String selectSql = "SELECT * from Users";
        ResultSet resultSet = statement.executeQuery(selectSql);

        ArrayList<UserModel> userModels = new ArrayList<>();

        while (resultSet.next()) {
            UserModel data = new UserModel();

            data.Id = resultSet.getInt("Id");
            data.FirstName = resultSet.getString("FirstName");
            data.LastName = resultSet.getString("LastName");
            data.IdNumber = resultSet.getString("IdNumber");
            data.PhoneNumber = resultSet.getString("PhoneNumber");
            data.MelliCode = resultSet.getString("MelliCode");

            userModels.add(data);
        }

        return userModels;
    }

    public boolean AddUser() throws SQLException 
    {
        return false;
    }
}