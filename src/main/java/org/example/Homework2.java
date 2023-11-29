package org.example;

import org.example.model.CourseType;
import org.example.model.Courses;
import org.example.model.Students;
import org.example.model.Teachers;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Scanner;

public class Homework2 {

    public static void main(String[] args) {
        Configuration configuration = new Configuration().configure();

        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {

            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

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
        }
    }
}
