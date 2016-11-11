package org.codehaus.jackson.jaxrs;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.HashSet;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.Providers;
import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonGenerator.Feature;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.Version;
import org.codehaus.jackson.Versioned;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.type.ClassKey;
import org.codehaus.jackson.map.util.ClassUtil;
import org.codehaus.jackson.map.util.JSONPObject;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.util.VersionUtil;

@Consumes({"application/json", "text/json"})
@Produces({"application/json", "text/json"})
@Provider
public class JacksonJsonProvider implements MessageBodyReader<Object>, MessageBodyWriter<Object>, Versioned {
    public static final Annotations[] BASIC_ANNOTATIONS;
    public static final Class<?>[] _unreadableClasses;
    public static final HashSet<ClassKey> _untouchables;
    public static final Class<?>[] _unwritableClasses;
    protected boolean _cfgCheckCanDeserialize;
    protected boolean _cfgCheckCanSerialize;
    protected HashSet<ClassKey> _cfgCustomUntouchables;
    protected String _jsonpFunctionName;
    protected final MapperConfigurator _mapperConfig;
    @Context
    protected Providers _providers;

    static {
        BASIC_ANNOTATIONS = new Annotations[]{Annotations.JACKSON};
        _untouchables = new HashSet();
        _untouchables.add(new ClassKey(InputStream.class));
        _untouchables.add(new ClassKey(Reader.class));
        _untouchables.add(new ClassKey(OutputStream.class));
        _untouchables.add(new ClassKey(Writer.class));
        _untouchables.add(new ClassKey(byte[].class));
        _untouchables.add(new ClassKey(char[].class));
        _untouchables.add(new ClassKey(String.class));
        _untouchables.add(new ClassKey(StreamingOutput.class));
        _untouchables.add(new ClassKey(Response.class));
        _unreadableClasses = new Class[]{InputStream.class, Reader.class};
        _unwritableClasses = new Class[]{OutputStream.class, Writer.class, StreamingOutput.class, Response.class};
    }

    public JacksonJsonProvider() {
        this(null, BASIC_ANNOTATIONS);
    }

    public JacksonJsonProvider(ObjectMapper objectMapper) {
        this(objectMapper, BASIC_ANNOTATIONS);
    }

    public JacksonJsonProvider(ObjectMapper objectMapper, Annotations[] annotationsArr) {
        this._cfgCheckCanSerialize = false;
        this._cfgCheckCanDeserialize = false;
        this._mapperConfig = new MapperConfigurator(objectMapper, annotationsArr);
    }

    public JacksonJsonProvider(Annotations... annotationsArr) {
        this(null, annotationsArr);
    }

    protected static boolean _containedIn(Class<?> cls, HashSet<ClassKey> hashSet) {
        if (hashSet != null) {
            ClassKey classKey = new ClassKey(cls);
            if (hashSet.contains(classKey)) {
                return true;
            }
            for (Class reset : ClassUtil.findSuperTypes(cls, null)) {
                classKey.reset(reset);
                if (hashSet.contains(classKey)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void addUntouchable(Class<?> cls) {
        if (this._cfgCustomUntouchables == null) {
            this._cfgCustomUntouchables = new HashSet();
        }
        this._cfgCustomUntouchables.add(new ClassKey(cls));
    }

    public void checkCanDeserialize(boolean z) {
        this._cfgCheckCanDeserialize = z;
    }

    public void checkCanSerialize(boolean z) {
        this._cfgCheckCanSerialize = z;
    }

    public JacksonJsonProvider configure(Feature feature, boolean z) {
        this._mapperConfig.configure(feature, z);
        return this;
    }

    public JacksonJsonProvider configure(JsonParser.Feature feature, boolean z) {
        this._mapperConfig.configure(feature, z);
        return this;
    }

    public JacksonJsonProvider configure(DeserializationConfig.Feature feature, boolean z) {
        this._mapperConfig.configure(feature, z);
        return this;
    }

    public JacksonJsonProvider configure(SerializationConfig.Feature feature, boolean z) {
        this._mapperConfig.configure(feature, z);
        return this;
    }

    public JacksonJsonProvider disable(Feature feature, boolean z) {
        this._mapperConfig.configure(feature, false);
        return this;
    }

    public JacksonJsonProvider disable(JsonParser.Feature feature, boolean z) {
        this._mapperConfig.configure(feature, false);
        return this;
    }

    public JacksonJsonProvider disable(DeserializationConfig.Feature feature, boolean z) {
        this._mapperConfig.configure(feature, false);
        return this;
    }

    public JacksonJsonProvider disable(SerializationConfig.Feature feature, boolean z) {
        this._mapperConfig.configure(feature, false);
        return this;
    }

    public JacksonJsonProvider enable(Feature feature, boolean z) {
        this._mapperConfig.configure(feature, true);
        return this;
    }

    public JacksonJsonProvider enable(JsonParser.Feature feature, boolean z) {
        this._mapperConfig.configure(feature, true);
        return this;
    }

    public JacksonJsonProvider enable(DeserializationConfig.Feature feature, boolean z) {
        this._mapperConfig.configure(feature, true);
        return this;
    }

    public JacksonJsonProvider enable(SerializationConfig.Feature feature, boolean z) {
        this._mapperConfig.configure(feature, true);
        return this;
    }

    protected JsonEncoding findEncoding(MediaType mediaType, MultivaluedMap<String, Object> multivaluedMap) {
        return JsonEncoding.UTF8;
    }

    public long getSize(Object obj, Class<?> cls, Type type, Annotation[] annotationArr, MediaType mediaType) {
        return -1;
    }

    protected boolean isJsonType(MediaType mediaType) {
        if (mediaType == null) {
            return true;
        }
        String subtype = mediaType.getSubtype();
        return "json".equalsIgnoreCase(subtype) || subtype.endsWith("+json");
    }

    public boolean isReadable(Class<?> cls, Type type, Annotation[] annotationArr, MediaType mediaType) {
        if (!isJsonType(mediaType) || _untouchables.contains(new ClassKey(cls))) {
            return false;
        }
        for (Class isAssignableFrom : _unreadableClasses) {
            if (isAssignableFrom.isAssignableFrom(cls)) {
                return false;
            }
        }
        if (_containedIn(cls, this._cfgCustomUntouchables)) {
            return false;
        }
        if (this._cfgCheckCanSerialize) {
            ObjectMapper locateMapper = locateMapper(cls, mediaType);
            if (!locateMapper.canDeserialize(locateMapper.constructType(cls))) {
                return false;
            }
        }
        return true;
    }

    public boolean isWriteable(Class<?> cls, Type type, Annotation[] annotationArr, MediaType mediaType) {
        if (!isJsonType(mediaType) || _untouchables.contains(new ClassKey(cls))) {
            return false;
        }
        for (Class isAssignableFrom : _unwritableClasses) {
            if (isAssignableFrom.isAssignableFrom(cls)) {
                return false;
            }
        }
        return !_containedIn(cls, this._cfgCustomUntouchables) ? !this._cfgCheckCanSerialize || locateMapper(cls, mediaType).canSerialize(cls) : false;
    }

    public ObjectMapper locateMapper(Class<?> cls, MediaType mediaType) {
        ObjectMapper configuredMapper = this._mapperConfig.getConfiguredMapper();
        if (configuredMapper != null) {
            return configuredMapper;
        }
        if (this._providers != null) {
            ContextResolver contextResolver = this._providers.getContextResolver(ObjectMapper.class, mediaType);
            if (contextResolver == null) {
                contextResolver = this._providers.getContextResolver(ObjectMapper.class, null);
            }
            if (contextResolver != null) {
                configuredMapper = (ObjectMapper) contextResolver.getContext(cls);
            }
        }
        return configuredMapper == null ? this._mapperConfig.getDefaultMapper() : configuredMapper;
    }

    public Object readFrom(Class<Object> cls, Type type, Annotation[] annotationArr, MediaType mediaType, MultivaluedMap<String, String> multivaluedMap, InputStream inputStream) {
        ObjectMapper locateMapper = locateMapper(cls, mediaType);
        JsonParser createJsonParser = locateMapper.getJsonFactory().createJsonParser(inputStream);
        createJsonParser.disable(JsonParser.Feature.AUTO_CLOSE_SOURCE);
        return locateMapper.readValue(createJsonParser, locateMapper.constructType(type));
    }

    public void setAnnotationsToUse(Annotations[] annotationsArr) {
        this._mapperConfig.setAnnotationsToUse(annotationsArr);
    }

    public void setJSONPFunctionName(String str) {
        this._jsonpFunctionName = str;
    }

    public void setMapper(ObjectMapper objectMapper) {
        this._mapperConfig.setMapper(objectMapper);
    }

    public Version version() {
        return VersionUtil.versionFor(getClass());
    }

    public void writeTo(Object obj, Class<?> cls, Type type, Annotation[] annotationArr, MediaType mediaType, MultivaluedMap<String, Object> multivaluedMap, OutputStream outputStream) {
        JavaType javaType = null;
        ObjectMapper locateMapper = locateMapper(cls, mediaType);
        JsonGenerator createJsonGenerator = locateMapper.getJsonFactory().createJsonGenerator(outputStream, findEncoding(mediaType, multivaluedMap));
        createJsonGenerator.disable(Feature.AUTO_CLOSE_TARGET);
        if (locateMapper.getSerializationConfig().isEnabled(SerializationConfig.Feature.INDENT_OUTPUT)) {
            createJsonGenerator.useDefaultPrettyPrinter();
        }
        if (!(type == null || obj == null || type.getClass() == Class.class)) {
            JavaType constructType = locateMapper.getTypeFactory().constructType(type);
            if (constructType.getRawClass() != Object.class) {
                javaType = constructType;
            }
        }
        if (this._jsonpFunctionName != null) {
            locateMapper.writeValue(createJsonGenerator, new JSONPObject(this._jsonpFunctionName, obj, javaType));
        } else if (javaType != null) {
            locateMapper.typedWriter(javaType).writeValue(createJsonGenerator, obj);
        } else {
            locateMapper.writeValue(createJsonGenerator, obj);
        }
    }
}
