import java.sql.*;

public class CheckTable {
    public static void checktable() {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mariadb://localhost/app_db", "hoge", "hogehoge");
            String dt = "SHOW TABLES FROM app_db";
            PreparedStatement sql = conn.prepareStatement(dt);
            ResultSet hrs = sql.executeQuery();
            if (hrs == null) {
                System.out.println("Table not found");
            }
            while (hrs.next()) {
                System.out.println(hrs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
