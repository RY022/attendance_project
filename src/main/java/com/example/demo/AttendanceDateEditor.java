import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Scanner;

public class AttendanceDateEditor {

    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/attendance_users";
        String dbUser = "root";
        String dbPassword = "chaki8044";

        Scanner scanner = new Scanner(System.in);

        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword)) {

            int date = Integer.parseInt(scanner.nextLine());

            String selectSql = "SELECT * FROM attendance_records WHERE id = ?";
            PreparedStatement selectStmt = connection.prepareStatement(selectSql);
            selectStmt.setInt(1, date);
            ResultSet rs = selectStmt.executeQuery();

            if (!rs.next()) {
                System.out.println("データが見つかりません。");
                return;
            }

            Date oldDate = rs.getDate("date");
            Time oldStarttime = rs.getTime("start_time");
            Time oldEndtime = rs.getTime("end_time");
            Time oldBreaktime = rs.getTime("break_time");
            Time oldOvertime = rs.getTime("over_time");

            System.out.printf("日付: %s, 開始時間: %s, 終了時間: %s, 休憩時間: %s, 残業時間: %s", oldDate, oldStarttime, oldEndtime, oldBreaktime, oldOvertime);

            System.out.print("新しい日付 : ");
            String dateStr = scanner.nextLine();
            Date workdate = dateStr.isEmpty() ? oldDate : Date.valueOf(dateStr);

            System.out.print("新しい開始時間 : ");
            String startStr = scanner.nextLine();
            Time starttime = startStr.isEmpty() ? oldStarttime : Time.valueOf(startStr);

            System.out.print("新しい終了時間 : ");
            String endStr = scanner.nextLine();
            Time endtime = endStr.isEmpty() ? oldEndtime : Time.valueOf(endStr);
            
            System.out.print("新しい休憩時間 : ");
            String breakStr = scanner.nextLine();
            Time breaktime = breakStr.isEmpty() ? oldBreaktime : Time.valueOf(breakStr);
            
            System.out.print("新しい残業時間 : ");
            String overStr = scanner.nextLine();
            Time overtime = overStr.isEmpty() ? oldOvertime : Time.valueOf(overStr);

            String updateSql = "UPDATE attendance_records SET work_date = ?, work_date = ?, start_time = ?, end_time = ?, break_time = ?, over_time = ?";
            PreparedStatement updateStmt = connection.prepareStatement(updateSql);
            updateStmt.setDate(1, workdate);
            updateStmt.setTime(2, starttime);
            updateStmt.setTime(3, endtime);
            updateStmt.setTime(4, breaktime);
            updateStmt.setTime(5, overtime);

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