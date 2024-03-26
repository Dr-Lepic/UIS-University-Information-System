import java.util.ArrayList;

public class Student implements QNA{
        
    String name;
    int Id;
    ArrayList<Course> courseList;


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
        question.teacher.askedQuestionList.remove(question);
        question.student.answerList.add(answer1);
        question.teacher.answerList.add(answer1);

    }
}
