package directoryManager.ejb;

import javax.naming.InitialContext;
import java.io.File;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;
import javax.naming.NamingException;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.JAXBException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import java.util.List;

@Path("/IUserDirectory")
public class UserDirectoryRest {
    
    InitialContext ic;
    IUserDirectory userd;

    public UserDirectoryRest() {
        try {
            ic = new InitialContext();
            userd = (IUserDirectory) ic.lookup(directoryManager.ejb.IUserDirectory.class.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Look up for user send news right and return "true" as string 
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/lookupUser/{userName}")
    public String lookupUserCanSendNews(@PathParam("userName") String userName) {
        return userd.lookupUserCanSendNews(userName).toString();
    }
    
    //Look for all users who have news group read rights and return the list of those users
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/receivers")
    public String lookupAllUsersCanReadNews() {
        String ret = "";
        for (String s : userd.lookupAllUsersCanReadNews()) {
            ret += s;
            ret += ",";
        }
        return ret;
    }
}
