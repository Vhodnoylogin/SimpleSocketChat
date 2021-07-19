package my.chat.common;

import loop.help.Builder;
import my.chat.common.message.MessageContainer;
import my.chat.implement.message.TextMessageContainer;
import my.chat.implement.threads.MessageReader;
import my.chat.implement.threads.MessageWriter;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

public class Connector {
    //    protected Peer owner;
    protected MessageReader read;
    protected MessageWriter write;
    protected Socket socketClient;
    //        protected Socket socketServer;
//    protected Integer numberOfThreads = 5;
//    protected ExecutorService threadPool = Executors.newFixedThreadPool(numberOfThreads);
    protected BlockingQueue<TextMessageContainer> queueRead;
    protected BlockingQueue<TextMessageContainer> queueWrite;

    public static ConnectorBuilder builder() {
        return new ConnectorBuilder();
    }

//    public MessageReader getRead() {
//        return read;
//    }
//
//    public MessageWriter getWrite() {
//        return write;
//    }

//    public void connect() {
//
//    }

    public void read() {
        while (true) {
            try {
                this.queueRead.add(this.read.read());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void write() {
        while (true) {
            try {
                this.write.write(this.queueWrite.take());
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public BlockingQueue<? extends MessageContainer> getQueueRead() {
        return queueRead;
    }

    public BlockingQueue<? extends MessageContainer> getQueueWrite() {
        return queueWrite;
    }

    public void disconnect() throws IOException {
        this.socketClient.close();
    }

    public static abstract class ConnectorBuilderAbstract<C extends Connector, B extends ConnectorBuilderAbstract<C, B>>
            extends Builder<C, B> {
        //        protected ReadMessage read;
//        protected WriteMessage write;
        protected Socket socketRead;
        protected Socket socketWrite;

//        protected Consumer<MessageReader> read;
//        protected Consumer<MessageWriter> write;

//        public B setRead(Consumer<MessageReader> read) {
//            this.read = read;
//            return _this();
//        }
//
//        public B setWrite(Consumer<MessageWriter> write) {
//            this.write = write;
//            return _this();
//        }

        public B setSocketRead(Socket read) {
            this.socketRead = read;
            return _this();
        }

        public B setSocketWrite(Socket write) {
            this.socketWrite = write;
            return _this();
        }

//        public B setReader(ReadMessage read) {
//            this.read = read;
//            return _this();
//        }
//        public B setWriter(WriteMessage write) {
//            this.write = write;
//            return _this();
//        }

        @Override
        public C build() throws Exception {
            C instance = super.build();
//            instance.read=this.read;
//            instance.write=this.write;.
            instance.socketClient = this.socketRead;
            instance.write = MessageWriter.builder()
                    .setInputStream(this.socketWrite.getInputStream())
                    .setOutputStream(this.socketWrite.getOutputStream())
//                    .setAction(this.write)
                    .build();
            instance.read = MessageReader.builder()
                    .setInputStream(this.socketRead.getInputStream())
                    .setOutputStream(this.socketRead.getOutputStream())
//                    .setAction(this.read)
                    .build();
            return instance;
        }
    }

    public static class ConnectorBuilder extends ConnectorBuilderAbstract<Connector, ConnectorBuilder> {
        @Override
        public ConnectorBuilder _this() {
            return this;
        }

        @Override
        public Connector instance() {
            return new Connector();
        }

        @Override
        public Connector build() {
            try {
                return super.build();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
