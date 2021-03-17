package database.lendsql;

import java.sql.*;

public class UpdateLendSql {
    public static int updatelendsql(int bid) {
        Connection conn = null;
        Statement stmt = null;
        int flag = 0;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mariadb://localhost/app_db", "hoge", "hogehoge");
            String dt = "UPDATE rental_lists " + "SET lend_flag = 0 " + "WHERE book_id = ? " + "AND lend_flag = 1;";
            PreparedStatement sql = conn.prepareStatement(dt);
            sql.setInt(1, bid);
            int hrs = sql.executeUpdate();
            if (hrs > 1) {
                flag = 1;
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
