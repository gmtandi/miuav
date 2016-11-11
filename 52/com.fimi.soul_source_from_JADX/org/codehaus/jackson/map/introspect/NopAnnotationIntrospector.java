package org.codehaus.jackson.map.introspect;

import java.lang.annotation.Annotation;
import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.KeyDeserializer;
import org.codehaus.jackson.map.annotate.JsonSerialize.Typing;
import org.codehaus.jackson.type.JavaType;

public class NopAnnotationIntrospector extends AnnotationIntrospector {
    public static final NopAnnotationIntrospector instance;

    static {
        instance = new NopAnnotationIntrospector();
    }

    public Boolean findCachability(AnnotatedClass annotatedClass) {
        return null;
    }

    public Class<JsonDeserializer<?>> findContentDeserializer(Annotated annotated) {
        return null;
    }

    public String findDeserializablePropertyName(AnnotatedField annotatedField) {
        return null;
    }

    public Class<?> findDeserializationContentType(Annotated annotated, JavaType javaType, String str) {
        return null;
    }

    public Class<?> findDeserializationKeyType(Annotated annotated, JavaType javaType, String str) {
        return null;
    }

    public Class<?> findDeserializationType(Annotated annotated, JavaType javaType, String str) {
        return null;
    }

    public Object findDeserializer(Annotated annotated, BeanProperty beanProperty) {
        return null;
    }

    public String findEnumValue(Enum<?> enumR) {
        return null;
    }

    public String findGettablePropertyName(AnnotatedMethod annotatedMethod) {
        return null;
    }

    public Boolean findIgnoreUnknownProperties(AnnotatedClass annotatedClass) {
        return null;
    }

    public Class<KeyDeserializer> findKeyDeserializer(Annotated annotated) {
        return null;
    }

    public String[] findPropertiesToIgnore(AnnotatedClass annotatedClass) {
        return null;
    }

    public String findPropertyNameForParam(AnnotatedParameter annotatedParameter) {
        return null;
    }

    public String findRootName(AnnotatedClass annotatedClass) {
        return null;
    }

    public String findSerializablePropertyName(AnnotatedField annotatedField) {
        return null;
    }

    public String[] findSerializationPropertyOrder(AnnotatedClass annotatedClass) {
        return null;
    }

    public Boolean findSerializationSortAlphabetically(AnnotatedClass annotatedClass) {
        return null;
    }

    public Class<?> findSerializationType(Annotated annotated) {
        return null;
    }

    public Typing findSerializationTyping(Annotated annotated) {
        return null;
    }

    public Class<?>[] findSerializationViews(Annotated annotated) {
        return null;
    }

    public Object findSerializer(Annotated annotated, BeanProperty beanProperty) {
        return null;
    }

    public String findSettablePropertyName(AnnotatedMethod annotatedMethod) {
        return null;
    }

    public boolean hasAsValueAnnotation(AnnotatedMethod annotatedMethod) {
        return false;
    }

    public boolean isHandled(Annotation annotation) {
        return false;
    }

    public boolean isIgnorableConstructor(AnnotatedConstructor annotatedConstructor) {
        return false;
    }

    public boolean isIgnorableField(AnnotatedField annotatedField) {
        return false;
    }

    public boolean isIgnorableMethod(AnnotatedMethod annotatedMethod) {
        return false;
    }
}
