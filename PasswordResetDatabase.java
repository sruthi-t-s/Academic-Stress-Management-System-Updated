import java.sql.*;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.MessageDigest;

public class PasswordResetDatabase {

    public static boolean resetPassword(
            String username, String answer,
            String newPassword) {

        String sql = "SELECT security_answer_hash " +
                     "FROM students WHERE username = ?";

        try (Connection con =
                DatabaseConnection.getConnection();
             PreparedStatement ps =
                con.prepareStatement(sql)) {

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                return false;
            }

            String stored = rs.getString(
                "security_answer_hash");

            if (stored == null) {
                return false;
            }

            String[] parts = stored.split(":");
            if (parts.length != 2) return false;

            byte[] salt = Base64.getDecoder()
                .decode(parts[0]);

            byte[] expected = Base64.getDecoder()
                .decode(parts[1]);

            PBEKeySpec spec = new PBEKeySpec(
                answer.trim().toLowerCase().toCharArray(),
                salt, 600000, expected.length * 8);

            byte[] actual = SecretKeyFactory
                .getInstance("PBKDF2WithHmacSHA256")
                .generateSecret(spec).getEncoded();

            spec.clearPassword();

            if (!MessageDigest.isEqual(expected, actual)) {
                return false;
            }

            String updateSql =
                "UPDATE students SET password_hash = ? " +
                "WHERE username = ?";

            try (PreparedStatement update =
                    con.prepareStatement(updateSql)) {

                update.setString(1,
                    PasswordUtil.hashPassword(newPassword));
                update.setString(2, username);

                return update.executeUpdate() > 0;
            }

        } catch (Exception e) {
            System.out.println(
                "Password reset error: " + e.getMessage());
            return false;
        }
    }
}