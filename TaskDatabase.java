import java.sql.*;
import javax.swing.DefaultListModel;


public class TaskDatabase {

    public static void addTask(int studentId, String taskName) {

        try {
            Connection con = DatabaseConnection.getConnection();

            String sql = "INSERT INTO study_tasks " +
                         "(student_id, task_name) VALUES (?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, studentId);
            ps.setString(2, taskName);

            ps.executeUpdate();

            System.out.println("Task saved successfully!");

            ps.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public static void loadTasks(
        int studentId,
        DefaultListModel<String> taskList) {

    try {
        Connection con = DatabaseConnection.getConnection();

        String sql = "SELECT task_name FROM study_tasks "
                   + "WHERE student_id = ?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, studentId);

        ResultSet rs = ps.executeQuery();

        taskList.clear();

        while (rs.next()) {
            taskList.addElement(rs.getString("task_name"));
        }

        rs.close();
        ps.close();
        con.close();

    } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
    }
}
public static boolean deleteTask(int studentId, String taskName) {

    try {
        Connection con = DatabaseConnection.getConnection();

        String sql = "DELETE FROM study_tasks "
                   + "WHERE student_id = ? AND task_name = ? "
                   + "LIMIT 1";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, studentId);
        ps.setString(2, taskName);

        int rows = ps.executeUpdate();

        ps.close();
        con.close();

        return rows > 0;

    } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
        return false;
    }
}
public static boolean addPlannedTask(
        int studentId, String taskName,
        String deadline, String priority) {

    String sql = "INSERT INTO study_tasks " +
        "(student_id, task_name, deadline, priority) " +
        "VALUES (?, ?, ?, ?)";

    try {
        Connection con = DatabaseConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, studentId);
        ps.setString(2, taskName);
        ps.setDate(3, java.sql.Date.valueOf(deadline));
        ps.setString(4, priority);

        int rows = ps.executeUpdate();

        ps.close();
        con.close();

        return rows > 0;

    } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
        return false;
    }
}
public static void loadSuggestedOrder(
        int studentId,
        DefaultListModel<String> taskList) {

    String sql = "SELECT task_name, deadline, priority " +
        "FROM study_tasks WHERE student_id = ? " +
        "ORDER BY " +
        "CASE WHEN deadline IS NULL THEN 1 ELSE 0 END, " +
        "deadline ASC, " +
        "CASE priority " +
        "WHEN 'High' THEN 1 " +
        "WHEN 'Medium' THEN 2 " +
        "WHEN 'Low' THEN 3 ELSE 4 END";

    try {
        Connection con = DatabaseConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, studentId);
        ResultSet rs = ps.executeQuery();

        taskList.clear();

        while (rs.next()) {
            String name = rs.getString("task_name");
            String date = rs.getString("deadline");
            String priority = rs.getString("priority");

            taskList.addElement(name + " | " +
                (date == null ? "No deadline" : date) +
                " | " + priority);
        }

        rs.close();
        ps.close();
        con.close();

    } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
    }
}
public static boolean updateTask(
        int studentId,
        String taskName,
        String newDeadline,
        String newPriority) {

    String sql = "UPDATE study_tasks " +
        "SET deadline = ?, priority = ? " +
        "WHERE student_id = ? AND task_name = ?";

    try {
        Connection con = DatabaseConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setDate(1, java.sql.Date.valueOf(newDeadline));
        ps.setString(2, newPriority);
        ps.setInt(3, studentId);
        ps.setString(4, taskName);

        int rows = ps.executeUpdate();

        ps.close();
        con.close();

        return rows > 0;

    } catch (Exception e) {
        System.out.println("Update Error: " + e.getMessage());
        return false;
    }
}
}