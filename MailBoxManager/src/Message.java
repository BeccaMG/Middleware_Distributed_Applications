package mailBoxManager;

import javax.persistence.*;
import static javax.persistence.CascadeType.*;

import java.util.Date;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    @ManyToOne
    private Box mailBox;
    
    private String senderName;
    private String receiverName;
    
    @Temporal(TemporalType.DATE)
    private Date sendingDate;
    
    private String subject;
    private String body;
    private Boolean isRead = false;

    public Message() {
        this.senderName = "unknown";
        this.receiverName = "unknown";
        this.sendingDate = new Date();
        this.subject = "unknown";
        this.body = "unknown";
    };

    public Message(String senderName, String receiverName, Date sendingDate, String subject, String body) {
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.sendingDate = sendingDate;
        this.subject = subject;
        this.body = body;
    }
            
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public Box getMailBox() {
        return this.mailBox;
    }
    
    public void setMailBox(Box mb) {
        this.mailBox = mb;
    }
    
	public String toString(){
		return ("############# Message Start #############\n" + 
                ">>>>> ID: " + this.getId() + " <<<<<" +
                "\nSender Name: " + this.senderName + 
                "\nReceiver Name: " + this.receiverName + 
                "\nSending Date: " + this.sendingDate.toString() + 
                "\nSubject: " + this.subject + 
                "\nBody:\n" + this.body + 
                "\n############## Message End ##############\n");
	}
		
	public Boolean isRead() {
		return this.isRead;
	}
	
    public void setIsRead() {
        this.isRead = true;
    }
    
	public void unsetIsRead() {
		this.isRead = false;
	}
	
    public void setSenderName(String name) {
        this.senderName = name;
    }
    
    public String getSenderName() {
        return this.senderName;
    }
    
    public void setReceiverName(String name) {
        this.receiverName = name;
    }
    
    public String getReceiverName() {
        return this.receiverName;
    }
    
    public void setSendingDate(Date date) {
        this.sendingDate = date;
    }
    
    public Date getSendingDate() {
        return this.sendingDate;
    }
    
    public void setSubject(String s) {
        this.subject = s;
    }
    
    public String getSubject() {
        return this.subject;
    }
    
	public void setBody(String body) {
        this.body = body;
	}
	
	public String getBody() {
        return this.body;
    }
}
