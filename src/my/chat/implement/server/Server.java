package my.chat.implement.server;

import my.chat.common.Connector;
import my.chat.common.Peer;
import my.chat.common.identifier.Id;

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

    @Override
    protected void connect() {
        try (ServerSocket server = new ServerSocket(this.serverPort)) {
            this.server = server;
            while (true) {
                Socket socket = server.accept();
                Id id = new Id();
                id.setName(socket.getInetAddress().getHostAddress() + " " + this.id.addAndGet(1));
                Connector connector = Connector.builder()
                        .setSocketRead(socket)
                        .setSocketWrite(socket)
                        .build();
                this.peers.put(id, connector);
                this.run(connector);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public void connect() {
//        try {
//            ServerSocket socket = new ServerSocket(this.serverPort);
//            this.socket = socket;
//            Runnable stopRun = this::stop;
//            Runnable connect = () -> {
//                while (true) {
//                    System.out.println("IN SERVER LISTEN FOR CLIENT");
//                    try (Socket client = socket.accept()) {
//                        Connector connector = Connector.builder()
//                                .setSocketRead(client)
//                                .setSocketWrite(client)
////                                .setWrite((x) -> x.read())
//                                .build();
//                        peers.put(id.getAndIncrement(), connector);
////                        this.beginListen(connector);
////                    Runnable read = () ->{while(true){try {
////                        connector.read();
////                    } catch (IOException e) {
////                        e.printStackTrace();
////                        stopRun.run();
////                    }}};
////                    this.threadPool.execute(read);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                        stopRun.run();
//                    }
//                }
//            };
//            System.out.println("Start connect");
//            this.threadPool.execute(connect);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

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
