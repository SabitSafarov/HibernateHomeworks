package org.example;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.PropertyAccessException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().configure();

        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {

            Session session = sessionFactory.openSession();
//
//            Courses course = session.get(Courses.class, 2);
//            Teachers teacher = session.get(Teachers.class, course.getTeacher().getId());
//            Students student = session.get(Students.class, 1);
//            System.out.println(course.getName() + " - " + teacher.getName() + " - " + student.getName());

            // Task 1
//            printStudentsName(session);
            // Task 2
//            printCourseName(session);
            // Task 3
//            printTeachersName(session);
            // Task 4
            printStudentsCount(session);
            // Task 5
            printAverageStudentAge(session);

        } catch (PropertyAccessException ex) {

        }
    }

    // Task 1
    public static void printStudentsName(Session session) {
        // Вариант 1
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Students> criteria = builder.createQuery(Students.class);
        criteria.from(Students.class);
        List<Students> studentsList = session.createQuery(criteria).getResultList();

        for (Students student : studentsList) {
            System.out.println(student.getName());
        }

        // Вариант 2
//        List<Students> studentsList = session.createQuery("FROM Students", Students.class).list();
//
//        for (Students student : studentsList) {
//            System.out.println(student.getName());
//        }
    }

    // Task 2
    public static void printCourseName(Session session) {
        List<String> coursesList = session.createQuery("SELECT name FROM Courses", String.class).list();

        for (String course : coursesList) {
            System.out.println(course);
        }

//        session.createQuery("FROM Courses", Courses.class)
//                .stream().forEach(courses -> System.out.println(courses.getName()));

//        List<Courses> coursesList = session.createQuery("FROM Courses", Courses.class).list();
//
//        for (Courses courses : coursesList) {
//            System.out.println(courses.getName());
//        }
    }

    // Task 3
    public static void printTeachersName(Session session) {
        List<Teachers> teachersList = session.createQuery("FROM Teachers", Teachers.class).list();

        for (Teachers teachers : teachersList) {
            System.out.println(teachers.getName());
        }

//        List<Courses> coursesList = session.createQuery("FROM Courses", Courses.class).list();
//
//        for (Courses courses : coursesList) {
//            System.out.println(courses.getTeacher().getName());
//        }
    }

    // Task 4
    public static void printStudentsCount(Session session) {
        Long count = session.createQuery("SELECT COUNT(id) FROM Students", Long.class).getSingleResult();
        System.out.println("Students count: " + count);
    }

    // Task 5
    public static void printAverageStudentAge(Session session) {
        Double avgAge = session.createQuery("SELECT AVG(age) FROM Students", Double.class).getSingleResult();
        System.out.println("Average age: " + avgAge);
    }
}