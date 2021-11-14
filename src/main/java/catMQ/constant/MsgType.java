package catMQ.constant;

public enum  MsgType {
    ACK(0),

    Heart_Check(1),

    Request(2),

    Response(3);

    int code;

    MsgType(int code) {
        this.code = code;
    }
}
