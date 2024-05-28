import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

//This main Class is used for code testing!!
public class Main {
    public static void main(String[] args) throws IOException {

        Teacher teacher = new Teacher();
        Student student = new Student();
        Student student2 = new Student();
        Course course = new Course();
        Book book = new Book("Demon Slayer", "Jani na");

        teacher.askQuestion(student, teacher, "What is your name?", course);
        System.out.println(teacher.askedQuestionList.getFirst().question);
        System.out.println(student.questionList.getFirst().question);

        student.ansQuestion(student.questionList.getFirst(), "Mahbub");
        System.out.println(teacher.answerList.getFirst().answer);
        System.out.println(student.answerList.getFirst().answer);

        course.courseCode = "CSE4201";
        teacher.name = "kamal";

        teacher.giveNotification(student, course,"Test notification");
        student.showNotificationList();
        student.clearNotificationList();
        student.showNotificationList();

        /*                                      Tested Book borrowing features
        student.borrowBook(book, 20);
        //student.returnBook(book);
        student.showBorrowedBook();
        */

        /*                                      Tested schedule features
        student.getSchedule();
        student.showSchedule();

        teacher.studentList = new ArrayList<>();
        teacher.studentList.add(student);
        student.courseList.add(course);
        teacher.updateSchedule(course, 3, "3 SWE4201 Room-501 10.30am-11.45am");
        student.showSchedule();

        student.getDefaultSchedule();
        student.showSchedule();
        student.showNotificationList();
        */

        /*                              Tested course topic add
        student.courseList=new ArrayList<>();
        student2.courseList = new ArrayList<>();
        student.addCourse(course);
        student2.addCourse(course);

        student.addTopic(course, "Don't know what to read");
        student.addTopic(course, "Another topic I don't know what to read");

        student2.addTopic(course, "Test");
        student.showTopic(course);
        System.out.println();
        student2.showTopic(course);

        student.removeTopic(course, 2);
        student.showTopic(course);
         */

        Semester semester = new Semester(2);

        Student realStudent = new Student("Mahbub", 220042148, "mahbubrahman@iut-dhaka.edu",
                "01234567890", semester);

        realStudent.showCourse();
        realStudent.showTeachers();
        realStudent.getSchedule();
        realStudent.showSchedule();
        realStudent.addTopic(realStudent.courseList.get(0), "Encapsulation");


        Student realStudent2 = new Student("Mahbub", 220042148, "mahbubrahman@iut-dhaka.edu",
                "01234567890", semester);

        realStudent2.addTopic(realStudent2.courseList.get(0), "Encapsulation new test");

        realStudent.showTopic(realStudent.courseList.get(0));
        realStudent2.showTopic(realStudent2.courseList.get(0));
        System.out.println(realStudent.teacherList.getFirst().studentList.getFirst().name);
        System.out.println();
        semester.teachers.getFirst().showStudent();
    }
}