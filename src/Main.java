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

        student.borrowBook(book, 20);
        //student.returnBook(book);
        student.showBorrowedBook();


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

    }
}