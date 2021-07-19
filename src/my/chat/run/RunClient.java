package my.chat.run;

import my.chat.implement.client.Client;

public class RunClient {
    public static void main(String[] args) {
        Client.builder()
                .setHost("localhost")
                .setPortActive(1488)
                .setPortListen(1488)
                .build()
                .start();
    }
}
