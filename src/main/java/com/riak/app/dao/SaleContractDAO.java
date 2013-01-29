package com.riak.app.dao;


import com.basho.riak.client.IRiakClient;
import com.basho.riak.client.RiakException;
import com.basho.riak.client.bucket.Bucket;
import com.riak.app.entity.SaleContract;

public class SaleContractDAO extends BaseDAO<SaleContract>{

    public static final String BUCKET_NAME = "saleContract";

    @Override
    protected Bucket getBucket(IRiakClient iRiakClient) throws RiakException {
        return iRiakClient.fetchBucket(BUCKET_NAME).execute();
    }

    @Override
    protected Class<SaleContract> getClassType() {
        return SaleContract.class;
    }
}
