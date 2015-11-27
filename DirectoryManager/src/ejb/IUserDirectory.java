package directoryManager.ejb;

import javax.ejb.Remote;
import java.util.List;

import directoryManager.entity.User;

@Remote
public interface IUserDirectory {

    // Methods for the administration client
    public void addUser(String userName) throws Exception;

    public void removeUser(String userName) throws Exception;

    public User lookupUser(String userName);

    public void updateUserNewsGroupRights(String userName, String userRights) throws Exception;
    
    public List<User> lookupAllUsers();
    
    // Methods for the MailBox Manager
    public Boolean lookupUserCanSendNews(String userName);
    
    public List<String> lookupAllUsersCanReadNews();

}