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
        ArrayList<Message> msgList = new ArrayList<>();
        try 
        {
            String sql = "SELECT * FROM message;";
            PreparedStatement ps = cnc.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Message msg = new Message();
            while(rs.next())
            {
                msg.setMessage_id(rs.getInt("message_id"));
                msg.setPosted_by(rs.getInt("posted_by"));
                msg.setMessage_text(rs.getString("message_text"));
                msg.setTime_posted_epoch(rs.getLong("time_posted_epoch"));
                msgList.add(msg);
            }

        }catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return msgList;
     }

    // Get One Message w/ msg_ID
     public Message getMessageWithId(int id)
     {
        Connection cnc = ConnectionUtil.getConnection();
        try 
        {
            String sql = "SELECT * FROM message WHERE message_id=?;";
            PreparedStatement ps = cnc.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Message msg = new Message();
            while(rs.next())
            {
                msg.setMessage_id(rs.getInt("message_id"));
                msg.setPosted_by(rs.getInt("posted_by"));
                msg.setMessage_text(rs.getString("message_text"));
                msg.setTime_posted_epoch(rs.getLong("time_posted_epoch"));
            }
            return msg;
        }catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return null;
     }
    // Delete One Message w/ msg_ID
     public int deleteMessageWithId(int id)
     {
        Connection cnc = ConnectionUtil.getConnection();
        int rowsAffected = 0;
        try 
        {
            String sql = "DELETE FROM message WHERE message_id=?";
            PreparedStatement ps = cnc.prepareStatement(sql);
            ps.setInt(1, id);
            rowsAffected = ps.executeUpdate();
        }catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return rowsAffected;
     }

    // Update One Message w/ msg_ID
     public int updateMessage(int id, String newMsg)
     {
        Connection cnc = ConnectionUtil.getConnection();
        int rowsAffected = 0;
        try 
        {
            String sql = "UPDATE message SET message_text=? WHERE message_id=?";
            PreparedStatement ps = cnc.prepareStatement(sql);
            ps.setString(1, newMsg);
            ps.setInt(2, id);
            rowsAffected = ps.executeUpdate();
        }catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return rowsAffected;
     }
    // Get All Messages from User w/ acc_ID
     public ArrayList<Message> getAllMessagesFromUser(int id)
     {
        Connection cnc = ConnectionUtil.getConnection();
        ArrayList<Message> msgList = new ArrayList<>();
        try 
        {
            String sql = "SELECT * FROM message, account a WHERE a.account_id=?;";
            PreparedStatement ps = cnc.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Message msg = new Message();
            while(rs.next())
            {
                msg.setMessage_id(rs.getInt("message_id"));
                msg.setPosted_by(rs.getInt("posted_by"));
                msg.setMessage_text(rs.getString("message_text"));
                msg.setTime_posted_epoch(rs.getLong("time_posted_epoch"));
                msgList.add(msg);
            }

        }catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return msgList;
    }
}
