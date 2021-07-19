package my.chat.implement.threads;

import my.chat.common.threads.ReadThread;
import my.chat.implement.message.TextMessageContainer;

import java.io.IOException;

public class MessageReader extends ReadThread<TextMessageContainer> {
    public static MessageReaderBuilder builder() {
        return new MessageReaderBuilder();
    }

    @Override
    public TextMessageContainer read() throws IOException {
        try {
            return TextMessageContainer.deserialize(this.in, TextMessageContainer.class);
        } catch (ClassNotFoundException e) {
            TextMessageContainer msg = new TextMessageContainer();
            msg.setMsg(e.getMessage());
            return msg;
        }
    }

    public static abstract class MessageReaderBuilderAbstract<C extends MessageReader, B extends MessageReaderBuilderAbstract<C, B>>
            extends ReadThreadBuilderAbstract<C, B, TextMessageContainer> {

    }

    public static class MessageReaderBuilder extends MessageReaderBuilderAbstract<MessageReader, MessageReaderBuilder> {

        @Override
        protected MessageReaderBuilder _this() {
            return this;
        }

        @Override
        protected MessageReader instance() {
            return new MessageReader();
        }
    }
}
