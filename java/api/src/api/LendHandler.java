//
//package api;
//
////import api.src.lendsql.AddLendSql;
//import com.sun.net.httpserver.Headers;
//import com.sun.net.httpserver.HttpExchange;
//import com.sun.net.httpserver.HttpHandler;
////import database.booksql.BooksData;
////import database.booksql.SelectBookSql;
//import database.lendsql.AddLendSql;
////import database.lendsql.UpdateLendSql;
////import database.src.*;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.nio.charset.StandardCharsets;
////import java.sql.Array;
////import java.sql.ResultSet;
//import java.time.ZoneOffset;
//import java.time.ZonedDateTime;
//import java.time.format.DateTimeFormatter;
////import java.util.ArrayList;
//import java.util.Locale;
////import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//
////import java.util.ArrayList;
//
//public class LendHandler implements HttpHandler {
//
//    // HTTP リクエストを処理する
//    public void handle(HttpExchange t) throws IOException {
//        System.out.println("**************************************************");
//
//        String resBody = "";
//        ObjectMapper mapper = new ObjectMapper();
//
//        // 開始行を取得
//        String startLine =
//                t.getRequestMethod() + " " +
//                        t.getRequestURI().toString() + " " +
//                        t.getProtocol();
//        System.out.println(startLine);
//
//        // リクエストヘッダを取得
//        Headers reqHeaders = t.getRequestHeaders();
//        for (String name : reqHeaders.keySet()) {
//            System.out.println(name + ": " + reqHeaders.getFirst(name));
//        }
//
//        // リクエストボディを取得
//        InputStream is = t.getRequestBody();
//        byte[] b = is.readAllBytes();
//        is.close();
//        if (b.length != 0) {
//            System.out.println(); // 空行
//            System.out.println(new String(b, StandardCharsets.UTF_8));
//        }
//
//        // レスポンスボディを構築
//        // (ここでは Java 14 から正式導入された Switch Expressions と
//        //  Java 14 でプレビュー機能として使えるヒアドキュメント的な Text Blocks 機能を使ってみる)
//
//
//        switch(t.getRequestMethod().toLowerCase(Locale.ROOT)){
//            case "post":
//               int id = 0;
//               String email = "";
//               int add = AddLendSql.addlendsql(id, email);
//                resBody = mapper.writeValueAsString(add);
//                System.out.println(add);
//                break;
//
//
//        }
////        else if(t.getRequestMethod().toLowerCase(Locale.ROOT).equals("put")) {
////            int id = 0;
////
////            int put = UpdateLendSql.updatelendsql(id);
////
////
////        }
//
//
//
//        Headers resHeaders = t.getResponseHeaders();
//        resHeaders.set("Content-Type", "application/json");
//        resHeaders.set("Last-Modified",
//                ZonedDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.RFC_1123_DATE_TIME));
//        resHeaders.set("Server",
//                "MyServer (" +
//                        System.getProperty("java.vm.name") + " " +
//                        System.getProperty("java.vm.vendor") + " " +
//                        System.getProperty("java.vm.version") + ")");
//
//
//
//        // レスポンスヘッダを送信
//        int statusCode = 200;
//        long contentLength = resBody.getBytes(StandardCharsets.UTF_8).length;
//        t.sendResponseHeaders(statusCode, contentLength);
//
//        // レスポンスボディを送信
//        OutputStream os = t.getResponseBody();
//        os.write(resBody.getBytes());
//        os.close();
//
//    }
//}
//
//
//
