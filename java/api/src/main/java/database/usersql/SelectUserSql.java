package database.usersql;

import api.UserHandler;

import java.sql.*;

public class SelectUserSql {

    public static UserHandler.selectdata selectusersql(String eml) {
        Connection conn = null;
        Statement stmt = null;
        UserHandler.selectdata rtn = new UserHandler.selectdata();
        rtn.setEmail(eml);
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mariadb://app_mariadb/app_db", "hoge", "hogehoge");
            String dt = "SELECT id " + "FROM users " + "WHERE mailaddress = ?;";
            PreparedStatement sql = conn.prepareStatement(dt);
            sql.setString(1, eml);
            ResultSet hrs = sql.executeQuery();
            if (hrs.next()) {
                rtn.setEmail(eml);
                String dt1 = "SELECT book_id" +
                                "FROM rental_lists" +
                                "WHERE user_id = ? " +
                                "AND lend_flag = 1;";
                sql = conn.prepareStatement(dt1);
                sql.setInt(1,hrs.getInt("id"));
                ResultSet rntllsts = sql.executeQuery();
                while (rntllsts.next()){
                    rtn.setList(rntllsts.getInt("book_id"));
                }
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
        return rtn;
    }
}
