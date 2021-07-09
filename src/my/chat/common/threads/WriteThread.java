package my.chat.common.threads;

public abstract class WriteThread<T> extends ReedWriteThread<T> {

    protected abstract void write(T obj);

    public static abstract class WriteThreadBuilderAbstract<
            C extends WriteThread<T>
            , B extends WriteThreadBuilderAbstract<C, B, T>
            , T
            >
            extends ReedWriteThreadBuilderAbstract<C, B, T> {

    }

//    public static class WriteThreadBuilder
//            extends WriteThreadBuilderAbstract<WriteThread, WriteThreadBuilder> {
//
//        @Override
//        protected WriteThreadBuilder _this() {
//            return this;
//        }
//
//        @Override
//        protected WriteThread instance() {
//            return new WriteThread();
//        }
//
//        @Override
//        public WriteThread build() throws Exception {
//            WriteThread instance = super.build();
//            instance.read = new BufferedReader(new InputStreamReader(this.in));
//            return instance;
//        }
//    }
}
