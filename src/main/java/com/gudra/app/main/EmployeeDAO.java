package com.gudra.app.main;

import com.gudra.app.Emp;
import com.gudra.app.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Set;

/**
 * Created by Ashritha on 11/26/2016.
 * Perform CRUD operations on Employee entity
 */
public class EmployeeDAO {
    /**
    *save employee details
    * @param employeeDetails (name and salary)
     */
    public void saveEmployeeDetails(Emp employeeDetails){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Emp employee1 =new Emp();
        employee1.setName(employeeDetails.getName());
        employee1.setSalary(employeeDetails.getSalary());
        employee1.setAddress(employeeDetails.getAddress());
        employee1.setContactNo(employeeDetails.getContactNo());
        session.persist(employee1);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * list all employees
     * return list of all employees
     */

    public List<Emp> listAllEmployees(){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Emp> employees=session.createQuery("from Emp").list();
        session.getTransaction().commit();
        session.close();
        return employees;
    }

    /**
     * delete all employees from the database
     */
    public void deleteAllEmployees(){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Emp> employees=session.createQuery("from Emp").list();
        for(int i=0;i<employees.size();i++)
            session.delete(employees.get(i));
        session.getTransaction().commit();
        session.close();
    }

    /**
     * delete selected employee
     * @param empNames
     */
    public String deleteSelectedEmployee(String empNames){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String message ="";
        for(String empName: empNames.split(",")){
            Emp employee = (Emp) session.get(Emp.class, empName);
            if(employee!=null) {
                session.delete(employee);
                message = "Employee deleted successfully";
            }
            else
                message = "Employee does not exists";
        }
        session.getTransaction().commit();
        session.close();
        return message ;
    }

    /**
     * update employee details
     * @param employeeName
     * @param employee
     */
    public void updateEmployeeDetails(String employeeName, Emp employee) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Emp emp= (Emp)session.get(Emp.class, employeeName);
        emp.setSalary(employee.getSalary());
        emp.setAddress(employee.getAddress());
        emp.setContactNo(employee.getContactNo());
        session.update(emp);
        session.getTransaction().commit();
        session.close();
    }
}
