package directoryManager.client;

import javax.naming.InitialContext;
import java.util.List;
import java.util.Iterator;

import directoryManager.ejb.IUserDirectory;
import directoryManager.entity.User;

public class AdministrationClient {

    public static void main(String args[]) {
    
        try {

            InitialContext ic = new InitialContext();
            IUserDirectory ud = (IUserDirectory) ic.lookup("directoryManager.ejb.IUserDirectory");
            User u;
    
            // Adding User Mohsin  
            
            System.out.println("Adding user Mohsin...");
            
            try {
                ud.addUser("Mohsin");
            } catch (Exception e) {
                System.out.println("User already exists.");
            }    
            
            // Adding User Amna  

            
            System.out.println("Adding user Amna...");
            
            try {
                ud.addUser("Amna");
            } catch (Exception e) {
                System.out.println("User already exists.");
            }
            
            // Adding User Mostafa  

            
            System.out.println("Adding user Mostafa...");
            
            try {
                ud.addUser("Mostafa");
            } catch (Exception e) {
                System.out.println("User already exists.");
            }
            
            // Adding User Rebeca  
            
            System.out.println("\nAdding user Rebeca...");

            try {
                ud.addUser("Rebeca");
            } catch (Exception e) {
                System.out.println("User already exists.");
            } 
            
            // Adding User Sophie
            
            System.out.println("\nAdding user Sophie...");
            
            try {
                ud.addUser("Sophie");
            } catch (Exception e) {
                System.out.println("User already exists.");
            }
             
            // Adding the user Rebeca again to test, What will happen if we try to add the user again
            
            System.out.println("\nTrying to add again user Rebeca...");
            
            try {
                ud.addUser("Rebeca");
            } catch (Exception e) {
                System.out.println("User already exists.");
            }
            
            // lookup for all users information in mailbox application
            
            System.out.println("\nRetrieving all users in system...");
            List<User> ulist = ud.lookupAllUsers();
            System.out.println(ulist);
            
            // Updating the User Mohsin rights to "rw"
            System.out.println("\nUpdating Mohsin...");
            try {
                ud.updateUserNewsGroupRights("Mohsin","rw");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            
            // Check for User Mohsin rights
            
            System.out.println("\nChecking user Mohsin rights ...");
            u = ud.lookupUser("Mohsin");
            if (u != null)
                System.out.println("User Mohsin with rights \""
                    + u.getNewsGroupRights()
                    + "\".");

                    
            System.out.println("\nUpdating Mostafa...");
            try {
                ud.updateUserNewsGroupRights("Mostafa","nr");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
                
            // Removing user Rebeca
            
            System.out.println("\nRemoving user Rebeca...");
            
            try {
                ud.removeUser("Rebeca");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } 
            
//             u = ud.lookupUser("Rebeca");
//             
//             if (u != null)
//                 System.out.println("Found user Rebeca with rights \""
//                 + u.getUserNewsGroupRights()
//                 + "\".");
//             else
//                 System.out.println("User Rebeca not found.");
//            
            // Try to remove user Rebeca again
            
            System.out.println("\nTrying to remove again user Rebeca...");
            
            try {
                ud.removeUser("Rebeca");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } 
            
            /*u = ud.lookupUser("Rebeca");
            
            if (u != null)
                System.out.println("Found user Rebeca with rights \""
                + u.getUserNewsGroupRights()
                + "\".");
            else
                System.out.println("User Rebeca not found.");
              */  
                
            // Printing all Users in System
                
            System.out.println("\nRetrieving all users in system...");
            ulist = ud.lookupAllUsers();
            System.out.println(ulist);                        
            
            
        } catch(Exception e) {
        
            e.printStackTrace();
        }
    }
}
