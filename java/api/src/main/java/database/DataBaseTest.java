package database;

import database.booksql.*;
import database.lendsql.AddLendSql;
import database.lendsql.UpdateLendSql;
import database.loginsql.LoginSql;
import database.usersql.AddUser;
import database.usersql.SelectUserSql;
import database.usersql.UpdateUser;
import database.usersql.UsersData;

import java.util.ArrayList;

public class DataBaseTest {
    public static void main(String args[]) {
        System.out.println("start");
        int test = 0;
        int ans;
        switch (test) {
        case 0:
            BooksData book = new BooksData();
            book.setBooksData(0, "幼女戦記", "カルロ・ゼン", "KADOKAWA", null, null);
            ans = AddBookSql.addbooksql(book);
            if (ans == 1) {
                System.out.println("addbook complete");
            }
            break;
        case 1:
            ArrayList<BooksData> data = SelectBookSql.selectbooksql();
            for (int i = 0; i < data.size(); i++) {
                System.out.println(data.get(i).id + data.get(i).title);
            }
            break;
        case 2:
            BooksData book1 = new BooksData();
            book1.setBooksData(1, "ようじょちぇんき", "かるろ・ぜん", "カドカワ", "2010", null);
            book1.settags("ラノベ,戦記");
            ans = UpdateBookSql.updatebooksql(book1);
            if (ans == 1) {
                System.out.println("updatebook complete");
            }
            break;
        case 3:
            ans = DeleteBookSql.deletebooksql(1);
            if (ans == 1) {
                System.out.println("deletebook complete");
            }
            break;
        case 4:
            ans = AddUser.adduser("k20001", "hoge", "hegohego");
            if (ans == 1) {
                System.out.println("adduser complete");
            }
            break;
        case 5:
            UsersData data1 = SelectUserSql.selectusersql("k20001");
            System.out.println(data1.name);
            break;
        case 6:
            ans = UpdateUser.updateuser("honehone", "k20001", "hoge");
            if (ans == 1) {
                System.out.println("updateuser complete");
            }
            break;
        case 7:
            ans = AddLendSql.addlendsql(1, "k20001");
            if (ans == 1) {
                System.out.println("addlend complete");
            }
            break;
        case 8:
            ans = UpdateLendSql.updatelendsql(1);
            if (ans == 1) {
                System.out.println("updatelend complete");
            }
            break;
        case 9:
            ans = LoginSql.loginsql("K20001", "hoge");
            if (ans == 1) {
                System.out.println("login complete");
            }
            break;
        default:
            break;

        }
    }
}
