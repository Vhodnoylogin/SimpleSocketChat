package my.chat.common.threads;

import my.chat.common.message.Message;

import java.io.IOException;

public abstract class ReadThread<T extends Message> extends ReadWriteThread<T> {
    public abstract T read() throws IOException;

    @Override
    public void run() {

    }

    public static abstract class ReadThreadBuilderAbstract<
            C extends ReadThread<T>
            , B extends ReadThreadBuilderAbstract<C, B, T>
            , T extends Message
            >
            extends ReedWriteThreadBuilderAbstract<C, B, T> {

    }

//    public static class ReadThreadBuilder<T>
//            extends ReadThreadBuilderAbstract<ReadThread<T>,ReadThreadBuilder<T>,T>{
//
//        @Override
//        protected ReadThreadBuilder<T> _this() {
//            return this;
//        }
//
//        @Override
//        protected ReadThread<T> instance() {
//            return new ReadThread<>();
//        }
//
//        @Override
//        public ReadThread<T> build() throws Exception {
//            ReadThread<T> instance = super.build();
//            instance.read = new BufferedReader(new InputStreamReader(this.in));
//            return instance;
//        }
//    }
}
