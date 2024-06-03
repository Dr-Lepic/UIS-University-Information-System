import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class AttendanceUI extends JFrame {
    private Map<String, Integer> studentAttendance;
    private int totalDays;

    public AttendanceUI(Teacher teacher) {
        studentAttendance = new HashMap<>();
        totalDays = 0;

        setTitle("Attendance");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(Color.decode("#28232a"));

        JPanel coursePanel = new JPanel(new GridLayout(2, 2, 10, 10));
        coursePanel.setBackground(Color.decode("#28232a"));

        String[] courses = {"Course1", "Course2", "Course3", "Course4"};
        for (String course : courses) {
            JButton courseButton = createStyledButton(course, 200, 40);
            courseButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    openCourseAttendance(course);
                }
            });
            coursePanel.add(courseButton);
        }

        mainPanel.add(coursePanel, BorderLayout.CENTER);

        JButton backButton = createStyledButton("Back", 100, 40);
        backButton.setPreferredSize(new Dimension(100, 40));
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new TeacherHomePage(teacher).setVisible(true);
                dispose();
            }
        });

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

    private void openCourseAttendance(String course) {
        JDialog dialog = new JDialog(this, "Attendance for " + course, true);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(600, 800);
        dialog.setLocationRelativeTo(this);

        JPanel studentPanel = new JPanel(new GridLayout(60, 4, 10, 10));
        studentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        studentPanel.setBackground(Color.decode("#28232a"));

        for (int i = 1; i <= 60; i++) {
            JTextField studentNameField = new JTextField("Student " + i);
            studentNameField.setHorizontalAlignment(SwingConstants.CENTER);

            JLabel attendanceLabel = new JLabel("Attendance: 0%", SwingConstants.CENTER);
            attendanceLabel.setForeground(Color.WHITE);

            JButton presentButton = createStyledButton("P", 50, 40);
            JButton absentButton = createStyledButton("A", 50, 40);

            presentButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String name = studentNameField.getText();
                    studentAttendance.putIfAbsent(name, 0);
                    studentAttendance.put(name, studentAttendance.get(name) + 1);
                    totalDays++;
                    updateAttendancePercentage(name, attendanceLabel);
                    JOptionPane.showMessageDialog(dialog, name + " is present.");
                }
            });

            absentButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String name = studentNameField.getText();
                    studentAttendance.putIfAbsent(name, 0);
                    totalDays++;
                    updateAttendancePercentage(name, attendanceLabel);
                    JOptionPane.showMessageDialog(dialog, name + " is absent.");
                }
            });

            studentPanel.add(studentNameField);
            studentPanel.add(attendanceLabel);
            studentPanel.add(presentButton);
            studentPanel.add(absentButton);
        }

        dialog.add(new JScrollPane(studentPanel), BorderLayout.CENTER);

        dialog.setVisible(true);
    }

    private void updateAttendancePercentage(String studentName, JLabel attendanceLabel) {
        int attendanceCount = studentAttendance.get(studentName);
        double percentage = (attendanceCount / (double) totalDays) * 100;
        attendanceLabel.setText("Attendance: " + String.format("%.2f", percentage) + "%");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AttendanceUI(new Teacher()).setVisible(true);
            }
        });
    }
}
