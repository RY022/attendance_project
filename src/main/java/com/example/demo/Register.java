import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Register {

    private static final String URL = "jdbc:mysql://localhost:3306/attendance_users";
    private static final String USER = "root";
    private static final String PASSWORD = "chaki8044"; 

    public static boolean registerUser(String id, String name, String password) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            String sql = "INSERT INTO users (user_id, user_name, password) VALUES (?, ?, ?)";

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);
            stmt.setString(2, name);
            stmt.setString(3, password);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {

            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        String user_id = "";
        String name = "";
        String password = "";

        boolean isRegistered = registerUser(user_id, name, password);

        if (isRegistered) {
            System.out.println("ユーザー登録成功！");
        } else {
            System.out.println("ユーザー登録失敗。");
        }
    }
}