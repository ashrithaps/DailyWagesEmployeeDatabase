package com.gudra.app;

/**
 * Created by Ashritha on 10/28/2016.
 */
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "employee")
public class Emp {
    @Id
    private String Name;
    private int Salary;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }



    public int getSalary() {
        return Salary;
    }

    public void setSalary(int salary) {
        Salary = salary;
    }


}
