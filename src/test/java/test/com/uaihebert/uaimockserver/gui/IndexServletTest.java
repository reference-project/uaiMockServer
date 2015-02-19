package test.com.uaihebert.uaimockserver.gui;

import org.junit.Test;
import test.com.uaihebert.uaimockserver.TestAbstract;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

public class IndexServletTest extends TestAbstract {

    @Test
    public void isReturning200OnIndex() {
        final String url = "http://localhost:1234/uai-mock-server-gui/index";

        Client client = ClientBuilder.newClient();
        Response response = client.target(url).request().get();

        assertEquals(200, response.getStatus());
    }

    @Test
    public void isReturningContentAsTextHtmlType() {
        final String url = "http://localhost:1234/uai-mock-server-gui/index";

        Client client = ClientBuilder.newClient();
        Response response = client.target(url).request().get();

        assertEquals(MediaType.TEXT_HTML, response.getMediaType().toString());
    }
}