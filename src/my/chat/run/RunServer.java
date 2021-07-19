package my.chat.run;

import my.chat.implement.server.Server;

public class RunServer {
    public static void main(String[] args) {
        Server.builder()
                .setHost("localhost")
                .setPortActive(1488)
                .setPortListen(1488)
                .build()
                .start();
    }
}
