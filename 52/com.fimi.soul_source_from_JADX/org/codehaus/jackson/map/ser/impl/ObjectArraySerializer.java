package org.codehaus.jackson.map.ser.impl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ResolvableSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.TypeSerializer;
import org.codehaus.jackson.map.annotate.JacksonStdImpl;
import org.codehaus.jackson.map.ser.ArraySerializers.AsArraySerializer;
import org.codehaus.jackson.map.ser.ContainerSerializerBase;
import org.codehaus.jackson.map.ser.impl.PropertySerializerMap.SerializerAndMapResult;
import org.codehaus.jackson.map.type.ArrayType;
import org.codehaus.jackson.schema.JsonSchema;
import org.codehaus.jackson.schema.SchemaAware;
import org.codehaus.jackson.type.JavaType;

@JacksonStdImpl
public class ObjectArraySerializer extends AsArraySerializer<Object[]> implements ResolvableSerializer {
    protected PropertySerializerMap _dynamicSerializers;
    protected JsonSerializer<Object> _elementSerializer;
    protected final JavaType _elementType;
    protected final boolean _staticTyping;

    @Deprecated
    public ObjectArraySerializer(JavaType javaType, boolean z, TypeSerializer typeSerializer, BeanProperty beanProperty) {
        this(javaType, z, typeSerializer, beanProperty, null);
    }

    public ObjectArraySerializer(JavaType javaType, boolean z, TypeSerializer typeSerializer, BeanProperty beanProperty, JsonSerializer<Object> jsonSerializer) {
        super(Object[].class, typeSerializer, beanProperty);
        this._elementType = javaType;
        this._staticTyping = z;
        this._dynamicSerializers = PropertySerializerMap.emptyMap();
        this._elementSerializer = jsonSerializer;
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

    public ContainerSerializerBase<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
        return new ObjectArraySerializer(this._elementType, this._staticTyping, typeSerializer, this._property, this._elementSerializer);
    }

    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
        JsonNode createSchemaNode = createSchemaNode("array", true);
        if (type != null) {
            JavaType constructType = serializerProvider.constructType(type);
            if (constructType.isArrayType()) {
                Class rawClass = ((ArrayType) constructType).getContentType().getRawClass();
                if (rawClass == Object.class) {
                    createSchemaNode.put("items", JsonSchema.getDefaultSchemaNode());
                } else {
                    JsonSerializer findValueSerializer = serializerProvider.findValueSerializer(rawClass, this._property);
                    createSchemaNode.put("items", findValueSerializer instanceof SchemaAware ? ((SchemaAware) findValueSerializer).getSchema(serializerProvider, null) : JsonSchema.getDefaultSchemaNode());
                }
            }
        }
        return createSchemaNode;
    }

    public void resolve(SerializerProvider serializerProvider) {
        if (this._staticTyping && this._elementSerializer == null) {
            this._elementSerializer = serializerProvider.findValueSerializer(this._elementType, this._property);
        }
    }

    public void serializeContents(Object[] objArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        int length = objArr.length;
        if (length != 0) {
            if (this._elementSerializer != null) {
                serializeContentsUsing(objArr, jsonGenerator, serializerProvider, this._elementSerializer);
            } else if (this._valueTypeSerializer != null) {
                serializeTypedContents(objArr, jsonGenerator, serializerProvider);
            } else {
                int i = 0;
                Object obj = null;
                try {
                    PropertySerializerMap propertySerializerMap = this._dynamicSerializers;
                    while (i < length) {
                        obj = objArr[i];
                        if (obj == null) {
                            serializerProvider.defaultSerializeNull(jsonGenerator);
                        } else {
                            Class cls = obj.getClass();
                            JsonSerializer serializerFor = propertySerializerMap.serializerFor(cls);
                            if (serializerFor == null) {
                                serializerFor = this._elementType.hasGenericTypes() ? _findAndAddDynamic(propertySerializerMap, this._elementType.forcedNarrowBy(cls), serializerProvider) : _findAndAddDynamic(propertySerializerMap, cls, serializerProvider);
                            }
                            serializerFor.serialize(obj, jsonGenerator, serializerProvider);
                        }
                        i++;
                    }
                } catch (IOException e) {
                    throw e;
                } catch (Exception e2) {
                    Throwable e3 = e2;
                    while ((e3 instanceof InvocationTargetException) && e3.getCause() != null) {
                        e3 = e3.getCause();
                    }
                    if (e3 instanceof Error) {
                        throw ((Error) e3);
                    }
                    throw JsonMappingException.wrapWithPath(e3, obj, i);
                }
            }
        }
    }

    public void serializeContentsUsing(Object[] objArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, JsonSerializer<Object> jsonSerializer) {
        int length = objArr.length;
        TypeSerializer typeSerializer = this._valueTypeSerializer;
        Object obj = null;
        int i = 0;
        while (i < length) {
            try {
                obj = objArr[i];
                if (obj == null) {
                    serializerProvider.defaultSerializeNull(jsonGenerator);
                } else if (typeSerializer == null) {
                    jsonSerializer.serialize(obj, jsonGenerator, serializerProvider);
                } else {
                    jsonSerializer.serializeWithType(obj, jsonGenerator, serializerProvider, typeSerializer);
                }
                i++;
            } catch (IOException e) {
                throw e;
            } catch (Exception e2) {
                Throwable e3 = e2;
                while ((e3 instanceof InvocationTargetException) && e3.getCause() != null) {
                    e3 = e3.getCause();
                }
                if (e3 instanceof Error) {
                    throw ((Error) e3);
                }
                throw JsonMappingException.wrapWithPath(e3, obj, i);
            }
        }
    }

    public void serializeTypedContents(Object[] objArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        int length = objArr.length;
        TypeSerializer typeSerializer = this._valueTypeSerializer;
        int i = 0;
        try {
            PropertySerializerMap propertySerializerMap = this._dynamicSerializers;
            while (i < length) {
                Object obj = objArr[i];
                if (obj == null) {
                    serializerProvider.defaultSerializeNull(jsonGenerator);
                } else {
                    Class cls = obj.getClass();
                    JsonSerializer serializerFor = propertySerializerMap.serializerFor(cls);
                    if (serializerFor == null) {
                        serializerFor = _findAndAddDynamic(propertySerializerMap, cls, serializerProvider);
                    }
                    serializerFor.serializeWithType(obj, jsonGenerator, serializerProvider, typeSerializer);
                }
                i++;
            }
        } catch (IOException e) {
            throw e;
        } catch (Exception e2) {
            Throwable e3 = e2;
            while ((e3 instanceof InvocationTargetException) && e3.getCause() != null) {
                e3 = e3.getCause();
            }
            if (e3 instanceof Error) {
                throw ((Error) e3);
            }
            throw JsonMappingException.wrapWithPath(e3, null, 0);
        }
    }
}
