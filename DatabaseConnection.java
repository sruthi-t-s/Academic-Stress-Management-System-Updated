import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection getConnection()
            throws SQLException {

        String url = "jdbc:mysql://localhost:3306/academic_stress_db";
        String username = "root";

        String password = System.getenv("MYSQL_PASSWORD");

        return DriverManager.getConnection(
            url, username, password);
    }
}