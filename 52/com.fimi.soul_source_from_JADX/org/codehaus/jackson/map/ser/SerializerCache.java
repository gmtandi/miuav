package org.codehaus.jackson.map.ser;

import java.util.HashMap;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ResolvableSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.ser.impl.ReadOnlyClassToSerializerMap;
import org.codehaus.jackson.type.JavaType;

public final class SerializerCache {
    private ReadOnlyClassToSerializerMap _readOnlyMap;
    private HashMap<TypeKey, JsonSerializer<Object>> _sharedMap;

    public final class TypeKey {
        protected Class<?> _class;
        protected int _hashCode;
        protected boolean _isTyped;
        protected JavaType _type;

        public TypeKey(Class<?> cls, boolean z) {
            this._class = cls;
            this._type = null;
            this._isTyped = z;
            this._hashCode = hash((Class) cls, z);
        }

        public TypeKey(JavaType javaType, boolean z) {
            this._type = javaType;
            this._class = null;
            this._isTyped = z;
            this._hashCode = hash(javaType, z);
        }

        private static final int hash(Class<?> cls, boolean z) {
            int hashCode = cls.getName().hashCode();
            return z ? hashCode + 1 : hashCode;
        }

        private static final int hash(JavaType javaType, boolean z) {
            int hashCode = javaType.hashCode() - 1;
            return z ? hashCode - 1 : hashCode;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            TypeKey typeKey = (TypeKey) obj;
            return typeKey._isTyped == this._isTyped ? this._class != null ? typeKey._class == this._class : this._type.equals(typeKey._type) : false;
        }

        public final int hashCode() {
            return this._hashCode;
        }

        public void resetTyped(Class<?> cls) {
            this._type = null;
            this._class = cls;
            this._isTyped = true;
            this._hashCode = hash((Class) cls, true);
        }

        public void resetTyped(JavaType javaType) {
            this._type = javaType;
            this._class = null;
            this._isTyped = true;
            this._hashCode = hash(javaType, true);
        }

        public void resetUntyped(Class<?> cls) {
            this._type = null;
            this._class = cls;
            this._isTyped = false;
            this._hashCode = hash((Class) cls, false);
        }

        public void resetUntyped(JavaType javaType) {
            this._type = javaType;
            this._class = null;
            this._isTyped = false;
            this._hashCode = hash(javaType, false);
        }

        public final String toString() {
            return this._class != null ? "{class: " + this._class.getName() + ", typed? " + this._isTyped + "}" : "{type: " + this._type + ", typed? " + this._isTyped + "}";
        }
    }

    public SerializerCache() {
        this._sharedMap = new HashMap(64);
        this._readOnlyMap = null;
    }

    public void addAndResolveNonTypedSerializer(Class<?> cls, JsonSerializer<Object> jsonSerializer, SerializerProvider serializerProvider) {
        synchronized (this) {
            if (this._sharedMap.put(new TypeKey((Class) cls, false), jsonSerializer) == null) {
                this._readOnlyMap = null;
            }
            if (jsonSerializer instanceof ResolvableSerializer) {
                ((ResolvableSerializer) jsonSerializer).resolve(serializerProvider);
            }
        }
    }

    public void addAndResolveNonTypedSerializer(JavaType javaType, JsonSerializer<Object> jsonSerializer, SerializerProvider serializerProvider) {
        synchronized (this) {
            if (this._sharedMap.put(new TypeKey(javaType, false), jsonSerializer) == null) {
                this._readOnlyMap = null;
            }
            if (jsonSerializer instanceof ResolvableSerializer) {
                ((ResolvableSerializer) jsonSerializer).resolve(serializerProvider);
            }
        }
    }

    public void addTypedSerializer(Class<?> cls, JsonSerializer<Object> jsonSerializer) {
        synchronized (this) {
            if (this._sharedMap.put(new TypeKey((Class) cls, true), jsonSerializer) == null) {
                this._readOnlyMap = null;
            }
        }
    }

    public void addTypedSerializer(JavaType javaType, JsonSerializer<Object> jsonSerializer) {
        synchronized (this) {
            if (this._sharedMap.put(new TypeKey(javaType, true), jsonSerializer) == null) {
                this._readOnlyMap = null;
            }
        }
    }

    public synchronized void flush() {
        this._sharedMap.clear();
    }

    public ReadOnlyClassToSerializerMap getReadOnlyLookupMap() {
        ReadOnlyClassToSerializerMap readOnlyClassToSerializerMap;
        synchronized (this) {
            readOnlyClassToSerializerMap = this._readOnlyMap;
            if (readOnlyClassToSerializerMap == null) {
                readOnlyClassToSerializerMap = ReadOnlyClassToSerializerMap.from(this._sharedMap);
                this._readOnlyMap = readOnlyClassToSerializerMap;
            }
        }
        return readOnlyClassToSerializerMap.instance();
    }

    public synchronized int size() {
        return this._sharedMap.size();
    }

    public JsonSerializer<Object> typedValueSerializer(Class<?> cls) {
        JsonSerializer<Object> jsonSerializer;
        synchronized (this) {
            jsonSerializer = (JsonSerializer) this._sharedMap.get(new TypeKey((Class) cls, true));
        }
        return jsonSerializer;
    }

    public JsonSerializer<Object> typedValueSerializer(JavaType javaType) {
        JsonSerializer<Object> jsonSerializer;
        synchronized (this) {
            jsonSerializer = (JsonSerializer) this._sharedMap.get(new TypeKey(javaType, true));
        }
        return jsonSerializer;
    }

    public JsonSerializer<Object> untypedValueSerializer(Class<?> cls) {
        JsonSerializer<Object> jsonSerializer;
        synchronized (this) {
            jsonSerializer = (JsonSerializer) this._sharedMap.get(new TypeKey((Class) cls, false));
        }
        return jsonSerializer;
    }

    public JsonSerializer<Object> untypedValueSerializer(JavaType javaType) {
        JsonSerializer<Object> jsonSerializer;
        synchronized (this) {
            jsonSerializer = (JsonSerializer) this._sharedMap.get(new TypeKey(javaType, false));
        }
        return jsonSerializer;
    }
}
