package org.codehaus.jackson.map.ser;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.Date;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.ContextualSerializer;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ResolvableSerializer;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.SerializationConfig.Feature;
import org.codehaus.jackson.map.SerializerFactory;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.TypeSerializer;
import org.codehaus.jackson.map.ser.impl.ReadOnlyClassToSerializerMap;
import org.codehaus.jackson.map.util.ClassUtil;
import org.codehaus.jackson.map.util.RootNameLookup;
import org.codehaus.jackson.node.ObjectNode;
import org.codehaus.jackson.schema.JsonSchema;
import org.codehaus.jackson.schema.SchemaAware;
import org.codehaus.jackson.type.JavaType;

public class StdSerializerProvider extends SerializerProvider {
    static final boolean CACHE_UNKNOWN_MAPPINGS = false;
    public static final JsonSerializer<Object> DEFAULT_KEY_SERIALIZER;
    public static final JsonSerializer<Object> DEFAULT_NULL_KEY_SERIALIZER;
    public static final JsonSerializer<Object> DEFAULT_UNKNOWN_SERIALIZER;
    protected DateFormat _dateFormat;
    protected JsonSerializer<Object> _keySerializer;
    protected final ReadOnlyClassToSerializerMap _knownSerializers;
    protected JsonSerializer<Object> _nullKeySerializer;
    protected JsonSerializer<Object> _nullValueSerializer;
    protected final RootNameLookup _rootNames;
    protected final SerializerCache _serializerCache;
    protected final SerializerFactory _serializerFactory;
    protected JsonSerializer<Object> _unknownTypeSerializer;

    /* renamed from: org.codehaus.jackson.map.ser.StdSerializerProvider.1 */
    final class C35911 extends SerializerBase<Object> {
        C35911(Class cls) {
            super(cls);
        }

        protected void failForEmpty(Object obj) {
            throw new JsonMappingException("No serializer found for class " + obj.getClass().getName() + " and no properties discovered to create BeanSerializer (to avoid exception, disable SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS) )");
        }

        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return null;
        }

        public void serialize(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            if (serializerProvider.isEnabled(Feature.FAIL_ON_EMPTY_BEANS)) {
                failForEmpty(obj);
            }
            jsonGenerator.writeStartObject();
            jsonGenerator.writeEndObject();
        }

        public final void serializeWithType(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
            if (serializerProvider.isEnabled(Feature.FAIL_ON_EMPTY_BEANS)) {
                failForEmpty(obj);
            }
            typeSerializer.writeTypePrefixForObject(obj, jsonGenerator);
            typeSerializer.writeTypeSuffixForObject(obj, jsonGenerator);
        }
    }

    final class WrappedSerializer extends JsonSerializer<Object> {
        protected final JsonSerializer<Object> _serializer;
        protected final TypeSerializer _typeSerializer;

        public WrappedSerializer(TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) {
            this._typeSerializer = typeSerializer;
            this._serializer = jsonSerializer;
        }

        public Class<Object> handledType() {
            return Object.class;
        }

        public void serialize(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            this._serializer.serializeWithType(obj, jsonGenerator, serializerProvider, this._typeSerializer);
        }

        public void serializeWithType(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
            this._serializer.serializeWithType(obj, jsonGenerator, serializerProvider, typeSerializer);
        }
    }

    static {
        DEFAULT_NULL_KEY_SERIALIZER = new FailingSerializer("Null key for a Map not allowed in JSON (use a converting NullKeySerializer?)");
        DEFAULT_KEY_SERIALIZER = new StdKeySerializer();
        DEFAULT_UNKNOWN_SERIALIZER = new C35911(Object.class);
    }

    public StdSerializerProvider() {
        super(null);
        this._unknownTypeSerializer = DEFAULT_UNKNOWN_SERIALIZER;
        this._keySerializer = DEFAULT_KEY_SERIALIZER;
        this._nullValueSerializer = NullSerializer.instance;
        this._nullKeySerializer = DEFAULT_NULL_KEY_SERIALIZER;
        this._serializerFactory = null;
        this._serializerCache = new SerializerCache();
        this._knownSerializers = null;
        this._rootNames = new RootNameLookup();
    }

    protected StdSerializerProvider(SerializationConfig serializationConfig, StdSerializerProvider stdSerializerProvider, SerializerFactory serializerFactory) {
        super(serializationConfig);
        this._unknownTypeSerializer = DEFAULT_UNKNOWN_SERIALIZER;
        this._keySerializer = DEFAULT_KEY_SERIALIZER;
        this._nullValueSerializer = NullSerializer.instance;
        this._nullKeySerializer = DEFAULT_NULL_KEY_SERIALIZER;
        if (serializationConfig == null) {
            throw new NullPointerException();
        }
        this._serializerFactory = serializerFactory;
        this._serializerCache = stdSerializerProvider._serializerCache;
        this._unknownTypeSerializer = stdSerializerProvider._unknownTypeSerializer;
        this._keySerializer = stdSerializerProvider._keySerializer;
        this._nullValueSerializer = stdSerializerProvider._nullValueSerializer;
        this._nullKeySerializer = stdSerializerProvider._nullKeySerializer;
        this._rootNames = stdSerializerProvider._rootNames;
        this._knownSerializers = this._serializerCache.getReadOnlyLookupMap();
    }

    protected JsonSerializer<Object> _createAndCacheUntypedSerializer(Class<?> cls, BeanProperty beanProperty) {
        try {
            JsonSerializer _createUntypedSerializer = _createUntypedSerializer(this._config.constructType(cls), beanProperty);
            if (_createUntypedSerializer != null) {
                this._serializerCache.addAndResolveNonTypedSerializer((Class) cls, _createUntypedSerializer, (SerializerProvider) this);
            }
            return _createUntypedSerializer;
        } catch (Throwable e) {
            throw new JsonMappingException(e.getMessage(), null, e);
        }
    }

    protected JsonSerializer<Object> _createAndCacheUntypedSerializer(JavaType javaType, BeanProperty beanProperty) {
        try {
            JsonSerializer _createUntypedSerializer = _createUntypedSerializer(javaType, beanProperty);
            if (_createUntypedSerializer != null) {
                this._serializerCache.addAndResolveNonTypedSerializer(javaType, _createUntypedSerializer, (SerializerProvider) this);
            }
            return _createUntypedSerializer;
        } catch (Throwable e) {
            throw new JsonMappingException(e.getMessage(), null, e);
        }
    }

    protected JsonSerializer<Object> _createUntypedSerializer(JavaType javaType, BeanProperty beanProperty) {
        return this._serializerFactory.createSerializer(this._config, javaType, beanProperty);
    }

    protected JsonSerializer<Object> _findExplicitUntypedSerializer(Class<?> cls, BeanProperty beanProperty) {
        JsonSerializer<Object> untypedValueSerializer = this._knownSerializers.untypedValueSerializer((Class) cls);
        if (untypedValueSerializer != null) {
            return untypedValueSerializer;
        }
        untypedValueSerializer = this._serializerCache.untypedValueSerializer((Class) cls);
        if (untypedValueSerializer != null) {
            return untypedValueSerializer;
        }
        try {
            return _createAndCacheUntypedSerializer((Class) cls, beanProperty);
        } catch (Exception e) {
            return null;
        }
    }

    protected JsonSerializer<Object> _handleContextualResolvable(JsonSerializer<Object> jsonSerializer, BeanProperty beanProperty) {
        if (!(jsonSerializer instanceof ContextualSerializer)) {
            return jsonSerializer;
        }
        JsonSerializer<Object> createContextual = ((ContextualSerializer) jsonSerializer).createContextual(this._config, beanProperty);
        if (createContextual == jsonSerializer) {
            createContextual = jsonSerializer;
        } else if (createContextual instanceof ResolvableSerializer) {
            ((ResolvableSerializer) createContextual).resolve(this);
        }
        return createContextual;
    }

    protected void _reportIncompatibleRootType(Object obj, JavaType javaType) {
        if (!javaType.isPrimitive() || !ClassUtil.wrapperType(javaType.getRawClass()).isAssignableFrom(obj.getClass())) {
            throw new JsonMappingException("Incompatible types: declared root type (" + javaType + ") vs " + obj.getClass().getName());
        }
    }

    protected void _serializeValue(JsonGenerator jsonGenerator, Object obj) {
        JsonSerializer nullValueSerializer;
        boolean z;
        if (obj == null) {
            nullValueSerializer = getNullValueSerializer();
            z = false;
        } else {
            nullValueSerializer = findTypedValueSerializer(obj.getClass(), true, null);
            z = this._config.isEnabled(Feature.WRAP_ROOT_VALUE);
            if (z) {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeFieldName(this._rootNames.findRootName(obj.getClass(), this._config));
            }
        }
        try {
            nullValueSerializer.serialize(obj, jsonGenerator, this);
            if (z) {
                jsonGenerator.writeEndObject();
            }
        } catch (IOException e) {
            throw e;
        } catch (Throwable e2) {
            Throwable th = e2;
            String message = th.getMessage();
            if (message == null) {
                message = "[no message for " + th.getClass().getName() + "]";
            }
            throw new JsonMappingException(message, th);
        }
    }

    protected void _serializeValue(JsonGenerator jsonGenerator, Object obj, JavaType javaType) {
        JsonSerializer nullValueSerializer;
        boolean z;
        if (obj == null) {
            nullValueSerializer = getNullValueSerializer();
            z = false;
        } else {
            if (!javaType.getRawClass().isAssignableFrom(obj.getClass())) {
                _reportIncompatibleRootType(obj, javaType);
            }
            nullValueSerializer = findTypedValueSerializer(javaType, true, null);
            z = this._config.isEnabled(Feature.WRAP_ROOT_VALUE);
            if (z) {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeFieldName(this._rootNames.findRootName(javaType, this._config));
            }
        }
        try {
            nullValueSerializer.serialize(obj, jsonGenerator, this);
            if (z) {
                jsonGenerator.writeEndObject();
            }
        } catch (IOException e) {
            throw e;
        } catch (Throwable e2) {
            Throwable th = e2;
            String message = th.getMessage();
            if (message == null) {
                message = "[no message for " + th.getClass().getName() + "]";
            }
            throw new JsonMappingException(message, th);
        }
    }

    public int cachedSerializersCount() {
        return this._serializerCache.size();
    }

    protected StdSerializerProvider createInstance(SerializationConfig serializationConfig, SerializerFactory serializerFactory) {
        return new StdSerializerProvider(serializationConfig, this, serializerFactory);
    }

    public final void defaultSerializeDateValue(long j, JsonGenerator jsonGenerator) {
        if (isEnabled(Feature.WRITE_DATES_AS_TIMESTAMPS)) {
            jsonGenerator.writeNumber(j);
            return;
        }
        if (this._dateFormat == null) {
            this._dateFormat = (DateFormat) this._config.getDateFormat().clone();
        }
        jsonGenerator.writeString(this._dateFormat.format(new Date(j)));
    }

    public final void defaultSerializeDateValue(Date date, JsonGenerator jsonGenerator) {
        if (isEnabled(Feature.WRITE_DATES_AS_TIMESTAMPS)) {
            jsonGenerator.writeNumber(date.getTime());
            return;
        }
        if (this._dateFormat == null) {
            this._dateFormat = (DateFormat) this._config.getDateFormat().clone();
        }
        jsonGenerator.writeString(this._dateFormat.format(date));
    }

    public JsonSerializer<Object> findKeySerializer(JavaType javaType, BeanProperty beanProperty) {
        JsonSerializer<Object> createKeySerializer = this._serializerFactory.createKeySerializer(this._config, javaType, beanProperty);
        if (createKeySerializer == null) {
            createKeySerializer = this._keySerializer;
        }
        return createKeySerializer instanceof ContextualSerializer ? ((ContextualSerializer) createKeySerializer).createContextual(this._config, beanProperty) : createKeySerializer;
    }

    public JsonSerializer<Object> findTypedValueSerializer(Class<?> cls, boolean z, BeanProperty beanProperty) {
        JsonSerializer<Object> typedValueSerializer = this._knownSerializers.typedValueSerializer((Class) cls);
        if (typedValueSerializer == null) {
            typedValueSerializer = this._serializerCache.typedValueSerializer((Class) cls);
            if (typedValueSerializer == null) {
                JsonSerializer<Object> findValueSerializer = findValueSerializer((Class) cls, beanProperty);
                TypeSerializer createTypeSerializer = this._serializerFactory.createTypeSerializer(this._config, this._config.constructType(cls), beanProperty);
                JsonSerializer wrappedSerializer = createTypeSerializer != null ? new WrappedSerializer(createTypeSerializer, findValueSerializer) : findValueSerializer;
                if (z) {
                    this._serializerCache.addTypedSerializer((Class) cls, wrappedSerializer);
                }
            }
        }
        return typedValueSerializer;
    }

    public JsonSerializer<Object> findTypedValueSerializer(JavaType javaType, boolean z, BeanProperty beanProperty) {
        JsonSerializer<Object> typedValueSerializer = this._knownSerializers.typedValueSerializer(javaType);
        if (typedValueSerializer == null) {
            typedValueSerializer = this._serializerCache.typedValueSerializer(javaType);
            if (typedValueSerializer == null) {
                JsonSerializer<Object> findValueSerializer = findValueSerializer(javaType, beanProperty);
                TypeSerializer createTypeSerializer = this._serializerFactory.createTypeSerializer(this._config, javaType, beanProperty);
                JsonSerializer wrappedSerializer = createTypeSerializer != null ? new WrappedSerializer(createTypeSerializer, findValueSerializer) : findValueSerializer;
                if (z) {
                    this._serializerCache.addTypedSerializer(javaType, wrappedSerializer);
                }
            }
        }
        return typedValueSerializer;
    }

    public JsonSerializer<Object> findValueSerializer(Class<?> cls, BeanProperty beanProperty) {
        JsonSerializer untypedValueSerializer = this._knownSerializers.untypedValueSerializer((Class) cls);
        if (untypedValueSerializer == null) {
            untypedValueSerializer = this._serializerCache.untypedValueSerializer((Class) cls);
            if (untypedValueSerializer == null) {
                untypedValueSerializer = this._serializerCache.untypedValueSerializer(this._config.constructType(cls));
                if (untypedValueSerializer == null) {
                    untypedValueSerializer = _createAndCacheUntypedSerializer((Class) cls, beanProperty);
                    if (untypedValueSerializer == null) {
                        return getUnknownTypeSerializer(cls);
                    }
                }
            }
        }
        return _handleContextualResolvable(untypedValueSerializer, beanProperty);
    }

    public JsonSerializer<Object> findValueSerializer(JavaType javaType, BeanProperty beanProperty) {
        JsonSerializer untypedValueSerializer = this._knownSerializers.untypedValueSerializer(javaType);
        if (untypedValueSerializer == null) {
            untypedValueSerializer = this._serializerCache.untypedValueSerializer(javaType);
            if (untypedValueSerializer == null) {
                untypedValueSerializer = _createAndCacheUntypedSerializer(javaType, beanProperty);
                if (untypedValueSerializer == null) {
                    return getUnknownTypeSerializer(javaType.getRawClass());
                }
            }
        }
        return _handleContextualResolvable(untypedValueSerializer, beanProperty);
    }

    public void flushCachedSerializers() {
        this._serializerCache.flush();
    }

    public JsonSchema generateJsonSchema(Class<?> cls, SerializationConfig serializationConfig, SerializerFactory serializerFactory) {
        if (cls == null) {
            throw new IllegalArgumentException("A class must be provided");
        }
        SerializerProvider createInstance = createInstance(serializationConfig, serializerFactory);
        if (createInstance.getClass() != getClass()) {
            throw new IllegalStateException("Broken serializer provider: createInstance returned instance of type " + createInstance.getClass() + "; blueprint of type " + getClass());
        }
        JsonSerializer findValueSerializer = createInstance.findValueSerializer((Class) cls, null);
        JsonNode schema = findValueSerializer instanceof SchemaAware ? ((SchemaAware) findValueSerializer).getSchema(createInstance, null) : JsonSchema.getDefaultSchemaNode();
        if (schema instanceof ObjectNode) {
            return new JsonSchema((ObjectNode) schema);
        }
        throw new IllegalArgumentException("Class " + cls.getName() + " would not be serialized as a JSON object and therefore has no schema");
    }

    public JsonSerializer<Object> getNullKeySerializer() {
        return this._nullKeySerializer;
    }

    public JsonSerializer<Object> getNullValueSerializer() {
        return this._nullValueSerializer;
    }

    public JsonSerializer<Object> getUnknownTypeSerializer(Class<?> cls) {
        return this._unknownTypeSerializer;
    }

    public boolean hasSerializerFor(SerializationConfig serializationConfig, Class<?> cls, SerializerFactory serializerFactory) {
        return createInstance(serializationConfig, serializerFactory)._findExplicitUntypedSerializer(cls, null) != null;
    }

    public final void serializeValue(SerializationConfig serializationConfig, JsonGenerator jsonGenerator, Object obj, SerializerFactory serializerFactory) {
        if (serializerFactory == null) {
            throw new IllegalArgumentException("Can not pass null serializerFactory");
        }
        StdSerializerProvider createInstance = createInstance(serializationConfig, serializerFactory);
        if (createInstance.getClass() != getClass()) {
            throw new IllegalStateException("Broken serializer provider: createInstance returned instance of type " + createInstance.getClass() + "; blueprint of type " + getClass());
        }
        createInstance._serializeValue(jsonGenerator, obj);
    }

    public final void serializeValue(SerializationConfig serializationConfig, JsonGenerator jsonGenerator, Object obj, JavaType javaType, SerializerFactory serializerFactory) {
        if (serializerFactory == null) {
            throw new IllegalArgumentException("Can not pass null serializerFactory");
        }
        StdSerializerProvider createInstance = createInstance(serializationConfig, serializerFactory);
        if (createInstance.getClass() != getClass()) {
            throw new IllegalStateException("Broken serializer provider: createInstance returned instance of type " + createInstance.getClass() + "; blueprint of type " + getClass());
        }
        createInstance._serializeValue(jsonGenerator, obj, javaType);
    }

    public void setDefaultKeySerializer(JsonSerializer<Object> jsonSerializer) {
        if (jsonSerializer == null) {
            throw new IllegalArgumentException("Can not pass null JsonSerializer");
        }
        this._keySerializer = jsonSerializer;
    }

    public void setNullKeySerializer(JsonSerializer<Object> jsonSerializer) {
        if (jsonSerializer == null) {
            throw new IllegalArgumentException("Can not pass null JsonSerializer");
        }
        this._nullKeySerializer = jsonSerializer;
    }

    public void setNullValueSerializer(JsonSerializer<Object> jsonSerializer) {
        if (jsonSerializer == null) {
            throw new IllegalArgumentException("Can not pass null JsonSerializer");
        }
        this._nullValueSerializer = jsonSerializer;
    }
}
