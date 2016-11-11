package org.codehaus.jackson.map.ser;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.io.SerializedString;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.TypeSerializer;
import org.codehaus.jackson.map.introspect.AnnotatedMember;
import org.codehaus.jackson.map.ser.impl.PropertySerializerMap;
import org.codehaus.jackson.map.ser.impl.PropertySerializerMap.SerializerAndMapResult;
import org.codehaus.jackson.map.util.Annotations;
import org.codehaus.jackson.type.JavaType;

public class BeanPropertyWriter implements BeanProperty {
    protected final Method _accessorMethod;
    protected final JavaType _cfgSerializationType;
    protected final Annotations _contextAnnotations;
    protected final JavaType _declaredType;
    protected PropertySerializerMap _dynamicSerializers;
    protected final Field _field;
    protected Class<?>[] _includeInViews;
    protected HashMap<Object, Object> _internalSettings;
    protected final AnnotatedMember _member;
    protected final SerializedString _name;
    protected JavaType _nonTrivialBaseType;
    protected final JsonSerializer<Object> _serializer;
    protected final boolean _suppressNulls;
    protected final Object _suppressableValue;
    protected TypeSerializer _typeSerializer;

    public BeanPropertyWriter(AnnotatedMember annotatedMember, Annotations annotations, String str, JavaType javaType, JsonSerializer<Object> jsonSerializer, TypeSerializer typeSerializer, JavaType javaType2, Method method, Field field, boolean z, Object obj) {
        this(annotatedMember, annotations, new SerializedString(str), javaType, (JsonSerializer) jsonSerializer, typeSerializer, javaType2, method, field, z, obj);
    }

    public BeanPropertyWriter(AnnotatedMember annotatedMember, Annotations annotations, SerializedString serializedString, JavaType javaType, JsonSerializer<Object> jsonSerializer, TypeSerializer typeSerializer, JavaType javaType2, Method method, Field field, boolean z, Object obj) {
        this._member = annotatedMember;
        this._contextAnnotations = annotations;
        this._name = serializedString;
        this._declaredType = javaType;
        this._serializer = jsonSerializer;
        this._dynamicSerializers = jsonSerializer == null ? PropertySerializerMap.emptyMap() : null;
        this._typeSerializer = typeSerializer;
        this._cfgSerializationType = javaType2;
        this._accessorMethod = method;
        this._field = field;
        this._suppressNulls = z;
        this._suppressableValue = obj;
    }

    protected BeanPropertyWriter(BeanPropertyWriter beanPropertyWriter) {
        this(beanPropertyWriter, beanPropertyWriter._serializer);
    }

    protected BeanPropertyWriter(BeanPropertyWriter beanPropertyWriter, JsonSerializer<Object> jsonSerializer) {
        this._serializer = jsonSerializer;
        this._member = beanPropertyWriter._member;
        this._contextAnnotations = beanPropertyWriter._contextAnnotations;
        this._declaredType = beanPropertyWriter._declaredType;
        this._accessorMethod = beanPropertyWriter._accessorMethod;
        this._field = beanPropertyWriter._field;
        if (beanPropertyWriter._internalSettings != null) {
            this._internalSettings = new HashMap(beanPropertyWriter._internalSettings);
        }
        this._name = beanPropertyWriter._name;
        this._cfgSerializationType = beanPropertyWriter._cfgSerializationType;
        this._dynamicSerializers = beanPropertyWriter._dynamicSerializers;
        this._suppressNulls = beanPropertyWriter._suppressNulls;
        this._suppressableValue = beanPropertyWriter._suppressableValue;
        this._includeInViews = beanPropertyWriter._includeInViews;
        this._typeSerializer = beanPropertyWriter._typeSerializer;
        this._nonTrivialBaseType = beanPropertyWriter._nonTrivialBaseType;
    }

    protected final JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap propertySerializerMap, Class<?> cls, SerializerProvider serializerProvider) {
        SerializerAndMapResult findAndAddSerializer = this._nonTrivialBaseType != null ? propertySerializerMap.findAndAddSerializer(this._nonTrivialBaseType.forcedNarrowBy(cls), serializerProvider, (BeanProperty) this) : propertySerializerMap.findAndAddSerializer((Class) cls, serializerProvider, (BeanProperty) this);
        if (propertySerializerMap != findAndAddSerializer.map) {
            this._dynamicSerializers = findAndAddSerializer.map;
        }
        return findAndAddSerializer.serializer;
    }

    protected void _reportSelfReference(Object obj) {
        throw new JsonMappingException("Direct self-reference leading to cycle");
    }

    public final Object get(Object obj) {
        return this._accessorMethod != null ? this._accessorMethod.invoke(obj, new Object[0]) : this._field.get(obj);
    }

    public <A extends Annotation> A getAnnotation(Class<A> cls) {
        return this._member.getAnnotation(cls);
    }

    public <A extends Annotation> A getContextAnnotation(Class<A> cls) {
        return this._contextAnnotations.get(cls);
    }

    public Type getGenericPropertyType() {
        return this._accessorMethod != null ? this._accessorMethod.getGenericReturnType() : this._field.getGenericType();
    }

    public Object getInternalSetting(Object obj) {
        return this._internalSettings == null ? null : this._internalSettings.get(obj);
    }

    public AnnotatedMember getMember() {
        return this._member;
    }

    public String getName() {
        return this._name.getValue();
    }

    public Class<?> getPropertyType() {
        return this._accessorMethod != null ? this._accessorMethod.getReturnType() : this._field.getType();
    }

    public Class<?> getRawSerializationType() {
        return this._cfgSerializationType == null ? null : this._cfgSerializationType.getRawClass();
    }

    public JavaType getSerializationType() {
        return this._cfgSerializationType;
    }

    public SerializedString getSerializedName() {
        return this._name;
    }

    protected JsonSerializer<Object> getSerializer() {
        return this._serializer;
    }

    public JavaType getType() {
        return this._declaredType;
    }

    public Class<?>[] getViews() {
        return this._includeInViews;
    }

    public boolean hasSerializer() {
        return this._serializer != null;
    }

    public Object removeInternalSetting(Object obj) {
        if (this._internalSettings == null) {
            return null;
        }
        Object remove = this._internalSettings.remove(obj);
        if (this._internalSettings.size() != 0) {
            return remove;
        }
        this._internalSettings = null;
        return remove;
    }

    public void serializeAsField(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        Object obj2 = get(obj);
        if (obj2 != null) {
            if (obj2 == obj) {
                _reportSelfReference(obj);
            }
            if (this._suppressableValue == null || !this._suppressableValue.equals(obj2)) {
                JsonSerializer jsonSerializer = this._serializer;
                if (jsonSerializer == null) {
                    Class cls = obj2.getClass();
                    PropertySerializerMap propertySerializerMap = this._dynamicSerializers;
                    jsonSerializer = propertySerializerMap.serializerFor(cls);
                    if (jsonSerializer == null) {
                        jsonSerializer = _findAndAddDynamic(propertySerializerMap, cls, serializerProvider);
                    }
                }
                jsonGenerator.writeFieldName(this._name);
                if (this._typeSerializer == null) {
                    jsonSerializer.serialize(obj2, jsonGenerator, serializerProvider);
                } else {
                    jsonSerializer.serializeWithType(obj2, jsonGenerator, serializerProvider, this._typeSerializer);
                }
            }
        } else if (!this._suppressNulls) {
            jsonGenerator.writeFieldName(this._name);
            serializerProvider.defaultSerializeNull(jsonGenerator);
        }
    }

    public Object setInternalSetting(Object obj, Object obj2) {
        if (this._internalSettings == null) {
            this._internalSettings = new HashMap();
        }
        return this._internalSettings.put(obj, obj2);
    }

    public void setNonTrivialBaseType(JavaType javaType) {
        this._nonTrivialBaseType = javaType;
    }

    public void setViews(Class<?>[] clsArr) {
        this._includeInViews = clsArr;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(40);
        stringBuilder.append("property '").append(getName()).append("' (");
        if (this._accessorMethod != null) {
            stringBuilder.append("via method ").append(this._accessorMethod.getDeclaringClass().getName()).append("#").append(this._accessorMethod.getName());
        } else {
            stringBuilder.append("field \"").append(this._field.getDeclaringClass().getName()).append("#").append(this._field.getName());
        }
        if (this._serializer == null) {
            stringBuilder.append(", no static serializer");
        } else {
            stringBuilder.append(", static serializer of type " + this._serializer.getClass().getName());
        }
        stringBuilder.append(')');
        return stringBuilder.toString();
    }

    public BeanPropertyWriter withSerializer(JsonSerializer<Object> jsonSerializer) {
        if (getClass() == BeanPropertyWriter.class) {
            return new BeanPropertyWriter(this, jsonSerializer);
        }
        throw new IllegalStateException("BeanPropertyWriter sub-class does not override 'withSerializer()'; needs to!");
    }
}
