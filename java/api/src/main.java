import api.BookHandler;
//import api.LendHandler;
//import api.UserHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

public class main {

    public static void main(String args[]) throws IOException {
        // HTTP サーバを起動
        int port = 8000;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/book", new BookHandler());
//        server.createContext("/lend", new LendHandler());
//        server.createContext("/user", new UserHandler());
//        server.createContext("/login", new LendHandler());
        System.out.println("MyServer wakes up: port=" + port);
        server.start();

    }

    // HTTP リクエストを処理するために呼び出されるハンドラ

}


