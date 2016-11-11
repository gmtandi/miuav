package org.codehaus.jackson.map.type;

import java.util.Collection;
import org.codehaus.jackson.type.JavaType;

public class CollectionLikeType extends TypeBase {
    protected final JavaType _elementType;

    protected CollectionLikeType(Class<?> cls, JavaType javaType) {
        super(cls, javaType.hashCode());
        this._elementType = javaType;
    }

    public static CollectionLikeType construct(Class<?> cls, JavaType javaType) {
        return new CollectionLikeType(cls, javaType);
    }

    protected JavaType _narrow(Class<?> cls) {
        return new CollectionLikeType(cls, this._elementType);
    }

    protected String buildCanonicalName() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this._class.getName());
        if (this._elementType != null) {
            stringBuilder.append('<');
            stringBuilder.append(this._elementType.toCanonical());
            stringBuilder.append('>');
        }
        return stringBuilder.toString();
    }

    public JavaType containedType(int i) {
        return i == 0 ? this._elementType : null;
    }

    public int containedTypeCount() {
        return 1;
    }

    public String containedTypeName(int i) {
        return i == 0 ? "E" : null;
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
        CollectionLikeType collectionLikeType = (CollectionLikeType) obj;
        return this._class == collectionLikeType._class && this._elementType.equals(collectionLikeType._elementType);
    }

    public JavaType getContentType() {
        return this._elementType;
    }

    public StringBuilder getErasedSignature(StringBuilder stringBuilder) {
        return TypeBase._classSignature(this._class, stringBuilder, true);
    }

    public StringBuilder getGenericSignature(StringBuilder stringBuilder) {
        TypeBase._classSignature(this._class, stringBuilder, false);
        stringBuilder.append('<');
        this._elementType.getGenericSignature(stringBuilder);
        stringBuilder.append(">;");
        return stringBuilder;
    }

    public boolean isCollectionLikeType() {
        return true;
    }

    public boolean isContainerType() {
        return true;
    }

    public boolean isTrueCollectionType() {
        return Collection.class.isAssignableFrom(this._class);
    }

    public JavaType narrowContentsBy(Class<?> cls) {
        return cls == this._elementType.getRawClass() ? this : new CollectionLikeType(this._class, this._elementType.narrowBy(cls)).copyHandlers(this);
    }

    public String toString() {
        return "[collection-like type; class " + this._class.getName() + ", contains " + this._elementType + "]";
    }

    public JavaType widenContentsBy(Class<?> cls) {
        return cls == this._elementType.getRawClass() ? this : new CollectionLikeType(this._class, this._elementType.widenBy(cls)).copyHandlers(this);
    }

    public CollectionLikeType withContentTypeHandler(Object obj) {
        return new CollectionLikeType(this._class, this._elementType.withTypeHandler(obj));
    }

    public CollectionLikeType withTypeHandler(Object obj) {
        CollectionLikeType collectionLikeType = new CollectionLikeType(this._class, this._elementType);
        collectionLikeType._typeHandler = obj;
        return collectionLikeType;
    }
}
