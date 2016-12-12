package com.gudra.app;

/**
 * Created by Ashritha on 10/28/2016.
 * Employee class containing two member variables Name and Salary
 * It is annotated using JPA hibernate annotations where Name is ID or Primary key for table employee
 */
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "employee")
public class Emp {
    @Id
    private String Name;
    private int Salary;
    private String address;
    private long contactNo;
    @Lob
    private byte[] image;

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

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
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getContactNo() {
        return contactNo;
    }

    public void setContactNo(long contactNo) {
        this.contactNo = contactNo;
    }

}
