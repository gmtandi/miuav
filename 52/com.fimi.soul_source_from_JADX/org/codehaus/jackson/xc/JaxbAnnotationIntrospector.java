package org.codehaus.jackson.xc;

import com.tencent.mm.sdk.plugin.MMPluginProviderConstants.SharedPref;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.ref.SoftReference;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter.DEFAULT;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;
import org.codehaus.jackson.Version;
import org.codehaus.jackson.Versioned;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.annotate.JsonTypeInfo.As;
import org.codehaus.jackson.annotate.JsonTypeInfo.Id;
import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.KeyDeserializer;
import org.codehaus.jackson.map.MapperConfig;
import org.codehaus.jackson.map.annotate.JsonCachable;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.map.annotate.JsonSerialize.Typing;
import org.codehaus.jackson.map.introspect.Annotated;
import org.codehaus.jackson.map.introspect.AnnotatedClass;
import org.codehaus.jackson.map.introspect.AnnotatedConstructor;
import org.codehaus.jackson.map.introspect.AnnotatedField;
import org.codehaus.jackson.map.introspect.AnnotatedMember;
import org.codehaus.jackson.map.introspect.AnnotatedMethod;
import org.codehaus.jackson.map.introspect.AnnotatedParameter;
import org.codehaus.jackson.map.introspect.VisibilityChecker;
import org.codehaus.jackson.map.jsontype.NamedType;
import org.codehaus.jackson.map.jsontype.TypeResolverBuilder;
import org.codehaus.jackson.map.jsontype.impl.StdTypeResolverBuilder;
import org.codehaus.jackson.map.util.ClassUtil;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.util.VersionUtil;
import org.p122a.p123a.C2915a;

public class JaxbAnnotationIntrospector extends AnnotationIntrospector implements Versioned {
    protected static final String MARKER_FOR_DEFAULT = "##default";
    private static final ThreadLocal<SoftReference<PropertyDescriptors>> _propertyDescriptors;
    protected final JsonDeserializer<?> _dataHandlerDeserializer;
    protected final JsonSerializer<?> _dataHandlerSerializer;
    protected final String _jaxbPackageName;

    /* renamed from: org.codehaus.jackson.xc.JaxbAnnotationIntrospector.1 */
    /* synthetic */ class C35961 {
        static final /* synthetic */ int[] $SwitchMap$javax$xml$bind$annotation$XmlAccessType;

        static {
            $SwitchMap$javax$xml$bind$annotation$XmlAccessType = new int[XmlAccessType.values().length];
            try {
                $SwitchMap$javax$xml$bind$annotation$XmlAccessType[XmlAccessType.FIELD.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$javax$xml$bind$annotation$XmlAccessType[XmlAccessType.NONE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$javax$xml$bind$annotation$XmlAccessType[XmlAccessType.PROPERTY.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$javax$xml$bind$annotation$XmlAccessType[XmlAccessType.PUBLIC_MEMBER.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    class AnnotatedProperty implements AnnotatedElement {
        private final PropertyDescriptor pd;

        private AnnotatedProperty(PropertyDescriptor propertyDescriptor) {
            this.pd = propertyDescriptor;
        }

        public <T extends Annotation> T getAnnotation(Class<T> cls) {
            Method readMethod = this.pd.getReadMethod();
            if (readMethod != null) {
                T annotation = readMethod.getAnnotation(cls);
                if (annotation != null) {
                    return annotation;
                }
            }
            readMethod = this.pd.getWriteMethod();
            return readMethod != null ? readMethod.getAnnotation(cls) : null;
        }

        public Annotation[] getAnnotations() {
            throw new UnsupportedOperationException();
        }

        public Annotation[] getDeclaredAnnotations() {
            throw new UnsupportedOperationException();
        }

        public boolean isAnnotationPresent(Class<? extends Annotation> cls) {
            Method readMethod = this.pd.getReadMethod();
            if (readMethod != null && readMethod.isAnnotationPresent(cls)) {
                return true;
            }
            readMethod = this.pd.getWriteMethod();
            return readMethod != null && readMethod.isAnnotationPresent(cls);
        }
    }

    public final class PropertyDescriptors {
        private Map<String, PropertyDescriptor> _byMethodName;
        private Map<String, PropertyDescriptor> _byPropertyName;
        private final Class<?> _forClass;
        private final List<PropertyDescriptor> _properties;

        public PropertyDescriptors(Class<?> cls, List<PropertyDescriptor> list) {
            this._forClass = cls;
            this._properties = list;
        }

        private static Map<String, PropertyDescriptor> _processReadMethod(Map<String, PropertyDescriptor> map, Method method, String str, List<PropertyDescriptor> list) {
            if (map == null) {
                map = new HashMap();
            } else {
                PropertyDescriptor propertyDescriptor = (PropertyDescriptor) map.get(str);
                if (propertyDescriptor != null) {
                    propertyDescriptor.setReadMethod(method);
                    if (propertyDescriptor.getWriteMethod() != null) {
                        list.add(propertyDescriptor);
                        map.remove(str);
                        return map;
                    }
                }
            }
            map.put(str, new PropertyDescriptor(str, method, null));
            return map;
        }

        private static Map<String, PropertyDescriptor> _processWriteMethod(Map<String, PropertyDescriptor> map, Method method, String str, List<PropertyDescriptor> list) {
            if (map == null) {
                map = new HashMap();
            } else {
                PropertyDescriptor propertyDescriptor = (PropertyDescriptor) map.get(str);
                if (propertyDescriptor != null) {
                    propertyDescriptor.setWriteMethod(method);
                    if (propertyDescriptor.getReadMethod() != null) {
                        list.add(propertyDescriptor);
                        map.remove(str);
                        return map;
                    }
                }
            }
            map.put(str, new PropertyDescriptor(str, null, method));
            return map;
        }

        public static PropertyDescriptors find(Class<?> cls) {
            List emptyList;
            BeanInfo beanInfo = Introspector.getBeanInfo(cls);
            if (beanInfo.getPropertyDescriptors().length == 0) {
                emptyList = Collections.emptyList();
            } else {
                List arrayList = new ArrayList();
                Map map = null;
                for (PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors()) {
                    AnnotatedElement annotatedElement;
                    AnnotatedElement annotatedElement2;
                    Method readMethod = propertyDescriptor.getReadMethod();
                    if (readMethod == null || readMethod.getAnnotation(XmlTransient.class) == null) {
                        Object obj = readMethod;
                    } else {
                        annotatedElement = null;
                    }
                    String findJaxbPropertyName = annotatedElement == null ? null : JaxbAnnotationIntrospector.findJaxbPropertyName(annotatedElement, propertyDescriptor.getPropertyType(), null);
                    Method writeMethod = propertyDescriptor.getWriteMethod();
                    if (writeMethod == null || writeMethod.getAnnotation(XmlTransient.class) == null) {
                        Object obj2 = writeMethod;
                    } else {
                        annotatedElement2 = null;
                    }
                    if (annotatedElement != null || annotatedElement2 != null) {
                        String findJaxbPropertyName2 = annotatedElement2 == null ? null : JaxbAnnotationIntrospector.findJaxbPropertyName(annotatedElement2, propertyDescriptor.getPropertyType(), null);
                        if (annotatedElement2 == null) {
                            if (findJaxbPropertyName == null) {
                                findJaxbPropertyName = propertyDescriptor.getName();
                            }
                            map = _processReadMethod(map, annotatedElement, findJaxbPropertyName, arrayList);
                        } else if (annotatedElement == null) {
                            map = _processWriteMethod(map, annotatedElement2, findJaxbPropertyName2 == null ? propertyDescriptor.getName() : findJaxbPropertyName2, arrayList);
                        } else if (findJaxbPropertyName == null || findJaxbPropertyName2 == null || findJaxbPropertyName.equals(findJaxbPropertyName2)) {
                            if (findJaxbPropertyName == null) {
                                findJaxbPropertyName = findJaxbPropertyName2 != null ? findJaxbPropertyName2 : propertyDescriptor.getName();
                            }
                            arrayList.add(new PropertyDescriptor(findJaxbPropertyName, annotatedElement, annotatedElement2));
                        } else {
                            map = _processWriteMethod(_processReadMethod(map, annotatedElement, findJaxbPropertyName, arrayList), annotatedElement2, findJaxbPropertyName2, arrayList);
                        }
                    }
                }
                emptyList = arrayList;
            }
            return new PropertyDescriptors(cls, emptyList);
        }

        public PropertyDescriptor findByMethodName(String str) {
            if (this._byMethodName == null) {
                this._byMethodName = new HashMap(this._properties.size());
                for (PropertyDescriptor propertyDescriptor : this._properties) {
                    Method readMethod = propertyDescriptor.getReadMethod();
                    if (readMethod != null) {
                        this._byMethodName.put(readMethod.getName(), propertyDescriptor);
                    }
                    readMethod = propertyDescriptor.getWriteMethod();
                    if (readMethod != null) {
                        this._byMethodName.put(readMethod.getName(), propertyDescriptor);
                    }
                }
            }
            return (PropertyDescriptor) this._byMethodName.get(str);
        }

        public PropertyDescriptor findByPropertyName(String str) {
            if (this._byPropertyName == null) {
                this._byPropertyName = new HashMap(this._properties.size());
                for (PropertyDescriptor propertyDescriptor : this._properties) {
                    this._byPropertyName.put(propertyDescriptor.getName(), propertyDescriptor);
                }
            }
            return (PropertyDescriptor) this._byPropertyName.get(str);
        }

        public Class<?> getBeanClass() {
            return this._forClass;
        }
    }

    static {
        _propertyDescriptors = new ThreadLocal();
    }

    public JaxbAnnotationIntrospector() {
        JsonSerializer jsonSerializer;
        JsonDeserializer jsonDeserializer;
        this._jaxbPackageName = XmlElement.class.getPackage().getName();
        try {
            jsonSerializer = (JsonSerializer) Class.forName("org.codehaus.jackson.xc.DataHandlerJsonSerializer").newInstance();
            try {
                jsonDeserializer = (JsonDeserializer) Class.forName("org.codehaus.jackson.xc.DataHandlerJsonDeserializer").newInstance();
            } catch (Throwable th) {
                jsonDeserializer = null;
                this._dataHandlerSerializer = jsonSerializer;
                this._dataHandlerDeserializer = jsonDeserializer;
            }
        } catch (Throwable th2) {
            jsonSerializer = null;
            jsonDeserializer = null;
            this._dataHandlerSerializer = jsonSerializer;
            this._dataHandlerDeserializer = jsonDeserializer;
        }
        this._dataHandlerSerializer = jsonSerializer;
        this._dataHandlerDeserializer = jsonDeserializer;
    }

    private final XmlAdapter<Object, Object> checkAdapter(XmlJavaTypeAdapter xmlJavaTypeAdapter, Class<?> cls) {
        Class type = xmlJavaTypeAdapter.type();
        return (type == DEFAULT.class || type.isAssignableFrom(cls)) ? (XmlAdapter) ClassUtil.createInstance(xmlJavaTypeAdapter.value(), false) : null;
    }

    protected static String findJaxbPropertyName(AnnotatedElement annotatedElement, Class<?> cls, String str) {
        XmlElementWrapper xmlElementWrapper = (XmlElementWrapper) annotatedElement.getAnnotation(XmlElementWrapper.class);
        String name;
        if (xmlElementWrapper != null) {
            name = xmlElementWrapper.name();
            return !MARKER_FOR_DEFAULT.equals(name) ? name : str;
        } else {
            XmlAttribute xmlAttribute = (XmlAttribute) annotatedElement.getAnnotation(XmlAttribute.class);
            if (xmlAttribute != null) {
                name = xmlAttribute.name();
                return !MARKER_FOR_DEFAULT.equals(name) ? name : str;
            } else {
                XmlElement xmlElement = (XmlElement) annotatedElement.getAnnotation(XmlElement.class);
                if (xmlElement != null) {
                    name = xmlElement.name();
                    return !MARKER_FOR_DEFAULT.equals(name) ? name : str;
                } else {
                    XmlElementRef xmlElementRef = (XmlElementRef) annotatedElement.getAnnotation(XmlElementRef.class);
                    if (xmlElementRef != null) {
                        str = xmlElementRef.name();
                        if (!MARKER_FOR_DEFAULT.equals(str)) {
                            return str;
                        }
                        if (cls != null) {
                            XmlRootElement xmlRootElement = (XmlRootElement) cls.getAnnotation(XmlRootElement.class);
                            if (xmlRootElement != null) {
                                str = xmlRootElement.name();
                                return MARKER_FOR_DEFAULT.equals(str) ? Introspector.decapitalize(cls.getSimpleName()) : str;
                            }
                        }
                    }
                    return ((XmlValue) annotatedElement.getAnnotation(XmlValue.class)) != null ? SharedPref.VALUE : null;
                }
            }
        }
    }

    private XmlRootElement findRootElementAnnotation(AnnotatedClass annotatedClass) {
        return (XmlRootElement) findAnnotation(XmlRootElement.class, annotatedClass, true, false, true);
    }

    private boolean isDataHandler(Class<?> cls) {
        return (cls == null || Object.class == cls || (!"javax.activation.DataHandler".equals(cls.getName()) && !isDataHandler(cls.getSuperclass()))) ? false : true;
    }

    protected Class<?> _doFindDeserializationType(Annotated annotated, JavaType javaType, String str) {
        if (annotated.hasAnnotation(XmlJavaTypeAdapter.class)) {
            return null;
        }
        XmlElement xmlElement = (XmlElement) findAnnotation(XmlElement.class, annotated, false, false, false);
        if (xmlElement != null) {
            Class<?> type = xmlElement.type();
            if (type != XmlElement.DEFAULT.class) {
                return type;
            }
        }
        if ((annotated instanceof AnnotatedMethod) && str != null) {
            xmlElement = (XmlElement) findFieldAnnotation(XmlElement.class, ((AnnotatedMethod) annotated).getDeclaringClass(), str);
            if (!(xmlElement == null || xmlElement.type() == XmlElement.DEFAULT.class)) {
                return xmlElement.type();
            }
        }
        return null;
    }

    protected TypeResolverBuilder<?> _typeResolverFromXmlElements(AnnotatedMember annotatedMember) {
        return (((XmlElements) findAnnotation(XmlElements.class, annotatedMember, false, false, false)) == null && ((XmlElementRefs) findAnnotation(XmlElementRefs.class, annotatedMember, false, false, false)) == null) ? null : new StdTypeResolverBuilder().init(Id.NAME, null).inclusion(As.WRAPPER_OBJECT);
    }

    protected XmlAccessType findAccessType(Annotated annotated) {
        XmlAccessorType xmlAccessorType = (XmlAccessorType) findAnnotation(XmlAccessorType.class, annotated, true, true, true);
        return xmlAccessorType == null ? null : xmlAccessorType.value();
    }

    protected XmlAdapter<Object, Object> findAdapter(Annotated annotated, boolean z) {
        int i = 0;
        if (annotated instanceof AnnotatedClass) {
            return findAdapterForClass((AnnotatedClass) annotated, z);
        }
        XmlJavaTypeAdapter xmlJavaTypeAdapter;
        XmlAdapter<Object, Object> checkAdapter;
        Class rawType = annotated.getRawType();
        Class parameterClass = (rawType == Void.TYPE && (annotated instanceof AnnotatedMethod)) ? ((AnnotatedMethod) annotated).getParameterClass(0) : rawType;
        Member member = (Member) annotated.getAnnotated();
        if (member != null) {
            rawType = member.getDeclaringClass();
            if (rawType != null) {
                xmlJavaTypeAdapter = (XmlJavaTypeAdapter) rawType.getAnnotation(XmlJavaTypeAdapter.class);
                if (xmlJavaTypeAdapter != null) {
                    checkAdapter = checkAdapter(xmlJavaTypeAdapter, parameterClass);
                    if (checkAdapter != null) {
                        return checkAdapter;
                    }
                }
            }
        }
        xmlJavaTypeAdapter = (XmlJavaTypeAdapter) findAnnotation(XmlJavaTypeAdapter.class, annotated, true, false, false);
        if (xmlJavaTypeAdapter != null) {
            checkAdapter = checkAdapter(xmlJavaTypeAdapter, parameterClass);
            if (checkAdapter != null) {
                return checkAdapter;
            }
        }
        XmlJavaTypeAdapters xmlJavaTypeAdapters = (XmlJavaTypeAdapters) findAnnotation(XmlJavaTypeAdapters.class, annotated, true, false, false);
        if (xmlJavaTypeAdapters != null) {
            XmlJavaTypeAdapter[] value = xmlJavaTypeAdapters.value();
            int length = value.length;
            while (i < length) {
                checkAdapter = checkAdapter(value[i], parameterClass);
                if (checkAdapter != null) {
                    return checkAdapter;
                }
                i++;
            }
        }
        return null;
    }

    protected XmlAdapter<Object, Object> findAdapterForClass(AnnotatedClass annotatedClass, boolean z) {
        XmlJavaTypeAdapter xmlJavaTypeAdapter = (XmlJavaTypeAdapter) annotatedClass.getAnnotated().getAnnotation(XmlJavaTypeAdapter.class);
        return xmlJavaTypeAdapter != null ? (XmlAdapter) ClassUtil.createInstance(xmlJavaTypeAdapter.value(), false) : null;
    }

    protected <A extends Annotation> A findAnnotation(Class<A> cls, Annotated annotated, boolean z, boolean z2, boolean z3) {
        A annotation;
        Class declaringClass;
        Class declaringClass2;
        if (annotated instanceof AnnotatedMethod) {
            PropertyDescriptor findPropertyDescriptor = findPropertyDescriptor((AnnotatedMethod) annotated);
            if (findPropertyDescriptor != null) {
                annotation = new AnnotatedProperty(null).getAnnotation(cls);
                if (annotation != null) {
                    return annotation;
                }
            }
        }
        AnnotatedElement annotated2 = annotated.getAnnotated();
        if (annotated instanceof AnnotatedParameter) {
            AnnotatedParameter annotatedParameter = (AnnotatedParameter) annotated;
            annotation = annotatedParameter.getAnnotation(cls);
            if (annotation != null) {
                return annotation;
            }
            declaringClass = annotatedParameter.getMember().getDeclaringClass();
        } else {
            A annotation2 = annotated2.getAnnotation(cls);
            if (annotation2 != null) {
                return annotation2;
            }
            if (annotated2 instanceof Member) {
                declaringClass2 = ((Member) annotated2).getDeclaringClass();
                if (z2) {
                    annotation = declaringClass2.getAnnotation(cls);
                    if (annotation != null) {
                        return annotation;
                    }
                }
                declaringClass = declaringClass2;
            } else if (annotated2 instanceof Class) {
                declaringClass = (Class) annotated2;
            } else {
                throw new IllegalStateException("Unsupported annotated member: " + annotated.getClass().getName());
            }
        }
        if (declaringClass != null) {
            if (z3) {
                declaringClass2 = declaringClass.getSuperclass();
                while (declaringClass2 != null && declaringClass2 != Object.class) {
                    annotation = declaringClass2.getAnnotation(cls);
                    if (annotation != null) {
                        return annotation;
                    }
                    declaringClass2 = declaringClass2.getSuperclass();
                }
            }
            if (z) {
                return declaringClass.getPackage().getAnnotation(cls);
            }
        }
        return null;
    }

    public VisibilityChecker<?> findAutoDetectVisibility(AnnotatedClass annotatedClass, VisibilityChecker<?> visibilityChecker) {
        XmlAccessType findAccessType = findAccessType(annotatedClass);
        if (findAccessType == null) {
            return visibilityChecker;
        }
        switch (C35961.$SwitchMap$javax$xml$bind$annotation$XmlAccessType[findAccessType.ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                return visibilityChecker.withFieldVisibility(Visibility.ANY).withSetterVisibility(Visibility.NONE).withGetterVisibility(Visibility.NONE).withIsGetterVisibility(Visibility.NONE);
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                return visibilityChecker.withFieldVisibility(Visibility.NONE).withSetterVisibility(Visibility.NONE).withGetterVisibility(Visibility.NONE).withIsGetterVisibility(Visibility.NONE);
            case Type.BYTE /*3*/:
                return visibilityChecker.withFieldVisibility(Visibility.NONE).withSetterVisibility(Visibility.PUBLIC_ONLY).withGetterVisibility(Visibility.PUBLIC_ONLY).withIsGetterVisibility(Visibility.PUBLIC_ONLY);
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                return visibilityChecker.withFieldVisibility(Visibility.PUBLIC_ONLY).withSetterVisibility(Visibility.PUBLIC_ONLY).withGetterVisibility(Visibility.PUBLIC_ONLY).withIsGetterVisibility(Visibility.PUBLIC_ONLY);
            default:
                return visibilityChecker;
        }
    }

    public Boolean findCachability(AnnotatedClass annotatedClass) {
        JsonCachable jsonCachable = (JsonCachable) annotatedClass.getAnnotation(JsonCachable.class);
        return jsonCachable != null ? jsonCachable.value() ? Boolean.TRUE : Boolean.FALSE : null;
    }

    public Class<JsonDeserializer<?>> findContentDeserializer(Annotated annotated) {
        return null;
    }

    public String findDeserializablePropertyName(AnnotatedField annotatedField) {
        if (isInvisible(annotatedField)) {
            return null;
        }
        Object annotated = annotatedField.getAnnotated();
        String findJaxbPropertyName = findJaxbPropertyName(annotated, annotated.getType(), C2915a.f14760f);
        return findJaxbPropertyName == null ? annotated.getName() : findJaxbPropertyName;
    }

    public Class<?> findDeserializationContentType(Annotated annotated, JavaType javaType, String str) {
        return _doFindDeserializationType(annotated, javaType, str);
    }

    public Class<?> findDeserializationKeyType(Annotated annotated, JavaType javaType, String str) {
        return null;
    }

    public Class<?> findDeserializationType(Annotated annotated, JavaType javaType, String str) {
        return !javaType.isContainerType() ? _doFindDeserializationType(annotated, javaType, str) : null;
    }

    public JsonDeserializer<?> findDeserializer(Annotated annotated, BeanProperty beanProperty) {
        XmlAdapter findAdapter = findAdapter(annotated, false);
        if (findAdapter != null) {
            return new XmlAdapterJsonDeserializer(findAdapter, beanProperty);
        }
        Class rawType = annotated.getRawType();
        return (rawType == null || this._dataHandlerDeserializer == null || !isDataHandler(rawType)) ? null : this._dataHandlerDeserializer;
    }

    public String findEnumValue(Enum<?> enumR) {
        Class declaringClass = enumR.getDeclaringClass();
        String name = enumR.name();
        try {
            XmlEnumValue xmlEnumValue = (XmlEnumValue) declaringClass.getDeclaredField(name).getAnnotation(XmlEnumValue.class);
            return xmlEnumValue != null ? xmlEnumValue.value() : name;
        } catch (Throwable e) {
            throw new IllegalStateException("Could not locate Enum entry '" + name + "' (Enum class " + declaringClass.getName() + ")", e);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected <A extends java.lang.annotation.Annotation> A findFieldAnnotation(java.lang.Class<A> r6, java.lang.Class<?> r7, java.lang.String r8) {
        /*
        r5 = this;
    L_0x0000:
        r1 = r7.getDeclaredFields();
        r2 = r1.length;
        r0 = 0;
    L_0x0006:
        if (r0 >= r2) goto L_0x001c;
    L_0x0008:
        r3 = r1[r0];
        r4 = r3.getName();
        r4 = r8.equals(r4);
        if (r4 == 0) goto L_0x0019;
    L_0x0014:
        r0 = r3.getAnnotation(r6);
    L_0x0018:
        return r0;
    L_0x0019:
        r0 = r0 + 1;
        goto L_0x0006;
    L_0x001c:
        r0 = r7.isInterface();
        if (r0 != 0) goto L_0x0026;
    L_0x0022:
        r0 = java.lang.Object.class;
        if (r7 != r0) goto L_0x0028;
    L_0x0026:
        r0 = 0;
        goto L_0x0018;
    L_0x0028:
        r7 = r7.getSuperclass();
        if (r7 != 0) goto L_0x0000;
    L_0x002e:
        goto L_0x0026;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.codehaus.jackson.xc.JaxbAnnotationIntrospector.findFieldAnnotation(java.lang.Class, java.lang.Class, java.lang.String):A");
    }

    public String findGettablePropertyName(AnnotatedMethod annotatedMethod) {
        PropertyDescriptor findPropertyDescriptor = findPropertyDescriptor(annotatedMethod);
        return findPropertyDescriptor != null ? findJaxbSpecifiedPropertyName(findPropertyDescriptor) : null;
    }

    public Boolean findIgnoreUnknownProperties(AnnotatedClass annotatedClass) {
        return null;
    }

    protected String findJaxbSpecifiedPropertyName(PropertyDescriptor propertyDescriptor) {
        return findJaxbPropertyName(new AnnotatedProperty(null), propertyDescriptor.getPropertyType(), propertyDescriptor.getName());
    }

    public Class<KeyDeserializer> findKeyDeserializer(Annotated annotated) {
        return null;
    }

    public String[] findPropertiesToIgnore(AnnotatedClass annotatedClass) {
        return null;
    }

    public TypeResolverBuilder<?> findPropertyContentTypeResolver(MapperConfig<?> mapperConfig, AnnotatedMember annotatedMember, JavaType javaType) {
        if (javaType.isContainerType()) {
            return _typeResolverFromXmlElements(annotatedMember);
        }
        throw new IllegalArgumentException("Must call method with a container type (got " + javaType + ")");
    }

    protected PropertyDescriptor findPropertyDescriptor(AnnotatedMethod annotatedMethod) {
        return getDescriptors(annotatedMethod.getDeclaringClass()).findByMethodName(annotatedMethod.getName());
    }

    public String findPropertyNameForParam(AnnotatedParameter annotatedParameter) {
        return null;
    }

    public TypeResolverBuilder<?> findPropertyTypeResolver(MapperConfig<?> mapperConfig, AnnotatedMember annotatedMember, JavaType javaType) {
        return javaType.isContainerType() ? null : _typeResolverFromXmlElements(annotatedMember);
    }

    public String findRootName(AnnotatedClass annotatedClass) {
        XmlRootElement findRootElementAnnotation = findRootElementAnnotation(annotatedClass);
        if (findRootElementAnnotation == null) {
            return null;
        }
        String name = findRootElementAnnotation.name();
        return MARKER_FOR_DEFAULT.equals(name) ? C2915a.f14760f : name;
    }

    public String findSerializablePropertyName(AnnotatedField annotatedField) {
        if (isInvisible(annotatedField)) {
            return null;
        }
        Object annotated = annotatedField.getAnnotated();
        String findJaxbPropertyName = findJaxbPropertyName(annotated, annotated.getType(), C2915a.f14760f);
        return findJaxbPropertyName == null ? annotated.getName() : findJaxbPropertyName;
    }

    public Inclusion findSerializationInclusion(Annotated annotated, Inclusion inclusion) {
        XmlElementWrapper xmlElementWrapper = (XmlElementWrapper) annotated.getAnnotation(XmlElementWrapper.class);
        if (xmlElementWrapper != null) {
            return xmlElementWrapper.nillable() ? Inclusion.ALWAYS : Inclusion.NON_NULL;
        } else {
            XmlElement xmlElement = (XmlElement) annotated.getAnnotation(XmlElement.class);
            return xmlElement != null ? xmlElement.nillable() ? Inclusion.ALWAYS : Inclusion.NON_NULL : inclusion;
        }
    }

    public String[] findSerializationPropertyOrder(AnnotatedClass annotatedClass) {
        XmlType xmlType = (XmlType) findAnnotation(XmlType.class, annotatedClass, true, true, true);
        if (xmlType == null) {
            return null;
        }
        String[] propOrder = xmlType.propOrder();
        if (propOrder == null || propOrder.length == 0) {
            return null;
        }
        PropertyDescriptors descriptors = getDescriptors(annotatedClass.getRawType());
        int length = propOrder.length;
        for (int i = 0; i < length; i++) {
            String str = propOrder[i];
            if (descriptors.findByPropertyName(str) == null && str.length() != 0) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("get");
                stringBuilder.append(Character.toUpperCase(str.charAt(0)));
                if (str.length() > 1) {
                    stringBuilder.append(str.substring(1));
                }
                PropertyDescriptor findByMethodName = descriptors.findByMethodName(stringBuilder.toString());
                if (findByMethodName != null) {
                    propOrder[i] = findByMethodName.getName();
                }
            }
        }
        return propOrder;
    }

    public Boolean findSerializationSortAlphabetically(AnnotatedClass annotatedClass) {
        boolean z = true;
        XmlAccessorOrder xmlAccessorOrder = (XmlAccessorOrder) findAnnotation(XmlAccessorOrder.class, annotatedClass, true, true, true);
        if (xmlAccessorOrder == null) {
            return null;
        }
        if (xmlAccessorOrder.value() != XmlAccessOrder.ALPHABETICAL) {
            z = false;
        }
        return Boolean.valueOf(z);
    }

    public Class<?> findSerializationType(Annotated annotated) {
        XmlElement xmlElement = (XmlElement) findAnnotation(XmlElement.class, annotated, false, false, false);
        return (xmlElement == null || xmlElement.type() == XmlElement.DEFAULT.class) ? null : isIndexedType(annotated.getRawType()) ? null : xmlElement.type();
    }

    public Typing findSerializationTyping(Annotated annotated) {
        return null;
    }

    public Class<?>[] findSerializationViews(Annotated annotated) {
        return null;
    }

    public JsonSerializer<?> findSerializer(Annotated annotated, BeanProperty beanProperty) {
        XmlAdapter findAdapter = findAdapter(annotated, true);
        if (findAdapter != null) {
            return new XmlAdapterJsonSerializer(findAdapter, beanProperty);
        }
        Class rawType = annotated.getRawType();
        return (rawType == null || this._dataHandlerSerializer == null || !isDataHandler(rawType)) ? null : this._dataHandlerSerializer;
    }

    public String findSettablePropertyName(AnnotatedMethod annotatedMethod) {
        PropertyDescriptor findPropertyDescriptor = findPropertyDescriptor(annotatedMethod);
        return findPropertyDescriptor != null ? findJaxbSpecifiedPropertyName(findPropertyDescriptor) : null;
    }

    public List<NamedType> findSubtypes(Annotated annotated) {
        int i = 0;
        XmlElements xmlElements = (XmlElements) findAnnotation(XmlElements.class, annotated, false, false, false);
        int length;
        String name;
        if (xmlElements != null) {
            ArrayList arrayList = new ArrayList();
            XmlElement[] value = xmlElements.value();
            length = value.length;
            while (i < length) {
                XmlElement xmlElement = value[i];
                name = xmlElement.name();
                if (MARKER_FOR_DEFAULT.equals(name)) {
                    name = null;
                }
                arrayList.add(new NamedType(xmlElement.type(), name));
                i++;
            }
            return arrayList;
        }
        XmlElementRefs xmlElementRefs = (XmlElementRefs) findAnnotation(XmlElementRefs.class, annotated, false, false, false);
        if (xmlElementRefs == null) {
            return null;
        }
        List arrayList2 = new ArrayList();
        XmlElementRef[] value2 = xmlElementRefs.value();
        length = value2.length;
        while (i < length) {
            XmlElementRef xmlElementRef = value2[i];
            Class type = xmlElementRef.type();
            if (!JAXBElement.class.isAssignableFrom(type)) {
                String name2 = xmlElementRef.name();
                if (name2 == null || MARKER_FOR_DEFAULT.equals(name2)) {
                    XmlRootElement xmlRootElement = (XmlRootElement) type.getAnnotation(XmlRootElement.class);
                    if (xmlRootElement != null) {
                        name = xmlRootElement.name();
                        if (name == null || MARKER_FOR_DEFAULT.equals(name)) {
                            name = Introspector.decapitalize(type.getSimpleName());
                        }
                        arrayList2.add(new NamedType(type, name));
                    }
                }
                name = name2;
                name = Introspector.decapitalize(type.getSimpleName());
                arrayList2.add(new NamedType(type, name));
            }
            i++;
        }
        return arrayList2;
    }

    public String findTypeName(AnnotatedClass annotatedClass) {
        XmlType xmlType = (XmlType) findAnnotation(XmlType.class, annotatedClass, false, false, false);
        if (xmlType != null) {
            String name = xmlType.name();
            if (!MARKER_FOR_DEFAULT.equals(name)) {
                return name;
            }
        }
        return null;
    }

    public TypeResolverBuilder<?> findTypeResolver(MapperConfig<?> mapperConfig, AnnotatedClass annotatedClass, JavaType javaType) {
        return null;
    }

    protected PropertyDescriptors getDescriptors(Class<?> cls) {
        SoftReference softReference = (SoftReference) _propertyDescriptors.get();
        PropertyDescriptors propertyDescriptors = softReference == null ? null : (PropertyDescriptors) softReference.get();
        if (propertyDescriptors == null || propertyDescriptors.getBeanClass() != cls) {
            try {
                propertyDescriptors = PropertyDescriptors.find(cls);
                _propertyDescriptors.set(new SoftReference(propertyDescriptors));
            } catch (IntrospectionException e) {
                throw new IllegalArgumentException("Problem introspecting bean properties: " + e.getMessage(), e);
            }
        }
        return propertyDescriptors;
    }

    public boolean hasAnySetterAnnotation(AnnotatedMethod annotatedMethod) {
        return false;
    }

    public boolean hasAsValueAnnotation(AnnotatedMethod annotatedMethod) {
        return false;
    }

    public boolean hasCreatorAnnotation(Annotated annotated) {
        return false;
    }

    public boolean isHandled(Annotation annotation) {
        Class annotationType = annotation.annotationType();
        Package packageR = annotationType.getPackage();
        return (packageR != null ? packageR.getName() : annotationType.getName()).startsWith(this._jaxbPackageName) ? true : annotationType == JsonCachable.class;
    }

    public boolean isIgnorableConstructor(AnnotatedConstructor annotatedConstructor) {
        return false;
    }

    public boolean isIgnorableField(AnnotatedField annotatedField) {
        return annotatedField.getAnnotation(XmlTransient.class) != null;
    }

    public boolean isIgnorableMethod(AnnotatedMethod annotatedMethod) {
        return annotatedMethod.getAnnotation(XmlTransient.class) != null;
    }

    public Boolean isIgnorableType(AnnotatedClass annotatedClass) {
        return null;
    }

    protected boolean isIndexedType(Class<?> cls) {
        return cls.isArray() || Collection.class.isAssignableFrom(cls) || Map.class.isAssignableFrom(cls);
    }

    protected boolean isInvisible(AnnotatedField annotatedField) {
        boolean z = true;
        for (Annotation isHandled : annotatedField.getAnnotated().getDeclaredAnnotations()) {
            if (isHandled(isHandled)) {
                z = false;
            }
        }
        if (!z) {
            return z;
        }
        XmlAccessorType xmlAccessorType = (XmlAccessorType) findAnnotation(XmlAccessorType.class, annotatedField, true, true, true);
        XmlAccessType value = xmlAccessorType != null ? xmlAccessorType.value() : XmlAccessType.PUBLIC_MEMBER;
        return (value == XmlAccessType.FIELD || (value == XmlAccessType.PUBLIC_MEMBER && Modifier.isPublic(annotatedField.getAnnotated().getModifiers()))) ? false : true;
    }

    public Version version() {
        return VersionUtil.versionFor(getClass());
    }
}
