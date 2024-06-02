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

            }
        });

        teacherButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open Teacher page
            }
        });

        courseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open Course page
            }
        });

        notification.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open Notification page
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
        answerDialog.setLayout(new BorderLayout(10, 10));
        answerDialog.setLocationRelativeTo(this);

        JTextArea scheduleTextArea = new JTextArea();
        scheduleTextArea.setEditable(false);
        JScrollPane scheduleScrollPane = new JScrollPane(scheduleTextArea);

       // scheduleTextArea.setText(student.);
        answerDialog.add(scheduleScrollPane, BorderLayout.CENTER);
        JLabel questionLabel = new JLabel("Question:");
        JComboBox<String> questionDropdown = new JComboBox<>(new String[]{"Question 1", "Question 2", "Question 3"});
        JLabel answerLabel = new JLabel("Answer:");
        JTextField answerField = new JTextField();
        JButton submitButton = new JButton("Submit");

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle the submission of the answer
                Question question = null;
                try {
                    question = student.questionList.get(questionDropdown.getSelectedIndex());
                }catch (ArrayIndexOutOfBoundsException ex){
                    JOptionPane.showMessageDialog(answerDialog, "No question found", "Error", JOptionPane.ERROR_MESSAGE);
                }


                student.ansQuestion(question, answerField.getText());
                JOptionPane.showMessageDialog(answerDialog, "Answer Submitted");
                answerDialog.dispose();
            }
        });

        answerDialog.add(questionLabel);
        answerDialog.add(questionDropdown);
        answerDialog.add(answerLabel);
        answerDialog.add(answerField);
        answerDialog.add(new JLabel()); // Empty placeholder
        answerDialog.add(submitButton);

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