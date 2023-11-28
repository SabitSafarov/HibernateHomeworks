package org.example;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.PropertyAccessException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().configure();

        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {

            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

//            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//            CriteriaQuery query = criteriaBuilder.createQuery(Subscriptions.class);
//            query.from(Subscriptions.class);
//            List<Subscriptions> list = session.createQuery(query).list();
//
//            for (Subscriptions subscriptions : list) {
//                System.out.println(subscriptions);
//            }
//----------------------------------------------------------------------------------------------------------------------
//            String studentName = "Бойков Максим";
//            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//            CriteriaQuery query = criteriaBuilder.createQuery(Purchaselist.class);
//            query.from(Purchaselist.class);
//            List<Purchaselist> list = session.createQuery(query).list();
//
//            System.out.println("Курсы: ");
//            for (Purchaselist purchaselist : list) {
//                if (purchaselist.getStudentName().equals(studentName)) {
//                    System.out.println("\t" + purchaselist.getCourseName());
//                }
//            }
//----------------------------------------------------------------------------------------------------------------------
//            Courses course = session.get(Courses.class, 2);
//            Teachers teacher = session.get(Teachers.class, course.getTeacher().getId());
//            Students student = session.get(Students.class, 1);
//            System.out.println(course.getName() + " - " + teacher.getName() + " - " + student.getName());
//----------------------------------------------------------------------------------------------------------------------
            // Task 1
//            printStudentsName(session);
            // Task 2
//            printCourseName(session);
            // Task 3
//            printTeachersName(session);
            // Task 4
//            printStudentsCount(session);
            // Task 5
//            printAverageStudentAge(session);
//----------------------------------------------------------------------------------------------------------------------

            // Отображение студентов подписанных на конкретный курс
//            String courseName = "Java-разработчик";
//            CriteriaBuilder builder = session.getCriteriaBuilder();
//            CriteriaQuery query = builder.createQuery(Purchaselist.class);
//            query.from(Purchaselist.class);
//            List<Purchaselist> list = session.createQuery(query).list();
//
//            System.out.println("Студены на курсе " + courseName + ": ");
//            for (Purchaselist purchaselist : list) {
//                if (purchaselist.getCourseName().equals(courseName)) {
//                    System.out.println("\t" + purchaselist.getStudentName());
//                }
//            }
//----------------------------------------------------------------------------------------------------------------------

            // Получение средней продолжительности курсов
//            Double avgDuration = session.createQuery("SELECT AVG(duration) FROM Courses", Double.class).getSingleResult();
//            System.out.println("Average duration: " + avgDuration);
//----------------------------------------------------------------------------------------------------------------------

            // Обновление цены на выбранном курсе
//            String courseName = "Java-разработчик";
//            List<Courses> coursesList = session.createQuery("FROM Courses", Courses.class).list();
//
//            for (Courses courses : coursesList) {
//                if (courses.getName().equals(courseName)) {
//                    Courses course = session.get(Courses.class, courses.getId());
//                    course.setPrice(8888);
//                    session.saveOrUpdate(course);
//                    break;
//                }
//            }
//----------------------------------------------------------------------------------------------------------------------

            // Удаление студента с курса
//            String studentName = "Бойков Максим"; // 47
//            String courseName = "Figma"; // 25
//            Courses course = session.createQuery("FROM Courses WHERE name = :courseName", Courses.class)
//                    .setParameter("courseName", courseName).getSingleResult();
//            Students student = session.createQuery("FROM Students WHERE name = :studentName", Students.class)
//                    .setParameter("studentName", studentName).getSingleResult();
//            Purchaselist purchaselist = session.createQuery("FROM Purchaselist WHERE courseName = :course AND studentName = :student", Purchaselist.class)
//                    .setParameter("course", courseName).setParameter("student", studentName).getSingleResult();
//            SubscriptionsKey key = new SubscriptionsKey(course.getId(), student.getId());
//            Subscriptions subscription = session.get(Subscriptions.class, key);
//
////            List<Students> studentsList = course.getStudentsList();
////            studentsList.remove(student);
////            List<Courses> coursesList = student.getCoursesList();
////            coursesList.remove(course);
//
//            session.remove(purchaselist);
//            session.remove(subscription);
//----------------------------------------------------------------------------------------------------------------------

            // Добавление нового курса
            Teachers teacher1 = session.get(Teachers.class, 1);
            Courses course = new Courses("Новый курс", 20, CourseType.PROGRAMMING, "Описание", teacher1, 10, 9999, 99);
            session.save(course);
//----------------------------------------------------------------------------------------------------------------------

            // Работа с преподавателями
            Scanner scr = new Scanner(System.in);

            boolean run = true;
            while (run) {
                System.out.println("\nВыберите команду: \n[1] - Добавление нового учителя\n[2] - Просмотр всех учителей\n[3] - Удаление учителя\n[4] - Выход");
                int input = Integer.parseInt(scr.nextLine());

                switch (input) {
                    case 1 -> {
                        System.out.print("Введите имя и фамилию учителя: ");
                        String name = scr.nextLine();
                        System.out.print("Введите возраст учителя: ");
                        int age = Integer.parseInt(scr.nextLine());
                        System.out.print("Введите зарплату учителя: ");
                        int salary = Integer.parseInt(scr.nextLine());

                        Teachers teacher = new Teachers(name, salary, age);
                        session.save(teacher);
                        System.out.println("Учитель успешно добавлен!");
                    }
                    case 2 -> {
                        List<Teachers> teachersList = session.createQuery("FROM Teachers", Teachers.class).list();
                        for (Teachers teacher : teachersList) {
                            System.out.println(teacher.getName() + " - " + teacher.getAge() + " | " + teacher.getSalary());
                        }
                    }
                    case 3 -> {
                        System.out.print("Введите имя и фамилию учителя которого хотите удалить: ");
                        String name = scr.nextLine();

                        Teachers teacher = session.createQuery("FROM Teachers WHERE name = :teacherName", Teachers.class)
                                .setParameter("teacherName", name).getSingleResult();

                        session.remove(teacher);
                        System.out.println("Учитель успешно удален!");
                    }
                    case 4 -> run = false;
                    default -> System.out.println("Неверная команда!");
                }
            }
//----------------------------------------------------------------------------------------------------------------------

            // Реализуйте вывод о студентах, зарегистрированных на курс, и общей стоимости обучения
            String courseName = "Java-разработчик";
            Courses course1 = session.createQuery("FROM Courses WHERE name = :courseName", Courses.class)
                    .setParameter("courseName", courseName).getSingleResult();

            List<Students> studentsList = course1.getStudentsList();
            for (Students student : studentsList) {
                System.out.println(student.getName() + " - " + student.getAge() + " - " + student.getRegistrationDate());
            }

            transaction.commit();

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