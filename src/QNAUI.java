import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QNAUI extends JFrame {
    public String username;

    public QNAUI(Student student) {
        setTitle("Q&A");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);

        JPanel mainPanel = new JPanel(new GridLayout(4, 1));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(Color.decode("#28232a"));

        JButton allQuestionsButton = createStyledButton("All Questions");
        JButton allAnswersButton = createStyledButton("All Answers");
        JButton askQuestionButton = createStyledButton("Ask a Question");
        JButton answerQuestionButton = createStyledButton("Answer a Question");
        JButton backButton = createStyledButton("Back");

        allQuestionsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open "All Questions" page
                showAllQuestionDialog(student);
            }
        });

        allAnswersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open "All Answers" page
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
        JDialog askDialog = new JDialog(this, "Ask a Question", true);
        askDialog.setSize(400, 300);
        askDialog.setLayout(new GridLayout(4, 2, 10, 10));
        askDialog.setLocationRelativeTo(this);

        JLabel teacherLabel = new JLabel("Teacher:");
        JTextField teacherField = new JTextField();
        JLabel questionLabel = new JLabel("Question:");
        JTextField questionField = new JTextField();
        JLabel courseLabel = new JLabel("Course Code:");
        JTextField courseField = new JTextField();
        JButton submitButton = new JButton("Submit");

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle the submission of the question
                Teacher teacher = null;
                for(Teacher t: student.teacherList){
                    if(t.name.equals(teacherField.getText())){
                        teacher = t;
                        break;
                    }
                }
                if(teacher == null){
                    JOptionPane.showMessageDialog(askDialog, "No teacher found", "Error", JOptionPane.ERROR_MESSAGE);
                }

                Course course = null;
                for(Course c: student.courseList){
                    if (c.courseCode.equals(courseField.getText())){
                        course = c;
                        break;
                    }
                }
                if(course == null){
                    JOptionPane.showMessageDialog(askDialog, "No course found", "Error", JOptionPane.ERROR_MESSAGE);
                }
                student.askQuestion(student,teacher, questionField.getText(), course);
                JOptionPane.showMessageDialog(askDialog, "Question Submitted");
                askDialog.dispose();
            }
        });

        askDialog.add(teacherLabel);
        askDialog.add(teacherField);
        askDialog.add(questionLabel);
        askDialog.add(questionField);
        askDialog.add(courseLabel);
        askDialog.add(courseField);
        askDialog.add(new JLabel()); // Empty placeholder
        askDialog.add(submitButton);

        askDialog.setVisible(true);
    }

    private void openAnswerQuestionDialog(Student student) {
        JDialog answerDialog = new JDialog(this, "Answer a Question", true);
        answerDialog.setSize(400, 300);
        answerDialog.setLayout(new GridLayout(3, 2, 10, 10));
        answerDialog.setLocationRelativeTo(this);

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

    private void showAllQuestionDialog(Student student) {
        JDialog returnDialog = new JDialog(this, "Question List", true);
        returnDialog.setSize(400, 200);
        returnDialog.setLayout(new GridLayout(2, 0, 10, 10));
        returnDialog.setLocationRelativeTo(this);

        JTextArea textArea = new JTextArea(20, 40);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setText(student.showQuestion());
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
        JDialog returnDialog = new JDialog(this, "Answer List", true);
        returnDialog.setSize(400, 200);
        returnDialog.setLayout(new GridLayout(2, 0, 10, 10));
        returnDialog.setLocationRelativeTo(this);

        JTextArea textArea = new JTextArea(20, 40);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setText(student.showAnswer());
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
                new QNAUI(new Student()).setVisible(true);
            }
        });
    }
}