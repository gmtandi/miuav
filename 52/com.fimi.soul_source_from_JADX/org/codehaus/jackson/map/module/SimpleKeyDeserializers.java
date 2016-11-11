package org.codehaus.jackson.map.module;

import java.util.HashMap;
import org.codehaus.jackson.map.BeanDescription;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.KeyDeserializer;
import org.codehaus.jackson.map.KeyDeserializers;
import org.codehaus.jackson.map.type.ClassKey;
import org.codehaus.jackson.type.JavaType;

public class SimpleKeyDeserializers implements KeyDeserializers {
    protected HashMap<ClassKey, KeyDeserializer> _classMappings;

    public SimpleKeyDeserializers() {
        this._classMappings = null;
    }

    public SimpleKeyDeserializers addDeserializer(Class<?> cls, KeyDeserializer keyDeserializer) {
        if (this._classMappings == null) {
            this._classMappings = new HashMap();
        }
        this._classMappings.put(new ClassKey(cls), keyDeserializer);
        return this;
    }

    public KeyDeserializer findKeyDeserializer(JavaType javaType, DeserializationConfig deserializationConfig, BeanDescription beanDescription, BeanProperty beanProperty) {
        return this._classMappings == null ? null : (KeyDeserializer) this._classMappings.get(new ClassKey(javaType.getRawClass()));
    }
}
