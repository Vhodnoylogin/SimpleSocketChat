package my.chat.common;

import loop.help.Builder;
import my.chat.common.identifier.Id;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class Peer {
    //    protected Socket client;
    protected ServerSocket server;

    protected Map<Id, Connector> peers = new ConcurrentHashMap<>();

    protected InetAddress host;
    protected int clientPort;
    protected int serverPort;

//    protected BlockingQueue<? extends MessageContainer> queueRead;
//    protected BlockingQueue<? extends MessageContainer> queueWrite;

    protected Runnable empty = () -> {
    };

    //    protected int tickrate = 30;
    protected Integer numberOfPeers = 5;
    protected Integer numberOfThreads = numberOfPeers * 2 + 1;
    protected ExecutorService threadPool = Executors.newFixedThreadPool(numberOfThreads);
//    protected Closeable socket;

//    protected abstract <T extends MessageContainer> void sendMessage(T msg);
//    protected abstract <T extends MessageContainer> T receiveMessage();

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
        try (Closeable ignored = this.server) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        this.threadPool.execute(this::connect);
    }

//    public void work(){
//
//    }

    protected abstract void connect();

    protected void run(Connector connector) {
        Runnable read = connector::read;
        Runnable write = connector::write;
        this.threadPool.execute(read);
        this.threadPool.execute(write);
    }

//    protected abstract Socket makeConnect() throws IOException;

//    public void connect() {
//        while(true) {
//            try (Socket socket = makeConnect()) {
//                Id id = new Id();
//                id.setName(socket.getInetAddress().getHostName() + " " + new Random().nextInt(10));
//                Connector connector = Connector.builder()
//                        .setSocketRead(socket)
//                        .setSocketWrite(socket)
////                                .setWrite((x) -> x.read())
//                        .build();
//                this.peers.put(id, connector);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
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
