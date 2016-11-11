package org.codehaus.jackson.map.introspect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import org.codehaus.jackson.map.type.TypeBindings;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.type.JavaType;

public abstract class AnnotatedWithParams extends AnnotatedMember {
    protected final AnnotationMap _annotations;
    protected final AnnotationMap[] _paramAnnotations;

    protected AnnotatedWithParams(AnnotationMap annotationMap, AnnotationMap[] annotationMapArr) {
        this._annotations = annotationMap;
        this._paramAnnotations = annotationMapArr;
    }

    public final void addIfNotPresent(Annotation annotation) {
        this._annotations.addIfNotPresent(annotation);
    }

    public final void addOrOverride(Annotation annotation) {
        this._annotations.add(annotation);
    }

    public final void addOrOverrideParam(int i, Annotation annotation) {
        AnnotationMap annotationMap = this._paramAnnotations[i];
        if (annotationMap == null) {
            annotationMap = new AnnotationMap();
            this._paramAnnotations[i] = annotationMap;
        }
        annotationMap.add(annotation);
    }

    public final <A extends Annotation> A getAnnotation(Class<A> cls) {
        return this._annotations.get(cls);
    }

    public final int getAnnotationCount() {
        return this._annotations.size();
    }

    public abstract AnnotatedParameter getParameter(int i);

    public final AnnotationMap getParameterAnnotations(int i) {
        return (this._paramAnnotations == null || i < 0 || i > this._paramAnnotations.length) ? null : this._paramAnnotations[i];
    }

    public abstract Class<?> getParameterClass(int i);

    public abstract int getParameterCount();

    public abstract Type getParameterType(int i);

    protected JavaType getType(TypeBindings typeBindings, TypeVariable<?>[] typeVariableArr) {
        if (typeVariableArr != null && typeVariableArr.length > 0) {
            typeBindings = typeBindings.childInstance();
            for (TypeVariable typeVariable : typeVariableArr) {
                typeBindings._addPlaceholder(typeVariable.getName());
                Type type = typeVariable.getBounds()[0];
                typeBindings.addBinding(typeVariable.getName(), type == null ? TypeFactory.unknownType() : typeBindings.resolveType(type));
            }
        }
        return typeBindings.resolveType(getGenericType());
    }

    public final JavaType resolveParameterType(int i, TypeBindings typeBindings) {
        return typeBindings.resolveType(getParameterType(i));
    }
}
