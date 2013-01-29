package com.riak.app.dao;

import com.basho.riak.client.IRiakClient;
import com.basho.riak.client.IRiakObject;
import com.basho.riak.client.RiakException;
import com.basho.riak.client.bucket.Bucket;
import com.riak.app.entity.BaseEntity;
import com.riak.app.entity.GoogleStock;
import com.riak.app.util.RiakClientFactory;

public class GoogleStockDAO extends BaseDAO<GoogleStock> {

    private static final String BUCKET_NAME = "goog";

    @Override
    public Bucket getBucket(IRiakClient iRiakClient) throws RiakException{
        return iRiakClient.fetchBucket(BUCKET_NAME).execute();
    }

    @Override
    protected Class<GoogleStock> getClassType() {
        return GoogleStock.class;
    }
}
