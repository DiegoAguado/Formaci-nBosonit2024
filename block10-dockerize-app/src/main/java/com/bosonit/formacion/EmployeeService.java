package com.bosonit.formacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeService {
    @Autowired
    EmployeeDao employeeDao;
    public List<Employee> findAll(){
        return employeeDao.findAll();
    }
    public void insertEmployee(Employee emp){
        employeeDao.insertEmployee(emp);
    }

    public void updateEmployee(Employee emp){
        employeeDao.updateEmployee(emp);
    }

    public void executeUpdateEmployee(Employee emp){
        employeeDao.executeUpdateEmployee(emp);
    }

    public void deleteEmployee(Employee emp){
        employeeDao.deleteEmployee(emp);
    }
}
