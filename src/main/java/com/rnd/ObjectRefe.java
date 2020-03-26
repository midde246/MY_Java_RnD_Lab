/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rnd;

import java.util.ArrayList;
import java.util.List;
import model.Employee;

/**
 *
 * @author saradam
 */
public class ObjectRefe {
    public static void main(String[] args) {
        Employee emp = null;
        List<Employee> empList = new ArrayList<>();
        for(int i =1 ; i<= 5; i++){
            emp = new Employee();
            emp.setName("Name"+i);
            emp.setAge(""+(25+i));
            emp.setSalary(""+200+i);
            empList.add(emp);
        }
        System.out.println("---------"+empList);
        emp = new Employee();
        emp.setName("Anup Ghosh");
        emp.setAge("56");
        emp.setSalary("258964585");
        System.out.println("---------"+empList);

            
        for(Employee employee : empList){
            System.out.println("Name : "+employee.getName());
            System.out.println("Age : "+employee.getAge());
            System.out.println("Salary : "+employee.getSalary());
            System.out.println("================================");
        }
       
    }
}
