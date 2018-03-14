package com.gudra.app.main;

import com.gudra.app.Emp;
import com.gudra.app.ExtraWage;
import com.gudra.app.Reports;
import com.gudra.app.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.Date;
import java.util.List;

/**
 * Created by Ashritha on 12/4/2016.
 */
public class ReportsDAO {
    public void saveOrUpdateReportForSpecifiedEmp(String empName, Date date, float totalWage, float totalExtraWageAmt, float totalWithdrawal) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        float totalEarnings =totalWage +totalExtraWageAmt;
        float totalBalance = totalEarnings - totalWithdrawal;
        Reports reports =  new Reports();
        reports.setTotal_earnings(totalEarnings);
        reports.setTotal_balance(totalBalance);
        reports.setTotal_withdrawal(totalWithdrawal);
        reports.setDate(date);
        Emp emp = (Emp) session.get(Emp.class,empName);
        reports.setEmployee(emp);
        Reports reportOfAnEmp = (Reports) session.createQuery("from Reports where empName = '"+ empName+"' and date= '"+date+"'").uniqueResult();
        if(reportOfAnEmp!=null) {
            reportOfAnEmp.setTotal_balance(totalBalance);
            reportOfAnEmp.setTotal_earnings(totalEarnings);
            reportOfAnEmp.setTotal_withdrawal(totalWithdrawal);
            session.update(reportOfAnEmp);
        }
        else
            session.persist(reports);
        session.getTransaction().commit();
        session.close();
    }

    public float getTotalEarningsForSpecifiedEmp(String empName,Date date) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Reports totalEarningsReport = (Reports) session.createQuery("from Reports where empName = '"+ empName+"' and date= '"+date+"'").uniqueResult();
        float totalEarnings = totalEarningsReport.getTotal_earnings();
        session.close();
        return totalEarnings;
    }

    public float getTotalBalanceForSpecifiedEmp(String empName, Date date) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Reports totalEarningsReport = (Reports) session.createQuery("from Reports where empName = '"+ empName+"' and date = '"+date+"'").list();
        float totalBalance = totalEarningsReport.getTotal_balance();
        session.close();
        return totalBalance;
    }

    public  List<Reports> getReportOfAnEmployee(String empName){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Reports> reportsOfAnEmployee= (List<Reports>) session.createQuery("from Reports where empName = '"+ empName+"'").list();
        session.close();
        return reportsOfAnEmployee;
    }
}
