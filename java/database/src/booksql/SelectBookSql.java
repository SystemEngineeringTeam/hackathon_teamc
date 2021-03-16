package booksql;

import java.sql.*;
import java.util.ArrayList;

public class SelectBookSql {
    public ArrayList<BooksData> selectbooksql(){
        Connection conn = null;
        Statement stmt = null;
        ArrayList<BooksData> rtn = new ArrayList<BooksData>();
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mariadb://localhost/app_db", "hoge", "hogehoge");
            String dt1 = "SELECT *" +
                            "FROM books;";
            PreparedStatement sql = conn.prepareStatement(dt1);
            ResultSet hrs = sql.executeQuery();
            while (hrs.next()){
                BooksData ind = new BooksData(
                        hrs.getInt("id"),
                        hrs.getString("title"),
                        hrs.getString("author"),
                        hrs.getString("publisher"),
                        hrs.getString("publisheryear"),
                        hrs.getString("title")
                );
                String dt2 = "SELECT tags_detail" +
                        "FROM tags,books,book_tags" +
                        "WHERE tags.id = book_tags.tags_id" +
                        "AND book_tags.book_id = ?;";
                sql = conn.prepareStatement(dt2);
                sql.setInt(1,hrs.getInt("id"));
                ResultSet tmp = sql.executeQuery();
                StringBuilder st = new StringBuilder();
                if (tmp.next()){ st.append(tmp.getString("tags_detail")); }
                while (tmp.next()){
                    st.append(","+tmp.getString("tags_detail"));
                }
                ind.addtags(new String(st));
                rtn.add(ind);
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
        return rtn;
    }
}
