package my.chat.help;

public interface Builder<C, B extends Builder<C, B>> {
    B _this();

    C instance();

    C build() throws Exception;
}
