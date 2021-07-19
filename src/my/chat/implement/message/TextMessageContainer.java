package my.chat.implement.message;

import my.chat.common.message.MessageContainer;

public class TextMessageContainer extends MessageContainer {
    protected String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
