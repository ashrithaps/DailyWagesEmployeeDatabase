package com.gudra.app;

/**
 * Created by Ashritha on 10/28/2016.
 */
public class DailyRecords {
    public int getWage() {
        return wage;
    }

    public void setWage(int wage) {
        this.wage = wage;
    }

    public int getWithdrawal() {
        return withdrawal;
    }

    public void setWithdrawal(int withdrawal) {
        this.withdrawal = withdrawal;
    }

    private int wage;
    private int withdrawal;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    private  String note;
}
