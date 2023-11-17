package org.example;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "subscriptions")
public class Subscriptions {
    @ManyToOne(cascade = CascadeType.ALL)
    private Students studentId;

    @ManyToOne(cascade = CascadeType.ALL)
    private Courses courseId;

    @Column(name = "subscription_date")
    private Timestamp subscriptionDate;

    public Students getStudentId() {
        return studentId;
    }

    public void setStudentId(Students studentId) {
        this.studentId = studentId;
    }

    public Courses getCourseId() {
        return courseId;
    }

    public void setCourseId(Courses courseId) {
        this.courseId = courseId;
    }

    public Timestamp getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Timestamp subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }
}
