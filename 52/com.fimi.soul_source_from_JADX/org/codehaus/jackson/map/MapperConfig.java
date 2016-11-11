package org.codehaus.jackson.map;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.codehaus.jackson.map.AnnotationIntrospector.Pair;
import org.codehaus.jackson.map.ClassIntrospector.MixInResolver;
import org.codehaus.jackson.map.introspect.Annotated;
import org.codehaus.jackson.map.introspect.VisibilityChecker;
import org.codehaus.jackson.map.jsontype.SubtypeResolver;
import org.codehaus.jackson.map.jsontype.TypeIdResolver;
import org.codehaus.jackson.map.jsontype.TypeResolverBuilder;
import org.codehaus.jackson.map.jsontype.impl.StdSubtypeResolver;
import org.codehaus.jackson.map.type.ClassKey;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.map.util.ClassUtil;
import org.codehaus.jackson.map.util.StdDateFormat;
import org.codehaus.jackson.type.JavaType;

public abstract class MapperConfig<T extends MapperConfig<T>> implements MixInResolver {
    protected static final DateFormat DEFAULT_DATE_FORMAT;
    protected Base _base;
    protected HashMap<ClassKey, Class<?>> _mixInAnnotations;
    protected boolean _mixInAnnotationsShared;
    protected SubtypeResolver _subtypeResolver;

    public class Base {
        protected final AnnotationIntrospector _annotationIntrospector;
        protected final ClassIntrospector<? extends BeanDescription> _classIntrospector;
        protected final DateFormat _dateFormat;
        protected final HandlerInstantiator _handlerInstantiator;
        protected final PropertyNamingStrategy _propertyNamingStrategy;
        protected final TypeFactory _typeFactory;
        protected final TypeResolverBuilder<?> _typeResolverBuilder;
        protected final VisibilityChecker<?> _visibilityChecker;

        public Base(ClassIntrospector<? extends BeanDescription> classIntrospector, AnnotationIntrospector annotationIntrospector, VisibilityChecker<?> visibilityChecker, PropertyNamingStrategy propertyNamingStrategy, TypeFactory typeFactory, TypeResolverBuilder<?> typeResolverBuilder, DateFormat dateFormat, HandlerInstantiator handlerInstantiator) {
            this._classIntrospector = classIntrospector;
            this._annotationIntrospector = annotationIntrospector;
            this._visibilityChecker = visibilityChecker;
            this._propertyNamingStrategy = propertyNamingStrategy;
            this._typeFactory = typeFactory;
            this._typeResolverBuilder = typeResolverBuilder;
            this._dateFormat = dateFormat;
            this._handlerInstantiator = handlerInstantiator;
        }

        public AnnotationIntrospector getAnnotationIntrospector() {
            return this._annotationIntrospector;
        }

        public ClassIntrospector<? extends BeanDescription> getClassIntrospector() {
            return this._classIntrospector;
        }

        public DateFormat getDateFormat() {
            return this._dateFormat;
        }

        public HandlerInstantiator getHandlerInstantiator() {
            return this._handlerInstantiator;
        }

        public PropertyNamingStrategy getPropertyNamingStrategy() {
            return this._propertyNamingStrategy;
        }

        public TypeFactory getTypeFactory() {
            return this._typeFactory;
        }

        public TypeResolverBuilder<?> getTypeResolverBuilder() {
            return this._typeResolverBuilder;
        }

        public VisibilityChecker<?> getVisibilityChecker() {
            return this._visibilityChecker;
        }

        public Base withAnnotationIntrospector(AnnotationIntrospector annotationIntrospector) {
            return new Base(this._classIntrospector, annotationIntrospector, this._visibilityChecker, this._propertyNamingStrategy, this._typeFactory, this._typeResolverBuilder, this._dateFormat, this._handlerInstantiator);
        }

        public Base withClassIntrospector(ClassIntrospector<? extends BeanDescription> classIntrospector) {
            return new Base(classIntrospector, this._annotationIntrospector, this._visibilityChecker, this._propertyNamingStrategy, this._typeFactory, this._typeResolverBuilder, this._dateFormat, this._handlerInstantiator);
        }

        public Base withDateFormat(DateFormat dateFormat) {
            return new Base(this._classIntrospector, this._annotationIntrospector, this._visibilityChecker, this._propertyNamingStrategy, this._typeFactory, this._typeResolverBuilder, dateFormat, this._handlerInstantiator);
        }

        public Base withHandlerInstantiator(HandlerInstantiator handlerInstantiator) {
            return new Base(this._classIntrospector, this._annotationIntrospector, this._visibilityChecker, this._propertyNamingStrategy, this._typeFactory, this._typeResolverBuilder, this._dateFormat, handlerInstantiator);
        }

        public Base withPropertyNamingStrategy(PropertyNamingStrategy propertyNamingStrategy) {
            return new Base(this._classIntrospector, this._annotationIntrospector, this._visibilityChecker, propertyNamingStrategy, this._typeFactory, this._typeResolverBuilder, this._dateFormat, this._handlerInstantiator);
        }

        public Base withTypeFactory(TypeFactory typeFactory) {
            return new Base(this._classIntrospector, this._annotationIntrospector, this._visibilityChecker, this._propertyNamingStrategy, typeFactory, this._typeResolverBuilder, this._dateFormat, this._handlerInstantiator);
        }

        public Base withTypeResolverBuilder(TypeResolverBuilder<?> typeResolverBuilder) {
            return new Base(this._classIntrospector, this._annotationIntrospector, this._visibilityChecker, this._propertyNamingStrategy, this._typeFactory, typeResolverBuilder, this._dateFormat, this._handlerInstantiator);
        }

        public Base withVisibilityChecker(VisibilityChecker<?> visibilityChecker) {
            return new Base(this._classIntrospector, this._annotationIntrospector, visibilityChecker, this._propertyNamingStrategy, this._typeFactory, this._typeResolverBuilder, this._dateFormat, this._handlerInstantiator);
        }
    }

    static {
        DEFAULT_DATE_FORMAT = StdDateFormat.instance;
    }

    protected MapperConfig(ClassIntrospector<? extends BeanDescription> classIntrospector, AnnotationIntrospector annotationIntrospector, VisibilityChecker<?> visibilityChecker, SubtypeResolver subtypeResolver, PropertyNamingStrategy propertyNamingStrategy, TypeFactory typeFactory, HandlerInstantiator handlerInstantiator) {
        this._base = new Base(classIntrospector, annotationIntrospector, visibilityChecker, propertyNamingStrategy, typeFactory, null, DEFAULT_DATE_FORMAT, handlerInstantiator);
        this._subtypeResolver = subtypeResolver;
        this._mixInAnnotationsShared = true;
    }

    protected MapperConfig(MapperConfig<?> mapperConfig) {
        this(mapperConfig, mapperConfig._base, mapperConfig._subtypeResolver);
    }

    protected MapperConfig(MapperConfig<?> mapperConfig, Base base, SubtypeResolver subtypeResolver) {
        this._base = base;
        this._subtypeResolver = subtypeResolver;
        this._mixInAnnotationsShared = true;
        this._mixInAnnotations = mapperConfig._mixInAnnotations;
    }

    public final void addMixInAnnotations(Class<?> cls, Class<?> cls2) {
        if (this._mixInAnnotations == null) {
            this._mixInAnnotationsShared = false;
            this._mixInAnnotations = new HashMap();
        } else if (this._mixInAnnotationsShared) {
            this._mixInAnnotationsShared = false;
            this._mixInAnnotations = new HashMap(this._mixInAnnotations);
        }
        this._mixInAnnotations.put(new ClassKey(cls), cls2);
    }

    public final void appendAnnotationIntrospector(AnnotationIntrospector annotationIntrospector) {
        this._base = this._base.withAnnotationIntrospector(Pair.create(getAnnotationIntrospector(), annotationIntrospector));
    }

    public abstract boolean canOverrideAccessModifiers();

    public final JavaType constructType(Class<?> cls) {
        return getTypeFactory().constructType((Type) cls);
    }

    public abstract T createUnshared(SubtypeResolver subtypeResolver);

    @Deprecated
    public abstract T createUnshared(TypeResolverBuilder<?> typeResolverBuilder, VisibilityChecker<?> visibilityChecker, SubtypeResolver subtypeResolver);

    public final Class<?> findMixInClassFor(Class<?> cls) {
        return this._mixInAnnotations == null ? null : (Class) this._mixInAnnotations.get(new ClassKey(cls));
    }

    public abstract void fromAnnotations(Class<?> cls);

    public AnnotationIntrospector getAnnotationIntrospector() {
        return this._base.getAnnotationIntrospector();
    }

    public ClassIntrospector<? extends BeanDescription> getClassIntrospector() {
        return this._base.getClassIntrospector();
    }

    public final DateFormat getDateFormat() {
        return this._base.getDateFormat();
    }

    public final TypeResolverBuilder<?> getDefaultTyper(JavaType javaType) {
        return this._base.getTypeResolverBuilder();
    }

    public final VisibilityChecker<?> getDefaultVisibilityChecker() {
        return this._base.getVisibilityChecker();
    }

    public final HandlerInstantiator getHandlerInstantiator() {
        return this._base.getHandlerInstantiator();
    }

    public final PropertyNamingStrategy getPropertyNamingStrategy() {
        return this._base.getPropertyNamingStrategy();
    }

    public final SubtypeResolver getSubtypeResolver() {
        if (this._subtypeResolver == null) {
            this._subtypeResolver = new StdSubtypeResolver();
        }
        return this._subtypeResolver;
    }

    public final TypeFactory getTypeFactory() {
        return this._base.getTypeFactory();
    }

    public final void insertAnnotationIntrospector(AnnotationIntrospector annotationIntrospector) {
        this._base = this._base.withAnnotationIntrospector(Pair.create(annotationIntrospector, getAnnotationIntrospector()));
    }

    public abstract <DESC extends BeanDescription> DESC introspectClassAnnotations(Class<?> cls);

    public abstract <DESC extends BeanDescription> DESC introspectDirectClassAnnotations(Class<?> cls);

    public abstract boolean isAnnotationProcessingEnabled();

    public final int mixInCount() {
        return this._mixInAnnotations == null ? 0 : this._mixInAnnotations.size();
    }

    @Deprecated
    public final void setAnnotationIntrospector(AnnotationIntrospector annotationIntrospector) {
        this._base = this._base.withAnnotationIntrospector(annotationIntrospector);
    }

    @Deprecated
    public void setDateFormat(DateFormat dateFormat) {
        if (dateFormat == null) {
            dateFormat = StdDateFormat.instance;
        }
        this._base = this._base.withDateFormat(dateFormat);
    }

    @Deprecated
    public final void setIntrospector(ClassIntrospector<? extends BeanDescription> classIntrospector) {
        this._base = this._base.withClassIntrospector(classIntrospector);
    }

    public final void setMixInAnnotations(Map<Class<?>, Class<?>> map) {
        HashMap hashMap = null;
        if (map != null && map.size() > 0) {
            HashMap hashMap2 = new HashMap(map.size());
            for (Entry entry : map.entrySet()) {
                hashMap2.put(new ClassKey((Class) entry.getKey()), entry.getValue());
            }
            hashMap = hashMap2;
        }
        this._mixInAnnotationsShared = false;
        this._mixInAnnotations = hashMap;
    }

    @Deprecated
    public final void setSubtypeResolver(SubtypeResolver subtypeResolver) {
        this._subtypeResolver = subtypeResolver;
    }

    public TypeIdResolver typeIdResolverInstance(Annotated annotated, Class<? extends TypeIdResolver> cls) {
        HandlerInstantiator handlerInstantiator = getHandlerInstantiator();
        if (handlerInstantiator != null) {
            TypeIdResolver typeIdResolverInstance = handlerInstantiator.typeIdResolverInstance(this, annotated, cls);
            if (typeIdResolverInstance != null) {
                return typeIdResolverInstance;
            }
        }
        return (TypeIdResolver) ClassUtil.createInstance(cls, canOverrideAccessModifiers());
    }

    public TypeResolverBuilder<?> typeResolverBuilderInstance(Annotated annotated, Class<? extends TypeResolverBuilder<?>> cls) {
        HandlerInstantiator handlerInstantiator = getHandlerInstantiator();
        if (handlerInstantiator != null) {
            TypeResolverBuilder<?> typeResolverBuilderInstance = handlerInstantiator.typeResolverBuilderInstance(this, annotated, cls);
            if (typeResolverBuilderInstance != null) {
                return typeResolverBuilderInstance;
            }
        }
        return (TypeResolverBuilder) ClassUtil.createInstance(cls, canOverrideAccessModifiers());
    }

    public abstract T withAnnotationIntrospector(AnnotationIntrospector annotationIntrospector);

    public abstract T withClassIntrospector(ClassIntrospector<? extends BeanDescription> classIntrospector);

    public abstract T withDateFormat(DateFormat dateFormat);

    public abstract T withHandlerInstantiator(HandlerInstantiator handlerInstantiator);

    public abstract T withPropertyNamingStrategy(PropertyNamingStrategy propertyNamingStrategy);

    public abstract T withSubtypeResolver(SubtypeResolver subtypeResolver);

    public abstract T withTypeFactory(TypeFactory typeFactory);

    public abstract T withTypeResolverBuilder(TypeResolverBuilder<?> typeResolverBuilder);

    public abstract T withVisibilityChecker(VisibilityChecker<?> visibilityChecker);
}
