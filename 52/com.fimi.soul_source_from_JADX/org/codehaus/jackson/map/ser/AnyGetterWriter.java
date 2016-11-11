package org.codehaus.jackson.map.ser;

import java.lang.reflect.Method;
import java.util.Map;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.introspect.AnnotatedMethod;

public class AnyGetterWriter {
    protected final Method _anyGetter;
    protected final MapSerializer _serializer;

    public AnyGetterWriter(AnnotatedMethod annotatedMethod, MapSerializer mapSerializer) {
        this._anyGetter = annotatedMethod.getAnnotated();
        this._serializer = mapSerializer;
    }

    public void getAndSerialize(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        Object invoke = this._anyGetter.invoke(obj, new Object[0]);
        if (invoke != null) {
            if (invoke instanceof Map) {
                this._serializer.serializeFields((Map) invoke, jsonGenerator, serializerProvider);
                return;
            }
            throw new JsonMappingException("Value returned by 'any-getter' (" + this._anyGetter.getName() + "()) not java.util.Map but " + invoke.getClass().getName());
        }
    }

    public void resolve(SerializerProvider serializerProvider) {
        this._serializer.resolve(serializerProvider);
    }
}
