package org.codehaus.jackson.map;

import java.text.DateFormat;
import java.util.HashMap;
import org.codehaus.jackson.map.MapperConfig.Base;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.map.annotate.JsonSerialize.Typing;
import org.codehaus.jackson.map.introspect.Annotated;
import org.codehaus.jackson.map.introspect.AnnotatedClass;
import org.codehaus.jackson.map.introspect.VisibilityChecker;
import org.codehaus.jackson.map.jsontype.SubtypeResolver;
import org.codehaus.jackson.map.jsontype.TypeResolverBuilder;
import org.codehaus.jackson.map.ser.FilterProvider;
import org.codehaus.jackson.map.type.ClassKey;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.map.util.ClassUtil;
import org.codehaus.jackson.type.JavaType;

public class SerializationConfig extends MapperConfig<SerializationConfig> {
    protected static final int DEFAULT_FEATURE_FLAGS;
    protected int _featureFlags;
    protected FilterProvider _filterProvider;
    protected Inclusion _serializationInclusion;
    protected Class<?> _serializationView;

    public enum Feature {
        USE_ANNOTATIONS(true),
        AUTO_DETECT_GETTERS(true),
        AUTO_DETECT_IS_GETTERS(true),
        AUTO_DETECT_FIELDS(true),
        CAN_OVERRIDE_ACCESS_MODIFIERS(true),
        WRITE_NULL_PROPERTIES(true),
        USE_STATIC_TYPING(false),
        DEFAULT_VIEW_INCLUSION(true),
        WRAP_ROOT_VALUE(false),
        INDENT_OUTPUT(false),
        SORT_PROPERTIES_ALPHABETICALLY(false),
        FAIL_ON_EMPTY_BEANS(true),
        WRAP_EXCEPTIONS(true),
        CLOSE_CLOSEABLE(false),
        FLUSH_AFTER_WRITE_VALUE(true),
        WRITE_DATES_AS_TIMESTAMPS(true),
        WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS(false),
        WRITE_ENUMS_USING_TO_STRING(false),
        WRITE_NULL_MAP_VALUES(true);
        
        final boolean _defaultState;

        private Feature(boolean z) {
            this._defaultState = z;
        }

        public static int collectDefaults() {
            int i = 0;
            for (Feature feature : values()) {
                if (feature.enabledByDefault()) {
                    i |= feature.getMask();
                }
            }
            return i;
        }

        public boolean enabledByDefault() {
            return this._defaultState;
        }

        public int getMask() {
            return 1 << ordinal();
        }
    }

    static {
        DEFAULT_FEATURE_FLAGS = Feature.collectDefaults();
    }

    public SerializationConfig(ClassIntrospector<? extends BeanDescription> classIntrospector, AnnotationIntrospector annotationIntrospector, VisibilityChecker<?> visibilityChecker, SubtypeResolver subtypeResolver, PropertyNamingStrategy propertyNamingStrategy, TypeFactory typeFactory, HandlerInstantiator handlerInstantiator) {
        super(classIntrospector, annotationIntrospector, visibilityChecker, subtypeResolver, propertyNamingStrategy, typeFactory, handlerInstantiator);
        this._featureFlags = DEFAULT_FEATURE_FLAGS;
        this._serializationInclusion = null;
        this._filterProvider = null;
    }

    protected SerializationConfig(SerializationConfig serializationConfig) {
        this(serializationConfig, serializationConfig._base);
    }

    protected SerializationConfig(SerializationConfig serializationConfig, Class<?> cls) {
        super(serializationConfig);
        this._featureFlags = DEFAULT_FEATURE_FLAGS;
        this._serializationInclusion = null;
        this._featureFlags = serializationConfig._featureFlags;
        this._serializationInclusion = serializationConfig._serializationInclusion;
        this._serializationView = cls;
        this._filterProvider = serializationConfig._filterProvider;
    }

    protected SerializationConfig(SerializationConfig serializationConfig, HashMap<ClassKey, Class<?>> hashMap, SubtypeResolver subtypeResolver) {
        this(serializationConfig, serializationConfig._base);
        this._mixInAnnotations = hashMap;
        this._subtypeResolver = subtypeResolver;
    }

    protected SerializationConfig(SerializationConfig serializationConfig, Base base) {
        super(serializationConfig, base, serializationConfig._subtypeResolver);
        this._featureFlags = DEFAULT_FEATURE_FLAGS;
        this._serializationInclusion = null;
        this._featureFlags = serializationConfig._featureFlags;
        this._serializationInclusion = serializationConfig._serializationInclusion;
        this._serializationView = serializationConfig._serializationView;
        this._filterProvider = serializationConfig._filterProvider;
    }

    protected SerializationConfig(SerializationConfig serializationConfig, FilterProvider filterProvider) {
        super(serializationConfig);
        this._featureFlags = DEFAULT_FEATURE_FLAGS;
        this._serializationInclusion = null;
        this._featureFlags = serializationConfig._featureFlags;
        this._serializationInclusion = serializationConfig._serializationInclusion;
        this._serializationView = serializationConfig._serializationView;
        this._filterProvider = filterProvider;
    }

    public boolean canOverrideAccessModifiers() {
        return isEnabled(Feature.CAN_OVERRIDE_ACCESS_MODIFIERS);
    }

    public SerializationConfig createUnshared(SubtypeResolver subtypeResolver) {
        HashMap hashMap = this._mixInAnnotations;
        this._mixInAnnotationsShared = true;
        return new SerializationConfig(this, hashMap, subtypeResolver);
    }

    @Deprecated
    public SerializationConfig createUnshared(TypeResolverBuilder<?> typeResolverBuilder, VisibilityChecker<?> visibilityChecker, SubtypeResolver subtypeResolver) {
        return createUnshared(subtypeResolver).withTypeResolverBuilder((TypeResolverBuilder) typeResolverBuilder).withVisibilityChecker((VisibilityChecker) visibilityChecker);
    }

    public void disable(Feature feature) {
        this._featureFlags &= feature.getMask() ^ -1;
    }

    public void enable(Feature feature) {
        this._featureFlags |= feature.getMask();
    }

    public void fromAnnotations(Class<?> cls) {
        AnnotationIntrospector annotationIntrospector = getAnnotationIntrospector();
        Annotated construct = AnnotatedClass.construct(cls, annotationIntrospector, null);
        this._base = this._base.withVisibilityChecker(annotationIntrospector.findAutoDetectVisibility(construct, getDefaultVisibilityChecker()));
        Inclusion findSerializationInclusion = annotationIntrospector.findSerializationInclusion(construct, null);
        if (findSerializationInclusion != this._serializationInclusion) {
            setSerializationInclusion(findSerializationInclusion);
        }
        Typing findSerializationTyping = annotationIntrospector.findSerializationTyping(construct);
        if (findSerializationTyping != null) {
            set(Feature.USE_STATIC_TYPING, findSerializationTyping == Typing.STATIC);
        }
    }

    public AnnotationIntrospector getAnnotationIntrospector() {
        return isEnabled(Feature.USE_ANNOTATIONS) ? super.getAnnotationIntrospector() : AnnotationIntrospector.nopInstance();
    }

    public FilterProvider getFilterProvider() {
        return this._filterProvider;
    }

    public Inclusion getSerializationInclusion() {
        return this._serializationInclusion != null ? this._serializationInclusion : isEnabled(Feature.WRITE_NULL_PROPERTIES) ? Inclusion.ALWAYS : Inclusion.NON_NULL;
    }

    public Class<?> getSerializationView() {
        return this._serializationView;
    }

    public <T extends BeanDescription> T introspect(JavaType javaType) {
        return getClassIntrospector().forSerialization(this, javaType, this);
    }

    public <T extends BeanDescription> T introspectClassAnnotations(Class<?> cls) {
        return getClassIntrospector().forClassAnnotations(this, cls, this);
    }

    public <T extends BeanDescription> T introspectDirectClassAnnotations(Class<?> cls) {
        return getClassIntrospector().forDirectClassAnnotations(this, cls, this);
    }

    public boolean isAnnotationProcessingEnabled() {
        return isEnabled(Feature.USE_ANNOTATIONS);
    }

    public final boolean isEnabled(Feature feature) {
        return (this._featureFlags & feature.getMask()) != 0;
    }

    public JsonSerializer<Object> serializerInstance(Annotated annotated, Class<? extends JsonSerializer<?>> cls) {
        HandlerInstantiator handlerInstantiator = getHandlerInstantiator();
        if (handlerInstantiator != null) {
            JsonSerializer<Object> serializerInstance = handlerInstantiator.serializerInstance(this, annotated, cls);
            if (serializerInstance != null) {
                return serializerInstance;
            }
        }
        return (JsonSerializer) ClassUtil.createInstance(cls, canOverrideAccessModifiers());
    }

    public void set(Feature feature, boolean z) {
        if (z) {
            enable(feature);
        } else {
            disable(feature);
        }
    }

    @Deprecated
    public final void setDateFormat(DateFormat dateFormat) {
        super.setDateFormat(dateFormat);
        set(Feature.WRITE_DATES_AS_TIMESTAMPS, dateFormat == null);
    }

    public void setSerializationInclusion(Inclusion inclusion) {
        this._serializationInclusion = inclusion;
        if (inclusion == Inclusion.NON_NULL) {
            disable(Feature.WRITE_NULL_PROPERTIES);
        } else {
            enable(Feature.WRITE_NULL_PROPERTIES);
        }
    }

    @Deprecated
    public void setSerializationView(Class<?> cls) {
        this._serializationView = cls;
    }

    public String toString() {
        return "[SerializationConfig: flags=0x" + Integer.toHexString(this._featureFlags) + "]";
    }

    public SerializationConfig withAnnotationIntrospector(AnnotationIntrospector annotationIntrospector) {
        return new SerializationConfig(this, this._base.withAnnotationIntrospector(annotationIntrospector));
    }

    public SerializationConfig withClassIntrospector(ClassIntrospector<? extends BeanDescription> classIntrospector) {
        return new SerializationConfig(this, this._base.withClassIntrospector(classIntrospector));
    }

    public SerializationConfig withDateFormat(DateFormat dateFormat) {
        SerializationConfig serializationConfig = new SerializationConfig(this, this._base.withDateFormat(dateFormat));
        serializationConfig.set(Feature.WRITE_DATES_AS_TIMESTAMPS, dateFormat == null);
        return serializationConfig;
    }

    public SerializationConfig withFilters(FilterProvider filterProvider) {
        return new SerializationConfig(this, filterProvider);
    }

    public SerializationConfig withHandlerInstantiator(HandlerInstantiator handlerInstantiator) {
        return new SerializationConfig(this, this._base.withHandlerInstantiator(handlerInstantiator));
    }

    public SerializationConfig withPropertyNamingStrategy(PropertyNamingStrategy propertyNamingStrategy) {
        return new SerializationConfig(this, this._base.withPropertyNamingStrategy(propertyNamingStrategy));
    }

    public SerializationConfig withSubtypeResolver(SubtypeResolver subtypeResolver) {
        SerializationConfig serializationConfig = new SerializationConfig(this);
        serializationConfig._subtypeResolver = subtypeResolver;
        return serializationConfig;
    }

    public SerializationConfig withTypeFactory(TypeFactory typeFactory) {
        return new SerializationConfig(this, this._base.withTypeFactory(typeFactory));
    }

    public SerializationConfig withTypeResolverBuilder(TypeResolverBuilder<?> typeResolverBuilder) {
        return new SerializationConfig(this, this._base.withTypeResolverBuilder(typeResolverBuilder));
    }

    public SerializationConfig withView(Class<?> cls) {
        return new SerializationConfig(this, (Class) cls);
    }

    public SerializationConfig withVisibilityChecker(VisibilityChecker<?> visibilityChecker) {
        return new SerializationConfig(this, this._base.withVisibilityChecker(visibilityChecker));
    }
}
