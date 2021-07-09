package my.chat.implement.message;

import my.chat.common.message.Message;

public class TextMessage extends Message {
    protected String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
