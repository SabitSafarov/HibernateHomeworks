package org.example;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.model.Courses;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class Homework3 {

    public static void main(String[] args) {
        Configuration configuration = new Configuration().configure();

        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {

            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            // Task 1
            task1(session);
            // Task 2
//            task2(session);
            // Task 3
//            task3(session);
            // Task 4
//            task4(session);
            // Task 5
//            task5(session);
            // Task 6
//            task6(session);

            transaction.commit();
        }
    }

    public static void task1(Session session) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Courses> query = builder.createQuery(Courses.class);
        Root<Courses> root = query.from(Courses.class);
        query.select(root)
                .where(builder.greaterThan(root.get("duration"), 50));

        List<Courses> coursesList = session.createQuery(query).list();

        for (Courses course : coursesList) {
            System.out.println(course.getName() + " - " + course.getDuration());
        }
    }

    public static void task2(Session session) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Courses> query = builder.createQuery(Courses.class);
        Root<Courses> root = query.from(Courses.class);
        query.select(root).where(builder.and(
                builder.lessThan(root.get("duration"), 40),
                builder.greaterThan(root.get("teacher").get("age"), 30)
        ));

        List<Courses> coursesList = session.createQuery(query).list();

        for (Courses course : coursesList) {
            System.out.println(course.getName() + " - " + course.getDuration() + " | "
                    + course.getTeacher().getName() + " - " + course.getTeacher().getAge() + "\n");
        }
    }

    public static void task3(Session session) {
        String hql = "FROM Courses WHERE price > :coursePrice";
        Query<Courses> query = session.createQuery(hql);
        query.setParameter("coursePrice", 120000);

        List<Courses> coursesList = query.list();

        for (Courses course : coursesList) {
            System.out.println(course.getName() + " - " + course.getPrice());
        }
    }

    public static void task4(Session session) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Courses> query = builder.createQuery(Courses.class);
        Root<Courses> root = query.from(Courses.class);
        query.select(root)
                .orderBy(builder.desc(root.get("duration")));

        List<Courses> coursesList = session.createQuery(query).setMaxResults(5).list();

        for (Courses course : coursesList) {
            System.out.println(course.getName() + " - " + course.getDuration());
        }
    }

    public static void task5(Session session) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Courses> query = builder.createQuery(Courses.class);
        Root<Courses> root = query.from(Courses.class);
        query.select(root).where(builder.or(
                builder.greaterThan(root.get("duration"), 40),
                builder.greaterThan(root.get("teacher").get("age"), 35)
        ));

        List<Courses> coursesList = session.createQuery(query).list();

        for (Courses course : coursesList) {
            System.out.println(course.getName() + " - " + course.getDuration() + " | "
                    + course.getTeacher().getName() + " - " + course.getTeacher().getAge() + "\n");
        }
    }

    public static void task6(Session session) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Courses> query = builder.createQuery(Courses.class);
        Root<Courses> root = query.from(Courses.class);
        query.select(root)
                .where(builder.between(root.get("price"), 50000, 150000))
                .orderBy(builder.desc(root.get("price")));

        List<Courses> coursesList = session.createQuery(query).list();

        for (Courses course : coursesList) {
            System.out.println(course.getName() + " - " + course.getPrice());
        }
    }
}
