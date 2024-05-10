package DAO;

import Model.Message;
import Util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
public class MessageDAO 
{
    // Message Creation
    public Message createMessage(Message msg)
    {
        Connection cnc = ConnectionUtil.getConnection();
        try 
        {
            String sql = "INSERT INTO message(posted_by,message_text,time_posted_epoch) VALUES(?,?,?);";
            PreparedStatement ps = cnc.prepareStatement(sql);
            ps.setInt(1, msg.getPosted_by());
            ps.setString(2, msg.getMessage_text());
            ps.setLong(3, msg.getTime_posted_epoch());

            ps.executeUpdate();
            return msg;
        }catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return null;
     }

    // Get All Messages
     public ArrayList<Message> getAllMessages()
     {
        Connection cnc = ConnectionUtil.getConnection();
        try 
        {
            String sql = "SELECT * FROM message;";
            PreparedStatement ps = cnc.prepareStatement(sql);
        }catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return null;
     }

    // Get One Message w/ msg_ID
     public Message getMessageWithId(int id)
     {
        Connection cnc = ConnectionUtil.getConnection();
        try 
        {
            String sql = "";
            PreparedStatement ps = cnc.prepareStatement(sql);
        }catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return null;
     }
    // Delete One Message w/ msg_ID
     public void deleteMessageWithId(int id)
     {
        Connection cnc = ConnectionUtil.getConnection();
        try 
        {
            String sql = "";
            PreparedStatement ps = cnc.prepareStatement(sql);
        }catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
     }

    // Update One Message w/ msg_ID
     public void updateMessage(int id)
     {
        Connection cnc = ConnectionUtil.getConnection();
        try 
        {
            String sql = "";
            PreparedStatement ps = cnc.prepareStatement(sql);
        }catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
     }
    // Get All Messages from User w/ acc_ID
     public ArrayList<Message> getAllMessagesFromUser(int id)
     {
        Connection cnc = ConnectionUtil.getConnection();
        try 
        {
            String sql = "";
            PreparedStatement ps = cnc.prepareStatement(sql);
        }catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return null;
     }
}
