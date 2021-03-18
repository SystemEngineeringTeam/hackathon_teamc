
package api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import database.lendsql.*;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.core.JsonParseException;



//import java.util.ArrayList;

public class LendHandler implements HttpHandler {

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
        is.close();
//        if (b.length != 0) {
//            System.out.println(); // 空行
//            System.out.println(new String(b, StandardCharsets.UTF_8));
//        }
        String reqBody = new String(b, StandardCharsets.UTF_8);
        // レスポンスボディを構築
        // (ここでは Java 14 から正式導入された Switch Expressions と
        //  Java 14 でプレビュー機能として使えるヒアドキュメント的な Text Blocks 機能を使ってみる)
        try {
            lendpostdata data;
            switch (t.getRequestMethod().toLowerCase(Locale.ROOT)) {
                case "post":
                    data = mapper.readValue(reqBody, lendpostdata.class);
                    int post = AddLendSql.addlendsql(data.bookID, data.email);
                    System.out.println(post);
                    resBody = mapper.writeValueAsString(post);
                    System.out.println(resBody);
                    break;

                case "put":
                    data = mapper.readValue(reqBody, lendpostdata.class);
                    int put = UpdateLendSql.updatelendsql(data.bookID);
                    resBody = mapper.writeValueAsString(put);
                    System.out.println(resBody);
                    break;

                default:
                    break;
            }
        }catch (IOException e){
            System.out.println(e);
        }



        if (resBody.equals("1") || resBody.equals("0")) {
            TFResBody rsbdy = new TFResBody();
            rsbdy.setAvailable(resBody);
            resBody = mapper.writeValueAsString(rsbdy);
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

        t.getResponseHeaders().add("Access-Control-Allow-Headers","*");
        t.getResponseHeaders().add("Access-Control-Allow-Methods","*");
        t.getResponseHeaders().add("Access-Control-Allow-Origin","*");


        // レスポンスヘッダを送信
        int statusCode = 200;
        long contentLength = resBody.getBytes(StandardCharsets.UTF_8).length;

        t.sendResponseHeaders(statusCode, contentLength);

        // レスポンスボディを送信
        OutputStream os = t.getResponseBody();
        os.write(resBody.getBytes());
        os.close();

    }


    public static class lendpostdata{
        public int bookID;
        public String email;

        public lendpostdata(){ }
    }
}



