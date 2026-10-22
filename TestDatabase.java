import java.sql.Connection;

public class TestDatabase {

    public static void main(String[] args) {

        try {
            Connection con =
                DatabaseConnection.getConnection();

            System.out.println("Database Connected Successfully!");

            con.close();
        }
        catch (Exception e) {
            System.out.println("Connection Failed!");
            System.out.println(e.getMessage());
        }
    }
}