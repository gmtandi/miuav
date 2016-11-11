package org.codehaus.jackson.map.deser;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.codehaus.jackson.io.NumberInput;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.KeyDeserializer;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

public abstract class StdKeyDeserializer extends KeyDeserializer {
    protected final Class<?> _keyClass;

    final class BoolKD extends StdKeyDeserializer {
        BoolKD() {
            super(Boolean.class);
        }

        public Boolean _parse(String str, DeserializationContext deserializationContext) {
            if ("true".equals(str)) {
                return Boolean.TRUE;
            }
            if ("false".equals(str)) {
                return Boolean.FALSE;
            }
            throw deserializationContext.weirdKeyException(this._keyClass, str, "value not 'true' or 'false'");
        }
    }

    final class ByteKD extends StdKeyDeserializer {
        ByteKD() {
            super(Byte.class);
        }

        public Byte _parse(String str, DeserializationContext deserializationContext) {
            int _parseInt = _parseInt(str);
            if (_parseInt >= -128 && _parseInt <= Opcodes.LAND) {
                return Byte.valueOf((byte) _parseInt);
            }
            throw deserializationContext.weirdKeyException(this._keyClass, str, "overflow, value can not be represented as 8-bit value");
        }
    }

    final class CharKD extends StdKeyDeserializer {
        CharKD() {
            super(Character.class);
        }

        public Character _parse(String str, DeserializationContext deserializationContext) {
            if (str.length() == 1) {
                return Character.valueOf(str.charAt(0));
            }
            throw deserializationContext.weirdKeyException(this._keyClass, str, "can only convert 1-character Strings");
        }
    }

    final class DoubleKD extends StdKeyDeserializer {
        DoubleKD() {
            super(Double.class);
        }

        public Double _parse(String str, DeserializationContext deserializationContext) {
            return Double.valueOf(_parseDouble(str));
        }
    }

    final class EnumKD extends StdKeyDeserializer {
        final EnumResolver<?> _resolver;

        EnumKD(EnumResolver<?> enumResolver) {
            super(enumResolver.getEnumClass());
            this._resolver = enumResolver;
        }

        public Enum<?> _parse(String str, DeserializationContext deserializationContext) {
            Enum<?> findEnum = this._resolver.findEnum(str);
            if (findEnum != null) {
                return findEnum;
            }
            throw deserializationContext.weirdKeyException(this._keyClass, str, "not one of values for Enum class");
        }
    }

    final class FloatKD extends StdKeyDeserializer {
        FloatKD() {
            super(Float.class);
        }

        public Float _parse(String str, DeserializationContext deserializationContext) {
            return Float.valueOf((float) _parseDouble(str));
        }
    }

    final class IntKD extends StdKeyDeserializer {
        IntKD() {
            super(Integer.class);
        }

        public Integer _parse(String str, DeserializationContext deserializationContext) {
            return Integer.valueOf(_parseInt(str));
        }
    }

    final class LongKD extends StdKeyDeserializer {
        LongKD() {
            super(Long.class);
        }

        public Long _parse(String str, DeserializationContext deserializationContext) {
            return Long.valueOf(_parseLong(str));
        }
    }

    final class ShortKD extends StdKeyDeserializer {
        ShortKD() {
            super(Integer.class);
        }

        public Short _parse(String str, DeserializationContext deserializationContext) {
            int _parseInt = _parseInt(str);
            if (_parseInt >= -32768 && _parseInt <= 32767) {
                return Short.valueOf((short) _parseInt);
            }
            throw deserializationContext.weirdKeyException(this._keyClass, str, "overflow, value can not be represented as 16-bit value");
        }
    }

    final class StringCtorKeyDeserializer extends StdKeyDeserializer {
        final Constructor<?> _ctor;

        public StringCtorKeyDeserializer(Constructor<?> constructor) {
            super(constructor.getDeclaringClass());
            this._ctor = constructor;
        }

        public Object _parse(String str, DeserializationContext deserializationContext) {
            return this._ctor.newInstance(new Object[]{str});
        }
    }

    final class StringFactoryKeyDeserializer extends StdKeyDeserializer {
        final Method _factoryMethod;

        public StringFactoryKeyDeserializer(Method method) {
            super(method.getDeclaringClass());
            this._factoryMethod = method;
        }

        public Object _parse(String str, DeserializationContext deserializationContext) {
            return this._factoryMethod.invoke(null, new Object[]{str});
        }
    }

    protected StdKeyDeserializer(Class<?> cls) {
        this._keyClass = cls;
    }

    protected abstract Object _parse(String str, DeserializationContext deserializationContext);

    protected double _parseDouble(String str) {
        return NumberInput.parseDouble(str);
    }

    protected int _parseInt(String str) {
        return Integer.parseInt(str);
    }

    protected long _parseLong(String str) {
        return Long.parseLong(str);
    }

    public final Object deserializeKey(String str, DeserializationContext deserializationContext) {
        if (str == null) {
            return null;
        }
        try {
            Object _parse = _parse(str, deserializationContext);
            if (_parse != null) {
                return _parse;
            }
            throw deserializationContext.weirdKeyException(this._keyClass, str, "not a valid representation");
        } catch (Exception e) {
            throw deserializationContext.weirdKeyException(this._keyClass, str, "not a valid representation: " + e.getMessage());
        }
    }

    public Class<?> getKeyClass() {
        return this._keyClass;
    }
}
