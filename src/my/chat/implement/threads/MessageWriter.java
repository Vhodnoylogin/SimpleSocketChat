package my.chat.implement.threads;

import my.chat.common.threads.WriteThread;
import my.chat.implement.message.TextMessage;

import java.io.IOException;

public class MessageWriter extends WriteThread<TextMessage> {
    public static MessageWriterBuilder builder() {
        return new MessageWriterBuilder();
    }

    @Override
    protected void write(TextMessage obj) throws IOException {
        TextMessage.serialize(obj, this.out);
    }

    public static abstract class MessageWriterBuilderAbstract<C extends MessageWriter, B extends MessageWriterBuilderAbstract<C, B>>
            extends WriteThreadBuilderAbstract<C, B, TextMessage> {

    }

    public static class MessageWriterBuilder extends MessageWriterBuilderAbstract<MessageWriter, MessageWriterBuilder> {

        @Override
        protected MessageWriterBuilder _this() {
            return this;
        }

        @Override
        protected MessageWriter instance() {
            return new MessageWriter();
        }
    }
}
