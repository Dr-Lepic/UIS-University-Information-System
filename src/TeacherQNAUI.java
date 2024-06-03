import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeacherQNAUI extends JFrame {
    public TeacherQNAUI(Teacher teacher) {
        setTitle("Teacher Q&A");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600); // Adjust the window size if needed

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(Color.decode("#28232a"));

        // Create buttons panel
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        buttonPanel.setBackground(Color.decode("#28232a"));

        // Create buttons
        JButton allQuestionsButton = createStyledButton("All Questions", 200, 40);
        JButton allAnswersButton = createStyledButton("All Answers", 200, 40);
        JButton answerQuestionButton = createStyledButton("Answer a Question", 200, 40);

        // Add action listeners to buttons
        allQuestionsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open All Questions page
                JOptionPane.showMessageDialog(TeacherQNAUI.this, teacher.showQuestion());
            }
        });

        allAnswersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open All Answers page
                JOptionPane.showMessageDialog(TeacherQNAUI.this, teacher.showAnswer());
            }
        });

        answerQuestionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open Answer a Question popup
                openAnswerQuestionPopup(teacher);
            }
        });

        // Add buttons to the button panel
        buttonPanel.add(allQuestionsButton);
        buttonPanel.add(allAnswersButton);
        buttonPanel.add(answerQuestionButton);

        // Create the back button
        JButton backButton = createStyledButton("Back", 100, 40);
        backButton.setPreferredSize(new Dimension(100, 40));
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Go back to the previous page
                new TeacherHomePage(teacher).setVisible(true);
                dispose();
            }
        });

        // Add the button panel and back button to the main panel
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(backButton, BorderLayout.SOUTH);

        add(mainPanel);
        setLocationRelativeTo(null); // Center the window
    }

    private JButton createStyledButton(String text, int width, int height) {
        JButton button = new JButton(text);
        button.setBackground(Color.decode("#6deeba")); // Set button background color
        button.setForeground(Color.BLACK); // Set text color to black
        button.setFont(new Font("Arial", Font.PLAIN, 14)); // Set font and size
        button.setPreferredSize(new Dimension(width, height)); // Set preferred size
        return button;
    }

    private void openAnswerQuestionPopup(Teacher teacher) {
        JDialog dialog = new JDialog(this, "Answer a Question", true);
        dialog.setLayout(new GridLayout(3, 2, 10, 10));
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);


        dialog.add(new JLabel("Question no:"));
        JTextArea questionArea = new JTextArea();
        questionArea.setRows(3); // Set the number of rows for the question textarea
        questionArea.setWrapStyleWord(true);
        questionArea.setLineWrap(true);
        JScrollPane questionScrollPane = new JScrollPane(questionArea);
        dialog.add(questionScrollPane);


        dialog.add(new JLabel("Answer:"));
        JTextArea answerField = new JTextArea();
        answerField.setRows(3); // Set the number of rows for the answer textarea
        answerField.setWrapStyleWord(true);
        answerField.setLineWrap(true);
        JScrollPane answerScrollPane = new JScrollPane(answerField);
        dialog.add(answerScrollPane);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle the answer submission
                int question = Integer.parseInt(questionArea.getText());
                String answer = answerField.getText();
                teacher.ansQuestion(teacher.questionList.get(question-1), answer);

                // Process the answer (e.g., save to a database or display a confirmation)
                JOptionPane.showMessageDialog(dialog, "Answer submitted for: " + question);
                dialog.dispose();
            }
        });

        dialog.add(new JLabel());
        dialog.add(submitButton);

        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TeacherQNAUI(new Teacher()).setVisible(true);
            }
        });
    }
}
