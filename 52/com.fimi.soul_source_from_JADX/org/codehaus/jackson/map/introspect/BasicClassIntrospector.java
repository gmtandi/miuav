package org.codehaus.jackson.map.introspect;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Map;
import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.ClassIntrospector;
import org.codehaus.jackson.map.ClassIntrospector.MixInResolver;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.MapperConfig;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.util.ClassUtil;
import org.codehaus.jackson.smile.SmileConstants;
import org.codehaus.jackson.type.JavaType;

public class BasicClassIntrospector extends ClassIntrospector<BasicBeanDescription> {
    public static final GetterMethodFilter DEFAULT_GETTER_FILTER;
    public static final SetterAndGetterMethodFilter DEFAULT_SETTER_AND_GETTER_FILTER;
    public static final SetterMethodFilter DEFAULT_SETTER_FILTER;
    public static final BasicClassIntrospector instance;

    public class GetterMethodFilter implements MethodFilter {
        private GetterMethodFilter() {
        }

        public boolean includeMethod(Method method) {
            return ClassUtil.hasGetterSignature(method);
        }
    }

    public class SetterMethodFilter implements MethodFilter {
        public boolean includeMethod(Method method) {
            if (Modifier.isStatic(method.getModifiers())) {
                return false;
            }
            switch (method.getParameterTypes().length) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    return true;
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    return true;
                default:
                    return false;
            }
        }
    }

    public final class SetterAndGetterMethodFilter extends SetterMethodFilter {
        public boolean includeMethod(Method method) {
            if (super.includeMethod(method)) {
                return true;
            }
            if (!ClassUtil.hasGetterSignature(method)) {
                return false;
            }
            Class returnType = method.getReturnType();
            return Collection.class.isAssignableFrom(returnType) || Map.class.isAssignableFrom(returnType);
        }
    }

    static {
        DEFAULT_GETTER_FILTER = new GetterMethodFilter();
        DEFAULT_SETTER_FILTER = new SetterMethodFilter();
        DEFAULT_SETTER_AND_GETTER_FILTER = new SetterAndGetterMethodFilter();
        instance = new BasicClassIntrospector();
    }

    public BasicBeanDescription forClassAnnotations(MapperConfig<?> mapperConfig, Class<?> cls, MixInResolver mixInResolver) {
        boolean isAnnotationProcessingEnabled = mapperConfig.isAnnotationProcessingEnabled();
        AnnotationIntrospector annotationIntrospector = mapperConfig.getAnnotationIntrospector();
        if (!isAnnotationProcessingEnabled) {
            annotationIntrospector = null;
        }
        return new BasicBeanDescription(mapperConfig, mapperConfig.constructType(cls), AnnotatedClass.construct(cls, annotationIntrospector, mixInResolver));
    }

    public BasicBeanDescription forCreation(DeserializationConfig deserializationConfig, JavaType javaType, MixInResolver mixInResolver) {
        boolean isAnnotationProcessingEnabled = deserializationConfig.isAnnotationProcessingEnabled();
        AnnotationIntrospector annotationIntrospector = deserializationConfig.getAnnotationIntrospector();
        Class rawClass = javaType.getRawClass();
        if (!isAnnotationProcessingEnabled) {
            annotationIntrospector = null;
        }
        AnnotatedClass construct = AnnotatedClass.construct(rawClass, annotationIntrospector, mixInResolver);
        construct.resolveCreators(true);
        return new BasicBeanDescription(deserializationConfig, javaType, construct);
    }

    public BasicBeanDescription forDeserialization(DeserializationConfig deserializationConfig, JavaType javaType, MixInResolver mixInResolver) {
        boolean isAnnotationProcessingEnabled = deserializationConfig.isAnnotationProcessingEnabled();
        AnnotationIntrospector annotationIntrospector = deserializationConfig.getAnnotationIntrospector();
        Class rawClass = javaType.getRawClass();
        if (!isAnnotationProcessingEnabled) {
            annotationIntrospector = null;
        }
        AnnotatedClass construct = AnnotatedClass.construct(rawClass, annotationIntrospector, mixInResolver);
        construct.resolveMemberMethods(getDeserializationMethodFilter(deserializationConfig), true);
        construct.resolveCreators(true);
        construct.resolveFields(true);
        return new BasicBeanDescription(deserializationConfig, javaType, construct);
    }

    public BasicBeanDescription forDirectClassAnnotations(MapperConfig<?> mapperConfig, Class<?> cls, MixInResolver mixInResolver) {
        boolean isAnnotationProcessingEnabled = mapperConfig.isAnnotationProcessingEnabled();
        AnnotationIntrospector annotationIntrospector = mapperConfig.getAnnotationIntrospector();
        if (!isAnnotationProcessingEnabled) {
            annotationIntrospector = null;
        }
        return new BasicBeanDescription(mapperConfig, mapperConfig.constructType(cls), AnnotatedClass.constructWithoutSuperTypes(cls, annotationIntrospector, mixInResolver));
    }

    public BasicBeanDescription forSerialization(SerializationConfig serializationConfig, JavaType javaType, MixInResolver mixInResolver) {
        AnnotatedClass construct = AnnotatedClass.construct(javaType.getRawClass(), serializationConfig.getAnnotationIntrospector(), mixInResolver);
        construct.resolveMemberMethods(getSerializationMethodFilter(serializationConfig), false);
        construct.resolveCreators(true);
        construct.resolveFields(false);
        return new BasicBeanDescription(serializationConfig, javaType, construct);
    }

    protected MethodFilter getDeserializationMethodFilter(DeserializationConfig deserializationConfig) {
        return deserializationConfig.isEnabled(Feature.USE_GETTERS_AS_SETTERS) ? DEFAULT_SETTER_AND_GETTER_FILTER : DEFAULT_SETTER_FILTER;
    }

    protected MethodFilter getSerializationMethodFilter(SerializationConfig serializationConfig) {
        return DEFAULT_GETTER_FILTER;
    }
}
