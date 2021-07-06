package my.chat.run;

import my.chat.server.Server;

public class RunServer {
    public static void main(String[] args) {
        Server.builder()
                .setHost("localhost")
                .setPort(1488)
                .build()
                .connect();
    }
}
