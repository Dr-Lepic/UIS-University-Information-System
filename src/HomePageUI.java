import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePageUI extends JFrame {
    public HomePageUI() {
        JButton loginStudent, loginTeacher, createTeacher, createStudent;
        setTitle("Home Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 400);

        ImageIcon icon = new ImageIcon("D:\\Home Page.png");

        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        icon = new ImageIcon(scaledImg);

        JLabel imageLabel = new JLabel(icon);

        loginStudent = new JButton("Student Sign In");
        loginTeacher = new JButton("Teacher Sign In");
        createStudent = new JButton("Create Student ID");
        createTeacher = new JButton("Create Teacher ID");

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        loginStudent.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginTeacher.setAlignmentX(Component.CENTER_ALIGNMENT);
        createStudent.setAlignmentX(Component.CENTER_ALIGNMENT);
        createTeacher.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        buttonPanel.add(loginStudent);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(loginTeacher);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(createStudent);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(createTeacher);

        mainPanel.add(imageLabel, BorderLayout.WEST);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        loginStudent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new StudentLogin(HomePageUI.this).setVisible(true);
                dispose();
            }
        });

        loginTeacher.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new TeacherLogin(HomePageUI.this).setVisible(true);
                dispose();
            }
        });

        createStudent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CreateStudent(HomePageUI.this).setVisible(true);
                dispose();
            }
        });

        createTeacher.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CreateTeacher(HomePageUI.this).setVisible(true);
                dispose();
            }
        });

        add(mainPanel);
        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new HomePageUI().setVisible(true);
            }
        });
    }
}