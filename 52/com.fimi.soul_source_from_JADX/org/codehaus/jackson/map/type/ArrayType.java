package org.codehaus.jackson.map.type;

import java.lang.reflect.Array;
import org.codehaus.jackson.type.JavaType;

public final class ArrayType extends TypeBase {
    final JavaType _componentType;
    final Object _emptyArray;

    private ArrayType(JavaType javaType, Object obj) {
        super(obj.getClass(), javaType.hashCode());
        this._componentType = javaType;
        this._emptyArray = obj;
    }

    public static ArrayType construct(JavaType javaType) {
        return new ArrayType(javaType, Array.newInstance(javaType.getRawClass(), 0));
    }

    protected JavaType _narrow(Class<?> cls) {
        if (cls.isArray()) {
            return construct(TypeFactory.defaultInstance().constructType(cls.getComponentType()));
        }
        throw new IllegalArgumentException("Incompatible narrowing operation: trying to narrow " + toString() + " to class " + cls.getName());
    }

    protected String buildCanonicalName() {
        return this._class.getName();
    }

    public JavaType containedType(int i) {
        return i == 0 ? this._componentType : null;
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
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        return this._componentType.equals(((ArrayType) obj)._componentType);
    }

    public JavaType getContentType() {
        return this._componentType;
    }

    public StringBuilder getErasedSignature(StringBuilder stringBuilder) {
        stringBuilder.append('[');
        return this._componentType.getErasedSignature(stringBuilder);
    }

    public StringBuilder getGenericSignature(StringBuilder stringBuilder) {
        stringBuilder.append('[');
        return this._componentType.getGenericSignature(stringBuilder);
    }

    public boolean hasGenericTypes() {
        return this._componentType.hasGenericTypes();
    }

    public boolean isAbstract() {
        return false;
    }

    public boolean isArrayType() {
        return true;
    }

    public boolean isConcrete() {
        return true;
    }

    public boolean isContainerType() {
        return true;
    }

    public JavaType narrowContentsBy(Class<?> cls) {
        return cls == this._componentType.getRawClass() ? this : construct(this._componentType.narrowBy(cls)).copyHandlers(this);
    }

    public String toString() {
        return "[array type, component type: " + this._componentType + "]";
    }

    public JavaType widenContentsBy(Class<?> cls) {
        return cls == this._componentType.getRawClass() ? this : construct(this._componentType.widenBy(cls)).copyHandlers(this);
    }

    public ArrayType withContentTypeHandler(Object obj) {
        return new ArrayType(this._componentType.withTypeHandler(obj), this._emptyArray);
    }

    public ArrayType withTypeHandler(Object obj) {
        ArrayType arrayType = new ArrayType(this._componentType, this._emptyArray);
        arrayType._typeHandler = obj;
        return arrayType;
    }
}
