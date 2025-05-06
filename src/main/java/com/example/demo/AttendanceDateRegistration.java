import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class AttendanceDateRegistration {

    private static final String DB_URL = "jdbc:mySQL://localhost:3306/attendance_users";
    private static final String USER = "root";
    private static final String PASSWORD = "chaki8044";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String work_date = scanner.nextLine();
        String start_time = scanner.nextLine();
        String end_time = scanner.nextLine();
        String break_time = scanner.nextLine();

        String url = "jdbc:mySQL://localhost/attendance_users";
        String user = "root";
        String password = "chaki8044";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
            connection = DriverManager.getConnection(url, user, password);
            
            String sql = "INSERT INTO work_log (work_date, start_time, end_time, break_time) VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setString(1,work_date);
            preparedStatement.setString(2,start_time);
            preparedStatement.setString(3,end_time);
            preparedStatement.setString(4,break_time);
            
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