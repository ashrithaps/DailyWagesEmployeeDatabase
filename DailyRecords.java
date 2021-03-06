package com.gudra.app;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Ashritha on 10/28/2016.
 * Daily Record of an employee containing member variables such as id, date,wage,withdrawal and note with hibernate annotations
 * table daily records has an auto generated id and employee name as foreign key
 *
 */
@Entity
@Table(name="dailyrecords")
public class DailyRecords {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    @Column(name = "Date")
    private Date TodaysDate;
    private int Wage;
    private int Withdrawal;
    private  String Note;

    @OneToOne (cascade = javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "Employee_Name",nullable = false)
    private Emp employee;

    public Date getTodaysDate() {
        return TodaysDate;
    }

    public void setTodaysDate(Date todaysDate) {
        TodaysDate = todaysDate;
    }




    public Emp getEmployee() {
        return employee;
    }

    public void setEmployee(Emp employee_Name) {
        employee = employee_Name;
    }



    public int getWage() {
        return Wage;
    }

    public void setWage(int wage) {
        this.Wage = wage;
    }

    public int getWithdrawal() {
        return Withdrawal;
    }

    public void setWithdrawal(int withdrawal) {
        this.Withdrawal = withdrawal;
    }



    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        this.Note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



}
