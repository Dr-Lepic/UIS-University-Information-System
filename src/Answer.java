

public class Answer implements java.io.Serializable{

    Question question;
    String answer;

    public Answer(Question question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    @Override//toString method
    public String toString() {
        return "question=" + question +
                "\nanswer: " + answer;
    }
}
