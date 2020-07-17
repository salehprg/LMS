
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Date;

import Helper.*;
//import sun.util.calendar.LocalGregorianCalendar.Date;

public class Main {
    public static void main(String[] args) 
    {
        try 
        {
            SqlManager sqlManager = new SqlManager();
            //System.out.println(sqlManager.GetAllUsers().get(0).MelliCode);
        } 
        catch (SQLException e) {
            
        }
        
        System.out.println(new Date());
    }
}
