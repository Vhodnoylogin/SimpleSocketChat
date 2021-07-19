package my.chat.implement.threads;

import my.chat.common.threads.WriteThread;
import my.chat.implement.message.TextMessageContainer;

import java.io.IOException;

public class MessageWriter extends WriteThread<TextMessageContainer> {
    public static MessageWriterBuilder builder() {
        return new MessageWriterBuilder();
    }

    @Override
    public void write(TextMessageContainer obj) throws IOException {
        TextMessageContainer.serialize(obj, this.out);
    }

    public static abstract class MessageWriterBuilderAbstract<C extends MessageWriter, B extends MessageWriterBuilderAbstract<C, B>>
            extends WriteThreadBuilderAbstract<C, B, TextMessageContainer> {

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
