package org.codehaus.jackson.type;

import java.lang.reflect.Modifier;

public abstract class JavaType {
    protected final Class<?> _class;
    protected final int _hashCode;
    protected Object _typeHandler;
    protected Object _valueHandler;

    protected JavaType(Class<?> cls, int i) {
        this._class = cls;
        this._hashCode = cls.getName().hashCode() + i;
    }

    protected void _assertSubclass(Class<?> cls, Class<?> cls2) {
        if (!this._class.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Class " + cls.getName() + " is not assignable to " + this._class.getName());
        }
    }

    protected abstract JavaType _narrow(Class<?> cls);

    protected JavaType _widen(Class<?> cls) {
        return _narrow(cls);
    }

    public JavaType containedType(int i) {
        return null;
    }

    public int containedTypeCount() {
        return 0;
    }

    public String containedTypeName(int i) {
        return null;
    }

    public abstract boolean equals(Object obj);

    public final JavaType forcedNarrowBy(Class<?> cls) {
        if (cls == this._class) {
            return this;
        }
        JavaType _narrow = _narrow(cls);
        if (this._valueHandler != null) {
            _narrow.setValueHandler(this._valueHandler);
        }
        if (this._typeHandler != null) {
            _narrow = _narrow.withTypeHandler(this._typeHandler);
        }
        return _narrow;
    }

    public JavaType getContentType() {
        return null;
    }

    public String getErasedSignature() {
        StringBuilder stringBuilder = new StringBuilder(40);
        getErasedSignature(stringBuilder);
        return stringBuilder.toString();
    }

    public abstract StringBuilder getErasedSignature(StringBuilder stringBuilder);

    public String getGenericSignature() {
        StringBuilder stringBuilder = new StringBuilder(40);
        getGenericSignature(stringBuilder);
        return stringBuilder.toString();
    }

    public abstract StringBuilder getGenericSignature(StringBuilder stringBuilder);

    public JavaType getKeyType() {
        return null;
    }

    public final Class<?> getRawClass() {
        return this._class;
    }

    public <T> T getTypeHandler() {
        return this._typeHandler;
    }

    public <T> T getValueHandler() {
        return this._valueHandler;
    }

    public boolean hasGenericTypes() {
        return containedTypeCount() > 0;
    }

    public final boolean hasRawClass(Class<?> cls) {
        return this._class == cls;
    }

    public final int hashCode() {
        return this._hashCode;
    }

    public boolean isAbstract() {
        return Modifier.isAbstract(this._class.getModifiers());
    }

    public boolean isArrayType() {
        return false;
    }

    public boolean isCollectionLikeType() {
        return false;
    }

    public boolean isConcrete() {
        return (this._class.getModifiers() & 1536) == 0 || this._class.isPrimitive();
    }

    public abstract boolean isContainerType();

    public final boolean isEnumType() {
        return this._class.isEnum();
    }

    public final boolean isFinal() {
        return Modifier.isFinal(this._class.getModifiers());
    }

    public final boolean isInterface() {
        return this._class.isInterface();
    }

    public boolean isMapLikeType() {
        return false;
    }

    public final boolean isPrimitive() {
        return this._class.isPrimitive();
    }

    public boolean isThrowable() {
        return Throwable.class.isAssignableFrom(this._class);
    }

    public final JavaType narrowBy(Class<?> cls) {
        if (cls == this._class) {
            return this;
        }
        _assertSubclass(cls, this._class);
        JavaType _narrow = _narrow(cls);
        if (this._valueHandler != null) {
            _narrow.setValueHandler(this._valueHandler);
        }
        if (this._typeHandler != null) {
            _narrow = _narrow.withTypeHandler(this._typeHandler);
        }
        return _narrow;
    }

    public abstract JavaType narrowContentsBy(Class<?> cls);

    @Deprecated
    public void setTypeHandler(Object obj) {
        if (obj == null || this._typeHandler == null) {
            this._typeHandler = obj;
            return;
        }
        throw new IllegalStateException("Trying to reset type handler for type [" + toString() + "]; old handler of type " + this._typeHandler.getClass().getName() + ", new handler of type " + obj.getClass().getName());
    }

    public void setValueHandler(Object obj) {
        if (obj == null || this._valueHandler == null) {
            this._valueHandler = obj;
            return;
        }
        throw new IllegalStateException("Trying to reset value handler for type [" + toString() + "]; old handler of type " + this._valueHandler.getClass().getName() + ", new handler of type " + obj.getClass().getName());
    }

    public abstract String toCanonical();

    public abstract String toString();

    public final JavaType widenBy(Class<?> cls) {
        if (cls == this._class) {
            return this;
        }
        _assertSubclass(this._class, cls);
        return _widen(cls);
    }

    public abstract JavaType widenContentsBy(Class<?> cls);

    public abstract JavaType withContentTypeHandler(Object obj);

    public abstract JavaType withTypeHandler(Object obj);
}
