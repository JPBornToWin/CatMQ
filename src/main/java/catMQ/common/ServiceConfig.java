package catMQ.common;

import lombok.Data;

import java.net.Inet4Address;
import java.net.InetAddress;

@Data
public class ServiceConfig {
    private Integer workThreadNum = Runtime.getRuntime().availableProcessors() + 1;

    private boolean isServer = false;

    private boolean openHeartCheck = true;

    private Integer maxReadIdleSecond = 60;

    private Integer serverPort = 9090;

    private String host;

    private boolean openServiceThreadPool = true;

    private Integer serviceThreadCoreNum = Runtime.getRuntime().availableProcessors() + 1;

    private Integer serviceThreadMaxNum = serviceThreadCoreNum * 2;

    private Integer serviceThreadKeepAliveSecond = 60;
}
