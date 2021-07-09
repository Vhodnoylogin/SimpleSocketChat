package my.chat.run;

import my.chat.implement.client.Client;

public class RunClient {
    public static void main(String[] args) {
        Client.builder()
                .setHost("localhost")
                .setPort(1488)
                .build()
                .connect();
    }
}
