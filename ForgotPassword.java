import javax.swing.*;

public class ForgotPassword {

    public ForgotPassword() {

        JFrame frame = new JFrame("Forgot Password");
        frame.setSize(420, 370);
        frame.setLayout(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(30, 30, 140, 30);
        frame.add(userLabel);

        JTextField username = new JTextField();
        username.setBounds(180, 30, 190, 30);
        frame.add(username);

        JLabel question = new JLabel("Childhood Nickname:");
        question.setBounds(30, 80, 150, 30);
        frame.add(question);

        JTextField answer = new JTextField();
        answer.setBounds(180, 80, 190, 30);
        frame.add(answer);

        JLabel passLabel = new JLabel("New Password:");
        passLabel.setBounds(30, 130, 140, 30);
        frame.add(passLabel);

        JPasswordField newPassword = new JPasswordField();
        newPassword.setBounds(180, 130, 190, 30);
        frame.add(newPassword);

        JLabel confirmLabel = new JLabel("Confirm Password:");
        confirmLabel.setBounds(30, 180, 150, 30);
        frame.add(confirmLabel);

        JPasswordField confirm = new JPasswordField();
        confirm.setBounds(180, 180, 190, 30);
        frame.add(confirm);

        JButton reset = new JButton("Reset Password");
        reset.setBounds(120, 240, 170, 35);
        frame.add(reset);

        reset.addActionListener(e -> {

            String user = username.getText().trim();
            String secret = answer.getText().trim();
            String pass = new String(newPassword.getPassword());
            String confirmPass = new String(confirm.getPassword());

            if (user.isEmpty() || secret.isEmpty()
                    || pass.isEmpty()) {
                JOptionPane.showMessageDialog(frame,
                    "Please fill all fields!");
                return;
            }

            if (pass.length() < 6) {
                JOptionPane.showMessageDialog(frame,
                    "Password must have at least 6 characters");
                return;
            }

            if (!pass.equals(confirmPass)) {
                JOptionPane.showMessageDialog(frame,
                    "Passwords do not match!");
                return;
            }

            boolean success =
                PasswordResetDatabase.resetPassword(
                    user, secret, pass);

            if (success) {
                JOptionPane.showMessageDialog(frame,
                    "Password Reset Successful!");
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(frame,
                    "Invalid Username or Security Answer!");
            }
        });

        frame.setDefaultCloseOperation(
            JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}