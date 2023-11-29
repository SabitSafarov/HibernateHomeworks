package org.example.model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "subscriptions")
public class Subscriptions {

    @EmbeddedId
    private SubscriptionsKey id;

    @Column(name = "student_id", insertable = false, updatable = false)
    private int studentId;

    @Column(name = "course_id", insertable = false, updatable = false)
    private int courseId;

    @Column(name = "subscription_date")
    private Timestamp subscriptionDate;

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public Timestamp getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Timestamp subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    @Override
    public String toString() {
        return "Subscriptions{" +
                "studentId=" + studentId +
                ", courseId=" + courseId +
                ", subscriptionDate=" + subscriptionDate +
                '}';
    }
}
