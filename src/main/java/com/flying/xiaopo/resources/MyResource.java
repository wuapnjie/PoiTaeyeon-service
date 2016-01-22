package com.flying.xiaopo.resources;

import org.glassfish.jersey.servlet.ServletContainer;
import org.springframework.http.HttpRequest;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.io.*;
import java.net.URI;
import java.net.URL;

/**
 * 只是一个初始类，实验用
 * Root resource (exposed at "myresource" path)
 */

@Path("myresource")
public class MyResource {
    @Context
    ServletContainer container;
    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt(@Context UriInfo uriInfo,@Context ServletContext context) {
        System.out.println(container);
        System.out.println(uriInfo);
        System.out.println(context);
        return "Got it!" + "->" ;
    }

    @GET
    @Path("file")
    @Produces("text/html")
    public String getText(@Context UriInfo uriInfo) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        URI uri = URI.create("http://localhost:8080/test.txt");
        final File file = new File(uri.getPath());
        System.out.println(file.getAbsolutePath());
        FileInputStream stream = null;
        try {
            stream = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            while ((stream.read(buffer,0,buffer.length))!=-1){
                stringBuilder.append(new String(buffer));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            if (stream!=null){
                stream.close();
            }
        }

        return stringBuilder.toString()+uriInfo.getBaseUri().getPath();

    }
}
