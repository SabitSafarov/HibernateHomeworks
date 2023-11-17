package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().configure();

        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {

            Session session = sessionFactory.openSession();

            Courses course = session.get(Courses.class, 2);
            Teachers teacher = session.get(Teachers.class, course.getTeacher().getId());
            Students student = session.get(Students.class, 1);
            System.out.println(course.getName() + " - " + teacher.getName() + " - " + student.getName());
        }
    }
}