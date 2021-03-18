package api;

import database.booksql.*;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import database.usersql.SelectUserSql;
import database.usersql.UsersData;
//import database.src.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
//import java.sql.Array;
//import java.sql.ResultSet;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

//import java.util.ArrayList;

public class BookHandler implements HttpHandler {

    // HTTP リクエストを処理する
    public void handle(HttpExchange t) throws IOException {
        t.getRequestHeaders().add("Access-Control-Allow-Headers","x-prototype-version,x-requested-with");
        t.getRequestHeaders().add("Access-Control-Allow-Methods","*");
        t.getRequestHeaders().add("Access-Control-Allow-Origin","*");
        String resBody = "";
        System.out.println("**************************************************");
        ObjectMapper mapper = new ObjectMapper();


        // 開始行を取得
        String startLine = t.getRequestMethod() + " " + t.getRequestURI().toString() + " " + t.getProtocol();
        System.out.println(startLine);

        // リクエストヘッダを取得
        Headers reqHeaders = t.getRequestHeaders();
        for (String name : reqHeaders.keySet()) {
            System.out.println(name + ": " + reqHeaders.getFirst(name));
        }

        // リクエストボディを取得
        InputStream is = t.getRequestBody();
        byte[] b = is.readAllBytes();
//        if (b.length != 0) {
//            InputStreamReader inputStreamReader = new InputStreamReader(is);
//            Stream<String> streamOfString = new BufferedReader(inputStreamReader).lines();
//            String streamToString = streamOfString.collect(Collectors.joining());
//
//            System.out.println(b);
//        }
        is.close();

        // レスポンスボディを構築
        // (ここでは Java 14 から正式導入された Switch Expressions と
        // Java 14 でプレビュー機能として使えるヒアドキュメント的な Text Blocks 機能を使ってみる)
        String reqBody;
        switch (t.getRequestMethod().toLowerCase(Locale.ROOT)) {
            case "get":
                ArrayList<BooksData> getBookData = SelectBookSql.selectbooksql();
                resBody = mapper.writeValueAsString(getBookData);
                System.out.println(resBody);
                break;

            case "post":
                reqBody = new String(b, StandardCharsets.UTF_8);
                BooksData book = mapper.readValue(reqBody, BooksData.class);
                int post = AddBookSql.addbooksql(book);
                resBody = mapper.writeValueAsString(post);
                System.out.println(resBody);
                break;

            case "delete":
                reqBody = new String(b, StandardCharsets.UTF_8);
                DeleteBookData bookid = mapper.readValue(reqBody, DeleteBookData.class);
                int delete = DeleteBookSql.deletebooksql(bookid.bookID);
                resBody = mapper.writeValueAsString(delete);
                System.out.println(resBody);
                break;

            // case "put":
            // String title ="";
            // String author ="";
            // String publisher = "";
            // String publishYear = "";
            // String cover = "";
            // int id = 0;
            // String tags[] = new String[5];
            // int put =
            // UpdateBookSql.updatebooksql(id,title,author,publisher,publishYear,cover,tags);
            default:
                break;
        }

        Headers resHeaders = t.getResponseHeaders();
        resHeaders.set("Content-Type", "application/json");
        resHeaders.set("Last-Modified", ZonedDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.RFC_1123_DATE_TIME));
        resHeaders.set("Server", "MyServer (" + System.getProperty("java.vm.name") + " "
                + System.getProperty("java.vm.vendor") + " " + System.getProperty("java.vm.version") + ")");

        // レスポンスヘッダを送信
        int statusCode = 200;
        long contentLength = resBody.getBytes(StandardCharsets.UTF_8).length;
        t.sendResponseHeaders(statusCode, contentLength);

        // レスポンスボディを送信
        OutputStream os = t.getResponseBody();
        os.write(resBody.getBytes());
        os.close();

    }

    public class DeleteBookData{
        public int bookID;
    }
}
