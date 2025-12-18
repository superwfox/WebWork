package org.web;

import com.sun.net.httpserver.HttpServer;

import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class ThreeColumnPageServer {

    private static final Path WEB_DIR =
            Path.of("src", "main", "java", "org", "web", "webContext");

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress("127.0.0.1", 8080), 0);

        server.createContext("/", exchange -> {
            byte[] bytes = Files.readString(WEB_DIR.resolve("index.html"), StandardCharsets.UTF_8)
                    .getBytes(StandardCharsets.UTF_8);
            exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
            exchange.sendResponseHeaders(200, bytes.length);
            try (OutputStream os = exchange.getResponseBody()) { os.write(bytes); }
        });

        server.createContext("/style.css", exchange -> {
            byte[] bytes = Files.readString(WEB_DIR.resolve("style.css"), StandardCharsets.UTF_8)
                    .getBytes(StandardCharsets.UTF_8);
            exchange.getResponseHeaders().set("Content-Type", "text/css; charset=UTF-8");
            exchange.sendResponseHeaders(200, bytes.length);
            try (OutputStream os = exchange.getResponseBody()) { os.write(bytes); }
        });

        server.start();
        System.out.println("http://127.0.0.1:8080/");
    }
}
