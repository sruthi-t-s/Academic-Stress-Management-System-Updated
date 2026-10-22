import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Student Login");
        frame.setSize(400, 300);
        frame.setLayout(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(50, 50, 100, 30);
        frame.add(userLabel);

        JTextField username = new JTextField();
        username.setBounds(150, 50, 180, 30);
        frame.add(username);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(50, 100, 100, 30);
        frame.add(passLabel);

        JPasswordField password = new JPasswordField();
        password.setBounds(150, 100, 180, 30);
        frame.add(password);

        JButton login = new JButton("Login");
        login.setBounds(90, 160, 100, 30);
        frame.add(login);
        JButton register = new JButton("Register");
register.setBounds(210, 160, 100, 30);
frame.add(register);
register.addActionListener(e -> {
    new RegisterStudent();
});
        login.addActionListener(e -> {
    String user = username.getText();
    String pass = new String(password.getPassword());
if (LoginDatabase.checkLogin(
        username.getText(),
        new String(password.getPassword()))) {
        JOptionPane.showMessageDialog(frame, "Login Successful!");
        new Dashboard();
        frame.dispose();
    } else {
        JOptionPane.showMessageDialog(frame, "Invalid Username or Password");
    }
});
JButton forgot = new JButton("Forgot Password");
forgot.setBounds(115, 210, 170, 30);
frame.add(forgot);

forgot.addActionListener(e -> {
    new ForgotPassword();
});
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
