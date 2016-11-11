package org.codehaus.jackson.map;

import java.util.Collection;
import java.util.LinkedHashMap;
import org.codehaus.jackson.map.introspect.AnnotatedMethod;
import org.codehaus.jackson.map.introspect.VisibilityChecker;
import org.codehaus.jackson.map.type.TypeBindings;
import org.codehaus.jackson.map.util.Annotations;
import org.codehaus.jackson.type.JavaType;

public abstract class BeanDescription {
    protected final JavaType _type;

    protected BeanDescription(JavaType javaType) {
        this._type = javaType;
    }

    public abstract TypeBindings bindingsForBeanType();

    public abstract LinkedHashMap<String, AnnotatedMethod> findGetters(VisibilityChecker<?> visibilityChecker, Collection<String> collection);

    public abstract LinkedHashMap<String, AnnotatedMethod> findSetters(VisibilityChecker<?> visibilityChecker);

    public Class<?> getBeanClass() {
        return this._type.getRawClass();
    }

    public abstract Annotations getClassAnnotations();

    public JavaType getType() {
        return this._type;
    }

    public abstract boolean hasKnownClassAnnotations();
}
