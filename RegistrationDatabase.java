import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistrationDatabase {

    public static boolean registerStudent(
            String username, String email,
            String password, String securityAnswer) {

        String sql = "INSERT INTO students " +
            "(username, email, password_hash, security_answer_hash) " +
            "VALUES (?, ?, ?, ?)";

        try {
            String hashedPassword =
                PasswordUtil.hashPassword(password);

            String hashedAnswer =
                PasswordUtil.hashPassword(
                    securityAnswer.trim().toLowerCase()
                );

            Connection con =
                DatabaseConnection.getConnection();

            PreparedStatement ps =
                con.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, email);
            ps.setString(3, hashedPassword);
            ps.setString(4, hashedAnswer);

            int result = ps.executeUpdate();

            ps.close();
            con.close();

            return result > 0;

        } catch (SQLException e) {
            System.out.println(
                "Registration database error: " +
                e.getMessage());
            return false;

        } catch (Exception e) {
            System.out.println(
                "Registration error: " +
                e.getMessage());
            return false;
        }
    }
}