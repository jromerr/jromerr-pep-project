import Controller.SocialMediaController;
import DAO.AccountDAO;
import DAO.MessageDAO;
import io.javalin.Javalin;
import Model.Account;
import Model.Message;

import java.util.List;

/**
 * This class is provided with a main method to allow you to manually run and test your application. This class will not
 * affect your program in any way and you may write whatever code you like here.
 */
public class Main {
    public static void main(String[] args) {
        //SocialMediaController controller = new SocialMediaController();
        //Javalin app = controller.startAPI();
        //app.start(8080);
        
        AccountDAO accountDAO = new AccountDAO();
        /* 
        Account acc = new Account("bob", "123");
        Account newAcc = accountDAO.insertAccount(acc);
        System.err.println(newAcc.toString());
       
        Account acc = accountDAO.getAccountByAccountId(1);
        System.out.println(acc.toString());
        
        Account acc = accountDAO.getAccountByUsername("testuser1");
        System.out.println(acc.toString());
        */
        MessageDAO messageDAO = new MessageDAO();
        //List<Message> msgs = messageDAO.getAllMessages();

        //System.out.println(msgs);
        Message msg = messageDAO.getMessageByMessageId(1);
        System.out.println(msg);

        //Message newMessage = new Message();

    }
}
