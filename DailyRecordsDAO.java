package com.gudra.app.main;

import com.gudra.app.DailyRecords;
import com.gudra.app.Emp;
import com.gudra.app.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.Date;
import java.util.List;

/**
 * Created by Ashritha on 11/28/2016.
 * Perform CRUD operations on Daily Records database entity using hibernate
 */
public class DailyRecordsDAO {
    /**
     * save daily record details to database
     * @param dailyRecord
     */
    public void saveDailyRecordsDetailsForEachEmployee(DailyRecords dailyRecord) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Emp emp = (Emp) session.get(Emp.class,dailyRecord.getEmployee().getName());
        DailyRecords dailyRec = new DailyRecords();
        dailyRec.setTodaysDate(new Date(new java.util.Date().getTime()));
        dailyRec.setEmployee(emp);
        dailyRec.setNote(dailyRecord.getNote());
        dailyRec.setWithdrawal(dailyRecord.getWithdrawal());
        dailyRec.setWage(dailyRecord.getWage());

        session.persist(dailyRec);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * get all the daily record details for each employee
     * @param empName
     * @return list of daily records for employee 'empName'
     */
    public List<DailyRecords> getDailyRecordDetailsForEachEmployee(String empName){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<DailyRecords> dailyRecords = (List<DailyRecords>) session.createQuery("from DailyRecords where Employee_Name = '"+empName+"'").list();
        session.getTransaction().commit();
        session.close();
        return dailyRecords;

    }

    /**
     * update daily record details based on date and foreign key employee name
     * @param empName
     * @param dateOnWhichRecordWasCreated
     * @param recordDetails
     */
    public void updateDailyRecordDetailsForEachEmployee(String empName, Date dateOnWhichRecordWasCreated,DailyRecords recordDetails) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        DailyRecords dailyRec = (DailyRecords) session.createQuery("from DailyRecords where Employee_Name = '"+empName+"' and TodaysDate='"+dateOnWhichRecordWasCreated+"'").uniqueResult();
        if(recordDetails.getNote()!="")
            dailyRec.setNote(recordDetails.getNote());
        if(recordDetails.getWithdrawal()!=0)
            dailyRec.setWithdrawal(recordDetails.getWithdrawal());
        if(recordDetails.getWage()!=0)
            dailyRec.setWage(recordDetails.getWage());
        session.update(dailyRec);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * get total wage for specified employee until given date
     * @param empName
     * @param date
     * @return
     */
    public float getTotalWageForSpecifiedEmp(String empName, Date date) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<DailyRecords> dailyRecords = (List<DailyRecords>) session.createQuery("from DailyRecords where Employee_Name = '"+empName+"' and TodaysDate <= '"+date+"'").list();
        float totalWage = 0;
        for(int i=0;i<dailyRecords.size();i++)
        {
            totalWage += dailyRecords.get(i).getWage();
        }
        session.getTransaction().commit();
        session.close();
        return totalWage;
    }

    /**
     * get total withdrawal for specified employee until given date
     * @param empName
     * @param date
     * @return
     */
    public float getTotalWithdrawalForSpecifiedEmployee(String empName, Date date) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<DailyRecords> dailyRecords = (List<DailyRecords>) session.createQuery("from DailyRecords where Employee_Name = '"+empName+"' and TodaysDate <= '"+date+"'").list();
        float totalWithdrawal = 0;
        for(int i=0;i<dailyRecords.size();i++)
        {
            totalWithdrawal += dailyRecords.get(i).getWithdrawal();
        }
        session.getTransaction().commit();
        session.close();
        return totalWithdrawal;
    }
}
