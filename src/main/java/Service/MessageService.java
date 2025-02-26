package Service;

import java.util.List;

import DAO.MessageDAO;
import Model.Message;

public class MessageService {
    private MessageDAO messageDAO;

    public MessageService(){
        messageDAO = new MessageDAO();
    }

    public MessageService(MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }

    public Message addMessage(Message message){
        if(message.getMessage_text() != "" && message.getMessage_text().length() < 255 && messageDAO.getAllMessagesByAccountId(message.getPosted_by()) != null){
            return messageDAO.insertMessage(message);
        }
        return null;
    }

    public List<Message> getAllMessages(){
        return messageDAO.getAllMessages();
    }

    public Message getMessageByMessageId(int messageId){
        return messageDAO.getMessageByMessageId(messageId);
    }

    public Message deleteMessage(int messageId){
        return messageDAO.deleteMessageByMessageId(messageId);
    }

    public Message replaceMessage(Message message){
        if(message.getMessage_text() != "" && message.getMessage_text().length() < 255 && messageDAO.getMessageByMessageId(message.getMessage_id()) != null){
            return messageDAO.updateMessageByMessageId(message.getMessage_id(), message.getMessage_text());
        }
        return null;
    }

    public List<Message> getAllMessagesFromAccount(Message message){
        return messageDAO.getAllMessagesByAccountId(message.getPosted_by());
    }
}
