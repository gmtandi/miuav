package org.codehaus.jackson.map.type;

import org.codehaus.jackson.type.JavaType;

public final class MapType extends MapLikeType {
    private MapType(Class<?> cls, JavaType javaType, JavaType javaType2) {
        super(cls, javaType, javaType2);
    }

    public static MapType construct(Class<?> cls, JavaType javaType, JavaType javaType2) {
        return new MapType(cls, javaType, javaType2);
    }

    protected JavaType _narrow(Class<?> cls) {
        return new MapType(cls, this._keyType, this._valueType);
    }

    public JavaType narrowContentsBy(Class<?> cls) {
        return cls == this._valueType.getRawClass() ? this : new MapType(this._class, this._keyType, this._valueType.narrowBy(cls)).copyHandlers(this);
    }

    public JavaType narrowKey(Class<?> cls) {
        return cls == this._keyType.getRawClass() ? this : new MapType(this._class, this._keyType.narrowBy(cls), this._valueType).copyHandlers(this);
    }

    public String toString() {
        return "[map type; class " + this._class.getName() + ", " + this._keyType + " -> " + this._valueType + "]";
    }

    public JavaType widenContentsBy(Class<?> cls) {
        return cls == this._valueType.getRawClass() ? this : new MapType(this._class, this._keyType, this._valueType.widenBy(cls)).copyHandlers(this);
    }

    public JavaType widenKey(Class<?> cls) {
        return cls == this._keyType.getRawClass() ? this : new MapType(this._class, this._keyType.widenBy(cls), this._valueType).copyHandlers(this);
    }

    public MapType withContentTypeHandler(Object obj) {
        return new MapType(this._class, this._keyType, this._valueType.withTypeHandler(obj));
    }

    public MapType withTypeHandler(Object obj) {
        MapType mapType = new MapType(this._class, this._keyType, this._valueType);
        mapType._typeHandler = obj;
        return mapType;
    }
}
