import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NotificationUI extends JFrame {
    public NotificationUI() {
        setTitle("Notification Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(Color.decode("#28232a"));

        JLabel notificationLabel = new JLabel("Send Notification");
        notificationLabel.setForeground(Color.WHITE);
        notificationLabel.setFont(new Font("Arial", Font.BOLD, 20));
        mainPanel.add(notificationLabel, BorderLayout.NORTH);

        JPanel fieldsPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        fieldsPanel.setBackground(Color.decode("#28232a"));

        JLabel courseLabel = new JLabel("Course:");
        courseLabel.setForeground(Color.WHITE);
        JTextField courseField = new JTextField();

        JLabel notificationTextLabel = new JLabel("Notification:");
        notificationTextLabel.setForeground(Color.WHITE);
        JTextField notificationField = new JTextField();

        fieldsPanel.add(courseLabel);
        fieldsPanel.add(courseField);
        fieldsPanel.add(notificationTextLabel);
        fieldsPanel.add(notificationField);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(Color.decode("#28232a"));

        JButton submitButton = createStyledButton("Submit", 100, 40);
        JButton backButton = createStyledButton("Back", 100, 40);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle the submit action (e.g. save the notification)
                String course = courseField.getText();
                String notification = notificationField.getText();
                // Implement submission logic
                JOptionPane.showMessageDialog(null, "Notification sent for course: " + course);
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new TeacherHomePage("JaneDoe").setVisible(true); // Assuming the username is "JaneDoe"
            }
        });

        buttonPanel.add(submitButton);
        buttonPanel.add(backButton);

        mainPanel.add(fieldsPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

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
                new NotificationUI().setVisible(true);
            }
        });
    }
}
