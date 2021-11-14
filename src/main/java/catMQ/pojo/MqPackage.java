package catMQ.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class MqPackage implements Serializable {
    private Integer magicNum;

    private Byte version;

    private Byte algorithm;
}
