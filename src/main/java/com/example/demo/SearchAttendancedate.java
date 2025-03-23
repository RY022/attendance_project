import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AttendanceSearch {
    // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/attendance_users?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask for the user ID to search for workdays
        System.out.print("Enter User ID to search for workdays: ");
        String userId = scanner.nextLine();

        // Call method to search for workdays and print the results
        List<String> workdays = getWorkdaysForUser(userId);

        if (workdays.isEmpty()) {
            System.out.println("No workdays found for user ID: " + userId);
        } else {
            System.out.println("Workdays for user ID " + userId + ":");
            for (String workday : workdays) {
                System.out.println(workday);
            }
        }
    }

    // Method to get workdays for a specific user from the database
    public static List<String> getWorkdaysForUser(String userId) {
        List<String> workdays = new ArrayList<>();

        // Establish connection to the database
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String sql = "SELECT work_day FROM attendance WHERE user_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, userId); // Bind userId to the query

                // Execute the query and get the result set
                ResultSet resultSet = stmt.executeQuery();

                // Process the result set and add workdays to the list
                while (resultSet.next()) {
                    String workDay = resultSet.getString("work_day");
                    workdays.add(workDay);
                }
            } catch (SQLException e) {
                System.out.println("Error while querying the database: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Error while connecting to the database: " + e.getMessage());
        }

        return workdays;
    }
}
