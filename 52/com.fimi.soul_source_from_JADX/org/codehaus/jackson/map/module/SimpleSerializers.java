package org.codehaus.jackson.map.module;

import java.util.HashMap;
import org.codehaus.jackson.map.BeanDescription;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.Serializers;
import org.codehaus.jackson.map.TypeSerializer;
import org.codehaus.jackson.map.type.ArrayType;
import org.codehaus.jackson.map.type.ClassKey;
import org.codehaus.jackson.map.type.CollectionLikeType;
import org.codehaus.jackson.map.type.CollectionType;
import org.codehaus.jackson.map.type.MapLikeType;
import org.codehaus.jackson.map.type.MapType;
import org.codehaus.jackson.type.JavaType;

public class SimpleSerializers implements Serializers {
    protected HashMap<ClassKey, JsonSerializer<?>> _classMappings;
    protected HashMap<ClassKey, JsonSerializer<?>> _interfaceMappings;

    public SimpleSerializers() {
        this._classMappings = null;
        this._interfaceMappings = null;
    }

    private void _addSerializer(Class<?> cls, JsonSerializer<?> jsonSerializer) {
        ClassKey classKey = new ClassKey(cls);
        if (cls.isInterface()) {
            if (this._interfaceMappings == null) {
                this._interfaceMappings = new HashMap();
            }
            this._interfaceMappings.put(classKey, jsonSerializer);
            return;
        }
        if (this._classMappings == null) {
            this._classMappings = new HashMap();
        }
        this._classMappings.put(classKey, jsonSerializer);
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

    public <T> void addSerializer(Class<? extends T> cls, JsonSerializer<T> jsonSerializer) {
        _addSerializer(cls, jsonSerializer);
    }

    public void addSerializer(JsonSerializer<?> jsonSerializer) {
        Class handledType = jsonSerializer.handledType();
        if (handledType == null || handledType == Object.class) {
            throw new IllegalArgumentException("JsonSerializer of type " + jsonSerializer.getClass().getName() + " does not define valid handledType() (use alternative registration method?)");
        }
        _addSerializer(handledType, jsonSerializer);
    }

    public JsonSerializer<?> findArraySerializer(SerializationConfig serializationConfig, ArrayType arrayType, BeanDescription beanDescription, BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) {
        return findSerializer(serializationConfig, arrayType, beanDescription, beanProperty);
    }

    public JsonSerializer<?> findCollectionLikeSerializer(SerializationConfig serializationConfig, CollectionLikeType collectionLikeType, BeanDescription beanDescription, BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) {
        return findSerializer(serializationConfig, collectionLikeType, beanDescription, beanProperty);
    }

    public JsonSerializer<?> findCollectionSerializer(SerializationConfig serializationConfig, CollectionType collectionType, BeanDescription beanDescription, BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) {
        return findSerializer(serializationConfig, collectionType, beanDescription, beanProperty);
    }

    public JsonSerializer<?> findMapLikeSerializer(SerializationConfig serializationConfig, MapLikeType mapLikeType, BeanDescription beanDescription, BeanProperty beanProperty, JsonSerializer<Object> jsonSerializer, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer2) {
        return findSerializer(serializationConfig, mapLikeType, beanDescription, beanProperty);
    }

    public JsonSerializer<?> findMapSerializer(SerializationConfig serializationConfig, MapType mapType, BeanDescription beanDescription, BeanProperty beanProperty, JsonSerializer<Object> jsonSerializer, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer2) {
        return findSerializer(serializationConfig, mapType, beanDescription, beanProperty);
    }

    public JsonSerializer<?> findSerializer(SerializationConfig serializationConfig, JavaType javaType, BeanDescription beanDescription, BeanProperty beanProperty) {
        JsonSerializer<?> jsonSerializer;
        Class rawClass = javaType.getRawClass();
        ClassKey classKey = new ClassKey(rawClass);
        if (rawClass.isInterface()) {
            if (this._interfaceMappings != null) {
                jsonSerializer = (JsonSerializer) this._interfaceMappings.get(classKey);
                if (jsonSerializer != null) {
                    return jsonSerializer;
                }
            }
        } else if (this._classMappings != null) {
            jsonSerializer = (JsonSerializer) this._classMappings.get(classKey);
            if (jsonSerializer != null) {
                return jsonSerializer;
            }
            for (Class cls = rawClass; cls != null; cls = cls.getSuperclass()) {
                classKey.reset(cls);
                jsonSerializer = (JsonSerializer) this._classMappings.get(classKey);
                if (jsonSerializer != null) {
                    return jsonSerializer;
                }
            }
        }
        if (this._interfaceMappings != null) {
            jsonSerializer = _findInterfaceMapping(rawClass, classKey);
            if (jsonSerializer != null) {
                return jsonSerializer;
            }
            if (!rawClass.isInterface()) {
                JsonSerializer<?> _findInterfaceMapping;
                Class cls2 = rawClass;
                do {
                    cls2 = cls2.getSuperclass();
                    if (cls2 != null) {
                        _findInterfaceMapping = _findInterfaceMapping(cls2, classKey);
                    }
                } while (_findInterfaceMapping == null);
                return _findInterfaceMapping;
            }
        }
        return null;
    }
}
