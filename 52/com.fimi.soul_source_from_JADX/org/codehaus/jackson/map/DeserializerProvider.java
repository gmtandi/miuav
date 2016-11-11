package org.codehaus.jackson.map;

import org.codehaus.jackson.map.deser.BeanDeserializerModifier;
import org.codehaus.jackson.type.JavaType;

public abstract class DeserializerProvider {
    protected DeserializerProvider() {
    }

    public abstract int cachedDeserializersCount();

    @Deprecated
    public final KeyDeserializer findKeyDeserializer(DeserializationConfig deserializationConfig, JavaType javaType) {
        return findKeyDeserializer(deserializationConfig, javaType, null);
    }

    public abstract KeyDeserializer findKeyDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, BeanProperty beanProperty);

    @Deprecated
    public final JsonDeserializer<Object> findTypedValueDeserializer(DeserializationConfig deserializationConfig, JavaType javaType) {
        return findTypedValueDeserializer(deserializationConfig, javaType, null);
    }

    public abstract JsonDeserializer<Object> findTypedValueDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, BeanProperty beanProperty);

    public abstract JsonDeserializer<Object> findValueDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, BeanProperty beanProperty);

    @Deprecated
    public final JsonDeserializer<Object> findValueDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, JavaType javaType2, String str) {
        return findValueDeserializer(deserializationConfig, javaType, (BeanProperty) null);
    }

    public abstract void flushCachedDeserializers();

    public abstract boolean hasValueDeserializerFor(DeserializationConfig deserializationConfig, JavaType javaType);

    public abstract DeserializerProvider withAbstractTypeResolver(AbstractTypeResolver abstractTypeResolver);

    public abstract DeserializerProvider withAdditionalDeserializers(Deserializers deserializers);

    public abstract DeserializerProvider withAdditionalKeyDeserializers(KeyDeserializers keyDeserializers);

    public abstract DeserializerProvider withDeserializerModifier(BeanDeserializerModifier beanDeserializerModifier);
}
