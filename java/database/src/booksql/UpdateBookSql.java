package booksql;

import java.sql.*;

public class UpdateBookSql {
    public int updatebooksql(int id,String ttl,String thr,String pblshr,String pblshryr,String cvr){

        Connection conn = null;
        Statement stmt = null;
        int flag = 0;
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mariadb://localhost/app_db", "hoge", "hogehoge");
            String dt = "UPDATE books " +
                            "SET title = ?, author = ?, publisher = ?, publisheryear = ?,cover_url = ?" +
                            "WHERE id = ?;";
            PreparedStatement sql = conn.prepareStatement(dt);
            sql.setString(1,ttl);
            sql.setString(2,thr);
            sql.setString(3,pblshr);
            sql.setString(4,pblshryr);
            sql.setString(5,cvr);
            sql.setInt(6,id);
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
