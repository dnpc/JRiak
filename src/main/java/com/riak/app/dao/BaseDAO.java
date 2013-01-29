package com.riak.app.dao;


import com.basho.riak.client.IRiakClient;
import com.basho.riak.client.IRiakObject;
import com.basho.riak.client.RiakException;
import com.basho.riak.client.bucket.Bucket;
import com.riak.app.entity.BaseEntity;
import com.riak.app.util.RiakClientFactory;

public abstract class BaseDAO<T extends BaseEntity> implements RiakDAO<T>{

    protected  abstract Bucket getBucket(IRiakClient iRiakClient) throws RiakException;
    protected abstract Class<T> getClassType();

    public String getValueAsString(String key) throws RiakException {
        IRiakClient iRiakClient = RiakClientFactory.getDefaultHttpClient();
        Bucket bucket = getBucket(iRiakClient);
        IRiakObject iRiakObject = bucket.fetch(key).execute();
        iRiakClient.shutdown();
        return iRiakObject.getValueAsString();
    }

    public T getValueAsPOJO (String key) throws RiakException{
        IRiakClient iRiakClient = RiakClientFactory.getHttpClusterClient();
        Bucket bucket = getBucket(iRiakClient);
        T baseEntity = bucket.fetch(key, getClassType()).execute();
        iRiakClient.shutdown();
        return baseEntity;
    }


    public void update(String key, T baseEntity) throws RiakException{
        IRiakClient iRiakClient = RiakClientFactory.getHttpClusterClient();
        Bucket bucket = getBucket(iRiakClient);
        bucket.store(key, baseEntity).execute();
        iRiakClient.shutdown();
    }

    public void delete(String key) throws RiakException{
        IRiakClient iRiakClient = RiakClientFactory.getHttpClusterClient();
        Bucket bucket = getBucket(iRiakClient);
        bucket.delete(key).execute();
        iRiakClient.shutdown();
    }

    /**
     * nVal is the number of replicas that will be written of each object in the bucket.
     * r is the number of nodes to consult to return a succcessful read.
     * @param bucketName
     * @throws RiakException
     */
    public void createBucket(String bucketName) throws RiakException{
        IRiakClient iRiakClient = RiakClientFactory.getHttpClusterClient();
        iRiakClient.createBucket(bucketName).nVal(2).r(2).execute();
        iRiakClient.shutdown();
    }

    public IRiakObject getRiakObject (String key) throws RiakException{
        IRiakClient iRiakClient = RiakClientFactory.getHttpClusterClient();
        Bucket bucket = getBucket(iRiakClient);
        IRiakObject riakObject = bucket.fetch(key).execute();
        return riakObject;
    }

    public void updateRiakObject (IRiakObject riakObject) throws RiakException{
        IRiakClient iRiakClient = RiakClientFactory.getHttpClusterClient();
        Bucket bucket = getBucket(iRiakClient);
        bucket.store(riakObject).execute();
        iRiakClient.shutdown();
    }
}
