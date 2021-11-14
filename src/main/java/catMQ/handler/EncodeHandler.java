package catMQ.handler;

import catMQ.constant.MsgConstant;
import catMQ.pojo.MsgBody;
import catMQ.utils.CodecUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class EncodeHandler extends MessageToByteEncoder<MsgBody> {

    @Override
    protected void encode(ChannelHandlerContext ctx, MsgBody msg, ByteBuf out) throws Exception {
        byte[] bytes = CodecUtils.serialize(msg);
        // todo 加密
        out.writeInt(bytes.length);
        out.writeBytes(bytes);
    }

    private static void encodeMqPackage(ByteBuf out) {
        out.writeInt(MsgConstant.magicNum);
        out.writeByte(MsgConstant.version);
        out.writeByte(MsgConstant.algorithm);
    }
}
