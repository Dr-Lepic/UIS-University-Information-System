import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookUI extends JFrame {
    public String username;

    public BookUI(Student student) {
        setTitle("Library");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);

        JPanel mainPanel = new JPanel(new GridLayout(5, 1));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(Color.decode("#28232a"));


        JButton showBorrowedButton = createStyledButton("Show Borrowed");
        JButton borrowButton = createStyledButton("Borrow");
        JButton returnButton = createStyledButton("Return");
        JButton backButton = createStyledButton("Back");

        showBorrowedButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Show borrowed books
                showBorrowedBookDialog(student);
            }
        });

        borrowButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openBorrowBookDialog(student);
            }
        });

        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openReturnBookDialog(student);
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new StudentHomePage(student).setVisible(true);
                dispose();
            }
        });

        mainPanel.add(showBorrowedButton);
        mainPanel.add(borrowButton);
        mainPanel.add(returnButton);

        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        backButtonPanel.setBackground(Color.decode("#28232a"));
        backButtonPanel.add(backButton);

        mainPanel.add(backButtonPanel);

        add(mainPanel);
        setLocationRelativeTo(null);
    }

    private void openBorrowBookDialog(Student student) {
        JDialog borrowDialog = new JDialog(this, "Borrow a Book", true);
        borrowDialog.setSize(400, 300);
        borrowDialog.setLayout(new GridLayout(4, 2, 10, 10));
        borrowDialog.setLocationRelativeTo(this);

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        JLabel authorLabel = new JLabel("Author:");
        JTextField authorField = new JTextField();
        JLabel dateLabel = new JLabel("Days:");
        JTextField dateField = new JTextField();
        JButton submitButton = new JButton("Submit");

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle the submission of the borrow request
                student.borrowBook(nameField.getText(),authorField.getText(),Integer.parseInt(dateField.getText()));
                borrowDialog.dispose();
            }
        });

        borrowDialog.add(nameLabel);
        borrowDialog.add(nameField);
        borrowDialog.add(authorLabel);
        borrowDialog.add(authorField);
        borrowDialog.add(dateLabel);
        borrowDialog.add(dateField);
        borrowDialog.add(new JLabel()); // Empty placeholder
        borrowDialog.add(submitButton);

        borrowDialog.setVisible(true);
    }

    private void openReturnBookDialog(Student student) {
        JDialog returnDialog = new JDialog(this, "Return a Book", true);
        returnDialog.setSize(400, 200);
        returnDialog.setLayout(new GridLayout(2, 2, 10, 10));
        returnDialog.setLocationRelativeTo(this);

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        JButton submitButton = new JButton("Submit");

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                student.returnBook(nameField.getText());
                returnDialog.dispose();
            }
        });

        returnDialog.add(nameLabel);
        returnDialog.add(nameField);
        returnDialog.add(new JLabel()); // Empty placeholder
        returnDialog.add(submitButton);

        returnDialog.setVisible(true);
    }

    private void showBorrowedBookDialog(Student student) {
        JDialog returnDialog = new JDialog(this, "Borrowed Books", true);
        returnDialog.setSize(400, 200);
        returnDialog.setLayout(new GridLayout(2, 0, 10, 10));
        returnDialog.setLocationRelativeTo(this);

        JTextArea textArea = new JTextArea(20, 40);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setText(student.showBorrowedBook());
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
                new BookUI(new Student()).setVisible(true);
            }
        });
    }
}
