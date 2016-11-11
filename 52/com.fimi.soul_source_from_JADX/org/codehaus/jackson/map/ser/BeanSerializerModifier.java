package org.codehaus.jackson.map.ser;

import java.util.List;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.introspect.BasicBeanDescription;

public abstract class BeanSerializerModifier {
    public List<BeanPropertyWriter> changeProperties(SerializationConfig serializationConfig, BasicBeanDescription basicBeanDescription, List<BeanPropertyWriter> list) {
        return list;
    }

    public JsonSerializer<?> modifySerializer(SerializationConfig serializationConfig, BasicBeanDescription basicBeanDescription, JsonSerializer<?> jsonSerializer) {
        return jsonSerializer;
    }

    public List<BeanPropertyWriter> orderProperties(SerializationConfig serializationConfig, BasicBeanDescription basicBeanDescription, List<BeanPropertyWriter> list) {
        return list;
    }

    public BeanSerializerBuilder updateBuilder(SerializationConfig serializationConfig, BasicBeanDescription basicBeanDescription, BeanSerializerBuilder beanSerializerBuilder) {
        return beanSerializerBuilder;
    }
}
