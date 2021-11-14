package catMQ.common;

import catMQ.exception.MqRuntimeException;
import catMQ.utils.CodecUtils;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.netty.channel.ChannelHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class Service {
    private int port;

    private String host;

    private ServiceConfig serviceConfig;

    private AtomicBoolean started = new AtomicBoolean(false);

    private LinkedHashMap<String, ChannelHandler> handlers = new LinkedHashMap<>();

    private ThreadPoolExecutor executorService;

    private ScheduledExecutorService heartExecutorService;


    public Service(ServiceConfig config) {
        this.serviceConfig = config;
    }

    public void init() {
        if (Objects.isNull(serviceConfig)) {
            throw new MqRuntimeException("Service.init# serviceConfig is null");
        }
        if (started.get()) {
            return;
        }


        if (serviceConfig.isOpenServiceThreadPool()) {
            executorService = new ThreadPoolExecutor(
                    serviceConfig.getServiceThreadCoreNum(),
                    serviceConfig.getServiceThreadMaxNum(),
                    serviceConfig.getServiceThreadKeepAliveSecond(),
                    TimeUnit.SECONDS,
                    new LinkedBlockingQueue<>(),
                    new ThreadFactoryBuilder().setNameFormat("ServiceThread %d---").build());
        }

        IdleStateHandler idleStateHandler = new IdleStateHandler(serviceConfig.getMaxReadIdleSecond(),
                serviceConfig.getMaxReadIdleSecond(),
                serviceConfig.getMaxReadIdleSecond(),
                TimeUnit.SECONDS);

        if (serviceConfig.isOpenHeartCheck()) {
            heartExecutorService = Executors.newScheduledThreadPool(1,
                    new ThreadFactoryBuilder().setNameFormat("heartExecutorService %d---").build());
            heartExecutorService.scheduleAtFixedRate(() -> {
                // todo
                System.out.println("发送心跳包");
            }, 1,serviceConfig.getMaxReadIdleSecond() / 2, TimeUnit.SECONDS);
        }

        handlers.put("idleStateHandler", idleStateHandler);

        started.compareAndSet(false, true);
    }
}
