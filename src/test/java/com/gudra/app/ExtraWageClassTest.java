package com.gudra.app;

import com.gudra.app.util.HibernateUtil;
import junit.framework.Assert;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

/**
 * Created by Ashritha on 11/18/2016.
 */
public class ExtraWageClassTest {

    SessionFactory sessionFactory;
    private Session session;
    private  Date todaysDate = new Date();

    @Before
    public void beginHibernateTransaction(){
        System.out.print("Inside beginHibernateTransaction\n ");
        sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();
        saveEmployeeDetailsTest();
    }


    public void saveEmployeeDetailsTest(){
        Emp employee1 =new Emp();
        employee1.setName("Sesu");
        employee1.setSalary(500);
        session.persist(employee1);
        ExtraWage extraWage =  new ExtraWage();
        extraWage.setEmployee(employee1);
        extraWage.setAmount(300);
        extraWage.setTodaysDate(todaysDate);
        extraWage.setNote("Arecanut 5 kg");
        session.persist(extraWage);
    }

    @Test
    public void saveExtraWageDetailsForEachEmpTest() {
        System.out.print("Inside saveDailyRecordsDetailsForEachEmpTest\n");
        ExtraWage extraWage = (ExtraWage) session.get(ExtraWage.class,todaysDate);
        session.getTransaction().commit();

        Assert.assertEquals("Sesu", extraWage.getEmployee().getName());
    }

    @Test
    public void updateDailyRecordsDetailsForEachEmpTest(){

        ExtraWage extraWage = (ExtraWage) session.get(ExtraWage.class,todaysDate);
        extraWage.setAmount(800);
        extraWage.setNote("Arecanut + coconut Peeling");

        session.update(extraWage);
        session.getTransaction().commit();

        Assert.assertEquals(800, extraWage.getAmount());
    }

    @Test
    public void deleteEmpExtraWageTest(){
        ExtraWage extraWage = (ExtraWage) session.get(ExtraWage.class,todaysDate);
        session.delete(extraWage);
        session.getTransaction().commit();
    }
    @After
    public void clearSession(){
        session.flush();
        session.close();

    }
}
