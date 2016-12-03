package com.gudra.app;

import com.gudra.app.util.HibernateUtil;
import junit.framework.Assert;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.*;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

/**
 * Created by Ashritha on 11/10/2016.
 */
public class DailyRecordsClassTest {
    static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private static Session session;
    private static Date todaysDate = new Date(new java.util.Date().getTime());
    @BeforeClass
    public static void createNewHibernateSession(){
        System.out.print("Inside createNewHibernateSession\n ");
        session = sessionFactory.openSession();


    }

    @Before
    public void beginHibernateTransaction(){
        System.out.print("Inside beginHibernateTransaction\n ");
        session.beginTransaction();
        DailyRecords record = new DailyRecords();
        Emp employee = new Emp();
        employee.setName("Ananda");
        employee.setSalary(600);
        session.save(employee);
        record.setTodaysDate(todaysDate);
        record.setWage(700);
        record.setEmployee(employee);
        record.setWithdrawal(600);
        record.setNote("salary");

        session.persist(record);

    }

    @Test
    public void saveDailyRecordsDetailsForEachEmpTest() {
        System.out.print("Inside saveDailyRecordsDetailsForEachEmpTest\n");
        DailyRecords dailyRecord = (DailyRecords) session.get(DailyRecords.class,todaysDate);
         session.getTransaction().commit();

        Assert.assertEquals("Ananda", dailyRecord.getEmployee().getName());
    }

    @Test
    public void updateDailyRecordsDetailsForEachEmpTest(){

        DailyRecords dailyRecord = (DailyRecords) session.get(DailyRecords.class,todaysDate);
        dailyRecord.setWithdrawal(800);
        dailyRecord.setNote("salary");

        session.update(dailyRecord);
        session.getTransaction().commit();

        Assert.assertEquals(800, dailyRecord.getWithdrawal());
    }

    @Test
    public void deleteEmployeeTest(){
        DailyRecords dailyRecord = (DailyRecords) session.get(DailyRecords.class,todaysDate);
        session.delete(dailyRecord);
        session.getTransaction().commit();
    }
    @After
    public void clearSession(){
        session.flush();
        session.clear();

    }


    @AfterClass
    public static void closeHibernateSession(){
        session.close();
    }
}
