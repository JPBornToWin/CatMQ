package catMQ.exception;

public class MqRuntimeException extends RuntimeException {

    public MqRuntimeException() {
        super();
    }

    public MqRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public MqRuntimeException(String message) {
        super(message);
    }

    public MqRuntimeException(Throwable cause) {
        super(cause);
    }
}
