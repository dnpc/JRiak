package com.riak.app.entity;


import com.basho.riak.client.DefaultRiakObject;
import com.basho.riak.client.RiakLink;
import com.basho.riak.client.cap.VClock;
import com.basho.riak.client.query.indexes.RiakIndexes;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

public class Employee implements BaseEntity{

    private String empId;
    private String empName;

    public Employee(){

    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (empId != null ? !empId.equals(employee.empId) : employee.empId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return empId != null ? empId.hashCode() : 0;
    }

    public static Employee getNewInstance(String empId, String empName){
        Employee employee = new Employee();
        employee.setEmpId(empId);
        employee.setEmpName(empName);
        return employee;
    }
}
