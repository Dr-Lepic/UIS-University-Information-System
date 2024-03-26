import java.util.ArrayList;

public interface QNA {
    ArrayList <Question> questionList = new ArrayList<>();
    ArrayList <Answer> answerList = new ArrayList<>();
    ArrayList <Question> askedQuestionList = new ArrayList<>();

    public void askQuestion(Student student, Teacher teacher,String question, Course course);
    void ansQuestion(Question question, String answer);



}
