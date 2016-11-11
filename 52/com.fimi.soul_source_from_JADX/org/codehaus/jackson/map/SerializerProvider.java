package org.codehaus.jackson.map;

import java.lang.reflect.Type;
import java.util.Date;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.SerializationConfig.Feature;
import org.codehaus.jackson.map.ser.FilterProvider;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.schema.JsonSchema;
import org.codehaus.jackson.type.JavaType;

public abstract class SerializerProvider {
    protected static final JavaType TYPE_OBJECT;
    protected final SerializationConfig _config;
    protected final Class<?> _serializationView;

    static {
        TYPE_OBJECT = TypeFactory.defaultInstance().uncheckedSimpleType(Object.class);
    }

    protected SerializerProvider(SerializationConfig serializationConfig) {
        this._config = serializationConfig;
        this._serializationView = serializationConfig == null ? null : this._config.getSerializationView();
    }

    public abstract int cachedSerializersCount();

    public JavaType constructType(Type type) {
        return this._config.getTypeFactory().constructType(type);
    }

    public abstract void defaultSerializeDateValue(long j, JsonGenerator jsonGenerator);

    public abstract void defaultSerializeDateValue(Date date, JsonGenerator jsonGenerator);

    public final void defaultSerializeField(String str, Object obj, JsonGenerator jsonGenerator) {
        jsonGenerator.writeFieldName(str);
        if (obj == null) {
            getNullValueSerializer().serialize(null, jsonGenerator, this);
        } else {
            findTypedValueSerializer(obj.getClass(), true).serialize(obj, jsonGenerator, this);
        }
    }

    public final void defaultSerializeNull(JsonGenerator jsonGenerator) {
        getNullValueSerializer().serialize(null, jsonGenerator, this);
    }

    public final void defaultSerializeValue(Object obj, JsonGenerator jsonGenerator) {
        if (obj == null) {
            getNullValueSerializer().serialize(null, jsonGenerator, this);
        } else {
            findTypedValueSerializer(obj.getClass(), true).serialize(obj, jsonGenerator, this);
        }
    }

    public abstract JsonSerializer<Object> findKeySerializer(JavaType javaType, BeanProperty beanProperty);

    @Deprecated
    public final JsonSerializer<Object> findTypedValueSerializer(Class<?> cls, boolean z) {
        return findTypedValueSerializer((Class) cls, z, null);
    }

    public abstract JsonSerializer<Object> findTypedValueSerializer(Class<?> cls, boolean z, BeanProperty beanProperty);

    @Deprecated
    public final JsonSerializer<Object> findTypedValueSerializer(JavaType javaType, boolean z) {
        return findTypedValueSerializer(javaType, z, null);
    }

    public abstract JsonSerializer<Object> findTypedValueSerializer(JavaType javaType, boolean z, BeanProperty beanProperty);

    @Deprecated
    public final JsonSerializer<Object> findValueSerializer(Class<?> cls) {
        return findValueSerializer((Class) cls, null);
    }

    public abstract JsonSerializer<Object> findValueSerializer(Class<?> cls, BeanProperty beanProperty);

    @Deprecated
    public final JsonSerializer<Object> findValueSerializer(JavaType javaType) {
        return findValueSerializer(javaType, null);
    }

    public abstract JsonSerializer<Object> findValueSerializer(JavaType javaType, BeanProperty beanProperty);

    public abstract void flushCachedSerializers();

    public abstract JsonSchema generateJsonSchema(Class<?> cls, SerializationConfig serializationConfig, SerializerFactory serializerFactory);

    public final SerializationConfig getConfig() {
        return this._config;
    }

    public final FilterProvider getFilterProvider() {
        return this._config.getFilterProvider();
    }

    @Deprecated
    public final JsonSerializer<Object> getKeySerializer() {
        return findKeySerializer(TYPE_OBJECT, null);
    }

    @Deprecated
    public final JsonSerializer<Object> getKeySerializer(JavaType javaType, BeanProperty beanProperty) {
        return findKeySerializer(javaType, beanProperty);
    }

    public abstract JsonSerializer<Object> getNullKeySerializer();

    public abstract JsonSerializer<Object> getNullValueSerializer();

    public final Class<?> getSerializationView() {
        return this._serializationView;
    }

    public abstract JsonSerializer<Object> getUnknownTypeSerializer(Class<?> cls);

    public abstract boolean hasSerializerFor(SerializationConfig serializationConfig, Class<?> cls, SerializerFactory serializerFactory);

    public final boolean isEnabled(Feature feature) {
        return this._config.isEnabled(feature);
    }

    public abstract void serializeValue(SerializationConfig serializationConfig, JsonGenerator jsonGenerator, Object obj, SerializerFactory serializerFactory);

    public abstract void serializeValue(SerializationConfig serializationConfig, JsonGenerator jsonGenerator, Object obj, JavaType javaType, SerializerFactory serializerFactory);

    public abstract void setDefaultKeySerializer(JsonSerializer<Object> jsonSerializer);

    public abstract void setNullKeySerializer(JsonSerializer<Object> jsonSerializer);

    public abstract void setNullValueSerializer(JsonSerializer<Object> jsonSerializer);
}
