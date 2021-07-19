package my.chat.common.message;

import java.io.*;

public class MessageContainer implements Serializable {
    public static <T extends MessageContainer> T deserialize(InputStream in, Class<T> clazz)
            throws IOException, ClassNotFoundException {
        try (ObjectInputStream inputStream = new ObjectInputStream(in)) {
            return clazz.cast(inputStream.readObject());
        }
    }

    public static <T extends MessageContainer> void serialize(T msg, OutputStream out) throws IOException {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(out)) {
            outputStream.writeObject(msg);
        }
    }
}
