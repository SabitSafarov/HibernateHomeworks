package org.example;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;

import java.io.Serializable;
import java.sql.Timestamp;

@Embeddable
public class SubscriptionsKey implements Serializable {

    @Column(name = "course_id")
    private int courseId;

    @Column(name = "student_id")
    private int studentId;

    public SubscriptionsKey(int courseId, int studentId) {
        this.courseId = courseId;
        this.studentId = studentId;
    }

    public SubscriptionsKey() {

    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
}
