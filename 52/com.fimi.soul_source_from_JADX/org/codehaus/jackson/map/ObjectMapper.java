package org.codehaus.jackson.map;

import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;
import java.text.DateFormat;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import org.codehaus.jackson.FormatSchema;
import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.ObjectCodec;
import org.codehaus.jackson.PrettyPrinter;
import org.codehaus.jackson.Version;
import org.codehaus.jackson.Versioned;
import org.codehaus.jackson.annotate.JsonTypeInfo.As;
import org.codehaus.jackson.annotate.JsonTypeInfo.Id;
import org.codehaus.jackson.io.SegmentedStringWriter;
import org.codehaus.jackson.map.Module.SetupContext;
import org.codehaus.jackson.map.SerializationConfig.Feature;
import org.codehaus.jackson.map.deser.BeanDeserializerModifier;
import org.codehaus.jackson.map.deser.StdDeserializationContext;
import org.codehaus.jackson.map.deser.StdDeserializerProvider;
import org.codehaus.jackson.map.introspect.BasicClassIntrospector;
import org.codehaus.jackson.map.introspect.JacksonAnnotationIntrospector;
import org.codehaus.jackson.map.introspect.VisibilityChecker;
import org.codehaus.jackson.map.introspect.VisibilityChecker.Std;
import org.codehaus.jackson.map.jsontype.NamedType;
import org.codehaus.jackson.map.jsontype.SubtypeResolver;
import org.codehaus.jackson.map.jsontype.TypeResolverBuilder;
import org.codehaus.jackson.map.jsontype.impl.StdSubtypeResolver;
import org.codehaus.jackson.map.jsontype.impl.StdTypeResolverBuilder;
import org.codehaus.jackson.map.ser.BeanSerializerFactory;
import org.codehaus.jackson.map.ser.BeanSerializerModifier;
import org.codehaus.jackson.map.ser.FilterProvider;
import org.codehaus.jackson.map.ser.StdSerializerProvider;
import org.codehaus.jackson.map.type.SimpleType;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.map.type.TypeModifier;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.NullNode;
import org.codehaus.jackson.node.ObjectNode;
import org.codehaus.jackson.node.TreeTraversingParser;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.schema.JsonSchema;
import org.codehaus.jackson.smile.SmileConstants;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.type.TypeReference;
import org.codehaus.jackson.util.ByteArrayBuilder;
import org.codehaus.jackson.util.DefaultPrettyPrinter;
import org.codehaus.jackson.util.TokenBuffer;
import org.codehaus.jackson.util.VersionUtil;

public class ObjectMapper extends ObjectCodec implements Versioned {
    protected static final AnnotationIntrospector DEFAULT_ANNOTATION_INTROSPECTOR;
    protected static final ClassIntrospector<? extends BeanDescription> DEFAULT_INTROSPECTOR;
    private static final JavaType JSON_NODE_TYPE;
    protected static final VisibilityChecker<?> STD_VISIBILITY_CHECKER;
    protected DeserializationConfig _deserializationConfig;
    protected DeserializerProvider _deserializerProvider;
    protected final JsonFactory _jsonFactory;
    protected final ConcurrentHashMap<JavaType, JsonDeserializer<Object>> _rootDeserializers;
    protected SerializationConfig _serializationConfig;
    protected SerializerFactory _serializerFactory;
    protected SerializerProvider _serializerProvider;
    protected SubtypeResolver _subtypeResolver;
    protected TypeFactory _typeFactory;

    /* renamed from: org.codehaus.jackson.map.ObjectMapper.1 */
    class C35801 implements SetupContext {
        final /* synthetic */ ObjectMapper val$mapper;

        C35801(ObjectMapper objectMapper) {
            this.val$mapper = objectMapper;
        }

        public void addAbstractTypeResolver(AbstractTypeResolver abstractTypeResolver) {
            this.val$mapper._deserializerProvider = this.val$mapper._deserializerProvider.withAbstractTypeResolver(abstractTypeResolver);
        }

        public void addBeanDeserializerModifier(BeanDeserializerModifier beanDeserializerModifier) {
            this.val$mapper._deserializerProvider = this.val$mapper._deserializerProvider.withDeserializerModifier(beanDeserializerModifier);
        }

        public void addBeanSerializerModifier(BeanSerializerModifier beanSerializerModifier) {
            this.val$mapper._serializerFactory = this.val$mapper._serializerFactory.withSerializerModifier(beanSerializerModifier);
        }

        public void addDeserializers(Deserializers deserializers) {
            this.val$mapper._deserializerProvider = this.val$mapper._deserializerProvider.withAdditionalDeserializers(deserializers);
        }

        public void addKeyDeserializers(KeyDeserializers keyDeserializers) {
            this.val$mapper._deserializerProvider = this.val$mapper._deserializerProvider.withAdditionalKeyDeserializers(keyDeserializers);
        }

        public void addKeySerializers(Serializers serializers) {
            this.val$mapper._serializerFactory = this.val$mapper._serializerFactory.withAdditionalKeySerializers(serializers);
        }

        public void addSerializers(Serializers serializers) {
            this.val$mapper._serializerFactory = this.val$mapper._serializerFactory.withAdditionalSerializers(serializers);
        }

        public void addTypeModifier(TypeModifier typeModifier) {
            this.val$mapper.setTypeFactory(this.val$mapper._typeFactory.withModifier(typeModifier));
        }

        public void appendAnnotationIntrospector(AnnotationIntrospector annotationIntrospector) {
            this.val$mapper._deserializationConfig.appendAnnotationIntrospector(annotationIntrospector);
            this.val$mapper._serializationConfig.appendAnnotationIntrospector(annotationIntrospector);
        }

        public DeserializationConfig getDeserializationConfig() {
            return this.val$mapper.getDeserializationConfig();
        }

        public Version getMapperVersion() {
            return ObjectMapper.this.version();
        }

        public SerializationConfig getSerializationConfig() {
            return this.val$mapper.getSerializationConfig();
        }

        public void insertAnnotationIntrospector(AnnotationIntrospector annotationIntrospector) {
            this.val$mapper._deserializationConfig.insertAnnotationIntrospector(annotationIntrospector);
            this.val$mapper._serializationConfig.insertAnnotationIntrospector(annotationIntrospector);
        }

        public void setMixInAnnotations(Class<?> cls, Class<?> cls2) {
            this.val$mapper._deserializationConfig.addMixInAnnotations(cls, cls2);
            this.val$mapper._serializationConfig.addMixInAnnotations(cls, cls2);
        }
    }

    /* renamed from: org.codehaus.jackson.map.ObjectMapper.2 */
    /* synthetic */ class C35812 {
        static final /* synthetic */ int[] $SwitchMap$org$codehaus$jackson$map$ObjectMapper$DefaultTyping;

        static {
            $SwitchMap$org$codehaus$jackson$map$ObjectMapper$DefaultTyping = new int[DefaultTyping.values().length];
            try {
                $SwitchMap$org$codehaus$jackson$map$ObjectMapper$DefaultTyping[DefaultTyping.NON_CONCRETE_AND_ARRAYS.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$map$ObjectMapper$DefaultTyping[DefaultTyping.OBJECT_AND_NON_CONCRETE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$map$ObjectMapper$DefaultTyping[DefaultTyping.NON_FINAL.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    public class DefaultTypeResolverBuilder extends StdTypeResolverBuilder {
        protected final DefaultTyping _appliesFor;

        public DefaultTypeResolverBuilder(DefaultTyping defaultTyping) {
            this._appliesFor = defaultTyping;
        }

        public TypeDeserializer buildTypeDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, Collection<NamedType> collection, BeanProperty beanProperty) {
            return useForType(javaType) ? super.buildTypeDeserializer(deserializationConfig, javaType, collection, beanProperty) : null;
        }

        public TypeSerializer buildTypeSerializer(SerializationConfig serializationConfig, JavaType javaType, Collection<NamedType> collection, BeanProperty beanProperty) {
            return useForType(javaType) ? super.buildTypeSerializer(serializationConfig, javaType, collection, beanProperty) : null;
        }

        public boolean useForType(JavaType javaType) {
            boolean z = false;
            switch (C35812.$SwitchMap$org$codehaus$jackson$map$ObjectMapper$DefaultTyping[this._appliesFor.ordinal()]) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    if (javaType.isArrayType()) {
                        javaType = javaType.getContentType();
                        break;
                    }
                    break;
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    break;
                case Type.BYTE /*3*/:
                    if (javaType.isArrayType()) {
                        javaType = javaType.getContentType();
                    }
                    return !javaType.isFinal();
                default:
                    return javaType.getRawClass() == Object.class;
            }
            if (javaType.getRawClass() == Object.class || !javaType.isConcrete()) {
                z = true;
            }
            return z;
        }
    }

    public enum DefaultTyping {
        JAVA_LANG_OBJECT,
        OBJECT_AND_NON_CONCRETE,
        NON_CONCRETE_AND_ARRAYS,
        NON_FINAL
    }

    static {
        JSON_NODE_TYPE = SimpleType.constructUnsafe(JsonNode.class);
        DEFAULT_INTROSPECTOR = BasicClassIntrospector.instance;
        DEFAULT_ANNOTATION_INTROSPECTOR = new JacksonAnnotationIntrospector();
        STD_VISIBILITY_CHECKER = Std.defaultInstance();
    }

    public ObjectMapper() {
        this(null, null, null);
    }

    public ObjectMapper(JsonFactory jsonFactory) {
        this(jsonFactory, null, null);
    }

    public ObjectMapper(JsonFactory jsonFactory, SerializerProvider serializerProvider, DeserializerProvider deserializerProvider) {
        this(jsonFactory, serializerProvider, deserializerProvider, null, null);
    }

    public ObjectMapper(JsonFactory jsonFactory, SerializerProvider serializerProvider, DeserializerProvider deserializerProvider, SerializationConfig serializationConfig, DeserializationConfig deserializationConfig) {
        this._rootDeserializers = new ConcurrentHashMap(64, 0.6f, 2);
        if (jsonFactory == null) {
            jsonFactory = new MappingJsonFactory(this);
        }
        this._jsonFactory = jsonFactory;
        this._typeFactory = TypeFactory.defaultInstance();
        if (serializationConfig == null) {
            serializationConfig = new SerializationConfig(DEFAULT_INTROSPECTOR, DEFAULT_ANNOTATION_INTROSPECTOR, STD_VISIBILITY_CHECKER, null, null, this._typeFactory, null);
        }
        this._serializationConfig = serializationConfig;
        if (deserializationConfig == null) {
            deserializationConfig = new DeserializationConfig(DEFAULT_INTROSPECTOR, DEFAULT_ANNOTATION_INTROSPECTOR, STD_VISIBILITY_CHECKER, null, null, this._typeFactory, null);
        }
        this._deserializationConfig = deserializationConfig;
        if (serializerProvider == null) {
            serializerProvider = new StdSerializerProvider();
        }
        this._serializerProvider = serializerProvider;
        if (deserializerProvider == null) {
            deserializerProvider = new StdDeserializerProvider();
        }
        this._deserializerProvider = deserializerProvider;
        this._serializerFactory = BeanSerializerFactory.instance;
    }

    @Deprecated
    public ObjectMapper(SerializerFactory serializerFactory) {
        this(null, null, null);
        setSerializerFactory(serializerFactory);
    }

    private final void _configAndWriteCloseable(JsonGenerator jsonGenerator, Object obj, SerializationConfig serializationConfig) {
        Throwable th;
        Closeable closeable;
        Closeable closeable2 = (Closeable) obj;
        try {
            Closeable closeable3;
            this._serializerProvider.serializeValue(serializationConfig, jsonGenerator, obj, this._serializerFactory);
            JsonGenerator jsonGenerator2 = null;
            try {
                jsonGenerator.close();
                closeable3 = null;
            } catch (Throwable th2) {
                jsonGenerator = null;
                Closeable closeable4 = closeable2;
                th = th2;
                closeable = closeable4;
                if (jsonGenerator != null) {
                    try {
                        jsonGenerator.close();
                    } catch (IOException e) {
                    }
                }
                if (closeable != null) {
                    try {
                        closeable.close();
                    } catch (IOException e2) {
                    }
                }
                throw th;
            }
            try {
                closeable2.close();
                if (null != null) {
                    try {
                        jsonGenerator2.close();
                    } catch (IOException e3) {
                    }
                }
                if (null != null) {
                    try {
                        closeable3.close();
                    } catch (IOException e4) {
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                closeable = null;
                jsonGenerator = null;
                if (jsonGenerator != null) {
                    jsonGenerator.close();
                }
                if (closeable != null) {
                    closeable.close();
                }
                throw th;
            }
        } catch (Throwable th22) {
            Throwable th4 = th22;
            closeable = closeable2;
            th = th4;
            if (jsonGenerator != null) {
                jsonGenerator.close();
            }
            if (closeable != null) {
                closeable.close();
            }
            throw th;
        }
    }

    private final void _writeCloseableValue(JsonGenerator jsonGenerator, Object obj, SerializationConfig serializationConfig) {
        Closeable closeable;
        Throwable th;
        Closeable closeable2 = (Closeable) obj;
        try {
            this._serializerProvider.serializeValue(serializationConfig, jsonGenerator, obj, this._serializerFactory);
            if (serializationConfig.isEnabled(Feature.FLUSH_AFTER_WRITE_VALUE)) {
                jsonGenerator.flush();
            }
            closeable = null;
            try {
                closeable2.close();
                if (closeable != null) {
                    try {
                        closeable.close();
                    } catch (IOException e) {
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (closeable != null) {
                    try {
                        closeable.close();
                    } catch (IOException e2) {
                    }
                }
                throw th;
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            closeable = closeable2;
            th = th4;
            if (closeable != null) {
                closeable.close();
            }
            throw th;
        }
    }

    protected final void _configAndWriteValue(JsonGenerator jsonGenerator, Object obj) {
        SerializationConfig copySerializationConfig = copySerializationConfig();
        if (copySerializationConfig.isEnabled(Feature.INDENT_OUTPUT)) {
            jsonGenerator.useDefaultPrettyPrinter();
        }
        if (copySerializationConfig.isEnabled(Feature.CLOSE_CLOSEABLE) && (obj instanceof Closeable)) {
            _configAndWriteCloseable(jsonGenerator, obj, copySerializationConfig);
            return;
        }
        Object obj2 = null;
        try {
            this._serializerProvider.serializeValue(copySerializationConfig, jsonGenerator, obj, this._serializerFactory);
            obj2 = 1;
            jsonGenerator.close();
        } catch (Throwable th) {
            if (obj2 == null) {
                try {
                    jsonGenerator.close();
                } catch (IOException e) {
                }
            }
        }
    }

    protected final void _configAndWriteValue(JsonGenerator jsonGenerator, Object obj, Class<?> cls) {
        SerializationConfig withView = copySerializationConfig().withView(cls);
        if (withView.isEnabled(Feature.INDENT_OUTPUT)) {
            jsonGenerator.useDefaultPrettyPrinter();
        }
        if (withView.isEnabled(Feature.CLOSE_CLOSEABLE) && (obj instanceof Closeable)) {
            _configAndWriteCloseable(jsonGenerator, obj, withView);
            return;
        }
        Object obj2 = null;
        try {
            this._serializerProvider.serializeValue(withView, jsonGenerator, obj, this._serializerFactory);
            obj2 = 1;
            jsonGenerator.close();
        } catch (Throwable th) {
            if (obj2 == null) {
                try {
                    jsonGenerator.close();
                } catch (IOException e) {
                }
            }
        }
    }

    protected Object _convert(Object obj, JavaType javaType) {
        if (obj == null) {
            return null;
        }
        JsonGenerator tokenBuffer = new TokenBuffer(this);
        try {
            writeValue(tokenBuffer, obj);
            JsonParser asParser = tokenBuffer.asParser();
            Object readValue = readValue(asParser, javaType);
            asParser.close();
            return readValue;
        } catch (Throwable e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    protected DeserializationContext _createDeserializationContext(JsonParser jsonParser, DeserializationConfig deserializationConfig) {
        return new StdDeserializationContext(deserializationConfig, jsonParser, this._deserializerProvider);
    }

    protected PrettyPrinter _defaultPrettyPrinter() {
        return new DefaultPrettyPrinter();
    }

    protected JsonDeserializer<Object> _findRootDeserializer(DeserializationConfig deserializationConfig, JavaType javaType) {
        JsonDeserializer<Object> jsonDeserializer = (JsonDeserializer) this._rootDeserializers.get(javaType);
        if (jsonDeserializer == null) {
            jsonDeserializer = this._deserializerProvider.findTypedValueDeserializer(deserializationConfig, javaType, null);
            if (jsonDeserializer == null) {
                throw new JsonMappingException("Can not find a deserializer for type " + javaType);
            }
            this._rootDeserializers.put(javaType, jsonDeserializer);
        }
        return jsonDeserializer;
    }

    protected JsonToken _initForReading(JsonParser jsonParser) {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == null) {
            currentToken = jsonParser.nextToken();
            if (currentToken == null) {
                throw new EOFException("No content to map to Object due to end of input");
            }
        }
        return currentToken;
    }

    protected Object _readMapAndClose(JsonParser jsonParser, JavaType javaType) {
        try {
            Object obj;
            JsonToken _initForReading = _initForReading(jsonParser);
            if (_initForReading == JsonToken.VALUE_NULL || _initForReading == JsonToken.END_ARRAY || _initForReading == JsonToken.END_OBJECT) {
                obj = null;
            } else {
                DeserializationConfig copyDeserializationConfig = copyDeserializationConfig();
                obj = _findRootDeserializer(copyDeserializationConfig, javaType).deserialize(jsonParser, _createDeserializationContext(jsonParser, copyDeserializationConfig));
            }
            jsonParser.clearCurrentToken();
            return obj;
        } finally {
            try {
                jsonParser.close();
            } catch (IOException e) {
            }
        }
    }

    protected Object _readValue(DeserializationConfig deserializationConfig, JsonParser jsonParser, JavaType javaType) {
        Object obj;
        JsonToken _initForReading = _initForReading(jsonParser);
        if (_initForReading == JsonToken.VALUE_NULL || _initForReading == JsonToken.END_ARRAY || _initForReading == JsonToken.END_OBJECT) {
            obj = null;
        } else {
            obj = _findRootDeserializer(deserializationConfig, javaType).deserialize(jsonParser, _createDeserializationContext(jsonParser, deserializationConfig));
        }
        jsonParser.clearCurrentToken();
        return obj;
    }

    public boolean canDeserialize(JavaType javaType) {
        return this._deserializerProvider.hasValueDeserializerFor(copyDeserializationConfig(), javaType);
    }

    public boolean canSerialize(Class<?> cls) {
        return this._serializerProvider.hasSerializerFor(copySerializationConfig(), cls, this._serializerFactory);
    }

    public ObjectMapper configure(JsonGenerator.Feature feature, boolean z) {
        this._jsonFactory.configure(feature, z);
        return this;
    }

    public ObjectMapper configure(JsonParser.Feature feature, boolean z) {
        this._jsonFactory.configure(feature, z);
        return this;
    }

    public ObjectMapper configure(DeserializationConfig.Feature feature, boolean z) {
        this._deserializationConfig.set(feature, z);
        return this;
    }

    public ObjectMapper configure(Feature feature, boolean z) {
        this._serializationConfig.set(feature, z);
        return this;
    }

    public JavaType constructType(java.lang.reflect.Type type) {
        return this._typeFactory.constructType(type);
    }

    public <T> T convertValue(Object obj, Class<T> cls) {
        return _convert(obj, this._typeFactory.constructType((java.lang.reflect.Type) cls));
    }

    public <T> T convertValue(Object obj, JavaType javaType) {
        return _convert(obj, javaType);
    }

    public <T> T convertValue(Object obj, TypeReference typeReference) {
        return _convert(obj, this._typeFactory.constructType(typeReference));
    }

    public DeserializationConfig copyDeserializationConfig() {
        return this._deserializationConfig.createUnshared(this._subtypeResolver);
    }

    public SerializationConfig copySerializationConfig() {
        return this._serializationConfig.createUnshared(this._subtypeResolver);
    }

    public ArrayNode createArrayNode() {
        return this._deserializationConfig.getNodeFactory().arrayNode();
    }

    public ObjectNode createObjectNode() {
        return this._deserializationConfig.getNodeFactory().objectNode();
    }

    public ObjectWriter defaultPrettyPrintingWriter() {
        return new ObjectWriter(this, copySerializationConfig(), null, _defaultPrettyPrinter());
    }

    public ObjectMapper disableDefaultTyping() {
        return setDefaultTyping(null);
    }

    public ObjectMapper enableDefaultTyping() {
        return enableDefaultTyping(DefaultTyping.OBJECT_AND_NON_CONCRETE);
    }

    public ObjectMapper enableDefaultTyping(DefaultTyping defaultTyping) {
        return enableDefaultTyping(defaultTyping, As.WRAPPER_ARRAY);
    }

    public ObjectMapper enableDefaultTyping(DefaultTyping defaultTyping, As as) {
        return setDefaultTyping(new DefaultTypeResolverBuilder(defaultTyping).init(Id.CLASS, null).inclusion(as));
    }

    public ObjectMapper enableDefaultTypingAsProperty(DefaultTyping defaultTyping, String str) {
        return setDefaultTyping(new DefaultTypeResolverBuilder(defaultTyping).init(Id.CLASS, null).inclusion(As.PROPERTY).typeProperty(str));
    }

    public ObjectWriter filteredWriter(FilterProvider filterProvider) {
        return new ObjectWriter(this, copySerializationConfig().withFilters(filterProvider));
    }

    public JsonSchema generateJsonSchema(Class<?> cls) {
        return generateJsonSchema(cls, copySerializationConfig());
    }

    public JsonSchema generateJsonSchema(Class<?> cls, SerializationConfig serializationConfig) {
        return this._serializerProvider.generateJsonSchema(cls, serializationConfig, this._serializerFactory);
    }

    public DeserializationConfig getDeserializationConfig() {
        return this._deserializationConfig;
    }

    public DeserializerProvider getDeserializerProvider() {
        return this._deserializerProvider;
    }

    public JsonFactory getJsonFactory() {
        return this._jsonFactory;
    }

    public JsonNodeFactory getNodeFactory() {
        return this._deserializationConfig.getNodeFactory();
    }

    public SerializationConfig getSerializationConfig() {
        return this._serializationConfig;
    }

    public SerializerProvider getSerializerProvider() {
        return this._serializerProvider;
    }

    public SubtypeResolver getSubtypeResolver() {
        if (this._subtypeResolver == null) {
            this._subtypeResolver = new StdSubtypeResolver();
        }
        return this._subtypeResolver;
    }

    public TypeFactory getTypeFactory() {
        return this._typeFactory;
    }

    public VisibilityChecker<?> getVisibilityChecker() {
        return this._serializationConfig.getDefaultVisibilityChecker();
    }

    public ObjectWriter prettyPrintingWriter(PrettyPrinter prettyPrinter) {
        if (prettyPrinter == null) {
            prettyPrinter = ObjectWriter.NULL_PRETTY_PRINTER;
        }
        return new ObjectWriter(this, copySerializationConfig(), null, prettyPrinter);
    }

    public JsonNode readTree(InputStream inputStream) {
        JsonNode jsonNode = (JsonNode) readValue(inputStream, JSON_NODE_TYPE);
        return jsonNode == null ? NullNode.instance : jsonNode;
    }

    public JsonNode readTree(Reader reader) {
        JsonNode jsonNode = (JsonNode) readValue(reader, JSON_NODE_TYPE);
        return jsonNode == null ? NullNode.instance : jsonNode;
    }

    public JsonNode readTree(String str) {
        JsonNode jsonNode = (JsonNode) readValue(str, JSON_NODE_TYPE);
        return jsonNode == null ? NullNode.instance : jsonNode;
    }

    public JsonNode readTree(JsonParser jsonParser) {
        return readTree(jsonParser, copyDeserializationConfig());
    }

    public JsonNode readTree(JsonParser jsonParser, DeserializationConfig deserializationConfig) {
        JsonNode jsonNode = (JsonNode) _readValue(deserializationConfig, jsonParser, JSON_NODE_TYPE);
        return jsonNode == null ? NullNode.instance : jsonNode;
    }

    public <T> T readValue(File file, Class<T> cls) {
        return _readMapAndClose(this._jsonFactory.createJsonParser(file), this._typeFactory.constructType((java.lang.reflect.Type) cls));
    }

    public <T> T readValue(File file, JavaType javaType) {
        return _readMapAndClose(this._jsonFactory.createJsonParser(file), javaType);
    }

    public <T> T readValue(File file, TypeReference typeReference) {
        return _readMapAndClose(this._jsonFactory.createJsonParser(file), this._typeFactory.constructType(typeReference));
    }

    public <T> T readValue(InputStream inputStream, Class<T> cls) {
        return _readMapAndClose(this._jsonFactory.createJsonParser(inputStream), this._typeFactory.constructType((java.lang.reflect.Type) cls));
    }

    public <T> T readValue(InputStream inputStream, JavaType javaType) {
        return _readMapAndClose(this._jsonFactory.createJsonParser(inputStream), javaType);
    }

    public <T> T readValue(InputStream inputStream, TypeReference typeReference) {
        return _readMapAndClose(this._jsonFactory.createJsonParser(inputStream), this._typeFactory.constructType(typeReference));
    }

    public <T> T readValue(Reader reader, Class<T> cls) {
        return _readMapAndClose(this._jsonFactory.createJsonParser(reader), this._typeFactory.constructType((java.lang.reflect.Type) cls));
    }

    public <T> T readValue(Reader reader, JavaType javaType) {
        return _readMapAndClose(this._jsonFactory.createJsonParser(reader), javaType);
    }

    public <T> T readValue(Reader reader, TypeReference typeReference) {
        return _readMapAndClose(this._jsonFactory.createJsonParser(reader), this._typeFactory.constructType(typeReference));
    }

    public <T> T readValue(String str, Class<T> cls) {
        return _readMapAndClose(this._jsonFactory.createJsonParser(str), this._typeFactory.constructType((java.lang.reflect.Type) cls));
    }

    public <T> T readValue(String str, JavaType javaType) {
        return _readMapAndClose(this._jsonFactory.createJsonParser(str), javaType);
    }

    public <T> T readValue(String str, TypeReference typeReference) {
        return _readMapAndClose(this._jsonFactory.createJsonParser(str), this._typeFactory.constructType(typeReference));
    }

    public <T> T readValue(URL url, Class<T> cls) {
        return _readMapAndClose(this._jsonFactory.createJsonParser(url), this._typeFactory.constructType((java.lang.reflect.Type) cls));
    }

    public <T> T readValue(URL url, JavaType javaType) {
        return _readMapAndClose(this._jsonFactory.createJsonParser(url), javaType);
    }

    public <T> T readValue(URL url, TypeReference typeReference) {
        return _readMapAndClose(this._jsonFactory.createJsonParser(url), this._typeFactory.constructType(typeReference));
    }

    public <T> T readValue(JsonNode jsonNode, Class<T> cls) {
        return _readValue(copyDeserializationConfig(), treeAsTokens(jsonNode), this._typeFactory.constructType((java.lang.reflect.Type) cls));
    }

    public <T> T readValue(JsonNode jsonNode, JavaType javaType) {
        return _readValue(copyDeserializationConfig(), treeAsTokens(jsonNode), javaType);
    }

    public <T> T readValue(JsonNode jsonNode, TypeReference typeReference) {
        return _readValue(copyDeserializationConfig(), treeAsTokens(jsonNode), this._typeFactory.constructType(typeReference));
    }

    public <T> T readValue(JsonParser jsonParser, Class<T> cls) {
        return _readValue(copyDeserializationConfig(), jsonParser, this._typeFactory.constructType((java.lang.reflect.Type) cls));
    }

    public <T> T readValue(JsonParser jsonParser, Class<T> cls, DeserializationConfig deserializationConfig) {
        return _readValue(deserializationConfig, jsonParser, this._typeFactory.constructType((java.lang.reflect.Type) cls));
    }

    public <T> T readValue(JsonParser jsonParser, JavaType javaType) {
        return _readValue(copyDeserializationConfig(), jsonParser, javaType);
    }

    public <T> T readValue(JsonParser jsonParser, JavaType javaType, DeserializationConfig deserializationConfig) {
        return _readValue(deserializationConfig, jsonParser, javaType);
    }

    public <T> T readValue(JsonParser jsonParser, TypeReference<?> typeReference) {
        return _readValue(copyDeserializationConfig(), jsonParser, this._typeFactory.constructType((TypeReference) typeReference));
    }

    public <T> T readValue(JsonParser jsonParser, TypeReference<?> typeReference, DeserializationConfig deserializationConfig) {
        return _readValue(deserializationConfig, jsonParser, this._typeFactory.constructType((TypeReference) typeReference));
    }

    public <T> T readValue(byte[] bArr, int i, int i2, Class<T> cls) {
        return _readMapAndClose(this._jsonFactory.createJsonParser(bArr, i, i2), this._typeFactory.constructType((java.lang.reflect.Type) cls));
    }

    public <T> T readValue(byte[] bArr, int i, int i2, JavaType javaType) {
        return _readMapAndClose(this._jsonFactory.createJsonParser(bArr, i, i2), javaType);
    }

    public <T> T readValue(byte[] bArr, int i, int i2, TypeReference typeReference) {
        return _readMapAndClose(this._jsonFactory.createJsonParser(bArr, i, i2), this._typeFactory.constructType(typeReference));
    }

    public <T> T readValue(byte[] bArr, Class<T> cls) {
        return _readMapAndClose(this._jsonFactory.createJsonParser(bArr), this._typeFactory.constructType((java.lang.reflect.Type) cls));
    }

    public <T> T readValue(byte[] bArr, JavaType javaType) {
        return _readMapAndClose(this._jsonFactory.createJsonParser(bArr), javaType);
    }

    public <T> T readValue(byte[] bArr, TypeReference typeReference) {
        return _readMapAndClose(this._jsonFactory.createJsonParser(bArr), this._typeFactory.constructType(typeReference));
    }

    public <T> MappingIterator<T> readValues(JsonParser jsonParser, Class<?> cls) {
        return readValues(jsonParser, this._typeFactory.constructType((java.lang.reflect.Type) cls));
    }

    public <T> MappingIterator<T> readValues(JsonParser jsonParser, JavaType javaType) {
        DeserializationConfig copyDeserializationConfig = copyDeserializationConfig();
        return new MappingIterator(javaType, jsonParser, _createDeserializationContext(jsonParser, copyDeserializationConfig), _findRootDeserializer(copyDeserializationConfig, javaType));
    }

    public <T> MappingIterator<T> readValues(JsonParser jsonParser, TypeReference<?> typeReference) {
        return readValues(jsonParser, this._typeFactory.constructType((TypeReference) typeReference));
    }

    public ObjectReader reader() {
        return new ObjectReader(this, copyDeserializationConfig());
    }

    public ObjectReader reader(Class<?> cls) {
        return reader(this._typeFactory.constructType((java.lang.reflect.Type) cls));
    }

    public ObjectReader reader(JsonNodeFactory jsonNodeFactory) {
        return new ObjectReader(this, copyDeserializationConfig()).withNodeFactory(jsonNodeFactory);
    }

    public ObjectReader reader(JavaType javaType) {
        return new ObjectReader(this, copyDeserializationConfig(), javaType, null, null);
    }

    public ObjectReader reader(TypeReference<?> typeReference) {
        return reader(this._typeFactory.constructType((TypeReference) typeReference));
    }

    public void registerModule(Module module) {
        if (module.getModuleName() == null) {
            throw new IllegalArgumentException("Module without defined name");
        } else if (module.version() == null) {
            throw new IllegalArgumentException("Module without defined version");
        } else {
            module.setupModule(new C35801(this));
        }
    }

    public void registerSubtypes(Class<?>... clsArr) {
        getSubtypeResolver().registerSubtypes((Class[]) clsArr);
    }

    public void registerSubtypes(NamedType... namedTypeArr) {
        getSubtypeResolver().registerSubtypes(namedTypeArr);
    }

    public ObjectReader schemaBasedReader(FormatSchema formatSchema) {
        return new ObjectReader(this, copyDeserializationConfig(), null, null, formatSchema);
    }

    public ObjectWriter schemaBasedWriter(FormatSchema formatSchema) {
        return new ObjectWriter(this, copySerializationConfig(), formatSchema);
    }

    public ObjectMapper setAnnotationIntrospector(AnnotationIntrospector annotationIntrospector) {
        this._serializationConfig = this._serializationConfig.withAnnotationIntrospector(annotationIntrospector);
        this._deserializationConfig = this._deserializationConfig.withAnnotationIntrospector(annotationIntrospector);
        return this;
    }

    public void setDateFormat(DateFormat dateFormat) {
        this._deserializationConfig = this._deserializationConfig.withDateFormat(dateFormat);
        this._serializationConfig = this._serializationConfig.withDateFormat(dateFormat);
    }

    public ObjectMapper setDefaultTyping(TypeResolverBuilder<?> typeResolverBuilder) {
        this._deserializationConfig = this._deserializationConfig.withTypeResolverBuilder((TypeResolverBuilder) typeResolverBuilder);
        this._serializationConfig = this._serializationConfig.withTypeResolverBuilder((TypeResolverBuilder) typeResolverBuilder);
        return this;
    }

    public ObjectMapper setDeserializationConfig(DeserializationConfig deserializationConfig) {
        this._deserializationConfig = deserializationConfig;
        return this;
    }

    public ObjectMapper setDeserializerProvider(DeserializerProvider deserializerProvider) {
        this._deserializerProvider = deserializerProvider;
        return this;
    }

    public void setFilters(FilterProvider filterProvider) {
        this._serializationConfig = this._serializationConfig.withFilters(filterProvider);
    }

    public void setHandlerInstantiator(HandlerInstantiator handlerInstantiator) {
        this._deserializationConfig = this._deserializationConfig.withHandlerInstantiator(handlerInstantiator);
        this._serializationConfig = this._serializationConfig.withHandlerInstantiator(handlerInstantiator);
    }

    public ObjectMapper setNodeFactory(JsonNodeFactory jsonNodeFactory) {
        this._deserializationConfig = this._deserializationConfig.withNodeFactory(jsonNodeFactory);
        return this;
    }

    public ObjectMapper setPropertyNamingStrategy(PropertyNamingStrategy propertyNamingStrategy) {
        this._serializationConfig = this._serializationConfig.withPropertyNamingStrategy(propertyNamingStrategy);
        this._deserializationConfig = this._deserializationConfig.withPropertyNamingStrategy(propertyNamingStrategy);
        return this;
    }

    public ObjectMapper setSerializationConfig(SerializationConfig serializationConfig) {
        this._serializationConfig = serializationConfig;
        return this;
    }

    public ObjectMapper setSerializerFactory(SerializerFactory serializerFactory) {
        this._serializerFactory = serializerFactory;
        return this;
    }

    public ObjectMapper setSerializerProvider(SerializerProvider serializerProvider) {
        this._serializerProvider = serializerProvider;
        return this;
    }

    public void setSubtypeResolver(SubtypeResolver subtypeResolver) {
        this._subtypeResolver = subtypeResolver;
    }

    public ObjectMapper setTypeFactory(TypeFactory typeFactory) {
        this._typeFactory = typeFactory;
        this._deserializationConfig = this._deserializationConfig.withTypeFactory(typeFactory);
        this._serializationConfig = this._serializationConfig.withTypeFactory(typeFactory);
        return this;
    }

    public void setVisibilityChecker(VisibilityChecker<?> visibilityChecker) {
        this._deserializationConfig = this._deserializationConfig.withVisibilityChecker((VisibilityChecker) visibilityChecker);
        this._serializationConfig = this._serializationConfig.withVisibilityChecker((VisibilityChecker) visibilityChecker);
    }

    public JsonParser treeAsTokens(JsonNode jsonNode) {
        return new TreeTraversingParser(jsonNode, this);
    }

    public <T> T treeToValue(JsonNode jsonNode, Class<T> cls) {
        return readValue(treeAsTokens(jsonNode), (Class) cls);
    }

    public ObjectWriter typedWriter(Class<?> cls) {
        return new ObjectWriter(this, copySerializationConfig(), cls == null ? null : this._typeFactory.constructType((java.lang.reflect.Type) cls), null);
    }

    public ObjectWriter typedWriter(JavaType javaType) {
        return new ObjectWriter(this, copySerializationConfig(), javaType, null);
    }

    public ObjectWriter typedWriter(TypeReference<?> typeReference) {
        return new ObjectWriter(this, copySerializationConfig(), typeReference == null ? null : this._typeFactory.constructType((TypeReference) typeReference), null);
    }

    public ObjectReader updatingReader(Object obj) {
        return new ObjectReader(this, copyDeserializationConfig(), this._typeFactory.constructType(obj.getClass()), obj, null);
    }

    public <T extends JsonNode> T valueToTree(Object obj) {
        if (obj == null) {
            return null;
        }
        JsonGenerator tokenBuffer = new TokenBuffer(this);
        try {
            writeValue(tokenBuffer, obj);
            JsonParser asParser = tokenBuffer.asParser();
            T readTree = readTree(asParser);
            asParser.close();
            return readTree;
        } catch (Throwable e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    public Version version() {
        return VersionUtil.versionFor(getClass());
    }

    public ObjectWriter viewWriter(Class<?> cls) {
        return new ObjectWriter(this, copySerializationConfig().withView(cls));
    }

    public ObjectMapper withModule(Module module) {
        registerModule(module);
        return this;
    }

    public void writeTree(JsonGenerator jsonGenerator, JsonNode jsonNode) {
        SerializationConfig copySerializationConfig = copySerializationConfig();
        this._serializerProvider.serializeValue(copySerializationConfig, jsonGenerator, jsonNode, this._serializerFactory);
        if (copySerializationConfig.isEnabled(Feature.FLUSH_AFTER_WRITE_VALUE)) {
            jsonGenerator.flush();
        }
    }

    public void writeTree(JsonGenerator jsonGenerator, JsonNode jsonNode, SerializationConfig serializationConfig) {
        this._serializerProvider.serializeValue(serializationConfig, jsonGenerator, jsonNode, this._serializerFactory);
        if (serializationConfig.isEnabled(Feature.FLUSH_AFTER_WRITE_VALUE)) {
            jsonGenerator.flush();
        }
    }

    public void writeValue(File file, Object obj) {
        _configAndWriteValue(this._jsonFactory.createJsonGenerator(file, JsonEncoding.UTF8), obj);
    }

    public void writeValue(OutputStream outputStream, Object obj) {
        _configAndWriteValue(this._jsonFactory.createJsonGenerator(outputStream, JsonEncoding.UTF8), obj);
    }

    public void writeValue(Writer writer, Object obj) {
        _configAndWriteValue(this._jsonFactory.createJsonGenerator(writer), obj);
    }

    public void writeValue(JsonGenerator jsonGenerator, Object obj) {
        SerializationConfig copySerializationConfig = copySerializationConfig();
        if (copySerializationConfig.isEnabled(Feature.CLOSE_CLOSEABLE) && (obj instanceof Closeable)) {
            _writeCloseableValue(jsonGenerator, obj, copySerializationConfig);
            return;
        }
        this._serializerProvider.serializeValue(copySerializationConfig, jsonGenerator, obj, this._serializerFactory);
        if (copySerializationConfig.isEnabled(Feature.FLUSH_AFTER_WRITE_VALUE)) {
            jsonGenerator.flush();
        }
    }

    public void writeValue(JsonGenerator jsonGenerator, Object obj, SerializationConfig serializationConfig) {
        if (serializationConfig.isEnabled(Feature.CLOSE_CLOSEABLE) && (obj instanceof Closeable)) {
            _writeCloseableValue(jsonGenerator, obj, serializationConfig);
            return;
        }
        this._serializerProvider.serializeValue(serializationConfig, jsonGenerator, obj, this._serializerFactory);
        if (serializationConfig.isEnabled(Feature.FLUSH_AFTER_WRITE_VALUE)) {
            jsonGenerator.flush();
        }
    }

    public byte[] writeValueAsBytes(Object obj) {
        OutputStream byteArrayBuilder = new ByteArrayBuilder(this._jsonFactory._getBufferRecycler());
        _configAndWriteValue(this._jsonFactory.createJsonGenerator(byteArrayBuilder, JsonEncoding.UTF8), obj);
        byte[] toByteArray = byteArrayBuilder.toByteArray();
        byteArrayBuilder.release();
        return toByteArray;
    }

    public String writeValueAsString(Object obj) {
        Writer segmentedStringWriter = new SegmentedStringWriter(this._jsonFactory._getBufferRecycler());
        _configAndWriteValue(this._jsonFactory.createJsonGenerator(segmentedStringWriter), obj);
        return segmentedStringWriter.getAndClear();
    }

    @Deprecated
    public void writeValueUsingView(OutputStream outputStream, Object obj, Class<?> cls) {
        _configAndWriteValue(this._jsonFactory.createJsonGenerator(outputStream, JsonEncoding.UTF8), obj, cls);
    }

    @Deprecated
    public void writeValueUsingView(Writer writer, Object obj, Class<?> cls) {
        _configAndWriteValue(this._jsonFactory.createJsonGenerator(writer), obj, cls);
    }

    @Deprecated
    public void writeValueUsingView(JsonGenerator jsonGenerator, Object obj, Class<?> cls) {
        _configAndWriteValue(jsonGenerator, obj, cls);
    }

    public ObjectWriter writer() {
        return new ObjectWriter(this, copySerializationConfig());
    }
}
