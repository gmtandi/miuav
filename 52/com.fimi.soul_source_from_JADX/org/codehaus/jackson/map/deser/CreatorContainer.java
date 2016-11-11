package org.codehaus.jackson.map.deser;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import org.codehaus.jackson.map.introspect.AnnotatedConstructor;
import org.codehaus.jackson.map.introspect.AnnotatedMethod;
import org.codehaus.jackson.map.introspect.BasicBeanDescription;
import org.codehaus.jackson.map.util.ClassUtil;

public class CreatorContainer {
    final BasicBeanDescription _beanDesc;
    final boolean _canFixAccess;
    protected Constructor<?> _defaultConstructor;
    AnnotatedConstructor _delegatingConstructor;
    AnnotatedMethod _delegatingFactory;
    AnnotatedConstructor _intConstructor;
    AnnotatedMethod _intFactory;
    AnnotatedConstructor _longConstructor;
    AnnotatedMethod _longFactory;
    AnnotatedConstructor _propertyBasedConstructor;
    SettableBeanProperty[] _propertyBasedConstructorProperties;
    AnnotatedMethod _propertyBasedFactory;
    SettableBeanProperty[] _propertyBasedFactoryProperties;
    AnnotatedConstructor _strConstructor;
    AnnotatedMethod _strFactory;

    public CreatorContainer(BasicBeanDescription basicBeanDescription, boolean z) {
        this._propertyBasedFactoryProperties = null;
        this._propertyBasedConstructorProperties = null;
        this._beanDesc = basicBeanDescription;
        this._canFixAccess = z;
    }

    public void addDelegatingConstructor(AnnotatedConstructor annotatedConstructor) {
        this._delegatingConstructor = verifyNonDup(annotatedConstructor, this._delegatingConstructor, "long");
    }

    public void addDelegatingFactory(AnnotatedMethod annotatedMethod) {
        this._delegatingFactory = verifyNonDup(annotatedMethod, this._delegatingFactory, "long");
    }

    public void addIntConstructor(AnnotatedConstructor annotatedConstructor) {
        this._intConstructor = verifyNonDup(annotatedConstructor, this._intConstructor, "int");
    }

    public void addIntFactory(AnnotatedMethod annotatedMethod) {
        this._intFactory = verifyNonDup(annotatedMethod, this._intFactory, "int");
    }

    public void addLongConstructor(AnnotatedConstructor annotatedConstructor) {
        this._longConstructor = verifyNonDup(annotatedConstructor, this._longConstructor, "long");
    }

    public void addLongFactory(AnnotatedMethod annotatedMethod) {
        this._longFactory = verifyNonDup(annotatedMethod, this._longFactory, "long");
    }

    public void addPropertyConstructor(AnnotatedConstructor annotatedConstructor, SettableBeanProperty[] settableBeanPropertyArr) {
        this._propertyBasedConstructor = verifyNonDup(annotatedConstructor, this._propertyBasedConstructor, "property-based");
        if (settableBeanPropertyArr.length > 1) {
            HashMap hashMap = new HashMap();
            int length = settableBeanPropertyArr.length;
            for (int i = 0; i < length; i++) {
                String name = settableBeanPropertyArr[i].getName();
                Integer num = (Integer) hashMap.put(name, Integer.valueOf(i));
                if (num != null) {
                    throw new IllegalArgumentException("Duplicate creator property \"" + name + "\" (index " + num + " vs " + i + ")");
                }
            }
        }
        this._propertyBasedConstructorProperties = settableBeanPropertyArr;
    }

    public void addPropertyFactory(AnnotatedMethod annotatedMethod, SettableBeanProperty[] settableBeanPropertyArr) {
        this._propertyBasedFactory = verifyNonDup(annotatedMethod, this._propertyBasedFactory, "property-based");
        this._propertyBasedFactoryProperties = settableBeanPropertyArr;
    }

    public void addStringConstructor(AnnotatedConstructor annotatedConstructor) {
        this._strConstructor = verifyNonDup(annotatedConstructor, this._strConstructor, "String");
    }

    public void addStringFactory(AnnotatedMethod annotatedMethod) {
        this._strFactory = verifyNonDup(annotatedMethod, this._strFactory, "String");
    }

    public Delegating delegatingCreator() {
        return (this._delegatingConstructor == null && this._delegatingFactory == null) ? null : new Delegating(this._beanDesc, this._delegatingConstructor, this._delegatingFactory);
    }

    public Constructor<?> getDefaultConstructor() {
        return this._defaultConstructor;
    }

    public NumberBased numberCreator() {
        return (this._intConstructor == null && this._intFactory == null && this._longConstructor == null && this._longFactory == null) ? null : new NumberBased(this._beanDesc.getBeanClass(), this._intConstructor, this._intFactory, this._longConstructor, this._longFactory);
    }

    public PropertyBased propertyBasedCreator() {
        return (this._propertyBasedConstructor == null && this._propertyBasedFactory == null) ? null : new PropertyBased(this._propertyBasedConstructor, this._propertyBasedConstructorProperties, this._propertyBasedFactory, this._propertyBasedFactoryProperties);
    }

    public void setDefaultConstructor(Constructor<?> constructor) {
        this._defaultConstructor = constructor;
    }

    public StringBased stringCreator() {
        return (this._strConstructor == null && this._strFactory == null) ? null : new StringBased(this._beanDesc.getBeanClass(), this._strConstructor, this._strFactory);
    }

    protected AnnotatedConstructor verifyNonDup(AnnotatedConstructor annotatedConstructor, AnnotatedConstructor annotatedConstructor2, String str) {
        if (annotatedConstructor2 != null) {
            throw new IllegalArgumentException("Conflicting " + str + " constructors: already had " + annotatedConstructor2 + ", encountered " + annotatedConstructor);
        }
        if (this._canFixAccess) {
            ClassUtil.checkAndFixAccess(annotatedConstructor.getAnnotated());
        }
        return annotatedConstructor;
    }

    protected AnnotatedMethod verifyNonDup(AnnotatedMethod annotatedMethod, AnnotatedMethod annotatedMethod2, String str) {
        if (annotatedMethod2 != null) {
            throw new IllegalArgumentException("Conflicting " + str + " factory methods: already had " + annotatedMethod2 + ", encountered " + annotatedMethod);
        }
        if (this._canFixAccess) {
            ClassUtil.checkAndFixAccess(annotatedMethod.getAnnotated());
        }
        return annotatedMethod;
    }
}
