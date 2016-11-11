package org.codehaus.jackson.map.deser;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.TypeDeserializer;
import org.codehaus.jackson.map.introspect.AnnotatedField;
import org.codehaus.jackson.map.introspect.AnnotatedMember;
import org.codehaus.jackson.map.introspect.AnnotatedMethod;
import org.codehaus.jackson.map.introspect.AnnotatedParameter;
import org.codehaus.jackson.map.util.Annotations;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.util.InternCache;
import org.p122a.p123a.C2915a;

public abstract class SettableBeanProperty implements BeanProperty {
    protected final Annotations _contextAnnotations;
    protected String _managedReferenceName;
    protected NullProvider _nullProvider;
    protected final String _propName;
    protected int _propertyIndex;
    protected final JavaType _type;
    protected JsonDeserializer<Object> _valueDeserializer;
    protected TypeDeserializer _valueTypeDeserializer;

    public final class CreatorProperty extends SettableBeanProperty {
        protected final AnnotatedParameter _annotated;
        protected final int _index;

        public CreatorProperty(String str, JavaType javaType, TypeDeserializer typeDeserializer, Annotations annotations, AnnotatedParameter annotatedParameter, int i) {
            super(str, javaType, typeDeserializer, annotations);
            this._annotated = annotatedParameter;
            this._index = i;
        }

        public void deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) {
            set(obj, deserialize(jsonParser, deserializationContext));
        }

        public <A extends Annotation> A getAnnotation(Class<A> cls) {
            return this._annotated.getAnnotation(cls);
        }

        public int getCreatorIndex() {
            return this._index;
        }

        public AnnotatedMember getMember() {
            return this._annotated;
        }

        public void set(Object obj, Object obj2) {
        }
    }

    public final class FieldProperty extends SettableBeanProperty {
        protected final AnnotatedField _annotated;
        protected final Field _field;

        public FieldProperty(String str, JavaType javaType, TypeDeserializer typeDeserializer, Annotations annotations, AnnotatedField annotatedField) {
            super(str, javaType, typeDeserializer, annotations);
            this._annotated = annotatedField;
            this._field = annotatedField.getAnnotated();
        }

        public void deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) {
            set(obj, deserialize(jsonParser, deserializationContext));
        }

        public <A extends Annotation> A getAnnotation(Class<A> cls) {
            return this._annotated.getAnnotation(cls);
        }

        public AnnotatedMember getMember() {
            return this._annotated;
        }

        public final void set(Object obj, Object obj2) {
            try {
                this._field.set(obj, obj2);
            } catch (Exception e) {
                _throwAsIOE(e, obj2);
            }
        }
    }

    public final class ManagedReferenceProperty extends SettableBeanProperty {
        protected final SettableBeanProperty _backProperty;
        protected final boolean _isContainer;
        protected final SettableBeanProperty _managedProperty;
        protected final String _referenceName;

        public ManagedReferenceProperty(String str, SettableBeanProperty settableBeanProperty, SettableBeanProperty settableBeanProperty2, Annotations annotations, boolean z) {
            super(settableBeanProperty.getName(), settableBeanProperty.getType(), settableBeanProperty._valueTypeDeserializer, annotations);
            this._referenceName = str;
            this._managedProperty = settableBeanProperty;
            this._backProperty = settableBeanProperty2;
            this._isContainer = z;
        }

        public void deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) {
            set(obj, this._managedProperty.deserialize(jsonParser, deserializationContext));
        }

        public <A extends Annotation> A getAnnotation(Class<A> cls) {
            return this._managedProperty.getAnnotation(cls);
        }

        public AnnotatedMember getMember() {
            return this._managedProperty.getMember();
        }

        public final void set(Object obj, Object obj2) {
            this._managedProperty.set(obj, obj2);
            if (obj2 == null) {
                return;
            }
            if (!this._isContainer) {
                this._backProperty.set(obj2, obj);
            } else if (obj2 instanceof Object[]) {
                for (Object obj3 : (Object[]) obj2) {
                    if (obj3 != null) {
                        this._backProperty.set(obj3, obj);
                    }
                }
            } else if (obj2 instanceof Collection) {
                for (Object next : (Collection) obj2) {
                    if (next != null) {
                        this._backProperty.set(next, obj);
                    }
                }
            } else if (obj2 instanceof Map) {
                for (Object next2 : ((Map) obj2).values()) {
                    if (next2 != null) {
                        this._backProperty.set(next2, obj);
                    }
                }
            } else {
                throw new IllegalStateException("Unsupported container type (" + obj2.getClass().getName() + ") when resolving reference '" + this._referenceName + "'");
            }
        }
    }

    public final class MethodProperty extends SettableBeanProperty {
        protected final AnnotatedMethod _annotated;
        protected final Method _setter;

        public MethodProperty(String str, JavaType javaType, TypeDeserializer typeDeserializer, Annotations annotations, AnnotatedMethod annotatedMethod) {
            super(str, javaType, typeDeserializer, annotations);
            this._annotated = annotatedMethod;
            this._setter = annotatedMethod.getAnnotated();
        }

        public void deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) {
            set(obj, deserialize(jsonParser, deserializationContext));
        }

        public <A extends Annotation> A getAnnotation(Class<A> cls) {
            return this._annotated.getAnnotation(cls);
        }

        public AnnotatedMember getMember() {
            return this._annotated;
        }

        public final void set(Object obj, Object obj2) {
            try {
                this._setter.invoke(obj, new Object[]{obj2});
            } catch (Exception e) {
                _throwAsIOE(e, obj2);
            }
        }
    }

    public final class NullProvider {
        private final boolean _isPrimitive;
        private final Object _nullValue;
        private final Class<?> _rawType;

        protected NullProvider(JavaType javaType, Object obj) {
            this._nullValue = obj;
            this._isPrimitive = javaType.isPrimitive();
            this._rawType = javaType.getRawClass();
        }

        public Object nullValue(DeserializationContext deserializationContext) {
            if (!this._isPrimitive || !deserializationContext.isEnabled(Feature.FAIL_ON_NULL_FOR_PRIMITIVES)) {
                return this._nullValue;
            }
            throw deserializationContext.mappingException("Can not map JSON null into type " + this._rawType.getName() + " (set DeserializationConfig.Feature.FAIL_ON_NULL_FOR_PRIMITIVES to 'false' to allow)");
        }
    }

    public final class SetterlessProperty extends SettableBeanProperty {
        protected final AnnotatedMethod _annotated;
        protected final Method _getter;

        public SetterlessProperty(String str, JavaType javaType, TypeDeserializer typeDeserializer, Annotations annotations, AnnotatedMethod annotatedMethod) {
            super(str, javaType, typeDeserializer, annotations);
            this._annotated = annotatedMethod;
            this._getter = annotatedMethod.getAnnotated();
        }

        public final void deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) {
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                try {
                    Object invoke = this._getter.invoke(obj, new Object[0]);
                    if (invoke == null) {
                        throw new JsonMappingException("Problem deserializing 'setterless' property '" + getName() + "': get method returned null");
                    }
                    this._valueDeserializer.deserialize(jsonParser, deserializationContext, invoke);
                } catch (Exception e) {
                    _throwAsIOE(e);
                }
            }
        }

        public <A extends Annotation> A getAnnotation(Class<A> cls) {
            return this._annotated.getAnnotation(cls);
        }

        public AnnotatedMember getMember() {
            return this._annotated;
        }

        public final void set(Object obj, Object obj2) {
            throw new UnsupportedOperationException("Should never call 'set' on setterless property");
        }
    }

    protected SettableBeanProperty(String str, JavaType javaType, TypeDeserializer typeDeserializer, Annotations annotations) {
        this._propertyIndex = -1;
        if (str == null || str.length() == 0) {
            this._propName = C2915a.f14760f;
        } else {
            this._propName = InternCache.instance.intern(str);
        }
        this._type = javaType;
        this._contextAnnotations = annotations;
        this._valueTypeDeserializer = typeDeserializer;
    }

    protected SettableBeanProperty(SettableBeanProperty settableBeanProperty) {
        this._propertyIndex = -1;
        this._propName = settableBeanProperty._propName;
        this._type = settableBeanProperty._type;
        this._contextAnnotations = settableBeanProperty._contextAnnotations;
        this._valueDeserializer = settableBeanProperty._valueDeserializer;
        this._valueTypeDeserializer = settableBeanProperty._valueTypeDeserializer;
        this._nullProvider = settableBeanProperty._nullProvider;
        this._managedReferenceName = settableBeanProperty._managedReferenceName;
        this._propertyIndex = settableBeanProperty._propertyIndex;
    }

    protected IOException _throwAsIOE(Exception exception) {
        if (exception instanceof IOException) {
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

    protected void _throwAsIOE(Exception exception, Object obj) {
        if (exception instanceof IllegalArgumentException) {
            String name = obj == null ? "[NULL]" : obj.getClass().getName();
            StringBuilder append = new StringBuilder("Problem deserializing property '").append(getPropertyName());
            append.append("' (expected type: ").append(getType());
            append.append("; actual type: ").append(name).append(")");
            name = exception.getMessage();
            if (name != null) {
                append.append(", problem: ").append(name);
            } else {
                append.append(" (no error message provided)");
            }
            throw new JsonMappingException(append.toString(), null, exception);
        }
        _throwAsIOE(exception);
    }

    public void assignIndex(int i) {
        if (this._propertyIndex != -1) {
            throw new IllegalStateException("Property '" + getName() + "' already had index (" + this._propertyIndex + "), trying to assign " + i);
        }
        this._propertyIndex = i;
    }

    public final Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        return jsonParser.getCurrentToken() == JsonToken.VALUE_NULL ? this._nullProvider == null ? null : this._nullProvider.nullValue(deserializationContext) : this._valueTypeDeserializer != null ? this._valueDeserializer.deserializeWithType(jsonParser, deserializationContext, this._valueTypeDeserializer) : this._valueDeserializer.deserialize(jsonParser, deserializationContext);
    }

    public abstract void deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj);

    public abstract <A extends Annotation> A getAnnotation(Class<A> cls);

    public <A extends Annotation> A getContextAnnotation(Class<A> cls) {
        return this._contextAnnotations.get(cls);
    }

    public int getCreatorIndex() {
        return -1;
    }

    protected final Class<?> getDeclaringClass() {
        return getMember().getDeclaringClass();
    }

    public String getManagedReferenceName() {
        return this._managedReferenceName;
    }

    public abstract AnnotatedMember getMember();

    public final String getName() {
        return this._propName;
    }

    @Deprecated
    public String getPropertyName() {
        return this._propName;
    }

    public int getProperytIndex() {
        return this._propertyIndex;
    }

    public JavaType getType() {
        return this._type;
    }

    public JsonDeserializer<Object> getValueDeserializer() {
        return this._valueDeserializer;
    }

    public boolean hasValueDeserializer() {
        return this._valueDeserializer != null;
    }

    public abstract void set(Object obj, Object obj2);

    public void setManagedReferenceName(String str) {
        this._managedReferenceName = str;
    }

    public void setValueDeserializer(JsonDeserializer<Object> jsonDeserializer) {
        if (this._valueDeserializer != null) {
            throw new IllegalStateException("Already had assigned deserializer for property '" + getName() + "' (class " + getDeclaringClass().getName() + ")");
        }
        this._valueDeserializer = jsonDeserializer;
        Object nullValue = this._valueDeserializer.getNullValue();
        this._nullProvider = nullValue == null ? null : new NullProvider(this._type, nullValue);
    }

    public String toString() {
        return "[property '" + getName() + "']";
    }
}
