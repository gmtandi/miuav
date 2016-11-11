package org.codehaus.jackson.map.ser;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.SerializationConfig.Feature;
import org.codehaus.jackson.map.TypeSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.map.annotate.JsonSerialize.Typing;
import org.codehaus.jackson.map.introspect.Annotated;
import org.codehaus.jackson.map.introspect.AnnotatedField;
import org.codehaus.jackson.map.introspect.AnnotatedMember;
import org.codehaus.jackson.map.introspect.AnnotatedMethod;
import org.codehaus.jackson.map.introspect.BasicBeanDescription;
import org.codehaus.jackson.map.util.Annotations;
import org.codehaus.jackson.smile.SmileConstants;
import org.codehaus.jackson.type.JavaType;

public class PropertyBuilder {
    protected final AnnotationIntrospector _annotationIntrospector;
    protected final BasicBeanDescription _beanDesc;
    protected final SerializationConfig _config;
    protected Object _defaultBean;
    protected final Inclusion _outputProps;

    /* renamed from: org.codehaus.jackson.map.ser.PropertyBuilder.1 */
    /* synthetic */ class C35901 {
        static final /* synthetic */ int[] f16122x1c046cf1;

        static {
            f16122x1c046cf1 = new int[Inclusion.values().length];
            try {
                f16122x1c046cf1[Inclusion.NON_DEFAULT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f16122x1c046cf1[Inclusion.NON_NULL.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public PropertyBuilder(SerializationConfig serializationConfig, BasicBeanDescription basicBeanDescription) {
        this._config = serializationConfig;
        this._beanDesc = basicBeanDescription;
        this._outputProps = basicBeanDescription.findSerializationInclusion(serializationConfig.getSerializationInclusion());
        this._annotationIntrospector = this._config.getAnnotationIntrospector();
    }

    protected Object _throwWrapped(Exception exception, String str, Object obj) {
        Throwable th = exception;
        while (th.getCause() != null) {
            th = th.getCause();
        }
        if (th instanceof Error) {
            throw ((Error) th);
        } else if (th instanceof RuntimeException) {
            throw ((RuntimeException) th);
        } else {
            throw new IllegalArgumentException("Failed to get property '" + str + "' of default " + obj.getClass().getName() + " instance");
        }
    }

    protected BeanPropertyWriter buildWriter(String str, JavaType javaType, JsonSerializer<Object> jsonSerializer, TypeSerializer typeSerializer, TypeSerializer typeSerializer2, AnnotatedMember annotatedMember, boolean z) {
        Method method;
        Field annotated;
        JavaType withContentTypeHandler;
        if (annotatedMember instanceof AnnotatedField) {
            method = null;
            annotated = ((AnnotatedField) annotatedMember).getAnnotated();
        } else {
            method = ((AnnotatedMethod) annotatedMember).getAnnotated();
            annotated = null;
        }
        JavaType findSerializationType = findSerializationType(annotatedMember, z, javaType);
        if (typeSerializer2 != null) {
            if (findSerializationType == null) {
                findSerializationType = javaType;
            }
            if (findSerializationType.getContentType() == null) {
                throw new IllegalStateException("Problem trying to create BeanPropertyWriter for property '" + str + "' (of type " + this._beanDesc.getType() + "); serialization type " + findSerializationType + " has no content");
            }
            withContentTypeHandler = findSerializationType.withContentTypeHandler(typeSerializer2);
            withContentTypeHandler.getContentType();
        } else {
            withContentTypeHandler = findSerializationType;
        }
        Object obj = null;
        boolean z2 = false;
        Inclusion findSerializationInclusion = this._annotationIntrospector.findSerializationInclusion(annotatedMember, this._outputProps);
        if (findSerializationInclusion != null) {
            switch (C35901.f16122x1c046cf1[findSerializationInclusion.ordinal()]) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    obj = getDefaultValue(str, method, annotated);
                    if (obj == null) {
                        z2 = true;
                        break;
                    }
                    break;
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    z2 = true;
                    break;
            }
        }
        return new BeanPropertyWriter(annotatedMember, this._beanDesc.getClassAnnotations(), str, javaType, (JsonSerializer) jsonSerializer, typeSerializer, withContentTypeHandler, method, annotated, z2, obj);
    }

    protected JavaType findSerializationType(Annotated annotated, boolean z, JavaType javaType) {
        JavaType widenBy;
        boolean z2;
        boolean z3 = true;
        Class findSerializationType = this._annotationIntrospector.findSerializationType(annotated);
        if (findSerializationType != null) {
            Class rawClass = javaType.getRawClass();
            if (findSerializationType.isAssignableFrom(rawClass)) {
                widenBy = javaType.widenBy(findSerializationType);
            } else if (rawClass.isAssignableFrom(findSerializationType)) {
                widenBy = javaType.forcedNarrowBy(findSerializationType);
            } else {
                throw new IllegalArgumentException("Illegal concrete-type annotation for method '" + annotated.getName() + "': class " + findSerializationType.getName() + " not a super-type of (declared) class " + rawClass.getName());
            }
            z = true;
        } else {
            widenBy = javaType;
        }
        JavaType modifySecondaryTypesByAnnotation = BasicSerializerFactory.modifySecondaryTypesByAnnotation(this._config, annotated, widenBy);
        if (modifySecondaryTypesByAnnotation != widenBy) {
            widenBy = modifySecondaryTypesByAnnotation;
            z2 = true;
        } else {
            z2 = z;
        }
        if (!z2) {
            Typing findSerializationTyping = this._annotationIntrospector.findSerializationTyping(annotated);
            if (findSerializationTyping != null) {
                if (findSerializationTyping != Typing.STATIC) {
                    z3 = false;
                }
                return z3 ? widenBy : null;
            }
        }
        z3 = z2;
        if (z3) {
        }
    }

    public Annotations getClassAnnotations() {
        return this._beanDesc.getClassAnnotations();
    }

    protected Object getDefaultBean() {
        if (this._defaultBean == null) {
            this._defaultBean = this._beanDesc.instantiateBean(this._config.isEnabled(Feature.CAN_OVERRIDE_ACCESS_MODIFIERS));
            if (this._defaultBean == null) {
                throw new IllegalArgumentException("Class " + this._beanDesc.getClassInfo().getAnnotated().getName() + " has no default constructor; can not instantiate default bean value to support 'properties=JsonSerialize.Inclusion.NON_DEFAULT' annotation");
            }
        }
        return this._defaultBean;
    }

    protected Object getDefaultValue(String str, Method method, Field field) {
        Object defaultBean = getDefaultBean();
        if (method == null) {
            return field.get(defaultBean);
        }
        try {
            return method.invoke(defaultBean, new Object[0]);
        } catch (Exception e) {
            return _throwWrapped(e, str, defaultBean);
        }
    }
}
