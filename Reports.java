package com.gudra.app;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Ashritha on 10/28/2016.
 */
@Entity
@Table(name="reports")
public class Reports {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private float total_balance;
    private float total_earnings;
    private float total_withdrawal;
   // private float loan_taken;
    private Date date;

    @OneToOne(cascade = javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "empName",nullable = false)
    private Emp employee;

    public float getTotal_balance() {
        return total_balance;
    }

    public void setTotal_balance(float total_balance) {
        this.total_balance = total_balance;
    }

    public float getTotal_earnings() {
        return total_earnings;
    }

    public void setTotal_earnings(float total_earnings) {
        this.total_earnings = total_earnings;
    }

    public float getTotal_withdrawal() {
        return total_withdrawal;
    }

    public void setTotal_withdrawal(float total_withdrawal) {
        this.total_withdrawal = total_withdrawal;
    }

   /* public float getLoan_taken() {
        return loan_taken;
    }

    public void setLoan_taken(float loan_taken) {
        this.loan_taken = loan_taken;
    }*/

    public Emp getEmployee() {
        return employee;
    }

    public void setEmployee(Emp employee) {
        this.employee = employee;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
