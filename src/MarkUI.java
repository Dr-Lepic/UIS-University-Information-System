import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class MarkUI extends JFrame {
    public MarkUI() {
        setTitle("Mark Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(Color.decode("#28232a"));

        JLabel markLabel = new JLabel("Marks Management");
        markLabel.setForeground(Color.WHITE);
        markLabel.setFont(new Font("Arial", Font.BOLD, 20));
        mainPanel.add(markLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        buttonPanel.setBackground(Color.decode("#28232a"));

        JButton courseButton1 = createStyledButton("Course 1", 100, 40);
        JButton courseButton2 = createStyledButton("Course 2", 100, 40);
        JButton courseButton3 = createStyledButton("Course 3", 100, 40);
        JButton courseButton4 = createStyledButton("Course 4", 100, 40);

        courseButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openCoursePopup("Course 1");
            }
        });

        courseButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openCoursePopup("Course 2");
            }
        });

        courseButton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openCoursePopup("Course 3");
            }
        });

        courseButton4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openCoursePopup("Course 4");
            }
        });

        buttonPanel.add(courseButton1);
        buttonPanel.add(courseButton2);
        buttonPanel.add(courseButton3);
        buttonPanel.add(courseButton4);

        JButton backButton = createStyledButton("Back", 100, 40);
        backButton.setPreferredSize(new Dimension(buttonPanel.getWidth(), 40));
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new TeacherHomePage("JaneDoe").setVisible(true); // Assuming the username is "JaneDoe"
            }
        });

        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(backButton, BorderLayout.SOUTH);

        add(mainPanel);
        setLocationRelativeTo(null);
    }

    private JButton createStyledButton(String text, int width, int height) {
        JButton button = new JButton(text);
        button.setBackground(Color.decode("#6deeba"));
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setPreferredSize(new Dimension(width, height));
        return button;
    }

    private void openCoursePopup(String courseName) {
        JFrame courseFrame = new JFrame(courseName);
        courseFrame.setBounds(100, 100, 1200, 800);
        courseFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(Color.decode("#28232a"));

        JLabel courseLabel = new JLabel(courseName);
        courseLabel.setForeground(Color.WHITE);
        courseLabel.setFont(new Font("Arial", Font.BOLD, 20));
        mainPanel.add(courseLabel, BorderLayout.NORTH);

        JPanel studentPanel = new JPanel(new GridLayout(61, 10, 10, 10));
        studentPanel.setBackground(Color.decode("#28232a"));

        studentPanel.add(createStyledLabel("Student"));
        studentPanel.add(createStyledLabel("Quiz 1"));
        studentPanel.add(createStyledLabel("Quiz 2"));
        studentPanel.add(createStyledLabel("Quiz 3"));
        studentPanel.add(createStyledLabel("Quiz 4"));
        studentPanel.add(createStyledLabel("Mid"));
        studentPanel.add(createStyledLabel("Final"));
        studentPanel.add(createStyledLabel("Attendance"));
        studentPanel.add(createStyledLabel("Total"));
        studentPanel.add(createStyledLabel("Grade"));

        ArrayList<JTextField[]> studentMarks = new ArrayList<>();

        for (int i = 1; i <= 60; i++) {
            JTextField[] marks = new JTextField[10];
            marks[0] = new JTextField("Student " + i);
            studentPanel.add(marks[0]);
            for (int j = 1; j < 10; j++) {
                marks[j] = new JTextField();
                studentPanel.add(marks[j]);
            }
            studentMarks.add(marks);
        }

        mainPanel.add(new JScrollPane(studentPanel), BorderLayout.CENTER);

        JButton calculateButton = createStyledButton("Calculate", 100, 40);
        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (JTextField[] marks : studentMarks) {
                    try {
                        int[] quizzes = {
                                Integer.parseInt(marks[1].getText()),
                                Integer.parseInt(marks[2].getText()),
                                Integer.parseInt(marks[3].getText()),
                                Integer.parseInt(marks[4].getText())
                        };
                        int mid = Integer.parseInt(marks[5].getText());
                        int finalMark = Integer.parseInt(marks[6].getText());
                        int attendance = Integer.parseInt(marks[7].getText());

                        int total = calculateTotal(quizzes, mid, finalMark, attendance);
                        marks[8].setText(String.valueOf(total));
                        marks[9].setText(calculateGrade(total));
                    } catch (NumberFormatException ex) {
                        marks[8].setText("Error");
                        marks[9].setText("Error");
                    }
                }
            }
        });

        mainPanel.add(calculateButton, BorderLayout.SOUTH);

        courseFrame.add(mainPanel);
        courseFrame.setLocationRelativeTo(null);
        courseFrame.setVisible(true);
    }

    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(JLabel.CENTER);
        return label;
    }

    private int calculateTotal(int[] quizzes, int mid, int finalMark, int attendance) {
        Arrays.sort(quizzes);
        return quizzes[1] + quizzes[2] + quizzes[3] + mid + finalMark + attendance;
    }

    private String calculateGrade(int total) {
        double percentage = (total / 300.0) * 100;
        if (percentage >= 80) {
            return "A+";
        } else if (percentage >= 75) {
            return "A";
        } else if (percentage >= 70) {
            return "A-";
        } else if (percentage >= 65) {
            return "B+";
        } else if (percentage >= 60) {
            return "B";
        } else if (percentage >= 55) {
            return "B-";
        } else if (percentage >= 50) {
            return "C+";
        } else if (percentage >= 45) {
            return "C";
        } else if (percentage >= 40) {
            return "C-";
        } else {
            return "F";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MarkUI().setVisible(true);
            }
        });
    }
}
