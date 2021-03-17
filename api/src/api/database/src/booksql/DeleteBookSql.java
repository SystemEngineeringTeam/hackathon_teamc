package booksql;

import java.sql.*;

public class DeleteBookSql {
    public static int deletebooksql(int id) {
        Connection conn = null;
        Statement stmt = null;
        int flag = 0;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mariadb://localhost/app_db", "hoge", "hogehoge");
            String dt = "DElETE FROM books WHERE id = ?;";
            PreparedStatement sql = conn.prepareStatement(dt);
            sql.setInt(1, id);
            int hrs = sql.executeUpdate();
            if (hrs == 1) {
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

