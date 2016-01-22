package com.flying.xiaopo;

import com.flying.xiaopo.domain.Taeyeon;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.Uri;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.net.URI;

/**
 * Created by Flying SnowBean on 2016/1/16.
 */
public class MyResourceTest {
    private static final Logger logger = LogManager.getLogger("MyResourceTest");
    private static final String BASE_URI = "http://localhost:9090/webapi/";
    private HttpServer mServer;
    private WebTarget mWebTarget;

    @Before
    public void ready() throws IOException {
        final ResourceConfig resourceConfig = new ResourceConfig().packages("com.flying.xiaopo.resources").register(MultiPartFeature.class);
        final URI uri = URI.create(BASE_URI);
        mServer = GrizzlyHttpServerFactory.createHttpServer(uri,resourceConfig);
        Client client = ClientBuilder.newClient();
        mWebTarget = client.target(BASE_URI);
        mServer.start();
    }

    @After
    public void end(){
        mServer.shutdown();
    }

    @Test
    public void testTaeyeon(){
        Taeyeon taeyeon = new Taeyeon();
        taeyeon.setPic("/user/pic.png");
        taeyeon.setDescription("Hello");
        taeyeon.setDate("2016-01-17");

        final Entity<Taeyeon> entity = Entity.entity(taeyeon, MediaType.APPLICATION_JSON_TYPE);
        logger.error(mWebTarget.getUri().toString());
        final Taeyeon saveTaeyeon = mWebTarget.request().post(entity,Taeyeon.class);

        Assert.assertNotNull(saveTaeyeon.getId());
    }

    @Test
    public void testText(){

        String s = mWebTarget.path("myresource").path("file").request().get(String.class);
        logger.debug(s);
        Assert.assertFalse("Hello Wupanjie!".equals(s));
    }
    @Test
    public void testContext(){
        String s = mWebTarget.path("myresource").request().get(String.class);
        logger.debug(s);

    }

}
