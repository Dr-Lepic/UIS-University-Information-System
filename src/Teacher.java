

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class Teacher implements QNA, Serializable{
    
    public String name;
    public String  mobileNo;
    public String emailAddress;
    public ArrayList<Course> courseList;
    public ArrayList<Student> studentList;
    //Will be a constructor here
    Teacher(){
        courseList = new ArrayList<>();
        studentList = new ArrayList<>();

    }
    Teacher(String name, String  mobileNo, String emailAddress){
        this.name = name;
        this.mobileNo = mobileNo;
        this.emailAddress = emailAddress;
        courseList = new ArrayList<>();
        studentList = new ArrayList<>();
    }

    //

    //toString method
    @Override
    public String toString() {
        return
                "name= '" + name +
                ", mobileNo= " + mobileNo +
                ", emailAddress= '" + emailAddress;
    }

    @Override
    public void askQuestion(Student student, Teacher teacher,String question, Course course ) {
        Question question1 = new Question(student, this, course, question);
        this.askedQuestionList.add(question1);
        student.questionList.add(question1);
        student.notificationList.add(new Notification(
                course, " asked a code.Question.", teacher));

    }

    @Override
    public void ansQuestion(Question question, String answer) {
        Answer answer1 = new Answer(question, answer);
        question.student.askedQuestionList.remove(question);
        question.student.answerList.add(answer1);
        question.teacher.answerList.add(answer1);
        question.student.notificationList.add(new Notification(
                question.course, " Answered your  question.", this));

    }

    //a method to ask question to all students of a particular course.
    //will run a for-each loop in the student list of teacher. If course matches,
    //then question will be inserted to his(code.Student) list

    public void askQuestionAll( Teacher teacher,String question, Course course){
        Question question1 = new Question(this, course, question);
        for(Student i : studentList){
            if(i.courseList.contains(course)){
                question1.student = i;
                i.questionList.add(question1);
                i.notificationList.add(new Notification(
                        course, " asked a code.Question.", teacher));
            }
        }
    }

    public String showQuestion(){
        StringBuilder question = new StringBuilder();
        for(Question q : this.questionList){
            question.append(q.question).append("\n");
        }
        return question.toString();
    }

    public String showAnswer(){
        StringBuilder answer = new StringBuilder();
        for(Answer a : this.answerList){
            answer.append(a.answer).append("\n");
        }
        return answer.toString();
    }

    //a method to clear answer list.
    private void clearAnsList(){
        answerList.clear();
    }

    public void giveNotification(Student student, Course course, String notification){

        Notification notification1 = new Notification(course, notification, this);
        student.notificationList.add(notification1);
    }

    public void giveNotification(Course course, String notification){

        Notification notification1 = new Notification(course, notification, this);
        for (Student i : studentList) {
            i.notificationList.add(notification1);
        }
    }

    //schedule
    void updateSchedule(Course course, int period, String update){
        for(Student i : studentList){
            if(i.courseList.contains(course)){
                i.schedule.set(period-1, "***" +update+ "***");
                i.notificationList.add(new Notification(course, "Extra class " + "'" + update + "'", this));
            }
        }
    }
    ////////////////

    //course
    void addCourse(Course course){
        courseList.add(course);
    }
    void removeCourse(Course course){
        courseList.remove(course);
    }
    void showCourse(){
        for(Course i : courseList){
            System.out.println(i);
        }
    }
    ///////////

    //add and remove student
    void addStudent(Student student){
        studentList.add(student);
    }
    void removeStudent(Student student){
        studentList.remove(student);
    }
    void showStudent(){
        for(Student i : studentList){
            System.out.println(i);
        }
    }
    void removeAllStudents(){
        studentList.clear();
    }
    /////////////

    //marking system
    void giveQuizMark(Course course){
        try {

            BufferedReader reader = new BufferedReader(new FileReader("Quiz.txt"));
            String line;
            while((line = reader.readLine())!=null){
                String[] parts = line.split(" ");

                for(Student i : studentList){
                    if(i.courseList.contains(course)){
                        if (Objects.equals(i.Id, parts[0])){
                            i.courseInfoList.get(courseList.indexOf(course)).quiz.add(parts[1]);
                            break;
                        }

                    }
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
