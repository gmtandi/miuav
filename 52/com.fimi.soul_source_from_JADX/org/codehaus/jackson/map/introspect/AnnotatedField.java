package org.codehaus.jackson.map.introspect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Type;

public final class AnnotatedField extends AnnotatedMember {
    protected final AnnotationMap _annotations;
    protected final Field _field;

    public AnnotatedField(Field field, AnnotationMap annotationMap) {
        this._field = field;
        this._annotations = annotationMap;
    }

    public void addOrOverride(Annotation annotation) {
        this._annotations.add(annotation);
    }

    public Field getAnnotated() {
        return this._field;
    }

    public <A extends Annotation> A getAnnotation(Class<A> cls) {
        return this._annotations.get(cls);
    }

    public int getAnnotationCount() {
        return this._annotations.size();
    }

    public Class<?> getDeclaringClass() {
        return this._field.getDeclaringClass();
    }

    public String getFullName() {
        return getDeclaringClass().getName() + "#" + getName();
    }

    public Type getGenericType() {
        return this._field.getGenericType();
    }

    public Member getMember() {
        return this._field;
    }

    public int getModifiers() {
        return this._field.getModifiers();
    }

    public String getName() {
        return this._field.getName();
    }

    public Class<?> getRawType() {
        return this._field.getType();
    }

    public String toString() {
        return "[field " + getName() + ", annotations: " + this._annotations + "]";
    }
}
