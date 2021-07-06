package my.chat.common;


import loop.help.Builder;

import java.io.*;

public class Connector {
    protected OutputStream out;
    protected InputStream in;

//    protected Peer owner;

    public static ConnectorBuilder builder() {
        return new ConnectorBuilder();
    }

    public void read() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(this.in));
        System.out.println(in.readLine());
    }

    public void write() throws IOException {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        String word = consoleReader.readLine();
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(this.out));
        out.write(word);
        out.newLine();
        out.flush();
    }

    public static abstract class ConnectorBuilderAbstract<C extends Connector, B extends ConnectorBuilderAbstract<C, B>>
            extends Builder<C, B> {
        protected OutputStream out;
        protected InputStream in;

        public B setOutputStream(OutputStream out) {
            this.out = out;
            return _this();
        }

        public B setInputStream(InputStream in) {
            this.in = in;
            return _this();
        }

        @Override
        public C build() {
            try {
                C instance = super.build();
                instance.in = this.in;
                instance.out = this.out;
                return instance;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
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
