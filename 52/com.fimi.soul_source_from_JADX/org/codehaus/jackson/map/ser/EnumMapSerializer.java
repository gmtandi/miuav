package org.codehaus.jackson.map.ser;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.EnumMap;
import java.util.Map.Entry;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ResolvableSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.TypeSerializer;
import org.codehaus.jackson.map.annotate.JacksonStdImpl;
import org.codehaus.jackson.map.util.EnumValues;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.schema.JsonSchema;
import org.codehaus.jackson.schema.SchemaAware;
import org.codehaus.jackson.type.JavaType;

@JacksonStdImpl
public class EnumMapSerializer extends ContainerSerializerBase<EnumMap<? extends Enum<?>, ?>> implements ResolvableSerializer {
    protected final EnumValues _keyEnums;
    protected final BeanProperty _property;
    protected final boolean _staticTyping;
    protected JsonSerializer<Object> _valueSerializer;
    protected final JavaType _valueType;
    protected final TypeSerializer _valueTypeSerializer;

    @Deprecated
    public EnumMapSerializer(JavaType javaType, boolean z, EnumValues enumValues, TypeSerializer typeSerializer, BeanProperty beanProperty) {
        this(javaType, z, enumValues, typeSerializer, beanProperty, null);
    }

    public EnumMapSerializer(JavaType javaType, boolean z, EnumValues enumValues, TypeSerializer typeSerializer, BeanProperty beanProperty, JsonSerializer<Object> jsonSerializer) {
        boolean z2 = false;
        super(EnumMap.class, false);
        if (z || (javaType != null && javaType.isFinal())) {
            z2 = true;
        }
        this._staticTyping = z2;
        this._valueType = javaType;
        this._keyEnums = enumValues;
        this._valueTypeSerializer = typeSerializer;
        this._property = beanProperty;
        this._valueSerializer = jsonSerializer;
    }

    public ContainerSerializerBase<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
        return new EnumMapSerializer(this._valueType, this._staticTyping, this._keyEnums, typeSerializer, this._property);
    }

    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
        JsonNode createSchemaNode = createSchemaNode("object", true);
        if (type instanceof ParameterizedType) {
            Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
            if (actualTypeArguments.length == 2) {
                JavaType constructType = serializerProvider.constructType(actualTypeArguments[0]);
                JavaType constructType2 = serializerProvider.constructType(actualTypeArguments[1]);
                JsonNode objectNode = JsonNodeFactory.instance.objectNode();
                for (Enum enumR : (Enum[]) constructType.getRawClass().getEnumConstants()) {
                    JsonSerializer findValueSerializer = serializerProvider.findValueSerializer(constructType2.getRawClass(), this._property);
                    objectNode.put(serializerProvider.getConfig().getAnnotationIntrospector().findEnumValue(enumR), findValueSerializer instanceof SchemaAware ? ((SchemaAware) findValueSerializer).getSchema(serializerProvider, null) : JsonSchema.getDefaultSchemaNode());
                }
                createSchemaNode.put("properties", objectNode);
            }
        }
        return createSchemaNode;
    }

    public void resolve(SerializerProvider serializerProvider) {
        if (this._staticTyping && this._valueSerializer == null) {
            this._valueSerializer = serializerProvider.findValueSerializer(this._valueType, this._property);
        }
    }

    public void serialize(EnumMap<? extends Enum<?>, ?> enumMap, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeStartObject();
        if (!enumMap.isEmpty()) {
            serializeContents(enumMap, jsonGenerator, serializerProvider);
        }
        jsonGenerator.writeEndObject();
    }

    protected void serializeContents(EnumMap<? extends Enum<?>, ?> enumMap, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        JsonSerializer jsonSerializer;
        Class cls = null;
        if (this._valueSerializer != null) {
            serializeContentsUsing(enumMap, jsonGenerator, serializerProvider, this._valueSerializer);
            return;
        }
        EnumValues enumValues = this._keyEnums;
        EnumValues enumValues2 = enumValues;
        JsonSerializer jsonSerializer2 = null;
        for (Entry entry : enumMap.entrySet()) {
            Class cls2;
            JsonSerializer jsonSerializer3;
            Enum enumR = (Enum) entry.getKey();
            EnumValues enumValues3 = enumValues2 == null ? ((EnumSerializer) ((SerializerBase) serializerProvider.findValueSerializer(enumR.getDeclaringClass(), this._property))).getEnumValues() : enumValues2;
            jsonGenerator.writeFieldName(enumValues3.serializedValueFor(enumR));
            Object value = entry.getValue();
            if (value == null) {
                serializerProvider.defaultSerializeNull(jsonGenerator);
                cls2 = cls;
                jsonSerializer3 = jsonSerializer2;
            } else {
                Class cls3 = value.getClass();
                if (cls3 == cls) {
                    cls3 = cls;
                    jsonSerializer = jsonSerializer2;
                } else {
                    jsonSerializer2 = serializerProvider.findValueSerializer(cls3, this._property);
                    jsonSerializer = jsonSerializer2;
                }
                try {
                    jsonSerializer2.serialize(value, jsonGenerator, serializerProvider);
                    cls2 = cls3;
                    jsonSerializer3 = jsonSerializer;
                } catch (Throwable e) {
                    wrapAndThrow(serializerProvider, e, (Object) enumMap, ((Enum) entry.getKey()).name());
                    cls2 = cls3;
                    jsonSerializer3 = jsonSerializer;
                }
            }
            enumValues2 = enumValues3;
            cls = cls2;
            jsonSerializer2 = jsonSerializer3;
        }
    }

    protected void serializeContentsUsing(EnumMap<? extends Enum<?>, ?> enumMap, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, JsonSerializer<Object> jsonSerializer) {
        EnumValues enumValues = this._keyEnums;
        EnumValues enumValues2 = enumValues;
        for (Entry entry : enumMap.entrySet()) {
            Enum enumR = (Enum) entry.getKey();
            if (enumValues2 == null) {
                enumValues2 = ((EnumSerializer) ((SerializerBase) serializerProvider.findValueSerializer(enumR.getDeclaringClass(), this._property))).getEnumValues();
            }
            jsonGenerator.writeFieldName(enumValues2.serializedValueFor(enumR));
            Object value = entry.getValue();
            if (value == null) {
                serializerProvider.defaultSerializeNull(jsonGenerator);
            } else {
                try {
                    jsonSerializer.serialize(value, jsonGenerator, serializerProvider);
                } catch (Throwable e) {
                    wrapAndThrow(serializerProvider, e, (Object) enumMap, ((Enum) entry.getKey()).name());
                }
            }
        }
    }

    public void serializeWithType(EnumMap<? extends Enum<?>, ?> enumMap, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
        typeSerializer.writeTypePrefixForObject(enumMap, jsonGenerator);
        if (!enumMap.isEmpty()) {
            serializeContents(enumMap, jsonGenerator, serializerProvider);
        }
        typeSerializer.writeTypeSuffixForObject(enumMap, jsonGenerator);
    }
}
