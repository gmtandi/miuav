package org.codehaus.jackson.map.ser;

import org.codehaus.jackson.map.TypeSerializer;

public abstract class ContainerSerializerBase<T> extends SerializerBase<T> {
    protected ContainerSerializerBase(Class<T> cls) {
        super((Class) cls);
    }

    protected ContainerSerializerBase(Class<?> cls, boolean z) {
        super(cls, z);
    }

    public abstract ContainerSerializerBase<?> _withValueTypeSerializer(TypeSerializer typeSerializer);

    public ContainerSerializerBase<?> withValueTypeSerializer(TypeSerializer typeSerializer) {
        return typeSerializer == null ? this : _withValueTypeSerializer(typeSerializer);
    }
}
