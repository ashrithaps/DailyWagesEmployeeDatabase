package com.gudra.app;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;


/**
 * Created by Ashritha on 10/28/2016.
 * Extra Wage class of  an employee contain fields such as auto generated id, todaysDate, note and amount or extra wage for the work done
 * here employee name is foreign key that references employee table
 */

@Entity
@Table(name="extrawage")
public class ExtraWage {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private Date todaysDate;
    private String note;
    private int amount;
    @OneToOne(cascade = javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "Emp_Name",nullable = false)
    private Emp employee;


    public Emp getEmployee() {
        return employee;
    }

    public void setEmployee(Emp employee) {
        this.employee = employee;
    }


    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getTodaysDate() {
        return todaysDate;
    }

    public void setTodaysDate(Date todaysDate) {
        this.todaysDate = todaysDate;
    }


}
