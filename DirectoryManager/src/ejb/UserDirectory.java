package directoryManager.ejb;

import javax.ejb.Stateful;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import directoryManager.entity.User;

import mailboxmanager.*;

@Stateful(name="directoryManager/ejb/IUserDirectory")
public class UserDirectory implements IUserDirectory {

    @PersistenceContext(unitName="userDB")
    private EntityManager em;
    
    // Connection with the MailBoxManager via SOAP (Web Services)
    IMailBoxManager mailbox = new MailBoxManagerService().getMailBoxManagerPort();
    
    /*
     * It adds a user for given user name by creating a mailbox in Mailbox manager
     * set the rights to read only for the newsgroup and store the 
     * new user in database
     */
    public void addUser(String userName) throws Exception {
        User newUser = new User(userName);
        newUser.setNewsGroupRights("ro");
        mailbox.createMailbox(userName);
        em.persist(newUser);
    }

    // It deletes the user email box and also remove user information from the database 
    public void removeUser(String userName) throws Exception {
        User u = em.find(User.class, userName);
        
        if (u != null) {
            em.remove(u);
            mailbox.deleteMailbox(userName);
        } else {
            throw new Exception("User does not exist.");
        }
    }

    // It returns the Users information for given user Name
    public User lookupUser(String userName) {
        return em.find(User.class, userName);
    }
    
    // It looks for given user rights to send news to newsgroup
    public Boolean lookupUserCanSendNews(String userName) {
        User u = em.find(User.class, userName);
        if (u != null)
            return u.hasNewsGroupWriteRights();
        return false;
    }

    // It updates the given user rights to given rights
    public void updateUserNewsGroupRights(String userName, String userRights) throws Exception {

        if (userRights.equals("nr") || userRights.equals("ro") || userRights.equals("rw")) {
            User u = em.find(User.class, userName);
 
            if (u != null)
                u.setNewsGroupRights(userRights);
            else
                throw new Exception("User does not exist.");
            
        } else
            throw new Exception("Not valid rights.");
        
    }

    // It looks for all users and return their information
    public List<User> lookupAllUsers() {
        Query q = em.createQuery(
            "SELECT u " +
            "FROM User u ");
        
        try {
            List<User> ulist = q.getResultList();
            return ulist;
        } catch (Exception e) {
            return null;
        }
    }
    
    // it looks for users who have newsgroup read rights and return the list of their names
    public List<String> lookupAllUsersCanReadNews() {
        List<User> ulist = this.lookupAllUsers();
        List<String> rightfulUsers = new ArrayList<String>();      
        
        if (ulist == null)
            return rightfulUsers;
          
        for (User u : ulist) {
            if (u.hasNewsGroupReadRights())
                rightfulUsers.add(u.getUserName());
        }
        return rightfulUsers;
    }
}
