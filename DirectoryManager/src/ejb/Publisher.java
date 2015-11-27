package directoryManager.ejb;

import java.io.IOException;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;
 
public class Publisher {
 
    public static void main(String[] args) {
        try {
            // This is the address where the REST Server of Directory Manager will be published
            String localhostname = java.net.InetAddress.getLocalHost().getHostName();
            String BASE_URI = "http://" + localhostname + ":2662/MyServer/";
            HttpServer server = HttpServerFactory.create(BASE_URI);
            // The root resources in the java path will be automatically detected
            server.start();
            System.out.println("Browse the available operations with this URL : "+BASE_URI+"application.wadl");
            System.out.println("Press CTRL+C to stop the server");
            try {
                Thread.sleep(360 * 60 * 1000);
            } catch (Exception e) {
                System.out.println("Server stopped.");            
            }
            System.out.println("Server stopped.");
            server.stop(0);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
