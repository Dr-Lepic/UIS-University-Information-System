import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CourseUI extends JFrame {
    public String username;

    public CourseUI(Student student) {
        setTitle("Course");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);

        JPanel mainPanel = new JPanel(new GridLayout(4, 1));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(Color.decode("#28232a"));

        JButton allQuestionsButton = createStyledButton("All Course");
        JButton allAnswersButton = createStyledButton("Add Topic");
        JButton askQuestionButton = createStyledButton("Remove Topic");
        JButton answerQuestionButton = createStyledButton("Show Topic");
        JButton backButton = createStyledButton("Back");

        allQuestionsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAllQuestionDialog(student);
            }
        });

        allAnswersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAllAnswerDialog(student);
            }
        });

        askQuestionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openAskQuestionDialog(student);
            }
        });

        answerQuestionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openAnswerQuestionDialog(student);
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new StudentHomePage(student).setVisible(true);
                dispose();
            }
        });

        mainPanel.add(allQuestionsButton);
        mainPanel.add(allAnswersButton);
        mainPanel.add(askQuestionButton);
        mainPanel.add(answerQuestionButton);

        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        backButtonPanel.setBackground(Color.decode("#28232a"));
        backButtonPanel.add(backButton);

        mainPanel.add(backButtonPanel);

        add(mainPanel);
        setLocationRelativeTo(null);
    }

    private void openAskQuestionDialog(Student student) {
        JDialog answerDialog = new JDialog(this, "Remove Topic", true);
        answerDialog.setSize(400, 200);
        answerDialog.setLayout(new GridLayout(4, 2, 10, 10));
        answerDialog.setLocationRelativeTo(this);

        JLabel nameLabel = new JLabel("Course Code:");
        JTextField nameField = new JTextField();
        JLabel topicLabel = new JLabel("Number:");
        JTextField topicField = new JTextField();
        JButton submitButton = new JButton("Submit");

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Course course = null;
                for(Course c: student.courseList){
                    if(c.courseCode.equals(nameField.getText())){
                        course = c;
                        break;
                    }
                }
                if(course == null){
                    JOptionPane.showMessageDialog(answerDialog, "No course found", "Error", JOptionPane.ERROR_MESSAGE);
                }
                student.removeTopic(course, Integer.parseInt(topicField.getText()));
                JOptionPane.showMessageDialog(answerDialog, "Topic Removed", "Success", JOptionPane.INFORMATION_MESSAGE);
                answerDialog.dispose();
            }
        });


        answerDialog.add(nameLabel);
        answerDialog.add(nameField);
        answerDialog.add(topicLabel);
        answerDialog.add(topicField);
        answerDialog.add(new JLabel());
        answerDialog.add(submitButton);
        answerDialog.setVisible(true);
    }

    private void openAnswerQuestionDialog(Student student) {
        JDialog answerDialog = new JDialog(this, "Topics", true);
        answerDialog.setSize(400, 300);
        answerDialog.setLayout(new GridLayout(3, 2, 10, 10));
        answerDialog.setLocationRelativeTo(this);

        JLabel questionLabel = new JLabel("Course Code:");
        JTextField nameField = new JTextField();
        JButton submitButton = new JButton("Submit");

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Course course = null;
                for(Course c: student.courseList){
                    if(c.courseCode.equals(nameField.getText())){
                        course = c;
                        break;
                    }
                }
                if(course == null){
                    JOptionPane.showMessageDialog(answerDialog, "No course found", "Error", JOptionPane.ERROR_MESSAGE);
                }
                JOptionPane.showMessageDialog(answerDialog, student.showTopic(course));
            }
        });

        answerDialog.add(questionLabel);
        answerDialog.add(nameField);

        answerDialog.add(new JLabel()); // Empty placeholder
        answerDialog.add(submitButton);

        answerDialog.setVisible(true);
    }

    private void showAllQuestionDialog(Student student) {
        JDialog returnDialog = new JDialog(this, "Course List", true);
        returnDialog.setSize(400, 200);
        returnDialog.setLayout(new GridLayout(2, 0, 10, 10));
        returnDialog.setLocationRelativeTo(this);

        JTextArea textArea = new JTextArea(20, 40);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setText(student.showCourse());
        JButton backButton = new JButton("Back");

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                returnDialog.dispose();
            }
        });

        returnDialog.add(textArea);
        returnDialog.add(backButton);

        returnDialog.setVisible(true);
    }


    private void showAllAnswerDialog(Student student) {
        JDialog answerDialog = new JDialog(this, "Add Topic", true);
        answerDialog.setSize(400, 200);
        answerDialog.setLayout(new GridLayout(4, 2, 10, 10));
        answerDialog.setLocationRelativeTo(this);

        JLabel nameLabel = new JLabel("Course Code:");
        JTextField nameField = new JTextField();
        JLabel topicLabel = new JLabel("Topic:");
        JTextField topicField = new JTextField();
        JButton submitButton = new JButton("Submit");

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Course course = null;
                for(Course c: student.courseList){
                    if(c.courseCode.equals(nameField.getText())){
                        course = c;
                        break;
                    }
                }
                if(course == null){
                    JOptionPane.showMessageDialog(answerDialog, "No course found", "Error", JOptionPane.ERROR_MESSAGE);
                }
                student.addTopic(course, topicField.getText());
                JOptionPane.showMessageDialog(answerDialog, "Topic Added", "Success", JOptionPane.INFORMATION_MESSAGE);
                answerDialog.dispose();
            }
        });

        answerDialog.add(nameLabel);
        answerDialog.add(nameField);
        answerDialog.add(topicLabel);
        answerDialog.add(topicField);
        answerDialog.add(new JLabel());
        answerDialog.add(submitButton);
        answerDialog.setVisible(true);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(Color.decode("#6deeba"));
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Arial", Font.BOLD, 20));
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CourseUI(new Student()).setVisible(true);
            }
        });
    }
}