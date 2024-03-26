public class Question {
    Student student;
    Teacher teacher;
    String question;
    Course course;

    public Question(Student student, Teacher teacher, Course course, String question) {
        this.student = student;
        this.teacher = teacher;
        this.question = question;
        this.course = course;
    }
    //constructor without student
    public Question(Teacher teacher, Course course, String question) {
        this.teacher = teacher;
        this.question = question;
        this.course = course;
    }

    @Override//toString method
    public String toString() {
        return "Question{" +
                "student=" + student +
                ", teacher=" + teacher +
                ", question='" + question + '\'' +
                ", course=" + course +
                '}';
    }
}
