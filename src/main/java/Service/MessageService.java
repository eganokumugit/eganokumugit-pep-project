package Service;
import  Model.Message;

import java.util.List;

import DAO.*;
public class MessageService 
{
    MessageDAO msgDAO;
    AccountDAO accDAO;
    public MessageService()
    {
        msgDAO = new MessageDAO();
        accDAO = new AccountDAO();
    }
    public MessageService(MessageDAO msgDAO)
    {
        this.msgDAO = msgDAO;
        this.accDAO = new AccountDAO();

    }

    public Message addMessage(Message msg)
    {
        if(msg.getMessage_text().length() <= 255 && msg.getMessage_text().length() > 0 && accDAO.idExists(msg.getPosted_by()) == true)
        {
            return msgDAO.createMessage(msg);
        }
        else
        {
            return null;
        }
    }

    public Message deleteMessage(int id)
    {
        if(msgDAO.getMessageWithId(id) == null)
        {
            return null;
        }
        else
        {
            return msgDAO.deleteMessageWithId(id);
        }
    }

    public List<Message> getAllMessages()
    {
        return msgDAO.getAllMessages();
    }

    public List<Message> getAllMessagesFromUser(int id)
    {
        return msgDAO.getAllMessagesFromUser(id);
    }
    
    public Message getMessageWithId(int id)
    {
        return msgDAO.getMessageWithId(id);
    }
    public Message updateMessage(int id, String newMsg)
    {
        if(msgDAO.getMessageWithId(id) == null)
        {
            return null;
        }
        else if(newMsg.length() > 255)
        {
            return null;
        }
        else
        {
            msgDAO.updateMessage(id, newMsg);
            return msgDAO.getMessageWithId(id);
        }
    }

}
