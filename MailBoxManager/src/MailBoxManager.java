package mailBoxManager;

import java.util.*;
import java.lang.reflect.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ejb.Stateful;
import javax.ejb.Stateless;

import org.eclipse.persistence.jpa.PersistenceProvider;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.WebParam;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

@WebService(endpointInterface="mailBoxManager.IMailBoxManager")
@Stateful
public class MailBoxManager implements IMailBoxManager {

    private EntityManager em;
    private EntityManagerFactory emf;
    private NewsBox newsBox;
    WebResource service;    

    public MailBoxManager(String dm) {
        emf = Persistence.createEntityManagerFactory("mailboxDB");
        em = emf.createEntityManager();
        this.createMailbox("NewsGroup");
        this.newsBox = em.find(NewsBox.class, "NewsGroup");
        
        // The directory manager
        String REST_URI = "http://" + dm + ":2662/MyServer/";
        Client client = Client.create(new DefaultClientConfig());
        URI uri = UriBuilder.fromUri(REST_URI).build();
        service = client.resource(uri);                
    }

    // Return the all new messages for given user
    public List<String> readAUserNewMessages(String userName) 		{
        MailBox mailBox = em.find(MailBox.class, userName);
        if (mailBox != null) {
            return mailBox.readNewMessages();
        }
        System.out.println("No mailbox with " + userName);
        return null;
    }

    // Return all messages for given user
    public List<String> readAUserAllMessages(String userName) 		{
        MailBox mailBox = em.find(MailBox.class, userName);
        if (mailBox != null) {
            return mailBox.readAllMessages();
        }
        return null;
    }

    // Delete a given user message with given message key 
    public void deleteAUserMessage(String userName, int messageKey) {
        MailBox mailBox = em.find(MailBox.class, userName);
        if (mailBox != null) {
            try {
                Message m = em.find(Message.class, messageKey);
                mailBox.deleteAMessage(m);
                m = em.find(Message.class, messageKey);

                em.getTransaction().begin();
                em.remove(m);
                em.getTransaction().commit();
                
            } catch (Exception e) { 
            }
        }
    }

    // Delete the all read messages of a given user
    public void deleteAUserReadMessages(String userName) {
        MailBox mailBox = em.find(MailBox.class, userName); 
        if (mailBox != null) {
            for (int i = 0; i < mailBox.getMessages().size(); i++) {
                Message message = mailBox.getMessages().get(i);
                if (message.isRead()) {
                    try {
                        em.getTransaction().begin();
                        em.remove(message);
                        em.getTransaction().commit();
                    } catch (Exception e) { }
                }
                mailBox.getMessages().remove(mailBox.getMessages().indexOf(message));
            }
        }
    }
    
    // Delete all the messages for given user
    public void deleteAllMessages(String userName) {
        MailBox mailBox = em.find(MailBox.class, userName); 
        if (mailBox != null) {
            for (int i = 0; i < mailBox.getMessages().size(); i++) {
                Message message = mailBox.getMessages().get(i);
                try {
                    em.getTransaction().begin();
                    em.remove(message);
                    em.getTransaction().commit();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
            mailBox.deleteAllMessages();
        }
    }

    // Send a message to given receiver
    public int sendMessagetoABox(String senderName, String receiverName, String subject, String body) {
        Date sendingDate = new Date();
        MailBox mailBox = em.find(MailBox.class, receiverName);
        MailBox mailBox1 = em.find(MailBox.class, senderName);
        if (mailBox != null && mailBox1 != null) {
            Message addMessage = new Message(senderName, receiverName, sendingDate, subject, body);
            int ret;            
            
            try {
                em.getTransaction().begin();
                em.persist(addMessage);
                em.getTransaction().commit();            
            } catch (Exception e) {
                return -1;
            }
        
            addMessage.setMailBox(mailBox);
            ret = mailBox.addMessage(addMessage);
            return ret;
        }
        return -1;
    }

    // Create a mail box for given user
    public void createMailbox(String userName) {
        MailBox mailBox = new MailBox(userName);
        try {
            em.getTransaction().begin();
            em.persist(mailBox);
            em.getTransaction().commit();
        } catch (Exception e) { }
    }

    // delete a mail box for given user
    public void deleteMailbox(String userName) {
        try {
            MailBox mailBox = em.find(MailBox.class, userName); // Check null
        
            em.getTransaction().begin();
            em.remove(mailBox);
            em.getTransaction().commit();
        } catch (Exception e) { }
    }

    // send a message to a news group
    public String sendNews(String senderName, String subject, String body) {
        String receiverNames = "";
        String senderCanWrite = "false";
        try {
            //receiverNames = (List<String>) service.path("IUserDirectory/receivers").type(MediaType.TEXT_PLAIN).get(List.class);
            senderCanWrite = service.path("IUserDirectory/lookupUser/"+senderName).type(MediaType.TEXT_PLAIN).get(String.class);
            //System.out.println("Receivers: " + receiverNames);
            System.out.println("Sender rights to send: " + senderCanWrite);
        } catch (Exception e) {
            System.out.println("Problem with the dm");
        }
            
        if (senderCanWrite.equals("true")) {
            try {
                receiverNames = service.path("IUserDirectory/receivers").type(MediaType.TEXT_PLAIN).get( String.class );
                String[] splits = receiverNames.split(",");
                System.out.println("Receivers: " + splits);
                for (String s : splits) {
                    sendMessagetoABox(senderName, s, subject, body);
                }
                return "Message Sent Successfully";
            } catch (Exception e) {
                System.out.println("Problem with the dm in receivers");
            }
        }
        
        return "Sender " + senderName + " doesn't have NewsGroup Rights to send Message";
    }
}
