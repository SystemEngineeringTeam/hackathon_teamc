package usersql;

import java.sql.*;

public class AddUser {
    public int adduser(String ml,String psswrd,String nm){
        Connection conn = null;
        Statement stmt = null;
        int flag = 0;
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mariadb://localhost/app_db", "hoge", "hogehoge");
            String dt = "INSERT INTO users(name,mailaddress,pass)" +
                            "VALUES (?,?,?);";
            PreparedStatement sql = conn.prepareStatement(dt);
            sql.setString(1,nm);
            sql.setString(2,psswrd);
            sql.setString(3,ml);
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
