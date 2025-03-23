import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class OvertimeDateRegistration {

    // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/attendance_users?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input: User ID, Overtime Date, Overtime Start Time, Overtime End Time, Overtime Reason
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();

        System.out.print("Enter Overtime Date (YYYY-MM-DD): ");
        String overtimeDate = scanner.nextLine();

        System.out.print("Enter Overtime Start Time (HH:MM:SS): ");
        String overtimeStartTime = scanner.nextLine();

        System.out.print("Enter Overtime End Time (HH:MM:SS): ");
        String overtimeEndTime = scanner.nextLine();

        System.out.print("Enter Overtime Reason: ");
        String overtimeReason = scanner.nextLine();

        // Call method to insert overtime data into the database
        insertOvertime(userId, overtimeDate, overtimeStartTime, overtimeEndTime, overtimeReason);

        scanner.close();
    }

    // Method to insert overtime data into the database
    public static void insertOvertime(String userId, String overtimeDate, String overtimeStartTime, String overtimeEndTime, String overtimeReason) {
        String sql = "INSERT INTO overtime (user_id, overtime_date, overtime_start_time, overtime_end_time, overtime_reason) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set the values for the prepared statement
            stmt.setString(1, userId);
            stmt.setString(2, overtimeDate);
            stmt.setString(3, overtimeStartTime);
            stmt.setString(4, overtimeEndTime);
            stmt.setString(5, overtimeReason);

            // Execute the insertion
            int rowsAffected = stmt.executeUpdate();

            // Check if the insertion was successful
            if (rowsAffected > 0) {
                System.out.println("Overtime record successfully added.");
            } else {
                System.out.println("Failed to add overtime record.");
            }

        } catch (SQLException e) {
            System.out.println("Error while inserting overtime: " + e.getMessage());
        }
    }
}
