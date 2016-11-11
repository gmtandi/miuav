package org.codehaus.jackson.map.deser;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicReference;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.BeanProperty.Std;
import org.codehaus.jackson.map.ContextualDeserializer;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.DeserializerFactory;
import org.codehaus.jackson.map.DeserializerFactory.Config;
import org.codehaus.jackson.map.DeserializerProvider;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.KeyDeserializer;
import org.codehaus.jackson.map.KeyDeserializer.None;
import org.codehaus.jackson.map.MapperConfig;
import org.codehaus.jackson.map.TypeDeserializer;
import org.codehaus.jackson.map.deser.SettableBeanProperty.CreatorProperty;
import org.codehaus.jackson.map.deser.StdDeserializer.AtomicReferenceDeserializer;
import org.codehaus.jackson.map.deser.StdDeserializer.ClassDeserializer;
import org.codehaus.jackson.map.deser.impl.StringCollectionDeserializer;
import org.codehaus.jackson.map.ext.OptionalHandlerFactory;
import org.codehaus.jackson.map.introspect.Annotated;
import org.codehaus.jackson.map.introspect.AnnotatedClass;
import org.codehaus.jackson.map.introspect.AnnotatedConstructor;
import org.codehaus.jackson.map.introspect.AnnotatedMember;
import org.codehaus.jackson.map.introspect.AnnotatedMethod;
import org.codehaus.jackson.map.introspect.AnnotatedParameter;
import org.codehaus.jackson.map.introspect.BasicBeanDescription;
import org.codehaus.jackson.map.jsontype.TypeResolverBuilder;
import org.codehaus.jackson.map.type.ArrayType;
import org.codehaus.jackson.map.type.CollectionLikeType;
import org.codehaus.jackson.map.type.CollectionType;
import org.codehaus.jackson.map.type.MapLikeType;
import org.codehaus.jackson.map.type.MapType;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.map.util.ClassUtil;
import org.codehaus.jackson.type.JavaType;

public abstract class BasicDeserializerFactory extends DeserializerFactory {
    protected static final HashMap<JavaType, JsonDeserializer<Object>> _arrayDeserializers;
    static final HashMap<String, Class<? extends Collection>> _collectionFallbacks;
    static final HashMap<String, Class<? extends Map>> _mapFallbacks;
    static final HashMap<JavaType, JsonDeserializer<Object>> _simpleDeserializers;
    protected OptionalHandlerFactory optionalHandlers;

    static {
        _simpleDeserializers = StdDeserializers.constructAll();
        _mapFallbacks = new HashMap();
        _mapFallbacks.put(Map.class.getName(), LinkedHashMap.class);
        _mapFallbacks.put(ConcurrentMap.class.getName(), ConcurrentHashMap.class);
        _mapFallbacks.put(SortedMap.class.getName(), TreeMap.class);
        _mapFallbacks.put("java.util.NavigableMap", TreeMap.class);
        try {
            Class cls = Class.forName("java.util.ConcurrentNavigableMap");
            _mapFallbacks.put(cls.getName(), Class.forName("java.util.ConcurrentSkipListMap"));
        } catch (ClassNotFoundException e) {
        }
        _collectionFallbacks = new HashMap();
        _collectionFallbacks.put(Collection.class.getName(), ArrayList.class);
        _collectionFallbacks.put(List.class.getName(), ArrayList.class);
        _collectionFallbacks.put(Set.class.getName(), HashSet.class);
        _collectionFallbacks.put(SortedSet.class.getName(), TreeSet.class);
        _collectionFallbacks.put(Queue.class.getName(), LinkedList.class);
        _collectionFallbacks.put("java.util.Deque", LinkedList.class);
        _collectionFallbacks.put("java.util.NavigableSet", TreeSet.class);
        _arrayDeserializers = ArrayDeserializers.getAll();
    }

    protected BasicDeserializerFactory() {
        this.optionalHandlers = OptionalHandlerFactory.instance;
    }

    JsonDeserializer<Object> _constructDeserializer(DeserializationConfig deserializationConfig, Annotated annotated, BeanProperty beanProperty, Object obj) {
        if (obj instanceof JsonDeserializer) {
            JsonDeserializer<Object> jsonDeserializer = (JsonDeserializer) obj;
            return jsonDeserializer instanceof ContextualDeserializer ? ((ContextualDeserializer) jsonDeserializer).createContextual(deserializationConfig, beanProperty) : jsonDeserializer;
        } else if (obj instanceof Class) {
            Class cls = (Class) obj;
            if (JsonDeserializer.class.isAssignableFrom(cls)) {
                JsonDeserializer<Object> deserializerInstance = deserializationConfig.deserializerInstance(annotated, cls);
                if (deserializerInstance instanceof ContextualDeserializer) {
                    deserializerInstance = ((ContextualDeserializer) deserializerInstance).createContextual(deserializationConfig, beanProperty);
                }
                return deserializerInstance;
            }
            throw new IllegalStateException("AnnotationIntrospector returned Class " + cls.getName() + "; expected Class<JsonDeserializer>");
        } else {
            throw new IllegalStateException("AnnotationIntrospector returned deserializer definition of type " + obj.getClass().getName() + "; expected type JsonDeserializer or Class<JsonDeserializer> instead");
        }
    }

    protected abstract JsonDeserializer<?> _findCustomArrayDeserializer(ArrayType arrayType, DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, BeanProperty beanProperty, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer);

    protected abstract JsonDeserializer<?> _findCustomCollectionDeserializer(CollectionType collectionType, DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer);

    protected abstract JsonDeserializer<?> _findCustomCollectionLikeDeserializer(CollectionLikeType collectionLikeType, DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer);

    protected abstract JsonDeserializer<?> _findCustomEnumDeserializer(Class<?> cls, DeserializationConfig deserializationConfig, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty);

    protected abstract JsonDeserializer<?> _findCustomMapDeserializer(MapType mapType, DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, KeyDeserializer keyDeserializer, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer);

    protected abstract JsonDeserializer<?> _findCustomMapLikeDeserializer(MapLikeType mapLikeType, DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, KeyDeserializer keyDeserializer, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer);

    protected abstract JsonDeserializer<?> _findCustomTreeNodeDeserializer(Class<? extends JsonNode> cls, DeserializationConfig deserializationConfig, BeanProperty beanProperty);

    protected SettableBeanProperty constructCreatorProperty(DeserializationConfig deserializationConfig, BasicBeanDescription basicBeanDescription, String str, int i, AnnotatedParameter annotatedParameter) {
        JavaType constructType = deserializationConfig.getTypeFactory().constructType(annotatedParameter.getParameterType(), basicBeanDescription.bindingsForBeanType());
        BeanProperty std = new Std(str, constructType, basicBeanDescription.getClassAnnotations(), annotatedParameter);
        JavaType resolveType = resolveType(deserializationConfig, basicBeanDescription, constructType, annotatedParameter, std);
        if (resolveType != constructType) {
            std = std.withType(resolveType);
        }
        JsonDeserializer findDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationConfig, annotatedParameter, std);
        JavaType modifyTypeByAnnotation = modifyTypeByAnnotation(deserializationConfig, annotatedParameter, resolveType, str);
        SettableBeanProperty creatorProperty = new CreatorProperty(str, modifyTypeByAnnotation, findTypeDeserializer(deserializationConfig, modifyTypeByAnnotation, std), basicBeanDescription.getClassAnnotations(), annotatedParameter, i);
        if (findDeserializerFromAnnotation != null) {
            creatorProperty.setValueDeserializer(findDeserializerFromAnnotation);
        }
        return creatorProperty;
    }

    protected EnumResolver<?> constructEnumResolver(Class<?> cls, DeserializationConfig deserializationConfig) {
        return deserializationConfig.isEnabled(Feature.READ_ENUMS_USING_TO_STRING) ? EnumResolver.constructUnsafeUsingToString(cls) : EnumResolver.constructUnsafe(cls, deserializationConfig.getAnnotationIntrospector());
    }

    public JsonDeserializer<?> createArrayDeserializer(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, ArrayType arrayType, BeanProperty beanProperty) {
        JsonDeserializer<?> jsonDeserializer;
        JavaType contentType = arrayType.getContentType();
        JsonDeserializer jsonDeserializer2 = (JsonDeserializer) contentType.getValueHandler();
        if (jsonDeserializer2 == null) {
            jsonDeserializer = (JsonDeserializer) _arrayDeserializers.get(contentType);
            if (jsonDeserializer != null) {
                JsonDeserializer<?> _findCustomArrayDeserializer = _findCustomArrayDeserializer(arrayType, deserializationConfig, deserializerProvider, beanProperty, null, null);
                return _findCustomArrayDeserializer != null ? _findCustomArrayDeserializer : jsonDeserializer;
            } else if (contentType.isPrimitive()) {
                throw new IllegalArgumentException("Internal error: primitive type (" + arrayType + ") passed, no array deserializer found");
            }
        }
        TypeDeserializer typeDeserializer = (TypeDeserializer) contentType.getTypeHandler();
        TypeDeserializer findTypeDeserializer = typeDeserializer == null ? findTypeDeserializer(deserializationConfig, contentType, beanProperty) : typeDeserializer;
        jsonDeserializer = _findCustomArrayDeserializer(arrayType, deserializationConfig, deserializerProvider, beanProperty, findTypeDeserializer, jsonDeserializer2);
        if (jsonDeserializer != null) {
            return jsonDeserializer;
        }
        if (jsonDeserializer2 == null) {
            jsonDeserializer2 = deserializerProvider.findValueDeserializer(deserializationConfig, contentType, beanProperty);
        }
        return new ArrayDeserializer(arrayType, jsonDeserializer2, findTypeDeserializer);
    }

    public JsonDeserializer<?> createCollectionDeserializer(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, CollectionType collectionType, BeanProperty beanProperty) {
        CollectionType collectionType2 = (CollectionType) mapAbstractType(deserializationConfig, collectionType);
        Class rawClass = collectionType2.getRawClass();
        BasicBeanDescription basicBeanDescription = (BasicBeanDescription) deserializationConfig.introspectClassAnnotations(rawClass);
        JsonDeserializer<?> findDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationConfig, basicBeanDescription.getClassInfo(), beanProperty);
        if (findDeserializerFromAnnotation != null) {
            return findDeserializerFromAnnotation;
        }
        CollectionType collectionType3 = (CollectionType) modifyTypeByAnnotation(deserializationConfig, basicBeanDescription.getClassInfo(), collectionType2, null);
        JavaType contentType = collectionType3.getContentType();
        JsonDeserializer jsonDeserializer = (JsonDeserializer) contentType.getValueHandler();
        TypeDeserializer typeDeserializer = (TypeDeserializer) contentType.getTypeHandler();
        TypeDeserializer findTypeDeserializer = typeDeserializer == null ? findTypeDeserializer(deserializationConfig, contentType, beanProperty) : typeDeserializer;
        JsonDeserializer<?> _findCustomCollectionDeserializer = _findCustomCollectionDeserializer(collectionType3, deserializationConfig, deserializerProvider, basicBeanDescription, beanProperty, findTypeDeserializer, jsonDeserializer);
        if (_findCustomCollectionDeserializer != null) {
            return _findCustomCollectionDeserializer;
        }
        Class cls;
        if (jsonDeserializer == null) {
            if (EnumSet.class.isAssignableFrom(rawClass)) {
                return new EnumSetDeserializer(constructEnumResolver(contentType.getRawClass(), deserializationConfig));
            }
            jsonDeserializer = deserializerProvider.findValueDeserializer(deserializationConfig, contentType, beanProperty);
        }
        if (collectionType3.isInterface() || collectionType3.isAbstract()) {
            cls = (Class) _collectionFallbacks.get(rawClass.getName());
            if (cls == null) {
                throw new IllegalArgumentException("Can not find a deserializer for non-concrete Collection type " + collectionType3);
            }
        }
        cls = rawClass;
        Constructor findConstructor = ClassUtil.findConstructor(cls, deserializationConfig.isEnabled(Feature.CAN_OVERRIDE_ACCESS_MODIFIERS));
        return contentType.getRawClass() == String.class ? new StringCollectionDeserializer(collectionType3, jsonDeserializer, findConstructor) : new CollectionDeserializer(collectionType3, jsonDeserializer, findTypeDeserializer, findConstructor);
    }

    public JsonDeserializer<?> createCollectionLikeDeserializer(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, CollectionLikeType collectionLikeType, BeanProperty beanProperty) {
        CollectionLikeType collectionLikeType2 = (CollectionLikeType) mapAbstractType(deserializationConfig, collectionLikeType);
        BasicBeanDescription basicBeanDescription = (BasicBeanDescription) deserializationConfig.introspectClassAnnotations(collectionLikeType2.getRawClass());
        JsonDeserializer<?> findDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationConfig, basicBeanDescription.getClassInfo(), beanProperty);
        if (findDeserializerFromAnnotation != null) {
            return findDeserializerFromAnnotation;
        }
        CollectionLikeType collectionLikeType3 = (CollectionLikeType) modifyTypeByAnnotation(deserializationConfig, basicBeanDescription.getClassInfo(), collectionLikeType2, null);
        JavaType contentType = collectionLikeType3.getContentType();
        TypeDeserializer typeDeserializer = (TypeDeserializer) contentType.getTypeHandler();
        return _findCustomCollectionLikeDeserializer(collectionLikeType3, deserializationConfig, deserializerProvider, basicBeanDescription, beanProperty, typeDeserializer == null ? findTypeDeserializer(deserializationConfig, contentType, beanProperty) : typeDeserializer, (JsonDeserializer) contentType.getValueHandler());
    }

    public JsonDeserializer<?> createEnumDeserializer(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, JavaType javaType, BeanProperty beanProperty) {
        BasicBeanDescription basicBeanDescription = (BasicBeanDescription) deserializationConfig.introspectForCreation(javaType);
        JsonDeserializer<?> findDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationConfig, basicBeanDescription.getClassInfo(), beanProperty);
        if (findDeserializerFromAnnotation != null) {
            return findDeserializerFromAnnotation;
        }
        Class rawClass = javaType.getRawClass();
        findDeserializerFromAnnotation = _findCustomEnumDeserializer(rawClass, deserializationConfig, basicBeanDescription, beanProperty);
        if (findDeserializerFromAnnotation != null) {
            return findDeserializerFromAnnotation;
        }
        for (AnnotatedMethod annotatedMethod : basicBeanDescription.getFactoryMethods()) {
            if (deserializationConfig.getAnnotationIntrospector().hasCreatorAnnotation(annotatedMethod)) {
                if (annotatedMethod.getParameterCount() == 1 && annotatedMethod.getRawType().isAssignableFrom(rawClass)) {
                    return EnumDeserializer.deserializerForCreator(deserializationConfig, rawClass, annotatedMethod);
                }
                throw new IllegalArgumentException("Unsuitable method (" + annotatedMethod + ") decorated with @JsonCreator (for Enum type " + rawClass.getName() + ")");
            }
        }
        return new EnumDeserializer(constructEnumResolver(rawClass, deserializationConfig));
    }

    public JsonDeserializer<?> createMapDeserializer(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, MapType mapType, BeanProperty beanProperty) {
        MapType mapType2 = (MapType) mapAbstractType(deserializationConfig, mapType);
        BasicBeanDescription basicBeanDescription = (BasicBeanDescription) deserializationConfig.introspectForCreation(mapType2);
        JsonDeserializer<?> findDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationConfig, basicBeanDescription.getClassInfo(), beanProperty);
        if (findDeserializerFromAnnotation != null) {
            return findDeserializerFromAnnotation;
        }
        JavaType javaType = (MapType) modifyTypeByAnnotation(deserializationConfig, basicBeanDescription.getClassInfo(), mapType2, null);
        JavaType keyType = javaType.getKeyType();
        JavaType contentType = javaType.getContentType();
        JsonDeserializer jsonDeserializer = (JsonDeserializer) contentType.getValueHandler();
        KeyDeserializer keyDeserializer = (KeyDeserializer) keyType.getValueHandler();
        KeyDeserializer findKeyDeserializer = keyDeserializer == null ? deserializerProvider.findKeyDeserializer(deserializationConfig, keyType, beanProperty) : keyDeserializer;
        TypeDeserializer typeDeserializer = (TypeDeserializer) contentType.getTypeHandler();
        TypeDeserializer findTypeDeserializer = typeDeserializer == null ? findTypeDeserializer(deserializationConfig, contentType, beanProperty) : typeDeserializer;
        JsonDeserializer<?> _findCustomMapDeserializer = _findCustomMapDeserializer(javaType, deserializationConfig, deserializerProvider, basicBeanDescription, beanProperty, findKeyDeserializer, findTypeDeserializer, jsonDeserializer);
        if (_findCustomMapDeserializer != null) {
            return _findCustomMapDeserializer;
        }
        if (jsonDeserializer == null) {
            jsonDeserializer = deserializerProvider.findValueDeserializer(deserializationConfig, contentType, beanProperty);
        }
        Class rawClass = javaType.getRawClass();
        if (EnumMap.class.isAssignableFrom(rawClass)) {
            Class rawClass2 = keyType.getRawClass();
            if (rawClass2 != null && rawClass2.isEnum()) {
                return new EnumMapDeserializer(constructEnumResolver(rawClass2, deserializationConfig), jsonDeserializer);
            }
            throw new IllegalArgumentException("Can not construct EnumMap; generic (key) type not available");
        }
        BasicBeanDescription basicBeanDescription2;
        if (javaType.isInterface() || javaType.isAbstract()) {
            rawClass = (Class) _mapFallbacks.get(rawClass.getName());
            if (rawClass == null) {
                throw new IllegalArgumentException("Can not find a deserializer for non-concrete Map type " + javaType);
            }
            JavaType javaType2 = (MapType) javaType.forcedNarrowBy(rawClass);
            basicBeanDescription2 = (BasicBeanDescription) deserializationConfig.introspectForCreation(javaType2);
            javaType = javaType2;
        } else {
            basicBeanDescription2 = basicBeanDescription;
        }
        boolean isEnabled = deserializationConfig.isEnabled(Feature.CAN_OVERRIDE_ACCESS_MODIFIERS);
        Object findDefaultConstructor = basicBeanDescription2.findDefaultConstructor();
        if (findDefaultConstructor != null && isEnabled) {
            ClassUtil.checkAndFixAccess(findDefaultConstructor);
        }
        JsonDeserializer mapDeserializer = new MapDeserializer(javaType, findDefaultConstructor, findKeyDeserializer, jsonDeserializer, findTypeDeserializer);
        mapDeserializer.setIgnorableProperties(deserializationConfig.getAnnotationIntrospector().findPropertiesToIgnore(basicBeanDescription2.getClassInfo()));
        mapDeserializer.setCreators(findMapCreators(deserializationConfig, basicBeanDescription2));
        return mapDeserializer;
    }

    public JsonDeserializer<?> createMapLikeDeserializer(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, MapLikeType mapLikeType, BeanProperty beanProperty) {
        MapLikeType mapLikeType2 = (MapLikeType) mapAbstractType(deserializationConfig, mapLikeType);
        BasicBeanDescription basicBeanDescription = (BasicBeanDescription) deserializationConfig.introspectForCreation(mapLikeType2);
        JsonDeserializer<?> findDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationConfig, basicBeanDescription.getClassInfo(), beanProperty);
        if (findDeserializerFromAnnotation != null) {
            return findDeserializerFromAnnotation;
        }
        MapLikeType mapLikeType3 = (MapLikeType) modifyTypeByAnnotation(deserializationConfig, basicBeanDescription.getClassInfo(), mapLikeType2, null);
        JavaType keyType = mapLikeType3.getKeyType();
        JavaType contentType = mapLikeType3.getContentType();
        JsonDeserializer jsonDeserializer = (JsonDeserializer) contentType.getValueHandler();
        KeyDeserializer keyDeserializer = (KeyDeserializer) keyType.getValueHandler();
        KeyDeserializer findKeyDeserializer = keyDeserializer == null ? deserializerProvider.findKeyDeserializer(deserializationConfig, keyType, beanProperty) : keyDeserializer;
        TypeDeserializer typeDeserializer = (TypeDeserializer) contentType.getTypeHandler();
        return _findCustomMapLikeDeserializer(mapLikeType3, deserializationConfig, deserializerProvider, basicBeanDescription, beanProperty, findKeyDeserializer, typeDeserializer == null ? findTypeDeserializer(deserializationConfig, contentType, beanProperty) : typeDeserializer, jsonDeserializer);
    }

    public JsonDeserializer<?> createTreeDeserializer(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, JavaType javaType, BeanProperty beanProperty) {
        Class rawClass = javaType.getRawClass();
        JsonDeserializer<?> _findCustomTreeNodeDeserializer = _findCustomTreeNodeDeserializer(rawClass, deserializationConfig, beanProperty);
        return _findCustomTreeNodeDeserializer != null ? _findCustomTreeNodeDeserializer : JsonNodeDeserializer.getDeserializer(rawClass);
    }

    protected JsonDeserializer<Object> findDeserializerFromAnnotation(DeserializationConfig deserializationConfig, Annotated annotated, BeanProperty beanProperty) {
        Object findDeserializer = deserializationConfig.getAnnotationIntrospector().findDeserializer(annotated);
        return findDeserializer != null ? _constructDeserializer(deserializationConfig, annotated, beanProperty, findDeserializer) : null;
    }

    protected CreatorContainer findMapCreators(DeserializationConfig deserializationConfig, BasicBeanDescription basicBeanDescription) {
        SettableBeanProperty[] settableBeanPropertyArr;
        int i;
        int i2;
        String findPropertyNameForParam;
        AnnotationIntrospector annotationIntrospector = deserializationConfig.getAnnotationIntrospector();
        CreatorContainer creatorContainer = new CreatorContainer(basicBeanDescription, deserializationConfig.isEnabled(Feature.CAN_OVERRIDE_ACCESS_MODIFIERS));
        for (AnnotatedConstructor annotatedConstructor : basicBeanDescription.getConstructors()) {
            AnnotatedParameter parameter;
            int parameterCount = annotatedConstructor.getParameterCount();
            if (parameterCount >= 1 && annotationIntrospector.hasCreatorAnnotation(annotatedConstructor)) {
                settableBeanPropertyArr = new SettableBeanProperty[parameterCount];
                i = 0;
                i2 = 0;
                while (i2 < parameterCount) {
                    parameter = annotatedConstructor.getParameter(i2);
                    findPropertyNameForParam = parameter == null ? null : annotationIntrospector.findPropertyNameForParam(parameter);
                    if (findPropertyNameForParam == null || findPropertyNameForParam.length() == 0) {
                        throw new IllegalArgumentException("Parameter #" + i2 + " of constructor " + annotatedConstructor + " has no property name annotation: must have for @JsonCreator for a Map type");
                    }
                    int i3 = i + 1;
                    settableBeanPropertyArr[i2] = constructCreatorProperty(deserializationConfig, basicBeanDescription, findPropertyNameForParam, i2, parameter);
                    i2++;
                    i = i3;
                }
                creatorContainer.addPropertyConstructor(annotatedConstructor, settableBeanPropertyArr);
            }
        }
        for (AnnotatedMethod annotatedMethod : basicBeanDescription.getFactoryMethods()) {
            parameterCount = annotatedMethod.getParameterCount();
            if (parameterCount >= 1 && annotationIntrospector.hasCreatorAnnotation(annotatedMethod)) {
                settableBeanPropertyArr = new SettableBeanProperty[parameterCount];
                i = 0;
                i2 = 0;
                while (i2 < parameterCount) {
                    parameter = annotatedMethod.getParameter(i2);
                    findPropertyNameForParam = parameter == null ? null : annotationIntrospector.findPropertyNameForParam(parameter);
                    if (findPropertyNameForParam == null || findPropertyNameForParam.length() == 0) {
                        throw new IllegalArgumentException("Parameter #" + i2 + " of factory method " + annotatedMethod + " has no property name annotation: must have for @JsonCreator for a Map type");
                    }
                    i3 = i + 1;
                    settableBeanPropertyArr[i2] = constructCreatorProperty(deserializationConfig, basicBeanDescription, findPropertyNameForParam, i2, parameter);
                    i2++;
                    i = i3;
                }
                creatorContainer.addPropertyFactory(annotatedMethod, settableBeanPropertyArr);
            }
        }
        return creatorContainer;
    }

    public TypeDeserializer findPropertyContentTypeDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, AnnotatedMember annotatedMember, BeanProperty beanProperty) {
        AnnotationIntrospector annotationIntrospector = deserializationConfig.getAnnotationIntrospector();
        TypeResolverBuilder findPropertyContentTypeResolver = annotationIntrospector.findPropertyContentTypeResolver(deserializationConfig, annotatedMember, javaType);
        JavaType contentType = javaType.getContentType();
        return findPropertyContentTypeResolver == null ? findTypeDeserializer(deserializationConfig, contentType, beanProperty) : findPropertyContentTypeResolver.buildTypeDeserializer(deserializationConfig, contentType, deserializationConfig.getSubtypeResolver().collectAndResolveSubtypes(annotatedMember, (MapperConfig) deserializationConfig, annotationIntrospector), beanProperty);
    }

    public TypeDeserializer findPropertyTypeDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, AnnotatedMember annotatedMember, BeanProperty beanProperty) {
        AnnotationIntrospector annotationIntrospector = deserializationConfig.getAnnotationIntrospector();
        TypeResolverBuilder findPropertyTypeResolver = annotationIntrospector.findPropertyTypeResolver(deserializationConfig, annotatedMember, javaType);
        return findPropertyTypeResolver == null ? findTypeDeserializer(deserializationConfig, javaType, beanProperty) : findPropertyTypeResolver.buildTypeDeserializer(deserializationConfig, javaType, deserializationConfig.getSubtypeResolver().collectAndResolveSubtypes(annotatedMember, (MapperConfig) deserializationConfig, annotationIntrospector), beanProperty);
    }

    protected JsonDeserializer<Object> findStdBeanDeserializer(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, JavaType javaType, BeanProperty beanProperty) {
        JsonDeserializer<Object> jsonDeserializer = (JsonDeserializer) _simpleDeserializers.get(javaType);
        if (jsonDeserializer != null) {
            return jsonDeserializer;
        }
        Class rawClass = javaType.getRawClass();
        if (rawClass == Class.class) {
            return new ClassDeserializer();
        }
        if (AtomicReference.class.isAssignableFrom(rawClass)) {
            JavaType[] findTypeParameters = deserializationConfig.getTypeFactory().findTypeParameters(javaType, AtomicReference.class);
            JavaType unknownType = (findTypeParameters == null || findTypeParameters.length < 1) ? TypeFactory.unknownType() : findTypeParameters[0];
            return new AtomicReferenceDeserializer(unknownType, beanProperty);
        }
        jsonDeserializer = this.optionalHandlers.findDeserializer(javaType, deserializationConfig, deserializerProvider);
        return jsonDeserializer == null ? null : jsonDeserializer;
    }

    public TypeDeserializer findTypeDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, BeanProperty beanProperty) {
        TypeResolverBuilder typeResolverBuilder;
        Collection collectAndResolveSubtypes;
        AnnotatedClass classInfo = ((BasicBeanDescription) deserializationConfig.introspectClassAnnotations(javaType.getRawClass())).getClassInfo();
        AnnotationIntrospector annotationIntrospector = deserializationConfig.getAnnotationIntrospector();
        TypeResolverBuilder findTypeResolver = annotationIntrospector.findTypeResolver(deserializationConfig, classInfo, javaType);
        if (findTypeResolver == null) {
            TypeResolverBuilder defaultTyper = deserializationConfig.getDefaultTyper(javaType);
            if (defaultTyper == null) {
                return null;
            }
            typeResolverBuilder = defaultTyper;
            Object obj = null;
        } else {
            collectAndResolveSubtypes = deserializationConfig.getSubtypeResolver().collectAndResolveSubtypes(classInfo, (MapperConfig) deserializationConfig, annotationIntrospector);
            typeResolverBuilder = findTypeResolver;
        }
        return typeResolverBuilder.buildTypeDeserializer(deserializationConfig, javaType, collectAndResolveSubtypes, beanProperty);
    }

    protected abstract JavaType mapAbstractType(DeserializationConfig deserializationConfig, JavaType javaType);

    protected <T extends JavaType> T modifyTypeByAnnotation(DeserializationConfig deserializationConfig, Annotated annotated, T t, String str) {
        T narrowBy;
        AnnotationIntrospector annotationIntrospector = deserializationConfig.getAnnotationIntrospector();
        Class findDeserializationType = annotationIntrospector.findDeserializationType(annotated, t, str);
        if (findDeserializationType != null) {
            try {
                narrowBy = t.narrowBy(findDeserializationType);
            } catch (Throwable e) {
                throw new JsonMappingException("Failed to narrow type " + t + " with concrete-type annotation (value " + findDeserializationType.getName() + "), method '" + annotated.getName() + "': " + e.getMessage(), null, e);
            }
        }
        narrowBy = t;
        if (narrowBy.isContainerType()) {
            Class findDeserializationKeyType = annotationIntrospector.findDeserializationKeyType(annotated, narrowBy.getKeyType(), str);
            if (findDeserializationKeyType != null) {
                if (narrowBy instanceof MapType) {
                    try {
                        narrowBy = ((MapType) narrowBy).narrowKey(findDeserializationKeyType);
                    } catch (Throwable e2) {
                        throw new JsonMappingException("Failed to narrow key type " + narrowBy + " with key-type annotation (" + findDeserializationKeyType.getName() + "): " + e2.getMessage(), null, e2);
                    }
                }
                throw new JsonMappingException("Illegal key-type annotation: type " + narrowBy + " is not a Map type");
            }
            JavaType keyType = narrowBy.getKeyType();
            if (keyType != null && keyType.getValueHandler() == null) {
                findDeserializationKeyType = annotationIntrospector.findKeyDeserializer(annotated);
                if (!(findDeserializationKeyType == null || findDeserializationKeyType == None.class)) {
                    keyType.setValueHandler(deserializationConfig.keyDeserializerInstance(annotated, findDeserializationKeyType));
                }
            }
            findDeserializationType = annotationIntrospector.findDeserializationContentType(annotated, narrowBy.getContentType(), str);
            if (findDeserializationType != null) {
                try {
                    narrowBy = narrowBy.narrowContentsBy(findDeserializationType);
                } catch (Throwable e3) {
                    throw new JsonMappingException("Failed to narrow content type " + narrowBy + " with content-type annotation (" + findDeserializationType.getName() + "): " + e3.getMessage(), null, e3);
                }
            }
            if (narrowBy.getContentType().getValueHandler() == null) {
                findDeserializationType = annotationIntrospector.findContentDeserializer(annotated);
                if (!(findDeserializationType == null || findDeserializationType == JsonDeserializer.None.class)) {
                    narrowBy.getContentType().setValueHandler(deserializationConfig.deserializerInstance(annotated, findDeserializationType));
                }
            }
        }
        return narrowBy;
    }

    protected JavaType resolveType(DeserializationConfig deserializationConfig, BasicBeanDescription basicBeanDescription, JavaType javaType, AnnotatedMember annotatedMember, BeanProperty beanProperty) {
        if (javaType.isContainerType()) {
            AnnotationIntrospector annotationIntrospector = deserializationConfig.getAnnotationIntrospector();
            JavaType keyType = javaType.getKeyType();
            if (keyType != null) {
                Class findKeyDeserializer = annotationIntrospector.findKeyDeserializer(annotatedMember);
                if (!(findKeyDeserializer == null || findKeyDeserializer == None.class)) {
                    keyType.setValueHandler(deserializationConfig.keyDeserializerInstance(annotatedMember, findKeyDeserializer));
                }
            }
            Class findContentDeserializer = annotationIntrospector.findContentDeserializer(annotatedMember);
            if (!(findContentDeserializer == null || findContentDeserializer == JsonDeserializer.None.class)) {
                javaType.getContentType().setValueHandler(deserializationConfig.deserializerInstance(annotatedMember, findContentDeserializer));
            }
            if (annotatedMember instanceof AnnotatedMember) {
                TypeDeserializer findPropertyContentTypeDeserializer = findPropertyContentTypeDeserializer(deserializationConfig, javaType, annotatedMember, beanProperty);
                if (findPropertyContentTypeDeserializer != null) {
                    javaType = javaType.withContentTypeHandler(findPropertyContentTypeDeserializer);
                }
            }
        }
        Object findPropertyTypeDeserializer = annotatedMember instanceof AnnotatedMember ? findPropertyTypeDeserializer(deserializationConfig, javaType, annotatedMember, beanProperty) : findTypeDeserializer(deserializationConfig, javaType, null);
        return findPropertyTypeDeserializer != null ? javaType.withTypeHandler(findPropertyTypeDeserializer) : javaType;
    }

    public abstract DeserializerFactory withConfig(Config config);
}
