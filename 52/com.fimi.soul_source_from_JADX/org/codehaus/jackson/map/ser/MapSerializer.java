package org.codehaus.jackson.map.ser;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ResolvableSerializer;
import org.codehaus.jackson.map.SerializationConfig.Feature;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.TypeSerializer;
import org.codehaus.jackson.map.annotate.JacksonStdImpl;
import org.codehaus.jackson.map.ser.impl.PropertySerializerMap;
import org.codehaus.jackson.map.ser.impl.PropertySerializerMap.SerializerAndMapResult;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.type.JavaType;
import org.p122a.p123a.C2915a;

@JacksonStdImpl
public class MapSerializer extends ContainerSerializerBase<Map<?, ?>> implements ResolvableSerializer {
    protected static final JavaType UNSPECIFIED_TYPE;
    protected PropertySerializerMap _dynamicValueSerializers;
    protected final HashSet<String> _ignoredEntries;
    protected JsonSerializer<Object> _keySerializer;
    protected final JavaType _keyType;
    protected final BeanProperty _property;
    protected JsonSerializer<Object> _valueSerializer;
    protected final JavaType _valueType;
    protected final boolean _valueTypeIsStatic;
    protected final TypeSerializer _valueTypeSerializer;

    static {
        UNSPECIFIED_TYPE = TypeFactory.unknownType();
    }

    protected MapSerializer() {
        this((HashSet) null, null, null, false, null, null, null, null);
    }

    @Deprecated
    protected MapSerializer(HashSet<String> hashSet, JavaType javaType, JavaType javaType2, boolean z, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer, BeanProperty beanProperty) {
        this(hashSet, javaType, javaType2, z, typeSerializer, jsonSerializer, null, beanProperty);
    }

    protected MapSerializer(HashSet<String> hashSet, JavaType javaType, JavaType javaType2, boolean z, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer, JsonSerializer<Object> jsonSerializer2, BeanProperty beanProperty) {
        super(Map.class, false);
        this._property = beanProperty;
        this._ignoredEntries = hashSet;
        this._keyType = javaType;
        this._valueType = javaType2;
        this._valueTypeIsStatic = z;
        this._valueTypeSerializer = typeSerializer;
        this._keySerializer = jsonSerializer;
        this._valueSerializer = jsonSerializer2;
        this._dynamicValueSerializers = PropertySerializerMap.emptyMap();
    }

    @Deprecated
    protected MapSerializer(HashSet<String> hashSet, JavaType javaType, boolean z, TypeSerializer typeSerializer) {
        this(hashSet, UNSPECIFIED_TYPE, javaType, z, typeSerializer, null, null, null);
    }

    @Deprecated
    public static MapSerializer construct(String[] strArr, JavaType javaType, boolean z, TypeSerializer typeSerializer, BeanProperty beanProperty) {
        return construct(strArr, javaType, z, typeSerializer, beanProperty, null, null);
    }

    public static MapSerializer construct(String[] strArr, JavaType javaType, boolean z, TypeSerializer typeSerializer, BeanProperty beanProperty, JsonSerializer<Object> jsonSerializer, JsonSerializer<Object> jsonSerializer2) {
        JavaType javaType2;
        JavaType javaType3;
        boolean z2;
        HashSet toSet = toSet(strArr);
        if (javaType == null) {
            javaType2 = UNSPECIFIED_TYPE;
            javaType3 = javaType2;
        } else {
            javaType3 = javaType.getKeyType();
            javaType2 = javaType.getContentType();
        }
        if (z) {
            z2 = z;
        } else {
            boolean z3 = javaType2 != null && javaType2.isFinal();
            z2 = z3;
        }
        return new MapSerializer(toSet, javaType3, javaType2, z2, typeSerializer, jsonSerializer, jsonSerializer2, beanProperty);
    }

    private static HashSet<String> toSet(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return null;
        }
        HashSet<String> hashSet = new HashSet(strArr.length);
        for (Object add : strArr) {
            hashSet.add(add);
        }
        return hashSet;
    }

    protected final JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap propertySerializerMap, Class<?> cls, SerializerProvider serializerProvider) {
        SerializerAndMapResult findAndAddSerializer = propertySerializerMap.findAndAddSerializer((Class) cls, serializerProvider, this._property);
        if (propertySerializerMap != findAndAddSerializer.map) {
            this._dynamicValueSerializers = findAndAddSerializer.map;
        }
        return findAndAddSerializer.serializer;
    }

    protected final JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap propertySerializerMap, JavaType javaType, SerializerProvider serializerProvider) {
        SerializerAndMapResult findAndAddSerializer = propertySerializerMap.findAndAddSerializer(javaType, serializerProvider, this._property);
        if (propertySerializerMap != findAndAddSerializer.map) {
            this._dynamicValueSerializers = findAndAddSerializer.map;
        }
        return findAndAddSerializer.serializer;
    }

    public ContainerSerializerBase<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
        ContainerSerializerBase mapSerializer = new MapSerializer(this._ignoredEntries, this._keyType, this._valueType, this._valueTypeIsStatic, typeSerializer, this._keySerializer, this._valueSerializer, this._property);
        if (this._valueSerializer != null) {
            mapSerializer._valueSerializer = this._valueSerializer;
        }
        return mapSerializer;
    }

    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
        return createSchemaNode("object", true);
    }

    public void resolve(SerializerProvider serializerProvider) {
        if (this._valueTypeIsStatic && this._valueSerializer == null) {
            this._valueSerializer = serializerProvider.findValueSerializer(this._valueType, this._property);
        }
        if (this._keySerializer == null) {
            this._keySerializer = serializerProvider.findKeySerializer(this._keyType, this._property);
        }
    }

    public void serialize(Map<?, ?> map, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeStartObject();
        if (!map.isEmpty()) {
            if (this._valueSerializer != null) {
                serializeFieldsUsing(map, jsonGenerator, serializerProvider, this._valueSerializer);
            } else {
                serializeFields(map, jsonGenerator, serializerProvider);
            }
        }
        jsonGenerator.writeEndObject();
    }

    protected void serializeFields(Map<?, ?> map, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        if (this._valueTypeSerializer != null) {
            serializeTypedFields(map, jsonGenerator, serializerProvider);
            return;
        }
        JsonSerializer jsonSerializer = this._keySerializer;
        HashSet hashSet = this._ignoredEntries;
        Object obj = !serializerProvider.isEnabled(Feature.WRITE_NULL_MAP_VALUES) ? 1 : null;
        PropertySerializerMap propertySerializerMap = this._dynamicValueSerializers;
        PropertySerializerMap propertySerializerMap2 = propertySerializerMap;
        for (Entry entry : map.entrySet()) {
            Object value = entry.getValue();
            Object key = entry.getKey();
            if (key == null) {
                serializerProvider.getNullKeySerializer().serialize(null, jsonGenerator, serializerProvider);
            } else if ((obj == null || value != null) && (hashSet == null || !hashSet.contains(key))) {
                jsonSerializer.serialize(key, jsonGenerator, serializerProvider);
            }
            if (value == null) {
                serializerProvider.defaultSerializeNull(jsonGenerator);
                propertySerializerMap = propertySerializerMap2;
            } else {
                JsonSerializer jsonSerializer2;
                Class cls = value.getClass();
                JsonSerializer serializerFor = propertySerializerMap2.serializerFor(cls);
                JsonSerializer _findAndAddDynamic;
                if (serializerFor == null) {
                    _findAndAddDynamic = this._valueType.hasGenericTypes() ? _findAndAddDynamic(propertySerializerMap2, this._valueType.forcedNarrowBy(cls), serializerProvider) : _findAndAddDynamic(propertySerializerMap2, cls, serializerProvider);
                    propertySerializerMap = this._dynamicValueSerializers;
                    jsonSerializer2 = _findAndAddDynamic;
                } else {
                    _findAndAddDynamic = serializerFor;
                    propertySerializerMap = propertySerializerMap2;
                    jsonSerializer2 = _findAndAddDynamic;
                }
                try {
                    jsonSerializer2.serialize(value, jsonGenerator, serializerProvider);
                } catch (Throwable e) {
                    wrapAndThrow(serializerProvider, e, (Object) map, C2915a.f14760f + key);
                }
            }
            propertySerializerMap2 = propertySerializerMap;
        }
    }

    protected void serializeFieldsUsing(Map<?, ?> map, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, JsonSerializer<Object> jsonSerializer) {
        JsonSerializer jsonSerializer2 = this._keySerializer;
        HashSet hashSet = this._ignoredEntries;
        TypeSerializer typeSerializer = this._valueTypeSerializer;
        Object obj = !serializerProvider.isEnabled(Feature.WRITE_NULL_MAP_VALUES) ? 1 : null;
        for (Entry entry : map.entrySet()) {
            Object value = entry.getValue();
            Object key = entry.getKey();
            if (key == null) {
                serializerProvider.getNullKeySerializer().serialize(null, jsonGenerator, serializerProvider);
            } else if ((obj == null || value != null) && (hashSet == null || !hashSet.contains(key))) {
                jsonSerializer2.serialize(key, jsonGenerator, serializerProvider);
            }
            if (value == null) {
                serializerProvider.defaultSerializeNull(jsonGenerator);
            } else if (typeSerializer == null) {
                try {
                    jsonSerializer.serialize(value, jsonGenerator, serializerProvider);
                } catch (Throwable e) {
                    wrapAndThrow(serializerProvider, e, (Object) map, C2915a.f14760f + key);
                }
            } else {
                jsonSerializer.serializeWithType(value, jsonGenerator, serializerProvider, typeSerializer);
            }
        }
    }

    protected void serializeTypedFields(Map<?, ?> map, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        JsonSerializer jsonSerializer = this._keySerializer;
        HashSet hashSet = this._ignoredEntries;
        Object obj = !serializerProvider.isEnabled(Feature.WRITE_NULL_MAP_VALUES) ? 1 : null;
        Object obj2 = null;
        JsonSerializer jsonSerializer2 = null;
        for (Entry entry : map.entrySet()) {
            Class cls;
            JsonSerializer jsonSerializer3;
            Object value = entry.getValue();
            Object key = entry.getKey();
            if (key == null) {
                serializerProvider.getNullKeySerializer().serialize(null, jsonGenerator, serializerProvider);
            } else if ((obj == null || value != null) && (hashSet == null || !hashSet.contains(key))) {
                jsonSerializer.serialize(key, jsonGenerator, serializerProvider);
            }
            if (value == null) {
                serializerProvider.defaultSerializeNull(jsonGenerator);
                cls = obj2;
                jsonSerializer3 = jsonSerializer2;
            } else {
                cls = value.getClass();
                if (cls == obj2) {
                    cls = obj2;
                    jsonSerializer3 = jsonSerializer2;
                } else {
                    jsonSerializer2 = serializerProvider.findValueSerializer(cls, this._property);
                    jsonSerializer3 = jsonSerializer2;
                }
                try {
                    jsonSerializer2.serializeWithType(value, jsonGenerator, serializerProvider, this._valueTypeSerializer);
                } catch (Throwable e) {
                    wrapAndThrow(serializerProvider, e, (Object) map, C2915a.f14760f + key);
                }
            }
            jsonSerializer2 = jsonSerializer3;
            obj2 = cls;
        }
    }

    public void serializeWithType(Map<?, ?> map, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
        typeSerializer.writeTypePrefixForObject(map, jsonGenerator);
        if (!map.isEmpty()) {
            if (this._valueSerializer != null) {
                serializeFieldsUsing(map, jsonGenerator, serializerProvider, this._valueSerializer);
            } else {
                serializeFields(map, jsonGenerator, serializerProvider);
            }
        }
        typeSerializer.writeTypeSuffixForObject(map, jsonGenerator);
    }
}
