package lendsql;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddLendSql {
    public int addlentsql(int bid,String eml){
        int flag = 0;
        Connection conn = null;
        Statement stmt = null;
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mariadb://localhost/app_db", "hoge", "hogehoge");
            String dt1 = "SELECT id" +
                            "FROM users" +
                            "WHERE mailaddress = ?;";
            PreparedStatement sql = conn.prepareStatement(dt1);
            sql.setString(1,eml);
            ResultSet hrs = sql.executeQuery();
            int uid = hrs.getInt("id");
            String dt2 = "INSERT INTO rental_lists" +
                            "VALUES(?,?,?,0);";
            String str = new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
            java.sql.Date ddln = java.sql.Date.valueOf(str);

            sql = conn.prepareStatement(dt2);
            sql.setInt(1,bid);
            sql.setInt(2,uid);
            sql.setDate(3,ddln);

        } catch(Exception e) {
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
