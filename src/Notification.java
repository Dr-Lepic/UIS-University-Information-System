import java.io.Serializable;

// To give update of class and quiz to students.
public class Notification implements Serializable {

    public Course course;
    public String message;
    public Teacher teacher;

    public Notification(Course course, String message, Teacher teacher) {
        this.course = course;
        this.message = message;
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return
                course.courseCode + ": " +
                 message + ". From: "+ teacher.name;
    }
}
