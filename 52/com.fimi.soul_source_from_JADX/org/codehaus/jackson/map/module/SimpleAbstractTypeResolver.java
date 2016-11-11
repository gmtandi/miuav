package org.codehaus.jackson.map.module;

import java.lang.reflect.Modifier;
import java.util.HashMap;
import org.codehaus.jackson.map.AbstractTypeResolver;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.type.ClassKey;
import org.codehaus.jackson.type.JavaType;

public class SimpleAbstractTypeResolver extends AbstractTypeResolver {
    protected final HashMap<ClassKey, Class<?>> _mappings;

    public SimpleAbstractTypeResolver() {
        this._mappings = new HashMap();
    }

    public <T> SimpleAbstractTypeResolver addMapping(Class<T> cls, Class<? extends T> cls2) {
        if (cls == cls2) {
            throw new IllegalArgumentException("Can not add mapping from class to itself");
        } else if (!cls.isAssignableFrom(cls2)) {
            throw new IllegalArgumentException("Can not add mapping from class " + cls.getName() + " to " + cls2.getName() + ", as latter is not a subtype of former");
        } else if (Modifier.isAbstract(cls.getModifiers())) {
            this._mappings.put(new ClassKey(cls), cls2);
            return this;
        } else {
            throw new IllegalArgumentException("Can not add mapping from class " + cls.getName() + " since it is not abstract");
        }
    }

    public JavaType findTypeMapping(DeserializationConfig deserializationConfig, JavaType javaType) {
        Class cls = (Class) this._mappings.get(new ClassKey(javaType.getRawClass()));
        return cls == null ? null : javaType.narrowBy(cls);
    }

    public JavaType resolveAbstractType(DeserializationConfig deserializationConfig, JavaType javaType) {
        return null;
    }
}
