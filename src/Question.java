

public class Question implements java.io.Serializable{
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
        return "student=" + student.name +
                ", teacher=" + teacher.name +
                ", question='" + question + '\'' +
                ", course=" + course.courseCode;
    }
}
