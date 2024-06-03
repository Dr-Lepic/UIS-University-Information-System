import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateStudent extends JFrame {
    private JTextField nameField, mobileField, deptField, programField, studentIdField, mailField;
    private JPasswordField passwordField;

    public CreateStudent(HomePageUI homePage) {

        Semester semester = Semester.restore();
        setTitle("Create Student ID");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 400);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel inputPanel = new JPanel(new GridLayout(8, 2));
        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Mobile Number:"));
        mobileField = new JTextField();
        inputPanel.add(mobileField);

        inputPanel.add(new JLabel("Department:"));
        deptField = new JTextField();
        inputPanel.add(deptField);

        inputPanel.add(new JLabel("Program:"));
        programField = new JTextField();
        inputPanel.add(programField);

        inputPanel.add(new JLabel("Student ID:"));
        studentIdField = new JTextField();
        inputPanel.add(studentIdField);

        inputPanel.add(new JLabel("E-mail:"));
        mailField = new JTextField();
        inputPanel.add(mailField);

        inputPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        inputPanel.add(passwordField);

        JButton createButton = new JButton("Create");
        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Retrieve data from fields and create student ID
                String name = nameField.getText();
                String mobileNumber = mobileField.getText();
                String department = deptField.getText();
                String program = programField.getText();
                String studentId = studentIdField.getText();
                String mail = mailField.getText();
                String password = new String(passwordField.getPassword());

                // Here implement logic to create a student ID
                Student student = new Student(name, studentId, mail, mobileNumber, department, program, password, semester);
                Semester.save(semester);
                // For demonstration, let's just display the entered data
                String studentInfo = "Name: " + name + "\n" +
                        "Mobile Number: " + mobileNumber + "\n" +
                        "Department: " + department + "\n" +
                        "Program: " + program + "\n" +
                        "Student ID: " + studentId + "\n" +
                        "Password: " + password;
                JOptionPane.showMessageDialog(null, "Student ID created successfully:\n" + studentInfo);

                // After creating the student ID, navigate back to the home page
                homePage.setVisible(true);
                dispose();
            }
        });

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                homePage.setVisible(true);
                dispose();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(createButton);
        buttonPanel.add(backButton);

        mainPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                HomePageUI homePage = new HomePageUI();
                homePage.setVisible(true);
                new CreateStudent(homePage).setVisible(true);
            }
        });
    }
}