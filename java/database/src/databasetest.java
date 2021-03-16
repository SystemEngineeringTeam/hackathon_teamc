import booksql.*;
import lendsql.AddLendSql;
import lendsql.UpdateLendSql;
import loginsql.LoginSql;
import usersql.AddUser;
import usersql.SelectUserSql;
import usersql.UpdateUser;
import usersql.UsersData;

import java.util.ArrayList;

public class databasetest {
    public static void main(String args[]){
        int test = 0;
        int ans;
        switch (test){
            case 0:
                BooksData book = new BooksData();
                book.setBooksData(0,"幼女戦記","カルロ・ゼン","KADOKAWA",null,null);
                ans = AddBookSql.addbooksql(book);
                if(ans == 1){ System.out.println("addbook complete"); }
                break;
            case 1:
                ArrayList<BooksData> data = SelectBookSql.selectbooksql();
                for(int i = 0;i < 1;i++){
                    System.out.println(data.get(i));
                }
                break;
            case 2:
                BooksData book1 = new BooksData();
                book1.setBooksData(1,"ようじょちぇんき","かるろ・ぜん", "カドカワ","2010",null);
                book1.settags("ラノベ,戦記");
                ans = UpdateBookSql.updatebooksql(book1);
                if(ans == 1){ System.out.println("updatebook complete"); }
                break;
            case 3:
                ans = DeleteBookSql.deletebooksql(1);
                if(ans == 1){ System.out.println("deletebook complete"); }
                break;
            case 4:
                ans = AddUser.adduser("k20001","hoge","henoheno");
                if(ans == 1){ System.out.println("adduser complete"); }
                break;
            case 5:
                UsersData data1 = SelectUserSql.selectusersql("k20001");
                System.out.println(data1);
                break;
            case 6:
                ans = UpdateUser.updateuser("hogehoge","k20001","hoge");
                if(ans == 1){ System.out.println("updateuser complete"); }
                break;
            case 7:
                ans = AddLendSql.addlendsql(1,"k20001");
                if(ans == 1){ System.out.println("addlend complete"); }
                break;
            case 8:
                ans = UpdateLendSql.updatelendsql(1);
                if(ans == 1){ System.out.println("updatelend complete"); }
                break;
            case 9:
                ans = LoginSql.loginsql("K20008","hoge");
                if(ans == 1){ System.out.println("login complete"); }
                break;
            default:
                break;

        }
    }
}
