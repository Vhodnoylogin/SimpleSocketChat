package my.chat.common;


import loop.Loop;
import my.chat.help.Builder;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ChatThread extends Loop {
    protected Socket socket;

    public ChatThread(String host, Integer port) throws IOException {
        this.socket = new Socket(host, port);
    }

    public OutputStream getOutput() throws IOException {
        return this.socket.getOutputStream();
    }

    public InputStream getInput() throws IOException {
        return this.socket.getInputStream();
    }

    public abstract static class ChatThreadBuilder<C extends ChatThread, B extends ChatThreadBuilder<C, B>>
            implements Builder<C, B> {
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
    }
}
