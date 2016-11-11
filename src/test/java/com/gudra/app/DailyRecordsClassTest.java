package com.gudra.app;

import com.gudra.app.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Ashritha on 11/10/2016.
 */
public class DailyRecordsClassTest {
    @Test
    public void saveDailyRecordsDetailsForEachEmpTest(){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();


       deleteEmployeeTest();
       DailyRecords record = new DailyRecords();
        Emp employee = new Emp();
        employee.setName("Ananda");
        employee.setSalary(600);
        record.setTodaysDate(new Date());
        record.setWage(700);
        record.setEmployee(employee);
        record.setWithdrawal(600);
        record.setNote("salary");

        session.save(record);
        session.getTransaction().commit();

        session.close();

    }

    @Test
    public void updateDailyRecordsDetailsForEachEmpTest(){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        DailyRecords record = (DailyRecords) session.createQuery("from DailyRecords where TodaysDate='2016-11-11' and employee='Ananda'").uniqueResult();
        record.setWithdrawal(800);
        record.setNote("salary");

        session.update(record);
        session.getTransaction().commit();

        session.close();

    }

    @Test
    public void deleteEmployeeTest(){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();


        List<DailyRecords> recordsList = new ArrayList<DailyRecords>();
        recordsList = (List<DailyRecords>) session.createQuery("from DailyRecords where TodaysDate='2016-11-11' and employee='Ananda'").list();
        if(recordsList.size()>0) {
            for (int i = 0; i < recordsList.size(); i++) {
                Session deleteSession = sessionFactory.openSession();
                deleteSession.beginTransaction();
                DailyRecords dailyRecord = recordsList.get(i);
                deleteSession.delete(dailyRecord);
                deleteSession.getTransaction().commit();
                deleteSession.close();
            }
        }
        session.getTransaction().commit();

        session.close();


    }
}
