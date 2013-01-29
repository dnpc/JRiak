package com.riak.app.service;

import com.basho.riak.client.IRiakObject;
import com.basho.riak.client.RiakException;
import com.basho.riak.client.RiakLink;
import com.google.inject.Inject;
import com.riak.app.dao.EmployeeDAO;
import com.riak.app.entity.Employee;

import java.util.Collection;
import java.util.Iterator;

public class EmployeeService extends BaseRiakService<Employee>{

    @Inject
    public EmployeeService(EmployeeDAO employeeDAO) {
        super(employeeDAO);
    }

    public void addLink (IRiakObject employeeObject, String tag, String linkTo) throws RiakException{
        employeeObject.addLink(new RiakLink(employeeObject.getBucket(), linkTo, tag));
    }

    public Iterator<Collection<IRiakObject>> getLinksForTag (String key, String tag) throws RiakException{
        return ((EmployeeDAO)getRiakDAO()).getLinksForTag(key, tag);
    }
}
