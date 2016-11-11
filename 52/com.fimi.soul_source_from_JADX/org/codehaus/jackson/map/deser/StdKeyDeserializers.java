package org.codehaus.jackson.map.deser;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.KeyDeserializer;
import org.codehaus.jackson.map.introspect.BasicBeanDescription;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.type.JavaType;

class StdKeyDeserializers {
    final HashMap<JavaType, KeyDeserializer> _keyDeserializers;

    private StdKeyDeserializers() {
        this._keyDeserializers = new HashMap();
        add(new BoolKD());
        add(new ByteKD());
        add(new CharKD());
        add(new ShortKD());
        add(new IntKD());
        add(new LongKD());
        add(new FloatKD());
        add(new DoubleKD());
    }

    private void add(StdKeyDeserializer stdKeyDeserializer) {
        this._keyDeserializers.put(TypeFactory.defaultInstance().constructType(stdKeyDeserializer.getKeyClass()), stdKeyDeserializer);
    }

    public static HashMap<JavaType, KeyDeserializer> constructAll() {
        return new StdKeyDeserializers()._keyDeserializers;
    }

    public static KeyDeserializer constructEnumKeyDeserializer(DeserializationConfig deserializationConfig, JavaType javaType) {
        return new EnumKD(EnumResolver.constructUnsafe(javaType.getRawClass(), deserializationConfig.getAnnotationIntrospector()));
    }

    public static KeyDeserializer findStringBasedKeyDeserializer(DeserializationConfig deserializationConfig, JavaType javaType) {
        BasicBeanDescription basicBeanDescription = (BasicBeanDescription) deserializationConfig.introspect(javaType);
        Constructor findSingleArgConstructor = basicBeanDescription.findSingleArgConstructor(String.class);
        if (findSingleArgConstructor != null) {
            return new StringCtorKeyDeserializer(findSingleArgConstructor);
        }
        Method findFactoryMethod = basicBeanDescription.findFactoryMethod(String.class);
        return findFactoryMethod != null ? new StringFactoryKeyDeserializer(findFactoryMethod) : null;
    }
}
