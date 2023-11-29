package org.example;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.model.Courses;
import org.hibernate.PropertyAccessException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Practice {
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

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Courses> query = builder.createQuery(Courses.class);
            Root<Courses> root = query.from(Courses.class);
            query.select(root).where(builder.greaterThan(root.get("price"), 100000)).orderBy(builder.desc(root.get("price")));
            List<Courses> courses = session.createQuery(query).setMaxResults(3).list();

            for (Courses course : courses) {
                System.out.println(course.getTeacher().getName() + " - " + course.getName() + " - " + course.getPrice());
            }

            transaction.commit();

        } catch (PropertyAccessException ex) {

        }
    }


}