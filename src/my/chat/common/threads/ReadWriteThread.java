package my.chat.common.threads;

import loop.help.Builder;
import my.chat.common.message.Message;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.function.Consumer;

public abstract class ReadWriteThread<T extends Message> {
    protected OutputStream out;
    protected InputStream in;
    protected Consumer<? extends ReadWriteThread<T>> action;

//    protected abstract T read();
//    protected abstract void write(T obj);

    public static abstract class ReedWriteThreadBuilderAbstract<
            C extends ReadWriteThread<T>
            , B extends ReedWriteThreadBuilderAbstract<C, B, T>
            , T extends Message
            >
            extends Builder<C, B> {
        protected OutputStream out;
        protected InputStream in;
        protected Consumer<C> action;

        public B setOutputStream(OutputStream out) {
            this.out = out;
            return _this();
        }

        public B setInputStream(InputStream in) {
            this.in = in;
            return _this();
        }

        public B setAction(Consumer<C> action) {
            this.action = action;
            return _this();
        }

        @Override
        public C build() throws Exception {
            C instance = super.build();
            instance.in = this.in;
            instance.out = this.out;
            instance.action = action;
            return instance;
        }
    }
}
