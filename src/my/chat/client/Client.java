package my.chat.client;

import my.chat.common.Connector;
import my.chat.common.Peer;

import java.io.IOException;
import java.net.Socket;

public class Client extends Peer {
//    protected Socket socket;

//    @Override
//    public void stop() {
//        threadPool.shutdown();
//        try {
//            socket.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public static ClientBuilder builder() {
        return new ClientBuilder();
    }

//    @Override
//    public void run() {
////        Runnable stopClient = this::stop;
//        this.beginListen(peers.get(1));
//    }

    @Override
    public void connect() {
        try {
            Socket socket = new Socket(this.host, this.clientPort);
            this.socket = socket;
            Connector connector = Connector.builder()
                    .setInputStream(socket.getInputStream())
                    .setOutputStream(socket.getOutputStream())
                    .build();
            this.peers.put(1, connector);
            this.beginListen(peers.get(1));
//            Runnable stopRun = this::stop;
//            Runnable write = () ->{while(true){try {
//                connector.write();
//            } catch (IOException e) {
//                e.printStackTrace();
//                stopRun.run();
//            }}};
//            this.threadPool.execute(write);
            System.out.println("CONNECT SUCCESS");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public abstract static class ClientBuilderAbstract<C extends Client, B extends ClientBuilderAbstract<C, B>>
            extends PeerBuilderAbstract<C, B> {
    }

    public static class ClientBuilder extends ClientBuilderAbstract<Client, ClientBuilder> {
        @Override
        public ClientBuilder _this() {
            return this;
        }

        @Override
        public Client instance() {
            return new Client();
        }

        @Override
        public Client build() {
            try {
                return super.build();
            } catch (Exception e) {
                return null;
            }
        }
    }
}
