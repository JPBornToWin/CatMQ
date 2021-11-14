package catMQ.utils;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CodecUtils {

    private static Map<Class<?>, Schema> SCHEMA_MAP = new ConcurrentHashMap<>();

    private static Objenesis objenesis = new ObjenesisStd(true);

    private CodecUtils(){
    }

    private static <T> Schema<T> getSchema(Class<T> clazz) {
        if (SCHEMA_MAP.containsKey(clazz)) {
            return SCHEMA_MAP.get(clazz);
        } else {
            Schema<T> schema = RuntimeSchema.getSchema(clazz);
            Schema<T> oldSchema = SCHEMA_MAP.putIfAbsent(clazz, schema);
            return oldSchema == null ? schema : oldSchema;
        }
    }

    public static <T> byte[] serialize(T obj) {
        Class<T> cls = (Class<T>) obj.getClass();
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try {
            Schema<T> schema = RuntimeSchema.getSchema(cls);
            return ProtostuffIOUtil.toByteArray(obj, schema, buffer);
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        } finally {
            buffer.clear();
        }
    }

    public static <T> T deserialize(Class<T> clazz, byte[] data) {
        try {
            T message = objenesis.newInstance(clazz);
            Schema<T> schema = getSchema(clazz);
            ProtostuffIOUtil.mergeFrom(data, message, schema);
            return message;
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    public static void main(String[] args) {

    }
}

