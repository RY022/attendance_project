import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class OvertimeDateRegistration {
    private static final String URL = "jdbc:mysql://localhost:3306/attendance_users?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "chaki8044";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("日付を入力してください（例: 2024-05-15）: ");
        String overtime_date = scanner.nextLine();

        System.out.print("開始時間を入力してください（例: 18:00）: ");
        String start_time = scanner.nextLine();

        System.out.print("終了時間を入力してください（例: 20:00）: ");
        String end_time = scanner.nextLine();

        System.out.print("理由を入力してください: ");
        String reason = scanner.nextLine();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("JDBCドライバが見つかりません。");
            e.printStackTrace();
            return;
        }

        // DB接続とINSERT処理
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(
                     "INSERT INTO overtime (overtime_date, start_time, end_time, reason) VALUES (?, ?, ?, ?)")) {

            stmt.setString(1, overtime_date);
            stmt.setString(2, start_time);
            stmt.setString(3, end_time);
            stmt.setString(4, reason);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("登録されました。");
            } else {
                System.out.println("登録に失敗しました。");
            }

        } catch (SQLException e) {
            System.out.println("SQLエラーが発生しました：");
            e.printStackTrace();
        }

        scanner.close();
    }
}
 