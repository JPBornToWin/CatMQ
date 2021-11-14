package catMQ.constant;

public class MsgConstant {
    // 固定长度
    public static final Integer magicNum = 1024;
    // 协议版本
    public static final byte version = 1;
    // 加密算法
    public static final byte algorithm = 0;
    // 消息体长度
    public static final int bodyLen = 4;
    // 头长度
    public static final int headLen = 4 + 1 + 1;
}
