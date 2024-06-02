import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeacherHomePage extends JFrame {
    private String username; // Assuming the username is passed to this class

    public TeacherHomePage(String username) {
        this.username = username;

        setTitle("Teacher Home Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(Color.decode("#28232a"));

        JLabel welcomeLabel = new JLabel("Welcome " + username);
        welcomeLabel.setForeground(Color.WHITE);
        mainPanel.add(welcomeLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        buttonPanel.setBackground(Color.decode("#28232a"));

        JButton qnaButton = createStyledButton("Q&A", 100, 40);
        JButton marksButton = createStyledButton("Marks", 100, 40);
        JButton notificationButton = createStyledButton("Notification", 100, 40);
        JButton attendanceButton = createStyledButton("Attendance", 100, 40);

        qnaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new TeacherQNAUI().setVisible(true);
            }
        });

        marksButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MarkUI().setVisible(true);
            }
        });

        notificationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new NotificationUI().setVisible(true);
            }
        });

        attendanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new AttendanceUI().setVisible(true);
            }
        });

        buttonPanel.add(qnaButton);
        buttonPanel.add(marksButton);
        buttonPanel.add(notificationButton);
        buttonPanel.add(attendanceButton);

        JButton logoutButton = createStyledButton("Logout", 100, 40);
        logoutButton.setPreferredSize(new Dimension(buttonPanel.getWidth(), 40));
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new HomePageUI().setVisible(true);
            }
        });

        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(logoutButton, BorderLayout.SOUTH);

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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TeacherHomePage("JaneDoe").setVisible(true);
            }
        });
    }
}