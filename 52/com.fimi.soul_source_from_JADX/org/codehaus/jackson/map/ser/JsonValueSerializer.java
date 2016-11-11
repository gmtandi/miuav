package org.codehaus.jackson.map.ser;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ResolvableSerializer;
import org.codehaus.jackson.map.SerializationConfig.Feature;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.TypeSerializer;
import org.codehaus.jackson.map.annotate.JacksonStdImpl;
import org.codehaus.jackson.schema.JsonSchema;
import org.codehaus.jackson.schema.SchemaAware;
import org.codehaus.jackson.type.JavaType;

@JacksonStdImpl
public final class JsonValueSerializer extends SerializerBase<Object> implements ResolvableSerializer, SchemaAware {
    protected final Method _accessorMethod;
    protected boolean _forceTypeInformation;
    protected final BeanProperty _property;
    protected JsonSerializer<Object> _valueSerializer;

    public JsonValueSerializer(Method method, JsonSerializer<Object> jsonSerializer, BeanProperty beanProperty) {
        super(Object.class);
        this._accessorMethod = method;
        this._valueSerializer = jsonSerializer;
        this._property = beanProperty;
    }

    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
        return this._valueSerializer instanceof SchemaAware ? ((SchemaAware) this._valueSerializer).getSchema(serializerProvider, null) : JsonSchema.getDefaultSchemaNode();
    }

    protected boolean isNaturalTypeWithStdHandling(JavaType javaType, JsonSerializer<?> jsonSerializer) {
        Class rawClass = javaType.getRawClass();
        if (javaType.isPrimitive()) {
            if (!(rawClass == Integer.TYPE || rawClass == Boolean.TYPE || rawClass == Double.TYPE)) {
                return false;
            }
        } else if (!(rawClass == String.class || rawClass == Integer.class || rawClass == Boolean.class || rawClass == Double.class)) {
            return false;
        }
        return jsonSerializer.getClass().getAnnotation(JacksonStdImpl.class) != null;
    }

    public void resolve(SerializerProvider serializerProvider) {
        if (this._valueSerializer != null) {
            return;
        }
        if (serializerProvider.isEnabled(Feature.USE_STATIC_TYPING) || Modifier.isFinal(this._accessorMethod.getReturnType().getModifiers())) {
            JavaType constructType = serializerProvider.constructType(this._accessorMethod.getGenericReturnType());
            this._valueSerializer = serializerProvider.findTypedValueSerializer(constructType, false, this._property);
            this._forceTypeInformation = isNaturalTypeWithStdHandling(constructType, this._valueSerializer);
        }
    }

    public void serialize(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        try {
            Object invoke = this._accessorMethod.invoke(obj, new Object[0]);
            if (invoke == null) {
                serializerProvider.defaultSerializeNull(jsonGenerator);
                return;
            }
            JsonSerializer jsonSerializer = this._valueSerializer;
            if (jsonSerializer == null) {
                jsonSerializer = serializerProvider.findTypedValueSerializer(invoke.getClass(), true, this._property);
            }
            jsonSerializer.serialize(invoke, jsonGenerator, serializerProvider);
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
            throw JsonMappingException.wrapWithPath(e3, obj, this._accessorMethod.getName() + "()");
        }
    }

    public void serializeWithType(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
        try {
            Object invoke = this._accessorMethod.invoke(obj, new Object[0]);
            if (invoke == null) {
                serializerProvider.defaultSerializeNull(jsonGenerator);
                return;
            }
            JsonSerializer jsonSerializer = this._valueSerializer;
            if (jsonSerializer != null) {
                if (this._forceTypeInformation) {
                    typeSerializer.writeTypePrefixForScalar(obj, jsonGenerator);
                }
                jsonSerializer.serializeWithType(invoke, jsonGenerator, serializerProvider, typeSerializer);
                if (this._forceTypeInformation) {
                    typeSerializer.writeTypeSuffixForScalar(obj, jsonGenerator);
                    return;
                }
                return;
            }
            serializerProvider.findTypedValueSerializer(invoke.getClass(), true, this._property).serialize(invoke, jsonGenerator, serializerProvider);
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
            throw JsonMappingException.wrapWithPath(e3, obj, this._accessorMethod.getName() + "()");
        }
    }

    public String toString() {
        return "(@JsonValue serializer for method " + this._accessorMethod.getDeclaringClass() + "#" + this._accessorMethod.getName() + ")";
    }
}
