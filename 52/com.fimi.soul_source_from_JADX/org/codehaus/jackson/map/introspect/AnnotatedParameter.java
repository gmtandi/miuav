package org.codehaus.jackson.map.introspect;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Member;
import java.lang.reflect.Type;
import org.codehaus.jackson.map.type.TypeFactory;
import org.p122a.p123a.C2915a;

public final class AnnotatedParameter extends AnnotatedMember {
    protected final AnnotationMap _annotations;
    protected final AnnotatedMember _owner;
    protected final Type _type;

    public AnnotatedParameter(AnnotatedMember annotatedMember, Type type, AnnotationMap annotationMap) {
        this._owner = annotatedMember;
        this._type = type;
        this._annotations = annotationMap;
    }

    public void addOrOverride(Annotation annotation) {
        this._annotations.add(annotation);
    }

    public AnnotatedElement getAnnotated() {
        return null;
    }

    public <A extends Annotation> A getAnnotation(Class<A> cls) {
        return this._annotations.get(cls);
    }

    public Class<?> getDeclaringClass() {
        return this._owner.getDeclaringClass();
    }

    public Type getGenericType() {
        return this._type;
    }

    public Member getMember() {
        return this._owner.getMember();
    }

    public int getModifiers() {
        return this._owner.getModifiers();
    }

    public String getName() {
        return C2915a.f14760f;
    }

    public Type getParameterType() {
        return this._type;
    }

    public Class<?> getRawType() {
        return this._type instanceof Class ? (Class) this._type : TypeFactory.defaultInstance().constructType(this._type).getRawClass();
    }
}
