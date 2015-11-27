package mailBoxManager;

import java.util.*;
import javax.persistence.*;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.WebParam;

// @WebService(endpointInterface="mailBoxManager.IMailBoxManager")

@Entity
@Table(name="BOX")
public class MailBox extends Box {

    public MailBox () {
        super();
    }

    public MailBox(String MailBoxName) {
        super();
        this.name = MailBoxName;
    }

    // Delete A message with given key 

    public void deleteAMessage(Message messagekey) {
        getMessages().remove(messagekey);
    }

    // Delete All Read messages from this Mail Box
    
    public void deleteReadMessages() {
        for (Message message : getMessages()) {
            if (message.isRead()) {
                getMessages().remove(message);
            }
        }
    }

    // Delete all messages

    public void deleteAllMessages() {
        getMessages().clear();
    }

    // Read all new messages from this mail Box

    public List<String> readNewMessages() {
        List<String> newMessages = new ArrayList<String>();
        for (Message message : getMessages()) {
            if (!message.isRead()) {
                message.setIsRead();
                newMessages.add(message.toString());
            }
        }
        return newMessages;
    }
}
