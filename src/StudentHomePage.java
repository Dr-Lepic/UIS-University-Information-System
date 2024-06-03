import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentHomePage extends JFrame {
    private String username; // Assuming the username is passed to this class

    public StudentHomePage(Student student) {
        this.username = student.name;


        setTitle("Student Home Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(Color.decode("#28232a"));

        JLabel welcomeLabel = new JLabel("Welcome " + username);
        welcomeLabel.setForeground(Color.decode("#6deeba"));
        mainPanel.add(welcomeLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 4));
        buttonPanel.setBackground(Color.decode("#28232a"));

        // Create buttons
        JButton qnaButton = createStyledButton("Q&A");
        JButton noteButton = createStyledButton("Note");
        JButton bookButton = createStyledButton("Book");
        JButton scheduleButton = createStyledButton("Schedule");
        JButton teacherButton = createStyledButton("Teacher");
        JButton courseButton = createStyledButton("Course");
        JButton notification = createStyledButton("Notification");
        JButton ewalletButton = createStyledButton("E-wallet");
        JButton logoutButton = createStyledButton("Logout");

        qnaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new QNAUI(student).setVisible(true);
                dispose();
            }
        });

        noteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new NoteUI(student).setVisible(true);
            }
        });

        bookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new BookUI(student).setVisible(true);
            }
        });

        scheduleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open Schedule page
                openScheduleDialog(student);
            }
        });

        teacherButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open Teacher page
                openTeacherDialog(student);
            }
        });

        courseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open Course page
                dispose();
                new CourseUI(student).setVisible(true);
                //openCourseDialog(student);
            }
        });

        notification.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open Notification page
                JOptionPane.showMessageDialog(null, student.showNotificationList(),"Notification", JOptionPane.QUESTION_MESSAGE);
            }
        });

        ewalletButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open E-wallet page
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Semester.save(student.semester);
                dispose();
                new HomePageUI().setVisible(true);
            }
        });

        buttonPanel.add(qnaButton);
        buttonPanel.add(noteButton);
        buttonPanel.add(bookButton);
        buttonPanel.add(scheduleButton);
        buttonPanel.add(teacherButton);
        buttonPanel.add(courseButton);
        buttonPanel.add(notification);
        buttonPanel.add(ewalletButton);

        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(logoutButton, BorderLayout.SOUTH);

        add(mainPanel);
        setLocationRelativeTo(null);
    }

    private void openScheduleDialog(Student student) {
        JDialog answerDialog = new JDialog(this, "Today's Schedule", true);
        answerDialog.setSize(400, 300);
        answerDialog.setLayout(new BorderLayout());
        answerDialog.setLocationRelativeTo(this);

        JTextArea scheduleTextArea = new JTextArea();
        scheduleTextArea.setEditable(false);
        JScrollPane scheduleScrollPane = new JScrollPane(scheduleTextArea);

        scheduleTextArea.setText(student.showSchedule());
        answerDialog.add(scheduleTextArea, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));

        JButton submitButton = new JButton("Back");
        JButton defaultButton = new JButton("Default");

        buttonPanel.add(submitButton);
        buttonPanel.add(defaultButton);

        answerDialog.add(buttonPanel, BorderLayout.SOUTH);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                answerDialog.dispose();
            }
        });
        defaultButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                student.getDefaultSchedule();
                scheduleTextArea.setText(student.showSchedule());
            }
        });
        answerDialog.setVisible(true);
    }

    private void openTeacherDialog(Student student) {
        JDialog answerDialog = new JDialog(this, "Teacher List", true);
        answerDialog.setSize(400, 300);
        answerDialog.setLayout(new BorderLayout());
        answerDialog.setLocationRelativeTo(this);

        JTextArea scheduleTextArea = new JTextArea();
        scheduleTextArea.setEditable(false);
        JScrollPane scheduleScrollPane = new JScrollPane(scheduleTextArea);

        scheduleTextArea.setText(student.showTeachers());
        answerDialog.add(scheduleTextArea, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));

        JButton submitButton = new JButton("Back");


        buttonPanel.add(new JLabel());
        buttonPanel.add(submitButton);

        answerDialog.add(buttonPanel, BorderLayout.SOUTH);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                answerDialog.dispose();
            }
        });

        answerDialog.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Student student = new Student();
                student.name = "JohnDoe";
                new StudentHomePage(student).setVisible(true);
            }
        });
    }
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(Color.decode("#6deeba"));
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Arial", Font.BOLD, 20));
        return button;
    }
}