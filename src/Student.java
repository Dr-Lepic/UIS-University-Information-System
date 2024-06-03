

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class Student implements QNA, Serializable{
        
    String name;
    String  Id;
    String email;
    String phoneNumber;
    Semester semester; // for serialization purpose
    String department;
    String program;
    private String password;

    //StringBuilder schedule;
    ArrayList<String > schedule;
    ArrayList<Course> courseList;
    ArrayList<Teacher> teacherList;
    ArrayList<Notification> notificationList;
    ArrayList<Book> borrowedBookList;
    ArrayList<CourseInfo> courseInfoList;


    //Will be a constructor here
    public Student(){
        schedule = new ArrayList<>();
        courseList = new ArrayList<>();
        teacherList = new ArrayList<>();
        notificationList = new ArrayList<>();
        borrowedBookList = new ArrayList<>();
        courseInfoList = new ArrayList<>(6);
        courseInfoList.add(new CourseInfo());
        courseInfoList.add(new CourseInfo());
        courseInfoList.add(new CourseInfo());
        getSchedule();

    }
    public Student(String name, String  Id, String email, String phoneNumber,
                   String department, String program,String password, Semester semester){
        this.name = name;
        this.Id = Id;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.schedule = new ArrayList<>();
        courseInfoList = new ArrayList<CourseInfo>(6);
        this.semester = semester;
        this.department = department;
        this.program = program;
        this.password = password;

        this.courseList = new ArrayList<>();
        courseList = semester.courses;
        teacherList = semester.teachers;
        semester.students.add(this);

        notificationList = new ArrayList<>();
        borrowedBookList = new ArrayList<>();
        courseInfoList.add(new CourseInfo());
        courseInfoList.add(new CourseInfo());
        courseInfoList.add(new CourseInfo());
        getSchedule();

        for(Teacher t : semester.teachers){
            t.addStudent(this);
        }

    }


    //

    public String getPassword() {
        return password;
    }


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
        //this.askedQuestionList.add(question1);
        teacher.questionList.add(question1);

    }

    @Override
    public void ansQuestion(Question question, String answer) {
        Answer answer1 = new Answer(question, answer);
        //if a question is asked to all students then we will only remove one time from teacher list
        if(question.teacher.askedQuestionList.contains(question)){
            question.teacher.askedQuestionList.remove(question);
        }
        //question.student.answerList.add(answer1);
        question.teacher.answerList.add(answer1);

    }

    public String showQuestion(){
        StringBuilder question = new StringBuilder();
        for(Question q : this.questionList){
            question.append(q.question).append("\n");
        }
        if(question.isEmpty()){
            return "No question YET!";
        }
        return question.toString();
    }

    public String showAnswer(){
        StringBuilder answer = new StringBuilder();
        for(Answer a : this.answerList){
            answer.append(a.answer).append("\n");
        }
        if(answer.isEmpty()){
            return "No answer YET!";
        }
        return answer.toString();
    }

    //notification
    public void clearNotificationList(){
        notificationList.clear();
    }

    public String  showNotificationList(){
        StringBuilder notification = new StringBuilder();
        if(notificationList.isEmpty()){
            notification.append("No notification found").append("\n");
        }
        else {
            for (Notification noti : notificationList) {
                notification.append(noti).append("\n");
            }
        }
        return notification.toString();
    }
    ////////////////////////

    //book borrowing and returning
    public void borrowBook(String name,String author, int howDays){
        Book book = new Book(name, author);
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

    public String  returnBook(String name){
        Book book = null;

        for(Book b : borrowedBookList){
            if(b.name.equals(name)){
                book = b;
            }
        }
        if(book == null){
            return " Book not found";
        }
        book.available = true;
        book.borrowDate = null;
        book.returnDate = null;
        borrowedBookList.remove(book);

        return "Returned  " + book;
    }

    public String  showBorrowedBook(){
        StringBuilder sb = new StringBuilder();
        if(borrowedBookList.isEmpty()){
            sb.append("No book found");
        }
        else{
            for (Book book : borrowedBookList) {
                sb.append("Have to return ").append(book).append(" within ").append(book.returnDate).append("\n");
            }
        }
        return sb.toString();
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
    String  showSchedule(){
        StringBuilder sb = new StringBuilder();
        sb.append("Today's  Schedule:").append("\n");
        for(String i:schedule){
            sb.append(i).append("\n");
        }
        return sb.toString();
    }
    void getDefaultSchedule(){
        getSchedule();
    }
    ////////////////////////

    // Course Topic
    void addTopic(Course course, String topic){
        for(Course c:courseList){
            if(c.equals(course)){
                c.topics.add(topic);

            }
        }
    }
    void removeTopic(Course course, int number){
        for(Course c:courseList){
            if(c.equals(course)){
               c.topics.remove(number-1);
            }
        }
    }
    String showTopic(Course course){
        StringBuilder sb = new StringBuilder();
        for(Course c:courseList){
            if(c.equals(course)){
                int i=1;
                sb.append("Topics for ").append(c.courseCode).append("\n");
                for(String str:c.topics){
                    sb.append(i).append(". ").append(str).append("\n");
                    i++;
                }
            }
        }
        return sb.toString();
    }
    ///////////////////////

    // Course add and remove
    void addCourse(Course course){
        courseList.add(course);
    }
    void removeCourse(Course course){
        courseList.remove(course);
    }
    String showCourse(){
        StringBuilder sb = new StringBuilder();
        sb.append("List of Courses: ");
        for(Course c:courseList){
            sb.append(c).append("\n");
        }
        return sb.toString();
    }
    /////////////

    // Teacher list
    String showTeachers(){
        StringBuilder sb = new StringBuilder();
        sb.append("List of Teachers: ");
        for(Teacher t:teacherList){
            sb.append(t).append("\n");
        }
        return sb.toString();
    }

    //////////////

    //mark
    String showMarks(Course course){
        StringBuilder sb = new StringBuilder();
        sb.append("List of Marks: ").append("\n");
        for(Course c:courseList){
            if(c.equals(course)){
                for (String s:courseInfoList.get(courseList.indexOf(c)).quiz){
                    sb.append(s).append("\n");
                }
                sb.append(courseInfoList.get(courseList.indexOf(c)).midMark).append("\n");
                sb.append(courseInfoList.get(courseList.indexOf(c)).finalMark).append("\n");
            }
        }
        return sb.toString();
    }


}
