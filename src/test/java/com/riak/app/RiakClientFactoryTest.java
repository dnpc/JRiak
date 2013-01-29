package com.riak.app;


import com.basho.riak.client.IRiakClient;
import com.basho.riak.client.RiakException;
import com.riak.app.util.RiakClientFactory;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class RiakClientFactoryTest {

    private IRiakClient iRiakClient;

    @After
    public void tearDown(){
        if (null != iRiakClient){
            iRiakClient.shutdown();
        }
    }

    @Test
    public void testDefaultHttpClient() throws RiakException{
        iRiakClient = RiakClientFactory.getDefaultHttpClient();
        iRiakClient.ping();
    }

    @Test
    public void testHttpClientForUrl() throws RiakException{
        iRiakClient = RiakClientFactory.getHttpClient("http://10.80.1.100:8098/riak");
        iRiakClient.ping();
    }

    /**
     * No Exception is thrown if the client is able to establish
     * connection to one of the clients.
     * @throws RiakException
     */
    @Test
    public void testHttpClusterClient() throws RiakException{
        iRiakClient = RiakClientFactory.getHttpClusterClient();
        iRiakClient.ping();
    }
}
