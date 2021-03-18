
package api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import database.usersql.AddUser;
import database.usersql.SelectUserSql;
import database.usersql.UpdateUser;
import database.usersql.UsersData;
//import database.src.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
//import java.sql.Array;
//import java.sql.ResultSet;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//import java.util.ArrayList;

public class UserHandler implements HttpHandler {

    // HTTP リクエストを処理する
    public void handle(HttpExchange t) throws IOException {
        System.out.println("**************************************************");
        String resBody = "";
        ObjectMapper mapper = new ObjectMapper();

        // 開始行を取得
        String startLine =
                t.getRequestMethod() + " " +
                        t.getRequestURI().toString() + " " +
                        t.getProtocol();
//        System.out.println(startLine);

        // リクエストヘッダを取得
        Headers reqHeaders = t.getRequestHeaders();
//        for (String name : reqHeaders.keySet()) {
//            System.out.println(name + ": " + reqHeaders.getFirst(name));
//        }

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
        //  Java 14 でプレビュー機能として使えるヒアドキュメント的な Text Blocks 機能を使ってみる)


        switch (t.getRequestMethod().toLowerCase(Locale.ROOT)) {

            case "get":
                String reqBody = new String(b,StandardCharsets.UTF_8);
                UsersData usersData = mapper.readValue(reqBody,UsersData.class);
                UsersData getUserData = SelectUserSql.selectusersql(usersData.mailaddress);
                resBody = mapper.writeValueAsString(getUserData);
                System.out.println(resBody);
                break;


            case "post":
                 reqBody = new String(b, StandardCharsets.UTF_8);
                 usersData = mapper.readValue(reqBody, UsersData.class);
                System.out.println(usersData.name);
                System.out.println(usersData.mailaddress);
                System.out.println(usersData.pass);

                int post = AddUser.adduser(usersData);
                resBody = mapper.writeValueAsString(post);
                System.out.println(post);

                break;
//
//
//
//
//            case "put":
////            String email = "";
////            String password = "";
////            String name = "";
////            int put = UpdateUser.updateuser(email,password,name);
//            break;
//
        }


            Headers resHeaders = t.getResponseHeaders();
            resHeaders.set("Content-Type", "application/json");
            resHeaders.set("Last-Modified",
                    ZonedDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.RFC_1123_DATE_TIME));
            resHeaders.set("Server",
                    "MyServer (" +
                            System.getProperty("java.vm.name") + " " +
                            System.getProperty("java.vm.vendor") + " " +
                            System.getProperty("java.vm.version") + ")");


            // レスポンスヘッダを送信
            int statusCode = 200;
            long contentLength = resBody.getBytes(StandardCharsets.UTF_8).length;
            t.sendResponseHeaders(statusCode, contentLength);

            // レスポンスボディを送信
            OutputStream os = t.getResponseBody();
            os.write(resBody.getBytes());
            os.close();

        }
    }




