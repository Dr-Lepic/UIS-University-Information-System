
import java.util.ArrayList;

public class Semester {
    int semester;
     ArrayList<Course> courses;
     ArrayList<Student> students;
     ArrayList<Teacher> teachers;

    public Semester(int num) {
        switch(num) {
            case 1:
                semester = 1;
                //default courses and teachers
                break;
            case 2:
                this.semester = num;
                courses = new ArrayList<>();
                courses.add(new Course("OOP", "SWE4201", 3, 30));
                courses.add(new Course("Discrete Math", "CSE4203", 3, 30));
                courses.add(new Course("Math", "Math4241", 4, 40));
                courses.add(new Course("Business Psychology", "HUM4249", 3, 25));

                students = new ArrayList<>();
                teachers = new ArrayList<>();
                teachers.add(new Teacher("Jubair sir", "01234567890", "jubair@iut-dhaka.edu"));
                teachers.add(new Teacher("Azam sir", "01234567890", "azam@iut-dhaka.edu"));
                teachers.add(new Teacher("Farid sir", "01234567890", "farid@iut-dhaka.edu"));
                teachers.add(new Teacher("Nahin Sir", "01234567890", "nahin@iut-dhaka.edu"));

                courses.get(0).addTeacher(teachers.get(0));
                courses.get(1).addTeacher(teachers.get(1));
                courses.get(2).addTeacher(teachers.get(2));
                courses.get(3).addTeacher(teachers.get(3));
                break;
                //there will be other case for all semester
                //we are only considering SWE here.
                //other can be added in a similar way....
        }
    }
}
