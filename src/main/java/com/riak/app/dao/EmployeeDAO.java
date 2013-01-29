package com.riak.app.dao;

import com.basho.riak.client.IRiakClient;
import com.basho.riak.client.IRiakObject;
import com.basho.riak.client.RiakException;
import com.basho.riak.client.bucket.Bucket;
import com.basho.riak.client.query.WalkResult;
import com.riak.app.entity.Employee;
import com.riak.app.util.RiakClientFactory;

import java.util.Collection;
import java.util.Iterator;

public class EmployeeDAO extends BaseDAO<Employee>{

    public static final String BUCKET_NAME = "employee";

    @Override
    protected Bucket getBucket(IRiakClient iRiakClient) throws RiakException {
        return iRiakClient.fetchBucket(BUCKET_NAME).execute();
    }

    @Override
    protected Class<Employee> getClassType() {
        return Employee.class;
    }

    public Iterator<Collection<IRiakObject>> getLinksForTag (String key, String tag) throws RiakException{
        IRiakClient riakClient = RiakClientFactory.getDefaultHttpClient();
        IRiakObject riakObject = getRiakObject(key);
        WalkResult walkResult = riakClient.walk(riakObject).addStep(BUCKET_NAME, tag, true).execute();
        return walkResult.iterator();
    }
}
