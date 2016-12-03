package com.gudra.app.main;

import com.gudra.app.Emp;
import com.gudra.app.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by Ashritha on 11/26/2016.
 */
public class EmployeeDAO {
    public void saveEmployeeDetails(Emp employeeDetails){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Emp employee1 =new Emp();
        employee1.setName(employeeDetails.getName());
        employee1.setSalary(employeeDetails.getSalary());
        session.persist(employee1);
        session.getTransaction().commit();
        session.close();
    }

    public List<Emp> listAllEmployees(){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Emp> employees=session.createQuery("from Emp").list();
        session.getTransaction().commit();
        session.close();
        return employees;
    }

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

    public void deleteSelectedEmployee(List<Emp> empList){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        for(int i=0;i<empList.size();i++) {
            Emp employee = (Emp) session.get(Emp.class, empList.get(i).getName());
            session.delete(employee);
        }
        session.getTransaction().commit();
        session.close();
    }

    public void updateEmployeeDetails(Emp employee) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Emp emp= (Emp)session.get(Emp.class, employee.getName());
        emp.setSalary(400);
        session.update(employee);
        session.getTransaction().commit();
        session.close();
    }
}
