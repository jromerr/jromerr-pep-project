package DAO;

import Model.Message;

import Util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO {

    public List<Message> getAllMessages(){
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();
        try {
            String sql = "SELECT * FROM message";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                Message msg = new Message(
                    rs.getInt("message_id"),
                    rs.getInt("posted_by"),
                    rs.getString("message_text"),
                    rs.getLong("time_posted_epoch")
                );
                messages.add(msg);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return messages;
    }

    public List<Message> getAllMessagesByAccountId(){
        return null;
    }
    
    public Message insertMessage(Message message){
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "INSERT INTO message (posted_by,message_text,time_posted_epoch) VALUES (?,?,?)";
            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            pstmt.setInt(1, message.getPosted_by());
            pstmt.setString(2, message.getMessage_text());
            pstmt.setLong(3, message.getTime_posted_epoch());
            pstmt.executeUpdate();
            ResultSet pkeyResultSet = pstmt.getGeneratedKeys();
            if(pkeyResultSet.next()){
                int generated_message_id = (int) pkeyResultSet.getLong(1);
                return new Message(generated_message_id, message.getPosted_by(), message.getMessage_text(), message.getTime_posted_epoch());
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Message getMessageByMessageId(int msgId){
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "SELECT * FROM message WHERE message_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setInt(1, msgId);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                Message msg = new Message(
                    rs.getInt("message_id"),
                    rs.getInt("posted_by"),
                    rs.getString("message_text"),
                    rs.getLong("time_posted_epoch"));
                return msg;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Message deleteMessageByMessageId(int msgId){
        Connection connection = ConnectionUtil.getConnection();
        Message deletedMessage = new Message();
        try {
            //String sqlSelect = "SELECT * FROM message WHERE message_id = ?";
            String sqlDelete = "DELETE FROM message WHERE message_id = ?";
            //PreparedStatement selectStatement = connection.prepareStatement(sqlSelect);
            PreparedStatement deleteStatement = connection.prepareStatement(sqlDelete);
            /* 
            selectStatement.setInt(1, msgId);
            ResultSet rs = selectStatement.executeQuery();
            while(rs.next()){
                deletedMessage.setMessage_id(rs.getInt("message_id"));
                deletedMessage.setPosted_by(rs.getInt("posted_by"));
                deletedMessage.setMessage_text(rs.getString("message_text"));
                deletedMessage.setTime_posted_epoch(rs.getLong("time_posted_epoch"));
            }
            */
            //testing if method call works instead
            deletedMessage = getMessageByMessageId(msgId);

            deleteStatement.setInt(1, msgId);
            deleteStatement.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return deletedMessage;
    }

    public Message updateMessageByMessageId(int msgId, String messageUpdate){
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "UPDATE message SET message_text = ? WHERE message_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, messageUpdate);
            pstmt.setInt(2, msgId);
            pstmt.executeUpdate();

            return getMessageByMessageId(msgId);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
