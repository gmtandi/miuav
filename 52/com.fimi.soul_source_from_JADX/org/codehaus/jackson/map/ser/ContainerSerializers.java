package org.codehaus.jackson.map.ser;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ResolvableSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.TypeSerializer;
import org.codehaus.jackson.map.annotate.JacksonStdImpl;
import org.codehaus.jackson.map.ser.impl.PropertySerializerMap;
import org.codehaus.jackson.map.ser.impl.PropertySerializerMap.SerializerAndMapResult;
import org.codehaus.jackson.schema.JsonSchema;
import org.codehaus.jackson.schema.SchemaAware;
import org.codehaus.jackson.type.JavaType;

public final class ContainerSerializers {

    public abstract class AsArraySerializer<T> extends ContainerSerializerBase<T> implements ResolvableSerializer {
        protected PropertySerializerMap _dynamicSerializers;
        protected JsonSerializer<Object> _elementSerializer;
        protected final JavaType _elementType;
        protected final BeanProperty _property;
        protected final boolean _staticTyping;
        protected final TypeSerializer _valueTypeSerializer;

        @Deprecated
        protected AsArraySerializer(Class<?> cls, JavaType javaType, boolean z, TypeSerializer typeSerializer, BeanProperty beanProperty) {
            this(cls, javaType, z, typeSerializer, beanProperty, null);
        }

        protected AsArraySerializer(Class<?> cls, JavaType javaType, boolean z, TypeSerializer typeSerializer, BeanProperty beanProperty, JsonSerializer<Object> jsonSerializer) {
            boolean z2 = false;
            super(cls, false);
            this._elementType = javaType;
            if (z || (javaType != null && javaType.isFinal())) {
                z2 = true;
            }
            this._staticTyping = z2;
            this._valueTypeSerializer = typeSerializer;
            this._property = beanProperty;
            this._elementSerializer = jsonSerializer;
            this._dynamicSerializers = PropertySerializerMap.emptyMap();
        }

        protected final JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap propertySerializerMap, Class<?> cls, SerializerProvider serializerProvider) {
            SerializerAndMapResult findAndAddSerializer = propertySerializerMap.findAndAddSerializer((Class) cls, serializerProvider, this._property);
            if (propertySerializerMap != findAndAddSerializer.map) {
                this._dynamicSerializers = findAndAddSerializer.map;
            }
            return findAndAddSerializer.serializer;
        }

        protected final JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap propertySerializerMap, JavaType javaType, SerializerProvider serializerProvider) {
            SerializerAndMapResult findAndAddSerializer = propertySerializerMap.findAndAddSerializer(javaType, serializerProvider, this._property);
            if (propertySerializerMap != findAndAddSerializer.map) {
                this._dynamicSerializers = findAndAddSerializer.map;
            }
            return findAndAddSerializer.serializer;
        }

        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            JavaType contentType;
            JsonNode createSchemaNode = createSchemaNode("array", true);
            if (type != null) {
                contentType = serializerProvider.constructType(type).getContentType();
                if (contentType == null && (type instanceof ParameterizedType)) {
                    Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
                    if (actualTypeArguments.length == 1) {
                        contentType = serializerProvider.constructType(actualTypeArguments[0]);
                    }
                }
            } else {
                contentType = null;
            }
            if (contentType == null && this._elementType != null) {
                contentType = this._elementType;
            }
            if (contentType != null) {
                JsonNode schema;
                if (contentType.getRawClass() != Object.class) {
                    JsonSerializer findValueSerializer = serializerProvider.findValueSerializer(contentType, this._property);
                    if (findValueSerializer instanceof SchemaAware) {
                        schema = ((SchemaAware) findValueSerializer).getSchema(serializerProvider, null);
                        if (schema == null) {
                            schema = JsonSchema.getDefaultSchemaNode();
                        }
                        createSchemaNode.put("items", schema);
                    }
                }
                schema = null;
                if (schema == null) {
                    schema = JsonSchema.getDefaultSchemaNode();
                }
                createSchemaNode.put("items", schema);
            }
            return createSchemaNode;
        }

        public void resolve(SerializerProvider serializerProvider) {
            if (this._staticTyping && this._elementType != null && this._elementSerializer == null) {
                this._elementSerializer = serializerProvider.findValueSerializer(this._elementType, this._property);
            }
        }

        public final void serialize(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            jsonGenerator.writeStartArray();
            serializeContents(t, jsonGenerator, serializerProvider);
            jsonGenerator.writeEndArray();
        }

        protected abstract void serializeContents(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider);

        public final void serializeWithType(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
            typeSerializer.writeTypePrefixForArray(t, jsonGenerator);
            serializeContents(t, jsonGenerator, serializerProvider);
            typeSerializer.writeTypeSuffixForArray(t, jsonGenerator);
        }
    }

    @JacksonStdImpl
    public class CollectionSerializer extends AsArraySerializer<Collection<?>> {
        @Deprecated
        public CollectionSerializer(JavaType javaType, boolean z, TypeSerializer typeSerializer, BeanProperty beanProperty) {
            this(javaType, z, typeSerializer, beanProperty, null);
        }

        public CollectionSerializer(JavaType javaType, boolean z, TypeSerializer typeSerializer, BeanProperty beanProperty, JsonSerializer<Object> jsonSerializer) {
            super(Collection.class, javaType, z, typeSerializer, beanProperty, jsonSerializer);
        }

        public ContainerSerializerBase<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
            return new CollectionSerializer(this._elementType, this._staticTyping, typeSerializer, this._property);
        }

        public void serializeContents(Collection<?> collection, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            if (this._elementSerializer != null) {
                serializeContentsUsing(collection, jsonGenerator, serializerProvider, this._elementSerializer);
                return;
            }
            Iterator it = collection.iterator();
            if (it.hasNext()) {
                PropertySerializerMap propertySerializerMap = this._dynamicSerializers;
                TypeSerializer typeSerializer = this._valueTypeSerializer;
                int i = 0;
                do {
                    Object next = it.next();
                    if (next == null) {
                        serializerProvider.defaultSerializeNull(jsonGenerator);
                    } else {
                        Class cls = next.getClass();
                        JsonSerializer serializerFor = propertySerializerMap.serializerFor(cls);
                        if (serializerFor == null) {
                            JsonSerializer _findAndAddDynamic;
                            if (this._elementType.hasGenericTypes()) {
                                _findAndAddDynamic = _findAndAddDynamic(propertySerializerMap, this._elementType.forcedNarrowBy(cls), serializerProvider);
                            } else {
                                try {
                                    _findAndAddDynamic = _findAndAddDynamic(propertySerializerMap, cls, serializerProvider);
                                } catch (Throwable e) {
                                    wrapAndThrow(serializerProvider, e, (Object) collection, i);
                                    return;
                                }
                            }
                            JsonSerializer jsonSerializer = _findAndAddDynamic;
                            propertySerializerMap = this._dynamicSerializers;
                            serializerFor = jsonSerializer;
                        }
                        if (typeSerializer == null) {
                            serializerFor.serialize(next, jsonGenerator, serializerProvider);
                        } else {
                            serializerFor.serializeWithType(next, jsonGenerator, serializerProvider, typeSerializer);
                        }
                    }
                    i++;
                } while (it.hasNext());
            }
        }

        public void serializeContentsUsing(Collection<?> collection, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, JsonSerializer<Object> jsonSerializer) {
            Iterator it = collection.iterator();
            if (it.hasNext()) {
                TypeSerializer typeSerializer = this._valueTypeSerializer;
                int i = 0;
                do {
                    Object next = it.next();
                    if (next == null) {
                        try {
                            serializerProvider.defaultSerializeNull(jsonGenerator);
                        } catch (Throwable e) {
                            wrapAndThrow(serializerProvider, e, (Object) collection, i);
                        }
                    } else if (typeSerializer == null) {
                        jsonSerializer.serialize(next, jsonGenerator, serializerProvider);
                    } else {
                        jsonSerializer.serializeWithType(next, jsonGenerator, serializerProvider, typeSerializer);
                    }
                    i++;
                } while (it.hasNext());
            }
        }
    }

    public class EnumSetSerializer extends AsArraySerializer<EnumSet<? extends Enum<?>>> {
        public EnumSetSerializer(JavaType javaType, BeanProperty beanProperty) {
            super(EnumSet.class, javaType, true, null, beanProperty);
        }

        public ContainerSerializerBase<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
            return this;
        }

        public void serializeContents(EnumSet<? extends Enum<?>> enumSet, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            JsonSerializer jsonSerializer = this._elementSerializer;
            Iterator it = enumSet.iterator();
            JsonSerializer jsonSerializer2 = jsonSerializer;
            while (it.hasNext()) {
                Enum enumR = (Enum) it.next();
                if (jsonSerializer2 == null) {
                    jsonSerializer2 = serializerProvider.findValueSerializer(enumR.getDeclaringClass(), this._property);
                }
                jsonSerializer2.serialize(enumR, jsonGenerator, serializerProvider);
            }
        }
    }

    @JacksonStdImpl
    public class IndexedListSerializer extends AsArraySerializer<List<?>> {
        public IndexedListSerializer(JavaType javaType, boolean z, TypeSerializer typeSerializer, BeanProperty beanProperty, JsonSerializer<Object> jsonSerializer) {
            super(List.class, javaType, z, typeSerializer, beanProperty, jsonSerializer);
        }

        public ContainerSerializerBase<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
            return new IndexedListSerializer(this._elementType, this._staticTyping, typeSerializer, this._property, this._elementSerializer);
        }

        public void serializeContents(List<?> list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            if (this._elementSerializer != null) {
                serializeContentsUsing(list, jsonGenerator, serializerProvider, this._elementSerializer);
            } else if (this._valueTypeSerializer != null) {
                serializeTypedContents(list, jsonGenerator, serializerProvider);
            } else {
                int size = list.size();
                if (size != 0) {
                    int i = 0;
                    try {
                        PropertySerializerMap propertySerializerMap = this._dynamicSerializers;
                        while (i < size) {
                            Object obj = list.get(i);
                            if (obj == null) {
                                serializerProvider.defaultSerializeNull(jsonGenerator);
                            } else {
                                Class cls = obj.getClass();
                                JsonSerializer serializerFor = propertySerializerMap.serializerFor(cls);
                                if (serializerFor == null) {
                                    JsonSerializer _findAndAddDynamic = this._elementType.hasGenericTypes() ? _findAndAddDynamic(propertySerializerMap, this._elementType.forcedNarrowBy(cls), serializerProvider) : _findAndAddDynamic(propertySerializerMap, cls, serializerProvider);
                                    propertySerializerMap = this._dynamicSerializers;
                                    serializerFor = _findAndAddDynamic;
                                }
                                serializerFor.serialize(obj, jsonGenerator, serializerProvider);
                            }
                            i++;
                        }
                    } catch (Throwable e) {
                        wrapAndThrow(serializerProvider, e, (Object) list, i);
                    }
                }
            }
        }

        public void serializeContentsUsing(List<?> list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, JsonSerializer<Object> jsonSerializer) {
            int size = list.size();
            if (size != 0) {
                TypeSerializer typeSerializer = this._valueTypeSerializer;
                for (int i = 0; i < size; i++) {
                    Object obj = list.get(i);
                    if (obj == null) {
                        try {
                            serializerProvider.defaultSerializeNull(jsonGenerator);
                        } catch (Throwable e) {
                            wrapAndThrow(serializerProvider, e, (Object) list, i);
                        }
                    } else if (typeSerializer == null) {
                        jsonSerializer.serialize(obj, jsonGenerator, serializerProvider);
                    } else {
                        jsonSerializer.serializeWithType(obj, jsonGenerator, serializerProvider, typeSerializer);
                    }
                }
            }
        }

        public void serializeTypedContents(List<?> list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            int size = list.size();
            if (size != 0) {
                int i = 0;
                try {
                    TypeSerializer typeSerializer = this._valueTypeSerializer;
                    PropertySerializerMap propertySerializerMap = this._dynamicSerializers;
                    while (i < size) {
                        Object obj = list.get(i);
                        if (obj == null) {
                            serializerProvider.defaultSerializeNull(jsonGenerator);
                        } else {
                            Class cls = obj.getClass();
                            JsonSerializer serializerFor = propertySerializerMap.serializerFor(cls);
                            if (serializerFor == null) {
                                JsonSerializer _findAndAddDynamic = this._elementType.hasGenericTypes() ? _findAndAddDynamic(propertySerializerMap, this._elementType.forcedNarrowBy(cls), serializerProvider) : _findAndAddDynamic(propertySerializerMap, cls, serializerProvider);
                                propertySerializerMap = this._dynamicSerializers;
                                serializerFor = _findAndAddDynamic;
                            }
                            serializerFor.serializeWithType(obj, jsonGenerator, serializerProvider, typeSerializer);
                        }
                        i++;
                    }
                } catch (Throwable e) {
                    wrapAndThrow(serializerProvider, e, (Object) list, i);
                }
            }
        }
    }

    @JacksonStdImpl
    public class IterableSerializer extends AsArraySerializer<Iterable<?>> {
        public IterableSerializer(JavaType javaType, boolean z, TypeSerializer typeSerializer, BeanProperty beanProperty) {
            super(Iterable.class, javaType, z, typeSerializer, beanProperty);
        }

        public ContainerSerializerBase<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
            return new IterableSerializer(this._elementType, this._staticTyping, typeSerializer, this._property);
        }

        public void serializeContents(Iterable<?> iterable, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            Class cls = null;
            Iterator it = iterable.iterator();
            if (it.hasNext()) {
                TypeSerializer typeSerializer = this._valueTypeSerializer;
                JsonSerializer jsonSerializer = null;
                do {
                    Object next = it.next();
                    if (next == null) {
                        serializerProvider.defaultSerializeNull(jsonGenerator);
                    } else {
                        JsonSerializer jsonSerializer2;
                        Class cls2 = next.getClass();
                        if (cls2 == cls) {
                            jsonSerializer2 = jsonSerializer;
                        } else {
                            jsonSerializer = serializerProvider.findValueSerializer(cls2, this._property);
                            cls = cls2;
                            jsonSerializer2 = jsonSerializer;
                        }
                        if (typeSerializer == null) {
                            jsonSerializer2.serialize(next, jsonGenerator, serializerProvider);
                        } else {
                            jsonSerializer2.serializeWithType(next, jsonGenerator, serializerProvider, typeSerializer);
                        }
                    }
                } while (it.hasNext());
            }
        }
    }

    @JacksonStdImpl
    public class IteratorSerializer extends AsArraySerializer<Iterator<?>> {
        public IteratorSerializer(JavaType javaType, boolean z, TypeSerializer typeSerializer, BeanProperty beanProperty) {
            super(Iterator.class, javaType, z, typeSerializer, beanProperty);
        }

        public ContainerSerializerBase<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
            return new IteratorSerializer(this._elementType, this._staticTyping, typeSerializer, this._property);
        }

        public void serializeContents(Iterator<?> it, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            Class cls = null;
            if (it.hasNext()) {
                TypeSerializer typeSerializer = this._valueTypeSerializer;
                JsonSerializer jsonSerializer = null;
                do {
                    Object next = it.next();
                    if (next == null) {
                        serializerProvider.defaultSerializeNull(jsonGenerator);
                    } else {
                        JsonSerializer jsonSerializer2;
                        Class cls2 = next.getClass();
                        if (cls2 == cls) {
                            jsonSerializer2 = jsonSerializer;
                        } else {
                            jsonSerializer = serializerProvider.findValueSerializer(cls2, this._property);
                            cls = cls2;
                            jsonSerializer2 = jsonSerializer;
                        }
                        if (typeSerializer == null) {
                            jsonSerializer2.serialize(next, jsonGenerator, serializerProvider);
                        } else {
                            jsonSerializer2.serializeWithType(next, jsonGenerator, serializerProvider, typeSerializer);
                        }
                    }
                } while (it.hasNext());
            }
        }
    }

    private ContainerSerializers() {
    }

    public static ContainerSerializerBase<?> collectionSerializer(JavaType javaType, boolean z, TypeSerializer typeSerializer, BeanProperty beanProperty, JsonSerializer<Object> jsonSerializer) {
        return new CollectionSerializer(javaType, z, typeSerializer, beanProperty, jsonSerializer);
    }

    public static JsonSerializer<?> enumSetSerializer(JavaType javaType, BeanProperty beanProperty) {
        return new EnumSetSerializer(javaType, beanProperty);
    }

    public static ContainerSerializerBase<?> indexedListSerializer(JavaType javaType, boolean z, TypeSerializer typeSerializer, BeanProperty beanProperty, JsonSerializer<Object> jsonSerializer) {
        return new IndexedListSerializer(javaType, z, typeSerializer, beanProperty, jsonSerializer);
    }

    public static ContainerSerializerBase<?> iterableSerializer(JavaType javaType, boolean z, TypeSerializer typeSerializer, BeanProperty beanProperty) {
        return new IterableSerializer(javaType, z, typeSerializer, beanProperty);
    }

    public static ContainerSerializerBase<?> iteratorSerializer(JavaType javaType, boolean z, TypeSerializer typeSerializer, BeanProperty beanProperty) {
        return new IteratorSerializer(javaType, z, typeSerializer, beanProperty);
    }
}
