package com.gudra.app;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Ashritha on 10/28/2016.
 */
@Entity
@Table(name="dailyrecords")
public class DailyRecords {
    @Id
    @Column(name = "Date")
    private Date TodaysDate;
    private int Wage;
    private int Withdrawal;
    private  String Note;

    @OneToOne
    @JoinColumn(name = "Employee_Name")
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


}
