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


}
