//this interface is used in Teacher and Student class

import java.util.ArrayList;
public interface QNA {
    ArrayList <Question> questionList = new ArrayList<>();//to store the incoming questions
    ArrayList <Answer> answerList = new ArrayList<>();//to store the given answer
    ArrayList <Question> askedQuestionList = new ArrayList<>();//to store asked question.

    //to ask question and save them in list
    void askQuestion(Student student, Teacher teacher,String question, Course course);

    //to answer questions and store them in list. removes questions from the list
    void ansQuestion(Question question, String answer);



}
