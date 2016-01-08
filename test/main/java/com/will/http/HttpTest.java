package com.will.http;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by yangwi on 1/8/2016.
 */
public class HttpTest {
    Server server;
    @Test
    public void createHttpServer() {
        Http http = new Http();
        server = http.createServer();
        Assert.assertNotNull(server);
    }

    @Test
    public void canGetResFromServer() {
        Http http = new Http();

    }

    private HttpOptions genHttpOptions() {
        String host = "localhost";
        int port = 80;
        String protocol = "http";
        String method = "GET";
        HttpOptions httpOptions = new HttpOptions();
        httpOptions.setHost(host);
        httpOptions.setPort(port);
        httpOptions.setProtocol(protocol);
        httpOptions.setMethod(method);
        return httpOptions;
    }


}
