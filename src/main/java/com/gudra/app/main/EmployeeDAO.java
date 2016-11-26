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
    public void saveEmployeeDetailsTest(Emp employeeDetails){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Emp employee1 =new Emp();
        employee1.setName(employeeDetails.getName());
        employee1.setSalary(500);
        session.persist(employee1);
       /* Emp employee2 =new Emp();
        employee2.setName("Asha");
        employee2.setSalary(600);
        session.persist(employee2);*/

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
}
