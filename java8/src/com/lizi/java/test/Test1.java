package com.lizi.java.test;

import com.lizi.java.pojo.Employee;
import com.lizi.java.service.MyPredicate;
import com.lizi.java.service.impl.FilterEmployeeByAge;

import java.util.*;

public class Test1 {
    public final static List<Employee> employees = Arrays.asList(
            new Employee("zhang",18,2222.22),
            new Employee("li",25,3333.33),
            new Employee("wang",45,4444.44),
            new Employee("tian",36,5555.55),
            new Employee("he",27,6666.66)
    );

    public void test1(){
        Comparator<Integer> integerComparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1,o2);
            }
        };

        TreeSet<Integer> integers = new TreeSet<>(integerComparator);

        // Lambda 表达式
        Comparator<Integer> com = (x,y) -> Integer.compare(x,y);
        TreeSet<Integer> integers1 = new TreeSet<>(com);
    }

    public void test2(){
        List<Employee> employeeList = filterEmployee(employees,new FilterEmployeeByAge());
        employeeList.forEach(employee -> {
            System.out.println(employee.getName() +"+"+ employee.getAge() +"+"+ employee.getPrice());
        });
    }

    // 策略设计模式
    public List<Employee> filterEmployee(List<Employee> emp, MyPredicate<Employee> myPredicate){
        List<Employee> employeeList = new ArrayList<Employee>();

        for (Employee employee:emp) {
            if(myPredicate.test(employee))
                employeeList.add(employee);
        }
        return employeeList;
    }

    // 匿名内部类
    public void test3(){
        List<Employee> emps = filterEmployee(employees, new MyPredicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getPrice() > 5000;
            }
        });
        Iterator<Employee> iterator = emps.listIterator();
        while(iterator.hasNext()){
            Employee employee = iterator.next();
            System.out.println("名字："+ employee.getName() + "年龄：" + employee.getAge() + "工资：" + employee.getPrice());
        }
    }

    // Lambda 表达式
    public void test4(){
        List<Employee> list = filterEmployee(employees,(e) -> e.getAge() <= 18);
        list.forEach( e ->{
            System.out.println(e.getName()+"--"+e.getAge()+"--"+e.getPrice());
        });
    }

    // stream API
    public void test5(){
        // 输出price小于等于三千的employee
        employees.stream().filter((e) -> e.getPrice() >= 3000).forEach(i -> {
            System.out.println("name:["+i.getName()+"],age:["+i.getAge()+"],price:["+i.getPrice()+"]");
        });
        // 输出所有名字
        employees.stream().map(Employee::getName).forEach(System.out::println);
    }

    public static void main(String[] args) {
        Test1 test1 = new Test1();
        // test1.test2();
        // test1.test4();
        test1.test5();
    }
}
