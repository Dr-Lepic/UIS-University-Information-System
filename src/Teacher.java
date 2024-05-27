import java.util.ArrayList;

public class Teacher implements QNA{
    
    String name;
    int mobileNo;
    String emailAddress;
    ArrayList<Course> courseList;
    ArrayList<Student> studentList;
    //Will be a constructor here

    //

    //toString method
    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", mobileNo=" + mobileNo +
                ", emailAddress='" + emailAddress + '\'' +
                ", courseList=" + courseList +
                ", studentList=" + studentList +
                '}';
    }

    @Override
    public void askQuestion(Student student, Teacher teacher,String question, Course course ) {
        Question question1 = new Question(student, this, course, question);
        this.askedQuestionList.add(question1);
        student.questionList.add(question1);

    }

    @Override
    public void ansQuestion(Question question, String answer) {
        Answer answer1 = new Answer(question, answer);
        question.student.askedQuestionList.remove(question);
        question.student.answerList.add(answer1);
        question.teacher.answerList.add(answer1);

    }

    //a method to ask question to all students of a particular course.
    //will run a for-each loop in the student list of teacher. If course matches,
    //then question will be inserted to his(Student) list

    public void askQuestionAll( Teacher teacher,String question, Course course){
        Question question1 = new Question(this, course, question);
        for(Student i : studentList){
            if(i.courseList.contains(course)){
                question1.student = i;
                i.questionList.add(question1);
            }
        }
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


}
