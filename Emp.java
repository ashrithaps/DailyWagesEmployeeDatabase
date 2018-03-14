package com.gudra.app;

/**
 * Created by Ashritha on 10/28/2016.
 * Employee class containing two member variables Name and Salary
 * It is annotated using JPA hibernate annotations where Name is ID or Primary key for table employee
 */
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table (name = "employee")
@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class Emp {
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    @Id

    @Column(name = "Name", nullable = false)
    private String Name;

    public int getSalary() {
        return Salary;
    }

    public void setSalary(int salary) {
        this.Salary = salary;
    }

    private int Salary;
    private String Address;
    private long ContactNo;
   /* @Lob
    private byte[] image;

    public File getImageFile() {
        return imageFile;
    }

    private File imageFile;
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }*/


    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        this.Address = address;
    }

    public long getContactNo() {
        return ContactNo;
    }

    public void setContactNo(long contactNo) {
        this.ContactNo = contactNo;
    }

}
