package org.codehaus.jackson.map.deser;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.introspect.BasicBeanDescription;

public abstract class BeanDeserializerModifier {
    public JsonDeserializer<?> modifyDeserializer(DeserializationConfig deserializationConfig, BasicBeanDescription basicBeanDescription, JsonDeserializer<?> jsonDeserializer) {
        return jsonDeserializer;
    }

    public BeanDeserializerBuilder updateBuilder(DeserializationConfig deserializationConfig, BasicBeanDescription basicBeanDescription, BeanDeserializerBuilder beanDeserializerBuilder) {
        return beanDeserializerBuilder;
    }
}
