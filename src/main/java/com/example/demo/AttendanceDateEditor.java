import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Scanner;

public class AttendanceUpdate {

    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/your_database";
        String dbUser = "your_username";
        String dbPassword = "your_password";

        Scanner scanner = new Scanner(System.in);

        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword)) {
            System.out.println("DBに接続しました。");

            // ID指定
            System.out.print("編集したい勤務データのIDを入力してください: ");
            int id = Integer.parseInt(scanner.nextLine());

            // 元データを表示
            String selectSql = "SELECT * FROM work_attendance WHERE id = ?";
            PreparedStatement selectStmt = connection.prepareStatement(selectSql);
            selectStmt.setInt(1, id);
            ResultSet rs = selectStmt.executeQuery();

            if (!rs.next()) {
                System.out.println("指定されたIDのデータが見つかりません。");
                return;
            }

            String oldName = rs.getString("employee_name");
            Date oldDate = rs.getDate("date");
            Time oldStart = rs.getTime("start_time");
            Time oldEnd = rs.getTime("end_time");

            System.out.println("現在のデータ:");
            System.out.printf("名前: %s, 日付: %s, 開始: %s, 終了: %s\n", oldName, oldDate, oldStart, oldEnd);

            // 入力（空欄なら元のまま）
            System.out.print("新しい名前（そのままならEnter）: ");
            String name = scanner.nextLine();
            if (name.isEmpty()) name = oldName;

            System.out.print("新しい日付 (yyyy-mm-dd): ");
            String dateStr = scanner.nextLine();
            Date date = dateStr.isEmpty() ? oldDate : Date.valueOf(dateStr);

            System.out.print("新しい開始時間 (HH:mm:ss): ");
            String startStr = scanner.nextLine();
            Time start = startStr.isEmpty() ? oldStart : Time.valueOf(startStr);

            System.out.print("新しい終了時間 (HH:mm:ss): ");
            String endStr = scanner.nextLine();
            Time end = endStr.isEmpty() ? oldEnd : Time.valueOf(endStr);

            // 更新クエリ
            String updateSql = "UPDATE work_attendance SET employee_name = ?, date = ?, start_time = ?, end_time = ? WHERE id = ?";
            PreparedStatement updateStmt = connection.prepareStatement(updateSql);
            updateStmt.setString(1, name);
            updateStmt.setDate(2, date);
            updateStmt.setTime(3, start);
            updateStmt.setTime(4, end);
            updateStmt.setInt(5, id);

            int rowsAffected = updateStmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("勤務データを更新しました！");
            } else {
                System.out.println("更新に失敗しました。");
            }

        } catch (SQLException e) {
            System.out.println("DBエラー: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("入力形式エラー: " + e.getMessage());
        }
    }
}