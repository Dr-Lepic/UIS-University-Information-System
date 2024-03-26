//This main Class is used for code testing!!
public class Main {
    public static void main(String[] args) {

        Teacher teacher = new Teacher();
        Student student = new Student();
        Course course = new Course();

        teacher.askQuestion(student, teacher, "What is your name?", course);
        System.out.println(teacher.askedQuestionList.getFirst().question);
        System.out.println(student.questionList.getFirst().question);

        student.ansQuestion(student.questionList.getFirst(), "Mahbub");
        System.out.println(teacher.answerList.getFirst().answer);
        System.out.println(student.answerList.getFirst().answer);


    }
}