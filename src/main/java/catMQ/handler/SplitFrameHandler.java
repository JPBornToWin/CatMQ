package catMQ.handler;

import catMQ.constant.MsgConstant;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

public class SplitFrameHandler extends LengthFieldBasedFrameDecoder {
    public SplitFrameHandler() {
        super(Integer.MAX_VALUE, MsgConstant.headLen, MsgConstant.bodyLen);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        ByteBuf buf = (ByteBuf) super.decode(ctx, in);
        buf.markReaderIndex();
        int magicNum = in.readInt();
        if (magicNum != MsgConstant.magicNum) {
            ctx.channel().close();
            return null;
        }
        buf.resetReaderIndex();

        return buf;
    }
}
