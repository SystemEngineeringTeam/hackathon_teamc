package database.booksql;

import java.sql.*;

public class AddBookSql {
    public static int addbooksql(BooksData book){
        Connection conn = null;
        Statement stmt = null;
        int flag = 0;
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mariadb://localhost/app_db", "hoge", "hogehoge");
            String dt = "INSERT INTO books(title,author,publisher,publisheryear,cover_url) " +
                            "VALUES (?,?,?,?,?);";
            PreparedStatement sql = conn.prepareStatement(dt);
            sql.setString(1, book.title);
            sql.setString(2, book.author);
            sql.setString(3, book.publisher);
            sql.setString(4, book.pyear);
            sql.setString(5, book.cover_url);
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
