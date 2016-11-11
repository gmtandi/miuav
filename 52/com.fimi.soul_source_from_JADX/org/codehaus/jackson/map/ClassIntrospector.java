package org.codehaus.jackson.map;

import org.codehaus.jackson.type.JavaType;

public abstract class ClassIntrospector<T extends BeanDescription> {

    public interface MixInResolver {
        Class<?> findMixInClassFor(Class<?> cls);
    }

    protected ClassIntrospector() {
    }

    public abstract T forClassAnnotations(MapperConfig<?> mapperConfig, Class<?> cls, MixInResolver mixInResolver);

    public abstract T forCreation(DeserializationConfig deserializationConfig, JavaType javaType, MixInResolver mixInResolver);

    public abstract T forDeserialization(DeserializationConfig deserializationConfig, JavaType javaType, MixInResolver mixInResolver);

    public abstract T forDirectClassAnnotations(MapperConfig<?> mapperConfig, Class<?> cls, MixInResolver mixInResolver);

    public abstract T forSerialization(SerializationConfig serializationConfig, JavaType javaType, MixInResolver mixInResolver);
}
