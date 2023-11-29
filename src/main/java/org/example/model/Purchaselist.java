package org.example.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "purchaselist")
@IdClass(Purchaselist.class)
public class Purchaselist implements Serializable {
    @Id
    @Column(name = "student_name")
    private String studentName;

    @Id
    @Column(name = "course_name")
    private String courseName;

    @Id
    @Column(name = "price")
    private int price;

    @Id
    @Column(name = "subscription_date")
    private Timestamp subscriptionDate;

    public Purchaselist() {

    }

    public Purchaselist(String studentName, String courseName, int price, Timestamp subscriptionDate) {
        this.studentName = studentName;
        this.courseName = courseName;
        this.price = price;
        this.subscriptionDate = subscriptionDate;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Timestamp getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Timestamp subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    @Override
    public String toString() {
        return "Purchaselist{" +
                "studentName='" + studentName + '\'' +
                ", courseName='" + courseName + '\'' +
                ", price=" + price +
                ", subscriptionDate=" + subscriptionDate +
                '}';
    }
}
