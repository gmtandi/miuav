package org.codehaus.jackson.map.introspect;

import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import org.codehaus.jackson.map.type.TypeBindings;
import org.codehaus.jackson.type.JavaType;

public final class AnnotatedMethod extends AnnotatedWithParams {
    protected final Method _method;
    protected Class<?>[] _paramTypes;

    public AnnotatedMethod(Method method, AnnotationMap annotationMap, AnnotationMap[] annotationMapArr) {
        super(annotationMap, annotationMapArr);
        this._method = method;
    }

    public Method getAnnotated() {
        return this._method;
    }

    public Class<?> getDeclaringClass() {
        return this._method.getDeclaringClass();
    }

    public String getFullName() {
        return getDeclaringClass().getName() + "#" + getName() + "(" + getParameterCount() + " params)";
    }

    public Type getGenericType() {
        return this._method.getGenericReturnType();
    }

    public Member getMember() {
        return this._method;
    }

    public int getModifiers() {
        return this._method.getModifiers();
    }

    public String getName() {
        return this._method.getName();
    }

    public AnnotatedParameter getParameter(int i) {
        return new AnnotatedParameter(this, getParameterType(i), this._paramAnnotations[i]);
    }

    public Class<?> getParameterClass(int i) {
        Class[] parameterTypes = this._method.getParameterTypes();
        return i >= parameterTypes.length ? null : parameterTypes[i];
    }

    public Class<?>[] getParameterClasses() {
        if (this._paramTypes == null) {
            this._paramTypes = this._method.getParameterTypes();
        }
        return this._paramTypes;
    }

    public int getParameterCount() {
        return getParameterTypes().length;
    }

    public Type getParameterType(int i) {
        Type[] genericParameterTypes = this._method.getGenericParameterTypes();
        return i >= genericParameterTypes.length ? null : genericParameterTypes[i];
    }

    public Type[] getParameterTypes() {
        return this._method.getGenericParameterTypes();
    }

    public Class<?> getRawType() {
        return this._method.getReturnType();
    }

    public JavaType getType(TypeBindings typeBindings) {
        return getType(typeBindings, this._method.getTypeParameters());
    }

    public String toString() {
        return "[method " + getName() + ", annotations: " + this._annotations + "]";
    }

    public AnnotatedMethod withMethod(Method method) {
        return new AnnotatedMethod(method, this._annotations, this._paramAnnotations);
    }
}
