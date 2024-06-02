import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentLogin extends JFrame {
    private JTextField nameField, password;
    Student userStudent = null;
    private HomePageUI homePage;

    public StudentLogin(HomePageUI homePage) {
        this.homePage = homePage;

        Semester semester = Semester.restore();

        setTitle("Student Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);

        JPanel mainPanelStudent = new JPanel(new BorderLayout());
        mainPanelStudent.setPreferredSize(new Dimension(600, 400));
        mainPanelStudent.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel inputPanelStudent = new JPanel(new GridLayout(4, 2));
        inputPanelStudent.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanelStudent.add(nameField);

        inputPanelStudent.add(new JLabel("Password:"));
        password = new JTextField();
        inputPanelStudent.add(password);

        JButton login = new JButton("Sign In");
        login.setMaximumSize(new Dimension(20, 10));
        login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String pass = password.getText();

                for (Student student : semester.students) {
                    if (student.name.equals(name) && student.phoneNumber.equals(pass)) {
                        JOptionPane.showMessageDialog(null, "You have successfully logged in!");
                        userStudent = student;
                        dispose();
                        new StudentHomePage(name).setVisible(true);
                        break;
                    }
                }
                if (userStudent == null) {
                    JOptionPane.showMessageDialog(StudentLogin.this, "Incorrect name or password!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    StudentUI studentUI = new StudentUI(userStudent);
                    studentUI.setVisible(true);
                    dispose();
                }
            }
        });

        JButton backButton = new JButton("Back");
        backButton.setMaximumSize(new Dimension(20, 10));
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                homePage.setVisible(true);
                dispose();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(login);
        buttonPanel.add(backButton);

        inputPanelStudent.add(new JLabel());
        inputPanelStudent.add(buttonPanel);

        mainPanelStudent.add(inputPanelStudent, BorderLayout.CENTER);
        add(mainPanelStudent);
        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new StudentLogin(new HomePageUI()).setVisible(true);
    }
}