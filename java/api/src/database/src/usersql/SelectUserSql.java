package database.src.usersql;

import java.sql.*;

public class SelectUserSql {
    public static UsersData selectusersql(String eml){
        Connection conn = null;
        Statement stmt = null;
        UsersData rtn = null;
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mariadb://localhost/app_db", "hoge", "hogehoge");
            String dt = "SELECT * " +
                            "FROM users " +
                            "WHERE mailaddress = ?;";
            PreparedStatement sql = conn.prepareStatement(dt);
            sql.setString(1,eml);
            ResultSet hrs = sql.executeQuery();
            rtn.SetData(hrs.getString("name"),hrs.getString("mailaddress"),hrs.getString("pass"));
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
        return rtn;
    }
}
