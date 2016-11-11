package org.codehaus.jackson.map.deser;

import java.io.IOException;
import java.lang.reflect.Method;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.introspect.AnnotatedMethod;
import org.codehaus.jackson.type.JavaType;

public final class SettableAnyProperty {
    protected final BeanProperty _property;
    protected final Method _setter;
    protected final JavaType _type;
    protected JsonDeserializer<Object> _valueDeserializer;

    public SettableAnyProperty(BeanProperty beanProperty, AnnotatedMethod annotatedMethod, JavaType javaType) {
        this._property = beanProperty;
        this._type = javaType;
        this._setter = annotatedMethod.getAnnotated();
    }

    private String getClassName() {
        return this._setter.getDeclaringClass().getName();
    }

    protected void _throwAsIOE(Exception exception, String str, Object obj) {
        if (exception instanceof IllegalArgumentException) {
            String name = obj == null ? "[NULL]" : obj.getClass().getName();
            StringBuilder append = new StringBuilder("Problem deserializing \"any\" property '").append(str);
            append.append("' of class " + getClassName() + " (expected type: ").append(this._type);
            append.append("; actual type: ").append(name).append(")");
            name = exception.getMessage();
            if (name != null) {
                append.append(", problem: ").append(name);
            } else {
                append.append(" (no error message provided)");
            }
            throw new JsonMappingException(append.toString(), null, exception);
        } else if (exception instanceof IOException) {
            throw ((IOException) exception);
        } else if (exception instanceof RuntimeException) {
            throw ((RuntimeException) exception);
        } else {
            Throwable cause;
            while (cause.getCause() != null) {
                cause = cause.getCause();
            }
            throw new JsonMappingException(cause.getMessage(), null, cause);
        }
    }

    public final Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        return jsonParser.getCurrentToken() == JsonToken.VALUE_NULL ? null : this._valueDeserializer.deserialize(jsonParser, deserializationContext);
    }

    public final void deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj, String str) {
        set(obj, str, deserialize(jsonParser, deserializationContext));
    }

    public BeanProperty getProperty() {
        return this._property;
    }

    public JavaType getType() {
        return this._type;
    }

    public boolean hasValueDeserializer() {
        return this._valueDeserializer != null;
    }

    public final void set(Object obj, String str, Object obj2) {
        try {
            this._setter.invoke(obj, new Object[]{str, obj2});
        } catch (Exception e) {
            _throwAsIOE(e, str, obj2);
        }
    }

    public void setValueDeserializer(JsonDeserializer<Object> jsonDeserializer) {
        if (this._valueDeserializer != null) {
            throw new IllegalStateException("Already had assigned deserializer for SettableAnyProperty");
        }
        this._valueDeserializer = jsonDeserializer;
    }

    public String toString() {
        return "[any property on class " + getClassName() + "]";
    }
}
