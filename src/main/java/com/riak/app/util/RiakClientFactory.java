package com.riak.app.util;


import com.basho.riak.client.IRiakClient;
import com.basho.riak.client.RiakException;
import com.basho.riak.client.RiakFactory;
import com.basho.riak.client.raw.http.HTTPClientConfig;
import com.basho.riak.client.raw.http.HTTPClusterConfig;

public class RiakClientFactory {

    private static final int MAX_CONNECTIONS = 50;
    private static final String HOST1 = "10.80.1.200";
    private static final String HOST2 = "10.80.1.100";
    private static final String DEFAULT_HOST = "http://10.80.1.100:8098/riak";


    /**
     * 127.0.0.1 is not working on my system.
     * @return
     * @throws RiakException
     */
    public static IRiakClient getDefaultHttpClient() throws RiakException{
        return RiakFactory.httpClient(DEFAULT_HOST);
    }

    public static IRiakClient getHttpClient (String url) throws RiakException{
        return RiakFactory.httpClient(url);
    }

    /**
     * By using a cluster client (HTTPClusterClient or PBClusterClient) you would get not only
     * fail-over on an operation failure but also round-robin balancing of
     * operations across your nodes.

     * When you create either a HTTPClusterClient or a PBClusterClient you provide a list of
     * Raik nodes. When performing an operation there's a Retrier object
     * that is used by the Bucket object.
     * The default Retrier re-tries an operation 3 times before reporting a failure.
     * On each try (the initial and each re-try), a different node is selected
     * from the list of Riak nodes.

     * @return
     * @throws RiakException
     */

    public static IRiakClient getHttpClusterClient () throws RiakException{
        HTTPClusterConfig clusterConfig = new HTTPClusterConfig(MAX_CONNECTIONS);
        HTTPClientConfig clientConfig = HTTPClientConfig.defaults();
        clusterConfig.addHosts(clientConfig, HOST1, HOST2);
        return RiakFactory.newClient(clusterConfig);
    }
}
