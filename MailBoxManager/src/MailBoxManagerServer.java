package mailBoxManager;

import javax.xml.ws.Endpoint;

public class MailBoxManagerServer {
	
    protected MailBoxManagerServer(String dm) throws Exception {
        // START SNIPPET: publish
        System.out.println("Starting Server");
        String localhostname = java.net.InetAddress.getLocalHost().getHostName();
        // The address of the Directory Manager is passed here
        MailBoxManager mailBoxManager = new MailBoxManager(dm);
        String address = "http://" + localhostname + ":7364/MailBoxManager";
        Endpoint.publish(address, mailBoxManager);
        // END SNIPPET: publish
    }

    public static void main(String args[]) throws Exception {
        new MailBoxManagerServer(args[0]);
        System.out.println("Server ready...");

        Thread.sleep(360 * 60 * 1000);
        System.out.println("Server exiting");
        System.exit(0);
    }
}
