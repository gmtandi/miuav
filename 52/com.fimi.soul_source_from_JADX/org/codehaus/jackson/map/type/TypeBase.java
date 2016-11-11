package org.codehaus.jackson.map.type;

import org.codehaus.jackson.type.JavaType;

public abstract class TypeBase extends JavaType {
    volatile String _canonicalName;

    protected TypeBase(Class<?> cls, int i) {
        super(cls, i);
    }

    protected static StringBuilder _classSignature(Class<?> cls, StringBuilder stringBuilder, boolean z) {
        if (!cls.isPrimitive()) {
            stringBuilder.append('L');
            String name = cls.getName();
            int length = name.length();
            for (int i = 0; i < length; i++) {
                char charAt = name.charAt(i);
                if (charAt == '.') {
                    charAt = '/';
                }
                stringBuilder.append(charAt);
            }
            if (z) {
                stringBuilder.append(';');
            }
        } else if (cls == Boolean.TYPE) {
            stringBuilder.append('Z');
        } else if (cls == Byte.TYPE) {
            stringBuilder.append('B');
        } else if (cls == Short.TYPE) {
            stringBuilder.append('S');
        } else if (cls == Character.TYPE) {
            stringBuilder.append('C');
        } else if (cls == Integer.TYPE) {
            stringBuilder.append('I');
        } else if (cls == Long.TYPE) {
            stringBuilder.append('J');
        } else if (cls == Float.TYPE) {
            stringBuilder.append('F');
        } else if (cls == Double.TYPE) {
            stringBuilder.append('D');
        } else if (cls == Void.TYPE) {
            stringBuilder.append('V');
        } else {
            throw new IllegalStateException("Unrecognized primitive type: " + cls.getName());
        }
        return stringBuilder;
    }

    protected abstract String buildCanonicalName();

    protected final JavaType copyHandlers(JavaType javaType) {
        this._valueHandler = javaType.getValueHandler();
        this._typeHandler = javaType.getTypeHandler();
        return this;
    }

    public abstract StringBuilder getErasedSignature(StringBuilder stringBuilder);

    public abstract StringBuilder getGenericSignature(StringBuilder stringBuilder);

    public String toCanonical() {
        String str = this._canonicalName;
        return str == null ? buildCanonicalName() : str;
    }
}
