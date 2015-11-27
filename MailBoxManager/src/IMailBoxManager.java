package mailBoxManager;

import java.util.*;
import javax.jws.WebService;

@WebService
public interface IMailBoxManager {

    // these are used by Directory Manager    
    public void createMailbox(String userName);
    
    public void deleteMailbox(String userName);
    
    // These are used by Mailing clients
    public List<String> readAUserNewMessages(String userName);

    public List<String> readAUserAllMessages(String userName);

    public void deleteAUserMessage(String userName, int messageKey);

    public void deleteAUserReadMessages(String userName);
    
    public void deleteAllMessages(String userName);

    public int sendMessagetoABox(String senderName, String receiverName, String subject, String body);    

    public String sendNews(String senderName, String subject, String body);
}
