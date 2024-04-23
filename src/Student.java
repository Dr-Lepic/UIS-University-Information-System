import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

public class Student implements QNA{
        
    String name;
    int Id;
    ArrayList<Course> courseList = new ArrayList<>();
    ArrayList<Notification> notificationList = new ArrayList<>();
    ArrayList<Book> borrowedBookList = new ArrayList<>();

    //Will be a constructor here

    //

    //toString method


    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", Id=" + Id +
                ", courseList=" + courseList +
                '}';
    }

    @Override
    public void askQuestion(Student student, Teacher teacher,String question, Course course ) {
        Question question1 = new Question(this, teacher, course, question);
        this.askedQuestionList.add(question1);
        teacher.questionList.add(question1);

    }

    @Override
    public void ansQuestion(Question question, String answer) {
        Answer answer1 = new Answer(question, answer);
        //if a question is asked to all students then we will only remove one time from teacher list
        if(question.teacher.askedQuestionList.contains(question)){
            question.teacher.askedQuestionList.remove(question);
        }
        question.student.answerList.add(answer1);
        question.teacher.answerList.add(answer1);

    }
    //notification
    public void clearNotificationList(){
        notificationList.clear();
    }

    public void showNotificationList(){
        if(notificationList.isEmpty()){
            System.out.println("No notification found");
        }
        else {
            for (Notification notification : notificationList) {
                System.out.println(notification);
            }
        }
    }
    ////////////////////////

    //book borrowing and returning
    public void borrowBook(Book book, int howDays){
        borrowedBookList.add(book);
        book.available = false;

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();

        book.borrowDate = cal.getTime();
        System.out.println("Borrowed Date: " + sdf.format(cal.getTime()));

        cal.add(Calendar.DAY_OF_MONTH, howDays);
        book.returnDate = cal.getTime();
        System.out.println("Return Date: " + sdf.format(cal.getTime()));


    }

    public void returnBook(Book book){

        book.available = true;
        book.borrowDate = null;
        book.returnDate = null;
        borrowedBookList.remove(book);
        System.out.println("Returned  " + book);
    }

    public void showBorrowedBook(){
        if(borrowedBookList.isEmpty()){
            System.out.println("No book found");
        }
        else{
            for (Book book : borrowedBookList) {
                System.out.println("Have to return " + book + " within " + book.returnDate);
            }
        }
    }
    ////////////////////////////
}
