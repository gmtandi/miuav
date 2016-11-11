package org.codehaus.jackson.map.ser;

import java.lang.reflect.Modifier;
import java.util.HashMap;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.SerializerFactory;
import org.codehaus.jackson.map.SerializerFactory.Config;
import org.codehaus.jackson.map.type.ClassKey;
import org.codehaus.jackson.type.JavaType;

public class CustomSerializerFactory extends BeanSerializerFactory {
    protected HashMap<ClassKey, JsonSerializer<?>> _directClassMappings;
    protected JsonSerializer<?> _enumSerializerOverride;
    protected HashMap<ClassKey, JsonSerializer<?>> _interfaceMappings;
    protected HashMap<ClassKey, JsonSerializer<?>> _transitiveClassMappings;

    public CustomSerializerFactory() {
        this(null);
    }

    public CustomSerializerFactory(Config config) {
        super(config);
        this._directClassMappings = null;
        this._transitiveClassMappings = null;
        this._interfaceMappings = null;
    }

    protected JsonSerializer<?> _findInterfaceMapping(Class<?> cls, ClassKey classKey) {
        for (Class cls2 : cls.getInterfaces()) {
            classKey.reset(cls2);
            JsonSerializer<?> jsonSerializer = (JsonSerializer) this._interfaceMappings.get(classKey);
            if (jsonSerializer != null) {
                return jsonSerializer;
            }
            jsonSerializer = _findInterfaceMapping(cls2, classKey);
            if (jsonSerializer != null) {
                return jsonSerializer;
            }
        }
        return null;
    }

    public <T> void addGenericMapping(Class<? extends T> cls, JsonSerializer<T> jsonSerializer) {
        ClassKey classKey = new ClassKey(cls);
        if (cls.isInterface()) {
            if (this._interfaceMappings == null) {
                this._interfaceMappings = new HashMap();
            }
            this._interfaceMappings.put(classKey, jsonSerializer);
            return;
        }
        if (this._transitiveClassMappings == null) {
            this._transitiveClassMappings = new HashMap();
        }
        this._transitiveClassMappings.put(classKey, jsonSerializer);
    }

    public <T> void addSpecificMapping(Class<? extends T> cls, JsonSerializer<T> jsonSerializer) {
        ClassKey classKey = new ClassKey(cls);
        if (cls.isInterface()) {
            throw new IllegalArgumentException("Can not add specific mapping for an interface (" + cls.getName() + ")");
        } else if (Modifier.isAbstract(cls.getModifiers())) {
            throw new IllegalArgumentException("Can not add specific mapping for an abstract class (" + cls.getName() + ")");
        } else {
            if (this._directClassMappings == null) {
                this._directClassMappings = new HashMap();
            }
            this._directClassMappings.put(classKey, jsonSerializer);
        }
    }

    public JsonSerializer<Object> createSerializer(SerializationConfig serializationConfig, JavaType javaType, BeanProperty beanProperty) {
        JsonSerializer<Object> findCustomSerializer = findCustomSerializer(javaType.getRawClass(), serializationConfig);
        return findCustomSerializer != null ? findCustomSerializer : super.createSerializer(serializationConfig, javaType, beanProperty);
    }

    protected JsonSerializer<?> findCustomSerializer(Class<?> cls, SerializationConfig serializationConfig) {
        JsonSerializer<?> jsonSerializer;
        ClassKey classKey = new ClassKey(cls);
        if (this._directClassMappings != null) {
            jsonSerializer = (JsonSerializer) this._directClassMappings.get(classKey);
            if (jsonSerializer != null) {
                return jsonSerializer;
            }
        }
        if (cls.isEnum() && this._enumSerializerOverride != null) {
            return this._enumSerializerOverride;
        }
        if (this._transitiveClassMappings != null) {
            for (Class cls2 = cls; cls2 != null; cls2 = cls2.getSuperclass()) {
                classKey.reset(cls2);
                jsonSerializer = (JsonSerializer) this._transitiveClassMappings.get(classKey);
                if (jsonSerializer != null) {
                    return jsonSerializer;
                }
            }
        }
        if (this._interfaceMappings != null) {
            classKey.reset(cls);
            jsonSerializer = (JsonSerializer) this._interfaceMappings.get(classKey);
            if (jsonSerializer != null) {
                return jsonSerializer;
            }
            Class superclass;
            while (superclass != null) {
                jsonSerializer = _findInterfaceMapping(superclass, classKey);
                if (jsonSerializer != null) {
                    return jsonSerializer;
                }
                superclass = superclass.getSuperclass();
            }
        }
        return null;
    }

    public void setEnumSerializer(JsonSerializer<?> jsonSerializer) {
        this._enumSerializerOverride = jsonSerializer;
    }

    public SerializerFactory withConfig(Config config) {
        if (getClass() == CustomSerializerFactory.class) {
            return new CustomSerializerFactory(config);
        }
        throw new IllegalStateException("Subtype of CustomSerializerFactory (" + getClass().getName() + ") has not properly overridden method 'withAdditionalSerializers': can not instantiate subtype with " + "additional serializer definitions");
    }
}
