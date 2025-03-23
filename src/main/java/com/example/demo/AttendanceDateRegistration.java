import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class AttendanceRegistration {

    // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/attendance_users?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input: User ID, Workday, Start Time, End Time, Break Time
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();

        System.out.print("Enter Work Day (YYYY-MM-DD): ");
        String workDay = scanner.nextLine();

        System.out.print("Enter Start Time (HH:MM:SS): ");
        String startTime = scanner.nextLine();

        System.out.print("Enter End Time (HH:MM:SS): ");
        String endTime = scanner.nextLine();

        System.out.print("Enter Break Time (HH:MM:SS): ");
        String breakTime = scanner.nextLine();

        // Call method to insert data into the database
        insertAttendance(userId, workDay, startTime, endTime, breakTime);

        scanner.close();
    }

    // Method to insert attendance data into the database
    public static void insertAttendance(String userId, String workDay, String startTime, String endTime, String breakTime) {
        String sql = "INSERT INTO attendance (user_id, work_day, start_time, end_time, break_time) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set the values for the prepared statement
            stmt.setString(1, userId);
            stmt.setString(2, workDay);
            stmt.setString(3, startTime);
            stmt.setString(4, endTime);
            stmt.setString(5, breakTime);

            // Execute the insertion
            int rowsAffected = stmt.executeUpdate();

            // Check if the insertion was successful
            if (rowsAffected > 0) {
                System.out.println("Attendance record successfully added.");
            } else {
                System.out.println("Failed to add attendance record.");
            }

        } catch (SQLException e) {
            System.out.println("Error while inserting attendance: " + e.getMessage());
        }
    }
}
