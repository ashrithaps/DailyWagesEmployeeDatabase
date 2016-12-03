package com.gudra.app.main;

import com.gudra.app.DailyRecords;
import com.gudra.app.Emp;
import com.gudra.app.ExtraWage;
import com.gudra.app.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.Date;
import java.util.List;

/**
 * Created by Ashritha on 12/1/2016.
 */
public class ExtraWageDAO {
    public void saveExtraWageDetailsForEachEmployee(ExtraWage extraWage) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Emp emp = (Emp) session.get(Emp.class,extraWage.getEmployee().getName());
        ExtraWage extraWage1 = new ExtraWage();
        extraWage1.setAmount(extraWage.getAmount());
        extraWage1.setNote(extraWage.getNote());
        extraWage1.setTodaysDate(new Date(new java.util.Date().getTime()));
        extraWage1.setEmployee(emp);
        session.persist(extraWage1);
        session.getTransaction().commit();
        session.close();
    }

    public List<ExtraWage> getExtraWageDetailsForEachEmployee(String empName) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ExtraWage> extraWageList = (List<ExtraWage>) session.createQuery("from ExtraWage where Emp_Name = '"+empName+"'").list();
        session.getTransaction().commit();
        session.close();
        return extraWageList;
    }

    public void updateExtraWageDetailForSpecifiedEmployee(String empName, Date dateOnWhichRecordWasCreated, ExtraWage wage) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        ExtraWage extraWage1 = (ExtraWage) session.createQuery("from ExtraWage where Emp_Name = '"+empName+"' and todaysDate='"+dateOnWhichRecordWasCreated+"'").uniqueResult();
        String note = wage.getNote();
        if(note !=null)
             extraWage1.setNote(note);
        int amount = wage.getAmount();
        if(amount !=0)
            extraWage1.setAmount(amount);
        session.getTransaction().commit();
        session.close();
    }

    public float getTotalExtraWageForSpecifiedEmp(String empName) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ExtraWage> extraWageList = (List<ExtraWage>) session.createQuery("from ExtraWage where Emp_Name = '"+empName+"'").list();
        float totalWageAmount = 0;
        for(int i=0;i<extraWageList.size();i++)
        {
            totalWageAmount += extraWageList.get(i).getAmount();
        }
        session.getTransaction().commit();
        session.close();
        return totalWageAmount;
    }
}
