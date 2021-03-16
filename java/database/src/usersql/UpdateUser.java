package usersql;

import java.sql.*;

public class UpdateUser {
    public static int updateuser(String nm, String ml, String pss){
        Connection conn = null;
        Statement stmt = null;
        int flag = 0;
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mariadb://localhost/app_db", "hoge", "hogehoge");
            String dt = "UPDATE users " +
                            "SET name = ?, mailaddress = ?,pass = ? " +
                            "WHERE name = ? " +
                            "OR mailaddress = ?;";
            PreparedStatement sql = conn.prepareStatement(dt);
            sql.setString(1,nm);
            sql.setString(4,nm);
            sql.setString(2,ml);
            sql.setString(5,ml);
            sql.setString(3,pss);
            int hrs = sql.executeUpdate();
            if (hrs == 1){ flag = 1; }
        } catch(Exception e){
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
