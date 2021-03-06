/*
 * Copyright 2015 uaiHebert
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * */
package test.com.uaihebert.uaimockserver.performance;

import com.uaihebert.uaimockserver.runner.UaiMockServerRunner;
import com.uaihebert.uaimockserver.runner.UaiRunnerMockServerConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(UaiMockServerRunner.class)
@UaiRunnerMockServerConfiguration(configurationFile = "performanceTest.json")
public class PerformanceTest {

    @Test
    public void isWithGoodPerformance() {
        // just a metric to make sure that we are not slowing down the processing
        // this value will change after developers start giving feedback about the performance
        // this test might break if the pc is processing other stuff
        System.out.println("============>       Starting performance test");

        final long startTime = new Date().getTime();

        for (int i = 0; i < 300; i++) {
            executeGetRequest();
        }

        final long endTime = new Date().getTime();

        final long timeLapse = endTime - startTime;

        assertTrue("making sure that we have a fast response", timeLapse < 10000);
    }

    private void executeGetRequest() {
        final String url = "http://localhost:1234/uaiMockServer/performanceTest";

        Client client = ClientBuilder.newClient();
        Response response = client.target(url).request().get();

        assertEquals(204, response.getStatus());
    }
}
