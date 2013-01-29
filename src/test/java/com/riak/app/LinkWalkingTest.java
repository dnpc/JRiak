package com.riak.app;

import com.basho.riak.client.IRiakObject;
import com.basho.riak.client.RiakException;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.riak.app.dao.EmployeeDAO;
import com.riak.app.entity.Employee;
import com.riak.app.service.EmployeeService;
import com.riak.app.service.RiakService;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Collection;
import java.util.Iterator;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

public class LinkWalkingTest {

    private static RiakService employeeService;
    private static Injector injector;

    private static final String EMP1_ID = "Emp_1000";
    private static final String EMP2_ID = "Emp_1001";
    private static final String EMP3_ID = "Emp_1002";

    private static final String TAG = "Supervises";


    @BeforeClass
    public static void init() {
        injector = Guice.createInjector(new GuiceModule());
        employeeService = injector.getInstance(EmployeeService.class);
    }

    @Test
    public void testCreateBucket(){
        Exception ex = null;
        try{
            employeeService.createBucket(EmployeeDAO.BUCKET_NAME);
        }catch(RiakException re){
            ex = re;
        }
        assertNull(ex);
    }

    private void createData () throws RiakException{
        Employee employee = Employee.getNewInstance(EMP1_ID, "John");
        employeeService.update(EMP1_ID, employee);
        employee = Employee.getNewInstance(EMP2_ID, "Bob");
        employeeService.update(EMP2_ID, employee);
        employee = Employee.getNewInstance(EMP3_ID, "James");
        employeeService.update(EMP3_ID, employee);
    }

    private void linkEmployees() throws RiakException{
        IRiakObject employeeObject = employeeService.getRiakObject(EMP1_ID);
        ((EmployeeService)employeeService).addLink(employeeObject, TAG, EMP2_ID);
        ((EmployeeService)employeeService).addLink(employeeObject, TAG, EMP3_ID);
         employeeService.updateRiakObject(employeeObject);
    }

    /**
     * This test lists all the employees Supervised by 'John'
     * @throws RiakException
     */
    @Test
    public void testAddedLinks() throws RiakException{
        createData();
        linkEmployees();
        Iterator<Collection<IRiakObject>> linksIterator = ((EmployeeService)employeeService).getLinksForTag(EMP1_ID, TAG);
        while(linksIterator.hasNext()){
            Collection<IRiakObject> links = linksIterator.next();
            for (IRiakObject link : links){
                assertNotNull(link);
                System.out.println("Link "+link.getValueAsString());
            }
        }
    }
}
