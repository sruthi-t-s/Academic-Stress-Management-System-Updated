import java.sql.*;

public class StressDatabase {

    public static boolean saveResult(
            int studentId, int score, String level) {

        try {
            Connection con = DatabaseConnection.getConnection();

            String sql = "INSERT INTO stress_results " +
                         "(student_id, stress_score, stress_level) " +
                         "VALUES (?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, studentId);
            ps.setInt(2, score);
            ps.setString(3, level);

            ps.executeUpdate();

            ps.close();
            con.close();

            return true;

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
}