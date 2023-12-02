package org.example;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.ListJoin;
import jakarta.persistence.criteria.Root;
import org.example.model.Courses;
import org.example.model.Purchaselist;
import org.example.model.Students;
import org.example.model.Subscriptions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Homework4 {

    public static void main(String[] args) {
        Configuration configuration = new Configuration().configure();

        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {

            Session session = sessionFactory.openSession();

            // Task 1: Агрегатная функция: Получение количества студентов на каждом курсе.
//            task1(session);
            // Task 2: Объединение: Получение списка курсов для конкретного студента.
            String studentName = "Бойков Максим"; // id = 47
            task2(session, studentName);
        }
    }

    public static void task1(Session session) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        Root<Courses> root = query.from(Courses.class);

        ListJoin<Courses, Students> studentsJoin = root.joinList("studentsList");
        query.multiselect(root.get("name"), builder.count(studentsJoin)).groupBy(root.get("name"));

        List<Object[]> list = session.createQuery(query).list();

        for (Object[] objects : list) {
            String name = (String) objects[0];
            Long count = (Long) objects[1];
            System.out.println(name + " - " + count);
        }
    }

    public static void task2(Session session, String studentName) {
        // 1-ый вариант решения
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Purchaselist> query = criteriaBuilder.createQuery(Purchaselist.class);
        query.from(Purchaselist.class);

        List<Purchaselist> list = session.createQuery(query).list();

        System.out.println("Курсы: ");
        for (Purchaselist purchaselist : list) {
            if (purchaselist.getStudentName().equals(studentName)) {
                System.out.println("\t" + purchaselist.getCourseName());
            }
        }

        // 2-ой вариант решения
//        CriteriaBuilder builder = session.getCriteriaBuilder();
//        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
//        Root<Students> root = query.from(Students.class);
//
//        ListJoin<Students, Courses> coursesListJoin = root.joinList("coursesList");
//        query.multiselect(
//                root.get("name"),
//                coursesListJoin.get("name")
//        );
//
//        List<Object[]> list = session.createQuery(query).list();
//
//        System.out.println("Курсы: ");
//        for (Object[] objects : list) {
//            String name = (String) objects[0];
//            if (name.equals(studentName)) {
//                String courseName = (String) objects[1];
//                System.out.println("\t" + courseName);
//            }
//        }

        // 3-ий вариант решения
//        CriteriaBuilder builder = session.getCriteriaBuilder();
//        CriteriaQuery<Subscriptions> query = builder.createQuery(Subscriptions.class);
//        query.from(Subscriptions.class);
//
//        List<Subscriptions> list = session.createQuery(query).list();
//
//        System.out.println("Курсы: ");
//        for (Subscriptions subscription : list) {
//            if (subscription.getStudentId() == 47) {
//                Courses course = session.get(Courses.class, subscription.getCourseId());
//                System.out.println("\t" + course.getName());
//            }
//        }
    }
}
