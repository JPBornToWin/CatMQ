package catMQ.pojo;

import catMQ.constant.MsgType;
import lombok.Data;

@Data
public class MsgBody {
    private Long id;

    private Integer checksum;

    private Long createTime;

    private MsgType msgType;

    private byte needReplyAck;

    private byte[] msgBody;
}
