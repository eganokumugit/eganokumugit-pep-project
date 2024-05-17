package Service;
import  Model.Message;

import java.util.List;

import DAO.MessageDAO;
public class MessageService 
{
    MessageDAO msgDAO;

    public MessageService(MessageDAO msgDAO)
    {
        this.msgDAO = msgDAO;
    }

    public Message addMessage(Message msg)
    {
        return msgDAO.createMessage(msg);
    }

    public Message deleteMessage(int id)
    {
        return msgDAO.deleteMessageWithId(id);
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
        else
        {
            msgDAO.updateMessage(id, newMsg);
            return msgDAO.getMessageWithId(id);
        }
    }

}
