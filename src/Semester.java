
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Semester implements java.io.Serializable{
    int semester;
    public ArrayList<Course> courses;
    public ArrayList<Student> students;
    public ArrayList<Teacher> teachers;

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

    public static Semester restore(){
        Semester semester = null;
        try
        {
            // Reading the object from a file
            FileInputStream file = new FileInputStream("Save.ser");
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            semester = (Semester) in.readObject();

            in.close();
            file.close();

            System.out.println("Object has been deserialized ");

        }

        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }

        catch(ClassNotFoundException ex)
        {
            System.out.println("ClassNotFoundException is caught");
        }
        return semester;
    }
}
