import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QNAUI extends JFrame {
    public String username;

    public QNAUI() {
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
            }
        });

        allAnswersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open "All Answers" page
            }
        });

        askQuestionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openAskQuestionDialog();
            }
        });

        answerQuestionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openAnswerQuestionDialog();
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new StudentHomePage(username).setVisible(true);
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

    private void openAskQuestionDialog() {
        JDialog askDialog = new JDialog(this, "Ask a Question", true);
        askDialog.setSize(400, 300);
        askDialog.setLayout(new GridLayout(4, 2, 10, 10));
        askDialog.setLocationRelativeTo(this);

        JLabel teacherLabel = new JLabel("Teacher:");
        JTextField teacherField = new JTextField();
        JLabel questionLabel = new JLabel("Question:");
        JTextField questionField = new JTextField();
        JLabel courseLabel = new JLabel("Course:");
        JTextField courseField = new JTextField();
        JButton submitButton = new JButton("Submit");

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle the submission of the question
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

    private void openAnswerQuestionDialog() {
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
                new QNAUI().setVisible(true);
            }
        });
    }
}