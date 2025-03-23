import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class AttendanceDateEditor {

    // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/attendance_users?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select operation:");
        System.out.println("1. Edit Attendance");
        System.out.println("2. Delete Attendance");
        System.out.print("Enter choice (1 or 2): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        if (choice == 1) {
            // Edit Attendance
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
            System.out.print("Enter Overtime (HH:MM:SS): ");
            String overtime = scanner.nextLine();

            editAttendance(userId, workDay, startTime, endTime, breakTime, overtime);
        } else if (choice == 2) {
            // Delete Attendance
            System.out.print("Enter User ID: ");
            String userId = scanner.nextLine();
            System.out.print("Enter Work Day (YYYY-MM-DD): ");
            String workDay = scanner.nextLine();

            deleteAttendance(userId, workDay);
        } else {
            System.out.println("Invalid choice. Exiting.");
        }

        scanner.close();
    }

    // Method to edit attendance
    public static void editAttendance(String userId, String workDay, String startTime, String endTime, String breakTime, String overtime) {
        String sql = "UPDATE attendance SET start_time = ?, end_time = ?, break_time = ?, overtime = ? WHERE user_id = ? AND work_day = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, startTime);
            stmt.setString(2, endTime);
            stmt.setString(3, breakTime);
            stmt.setString(4, overtime);
            stmt.setString(5, userId);
            stmt.setString(6, workDay);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Attendance record updated successfully.");
            } else {
                System.out.println("No matching record found to update.");
            }

        } catch (SQLException e) {
            System.out.println("Error while editing attendance: " + e.getMessage());
        }
    }

    // Method to delete attendance
    public static void deleteAttendance(String userId, String workDay) {
        String sql = "DELETE FROM attendance WHERE user_id = ? AND work_day = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, userId);
            stmt.setString(2, workDay);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Attendance record deleted successfully.");
            } else {
                System.out.println("No matching record found to delete.");
            }

        } catch (SQLException e) {
            System.out.println("Error while deleting attendance: " + e.getMessage());
        }
    }
}
