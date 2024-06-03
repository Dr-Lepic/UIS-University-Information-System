import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeacherLogin extends JFrame {
    private JTextField nameField, password;
    Teacher userTeacher = null;
    private HomePageUI homePage;

    public TeacherLogin(HomePageUI homePage) {
        this.homePage = homePage;

        Semester semester = Semester.restore();

        setTitle("Teacher Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);

        JPanel mainPanelTeacher = new JPanel(new BorderLayout());
        mainPanelTeacher.setPreferredSize(new Dimension(600, 400));
        mainPanelTeacher.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel inputPanelTeacher = new JPanel(new GridLayout(4, 2));
        inputPanelTeacher.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanelTeacher.add(nameField);

        inputPanelTeacher.add(new JLabel("Password:"));
        password = new JTextField();
        inputPanelTeacher.add(password);

        JButton login = new JButton("Sign In");
        login.setMaximumSize(new Dimension(20, 10));
        login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String pass = password.getText();

                for (Teacher teacher : semester.teachers) {
                    if (teacher.name.equals(name) && teacher.mobileNo.equals(pass)) {
                        JOptionPane.showMessageDialog(null, "You have successfully logged in!");
                        userTeacher = teacher;
                        break;
                    }
                }
                if (userTeacher == null) {
                    JOptionPane.showMessageDialog(TeacherLogin.this, "Incorrect name or password!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    TeacherHomePage teacherUI = new TeacherHomePage(userTeacher);
                    teacherUI.setVisible(true);
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

        // Create a new panel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(login);
        buttonPanel.add(backButton);

        inputPanelTeacher.add(new JLabel()); // Empty label to adjust grid
        inputPanelTeacher.add(buttonPanel); // Add the button panel to the grid

        mainPanelTeacher.add(inputPanelTeacher, BorderLayout.CENTER);
        add(mainPanelTeacher);
        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new TeacherLogin(new HomePageUI()).setVisible(true);
    }
}