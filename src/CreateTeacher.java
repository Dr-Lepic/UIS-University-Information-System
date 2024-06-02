import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateTeacher extends JFrame {
    private JTextField nameField, mobileField, deptField, facultyIdField;
    private JPasswordField passwordField;

    public CreateTeacher(HomePageUI homePage) {
        setTitle("Create Teacher ID");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel inputPanel = new JPanel(new GridLayout(6, 2));
        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Mobile Number:"));
        mobileField = new JTextField();
        inputPanel.add(mobileField);

        inputPanel.add(new JLabel("Department:"));
        deptField = new JTextField();
        inputPanel.add(deptField);

        inputPanel.add(new JLabel("Faculty ID:"));
        facultyIdField = new JTextField();
        inputPanel.add(facultyIdField);

        inputPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        inputPanel.add(passwordField);

        JButton createButton = new JButton("Create");
        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Retrieve data from fields and create teacher ID
                String name = nameField.getText();
                String mobileNumber = mobileField.getText();
                String department = deptField.getText();
                String facultyId = facultyIdField.getText();
                String password = new String(passwordField.getPassword());

                // Here implement logic to create a teacher ID
                // For demonstration, let's just display the entered data
                String teacherInfo = "Name: " + name + "\n" +
                        "Mobile Number: " + mobileNumber + "\n" +
                        "Department: " + department + "\n" +
                        "Faculty ID: " + facultyId + "\n" +
                        "Password: " + password;
                JOptionPane.showMessageDialog(null, "Teacher ID created successfully:\n" + teacherInfo);

                // After creating the teacher ID, navigate back to the home page
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
                new CreateTeacher(homePage).setVisible(true);
            }
        });
    }
}