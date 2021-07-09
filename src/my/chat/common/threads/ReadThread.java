package my.chat.common.threads;

public abstract class ReadThread<T> extends ReedWriteThread<T> {
    protected abstract T read();

    public static abstract class ReadThreadBuilderAbstract<
            C extends ReadThread<T>
            , B extends ReadThreadBuilderAbstract<C, B, T>
            , T
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
