package my.chat.common;

import loop.Loop;
import loop.help.Builder;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class Peer {
//    protected Socket client;
//    protected ServerSocket server;

    protected Map<Integer, Connector> peers = new HashMap<>();

    protected InetAddress host;
    protected int clientPort;

    protected int tickrate = 30;
    protected ExecutorService threadPool = Executors.newFixedThreadPool(5);
    protected Closeable socket;

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
        threadPool.shutdown();
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public abstract void connect();
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

    public void beginListen(Connector connector) {
        Runnable stopRun = this::stop;
        Loop read = Loop.builder()
                .setTickRate(this.tickrate)
                .setAction((delta, stop) -> {
                    try {
                        connector.read();
                    } catch (IOException e) {
                        e.printStackTrace();
                        stopRun.run();
                    }
                })
                .build();
        Loop write = Loop.builder()
                .setTickRate(this.tickrate)
                .setAction((delta, stop) -> {
                    try {
                        connector.write();
                    } catch (IOException e) {
                        e.printStackTrace();
                        stopRun.run();
                    }
                })
                .build();
//        this.threadPool.execute(read);
        this.threadPool.execute(write);
    }

    public abstract static class PeerBuilderAbstract<C extends Peer, B extends PeerBuilderAbstract<C, B>>
            extends Builder<C, B> {
        protected String host;
        protected Integer port;

        public B setHost(String host) {
            this.host = host;
            return _this();
        }

        public B setPort(Integer port) {
            this.port = port;
            return _this();
        }

        @Override
        public C build() throws Exception {
            C instance = super.build();
            instance.clientPort = port;
            instance.host = InetAddress.getByName(host);
            return instance;
        }
    }
}
