package com.riak.app.service;

import com.basho.riak.client.IRiakObject;
import com.basho.riak.client.RiakException;
import com.riak.app.dao.RiakDAO;
import com.riak.app.entity.BaseEntity;

public abstract class BaseRiakService<T extends BaseEntity> implements RiakService<T> {

    private final RiakDAO<T> riakDAO;

    public BaseRiakService(RiakDAO<T> riakDAO){
        this.riakDAO = riakDAO;
    }

    public String getValueAsString(String key) throws RiakException{
        return riakDAO.getValueAsString(key);
    }

    public T getValueAsPOJO (String key) throws RiakException{
        return riakDAO.getValueAsPOJO(key);
    }


    public void update(String key, T object) throws RiakException {
        riakDAO.update(key, object);
    }

    public void delete(String key) throws RiakException {
        riakDAO.delete(key);
    }

    public void createBucket(String bucketName) throws RiakException{
        riakDAO.createBucket(bucketName);
    }

    public IRiakObject getRiakObject (String key) throws RiakException{
        return riakDAO.getRiakObject(key);
    }

    public void updateRiakObject (IRiakObject riakObject) throws RiakException{
        riakDAO.updateRiakObject(riakObject);
    }

    public RiakDAO<T> getRiakDAO() {
        return riakDAO;
    }
}
