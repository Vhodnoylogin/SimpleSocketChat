package my.chat.common.threads;

import loop.help.Builder;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.function.BiConsumer;

public abstract class ReedWriteThread<T> implements Runnable {
    protected OutputStream out;
    protected InputStream in;
//    protected BiConsumer<InputStream,OutputStream> action;

//    protected abstract T read();
//    protected abstract void write(T obj);


    @Override
    public void run() {
//        this.action.a
    }

    public static abstract class ReedWriteThreadBuilderAbstract<
            C extends ReedWriteThread<T>
            , B extends ReedWriteThreadBuilderAbstract<C, B, T>
            , T
            >
            extends Builder<C, B> {
        protected OutputStream out;
        protected InputStream in;
        protected BiConsumer<InputStream, OutputStream> action;

        public B setOutputStream(OutputStream out) {
            this.out = out;
            return _this();
        }

        public B setInputStream(InputStream in) {
            this.in = in;
            return _this();
        }

        public B setAction(BiConsumer<InputStream, OutputStream> action) {
            this.action = action;
            return _this();
        }

        @Override
        public C build() throws Exception {
            C instance = super.build();
            instance.in = this.in;
            instance.out = this.out;
//            instance.action=action;
            return instance;
        }
    }
}
