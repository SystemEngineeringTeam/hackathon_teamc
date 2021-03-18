package database.loginsql;

import java.sql.*;

public class LoginSql {
    public static int loginsql(String eml, String psswrd) {
        Connection conn = null;
        Statement stmt = null;
        int flag = 0;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mariadb://app_mariadb/app_db", "hoge", "hogehoge");
            String dt = "SELECT COUNT(*) AS judg " + "FROM users " + "WHERE mailaddress = ? " + "AND pass = ?;";
            PreparedStatement sql = conn.prepareStatement(dt);
            sql.setString(1, eml);
            sql.setString(2, psswrd);
            ResultSet hrs = sql.executeQuery();
            if (hrs.next()) {
                flag = hrs.getInt("judg");
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
        return flag;
    }
}
