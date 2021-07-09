package my.chat.implement.threads;

import my.chat.common.threads.ReadThread;
import my.chat.implement.message.TextMessage;

import java.io.IOException;

public class MessageReader extends ReadThread<TextMessage> {
    public static MessageReaderBuilder builder() {
        return new MessageReaderBuilder();
    }

    @Override
    protected TextMessage read() throws IOException {
        try {
            return TextMessage.deserialize(this.in, TextMessage.class);
        } catch (ClassNotFoundException e) {
            TextMessage msg = new TextMessage();
            msg.setMsg(e.getMessage());
            return msg;
        }
    }

    public static abstract class MessageReaderBuilderAbstract<C extends MessageReader, B extends MessageReaderBuilderAbstract<C, B>>
            extends ReadThreadBuilderAbstract<C, B, TextMessage> {

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
