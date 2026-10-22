import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class CreateStudent {

    public static void main(String[] args) {

        try {
            Scanner sc = new Scanner(System.in);

            System.out.print("Enter username: ");
            String username = sc.nextLine();

            System.out.print("Enter password: ");
            String password = sc.nextLine();

            String hash = PasswordUtil.hashPassword(password);

            Connection con = DatabaseConnection.getConnection();

            String sql = "INSERT INTO students " +
                         "(username, password_hash) VALUES (?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, hash);

            ps.executeUpdate();

            System.out.println("Student Created Successfully!");

            ps.close();
            con.close();
            sc.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}