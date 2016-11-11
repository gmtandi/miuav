package org.codehaus.jackson.map.ser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.AnnotationIntrospector.ReferenceProperty;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.BeanProperty.Std;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.MapperConfig;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.SerializationConfig.Feature;
import org.codehaus.jackson.map.SerializerFactory;
import org.codehaus.jackson.map.SerializerFactory.Config;
import org.codehaus.jackson.map.Serializers;
import org.codehaus.jackson.map.TypeSerializer;
import org.codehaus.jackson.map.introspect.AnnotatedClass;
import org.codehaus.jackson.map.introspect.AnnotatedMember;
import org.codehaus.jackson.map.introspect.AnnotatedMethod;
import org.codehaus.jackson.map.introspect.BasicBeanDescription;
import org.codehaus.jackson.map.introspect.VisibilityChecker;
import org.codehaus.jackson.map.jsontype.TypeResolverBuilder;
import org.codehaus.jackson.map.type.TypeBindings;
import org.codehaus.jackson.map.util.ArrayBuilders;
import org.codehaus.jackson.map.util.ClassUtil;
import org.codehaus.jackson.type.JavaType;

public class BeanSerializerFactory extends BasicSerializerFactory {
    public static final BeanSerializerFactory instance;
    protected final Config _factoryConfig;

    public class ConfigImpl extends Config {
        protected static final BeanSerializerModifier[] NO_MODIFIERS;
        protected static final Serializers[] NO_SERIALIZERS;
        protected final Serializers[] _additionalKeySerializers;
        protected final Serializers[] _additionalSerializers;
        protected final BeanSerializerModifier[] _modifiers;

        static {
            NO_SERIALIZERS = new Serializers[0];
            NO_MODIFIERS = new BeanSerializerModifier[0];
        }

        public ConfigImpl() {
            this(null, null, null);
        }

        protected ConfigImpl(Serializers[] serializersArr, Serializers[] serializersArr2, BeanSerializerModifier[] beanSerializerModifierArr) {
            if (serializersArr == null) {
                serializersArr = NO_SERIALIZERS;
            }
            this._additionalSerializers = serializersArr;
            if (serializersArr2 == null) {
                serializersArr2 = NO_SERIALIZERS;
            }
            this._additionalKeySerializers = serializersArr2;
            if (beanSerializerModifierArr == null) {
                beanSerializerModifierArr = NO_MODIFIERS;
            }
            this._modifiers = beanSerializerModifierArr;
        }

        public boolean hasKeySerializers() {
            return this._additionalKeySerializers.length > 0;
        }

        public boolean hasSerializerModifiers() {
            return this._modifiers.length > 0;
        }

        public boolean hasSerializers() {
            return this._additionalSerializers.length > 0;
        }

        public Iterable<Serializers> keySerializers() {
            return ArrayBuilders.arrayAsIterable(this._additionalKeySerializers);
        }

        public Iterable<BeanSerializerModifier> serializerModifiers() {
            return ArrayBuilders.arrayAsIterable(this._modifiers);
        }

        public Iterable<Serializers> serializers() {
            return ArrayBuilders.arrayAsIterable(this._additionalSerializers);
        }

        public Config withAdditionalKeySerializers(Serializers serializers) {
            if (serializers == null) {
                throw new IllegalArgumentException("Can not pass null Serializers");
            }
            return new ConfigImpl(this._additionalSerializers, (Serializers[]) ArrayBuilders.insertInListNoDup(this._additionalKeySerializers, serializers), this._modifiers);
        }

        public Config withAdditionalSerializers(Serializers serializers) {
            if (serializers != null) {
                return new ConfigImpl((Serializers[]) ArrayBuilders.insertInListNoDup(this._additionalSerializers, serializers), this._additionalKeySerializers, this._modifiers);
            }
            throw new IllegalArgumentException("Can not pass null Serializers");
        }

        public Config withSerializerModifier(BeanSerializerModifier beanSerializerModifier) {
            if (beanSerializerModifier == null) {
                throw new IllegalArgumentException("Can not pass null modifier");
            }
            return new ConfigImpl(this._additionalSerializers, this._additionalKeySerializers, (BeanSerializerModifier[]) ArrayBuilders.insertInListNoDup(this._modifiers, beanSerializerModifier));
        }
    }

    static {
        instance = new BeanSerializerFactory(null);
    }

    @Deprecated
    protected BeanSerializerFactory() {
        this(null);
    }

    protected BeanSerializerFactory(Config config) {
        if (config == null) {
            config = new ConfigImpl();
        }
        this._factoryConfig = config;
    }

    protected BeanPropertyWriter _constructWriter(SerializationConfig serializationConfig, TypeBindings typeBindings, PropertyBuilder propertyBuilder, boolean z, String str, AnnotatedMember annotatedMember) {
        if (serializationConfig.isEnabled(Feature.CAN_OVERRIDE_ACCESS_MODIFIERS)) {
            annotatedMember.fixAccess();
        }
        JavaType type = annotatedMember.getType(typeBindings);
        BeanProperty std = new Std(str, type, propertyBuilder.getClassAnnotations(), annotatedMember);
        JsonSerializer findSerializerFromAnnotation = findSerializerFromAnnotation(serializationConfig, annotatedMember, std);
        TypeSerializer typeSerializer = null;
        if (ClassUtil.isCollectionMapOrArray(type.getRawClass())) {
            typeSerializer = findPropertyContentTypeSerializer(type, serializationConfig, annotatedMember, std);
        }
        BeanPropertyWriter buildWriter = propertyBuilder.buildWriter(str, type, findSerializerFromAnnotation, findPropertyTypeSerializer(type, serializationConfig, annotatedMember, std), typeSerializer, annotatedMember, z);
        buildWriter.setViews(serializationConfig.getAnnotationIntrospector().findSerializationViews(annotatedMember));
        return buildWriter;
    }

    protected List<BeanPropertyWriter> _sortBeanProperties(List<BeanPropertyWriter> list, List<String> list2, String[] strArr, boolean z) {
        Map treeMap;
        int size = list.size();
        if (z) {
            treeMap = new TreeMap();
        } else {
            Object linkedHashMap = new LinkedHashMap(size * 2);
        }
        for (BeanPropertyWriter beanPropertyWriter : list) {
            BeanPropertyWriter beanPropertyWriter2;
            treeMap.put(beanPropertyWriter2.getName(), beanPropertyWriter2);
        }
        Map linkedHashMap2 = new LinkedHashMap(size * 2);
        if (strArr != null) {
            for (Object obj : strArr) {
                beanPropertyWriter2 = (BeanPropertyWriter) treeMap.get(obj);
                if (beanPropertyWriter2 != null) {
                    linkedHashMap2.put(obj, beanPropertyWriter2);
                }
            }
        }
        for (String str : list2) {
            BeanPropertyWriter beanPropertyWriter3 = (BeanPropertyWriter) treeMap.get(str);
            if (beanPropertyWriter3 != null) {
                linkedHashMap2.put(str, beanPropertyWriter3);
            }
        }
        linkedHashMap2.putAll(treeMap);
        return new ArrayList(linkedHashMap2.values());
    }

    protected JsonSerializer<Object> constructBeanSerializer(SerializationConfig serializationConfig, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty) {
        if (basicBeanDescription.getBeanClass() == Object.class) {
            throw new IllegalArgumentException("Can not create bean serializer for Object.class");
        }
        List list;
        BeanSerializerBuilder beanSerializerBuilder;
        BeanSerializerBuilder constructBeanSerializerBuilder = constructBeanSerializerBuilder(basicBeanDescription);
        List findBeanProperties = findBeanProperties(serializationConfig, basicBeanDescription);
        AnnotatedMethod findAnyGetter = basicBeanDescription.findAnyGetter();
        if (this._factoryConfig.hasSerializerModifiers()) {
            if (findBeanProperties == null) {
                findBeanProperties = new ArrayList();
            }
            list = findBeanProperties;
            for (BeanSerializerModifier changeProperties : this._factoryConfig.serializerModifiers()) {
                list = changeProperties.changeProperties(serializationConfig, basicBeanDescription, list);
            }
        } else {
            list = findBeanProperties;
        }
        if (list != null && list.size() != 0) {
            findBeanProperties = sortBeanProperties(serializationConfig, basicBeanDescription, filterBeanProperties(serializationConfig, basicBeanDescription, list));
        } else if (findAnyGetter == null) {
            return basicBeanDescription.hasKnownClassAnnotations() ? constructBeanSerializerBuilder.createDummy() : null;
        } else {
            findBeanProperties = Collections.emptyList();
        }
        if (this._factoryConfig.hasSerializerModifiers()) {
            list = findBeanProperties;
            for (BeanSerializerModifier changeProperties2 : this._factoryConfig.serializerModifiers()) {
                list = changeProperties2.orderProperties(serializationConfig, basicBeanDescription, list);
            }
        } else {
            list = findBeanProperties;
        }
        constructBeanSerializerBuilder.setProperties(list);
        constructBeanSerializerBuilder.setFilterId(findFilterId(serializationConfig, basicBeanDescription));
        if (findAnyGetter != null) {
            JavaType type = findAnyGetter.getType(basicBeanDescription.bindingsForBeanType());
            constructBeanSerializerBuilder.setAnyGetter(new AnyGetterWriter(findAnyGetter, MapSerializer.construct(null, type, serializationConfig.isEnabled(Feature.USE_STATIC_TYPING), createTypeSerializer(serializationConfig, type.getContentType(), beanProperty), beanProperty, null, null)));
        }
        processViews(serializationConfig, constructBeanSerializerBuilder);
        if (this._factoryConfig.hasSerializerModifiers()) {
            beanSerializerBuilder = constructBeanSerializerBuilder;
            for (BeanSerializerModifier updateBuilder : this._factoryConfig.serializerModifiers()) {
                beanSerializerBuilder = updateBuilder.updateBuilder(serializationConfig, basicBeanDescription, beanSerializerBuilder);
            }
        } else {
            beanSerializerBuilder = constructBeanSerializerBuilder;
        }
        return beanSerializerBuilder.build();
    }

    protected BeanSerializerBuilder constructBeanSerializerBuilder(BasicBeanDescription basicBeanDescription) {
        return new BeanSerializerBuilder(basicBeanDescription);
    }

    protected BeanPropertyWriter constructFilteredBeanWriter(BeanPropertyWriter beanPropertyWriter, Class<?>[] clsArr) {
        return FilteredBeanPropertyWriter.constructViewBased(beanPropertyWriter, clsArr);
    }

    protected PropertyBuilder constructPropertyBuilder(SerializationConfig serializationConfig, BasicBeanDescription basicBeanDescription) {
        return new PropertyBuilder(serializationConfig, basicBeanDescription);
    }

    public JsonSerializer<Object> createKeySerializer(SerializationConfig serializationConfig, JavaType javaType, BeanProperty beanProperty) {
        JsonSerializer<Object> jsonSerializer = null;
        if (this._factoryConfig.hasKeySerializers()) {
            BasicBeanDescription basicBeanDescription = (BasicBeanDescription) serializationConfig.introspectClassAnnotations(javaType.getRawClass());
            for (Serializers findSerializer : this._factoryConfig.keySerializers()) {
                jsonSerializer = findSerializer.findSerializer(serializationConfig, javaType, basicBeanDescription, beanProperty);
                if (jsonSerializer != null) {
                    break;
                }
            }
        }
        return jsonSerializer;
    }

    public JsonSerializer<Object> createSerializer(SerializationConfig serializationConfig, JavaType javaType, BeanProperty beanProperty) {
        BasicBeanDescription basicBeanDescription = (BasicBeanDescription) serializationConfig.introspect(javaType);
        JsonSerializer<Object> findSerializerFromAnnotation = findSerializerFromAnnotation(serializationConfig, basicBeanDescription.getClassInfo(), beanProperty);
        if (findSerializerFromAnnotation != null) {
            return findSerializerFromAnnotation;
        }
        JavaType modifyTypeByAnnotation = modifyTypeByAnnotation(serializationConfig, basicBeanDescription.getClassInfo(), javaType);
        boolean z = modifyTypeByAnnotation != javaType;
        if (javaType.isContainerType()) {
            return buildContainerSerializer(serializationConfig, modifyTypeByAnnotation, basicBeanDescription, beanProperty, z);
        }
        for (Serializers findSerializer : this._factoryConfig.serializers()) {
            findSerializerFromAnnotation = findSerializer.findSerializer(serializationConfig, modifyTypeByAnnotation, basicBeanDescription, beanProperty);
            if (findSerializerFromAnnotation != null) {
                return findSerializerFromAnnotation;
            }
        }
        findSerializerFromAnnotation = findSerializerByLookup(modifyTypeByAnnotation, serializationConfig, basicBeanDescription, beanProperty, z);
        if (findSerializerFromAnnotation != null) {
            return findSerializerFromAnnotation;
        }
        findSerializerFromAnnotation = findSerializerByPrimaryType(modifyTypeByAnnotation, serializationConfig, basicBeanDescription, beanProperty, z);
        if (findSerializerFromAnnotation != null) {
            return findSerializerFromAnnotation;
        }
        findSerializerFromAnnotation = findBeanSerializer(serializationConfig, modifyTypeByAnnotation, basicBeanDescription, beanProperty);
        return findSerializerFromAnnotation == null ? super.findSerializerByAddonType(serializationConfig, modifyTypeByAnnotation, basicBeanDescription, beanProperty, z) : findSerializerFromAnnotation;
    }

    protected Iterable<Serializers> customSerializers() {
        return this._factoryConfig.serializers();
    }

    protected List<BeanPropertyWriter> filterBeanProperties(SerializationConfig serializationConfig, BasicBeanDescription basicBeanDescription, List<BeanPropertyWriter> list) {
        String[] findPropertiesToIgnore = serializationConfig.getAnnotationIntrospector().findPropertiesToIgnore(basicBeanDescription.getClassInfo());
        if (findPropertiesToIgnore != null && findPropertiesToIgnore.length > 0) {
            HashSet arrayToSet = ArrayBuilders.arrayToSet(findPropertiesToIgnore);
            Iterator it = list.iterator();
            while (it.hasNext()) {
                if (arrayToSet.contains(((BeanPropertyWriter) it.next()).getName())) {
                    it.remove();
                }
            }
        }
        return list;
    }

    protected List<BeanPropertyWriter> findBeanProperties(SerializationConfig serializationConfig, BasicBeanDescription basicBeanDescription) {
        VisibilityChecker defaultVisibilityChecker = serializationConfig.getDefaultVisibilityChecker();
        if (!serializationConfig.isEnabled(Feature.AUTO_DETECT_GETTERS)) {
            defaultVisibilityChecker = defaultVisibilityChecker.withGetterVisibility(Visibility.NONE);
        }
        if (!serializationConfig.isEnabled(Feature.AUTO_DETECT_IS_GETTERS)) {
            defaultVisibilityChecker = defaultVisibilityChecker.withIsGetterVisibility(Visibility.NONE);
        }
        if (!serializationConfig.isEnabled(Feature.AUTO_DETECT_FIELDS)) {
            defaultVisibilityChecker = defaultVisibilityChecker.withFieldVisibility(Visibility.NONE);
        }
        AnnotationIntrospector annotationIntrospector = serializationConfig.getAnnotationIntrospector();
        defaultVisibilityChecker = annotationIntrospector.findAutoDetectVisibility(basicBeanDescription.getClassInfo(), defaultVisibilityChecker);
        LinkedHashMap findGetters = basicBeanDescription.findGetters(defaultVisibilityChecker, null);
        LinkedHashMap findSerializableFields = basicBeanDescription.findSerializableFields(defaultVisibilityChecker, findGetters.keySet());
        removeIgnorableTypes(serializationConfig, basicBeanDescription, findGetters);
        removeIgnorableTypes(serializationConfig, basicBeanDescription, findSerializableFields);
        if (findGetters.isEmpty() && findSerializableFields.isEmpty()) {
            return null;
        }
        boolean usesStaticTyping = usesStaticTyping(serializationConfig, basicBeanDescription, null, null);
        PropertyBuilder constructPropertyBuilder = constructPropertyBuilder(serializationConfig, basicBeanDescription);
        ArrayList arrayList = new ArrayList(findGetters.size());
        TypeBindings bindingsForBeanType = basicBeanDescription.bindingsForBeanType();
        for (Entry entry : findSerializableFields.entrySet()) {
            ReferenceProperty findReferenceType = annotationIntrospector.findReferenceType((AnnotatedMember) entry.getValue());
            if (findReferenceType == null || !findReferenceType.isBackReference()) {
                arrayList.add(_constructWriter(serializationConfig, bindingsForBeanType, constructPropertyBuilder, usesStaticTyping, (String) entry.getKey(), (AnnotatedMember) entry.getValue()));
            }
        }
        for (Entry entry2 : findGetters.entrySet()) {
            findReferenceType = annotationIntrospector.findReferenceType((AnnotatedMember) entry2.getValue());
            if (findReferenceType == null || !findReferenceType.isBackReference()) {
                arrayList.add(_constructWriter(serializationConfig, bindingsForBeanType, constructPropertyBuilder, usesStaticTyping, (String) entry2.getKey(), (AnnotatedMember) entry2.getValue()));
            }
        }
        return arrayList;
    }

    public JsonSerializer<Object> findBeanSerializer(SerializationConfig serializationConfig, JavaType javaType, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty) {
        if (!isPotentialBeanType(javaType.getRawClass())) {
            return null;
        }
        JsonSerializer<Object> constructBeanSerializer = constructBeanSerializer(serializationConfig, basicBeanDescription, beanProperty);
        if (!this._factoryConfig.hasSerializerModifiers()) {
            return constructBeanSerializer;
        }
        JsonSerializer<Object> jsonSerializer = constructBeanSerializer;
        for (BeanSerializerModifier modifySerializer : this._factoryConfig.serializerModifiers()) {
            jsonSerializer = modifySerializer.modifySerializer(serializationConfig, basicBeanDescription, jsonSerializer);
        }
        return jsonSerializer;
    }

    protected Object findFilterId(SerializationConfig serializationConfig, BasicBeanDescription basicBeanDescription) {
        return serializationConfig.getAnnotationIntrospector().findFilterId(basicBeanDescription.getClassInfo());
    }

    public TypeSerializer findPropertyContentTypeSerializer(JavaType javaType, SerializationConfig serializationConfig, AnnotatedMember annotatedMember, BeanProperty beanProperty) {
        JavaType contentType = javaType.getContentType();
        AnnotationIntrospector annotationIntrospector = serializationConfig.getAnnotationIntrospector();
        TypeResolverBuilder findPropertyContentTypeResolver = annotationIntrospector.findPropertyContentTypeResolver(serializationConfig, annotatedMember, javaType);
        return findPropertyContentTypeResolver == null ? createTypeSerializer(serializationConfig, contentType, beanProperty) : findPropertyContentTypeResolver.buildTypeSerializer(serializationConfig, contentType, serializationConfig.getSubtypeResolver().collectAndResolveSubtypes(annotatedMember, (MapperConfig) serializationConfig, annotationIntrospector), beanProperty);
    }

    public TypeSerializer findPropertyTypeSerializer(JavaType javaType, SerializationConfig serializationConfig, AnnotatedMember annotatedMember, BeanProperty beanProperty) {
        AnnotationIntrospector annotationIntrospector = serializationConfig.getAnnotationIntrospector();
        TypeResolverBuilder findPropertyTypeResolver = annotationIntrospector.findPropertyTypeResolver(serializationConfig, annotatedMember, javaType);
        return findPropertyTypeResolver == null ? createTypeSerializer(serializationConfig, javaType, beanProperty) : findPropertyTypeResolver.buildTypeSerializer(serializationConfig, javaType, serializationConfig.getSubtypeResolver().collectAndResolveSubtypes(annotatedMember, (MapperConfig) serializationConfig, annotationIntrospector), beanProperty);
    }

    public Config getConfig() {
        return this._factoryConfig;
    }

    protected boolean isPotentialBeanType(Class<?> cls) {
        return ClassUtil.canBeABeanType(cls) == null && !ClassUtil.isProxyType(cls);
    }

    protected void processViews(SerializationConfig serializationConfig, BeanSerializerBuilder beanSerializerBuilder) {
        List properties = beanSerializerBuilder.getProperties();
        boolean isEnabled = serializationConfig.isEnabled(Feature.DEFAULT_VIEW_INCLUSION);
        int size = properties.size();
        BeanPropertyWriter[] beanPropertyWriterArr = new BeanPropertyWriter[size];
        int i = 0;
        int i2 = 0;
        while (i < size) {
            int i3;
            BeanPropertyWriter beanPropertyWriter = (BeanPropertyWriter) properties.get(i);
            Class[] views = beanPropertyWriter.getViews();
            if (views == null) {
                if (isEnabled) {
                    beanPropertyWriterArr[i] = beanPropertyWriter;
                    i3 = i2;
                }
                i3 = i2;
            } else {
                i2++;
                beanPropertyWriterArr[i] = constructFilteredBeanWriter(beanPropertyWriter, views);
                i3 = i2;
            }
            i++;
            i2 = i3;
        }
        if (!isEnabled || i2 != 0) {
            beanSerializerBuilder.setFilteredProperties(beanPropertyWriterArr);
        }
    }

    protected <T extends AnnotatedMember> void removeIgnorableTypes(SerializationConfig serializationConfig, BasicBeanDescription basicBeanDescription, Map<String, T> map) {
        if (!map.isEmpty()) {
            AnnotationIntrospector annotationIntrospector = serializationConfig.getAnnotationIntrospector();
            Iterator it = map.entrySet().iterator();
            HashMap hashMap = new HashMap();
            while (it.hasNext()) {
                Class rawType = ((AnnotatedMember) ((Entry) it.next()).getValue()).getRawType();
                Boolean bool = (Boolean) hashMap.get(rawType);
                if (bool == null) {
                    bool = annotationIntrospector.isIgnorableType(((BasicBeanDescription) serializationConfig.introspectClassAnnotations(rawType)).getClassInfo());
                    if (bool == null) {
                        bool = Boolean.FALSE;
                    }
                    hashMap.put(rawType, bool);
                }
                if (bool.booleanValue()) {
                    it.remove();
                }
            }
        }
    }

    protected List<BeanPropertyWriter> sortBeanProperties(SerializationConfig serializationConfig, BasicBeanDescription basicBeanDescription, List<BeanPropertyWriter> list) {
        List findCreatorPropertyNames = basicBeanDescription.findCreatorPropertyNames();
        AnnotationIntrospector annotationIntrospector = serializationConfig.getAnnotationIntrospector();
        AnnotatedClass classInfo = basicBeanDescription.getClassInfo();
        String[] findSerializationPropertyOrder = annotationIntrospector.findSerializationPropertyOrder(classInfo);
        Boolean findSerializationSortAlphabetically = annotationIntrospector.findSerializationSortAlphabetically(classInfo);
        boolean isEnabled = findSerializationSortAlphabetically == null ? serializationConfig.isEnabled(Feature.SORT_PROPERTIES_ALPHABETICALLY) : findSerializationSortAlphabetically.booleanValue();
        return (!isEnabled && findCreatorPropertyNames.isEmpty() && findSerializationPropertyOrder == null) ? list : _sortBeanProperties(list, findCreatorPropertyNames, findSerializationPropertyOrder, isEnabled);
    }

    public SerializerFactory withConfig(Config config) {
        if (this._factoryConfig == config) {
            return this;
        }
        if (getClass() == BeanSerializerFactory.class) {
            return new BeanSerializerFactory(config);
        }
        throw new IllegalStateException("Subtype of BeanSerializerFactory (" + getClass().getName() + ") has not properly overridden method 'withAdditionalSerializers': can not instantiate subtype with " + "additional serializer definitions");
    }
}
