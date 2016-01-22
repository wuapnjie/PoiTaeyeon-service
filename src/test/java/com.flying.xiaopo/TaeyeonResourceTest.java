package com.flying.xiaopo;

import com.flying.xiaopo.domain.Taeyeon;
import com.flying.xiaopo.domain.Taeyeons;
import com.flying.xiaopo.resources.TaeyeonResource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.StringBuilders;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.io.*;
import java.net.URI;

/**
 * Created by Flying SnowBean on 2016/1/17.
 */
public class TaeyeonResourceTest {
    private static final Logger logger = LogManager.getLogger(TaeyeonResourceTest.class);
    private HttpServer server;
    private WebTarget target;
    private static final String BASE_URI = "http://localhost:8080/webapi/";

    @Before
    public void setUp() {
        final ResourceConfig config = new ResourceConfig(TaeyeonResource.class);
        final URI uri = URI.create(BASE_URI);
        server = GrizzlyHttpServerFactory.createHttpServer(uri, config);
        Client client = ClientBuilder.newClient();
        target = client.target(BASE_URI).path("taeyeons");
        try {
            server.start();
        } catch (IOException e) {
            logger.error(e.toString());
            e.printStackTrace();
        }
    }

    @After
    public void shutDown() {
        server.shutdown();
    }

    @Test
    public void testGetAll() {
        logger.error("start->" + target.getUri().toString());
        final Taeyeons taeyeons = target.request().get(Taeyeons.class);
        logger.error(taeyeons.getTaeyeons());
        Assert.assertNotNull(taeyeons);
        logger.error("->end");
    }

//    @Test
//    public void testGetPhoto() throws IOException {
//        final File file = target.path("taeyeon").path("photos").path("latest").request().get(File.class);
//        logger.debug(file.getName());
//        StringBuilder stringBuilder = new StringBuilder();
//        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
//        String s;
//        do {
//            s = bufferedReader.readLine();
//            stringBuilder.append(s);
//        }while (s!=null);
//
//        logger.debug(stringBuilder.toString());
//
//    }
    @Test
    public void testGetLatest() {
        logger.error("start->" + target.path("taeyeon").path("latest").getUri().toString());
        Taeyeon taeyeon = target.path("taeyeon").path("latest").request().get(Taeyeon.class);
        Assert.assertNotNull(taeyeon);
    }
    @Test
    public void testInfo() {
        logger.debug(target.getUri().getPath());
    }

}
