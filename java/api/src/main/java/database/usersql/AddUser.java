package database.usersql;

import java.sql.*;

public class AddUser {
    public static int adduser(UsersData usersData) {
        Connection conn = null;
        Statement stmt = null;
        int flag = 0;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mariadb://app_mariadb/app_db", "hoge", "hogehoge");
            String dt = "INSERT INTO users(name,mailaddress,pass) " + "VALUES (?,?,?);";
            PreparedStatement sql = conn.prepareStatement(dt);
            sql.setString(1, usersData.name);
            sql.setString(2, usersData.mailaddress);
            sql.setString(3, usersData.pass);
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
