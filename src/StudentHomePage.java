import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentHomePage extends JFrame {
    private String username; // Assuming the username is passed to this class

    public StudentHomePage(String username) {
        this.username = username;


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
                new QNAUI().setVisible(true);
                dispose();
            }
        });

        noteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new NoteUI().setVisible(true);
            }
        });

        bookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new BookUI().setVisible(true);
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new StudentHomePage("JohnDoe").setVisible(true);
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