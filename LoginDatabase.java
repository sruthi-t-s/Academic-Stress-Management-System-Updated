import java.sql.*;
import java.util.Base64;
import java.security.MessageDigest;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class LoginDatabase {
    private static int studentId = -1;
    private static Student currentStudent;

public static int getStudentId() {
    return studentId;
}

    public static boolean checkLogin(
            String username, String password) {

        try {
            Connection con = DatabaseConnection.getConnection();
            String sql = "SELECT student_id, password_hash FROM students WHERE username = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();
            boolean valid = false;
            studentId = -1;
            currentStudent = null;

            if (rs.next()) {
                String stored = rs.getString("password_hash");
                String[] parts = stored.split(":");

                if (parts.length == 2) {
                    byte[] salt = Base64.getDecoder().decode(parts[0]);
                    byte[] expected = Base64.getDecoder().decode(parts[1]);

                    PBEKeySpec spec = new PBEKeySpec(
                        password.toCharArray(), salt, 600000, 256);

                    byte[] actual = SecretKeyFactory
                        .getInstance("PBKDF2WithHmacSHA256")
                        .generateSecret(spec).getEncoded();

                    valid = MessageDigest.isEqual(expected, actual);
                   if (valid) {
    currentStudent = new Student(
        rs.getInt("student_id"), username);

    studentId = currentStudent.getStudentId();
}
                    spec.clearPassword();
                }
            }

            rs.close();
            ps.close();
            con.close();

            return valid;

        } catch (Exception e) {
            System.out.println("Login Error: " + e.getMessage());
            return false;
        }
    }
}