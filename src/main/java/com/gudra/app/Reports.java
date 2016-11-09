package com.gudra.app;

/**
 * Created by Ashritha on 10/28/2016.
 */
public class Reports {
    public int getTotal_balance() {
        return total_balance;
    }

    public void setTotal_balance(int total_balance) {
        this.total_balance = total_balance;
    }

    public int getEarnings() {
        return earnings;
    }

    public void setEarnings(int earnings) {
        this.earnings = earnings;
    }

    public int getWithdrawal() {
        return withdrawal;
    }

    public void setWithdrawal(int withdrawal) {
        this.withdrawal = withdrawal;
    }

    public String getLoan_report_of_all_employees() {
        return loan_report_of_all_employees;
    }

    public void setLoan_report_of_all_employees(String loan_report_of_all_employees) {
        this.loan_report_of_all_employees = loan_report_of_all_employees;
    }

    private int total_balance;
    private int earnings;
    private int withdrawal;
    private String loan_report_of_all_employees;
}
