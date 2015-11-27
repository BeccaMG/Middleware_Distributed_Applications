import mailboxmanager.*;

import java.util.*;

public class MailBoxClient { 
      public static void main(String[] a) throws Exception { 

        IMailBoxManager mailbox = new MailBoxManagerService().getMailBoxManagerPort();
        int ret;
        String ret2;
        List<String> message;
        List<String> messages;
        
        // All mailboxs added through the DirectoryManager
        System.out.println("================================= MOHSIN TRIES TO SEND NEWS");
        ret2 = mailbox.sendNews("Mohsin", "Project", "It is not really good :p");
        System.out.println(ret2);
        
        System.out.println("================================= Amna TRIES TO SEND NEWS");
        ret2 = mailbox.sendNews("Amna", "Class", "We have a class guys !!!!");
        System.out.println(ret2);
        System.out.println("================================= READ NEW MESSAGES OF Amna");
        messages = mailbox.readAUserNewMessages("Amna");
        System.out.println(messages);
        System.out.println("================================= READ NEW MESSAGES OF MOHSIN");
        messages = mailbox.readAUserNewMessages("Mohsin");
        System.out.println(messages);

        System.out.println("================================= SEND A MESSAGE TO Amna");
        mailbox.sendMessagetoABox("Mohsin", "Amna", "TestClient", "We are doing a project :)");
        System.out.println("================================= READ NEW MESSAGES OF Amna");
        messages = mailbox.readAUserNewMessages("Amna");
        System.out.println(messages);
        
        System.out.println("================================= READ ALL MESSAGES OF SOPHIE");
        messages = mailbox.readAUserAllMessages("Sophie");
        System.out.println(messages);        
        
        System.out.println("================================= SEND A MESSAGE TO REBECA WHO DOESN'T EXIST");
        mailbox.sendMessagetoABox("Mohsin", "Rebeca", "Project", "How is it going?");
        System.out.println("================================= READ ALL MESSAGES OF REBECA WHO DOESN'T EXIST");
        messages = mailbox.readAUserAllMessages("Rebeca");
        System.out.println(messages);        
        
        // Gives problems when ran twice
        System.out.println("================================= DELETE ALL MESSAGES OF Sophie");
        mailbox.deleteAllMessages("Sophie");
        System.out.println("================================= READ ALL MESSAGES OF Sophie");
        messages = mailbox.readAUserAllMessages("Sophie");
        System.out.println(messages);


        System.out.println("================================= SEND A MESSAGE TO Mohsin");
        mailbox.sendMessagetoABox("Mostafa", "Mohsin", "Hello", "Mohsin have you deleted your messages?");
        System.out.println("================================= DELETE READ MESSAGES OF Mohsin");
        mailbox.deleteAUserReadMessages("Mohsin");
        System.out.println("================================= READ ALL MESSAGES OF Mohsin");
        messages = mailbox.readAUserAllMessages("Mohsin");
        System.out.println(messages);
        
        System.out.println("================================= MOHSIN TRIES TO SEND NEWS");
        ret2 = mailbox.sendNews("Mohsin", "El Beta", "I HAVE SUCH A BIG NEWS!");
        System.out.println(ret2);
        
        System.out.println("================================= READ ALL MESSAGES OF MOSTAFA WHO DOESN'T HAVE RIGHT TO READ FROM NEWS");
        messages = mailbox.readAUserAllMessages("Mostafa");
        System.out.println(messages);
        
      }
}
