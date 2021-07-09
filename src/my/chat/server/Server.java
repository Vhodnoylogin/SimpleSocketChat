package my.chat.server;

import my.chat.common.ConnectorOld;
import my.chat.common.Peer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

public class Server extends Peer {
    //    protected ServerSocket socket;
    protected AtomicInteger id = new AtomicInteger(0);

//    @Override
//    public void stop() {
//        threadPool.shutdown();
//        try {
//            socket.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public static ServerBuilder builder() {
        return new ServerBuilder();
    }

//    @Override
//    public void run() {
//
//    }

    @Override
    public void connect() {
        try {
            ServerSocket socket = new ServerSocket(this.clientPort);
            this.socket = socket;
            Runnable stopRun = this::stop;
            Runnable connect = () -> {
                while (true) {
                    try {
                        System.out.println("IN SERVER LISTEN FOR CLIENT");
                        Socket client = socket.accept();
                        ConnectorOld connectorOld = ConnectorOld.builder()
                                .setInputStream(client.getInputStream())
                                .setOutputStream(client.getOutputStream())
                                .build();
                        peers.put(id.getAndIncrement(), connectorOld);
                        this.beginListen(connectorOld);
//                    Runnable read = () ->{while(true){try {
//                        connector.read();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                        stopRun.run();
//                    }}};
//                    this.threadPool.execute(read);
                    } catch (IOException e) {
                        e.printStackTrace();
                        stopRun.run();
                    }
                }
            };
            System.out.println("Start connect");
            this.threadPool.execute(connect);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public abstract static class ServerBuilderAbstract<C extends Server, B extends ServerBuilderAbstract<C, B>>
            extends PeerBuilderAbstract<C, B> {
    }

    public static class ServerBuilder extends ServerBuilderAbstract<Server, ServerBuilder> {
        @Override
        public ServerBuilder _this() {
            return this;
        }

        @Override
        public Server instance() {
            return new Server();
        }

        @Override
        public Server build() {
            try {
                return super.build();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
