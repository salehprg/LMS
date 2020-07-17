package Model;

import Helper.SqlManager;

public class UserModel {
    public int Id;
    public String FirstName;
    public String LastName;
    public String IdNumber;
    public String UserName;
    public String Password;
    public int RoleId;


    public enum Role{
        Student ,
        Admin
    }
}

