package booksql;

import java.sql.*;

public class UpdateBookSql {
    public static int updatebooksql(int bid,String ttl,String thr,String pblshr,String pblshryr,String cvr,String tags[]){

        Connection conn = null;
        Statement stmt = null;
        int flag = 0;
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mariadb://localhost/app_db", "hoge", "hogehoge");
            String dt1 = "UPDATE books " +
                            "SET title = ?, author = ?, publisher = ?, publisheryear = ?,cover_url = ?" +
                            "WHERE id = ?;";
            PreparedStatement sql = conn.prepareStatement(dt1);
            sql.setString(1,ttl);
            sql.setString(2,thr);
            sql.setString(3,pblshr);
            sql.setString(4,pblshryr);
            sql.setString(5,cvr);
            sql.setInt(6,bid);
            int hrs = sql.executeUpdate();
            if (hrs == 1){ flag = 1; }
            String dlttg = "DELETE FROM book_tags" +
                                "WHERE book_id = ?;";
            sql = conn.prepareStatement(dlttg);
            sql.setInt(1,bid);
            hrs = sql.executeUpdate();

            for (int i = 0;i < tags.length;i++){
                String dt2 = "SELECT COUNT(*) AS judg" +
                        "FROM tags" +
                        "WHERE tags_detail = '?';";
                sql = conn.prepareStatement(dt2);
                sql.setString(1,tags[i]);
                ResultSet jdg = sql.executeQuery();

                if (jdg.getInt("judg") == 0){
                    String addtg = "INSERT INTO tags(tags_detail)" +
                                    "VALUES (?);";
                    sql = conn.prepareStatement(addtg);
                    sql.setString(1,tags[i]);
                    hrs = sql.executeUpdate();
                }

                String slcttgid = "SELECT id" +
                                    "FROM tags" +
                                    "WHERE tags detail = '?';";
                sql = conn.prepareStatement(slcttgid);
                sql.setString(1,tags[i]);
                ResultSet tagid = sql.executeQuery();
                int tid = tagid.getInt("id");
                String btcnct = "INSERT INTO book_tags" +
                                    "VALUES(?,?);";
                sql = conn.prepareStatement(btcnct);
                sql.setInt(1,bid);
                sql.setInt(2,tid);
                hrs = sql.executeUpdate();
                if(hrs == 0){ flag = 0; }
            }


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
