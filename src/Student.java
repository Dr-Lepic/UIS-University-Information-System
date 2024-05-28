import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.SortedMap;


public class Student implements QNA, Serializable{
        
    String name;
    String  Id;
    String email;
    String phoneNumber;

    //StringBuilder schedule;
    ArrayList<String > schedule;
    ArrayList<Course> courseList;
    ArrayList<Teacher> teacherList;
    ArrayList<Notification> notificationList;
    ArrayList<Book> borrowedBookList;


    //Will be a constructor here
    public Student(){
        schedule = new ArrayList<>();
        courseList = new ArrayList<>();
        teacherList = new ArrayList<>();
        notificationList = new ArrayList<>();
        borrowedBookList = new ArrayList<>();

    }
    public Student(String name, String  Id, String email, String phoneNumber, Semester semester){
        this.name = name;
        this.Id = Id;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.schedule = new ArrayList<>();

        //courseList = new ArrayList<>(semester.courses);
        this.courseList = new ArrayList<>();
        for(Course c : semester.courses){
            Course co =c;
            courseList.add(co);
        }
        //courseList = new ArrayList<>(List.copyOf(Semester.courses));//Semester.courses;
        teacherList = semester.teachers;
        semester.students.add(this);

        notificationList = new ArrayList<>();
        borrowedBookList = new ArrayList<>();

        for(Teacher t : semester.teachers){
            t.addStudent(this);
        }
    }

    //

    //toString method


    @Override
    public String toString() {
        return
                "name='" + name + '\'' +
                ", Id=" + Id +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\''
                ;
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

    //schedule
    void getSchedule(){
        schedule = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("schedule.txt"));
            String line;
            while((line = reader.readLine())!=null){
                schedule.add(line);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    void showSchedule(){
        System.out.println("Today's Schedule:");
        for(String i:schedule){
            System.out.println(i);
        }
    }
    void getDefaultSchedule(){
        getSchedule();
    }
    ////////////////////////

    //Course Topic
    void addTopic(Course course, String topic){
        for(Course c:courseList){
            if(c.equals(course)){
                c.info.topics.add(topic);
            }
        }
    }
    void removeTopic(Course course, int number){
        for(Course c:courseList){
            if(c.equals(course)){
                c.info.topics.remove(number-1);
            }
        }
    }
    void showTopic(Course course){
        for(Course c:courseList){
            if(c.equals(course)){
                int i=1;
                System.out.println("Topics for "+c.courseCode);
                for(String str:c.info.topics){
                    System.out.print(i + ". ");
                    System.out.println(str);
                    i++;
                }
            }
        }
    }
    ///////////////////////

    //Course add and remove
    void addCourse(Course course){
        courseList.add(course);
    }
    void removeCourse(Course course){
        courseList.remove(course);
    }
    void showCourse(){
        System.out.println("List of Courses: ");
        for(Course c:courseList){
            System.out.println(c);
        }
    }
    /////////////

    //Teacher list
    void showTeachers(){
        System.out.println("List of Teachers: ");
        for(Teacher t:teacherList){
            System.out.println(t);
        }
    }

    //////////////

    //mark
    void showMarks(Course course){
        System.out.println("List of Marks: ");
        for(Course c:courseList){
            if(c.equals(course)){
                for (String s:c.info.quiz){
                    System.out.println(s);
                }
                System.out.println(c.info.midMark);
                System.out.println(c.info.finalMark);
            }
        }
        System.out.println();
    }


}
