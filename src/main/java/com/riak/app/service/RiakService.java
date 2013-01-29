package com.riak.app.service;


import com.basho.riak.client.IRiakObject;
import com.basho.riak.client.RiakException;
import com.riak.app.entity.GoogleStock;

public interface RiakService<T> {

    public String getValueAsString (String key) throws RiakException;

    public T getValueAsPOJO (String key) throws RiakException;

    public void update(String key, T object) throws RiakException;

    public void delete(String key) throws RiakException;

    public void createBucket(String bucketName) throws RiakException;

    public IRiakObject getRiakObject (String key) throws RiakException;

    public void updateRiakObject (IRiakObject riakObject) throws RiakException;
}
