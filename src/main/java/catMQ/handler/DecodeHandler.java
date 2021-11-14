package catMQ.handler;

import catMQ.pojo.MqPackage;
import catMQ.pojo.MsgBody;
import catMQ.utils.CodecUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class DecodeHandler extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        MqPackage mqPackage = decodeMqPackage(in);
        int len = in.readInt();
        byte[] bytes = new byte[len];
        in.readBytes(bytes);
        // todo 解密
        MsgBody msgBody = CodecUtils.deserialize(MsgBody.class, bytes);
        out.add(msgBody);
    }

    private MqPackage decodeMqPackage(ByteBuf in) {
        MqPackage mqPackage = new MqPackage();
        mqPackage.setMagicNum(in.readInt());
        mqPackage.setVersion(in.readByte());
        mqPackage.setAlgorithm(in.readByte());
        return mqPackage;
    }
}
