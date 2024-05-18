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
            String sql = "SELECT * FROM account WHERE username=?;";
            PreparedStatement ps = cnc.prepareStatement(sql);
            ps.setString(1, username);
            int check = ps.executeUpdate();
            if(check == 0)
            {
                return false;
            }
            
        }catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return true;
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
    public Account loginUser(Account acc)
    {
       Connection cnc = ConnectionUtil.getConnection();
       try 
       {
           String sql = "SELECT * FROM account WHERE username=? AND password=?;";
           PreparedStatement ps = cnc.prepareStatement(sql);
           ps.setString(1, acc.getUsername());
           ps.setString(2, acc.getPassword());   
           if(ps.executeUpdate() > 0)
           {    
                ResultSet rs = ps.executeQuery();
                Account newAcc = new Account();
                while(rs.next())
                {
                    newAcc.setAccount_id(rs.getInt("account_id"));
                    newAcc.setUsername(rs.getString("username"));
                    newAcc.setPassword(rs.getString("password"));
                }
                return acc;
           }  
            else
            {
                return null;
            }
       }catch (SQLException e) {
           System.out.println("ERROR: " + e.getMessage());
       }
       return null;
    }

    public boolean idExists(int id)
    {
       Connection cnc = ConnectionUtil.getConnection();
       try 
       {
           String sql = "SELECT * FROM account WHERE account_id=?;";
           PreparedStatement ps = cnc.prepareStatement(sql);
           ps.setInt(1, id);

           if(ps.executeUpdate() > 0)
           {
               return true;
           }
           
       }catch (SQLException e) {
           System.out.println("ERROR: " + e.getMessage());
       }
       return false;
    }
}
