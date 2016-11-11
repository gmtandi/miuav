package org.codehaus.jackson.map.ser;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.RandomAccess;
import java.util.TimeZone;
import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.ContextualSerializer;
import org.codehaus.jackson.map.JsonSerializable;
import org.codehaus.jackson.map.JsonSerializableWithType;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.JsonSerializer.None;
import org.codehaus.jackson.map.MapperConfig;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.SerializationConfig.Feature;
import org.codehaus.jackson.map.SerializerFactory;
import org.codehaus.jackson.map.Serializers;
import org.codehaus.jackson.map.TypeSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize.Typing;
import org.codehaus.jackson.map.ext.OptionalHandlerFactory;
import org.codehaus.jackson.map.introspect.Annotated;
import org.codehaus.jackson.map.introspect.AnnotatedClass;
import org.codehaus.jackson.map.introspect.BasicBeanDescription;
import org.codehaus.jackson.map.jsontype.TypeResolverBuilder;
import org.codehaus.jackson.map.ser.ArraySerializers.BooleanArraySerializer;
import org.codehaus.jackson.map.ser.ArraySerializers.ByteArraySerializer;
import org.codehaus.jackson.map.ser.ArraySerializers.CharArraySerializer;
import org.codehaus.jackson.map.ser.ArraySerializers.DoubleArraySerializer;
import org.codehaus.jackson.map.ser.ArraySerializers.FloatArraySerializer;
import org.codehaus.jackson.map.ser.ArraySerializers.IntArraySerializer;
import org.codehaus.jackson.map.ser.ArraySerializers.LongArraySerializer;
import org.codehaus.jackson.map.ser.ArraySerializers.ShortArraySerializer;
import org.codehaus.jackson.map.ser.ArraySerializers.StringArraySerializer;
import org.codehaus.jackson.map.ser.StdSerializers.BooleanSerializer;
import org.codehaus.jackson.map.ser.StdSerializers.CalendarSerializer;
import org.codehaus.jackson.map.ser.StdSerializers.DoubleSerializer;
import org.codehaus.jackson.map.ser.StdSerializers.FloatSerializer;
import org.codehaus.jackson.map.ser.StdSerializers.IntLikeSerializer;
import org.codehaus.jackson.map.ser.StdSerializers.IntegerSerializer;
import org.codehaus.jackson.map.ser.StdSerializers.LongSerializer;
import org.codehaus.jackson.map.ser.StdSerializers.NumberSerializer;
import org.codehaus.jackson.map.ser.StdSerializers.SerializableSerializer;
import org.codehaus.jackson.map.ser.StdSerializers.SerializableWithTypeSerializer;
import org.codehaus.jackson.map.ser.StdSerializers.SqlDateSerializer;
import org.codehaus.jackson.map.ser.StdSerializers.SqlTimeSerializer;
import org.codehaus.jackson.map.ser.StdSerializers.StringSerializer;
import org.codehaus.jackson.map.ser.StdSerializers.TokenBufferSerializer;
import org.codehaus.jackson.map.ser.StdSerializers.UtilDateSerializer;
import org.codehaus.jackson.map.ser.impl.IndexedStringListSerializer;
import org.codehaus.jackson.map.ser.impl.InetAddressSerializer;
import org.codehaus.jackson.map.ser.impl.ObjectArraySerializer;
import org.codehaus.jackson.map.ser.impl.StringCollectionSerializer;
import org.codehaus.jackson.map.ser.impl.TimeZoneSerializer;
import org.codehaus.jackson.map.type.ArrayType;
import org.codehaus.jackson.map.type.CollectionLikeType;
import org.codehaus.jackson.map.type.CollectionType;
import org.codehaus.jackson.map.type.MapLikeType;
import org.codehaus.jackson.map.type.MapType;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.map.util.ClassUtil;
import org.codehaus.jackson.map.util.EnumValues;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.util.TokenBuffer;

public abstract class BasicSerializerFactory extends SerializerFactory {
    protected static final HashMap<String, JsonSerializer<?>> _arraySerializers;
    protected static final HashMap<String, JsonSerializer<?>> _concrete;
    protected static final HashMap<String, Class<? extends JsonSerializer<?>>> _concreteLazy;
    protected OptionalHandlerFactory optionalHandlers;

    static {
        _concrete = new HashMap();
        _concreteLazy = new HashMap();
        _concrete.put(String.class.getName(), new StringSerializer());
        ToStringSerializer toStringSerializer = ToStringSerializer.instance;
        _concrete.put(StringBuffer.class.getName(), toStringSerializer);
        _concrete.put(StringBuilder.class.getName(), toStringSerializer);
        _concrete.put(Character.class.getName(), toStringSerializer);
        _concrete.put(Character.TYPE.getName(), toStringSerializer);
        _concrete.put(Boolean.TYPE.getName(), new BooleanSerializer(true));
        _concrete.put(Boolean.class.getName(), new BooleanSerializer(false));
        IntegerSerializer integerSerializer = new IntegerSerializer();
        _concrete.put(Integer.class.getName(), integerSerializer);
        _concrete.put(Integer.TYPE.getName(), integerSerializer);
        _concrete.put(Long.class.getName(), LongSerializer.instance);
        _concrete.put(Long.TYPE.getName(), LongSerializer.instance);
        _concrete.put(Byte.class.getName(), IntLikeSerializer.instance);
        _concrete.put(Byte.TYPE.getName(), IntLikeSerializer.instance);
        _concrete.put(Short.class.getName(), IntLikeSerializer.instance);
        _concrete.put(Short.TYPE.getName(), IntLikeSerializer.instance);
        _concrete.put(Float.class.getName(), FloatSerializer.instance);
        _concrete.put(Float.TYPE.getName(), FloatSerializer.instance);
        _concrete.put(Double.class.getName(), DoubleSerializer.instance);
        _concrete.put(Double.TYPE.getName(), DoubleSerializer.instance);
        NumberSerializer numberSerializer = new NumberSerializer();
        _concrete.put(BigInteger.class.getName(), numberSerializer);
        _concrete.put(BigDecimal.class.getName(), numberSerializer);
        _concrete.put(Calendar.class.getName(), CalendarSerializer.instance);
        _concrete.put(Date.class.getName(), UtilDateSerializer.instance);
        _concrete.put(java.sql.Date.class.getName(), new SqlDateSerializer());
        _concrete.put(Time.class.getName(), new SqlTimeSerializer());
        _concrete.put(Timestamp.class.getName(), UtilDateSerializer.instance);
        for (Entry entry : new JdkSerializers().provide()) {
            Object value = entry.getValue();
            if (value instanceof JsonSerializer) {
                _concrete.put(((Class) entry.getKey()).getName(), (JsonSerializer) value);
            } else if (value instanceof Class) {
                _concreteLazy.put(((Class) entry.getKey()).getName(), (Class) value);
            } else {
                throw new IllegalStateException("Internal error: unrecognized value of type " + entry.getClass().getName());
            }
        }
        _concreteLazy.put(TokenBuffer.class.getName(), TokenBufferSerializer.class);
        _arraySerializers = new HashMap();
        _arraySerializers.put(boolean[].class.getName(), new BooleanArraySerializer());
        _arraySerializers.put(byte[].class.getName(), new ByteArraySerializer());
        _arraySerializers.put(char[].class.getName(), new CharArraySerializer());
        _arraySerializers.put(short[].class.getName(), new ShortArraySerializer());
        _arraySerializers.put(int[].class.getName(), new IntArraySerializer());
        _arraySerializers.put(long[].class.getName(), new LongArraySerializer());
        _arraySerializers.put(float[].class.getName(), new FloatArraySerializer());
        _arraySerializers.put(double[].class.getName(), new DoubleArraySerializer());
    }

    protected BasicSerializerFactory() {
        this.optionalHandlers = OptionalHandlerFactory.instance;
    }

    protected static JsonSerializer<Object> findContentSerializer(SerializationConfig serializationConfig, Annotated annotated, BeanProperty beanProperty) {
        AnnotationIntrospector annotationIntrospector = serializationConfig.getAnnotationIntrospector();
        Class findContentSerializer = annotationIntrospector.findContentSerializer(annotated);
        if ((findContentSerializer == null || findContentSerializer == None.class) && beanProperty != null) {
            findContentSerializer = annotationIntrospector.findContentSerializer(beanProperty.getMember());
        }
        return (findContentSerializer == null || findContentSerializer == None.class) ? null : serializationConfig.serializerInstance(annotated, findContentSerializer);
    }

    protected static JsonSerializer<Object> findKeySerializer(SerializationConfig serializationConfig, Annotated annotated, BeanProperty beanProperty) {
        AnnotationIntrospector annotationIntrospector = serializationConfig.getAnnotationIntrospector();
        Class findKeySerializer = annotationIntrospector.findKeySerializer(annotated);
        if ((findKeySerializer == null || findKeySerializer == None.class) && beanProperty != null) {
            findKeySerializer = annotationIntrospector.findKeySerializer(beanProperty.getMember());
        }
        return (findKeySerializer == null || findKeySerializer == None.class) ? null : serializationConfig.serializerInstance(annotated, findKeySerializer);
    }

    protected static <T extends JavaType> T modifySecondaryTypesByAnnotation(SerializationConfig serializationConfig, Annotated annotated, T t) {
        AnnotationIntrospector annotationIntrospector = serializationConfig.getAnnotationIntrospector();
        if (t.isContainerType()) {
            Class findSerializationKeyType = annotationIntrospector.findSerializationKeyType(annotated, t.getKeyType());
            if (findSerializationKeyType != null) {
                if (t instanceof MapType) {
                    try {
                        t = ((MapType) t).widenKey(findSerializationKeyType);
                    } catch (IllegalArgumentException e) {
                        throw new IllegalArgumentException("Failed to narrow key type " + t + " with key-type annotation (" + findSerializationKeyType.getName() + "): " + e.getMessage());
                    }
                }
                throw new IllegalArgumentException("Illegal key-type annotation: type " + t + " is not a Map type");
            }
            Class findSerializationContentType = annotationIntrospector.findSerializationContentType(annotated, t.getContentType());
            if (findSerializationContentType != null) {
                try {
                    t = t.widenContentsBy(findSerializationContentType);
                } catch (IllegalArgumentException e2) {
                    throw new IllegalArgumentException("Failed to narrow content type " + t + " with content-type annotation (" + findSerializationContentType.getName() + "): " + e2.getMessage());
                }
            }
        }
        return t;
    }

    protected JsonSerializer<?> buildArraySerializer(SerializationConfig serializationConfig, ArrayType arrayType, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, boolean z, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) {
        Class rawClass = arrayType.getRawClass();
        if (String[].class == rawClass) {
            return new StringArraySerializer(beanProperty);
        }
        JsonSerializer<?> jsonSerializer2 = (JsonSerializer) _arraySerializers.get(rawClass.getName());
        return jsonSerializer2 == null ? new ObjectArraySerializer(arrayType.getContentType(), z, typeSerializer, beanProperty, jsonSerializer) : jsonSerializer2;
    }

    protected JsonSerializer<?> buildCollectionLikeSerializer(SerializationConfig serializationConfig, CollectionLikeType collectionLikeType, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, boolean z, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) {
        for (Serializers findCollectionLikeSerializer : customSerializers()) {
            JsonSerializer<?> findCollectionLikeSerializer2 = findCollectionLikeSerializer.findCollectionLikeSerializer(serializationConfig, collectionLikeType, basicBeanDescription, beanProperty, typeSerializer, jsonSerializer);
            if (findCollectionLikeSerializer2 != null) {
                return findCollectionLikeSerializer2;
            }
        }
        return null;
    }

    protected JsonSerializer<?> buildCollectionSerializer(SerializationConfig serializationConfig, CollectionType collectionType, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, boolean z, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) {
        for (Serializers findCollectionSerializer : customSerializers()) {
            JsonSerializer<?> findCollectionSerializer2 = findCollectionSerializer.findCollectionSerializer(serializationConfig, collectionType, basicBeanDescription, beanProperty, typeSerializer, jsonSerializer);
            if (findCollectionSerializer2 != null) {
                return findCollectionSerializer2;
            }
        }
        Class rawClass = collectionType.getRawClass();
        if (EnumSet.class.isAssignableFrom(rawClass)) {
            return buildEnumSetSerializer(serializationConfig, collectionType, basicBeanDescription, beanProperty, z, typeSerializer, jsonSerializer);
        }
        Class rawClass2 = collectionType.getContentType().getRawClass();
        return isIndexedList(rawClass) ? rawClass2 == String.class ? new IndexedStringListSerializer(beanProperty) : ContainerSerializers.indexedListSerializer(collectionType.getContentType(), z, typeSerializer, beanProperty, jsonSerializer) : rawClass2 == String.class ? new StringCollectionSerializer(beanProperty) : ContainerSerializers.collectionSerializer(collectionType.getContentType(), z, typeSerializer, beanProperty, jsonSerializer);
    }

    public JsonSerializer<?> buildContainerSerializer(SerializationConfig serializationConfig, JavaType javaType, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, boolean z) {
        TypeSerializer createTypeSerializer = createTypeSerializer(serializationConfig, javaType.getContentType(), beanProperty);
        boolean usesStaticTyping = createTypeSerializer != null ? false : !z ? usesStaticTyping(serializationConfig, basicBeanDescription, createTypeSerializer, beanProperty) : z;
        JsonSerializer findContentSerializer = findContentSerializer(serializationConfig, basicBeanDescription.getClassInfo(), beanProperty);
        if (javaType.isMapLikeType()) {
            MapLikeType mapLikeType = (MapLikeType) javaType;
            JsonSerializer findKeySerializer = findKeySerializer(serializationConfig, basicBeanDescription.getClassInfo(), beanProperty);
            if (!mapLikeType.isTrueMapType()) {
                return buildMapLikeSerializer(serializationConfig, mapLikeType, basicBeanDescription, beanProperty, usesStaticTyping, findKeySerializer, createTypeSerializer, findContentSerializer);
            }
            return buildMapSerializer(serializationConfig, (MapType) mapLikeType, basicBeanDescription, beanProperty, usesStaticTyping, findKeySerializer, createTypeSerializer, findContentSerializer);
        } else if (javaType.isCollectionLikeType()) {
            CollectionLikeType collectionLikeType = (CollectionLikeType) javaType;
            if (!collectionLikeType.isTrueCollectionType()) {
                return buildCollectionLikeSerializer(serializationConfig, collectionLikeType, basicBeanDescription, beanProperty, usesStaticTyping, createTypeSerializer, findContentSerializer);
            }
            return buildCollectionSerializer(serializationConfig, (CollectionType) collectionLikeType, basicBeanDescription, beanProperty, usesStaticTyping, createTypeSerializer, findContentSerializer);
        } else if (!javaType.isArrayType()) {
            return null;
        } else {
            return buildArraySerializer(serializationConfig, (ArrayType) javaType, basicBeanDescription, beanProperty, usesStaticTyping, createTypeSerializer, findContentSerializer);
        }
    }

    protected JsonSerializer<?> buildEnumMapSerializer(SerializationConfig serializationConfig, JavaType javaType, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, boolean z, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) {
        JavaType keyType = javaType.getKeyType();
        EnumValues enumValues = null;
        if (keyType.isEnumType()) {
            enumValues = EnumValues.construct(keyType.getRawClass(), serializationConfig.getAnnotationIntrospector());
        }
        return new EnumMapSerializer(javaType.getContentType(), z, enumValues, typeSerializer, beanProperty, jsonSerializer);
    }

    protected JsonSerializer<?> buildEnumSetSerializer(SerializationConfig serializationConfig, JavaType javaType, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, boolean z, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) {
        JavaType contentType = javaType.getContentType();
        if (!contentType.isEnumType()) {
            contentType = null;
        }
        return ContainerSerializers.enumSetSerializer(contentType, beanProperty);
    }

    protected JsonSerializer<?> buildIterableSerializer(SerializationConfig serializationConfig, JavaType javaType, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, boolean z) {
        JavaType containedType = javaType.containedType(0);
        if (containedType == null) {
            containedType = TypeFactory.unknownType();
        }
        TypeSerializer createTypeSerializer = createTypeSerializer(serializationConfig, containedType, beanProperty);
        return ContainerSerializers.iterableSerializer(containedType, usesStaticTyping(serializationConfig, basicBeanDescription, createTypeSerializer, beanProperty), createTypeSerializer, beanProperty);
    }

    protected JsonSerializer<?> buildIteratorSerializer(SerializationConfig serializationConfig, JavaType javaType, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, boolean z) {
        JavaType containedType = javaType.containedType(0);
        if (containedType == null) {
            containedType = TypeFactory.unknownType();
        }
        TypeSerializer createTypeSerializer = createTypeSerializer(serializationConfig, containedType, beanProperty);
        return ContainerSerializers.iteratorSerializer(containedType, usesStaticTyping(serializationConfig, basicBeanDescription, createTypeSerializer, beanProperty), createTypeSerializer, beanProperty);
    }

    protected JsonSerializer<?> buildMapLikeSerializer(SerializationConfig serializationConfig, MapLikeType mapLikeType, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, boolean z, JsonSerializer<Object> jsonSerializer, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer2) {
        for (Serializers findMapLikeSerializer : customSerializers()) {
            JsonSerializer<?> findMapLikeSerializer2 = findMapLikeSerializer.findMapLikeSerializer(serializationConfig, mapLikeType, basicBeanDescription, beanProperty, jsonSerializer, typeSerializer, jsonSerializer2);
            if (findMapLikeSerializer2 != null) {
                return findMapLikeSerializer2;
            }
        }
        return null;
    }

    protected JsonSerializer<?> buildMapSerializer(SerializationConfig serializationConfig, MapType mapType, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, boolean z, JsonSerializer<Object> jsonSerializer, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer2) {
        for (Serializers findMapSerializer : customSerializers()) {
            JsonSerializer<?> findMapSerializer2 = findMapSerializer.findMapSerializer(serializationConfig, mapType, basicBeanDescription, beanProperty, jsonSerializer, typeSerializer, jsonSerializer2);
            if (findMapSerializer2 != null) {
                return findMapSerializer2;
            }
        }
        return EnumMap.class.isAssignableFrom(mapType.getRawClass()) ? buildEnumMapSerializer(serializationConfig, mapType, basicBeanDescription, beanProperty, z, typeSerializer, jsonSerializer2) : MapSerializer.construct(serializationConfig.getAnnotationIntrospector().findPropertiesToIgnore(basicBeanDescription.getClassInfo()), mapType, z, typeSerializer, beanProperty, jsonSerializer, jsonSerializer2);
    }

    public abstract JsonSerializer<Object> createSerializer(SerializationConfig serializationConfig, JavaType javaType, BeanProperty beanProperty);

    public TypeSerializer createTypeSerializer(SerializationConfig serializationConfig, JavaType javaType, BeanProperty beanProperty) {
        Collection collection;
        AnnotatedClass classInfo = ((BasicBeanDescription) serializationConfig.introspectClassAnnotations(javaType.getRawClass())).getClassInfo();
        AnnotationIntrospector annotationIntrospector = serializationConfig.getAnnotationIntrospector();
        TypeResolverBuilder findTypeResolver = annotationIntrospector.findTypeResolver(serializationConfig, classInfo, javaType);
        if (findTypeResolver == null) {
            findTypeResolver = serializationConfig.getDefaultTyper(javaType);
            collection = null;
        } else {
            collection = serializationConfig.getSubtypeResolver().collectAndResolveSubtypes(classInfo, (MapperConfig) serializationConfig, annotationIntrospector);
        }
        return findTypeResolver == null ? null : findTypeResolver.buildTypeSerializer(serializationConfig, javaType, collection, beanProperty);
    }

    protected abstract Iterable<Serializers> customSerializers();

    public final JsonSerializer<?> findSerializerByAddonType(SerializationConfig serializationConfig, JavaType javaType, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, boolean z) {
        Class rawClass = javaType.getRawClass();
        return Iterator.class.isAssignableFrom(rawClass) ? buildIteratorSerializer(serializationConfig, javaType, basicBeanDescription, beanProperty, z) : Iterable.class.isAssignableFrom(rawClass) ? buildIterableSerializer(serializationConfig, javaType, basicBeanDescription, beanProperty, z) : CharSequence.class.isAssignableFrom(rawClass) ? ToStringSerializer.instance : null;
    }

    public final JsonSerializer<?> findSerializerByLookup(JavaType javaType, SerializationConfig serializationConfig, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, boolean z) {
        String name = javaType.getRawClass().getName();
        JsonSerializer<?> jsonSerializer = (JsonSerializer) _concrete.get(name);
        if (jsonSerializer != null) {
            return jsonSerializer;
        }
        Class cls = (Class) _concreteLazy.get(name);
        if (cls == null) {
            return null;
        }
        try {
            return (JsonSerializer) cls.newInstance();
        } catch (Throwable e) {
            throw new IllegalStateException("Failed to instantiate standard serializer (of type " + cls.getName() + "): " + e.getMessage(), e);
        }
    }

    public final JsonSerializer<?> findSerializerByPrimaryType(JavaType javaType, SerializationConfig serializationConfig, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, boolean z) {
        Class rawClass = javaType.getRawClass();
        if (JsonSerializable.class.isAssignableFrom(rawClass)) {
            return JsonSerializableWithType.class.isAssignableFrom(rawClass) ? SerializableWithTypeSerializer.instance : SerializableSerializer.instance;
        } else {
            Annotated findJsonValueMethod = basicBeanDescription.findJsonValueMethod();
            if (findJsonValueMethod != null) {
                Object annotated = findJsonValueMethod.getAnnotated();
                if (serializationConfig.isEnabled(Feature.CAN_OVERRIDE_ACCESS_MODIFIERS)) {
                    ClassUtil.checkAndFixAccess(annotated);
                }
                return new JsonValueSerializer(annotated, findSerializerFromAnnotation(serializationConfig, findJsonValueMethod, beanProperty), beanProperty);
            } else if (InetAddress.class.isAssignableFrom(rawClass)) {
                return InetAddressSerializer.instance;
            } else {
                if (TimeZone.class.isAssignableFrom(rawClass)) {
                    return TimeZoneSerializer.instance;
                }
                JsonSerializer<?> findSerializer = this.optionalHandlers.findSerializer(serializationConfig, javaType);
                return findSerializer == null ? Number.class.isAssignableFrom(rawClass) ? NumberSerializer.instance : Enum.class.isAssignableFrom(rawClass) ? EnumSerializer.construct(rawClass, serializationConfig, basicBeanDescription) : Calendar.class.isAssignableFrom(rawClass) ? CalendarSerializer.instance : Date.class.isAssignableFrom(rawClass) ? UtilDateSerializer.instance : null : findSerializer;
            }
        }
    }

    protected JsonSerializer<Object> findSerializerFromAnnotation(SerializationConfig serializationConfig, Annotated annotated, BeanProperty beanProperty) {
        Object findSerializer = serializationConfig.getAnnotationIntrospector().findSerializer(annotated);
        if (findSerializer == null) {
            return null;
        }
        JsonSerializer<Object> jsonSerializer;
        if (findSerializer instanceof JsonSerializer) {
            jsonSerializer = (JsonSerializer) findSerializer;
            return jsonSerializer instanceof ContextualSerializer ? ((ContextualSerializer) jsonSerializer).createContextual(serializationConfig, beanProperty) : jsonSerializer;
        } else if (findSerializer instanceof Class) {
            Class cls = (Class) findSerializer;
            if (JsonSerializer.class.isAssignableFrom(cls)) {
                jsonSerializer = serializationConfig.serializerInstance(annotated, cls);
                return jsonSerializer instanceof ContextualSerializer ? ((ContextualSerializer) jsonSerializer).createContextual(serializationConfig, beanProperty) : jsonSerializer;
            } else {
                throw new IllegalStateException("AnnotationIntrospector returned Class " + cls.getName() + "; expected Class<JsonSerializer>");
            }
        } else {
            throw new IllegalStateException("AnnotationIntrospector returned value of type " + findSerializer.getClass().getName() + "; expected type JsonSerializer or Class<JsonSerializer> instead");
        }
    }

    public final JsonSerializer<?> getNullSerializer() {
        return NullSerializer.instance;
    }

    protected boolean isIndexedList(Class<?> cls) {
        return RandomAccess.class.isAssignableFrom(cls);
    }

    protected <T extends JavaType> T modifyTypeByAnnotation(SerializationConfig serializationConfig, Annotated annotated, T t) {
        JavaType widenBy;
        Class findSerializationType = serializationConfig.getAnnotationIntrospector().findSerializationType(annotated);
        if (findSerializationType != null) {
            try {
                widenBy = t.widenBy(findSerializationType);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Failed to widen type " + t + " with concrete-type annotation (value " + findSerializationType.getName() + "), method '" + annotated.getName() + "': " + e.getMessage());
            }
        }
        return modifySecondaryTypesByAnnotation(serializationConfig, annotated, widenBy);
    }

    protected boolean usesStaticTyping(SerializationConfig serializationConfig, BasicBeanDescription basicBeanDescription, TypeSerializer typeSerializer, BeanProperty beanProperty) {
        if (typeSerializer != null) {
            return false;
        }
        AnnotationIntrospector annotationIntrospector = serializationConfig.getAnnotationIntrospector();
        Typing findSerializationTyping = annotationIntrospector.findSerializationTyping(basicBeanDescription.getClassInfo());
        if (findSerializationTyping != null) {
            if (findSerializationTyping == Typing.STATIC) {
                return true;
            }
        } else if (serializationConfig.isEnabled(Feature.USE_STATIC_TYPING)) {
            return true;
        }
        if (beanProperty == null) {
            return false;
        }
        JavaType type = beanProperty.getType();
        return type.isContainerType() ? annotationIntrospector.findSerializationContentType(beanProperty.getMember(), beanProperty.getType()) != null ? true : (type instanceof MapType) && annotationIntrospector.findSerializationKeyType(beanProperty.getMember(), beanProperty.getType()) != null : false;
    }
}
