package org.example.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "teachers")
public class Teachers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int salary;

    private int age;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.REMOVE)
    private List<Courses> coursesList;

    public Teachers() {

    }

    public Teachers(String name, int salary, int age) {
        this.name = name;
        this.salary = salary;
        this.age = age;
    }

    public List<Courses> getCoursesList() {
        return coursesList;
    }

    public void setCoursesList(List<Courses> coursesList) {
        this.coursesList = coursesList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Teachers{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                '}';
    }
}
