package com.gudra.app.main;

import com.gudra.app.DailyRecords;
import com.gudra.app.Emp;
import com.gudra.app.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.sql.Date;

/**
 * Created by Ashritha on 11/28/2016.
 */
public class DailyRecordsDAO {
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
        session.persist(dailyRec);
       // java.io.Serializable todaysDate = new Date(new java.util.Date().getTime());
      //  DailyRecords dailyRecord = (DailyRecords) session.createCriteria(DailyRecords.class).add(Restrictions.eq("Employee_Name",empName)).add(Restrictions.eq("TodaysDate",todaysDate));
        session.getTransaction().commit();
        session.close();
    }
}
