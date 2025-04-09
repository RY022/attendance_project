import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class OvertimeDateRegistration {

	 private static final String DB_URL = "jdbc:mysql://localhost:3306/attendance_users?useSSL=false&serverTimezone=UTC";
	 private static final String USER = "root";
	 private static final String PASSWORD = "";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        String overtime_date = scanner.nextLine();
        String start_time = scanner.nextLine();
        String end_time = scanner.nextLine();
        String reason = scanner.nextLine();
        
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "INSERT INTO overtime (overtime_date, start_time, end_time, reason) VALUES (?, ?, ?, ?)";
            
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1,overtime_date);
                stmt.setString(2,start_time);
                stmt.setString(3,end_time);
                stmt.setString(4,reason);
                
                int rowsAffected = preparedStatement.executeUpdate();
                
                if (rowsAffected > 0) {
                    System.out.println("登録されました。");
                } else {
                    System.out.println("登録に失敗しました。");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (preparedStatement != null) preparedStatement.close();
                    if (connection != null) connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            scanner.close();
        }
    }
}