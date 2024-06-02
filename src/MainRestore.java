
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;

public class MainRestore {
    public static void main(String[] args) {

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

        String name, Id;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        name = scanner.nextLine();
        System.out.print("Enter your Id: ");
        Id = scanner.nextLine();

        Student student = null;

        for (Student s: semester.students)
        {
            if(s.name.equals(name) && s.Id.equals(Id))
            {
                student = s;
                break;
            }
            else {
                System.out.println("Name does not match");
            }
        }

        student.showCourse();
        student.showTopic(student.courseList.getFirst());


    }

}
