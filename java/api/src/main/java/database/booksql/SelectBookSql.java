package database.booksql;

import java.sql.*;
import java.util.ArrayList;

public class SelectBookSql {
    public static ArrayList<BooksData> selectbooksql(){
        Connection conn = null;
        Statement stmt = null;
        ArrayList<BooksData> rtn = new ArrayList<BooksData>();
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mariadb://app_mariadb/app_db", "hoge", "hogehoge");
            String dt1 = "SELECT * " +
                            "FROM books;";
            PreparedStatement sql = conn.prepareStatement(dt1);
            ResultSet hrs = sql.executeQuery();

            while (hrs.next()){
                BooksData ind = new BooksData ();
                ind.setBooksData(
                        hrs.getInt("id"),
                        hrs.getString("title"),
                        hrs.getString("author"),
                        hrs.getString("publisher"),
                        hrs.getString("publishyear"),
                        hrs.getString("title"));
                String dt2 = "SELECT tags_detail " +
                        "FROM tags,books,book_tags " +
                        "WHERE tags.id = book_tags.tags_id " +
                        "AND book_tags.book_id = ?;";
                sql = conn.prepareStatement(dt2);
                sql.setInt(1,hrs.getInt("id"));
                ResultSet tmp = sql.executeQuery();
                StringBuilder st = new StringBuilder();
                if (tmp.next()){ st.append(tmp.getString("tags_detail")); }
                while (tmp.next()){
                    st.append(","+tmp.getString("tags_detail"));
                }
                ind.settags(new String(st));
                String checklend = "SELECT COUNT(*) AS flg " +
                                        "FROM rental_lists " +
                                        "WHERE book_id = ? " +
                                        "AND lend_flag = 1;";
                sql = conn.prepareStatement(checklend);
                sql.setInt(1,ind.id);
                tmp = sql.executeQuery();
                if (tmp.next() && tmp.getInt("flg") > 0){
                    ind.setlend();
                }
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
