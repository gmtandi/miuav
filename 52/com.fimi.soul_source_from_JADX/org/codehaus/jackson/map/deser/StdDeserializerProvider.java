package org.codehaus.jackson.map.deser;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.AbstractTypeResolver;
import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.ContextualDeserializer;
import org.codehaus.jackson.map.ContextualKeyDeserializer;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.DeserializerFactory;
import org.codehaus.jackson.map.DeserializerProvider;
import org.codehaus.jackson.map.Deserializers;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.KeyDeserializer;
import org.codehaus.jackson.map.KeyDeserializers;
import org.codehaus.jackson.map.ResolvableDeserializer;
import org.codehaus.jackson.map.TypeDeserializer;
import org.codehaus.jackson.map.introspect.AnnotatedClass;
import org.codehaus.jackson.map.type.ArrayType;
import org.codehaus.jackson.map.type.CollectionLikeType;
import org.codehaus.jackson.map.type.CollectionType;
import org.codehaus.jackson.map.type.MapLikeType;
import org.codehaus.jackson.map.type.MapType;
import org.codehaus.jackson.map.util.ClassUtil;
import org.codehaus.jackson.type.JavaType;

public class StdDeserializerProvider extends DeserializerProvider {
    static final HashMap<JavaType, KeyDeserializer> _keyDeserializers;
    protected final ConcurrentHashMap<JavaType, JsonDeserializer<Object>> _cachedDeserializers;
    protected DeserializerFactory _factory;
    protected final HashMap<JavaType, JsonDeserializer<Object>> _incompleteDeserializers;

    public final class WrappedDeserializer extends JsonDeserializer<Object> {
        final JsonDeserializer<Object> _deserializer;
        final TypeDeserializer _typeDeserializer;

        public WrappedDeserializer(TypeDeserializer typeDeserializer, JsonDeserializer<Object> jsonDeserializer) {
            this._typeDeserializer = typeDeserializer;
            this._deserializer = jsonDeserializer;
        }

        public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            return this._deserializer.deserializeWithType(jsonParser, deserializationContext, this._typeDeserializer);
        }

        public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
            throw new IllegalStateException("Type-wrapped deserializer's deserializeWithType should never get called");
        }
    }

    static {
        _keyDeserializers = StdKeyDeserializers.constructAll();
    }

    public StdDeserializerProvider() {
        this(BeanDeserializerFactory.instance);
    }

    public StdDeserializerProvider(DeserializerFactory deserializerFactory) {
        this._cachedDeserializers = new ConcurrentHashMap(64, 0.75f, 2);
        this._incompleteDeserializers = new HashMap(8);
        this._factory = deserializerFactory;
    }

    protected JsonDeserializer<Object> _createAndCache2(DeserializationConfig deserializationConfig, JavaType javaType, BeanProperty beanProperty) {
        try {
            JsonDeserializer<Object> _createDeserializer = _createDeserializer(deserializationConfig, javaType, beanProperty);
            if (_createDeserializer == null) {
                return null;
            }
            boolean z = _createDeserializer instanceof ResolvableDeserializer;
            boolean z2 = _createDeserializer.getClass() == BeanDeserializer.class;
            if (!z2 && deserializationConfig.isEnabled(Feature.USE_ANNOTATIONS)) {
                AnnotationIntrospector annotationIntrospector = deserializationConfig.getAnnotationIntrospector();
                Boolean findCachability = annotationIntrospector.findCachability(AnnotatedClass.construct(_createDeserializer.getClass(), annotationIntrospector, null));
                if (findCachability != null) {
                    z2 = findCachability.booleanValue();
                }
            }
            if (z) {
                this._incompleteDeserializers.put(javaType, _createDeserializer);
                _resolveDeserializer(deserializationConfig, (ResolvableDeserializer) _createDeserializer);
                this._incompleteDeserializers.remove(javaType);
            }
            if (!z2) {
                return _createDeserializer;
            }
            this._cachedDeserializers.put(javaType, _createDeserializer);
            return _createDeserializer;
        } catch (Throwable e) {
            throw new JsonMappingException(e.getMessage(), null, e);
        }
    }

    protected JsonDeserializer<Object> _createAndCacheValueDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, BeanProperty beanProperty) {
        JsonDeserializer<Object> _findCachedDeserializer;
        synchronized (this._incompleteDeserializers) {
            _findCachedDeserializer = _findCachedDeserializer(javaType);
            if (_findCachedDeserializer != null) {
            } else {
                r2 = this._incompleteDeserializers.size();
                if (r2 > 0) {
                    _findCachedDeserializer = (JsonDeserializer) this._incompleteDeserializers.get(javaType);
                    if (_findCachedDeserializer != null) {
                    }
                }
                try {
                    _findCachedDeserializer = _createAndCache2(deserializationConfig, javaType, beanProperty);
                    if (r2 == 0) {
                        if (this._incompleteDeserializers.size() > 0) {
                            this._incompleteDeserializers.clear();
                        }
                    }
                } catch (Throwable th) {
                    int size;
                    if (size == 0 && this._incompleteDeserializers.size() > 0) {
                        this._incompleteDeserializers.clear();
                    }
                }
            }
        }
        return _findCachedDeserializer;
    }

    protected JsonDeserializer<Object> _createDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, BeanProperty beanProperty) {
        if (javaType.isEnumType()) {
            return this._factory.createEnumDeserializer(deserializationConfig, this, javaType, beanProperty);
        }
        if (javaType.isContainerType()) {
            if (javaType.isArrayType()) {
                return this._factory.createArrayDeserializer(deserializationConfig, this, (ArrayType) javaType, beanProperty);
            }
            if (javaType.isMapLikeType()) {
                MapLikeType mapLikeType = (MapLikeType) javaType;
                return mapLikeType.isTrueMapType() ? this._factory.createMapDeserializer(deserializationConfig, this, (MapType) mapLikeType, beanProperty) : this._factory.createMapLikeDeserializer(deserializationConfig, this, mapLikeType, beanProperty);
            } else if (javaType.isCollectionLikeType()) {
                CollectionLikeType collectionLikeType = (CollectionLikeType) javaType;
                return collectionLikeType.isTrueCollectionType() ? this._factory.createCollectionDeserializer(deserializationConfig, this, (CollectionType) collectionLikeType, beanProperty) : this._factory.createCollectionLikeDeserializer(deserializationConfig, this, collectionLikeType, beanProperty);
            }
        }
        return JsonNode.class.isAssignableFrom(javaType.getRawClass()) ? this._factory.createTreeDeserializer(deserializationConfig, this, javaType, beanProperty) : this._factory.createBeanDeserializer(deserializationConfig, this, javaType, beanProperty);
    }

    protected JsonDeserializer<Object> _findCachedDeserializer(JavaType javaType) {
        return (JsonDeserializer) this._cachedDeserializers.get(javaType);
    }

    protected KeyDeserializer _handleUnknownKeyDeserializer(JavaType javaType) {
        throw new JsonMappingException("Can not find a (Map) Key deserializer for type " + javaType);
    }

    protected JsonDeserializer<Object> _handleUnknownValueDeserializer(JavaType javaType) {
        if (ClassUtil.isConcrete(javaType.getRawClass())) {
            throw new JsonMappingException("Can not find a Value deserializer for type " + javaType);
        }
        throw new JsonMappingException("Can not find a Value deserializer for abstract type " + javaType);
    }

    protected void _resolveDeserializer(DeserializationConfig deserializationConfig, ResolvableDeserializer resolvableDeserializer) {
        resolvableDeserializer.resolve(deserializationConfig, this);
    }

    public int cachedDeserializersCount() {
        return this._cachedDeserializers.size();
    }

    public KeyDeserializer findKeyDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, BeanProperty beanProperty) {
        KeyDeserializer createKeyDeserializer = this._factory.createKeyDeserializer(deserializationConfig, javaType, beanProperty);
        if (createKeyDeserializer == null) {
            Class rawClass = javaType.getRawClass();
            if (rawClass == String.class || rawClass == Object.class) {
                return null;
            }
            KeyDeserializer keyDeserializer = (KeyDeserializer) _keyDeserializers.get(javaType);
            if (keyDeserializer != null) {
                return keyDeserializer;
            }
            if (javaType.isEnumType()) {
                return StdKeyDeserializers.constructEnumKeyDeserializer(deserializationConfig, javaType);
            }
            keyDeserializer = StdKeyDeserializers.findStringBasedKeyDeserializer(deserializationConfig, javaType);
            if (keyDeserializer != null) {
                return keyDeserializer;
            }
            if (createKeyDeserializer == null) {
                return _handleUnknownKeyDeserializer(javaType);
            }
        }
        if (createKeyDeserializer instanceof ContextualKeyDeserializer) {
            createKeyDeserializer = ((ContextualKeyDeserializer) createKeyDeserializer).createContextual(deserializationConfig, beanProperty);
        }
        return createKeyDeserializer;
    }

    public JsonDeserializer<Object> findTypedValueDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, BeanProperty beanProperty) {
        JsonDeserializer<Object> findValueDeserializer = findValueDeserializer(deserializationConfig, javaType, beanProperty);
        TypeDeserializer findTypeDeserializer = this._factory.findTypeDeserializer(deserializationConfig, javaType, beanProperty);
        return findTypeDeserializer != null ? new WrappedDeserializer(findTypeDeserializer, findValueDeserializer) : findValueDeserializer;
    }

    public JsonDeserializer<Object> findValueDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, BeanProperty beanProperty) {
        JsonDeserializer<Object> _findCachedDeserializer = _findCachedDeserializer(javaType);
        if (_findCachedDeserializer != null) {
            return _findCachedDeserializer instanceof ContextualDeserializer ? ((ContextualDeserializer) _findCachedDeserializer).createContextual(deserializationConfig, beanProperty) : _findCachedDeserializer;
        } else {
            _findCachedDeserializer = _createAndCacheValueDeserializer(deserializationConfig, javaType, beanProperty);
            if (_findCachedDeserializer == null) {
                _findCachedDeserializer = _handleUnknownValueDeserializer(javaType);
            }
            return _findCachedDeserializer instanceof ContextualDeserializer ? ((ContextualDeserializer) _findCachedDeserializer).createContextual(deserializationConfig, beanProperty) : _findCachedDeserializer;
        }
    }

    public void flushCachedDeserializers() {
        this._cachedDeserializers.clear();
    }

    public boolean hasValueDeserializerFor(DeserializationConfig deserializationConfig, JavaType javaType) {
        JsonDeserializer _findCachedDeserializer = _findCachedDeserializer(javaType);
        if (_findCachedDeserializer == null) {
            try {
                _findCachedDeserializer = _createAndCacheValueDeserializer(deserializationConfig, javaType, null);
            } catch (Exception e) {
                return false;
            }
        }
        return _findCachedDeserializer != null;
    }

    public DeserializerProvider withAbstractTypeResolver(AbstractTypeResolver abstractTypeResolver) {
        this._factory = this._factory.withAbstractTypeResolver(abstractTypeResolver);
        return this;
    }

    public DeserializerProvider withAdditionalDeserializers(Deserializers deserializers) {
        this._factory = this._factory.withAdditionalDeserializers(deserializers);
        return this;
    }

    public DeserializerProvider withAdditionalKeyDeserializers(KeyDeserializers keyDeserializers) {
        this._factory = this._factory.withAdditionalKeyDeserializers(keyDeserializers);
        return this;
    }

    public DeserializerProvider withDeserializerModifier(BeanDeserializerModifier beanDeserializerModifier) {
        this._factory = this._factory.withDeserializerModifier(beanDeserializerModifier);
        return this;
    }
}
