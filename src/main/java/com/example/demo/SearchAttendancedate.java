import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Scanner;

public class SearchAttendancedate {
    String DB_URL = "jdbc:mysql://localhost:3306/attendance_users?useSSL=false&serverTimezone=UTC";
    String USER = "root";
    String PASSWORD = "";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            System.out.println("DBに接続しました。");

            String sql = "SELECT * FROM attendance_date";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            System.out.println("勤務データ一覧：");

            while (resultSet.next()) {
                int attendance_date = resultSet.getInt("attendance_date");
                Date work_date = resultSet.getDate("work_date");
                Time start_time = resultSet.getTime("start_time");
                Time end_time = resultSet.getTime("end_time");
                Time break_time = resultSet.getTime("break_time");
                Time over_time = resultSet.getTime("over_time");

                System.out.printf("日付: %d, 開始時間: %s, 終了時間: %s, 休憩時間: %s, 残業時間: %s\n",
                    work_date, start_time.toString(), end_time.toString(), break_time.toString(), over_time.toString());
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("エラーが発生しました: " + e.getMessage());
        }
    }
}