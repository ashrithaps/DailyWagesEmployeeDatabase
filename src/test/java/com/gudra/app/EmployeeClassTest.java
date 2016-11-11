package com.gudra.app;

import com.gudra.app.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by Ashritha on 11/9/2016.
 */
public class EmployeeClassTest {
   @Test
    public void saveEmployeeDetailsTest(){
       SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
       Session session = sessionFactory.openSession();
       session.beginTransaction();

       Emp employee =new Emp();
       employee.setName("Sesu");
       employee.setSalary(500);
       session.save(employee);
       session.getTransaction().commit();

       session.close();

   }

   @Test
   public void listAllEmployeesTest(){
      SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
      Session session = sessionFactory.openSession();
      session.beginTransaction();

      List<Emp> employees=session.createQuery("from Emp").list();
      session.getTransaction().commit();

      session.close();
      Assert.assertEquals(3, employees.size());
   }

   @Test
   public void getEmployeeDetailsTest(){
      SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
      Session session = sessionFactory.openSession();
      session.beginTransaction();

      Emp empDetails = (Emp) session.get(Emp.class, "Sesu");
      session.getTransaction().commit();

      session.close();
      Assert.assertEquals(500, empDetails.getSalary());
   }

   @Test
   public void updateEmployeeDetailsTest(){
      SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
      Session session = sessionFactory.openSession();
      session.beginTransaction();


      Emp employee= (Emp)session.createQuery("from Emp where Name='Asha'").uniqueResult();
      employee.setSalary(400);
      session.update(employee);
      session.getTransaction().commit();

      session.close();
   }

   @Test
   public void deleteEmployeeTest(){
      SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
      Session session = sessionFactory.openSession();
      session.beginTransaction();


      Emp employee= (Emp)session.createQuery("from Emp where Name='Sesu'").uniqueResult();
      session.delete(employee);
      session.getTransaction().commit();

      session.close();
   }
}
