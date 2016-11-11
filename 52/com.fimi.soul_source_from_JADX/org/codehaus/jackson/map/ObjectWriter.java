package org.codehaus.jackson.map;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import org.codehaus.jackson.FormatSchema;
import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.PrettyPrinter;
import org.codehaus.jackson.Version;
import org.codehaus.jackson.Versioned;
import org.codehaus.jackson.io.SegmentedStringWriter;
import org.codehaus.jackson.map.SerializationConfig.Feature;
import org.codehaus.jackson.map.ser.FilterProvider;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.type.TypeReference;
import org.codehaus.jackson.util.ByteArrayBuilder;
import org.codehaus.jackson.util.DefaultPrettyPrinter;
import org.codehaus.jackson.util.MinimalPrettyPrinter;
import org.codehaus.jackson.util.VersionUtil;

public class ObjectWriter implements Versioned {
    protected static final PrettyPrinter NULL_PRETTY_PRINTER;
    protected final SerializationConfig _config;
    protected final JsonFactory _jsonFactory;
    protected final PrettyPrinter _prettyPrinter;
    protected final SerializerProvider _provider;
    protected final JavaType _rootType;
    protected final FormatSchema _schema;
    protected final SerializerFactory _serializerFactory;

    static {
        NULL_PRETTY_PRINTER = new MinimalPrettyPrinter();
    }

    protected ObjectWriter(ObjectMapper objectMapper, SerializationConfig serializationConfig) {
        this._config = serializationConfig;
        this._provider = objectMapper._serializerProvider;
        this._serializerFactory = objectMapper._serializerFactory;
        this._jsonFactory = objectMapper._jsonFactory;
        this._rootType = null;
        this._prettyPrinter = null;
        this._schema = null;
    }

    protected ObjectWriter(ObjectMapper objectMapper, SerializationConfig serializationConfig, FormatSchema formatSchema) {
        this._config = serializationConfig;
        this._provider = objectMapper._serializerProvider;
        this._serializerFactory = objectMapper._serializerFactory;
        this._jsonFactory = objectMapper._jsonFactory;
        this._rootType = null;
        this._prettyPrinter = null;
        this._schema = formatSchema;
    }

    protected ObjectWriter(ObjectMapper objectMapper, SerializationConfig serializationConfig, JavaType javaType, PrettyPrinter prettyPrinter) {
        this._config = serializationConfig;
        this._provider = objectMapper._serializerProvider;
        this._serializerFactory = objectMapper._serializerFactory;
        this._jsonFactory = objectMapper._jsonFactory;
        this._rootType = javaType;
        this._prettyPrinter = prettyPrinter;
        this._schema = null;
    }

    protected ObjectWriter(ObjectWriter objectWriter, SerializationConfig serializationConfig) {
        this._config = serializationConfig;
        this._provider = objectWriter._provider;
        this._serializerFactory = objectWriter._serializerFactory;
        this._jsonFactory = objectWriter._jsonFactory;
        this._schema = objectWriter._schema;
        this._rootType = objectWriter._rootType;
        this._prettyPrinter = objectWriter._prettyPrinter;
    }

    protected ObjectWriter(ObjectWriter objectWriter, SerializationConfig serializationConfig, JavaType javaType, PrettyPrinter prettyPrinter, FormatSchema formatSchema) {
        this._config = serializationConfig;
        this._provider = objectWriter._provider;
        this._serializerFactory = objectWriter._serializerFactory;
        this._jsonFactory = objectWriter._jsonFactory;
        this._rootType = javaType;
        this._prettyPrinter = prettyPrinter;
        this._schema = formatSchema;
    }

    private final void _configAndWriteCloseable(JsonGenerator jsonGenerator, Object obj, SerializationConfig serializationConfig) {
        Throwable th;
        Closeable closeable = (Closeable) obj;
        try {
            if (this._rootType == null) {
                this._provider.serializeValue(serializationConfig, jsonGenerator, obj, this._serializerFactory);
            } else {
                this._provider.serializeValue(serializationConfig, jsonGenerator, obj, this._rootType, this._serializerFactory);
            }
            if (this._schema != null) {
                jsonGenerator.setSchema(this._schema);
            }
            JsonGenerator jsonGenerator2 = null;
            try {
                jsonGenerator.close();
                Closeable closeable2 = null;
                try {
                    closeable.close();
                    if (null != null) {
                        try {
                            jsonGenerator2.close();
                        } catch (IOException e) {
                        }
                    }
                    if (null != null) {
                        try {
                            closeable2.close();
                        } catch (IOException e2) {
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    closeable = null;
                    jsonGenerator = null;
                    if (jsonGenerator != null) {
                        try {
                            jsonGenerator.close();
                        } catch (IOException e3) {
                        }
                    }
                    if (closeable != null) {
                        try {
                            closeable.close();
                        } catch (IOException e4) {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                jsonGenerator = null;
                if (jsonGenerator != null) {
                    jsonGenerator.close();
                }
                if (closeable != null) {
                    closeable.close();
                }
                throw th;
            }
        } catch (Throwable th4) {
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
        Throwable th;
        Closeable closeable = (Closeable) obj;
        try {
            if (this._rootType == null) {
                this._provider.serializeValue(serializationConfig, jsonGenerator, obj, this._serializerFactory);
            } else {
                this._provider.serializeValue(serializationConfig, jsonGenerator, obj, this._rootType, this._serializerFactory);
            }
            if (this._config.isEnabled(Feature.FLUSH_AFTER_WRITE_VALUE)) {
                jsonGenerator.flush();
            }
            Closeable closeable2 = null;
            try {
                closeable.close();
                if (closeable2 != null) {
                    try {
                        closeable2.close();
                    } catch (IOException e) {
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                closeable = closeable2;
                if (closeable != null) {
                    try {
                        closeable.close();
                    } catch (IOException e2) {
                    }
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            if (closeable != null) {
                closeable.close();
            }
            throw th;
        }
    }

    protected final void _configAndWriteValue(JsonGenerator jsonGenerator, Object obj) {
        Object obj2;
        Throwable th;
        if (this._prettyPrinter != null) {
            PrettyPrinter prettyPrinter = this._prettyPrinter;
            if (prettyPrinter == NULL_PRETTY_PRINTER) {
                prettyPrinter = null;
            }
            jsonGenerator.setPrettyPrinter(prettyPrinter);
        } else if (this._config.isEnabled(Feature.INDENT_OUTPUT)) {
            jsonGenerator.useDefaultPrettyPrinter();
        }
        if (this._schema != null) {
            jsonGenerator.setSchema(this._schema);
        }
        if (this._config.isEnabled(Feature.CLOSE_CLOSEABLE) && (obj instanceof Closeable)) {
            _configAndWriteCloseable(jsonGenerator, obj, this._config);
            return;
        }
        try {
            if (this._rootType == null) {
                this._provider.serializeValue(this._config, jsonGenerator, obj, this._serializerFactory);
            } else {
                this._provider.serializeValue(this._config, jsonGenerator, obj, this._rootType, this._serializerFactory);
            }
            obj2 = 1;
            try {
                jsonGenerator.close();
            } catch (Throwable th2) {
                th = th2;
                if (obj2 == null) {
                    try {
                        jsonGenerator.close();
                    } catch (IOException e) {
                    }
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            obj2 = null;
            if (obj2 == null) {
                jsonGenerator.close();
            }
            throw th;
        }
    }

    public boolean canSerialize(Class<?> cls) {
        return this._provider.hasSerializerFor(this._config, cls, this._serializerFactory);
    }

    public Version version() {
        return VersionUtil.versionFor(getClass());
    }

    public ObjectWriter withDefaultPrettyPrinter() {
        return withPrettyPrinter(new DefaultPrettyPrinter());
    }

    public ObjectWriter withFilters(FilterProvider filterProvider) {
        return filterProvider == this._config.getFilterProvider() ? this : new ObjectWriter(this, this._config.withFilters(filterProvider));
    }

    public ObjectWriter withPrettyPrinter(PrettyPrinter prettyPrinter) {
        if (prettyPrinter == this._prettyPrinter) {
            return this;
        }
        return new ObjectWriter(this, this._config, this._rootType, prettyPrinter == null ? NULL_PRETTY_PRINTER : prettyPrinter, this._schema);
    }

    public ObjectWriter withSchema(FormatSchema formatSchema) {
        return this._schema == formatSchema ? this : new ObjectWriter(this, this._config, this._rootType, this._prettyPrinter, formatSchema);
    }

    public ObjectWriter withType(Class<?> cls) {
        return withType(this._config.constructType(cls));
    }

    public ObjectWriter withType(JavaType javaType) {
        if (javaType == this._rootType) {
            return this;
        }
        return new ObjectWriter(this, this._config, javaType, this._prettyPrinter, this._schema);
    }

    public ObjectWriter withType(TypeReference<?> typeReference) {
        return withType(this._config.getTypeFactory().constructType(typeReference.getType()));
    }

    public ObjectWriter withView(Class<?> cls) {
        return cls == this._config.getSerializationView() ? this : new ObjectWriter(this, this._config.withView(cls));
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
        if (this._config.isEnabled(Feature.CLOSE_CLOSEABLE) && (obj instanceof Closeable)) {
            _writeCloseableValue(jsonGenerator, obj, this._config);
            return;
        }
        if (this._rootType == null) {
            this._provider.serializeValue(this._config, jsonGenerator, obj, this._serializerFactory);
        } else {
            this._provider.serializeValue(this._config, jsonGenerator, obj, this._rootType, this._serializerFactory);
        }
        if (this._config.isEnabled(Feature.FLUSH_AFTER_WRITE_VALUE)) {
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
}
