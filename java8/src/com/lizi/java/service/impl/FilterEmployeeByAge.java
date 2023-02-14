package com.lizi.java.service.impl;

import com.lizi.java.pojo.Employee;
import com.lizi.java.service.MyPredicate;

public class FilterEmployeeByAge implements MyPredicate<Employee> {

    @Override
    public boolean test(Employee employee) {
        return employee.getAge() >= 35;
    }
}
