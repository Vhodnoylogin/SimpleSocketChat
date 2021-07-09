package my.chat.common;

import loop.help.Builder;
import my.chat.common.message.Message;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public abstract class Peer {
//    protected Socket client;
//    protected ServerSocket server;

    protected Map<Integer, Connector> peers = new HashMap<>();

    protected InetAddress host;
    protected int clientPort;
    protected int serverPort;

    //    protected int tickrate = 30;
//    protected ExecutorService threadPool = Executors.newFixedThreadPool(5);
    protected Closeable socket;

    protected abstract <T extends Message> void sendMessage(T msg);

    protected abstract <T extends Message> void receiveMessage(T msg);

//    public void startClient(){
//        try(Socket client = new Socket(host, clientPort)){
//            try(InputStream in = client.getInputStream();OutputStream out = client.getOutputStream()){
//                ReadThread read = ReadThread.builder()
//                        .setInputStream(in)
//                        .setOutputStream(out)
//                        .build();
//                WriteThread write = WriteThread.builder()
//                        .setInputStream(in)
//                        .setOutputStream(out)
//                        .build();
//                Supplier<Boolean> stopPeer = this::stop;
//
//                threadPool.execute(
//                        Loop.builder()
//                                .setTickRate(tickrate)
//                                .setAction((delta, stop) -> {
//                                    read.action();
//                                    if(stopPeer.get()){
//                                        stop.stop();
//                                    }
//                                })
//                                .build()
//                );
//
//                threadPool.execute(
//                        Loop.builder()
//                                .setTickRate(tickrate)
//                                .setAction((delta, stop) -> {
//                                    write.action();
//                                    if(stopPeer.get()){
//                                        stop.stop();
//                                    }
//                                })
//                                .build()
//                );
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public void stop() {
//        threadPool.shutdown();
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        Runnable connect = this::connect;
        Runnable run = () -> {
        };
    }

    public void work()

    protected abstract Socket makeConnect();

    public void connect() {
        try (Socket socket = makeConnect()) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    public abstract void run();

//    public void startServer(){
//        try(ServerSocket server = new ServerSocket(this.clientPort)){
//            Supplier<Boolean> stopPeer = this::stop;
//            Loop.builder()
//                    .setTickRate(tickrate)
//                    .setAction((delta, stop) -> {
//                        try(Socket socket = server.accept()) {
//                            peers.put(socket.hashCode(),socket)
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        if(stopPeer.get()){
//                            stop.stop();
//                        }
//                    })
//                    .build()
//                    .run();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//    public void beginListen(Connector connector) {
//        Runnable stopRun = this::stop;
//        Runnable read = () -> {
//            while (true) {
//                try {
//                    connector.
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    stopRun.run();
//                }
//            }
//        };
//        Runnable write = () -> {
//            while (true) {
//                try {
//                    connectorOld.write();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    stopRun.run();
//                }
//            }
//        };
////        System.out.println("start READ");
//        this.threadPool.execute(read);
////        System.out.println("start WRITE");
//        this.threadPool.execute(write);
//    }

    public abstract static class PeerBuilderAbstract<C extends Peer, B extends PeerBuilderAbstract<C, B>>
            extends Builder<C, B> {
        protected String host;
        protected Integer portListen;
        protected Integer portServer;

        public B setHost(String host) {
            this.host = host;
            return _this();
        }

        public B setPortListen(Integer port) {
            this.portListen = port;
            return _this();
        }

        public B setPortActive(Integer port) {
            this.portServer = port;
            return _this();
        }

        @Override
        public C build() throws Exception {
            C instance = super.build();
            instance.clientPort = portListen;
            instance.serverPort = portServer;
            instance.host = InetAddress.getByName(host);
            return instance;
        }
    }
}
