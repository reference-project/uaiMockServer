package test.com.uaihebert.uaimockserver.service;

import com.uaihebert.uaimockserver.runner.UaiMockServerRunner;
import com.uaihebert.uaimockserver.runner.UaiRunnerMockServerConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

@RunWith(UaiMockServerRunner.class)
@UaiRunnerMockServerConfiguration(configurationFile = "otherContextTest.json")
public class OtherContextTest {

    @Test
    public void isInvokingOtherContext() {
        final String url = "http://localhost:1234/otherContext/otherContext";

        Client client = ClientBuilder.newClient();
        Response response = client.target(url).request().get();

        assertEquals(204, response.getStatus());
    }
}