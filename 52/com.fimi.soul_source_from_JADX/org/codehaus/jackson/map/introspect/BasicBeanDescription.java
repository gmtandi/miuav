package org.codehaus.jackson.map.introspect;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.AnnotationIntrospector.ReferenceProperty;
import org.codehaus.jackson.map.BeanDescription;
import org.codehaus.jackson.map.MapperConfig;
import org.codehaus.jackson.map.PropertyNamingStrategy;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.map.type.TypeBindings;
import org.codehaus.jackson.map.util.Annotations;
import org.codehaus.jackson.map.util.ClassUtil;
import org.codehaus.jackson.type.JavaType;

public class BasicBeanDescription extends BeanDescription {
    protected final AnnotationIntrospector _annotationIntrospector;
    protected TypeBindings _bindings;
    protected final AnnotatedClass _classInfo;
    protected final MapperConfig<?> _config;

    public BasicBeanDescription(MapperConfig<?> mapperConfig, JavaType javaType, AnnotatedClass annotatedClass) {
        super(javaType);
        this._config = mapperConfig;
        this._annotationIntrospector = mapperConfig.getAnnotationIntrospector();
        this._classInfo = annotatedClass;
    }

    public static String descFor(AnnotatedElement annotatedElement) {
        if (annotatedElement instanceof Class) {
            return "class " + ((Class) annotatedElement).getName();
        }
        if (annotatedElement instanceof Method) {
            Method method = (Method) annotatedElement;
            return "method " + method.getName() + " (from class " + method.getDeclaringClass().getName() + ")";
        } else if (!(annotatedElement instanceof Constructor)) {
            return "unknown type [" + annotatedElement.getClass() + "]";
        } else {
            return "constructor() (from class " + ((Constructor) annotatedElement).getDeclaringClass().getName() + ")";
        }
    }

    public static String manglePropertyName(String str) {
        StringBuilder stringBuilder = null;
        int length = str.length();
        if (length == 0) {
            return null;
        }
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            char toLowerCase = Character.toLowerCase(charAt);
            if (charAt == toLowerCase) {
                break;
            }
            if (stringBuilder == null) {
                stringBuilder = new StringBuilder(str);
            }
            stringBuilder.setCharAt(i, toLowerCase);
        }
        return stringBuilder != null ? stringBuilder.toString() : str;
    }

    public LinkedHashMap<String, AnnotatedField> _findPropertyFields(VisibilityChecker<?> visibilityChecker, Collection<String> collection, boolean z) {
        LinkedHashMap<String, AnnotatedField> linkedHashMap = new LinkedHashMap();
        PropertyNamingStrategy propertyNamingStrategy = this._config.getPropertyNamingStrategy();
        for (AnnotatedField annotatedField : this._classInfo.fields()) {
            String nameForField;
            String findSerializablePropertyName = z ? this._annotationIntrospector.findSerializablePropertyName(annotatedField) : this._annotationIntrospector.findDeserializablePropertyName(annotatedField);
            if (findSerializablePropertyName != null) {
                if (findSerializablePropertyName.length() == 0) {
                    findSerializablePropertyName = annotatedField.getName();
                    if (propertyNamingStrategy != null) {
                        nameForField = propertyNamingStrategy.nameForField(this._config, annotatedField, findSerializablePropertyName);
                    }
                }
                nameForField = findSerializablePropertyName;
            } else if (visibilityChecker.isFieldVisible(annotatedField)) {
                findSerializablePropertyName = annotatedField.getName();
                if (propertyNamingStrategy != null) {
                    nameForField = propertyNamingStrategy.nameForField(this._config, annotatedField, findSerializablePropertyName);
                }
                nameForField = findSerializablePropertyName;
            } else {
                continue;
            }
            if (collection == null || !collection.contains(nameForField)) {
                AnnotatedField annotatedField2 = (AnnotatedField) linkedHashMap.put(nameForField, annotatedField);
                if (annotatedField2 != null && annotatedField2.getDeclaringClass() == annotatedField.getDeclaringClass()) {
                    throw new IllegalArgumentException("Multiple fields representing property \"" + nameForField + "\": " + annotatedField2.getFullName() + " vs " + annotatedField.getFullName());
                }
            }
        }
        return linkedHashMap;
    }

    public TypeBindings bindingsForBeanType() {
        if (this._bindings == null) {
            this._bindings = new TypeBindings(this._config.getTypeFactory(), this._type);
        }
        return this._bindings;
    }

    public AnnotatedMethod findAnyGetter() {
        AnnotatedMethod annotatedMethod = null;
        for (AnnotatedMethod annotatedMethod2 : this._classInfo.memberMethods()) {
            if (this._annotationIntrospector.hasAnyGetterAnnotation(annotatedMethod2)) {
                if (annotatedMethod != null) {
                    throw new IllegalArgumentException("Multiple methods with 'any-getter' annotation (" + annotatedMethod.getName() + "(), " + annotatedMethod2.getName() + ")");
                }
                if (Map.class.isAssignableFrom(annotatedMethod2.getRawType())) {
                    annotatedMethod = annotatedMethod2;
                } else {
                    throw new IllegalArgumentException("Invalid 'any-getter' annotation on method " + annotatedMethod2.getName() + "(): return type is not instance of java.util.Map");
                }
            }
        }
        return annotatedMethod;
    }

    public AnnotatedMethod findAnySetter() {
        AnnotatedMethod annotatedMethod = null;
        for (AnnotatedMethod annotatedMethod2 : this._classInfo.memberMethods()) {
            if (this._annotationIntrospector.hasAnySetterAnnotation(annotatedMethod2)) {
                if (annotatedMethod != null) {
                    throw new IllegalArgumentException("Multiple methods with 'any-setter' annotation (" + annotatedMethod.getName() + "(), " + annotatedMethod2.getName() + ")");
                }
                int parameterCount = annotatedMethod2.getParameterCount();
                if (parameterCount != 2) {
                    throw new IllegalArgumentException("Invalid 'any-setter' annotation on method " + annotatedMethod2.getName() + "(): takes " + parameterCount + " parameters, should take 2");
                }
                Class parameterClass = annotatedMethod2.getParameterClass(0);
                if (parameterClass == String.class || parameterClass == Object.class) {
                    annotatedMethod = annotatedMethod2;
                } else {
                    throw new IllegalArgumentException("Invalid 'any-setter' annotation on method " + annotatedMethod2.getName() + "(): first argument not of type String or Object, but " + parameterClass.getName());
                }
            }
        }
        return annotatedMethod;
    }

    public Map<String, AnnotatedMember> findBackReferenceProperties() {
        Map<String, AnnotatedMember> map = null;
        for (AnnotatedMethod annotatedMethod : this._classInfo.memberMethods()) {
            ReferenceProperty findReferenceType;
            if (annotatedMethod.getParameterCount() == 1) {
                findReferenceType = this._annotationIntrospector.findReferenceType(annotatedMethod);
                if (findReferenceType != null && findReferenceType.isBackReference()) {
                    if (map == null) {
                        map = new HashMap();
                    }
                    if (map.put(findReferenceType.getName(), annotatedMethod) != null) {
                        throw new IllegalArgumentException("Multiple back-reference properties with name '" + findReferenceType.getName() + "'");
                    }
                }
            }
        }
        for (AnnotatedField annotatedField : this._classInfo.fields()) {
            findReferenceType = this._annotationIntrospector.findReferenceType(annotatedField);
            if (findReferenceType != null && findReferenceType.isBackReference()) {
                if (map == null) {
                    map = new HashMap();
                }
                if (map.put(findReferenceType.getName(), annotatedField) != null) {
                    throw new IllegalArgumentException("Multiple back-reference properties with name '" + findReferenceType.getName() + "'");
                }
            }
        }
        return map;
    }

    public List<String> findCreatorPropertyNames() {
        List<String> list = null;
        int i = 0;
        while (i < 2) {
            for (AnnotatedWithParams annotatedWithParams : i == 0 ? getConstructors() : getFactoryMethods()) {
                int parameterCount = annotatedWithParams.getParameterCount();
                if (parameterCount >= 1) {
                    String findPropertyNameForParam = this._annotationIntrospector.findPropertyNameForParam(annotatedWithParams.getParameter(0));
                    if (findPropertyNameForParam != null) {
                        if (list == null) {
                            list = new ArrayList();
                        }
                        list.add(findPropertyNameForParam);
                        for (int i2 = 1; i2 < parameterCount; i2++) {
                            list.add(this._annotationIntrospector.findPropertyNameForParam(annotatedWithParams.getParameter(i2)));
                        }
                    }
                }
            }
            i++;
        }
        return list == null ? Collections.emptyList() : list;
    }

    public Constructor<?> findDefaultConstructor() {
        AnnotatedConstructor defaultConstructor = this._classInfo.getDefaultConstructor();
        return defaultConstructor == null ? null : defaultConstructor.getAnnotated();
    }

    public LinkedHashMap<String, AnnotatedField> findDeserializableFields(VisibilityChecker<?> visibilityChecker, Collection<String> collection) {
        return _findPropertyFields(visibilityChecker, collection, false);
    }

    public Method findFactoryMethod(Class<?>... clsArr) {
        for (AnnotatedMethod annotatedMethod : this._classInfo.getStaticMethods()) {
            if (isFactoryMethod(annotatedMethod)) {
                Class parameterClass = annotatedMethod.getParameterClass(0);
                for (Class isAssignableFrom : clsArr) {
                    if (parameterClass.isAssignableFrom(isAssignableFrom)) {
                        return annotatedMethod.getAnnotated();
                    }
                }
                continue;
            }
        }
        return null;
    }

    public LinkedHashMap<String, AnnotatedMethod> findGetters(VisibilityChecker<?> visibilityChecker, Collection<String> collection) {
        LinkedHashMap<String, AnnotatedMethod> linkedHashMap = new LinkedHashMap();
        PropertyNamingStrategy propertyNamingStrategy = this._config.getPropertyNamingStrategy();
        for (AnnotatedMethod annotatedMethod : this._classInfo.memberMethods()) {
            if (annotatedMethod.getParameterCount() == 0) {
                String nameForGetterMethod;
                String findGettablePropertyName = this._annotationIntrospector.findGettablePropertyName(annotatedMethod);
                if (findGettablePropertyName != null) {
                    if (findGettablePropertyName.length() == 0) {
                        findGettablePropertyName = okNameForAnyGetter(annotatedMethod, annotatedMethod.getName());
                        if (findGettablePropertyName == null) {
                            findGettablePropertyName = annotatedMethod.getName();
                        }
                        if (propertyNamingStrategy != null) {
                            nameForGetterMethod = propertyNamingStrategy.nameForGetterMethod(this._config, annotatedMethod, findGettablePropertyName);
                        }
                    }
                    nameForGetterMethod = findGettablePropertyName;
                } else {
                    findGettablePropertyName = annotatedMethod.getName();
                    if (findGettablePropertyName.startsWith("get")) {
                        if (visibilityChecker.isGetterVisible(annotatedMethod)) {
                            findGettablePropertyName = okNameForGetter(annotatedMethod, findGettablePropertyName);
                        } else {
                            continue;
                        }
                    } else if (visibilityChecker.isIsGetterVisible(annotatedMethod)) {
                        findGettablePropertyName = okNameForIsGetter(annotatedMethod, findGettablePropertyName);
                    } else {
                        continue;
                    }
                    if (!(findGettablePropertyName == null || this._annotationIntrospector.hasAnyGetterAnnotation(annotatedMethod))) {
                        if (propertyNamingStrategy != null) {
                            nameForGetterMethod = propertyNamingStrategy.nameForGetterMethod(this._config, annotatedMethod, findGettablePropertyName);
                        }
                        nameForGetterMethod = findGettablePropertyName;
                    }
                }
                if (collection == null || !collection.contains(nameForGetterMethod)) {
                    AnnotatedMethod annotatedMethod2 = (AnnotatedMethod) linkedHashMap.put(nameForGetterMethod, annotatedMethod);
                    if (annotatedMethod2 != null) {
                        findGettablePropertyName = annotatedMethod2.getFullName();
                        throw new IllegalArgumentException("Conflicting getter definitions for property \"" + nameForGetterMethod + "\": " + findGettablePropertyName + " vs " + annotatedMethod.getFullName());
                    }
                }
            }
        }
        return linkedHashMap;
    }

    public AnnotatedMethod findJsonValueMethod() {
        AnnotatedMethod annotatedMethod = null;
        for (AnnotatedMethod annotatedMethod2 : this._classInfo.memberMethods()) {
            if (this._annotationIntrospector.hasAsValueAnnotation(annotatedMethod2)) {
                if (annotatedMethod != null) {
                    throw new IllegalArgumentException("Multiple methods with active 'as-value' annotation (" + annotatedMethod.getName() + "(), " + annotatedMethod2.getName() + ")");
                } else if (ClassUtil.hasGetterSignature(annotatedMethod2.getAnnotated())) {
                    annotatedMethod = annotatedMethod2;
                } else {
                    throw new IllegalArgumentException("Method " + annotatedMethod2.getName() + "() marked with an 'as-value' annotation, but does not have valid getter signature (non-static, takes no args, returns a value)");
                }
            }
        }
        return annotatedMethod;
    }

    public AnnotatedMethod findMethod(String str, Class<?>[] clsArr) {
        return this._classInfo.findMethod(str, clsArr);
    }

    public LinkedHashMap<String, AnnotatedField> findSerializableFields(VisibilityChecker<?> visibilityChecker, Collection<String> collection) {
        return _findPropertyFields(visibilityChecker, collection, true);
    }

    public Inclusion findSerializationInclusion(Inclusion inclusion) {
        return this._annotationIntrospector.findSerializationInclusion(this._classInfo, inclusion);
    }

    public LinkedHashMap<String, AnnotatedMethod> findSetters(VisibilityChecker<?> visibilityChecker) {
        LinkedHashMap<String, AnnotatedMethod> linkedHashMap = new LinkedHashMap();
        PropertyNamingStrategy propertyNamingStrategy = this._config.getPropertyNamingStrategy();
        for (AnnotatedMethod annotatedMethod : this._classInfo.memberMethods()) {
            if (annotatedMethod.getParameterCount() == 1) {
                String nameForSetterMethod;
                String findSettablePropertyName = this._annotationIntrospector.findSettablePropertyName(annotatedMethod);
                if (findSettablePropertyName != null) {
                    if (findSettablePropertyName.length() == 0) {
                        findSettablePropertyName = okNameForSetter(annotatedMethod);
                        if (findSettablePropertyName == null) {
                            findSettablePropertyName = annotatedMethod.getName();
                        }
                        if (propertyNamingStrategy != null) {
                            nameForSetterMethod = propertyNamingStrategy.nameForSetterMethod(this._config, annotatedMethod, findSettablePropertyName);
                        }
                    }
                    nameForSetterMethod = findSettablePropertyName;
                } else if (visibilityChecker.isSetterVisible(annotatedMethod)) {
                    findSettablePropertyName = okNameForSetter(annotatedMethod);
                    if (findSettablePropertyName == null) {
                        continue;
                    } else {
                        if (propertyNamingStrategy != null) {
                            nameForSetterMethod = propertyNamingStrategy.nameForSetterMethod(this._config, annotatedMethod, findSettablePropertyName);
                        }
                        nameForSetterMethod = findSettablePropertyName;
                    }
                } else {
                    continue;
                }
                AnnotatedMethod annotatedMethod2 = (AnnotatedMethod) linkedHashMap.put(nameForSetterMethod, annotatedMethod);
                if (annotatedMethod2 == null) {
                    continue;
                } else if (annotatedMethod2.getDeclaringClass() == annotatedMethod.getDeclaringClass()) {
                    findSettablePropertyName = annotatedMethod2.getFullName();
                    throw new IllegalArgumentException("Conflicting setter definitions for property \"" + nameForSetterMethod + "\": " + findSettablePropertyName + " vs " + annotatedMethod.getFullName());
                } else {
                    linkedHashMap.put(nameForSetterMethod, annotatedMethod2);
                }
            }
        }
        return linkedHashMap;
    }

    public Constructor<?> findSingleArgConstructor(Class<?>... clsArr) {
        for (AnnotatedConstructor annotatedConstructor : this._classInfo.getConstructors()) {
            if (annotatedConstructor.getParameterCount() == 1) {
                Class<?> parameterClass = annotatedConstructor.getParameterClass(0);
                for (Class<?> cls : clsArr) {
                    if (cls == parameterClass) {
                        return annotatedConstructor.getAnnotated();
                    }
                }
                continue;
            }
        }
        return null;
    }

    public Annotations getClassAnnotations() {
        return this._classInfo.getAnnotations();
    }

    public AnnotatedClass getClassInfo() {
        return this._classInfo;
    }

    public List<AnnotatedConstructor> getConstructors() {
        return this._classInfo.getConstructors();
    }

    public List<AnnotatedMethod> getFactoryMethods() {
        List<AnnotatedMethod> staticMethods = this._classInfo.getStaticMethods();
        if (staticMethods.isEmpty()) {
            return staticMethods;
        }
        ArrayList arrayList = new ArrayList();
        for (AnnotatedMethod annotatedMethod : staticMethods) {
            if (isFactoryMethod(annotatedMethod)) {
                arrayList.add(annotatedMethod);
            }
        }
        return arrayList;
    }

    public boolean hasKnownClassAnnotations() {
        return this._classInfo.hasAnnotations();
    }

    public Object instantiateBean(boolean z) {
        AnnotatedConstructor defaultConstructor = this._classInfo.getDefaultConstructor();
        if (defaultConstructor == null) {
            return null;
        }
        if (z) {
            defaultConstructor.fixAccess();
        }
        try {
            return defaultConstructor.getAnnotated().newInstance(new Object[0]);
        } catch (Exception e) {
            Throwable e2 = e;
            while (e2.getCause() != null) {
                e2 = e2.getCause();
            }
            if (e2 instanceof Error) {
                throw ((Error) e2);
            } else if (e2 instanceof RuntimeException) {
                throw ((RuntimeException) e2);
            } else {
                throw new IllegalArgumentException("Failed to instantiate bean of type " + this._classInfo.getAnnotated().getName() + ": (" + e2.getClass().getName() + ") " + e2.getMessage(), e2);
            }
        }
    }

    protected boolean isCglibGetCallbacks(AnnotatedMethod annotatedMethod) {
        Class rawType = annotatedMethod.getRawType();
        if (rawType == null || !rawType.isArray()) {
            return false;
        }
        Package packageR = rawType.getComponentType().getPackage();
        if (packageR == null) {
            return false;
        }
        String name = packageR.getName();
        return name.startsWith("net.sf.cglib") || name.startsWith("org.hibernate.repackage.cglib");
    }

    protected boolean isFactoryMethod(AnnotatedMethod annotatedMethod) {
        return !getBeanClass().isAssignableFrom(annotatedMethod.getRawType()) ? false : this._annotationIntrospector.hasCreatorAnnotation(annotatedMethod) ? true : "valueOf".equals(annotatedMethod.getName());
    }

    protected boolean isGroovyMetaClassGetter(AnnotatedMethod annotatedMethod) {
        Class rawType = annotatedMethod.getRawType();
        if (rawType == null || rawType.isArray()) {
            return false;
        }
        Package packageR = rawType.getPackage();
        return packageR != null && packageR.getName().startsWith("groovy.lang");
    }

    protected boolean isGroovyMetaClassSetter(AnnotatedMethod annotatedMethod) {
        Package packageR = annotatedMethod.getParameterClass(0).getPackage();
        return packageR != null && packageR.getName().startsWith("groovy.lang");
    }

    protected String mangleGetterName(Annotated annotated, String str) {
        return manglePropertyName(str);
    }

    protected String mangleSetterName(Annotated annotated, String str) {
        return manglePropertyName(str);
    }

    public String okNameForAnyGetter(AnnotatedMethod annotatedMethod, String str) {
        String okNameForIsGetter = okNameForIsGetter(annotatedMethod, str);
        return okNameForIsGetter == null ? okNameForGetter(annotatedMethod, str) : okNameForIsGetter;
    }

    public String okNameForGetter(AnnotatedMethod annotatedMethod, String str) {
        if (!str.startsWith("get")) {
            return null;
        }
        if ("getCallbacks".equals(str)) {
            if (isCglibGetCallbacks(annotatedMethod)) {
                return null;
            }
        } else if ("getMetaClass".equals(str) && isGroovyMetaClassGetter(annotatedMethod)) {
            return null;
        }
        return mangleGetterName(annotatedMethod, str.substring(3));
    }

    public String okNameForIsGetter(AnnotatedMethod annotatedMethod, String str) {
        if (!str.startsWith("is")) {
            return null;
        }
        Class rawType = annotatedMethod.getRawType();
        return (rawType == Boolean.class || rawType == Boolean.TYPE) ? mangleGetterName(annotatedMethod, str.substring(2)) : null;
    }

    public String okNameForSetter(AnnotatedMethod annotatedMethod) {
        String name = annotatedMethod.getName();
        if (!name.startsWith("set")) {
            return null;
        }
        name = mangleSetterName(annotatedMethod, name.substring(3));
        return name == null ? null : ("metaClass".equals(name) && isGroovyMetaClassSetter(annotatedMethod)) ? null : name;
    }
}
