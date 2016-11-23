package com.gudra.app;

import javax.persistence.*;
import java.util.Date;


/**
 * Created by Ashritha on 10/28/2016.
 */

@Entity
public class ExtraWage {


    @Id
    private Date TodaysDate;
    private String note;
    private int amount;
    @OneToOne(cascade = javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "Emp_Name")
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
        return TodaysDate;
    }

    public void setTodaysDate(Date todaysDate) {
        TodaysDate = todaysDate;
    }


}
