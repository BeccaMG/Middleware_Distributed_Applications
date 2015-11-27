package directoryManager.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class User implements Serializable {
	
	private String userName;
    private String NewsGroupRights;
	
	public User() {}
	
	public User(String userName){
		this.userName = userName;
	}
	
	@Id
	public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getNewsGroupRights() {
        return this.NewsGroupRights;
    }
    
    public void setNewsGroupRights(String rights) {
        this.NewsGroupRights = rights;
    }
    
    public String newsGroupRightsToString() {

        switch (NewsGroupRights) {
        
            case "nr": return "No rights";
                       
            case "ro": return "Read only";
                       
            case "rw": return "Read and write";
                       
            default: return null;
        }
    }
    
    public Boolean hasNewsGroupReadRights() {
        return (NewsGroupRights.equals("ro") 
                || NewsGroupRights.equals("rw"));
    }
    
    public Boolean hasNewsGroupWriteRights() {
        return NewsGroupRights.equals("rw");
    }
    
    public String toString() {
        return "User: " + this.userName + 
               "\tNews group rights: " + this.newsGroupRightsToString() + "\n";
        
    }
}