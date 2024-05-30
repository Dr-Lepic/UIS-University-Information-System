



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeacherLogin extends JFrame {
    private JTextField nameField, password;
    Teacher userTeacher = null;

    public TeacherLogin() {
        Semester semester = Semester.restore();


        setTitle("code.Teacher Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(600, 400));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

//        JPanel buttonPanel = new JPanel();
//
//
//        buttonPanel.add(login);

        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Password:"));
        password = new JTextField();
        inputPanel.add(password);

        JButton login = new JButton("Sign In");
        login.setMaximumSize(new Dimension(20,10));

        login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String name = nameField.getText();
                String pass = password.getText();

                for(Teacher teacher: semester.teachers){
                    if(teacher.name.equals(name) && teacher.mobileNo.equals(pass)){
                        JOptionPane.showMessageDialog(null, "You have successfully logged in!");
                        userTeacher = teacher;
                        break;
                    }
                }
                if(userTeacher == null){
                    JOptionPane.showMessageDialog(TeacherLogin.this, "Incorrect name or pass!","Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    TeacherUI teacherUI = new TeacherUI(userTeacher);
                }
            }
        });
        inputPanel.add(new JLabel());
        inputPanel.add(login);

        mainPanel.add(inputPanel, BorderLayout.CENTER);
        add(mainPanel);
        pack();
        setLocationRelativeTo(null);

    }
    public static void main(String[] args) {
        new TeacherLogin().setVisible(true);
    }
}
