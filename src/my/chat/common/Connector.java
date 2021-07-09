package my.chat.common;

import loop.help.Builder;

import java.net.Socket;

public class Connector {
    protected Peer owner;
//    protected ReadThread read;
//    protected WriteThread write;


    public static ConnectorBuilder builder() {
        return new ConnectorBuilder();
    }


    public static abstract class ConnectorBuilderAbstract<C extends Connector, B extends ConnectorBuilderAbstract<C, B>>
            extends Builder<C, B> {
        protected Socket socket;
//        protected InputStream in;

//        public B setOutputStream(OutputStream out) {
//            this.out = out;
//            return _this();
//        }
//
//        public B setInputStream(InputStream in) {
//            this.in = in;
//            return _this();
//        }

        public B setSocket(Socket socket) {
            this.socket = socket;
            return _this();
        }

        @Override
        public C build() throws Exception {
            C instance = super.build();
//                instance.
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
    }
}
