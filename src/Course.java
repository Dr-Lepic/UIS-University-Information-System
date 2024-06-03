

import java.io.Serializable;
import java.util.ArrayList;

public class Course implements Serializable {

        String courseName;
        String courseCode;
        float credit;
        Teacher teacher;
        int totalClass;
        ArrayList<String> topics;



        Course(){

        }

        public Course(String courseName, String courseCode, float credit, int totalClass) {
                this.courseName = courseName;
                this.courseCode = courseCode;
                this.credit = credit;
                this.totalClass = totalClass;
                topics = new ArrayList<>();

        }

        void addTeacher(Teacher t){
                teacher = t;
                teacher.addCourse(this);
        }

        @Override
        public String toString() {
                return
                        "courseName= " + courseName + ' ' +
                        ", courseCode= " + courseCode + ' ' +
                        ", credit= " + credit +
                        ", teacher= " + teacher.name +
                        ", totalClass= " + totalClass;
        }
}
