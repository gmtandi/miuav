package org.codehaus.jackson.map.ser.impl;

import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.type.JavaType;

public abstract class PropertySerializerMap {

    final class Double extends PropertySerializerMap {
        private final JsonSerializer<Object> _serializer1;
        private final JsonSerializer<Object> _serializer2;
        private final Class<?> _type1;
        private final Class<?> _type2;

        public Double(Class<?> cls, JsonSerializer<Object> jsonSerializer, Class<?> cls2, JsonSerializer<Object> jsonSerializer2) {
            this._type1 = cls;
            this._serializer1 = jsonSerializer;
            this._type2 = cls2;
            this._serializer2 = jsonSerializer2;
        }

        protected PropertySerializerMap newWith(Class<?> cls, JsonSerializer<Object> jsonSerializer) {
            return new Multi(new TypeAndSerializer[]{new TypeAndSerializer(this._type1, this._serializer1), new TypeAndSerializer(this._type2, this._serializer2)});
        }

        public JsonSerializer<Object> serializerFor(Class<?> cls) {
            return cls == this._type1 ? this._serializer1 : cls == this._type2 ? this._serializer2 : null;
        }
    }

    final class Empty extends PropertySerializerMap {
        protected static final Empty instance;

        static {
            instance = new Empty();
        }

        private Empty() {
        }

        protected PropertySerializerMap newWith(Class<?> cls, JsonSerializer<Object> jsonSerializer) {
            return new Single(cls, jsonSerializer);
        }

        public JsonSerializer<Object> serializerFor(Class<?> cls) {
            return null;
        }
    }

    final class Multi extends PropertySerializerMap {
        private static final int MAX_ENTRIES = 8;
        private final TypeAndSerializer[] _entries;

        public Multi(TypeAndSerializer[] typeAndSerializerArr) {
            this._entries = typeAndSerializerArr;
        }

        protected PropertySerializerMap newWith(Class<?> cls, JsonSerializer<Object> jsonSerializer) {
            int length = this._entries.length;
            if (length == MAX_ENTRIES) {
                return this;
            }
            Object obj = new TypeAndSerializer[(length + 1)];
            System.arraycopy(this._entries, 0, obj, 0, length);
            obj[length] = new TypeAndSerializer(cls, jsonSerializer);
            return new Multi(obj);
        }

        public JsonSerializer<Object> serializerFor(Class<?> cls) {
            for (TypeAndSerializer typeAndSerializer : this._entries) {
                if (typeAndSerializer.type == cls) {
                    return typeAndSerializer.serializer;
                }
            }
            return null;
        }
    }

    public final class SerializerAndMapResult {
        public final PropertySerializerMap map;
        public final JsonSerializer<Object> serializer;

        public SerializerAndMapResult(JsonSerializer<Object> jsonSerializer, PropertySerializerMap propertySerializerMap) {
            this.serializer = jsonSerializer;
            this.map = propertySerializerMap;
        }
    }

    final class Single extends PropertySerializerMap {
        private final JsonSerializer<Object> _serializer;
        private final Class<?> _type;

        public Single(Class<?> cls, JsonSerializer<Object> jsonSerializer) {
            this._type = cls;
            this._serializer = jsonSerializer;
        }

        protected PropertySerializerMap newWith(Class<?> cls, JsonSerializer<Object> jsonSerializer) {
            return new Double(this._type, this._serializer, cls, jsonSerializer);
        }

        public JsonSerializer<Object> serializerFor(Class<?> cls) {
            return cls == this._type ? this._serializer : null;
        }
    }

    final class TypeAndSerializer {
        public final JsonSerializer<Object> serializer;
        public final Class<?> type;

        public TypeAndSerializer(Class<?> cls, JsonSerializer<Object> jsonSerializer) {
            this.type = cls;
            this.serializer = jsonSerializer;
        }
    }

    public static PropertySerializerMap emptyMap() {
        return Empty.instance;
    }

    public final SerializerAndMapResult findAndAddSerializer(Class<?> cls, SerializerProvider serializerProvider, BeanProperty beanProperty) {
        JsonSerializer findValueSerializer = serializerProvider.findValueSerializer((Class) cls, beanProperty);
        return new SerializerAndMapResult(findValueSerializer, newWith(cls, findValueSerializer));
    }

    public final SerializerAndMapResult findAndAddSerializer(JavaType javaType, SerializerProvider serializerProvider, BeanProperty beanProperty) {
        JsonSerializer findValueSerializer = serializerProvider.findValueSerializer(javaType, beanProperty);
        return new SerializerAndMapResult(findValueSerializer, newWith(javaType.getRawClass(), findValueSerializer));
    }

    protected abstract PropertySerializerMap newWith(Class<?> cls, JsonSerializer<Object> jsonSerializer);

    public abstract JsonSerializer<Object> serializerFor(Class<?> cls);
}
