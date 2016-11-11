package org.codehaus.jackson.map.deser;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.introspect.AnnotatedConstructor;
import org.codehaus.jackson.map.introspect.AnnotatedMember;
import org.codehaus.jackson.map.introspect.AnnotatedMethod;
import org.codehaus.jackson.map.introspect.BasicBeanDescription;
import org.codehaus.jackson.map.type.TypeBindings;
import org.codehaus.jackson.map.util.ClassUtil;
import org.codehaus.jackson.type.JavaType;

abstract class Creator {

    final class Delegating {
        protected final AnnotatedMember _creator;
        protected final Constructor<?> _ctor;
        protected JsonDeserializer<Object> _deserializer;
        protected final Method _factoryMethod;
        protected final JavaType _valueType;

        public Delegating(BasicBeanDescription basicBeanDescription, AnnotatedConstructor annotatedConstructor, AnnotatedMethod annotatedMethod) {
            TypeBindings bindingsForBeanType = basicBeanDescription.bindingsForBeanType();
            if (annotatedConstructor != null) {
                this._creator = annotatedConstructor;
                this._ctor = annotatedConstructor.getAnnotated();
                this._factoryMethod = null;
                this._valueType = bindingsForBeanType.resolveType(annotatedConstructor.getParameterType(0));
            } else if (annotatedMethod != null) {
                this._creator = annotatedMethod;
                this._ctor = null;
                this._factoryMethod = annotatedMethod.getAnnotated();
                this._valueType = bindingsForBeanType.resolveType(annotatedMethod.getParameterType(0));
            } else {
                throw new IllegalArgumentException("Internal error: neither delegating constructor nor factory method passed");
            }
        }

        public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            Object deserialize = this._deserializer.deserialize(jsonParser, deserializationContext);
            try {
                if (this._ctor != null) {
                    return this._ctor.newInstance(new Object[]{deserialize});
                }
                return this._factoryMethod.invoke(null, new Object[]{deserialize});
            } catch (Throwable e) {
                ClassUtil.unwrapAndThrowAsIAE(e);
                return null;
            }
        }

        public AnnotatedMember getCreator() {
            return this._creator;
        }

        public JavaType getValueType() {
            return this._valueType;
        }

        public void setDeserializer(JsonDeserializer<Object> jsonDeserializer) {
            this._deserializer = jsonDeserializer;
        }
    }

    final class NumberBased {
        protected final Constructor<?> _intCtor;
        protected final Method _intFactoryMethod;
        protected final Constructor<?> _longCtor;
        protected final Method _longFactoryMethod;
        protected final Class<?> _valueClass;

        public NumberBased(Class<?> cls, AnnotatedConstructor annotatedConstructor, AnnotatedMethod annotatedMethod, AnnotatedConstructor annotatedConstructor2, AnnotatedMethod annotatedMethod2) {
            Method method = null;
            this._valueClass = cls;
            this._intCtor = annotatedConstructor == null ? null : annotatedConstructor.getAnnotated();
            this._longCtor = annotatedConstructor2 == null ? null : annotatedConstructor2.getAnnotated();
            this._intFactoryMethod = annotatedMethod == null ? null : annotatedMethod.getAnnotated();
            if (annotatedMethod2 != null) {
                method = annotatedMethod2.getAnnotated();
            }
            this._longFactoryMethod = method;
        }

        public Object construct(int i) {
            try {
                if (this._intCtor != null) {
                    return this._intCtor.newInstance(new Object[]{Integer.valueOf(i)});
                }
                if (this._intFactoryMethod != null) {
                    return this._intFactoryMethod.invoke(this._valueClass, new Object[]{Integer.valueOf(i)});
                }
                return construct((long) i);
            } catch (Throwable e) {
                ClassUtil.unwrapAndThrowAsIAE(e);
            }
        }

        public Object construct(long j) {
            try {
                if (this._longCtor != null) {
                    return this._longCtor.newInstance(new Object[]{Long.valueOf(j)});
                }
                if (this._longFactoryMethod != null) {
                    return this._longFactoryMethod.invoke(this._valueClass, new Object[]{Long.valueOf(j)});
                }
                return null;
            } catch (Throwable e) {
                ClassUtil.unwrapAndThrowAsIAE(e);
            }
        }
    }

    final class PropertyBased {
        protected final Constructor<?> _ctor;
        protected final Object[] _defaultValues;
        protected final Method _factoryMethod;
        protected final HashMap<String, SettableBeanProperty> _properties;

        public PropertyBased(AnnotatedConstructor annotatedConstructor, SettableBeanProperty[] settableBeanPropertyArr, AnnotatedMethod annotatedMethod, SettableBeanProperty[] settableBeanPropertyArr2) {
            if (annotatedConstructor != null) {
                this._ctor = annotatedConstructor.getAnnotated();
                this._factoryMethod = null;
            } else if (annotatedMethod != null) {
                this._ctor = null;
                this._factoryMethod = annotatedMethod.getAnnotated();
                settableBeanPropertyArr = settableBeanPropertyArr2;
            } else {
                throw new IllegalArgumentException("Internal error: neither delegating constructor nor factory method passed");
            }
            this._properties = new HashMap();
            int length = settableBeanPropertyArr.length;
            Object[] objArr = null;
            for (int i = 0; i < length; i++) {
                SettableBeanProperty settableBeanProperty = settableBeanPropertyArr[i];
                this._properties.put(settableBeanProperty.getName(), settableBeanProperty);
                if (settableBeanProperty.getType().isPrimitive()) {
                    if (objArr == null) {
                        objArr = new Object[length];
                    }
                    objArr[i] = ClassUtil.defaultValue(settableBeanProperty.getType().getRawClass());
                }
            }
            this._defaultValues = objArr;
        }

        public Object build(PropertyValueBuffer propertyValueBuffer) {
            Object obj = null;
            try {
                obj = this._ctor != null ? this._ctor.newInstance(propertyValueBuffer.getParameters(this._defaultValues)) : this._factoryMethod.invoke(null, propertyValueBuffer.getParameters(this._defaultValues));
                for (PropertyValue buffered = propertyValueBuffer.buffered(); buffered != null; buffered = buffered.next) {
                    buffered.assign(obj);
                }
            } catch (Throwable e) {
                ClassUtil.throwRootCause(e);
            }
            return obj;
        }

        public SettableBeanProperty findCreatorProperty(String str) {
            return (SettableBeanProperty) this._properties.get(str);
        }

        public Collection<SettableBeanProperty> properties() {
            return this._properties.values();
        }

        public PropertyValueBuffer startBuilding(JsonParser jsonParser, DeserializationContext deserializationContext) {
            return new PropertyValueBuffer(jsonParser, deserializationContext, this._properties.size());
        }
    }

    final class StringBased {
        protected final Constructor<?> _ctor;
        protected final Method _factoryMethod;
        protected final Class<?> _valueClass;

        public StringBased(Class<?> cls, AnnotatedConstructor annotatedConstructor, AnnotatedMethod annotatedMethod) {
            Method method = null;
            this._valueClass = cls;
            this._ctor = annotatedConstructor == null ? null : annotatedConstructor.getAnnotated();
            if (annotatedMethod != null) {
                method = annotatedMethod.getAnnotated();
            }
            this._factoryMethod = method;
        }

        public Object construct(String str) {
            try {
                if (this._ctor != null) {
                    return this._ctor.newInstance(new Object[]{str});
                }
                if (this._factoryMethod != null) {
                    return this._factoryMethod.invoke(this._valueClass, new Object[]{str});
                }
                return null;
            } catch (Throwable e) {
                ClassUtil.unwrapAndThrowAsIAE(e);
            }
        }
    }

    private Creator() {
    }
}
