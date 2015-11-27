package mailBoxManager;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.persistence.*;
import static javax.persistence.CascadeType.*;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.WebParam;

@Entity
@Table(name="BOX")
public abstract class Box {

    @Id
    protected String name;
    
    @OneToMany(cascade=CascadeType.ALL, mappedBy="mailBox")
    protected List<Message> messages;

    public Box() {
        messages = new CopyOnWriteArrayList<Message>();
    }
        
    // Get the name of this box

    public String getName() {
        return this.name;
    }

    // Set the name of this box
   
    public void setName(String name) {
        this.name = name;
    }
    
    public List<Message> getMessages() {
        return this.messages;
    }
    
    public void setMessages(List<Message> m) {
        this.messages = m;
    }

    // Return all messages of a this box

    public List<String> readAllMessages() {
        List<String> list= new ArrayList<String>();
        for (Message m : this.getMessages()) {
            m.setIsRead();
            list.add(m.toString());
        }
        return list;
    }

	// add a new message to this user box

    public int addMessage(Message addMessage) {
        System.out.println("I send " + addMessage);
        this.getMessages().add(addMessage);
        System.out.println("After sending " + this.getMessages());
        return addMessage.getId();
    }
}
