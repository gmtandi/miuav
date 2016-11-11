package org.codehaus.jackson.map.type;

import java.util.Map;
import org.codehaus.jackson.type.JavaType;

public class MapLikeType extends TypeBase {
    protected final JavaType _keyType;
    protected final JavaType _valueType;

    protected MapLikeType(Class<?> cls, JavaType javaType, JavaType javaType2) {
        super(cls, javaType.hashCode() ^ javaType2.hashCode());
        this._keyType = javaType;
        this._valueType = javaType2;
    }

    public static MapLikeType construct(Class<?> cls, JavaType javaType, JavaType javaType2) {
        return new MapLikeType(cls, javaType, javaType2);
    }

    protected JavaType _narrow(Class<?> cls) {
        return new MapLikeType(cls, this._keyType, this._valueType);
    }

    protected String buildCanonicalName() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this._class.getName());
        if (this._keyType != null) {
            stringBuilder.append('<');
            stringBuilder.append(this._keyType.toCanonical());
            stringBuilder.append(',');
            stringBuilder.append(this._valueType.toCanonical());
            stringBuilder.append('>');
        }
        return stringBuilder.toString();
    }

    public JavaType containedType(int i) {
        return i == 0 ? this._keyType : i == 1 ? this._valueType : null;
    }

    public int containedTypeCount() {
        return 2;
    }

    public String containedTypeName(int i) {
        return i == 0 ? "K" : i == 1 ? "V" : null;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        MapLikeType mapLikeType = (MapLikeType) obj;
        return this._class == mapLikeType._class && this._keyType.equals(mapLikeType._keyType) && this._valueType.equals(mapLikeType._valueType);
    }

    public JavaType getContentType() {
        return this._valueType;
    }

    public StringBuilder getErasedSignature(StringBuilder stringBuilder) {
        return TypeBase._classSignature(this._class, stringBuilder, true);
    }

    public StringBuilder getGenericSignature(StringBuilder stringBuilder) {
        TypeBase._classSignature(this._class, stringBuilder, false);
        stringBuilder.append('<');
        this._keyType.getGenericSignature(stringBuilder);
        this._valueType.getGenericSignature(stringBuilder);
        stringBuilder.append(">;");
        return stringBuilder;
    }

    public JavaType getKeyType() {
        return this._keyType;
    }

    public boolean isContainerType() {
        return true;
    }

    public boolean isMapLikeType() {
        return true;
    }

    public boolean isTrueMapType() {
        return Map.class.isAssignableFrom(this._class);
    }

    public JavaType narrowContentsBy(Class<?> cls) {
        return cls == this._valueType.getRawClass() ? this : new MapLikeType(this._class, this._keyType, this._valueType.narrowBy(cls)).copyHandlers(this);
    }

    public JavaType narrowKey(Class<?> cls) {
        return cls == this._keyType.getRawClass() ? this : new MapLikeType(this._class, this._keyType.narrowBy(cls), this._valueType).copyHandlers(this);
    }

    public String toString() {
        return "[map-like type; class " + this._class.getName() + ", " + this._keyType + " -> " + this._valueType + "]";
    }

    public JavaType widenContentsBy(Class<?> cls) {
        return cls == this._valueType.getRawClass() ? this : new MapLikeType(this._class, this._keyType, this._valueType.widenBy(cls)).copyHandlers(this);
    }

    public JavaType widenKey(Class<?> cls) {
        return cls == this._keyType.getRawClass() ? this : new MapLikeType(this._class, this._keyType.widenBy(cls), this._valueType).copyHandlers(this);
    }

    public MapLikeType withContentTypeHandler(Object obj) {
        return new MapLikeType(this._class, this._keyType, this._valueType.withTypeHandler(obj));
    }

    public MapLikeType withTypeHandler(Object obj) {
        MapLikeType mapLikeType = new MapLikeType(this._class, this._keyType, this._valueType);
        mapLikeType._typeHandler = obj;
        return mapLikeType;
    }
}
