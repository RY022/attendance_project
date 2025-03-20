import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class UserDatabase {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/cri_sortable?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Database connection setup
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            // Prompt for user ID
            System.out.print("Enter User ID: ");
            String userId = scanner.nextLine();

            if (!userId.isEmpty()) {
                insertUser(conn, userId, "user_id");
            }

            // Prompt for password
            System.out.print("Enter Password: ");
            String password = scanner.nextLine();

            if (!password.isEmpty()) {
                insertUser(conn, password, "password");
            }

            // Redirect to the main page (simulate redirection)
            System.out.println("Redirecting to http://localhost:8001/");
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    private static void insertUser(Connection conn, String value, String columnName) {
        String sql = "INSERT INTO users (" + columnName + ") VALUES (?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, value);
            stmt.executeUpdate();
            System.out.println("Data inserted successfully into column: " + columnName);
        } catch (SQLException e) {
            System.out.println("Failed to insert data: " + e.getMessage());
        }
    }
}