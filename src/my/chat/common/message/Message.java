package my.chat.common.message;

import java.io.*;

public class Message implements Serializable {
    public static <T extends Message> T deserialize(InputStream in, Class<T> clazz)
            throws IOException, ClassNotFoundException {
        try (ObjectInputStream inputStream = new ObjectInputStream(in)) {
            return clazz.cast(inputStream.readObject());
        }
    }

    public static <T extends Message> void serialize(T msg, OutputStream out) throws IOException {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(out)) {
            outputStream.writeObject(msg);
        }
    }
}
