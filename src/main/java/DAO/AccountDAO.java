package DAO;

import Model.Account;
import Util.ConnectionUtil;

import java.sql.*;
public class AccountDAO 
{
    // Check Username
    public boolean userExists(String username)
    {
        Connection cnc = ConnectionUtil.getConnection();
        try 
        {
            String sql = "SELECT username FROM account WHERE username=?;";
            PreparedStatement ps = cnc.prepareStatement(sql);
            ps.setString(1, username);

            if(ps.executeUpdate() > 0)
            {
                return true;
            }
            
        }catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return false;
     }

    // Register User
     public Account registerAccount(Account acc)
     {
  
        Connection cnc = ConnectionUtil.getConnection();
        try 
        {
            String sql = "INSERT INTO account(username,password) VALUES(?,?);";
            PreparedStatement ps = cnc.prepareStatement(sql);
            ps.setString(1, acc.getUsername());
            ps.setString(2, acc.getPassword());
            ps.executeUpdate();
            return acc;
        }catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return null;
     }
    // Login User
    public Account loginUser(String username, String password)
    {
       Connection cnc = ConnectionUtil.getConnection();
       try 
       {
           String sql = "SELECT * FROM account WHERE username=? AND password=?;";
           PreparedStatement ps = cnc.prepareStatement(sql);
           ps.setString(1, username);
           ps.setString(2, password);           
           ResultSet rs = ps.executeQuery();
           Account acc = new Account();
           while(rs.next())
           {
               acc.setUsername(rs.getString("username"));
               acc.setPassword(rs.getString("password"));
           }
           return acc;
       }catch (SQLException e) {
           System.out.println("ERROR: " + e.getMessage());
       }
       return null;
    }
}
