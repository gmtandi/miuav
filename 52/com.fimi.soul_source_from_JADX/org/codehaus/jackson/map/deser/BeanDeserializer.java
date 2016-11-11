package org.codehaus.jackson.map.deser;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonParser.NumberType;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.BeanProperty.Std;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.DeserializerProvider;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ResolvableDeserializer;
import org.codehaus.jackson.map.TypeDeserializer;
import org.codehaus.jackson.map.annotate.JsonCachable;
import org.codehaus.jackson.map.deser.SettableBeanProperty.ManagedReferenceProperty;
import org.codehaus.jackson.map.deser.impl.BeanPropertyMap;
import org.codehaus.jackson.map.introspect.AnnotatedClass;
import org.codehaus.jackson.map.type.ClassKey;
import org.codehaus.jackson.map.util.ClassUtil;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.util.TokenBuffer;

@JsonCachable
public class BeanDeserializer extends StdDeserializer<Object> implements ResolvableDeserializer {
    protected final SettableAnyProperty _anySetter;
    protected final Map<String, SettableBeanProperty> _backRefs;
    protected final BeanPropertyMap _beanProperties;
    protected final JavaType _beanType;
    protected final Constructor<?> _defaultConstructor;
    protected final Delegating _delegatingCreator;
    protected final AnnotatedClass _forClass;
    protected final HashSet<String> _ignorableProps;
    protected final boolean _ignoreAllUnknown;
    protected final NumberBased _numberCreator;
    protected final BeanProperty _property;
    protected final PropertyBased _propertyBasedCreator;
    protected final StringBased _stringCreator;
    protected HashMap<ClassKey, JsonDeserializer<Object>> _subDeserializers;

    /* renamed from: org.codehaus.jackson.map.deser.BeanDeserializer.1 */
    /* synthetic */ class C35841 {
        static final /* synthetic */ int[] $SwitchMap$org$codehaus$jackson$JsonParser$NumberType;
        static final /* synthetic */ int[] $SwitchMap$org$codehaus$jackson$JsonToken;

        static {
            $SwitchMap$org$codehaus$jackson$JsonParser$NumberType = new int[NumberType.values().length];
            try {
                $SwitchMap$org$codehaus$jackson$JsonParser$NumberType[NumberType.INT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonParser$NumberType[NumberType.LONG.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            $SwitchMap$org$codehaus$jackson$JsonToken = new int[JsonToken.values().length];
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_STRING.ordinal()] = 1;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NUMBER_INT.ordinal()] = 2;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NUMBER_FLOAT.ordinal()] = 3;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_EMBEDDED_OBJECT.ordinal()] = 4;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_TRUE.ordinal()] = 5;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.VALUE_FALSE.ordinal()] = 6;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.START_ARRAY.ordinal()] = 7;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.FIELD_NAME.ordinal()] = 8;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$org$codehaus$jackson$JsonToken[JsonToken.END_OBJECT.ordinal()] = 9;
            } catch (NoSuchFieldError e11) {
            }
        }
    }

    protected BeanDeserializer(BeanDeserializer beanDeserializer) {
        super(beanDeserializer._beanType);
        this._forClass = beanDeserializer._forClass;
        this._beanType = beanDeserializer._beanType;
        this._property = beanDeserializer._property;
        this._beanProperties = beanDeserializer._beanProperties;
        this._backRefs = beanDeserializer._backRefs;
        this._ignorableProps = beanDeserializer._ignorableProps;
        this._ignoreAllUnknown = beanDeserializer._ignoreAllUnknown;
        this._anySetter = beanDeserializer._anySetter;
        this._defaultConstructor = beanDeserializer._defaultConstructor;
        this._stringCreator = beanDeserializer._stringCreator;
        this._numberCreator = beanDeserializer._numberCreator;
        this._delegatingCreator = beanDeserializer._delegatingCreator;
        this._propertyBasedCreator = beanDeserializer._propertyBasedCreator;
    }

    public BeanDeserializer(AnnotatedClass annotatedClass, JavaType javaType, BeanProperty beanProperty, CreatorContainer creatorContainer, BeanPropertyMap beanPropertyMap, Map<String, SettableBeanProperty> map, HashSet<String> hashSet, boolean z, SettableAnyProperty settableAnyProperty) {
        super(javaType);
        this._forClass = annotatedClass;
        this._beanType = javaType;
        this._property = beanProperty;
        this._beanProperties = beanPropertyMap;
        this._backRefs = map;
        this._ignorableProps = hashSet;
        this._ignoreAllUnknown = z;
        this._anySetter = settableAnyProperty;
        this._stringCreator = creatorContainer.stringCreator();
        this._numberCreator = creatorContainer.numberCreator();
        this._delegatingCreator = creatorContainer.delegatingCreator();
        this._propertyBasedCreator = creatorContainer.propertyBasedCreator();
        if (this._delegatingCreator == null && this._propertyBasedCreator == null) {
            this._defaultConstructor = creatorContainer.getDefaultConstructor();
        } else {
            this._defaultConstructor = null;
        }
    }

    protected final Object _deserializeUsingPropertyBased(JsonParser jsonParser, DeserializationContext deserializationContext) {
        Object build;
        PropertyBased propertyBased = this._propertyBasedCreator;
        PropertyValueBuffer startBuilding = propertyBased.startBuilding(jsonParser, deserializationContext);
        JsonToken currentToken = jsonParser.getCurrentToken();
        TokenBuffer tokenBuffer = null;
        while (currentToken == JsonToken.FIELD_NAME) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            SettableBeanProperty findCreatorProperty = propertyBased.findCreatorProperty(currentName);
            if (findCreatorProperty != null) {
                if (startBuilding.assignParameter(findCreatorProperty.getCreatorIndex(), findCreatorProperty.deserialize(jsonParser, deserializationContext))) {
                    jsonParser.nextToken();
                    try {
                        build = propertyBased.build(startBuilding);
                        if (build.getClass() != this._beanType.getRawClass()) {
                            return handlePolymorphic(jsonParser, deserializationContext, build, tokenBuffer);
                        }
                        return deserialize(jsonParser, deserializationContext, tokenBuffer != null ? handleUnknownProperties(deserializationContext, build, tokenBuffer) : build);
                    } catch (Throwable e) {
                        wrapAndThrow(e, this._beanType.getRawClass(), currentName, deserializationContext);
                    }
                } else {
                    continue;
                }
            } else {
                findCreatorProperty = this._beanProperties.find(currentName);
                if (findCreatorProperty != null) {
                    startBuilding.bufferProperty(findCreatorProperty, findCreatorProperty.deserialize(jsonParser, deserializationContext));
                } else if (this._ignorableProps != null && this._ignorableProps.contains(currentName)) {
                    jsonParser.skipChildren();
                } else if (this._anySetter != null) {
                    startBuilding.bufferAnyProperty(this._anySetter, currentName, this._anySetter.deserialize(jsonParser, deserializationContext));
                } else {
                    if (tokenBuffer == null) {
                        tokenBuffer = new TokenBuffer(jsonParser.getCodec());
                    }
                    tokenBuffer.writeFieldName(currentName);
                    tokenBuffer.copyCurrentStructure(jsonParser);
                }
            }
            currentToken = jsonParser.nextToken();
        }
        try {
            build = propertyBased.build(startBuilding);
            return tokenBuffer != null ? build.getClass() != this._beanType.getRawClass() ? handlePolymorphic(null, deserializationContext, build, tokenBuffer) : handleUnknownProperties(deserializationContext, build, tokenBuffer) : build;
        } catch (Throwable e2) {
            wrapInstantiationProblem(e2, deserializationContext);
            return null;
        }
    }

    protected JsonDeserializer<Object> _findSubclassDeserializer(DeserializationContext deserializationContext, Object obj, TokenBuffer tokenBuffer) {
        JsonDeserializer<Object> jsonDeserializer;
        synchronized (this) {
            jsonDeserializer = this._subDeserializers == null ? null : (JsonDeserializer) this._subDeserializers.get(new ClassKey(obj.getClass()));
        }
        if (jsonDeserializer == null) {
            DeserializerProvider deserializerProvider = deserializationContext.getDeserializerProvider();
            if (deserializerProvider != null) {
                jsonDeserializer = deserializerProvider.findValueDeserializer(deserializationContext.getConfig(), deserializationContext.constructType(obj.getClass()), this._property);
                if (jsonDeserializer != null) {
                    synchronized (this) {
                        if (this._subDeserializers == null) {
                            this._subDeserializers = new HashMap();
                        }
                        this._subDeserializers.put(new ClassKey(obj.getClass()), jsonDeserializer);
                    }
                }
            }
        }
        return jsonDeserializer;
    }

    protected Object constructDefaultInstance() {
        try {
            return this._defaultConstructor.newInstance(new Object[0]);
        } catch (Throwable e) {
            ClassUtil.unwrapAndThrowAsIAE(e);
            return null;
        }
    }

    public final Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.START_OBJECT) {
            jsonParser.nextToken();
            return deserializeFromObject(jsonParser, deserializationContext);
        }
        switch (C35841.$SwitchMap$org$codehaus$jackson$JsonToken[currentToken.ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                return deserializeFromString(jsonParser, deserializationContext);
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
            case Type.BYTE /*3*/:
                return deserializeFromNumber(jsonParser, deserializationContext);
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                return jsonParser.getEmbeddedObject();
            case Type.INT /*5*/:
            case Type.FLOAT /*6*/:
            case Type.LONG /*7*/:
                return deserializeUsingCreator(jsonParser, deserializationContext);
            case Type.DOUBLE /*8*/:
            case Type.ARRAY /*9*/:
                return deserializeFromObject(jsonParser, deserializationContext);
            default:
                throw deserializationContext.mappingException(getBeanClass());
        }
    }

    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.START_OBJECT) {
            currentToken = jsonParser.nextToken();
        }
        while (currentToken == JsonToken.FIELD_NAME) {
            String currentName = jsonParser.getCurrentName();
            SettableBeanProperty find = this._beanProperties.find(currentName);
            jsonParser.nextToken();
            if (find != null) {
                try {
                    find.deserializeAndSet(jsonParser, deserializationContext, obj);
                } catch (Throwable e) {
                    wrapAndThrow(e, obj, currentName, deserializationContext);
                }
            } else if (this._ignorableProps != null && this._ignorableProps.contains(currentName)) {
                jsonParser.skipChildren();
            } else if (this._anySetter != null) {
                this._anySetter.deserializeAndSet(jsonParser, deserializationContext, obj, currentName);
            } else {
                handleUnknownProperty(jsonParser, deserializationContext, obj, currentName);
            }
            currentToken = jsonParser.nextToken();
        }
        return obj;
    }

    public Object deserializeFromNumber(JsonParser jsonParser, DeserializationContext deserializationContext) {
        if (this._numberCreator != null) {
            switch (C35841.$SwitchMap$org$codehaus$jackson$JsonParser$NumberType[jsonParser.getNumberType().ordinal()]) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    return this._numberCreator.construct(jsonParser.getIntValue());
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    return this._numberCreator.construct(jsonParser.getLongValue());
            }
        }
        if (this._delegatingCreator != null) {
            return this._delegatingCreator.deserialize(jsonParser, deserializationContext);
        }
        throw deserializationContext.instantiationException(getBeanClass(), "no suitable creator method found to deserialize from JSON Number");
    }

    public Object deserializeFromObject(JsonParser jsonParser, DeserializationContext deserializationContext) {
        if (this._defaultConstructor != null) {
            Object constructDefaultInstance = constructDefaultInstance();
            while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
                String currentName = jsonParser.getCurrentName();
                jsonParser.nextToken();
                SettableBeanProperty find = this._beanProperties.find(currentName);
                if (find != null) {
                    try {
                        find.deserializeAndSet(jsonParser, deserializationContext, constructDefaultInstance);
                    } catch (Throwable e) {
                        wrapAndThrow(e, constructDefaultInstance, currentName, deserializationContext);
                    }
                } else if (this._ignorableProps != null && this._ignorableProps.contains(currentName)) {
                    jsonParser.skipChildren();
                } else if (this._anySetter != null) {
                    try {
                        this._anySetter.deserializeAndSet(jsonParser, deserializationContext, constructDefaultInstance, currentName);
                    } catch (Throwable e2) {
                        wrapAndThrow(e2, constructDefaultInstance, currentName, deserializationContext);
                    }
                } else {
                    handleUnknownProperty(jsonParser, deserializationContext, constructDefaultInstance, currentName);
                }
                jsonParser.nextToken();
            }
            return constructDefaultInstance;
        } else if (this._propertyBasedCreator != null) {
            return _deserializeUsingPropertyBased(jsonParser, deserializationContext);
        } else {
            if (this._delegatingCreator != null) {
                return this._delegatingCreator.deserialize(jsonParser, deserializationContext);
            }
            if (this._beanType.isAbstract()) {
                throw JsonMappingException.from(jsonParser, "Can not instantiate abstract type " + this._beanType + " (need to add/enable type information?)");
            }
            throw JsonMappingException.from(jsonParser, "No suitable constructor found for type " + this._beanType + ": can not instantiate from JSON object (need to add/enable type information?)");
        }
    }

    public Object deserializeFromString(JsonParser jsonParser, DeserializationContext deserializationContext) {
        if (this._stringCreator != null) {
            return this._stringCreator.construct(jsonParser.getText());
        }
        if (this._delegatingCreator != null) {
            return this._delegatingCreator.deserialize(jsonParser, deserializationContext);
        }
        if (deserializationContext.isEnabled(Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && jsonParser.getTextLength() == 0) {
            return null;
        }
        throw deserializationContext.instantiationException(getBeanClass(), "no suitable creator method found to deserialize from JSON String");
    }

    public Object deserializeUsingCreator(JsonParser jsonParser, DeserializationContext deserializationContext) {
        if (this._delegatingCreator != null) {
            try {
                return this._delegatingCreator.deserialize(jsonParser, deserializationContext);
            } catch (Throwable e) {
                wrapInstantiationProblem(e, deserializationContext);
            }
        }
        throw deserializationContext.mappingException(getBeanClass());
    }

    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
        return typeDeserializer.deserializeTypedFromObject(jsonParser, deserializationContext);
    }

    public SettableBeanProperty findBackReference(String str) {
        return this._backRefs == null ? null : (SettableBeanProperty) this._backRefs.get(str);
    }

    public final Class<?> getBeanClass() {
        return this._beanType.getRawClass();
    }

    public int getPropertyCount() {
        return this._beanProperties.size();
    }

    public JavaType getValueType() {
        return this._beanType;
    }

    protected Object handlePolymorphic(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj, TokenBuffer tokenBuffer) {
        JsonDeserializer _findSubclassDeserializer = _findSubclassDeserializer(deserializationContext, obj, tokenBuffer);
        Object deserialize;
        if (_findSubclassDeserializer != null) {
            if (tokenBuffer != null) {
                tokenBuffer.writeEndObject();
                JsonParser asParser = tokenBuffer.asParser();
                asParser.nextToken();
                deserialize = _findSubclassDeserializer.deserialize(asParser, deserializationContext, obj);
            } else {
                deserialize = obj;
            }
            return jsonParser != null ? _findSubclassDeserializer.deserialize(jsonParser, deserializationContext, deserialize) : deserialize;
        } else {
            deserialize = tokenBuffer != null ? handleUnknownProperties(deserializationContext, obj, tokenBuffer) : obj;
            return jsonParser != null ? deserialize(jsonParser, deserializationContext, deserialize) : deserialize;
        }
    }

    protected Object handleUnknownProperties(DeserializationContext deserializationContext, Object obj, TokenBuffer tokenBuffer) {
        tokenBuffer.writeEndObject();
        JsonParser asParser = tokenBuffer.asParser();
        while (asParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = asParser.getCurrentName();
            asParser.nextToken();
            handleUnknownProperty(asParser, deserializationContext, obj, currentName);
        }
        return obj;
    }

    protected void handleUnknownProperty(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj, String str) {
        if (this._ignoreAllUnknown || (this._ignorableProps != null && this._ignorableProps.contains(str))) {
            jsonParser.skipChildren();
        } else {
            super.handleUnknownProperty(jsonParser, deserializationContext, obj, str);
        }
    }

    public boolean hasProperty(String str) {
        return this._beanProperties.find(str) != null;
    }

    public Iterator<SettableBeanProperty> properties() {
        if (this._beanProperties != null) {
            return this._beanProperties.allProperties();
        }
        throw new IllegalStateException("Can only call before BeanDeserializer has been resolved");
    }

    public void resolve(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider) {
        Iterator allProperties = this._beanProperties.allProperties();
        while (allProperties.hasNext()) {
            SettableBeanProperty settableBeanProperty = (SettableBeanProperty) allProperties.next();
            if (!settableBeanProperty.hasValueDeserializer()) {
                settableBeanProperty.setValueDeserializer(findDeserializer(deserializationConfig, deserializerProvider, settableBeanProperty.getType(), settableBeanProperty));
            }
            String managedReferenceName = settableBeanProperty.getManagedReferenceName();
            if (managedReferenceName != null) {
                SettableBeanProperty findBackReference;
                JsonDeserializer jsonDeserializer = settableBeanProperty._valueDeserializer;
                boolean z = false;
                if (jsonDeserializer instanceof BeanDeserializer) {
                    findBackReference = ((BeanDeserializer) jsonDeserializer).findBackReference(managedReferenceName);
                } else if (jsonDeserializer instanceof ContainerDeserializer) {
                    jsonDeserializer = ((ContainerDeserializer) jsonDeserializer).getContentDeserializer();
                    if (jsonDeserializer instanceof BeanDeserializer) {
                        findBackReference = ((BeanDeserializer) jsonDeserializer).findBackReference(managedReferenceName);
                        z = true;
                    } else {
                        throw new IllegalArgumentException("Can not handle managed/back reference '" + managedReferenceName + "': value deserializer is of type ContainerDeserializer, but content type is not handled by a BeanDeserializer " + " (instead it's of type " + jsonDeserializer.getClass().getName() + ")");
                    }
                } else if (jsonDeserializer instanceof AbstractDeserializer) {
                    throw new IllegalArgumentException("Can not handle managed/back reference for abstract types (property " + this._beanType.getRawClass().getName() + "." + settableBeanProperty.getName() + ")");
                } else {
                    throw new IllegalArgumentException("Can not handle managed/back reference '" + managedReferenceName + "': type for value deserializer is not BeanDeserializer or ContainerDeserializer, but " + jsonDeserializer.getClass().getName());
                }
                if (findBackReference == null) {
                    throw new IllegalArgumentException("Can not handle managed/back reference '" + managedReferenceName + "': no back reference property found from type " + settableBeanProperty.getType());
                }
                JavaType javaType = this._beanType;
                JavaType type = findBackReference.getType();
                if (type.getRawClass().isAssignableFrom(javaType.getRawClass())) {
                    this._beanProperties.replace(new ManagedReferenceProperty(managedReferenceName, settableBeanProperty, findBackReference, this._forClass.getAnnotations(), z));
                } else {
                    throw new IllegalArgumentException("Can not handle managed/back reference '" + managedReferenceName + "': back reference type (" + type.getRawClass().getName() + ") not compatible with managed type (" + javaType.getRawClass().getName() + ")");
                }
            }
        }
        if (!(this._anySetter == null || this._anySetter.hasValueDeserializer())) {
            this._anySetter.setValueDeserializer(findDeserializer(deserializationConfig, deserializerProvider, this._anySetter.getType(), this._anySetter.getProperty()));
        }
        if (this._delegatingCreator != null) {
            this._delegatingCreator.setDeserializer(findDeserializer(deserializationConfig, deserializerProvider, this._delegatingCreator.getValueType(), new Std(null, this._delegatingCreator.getValueType(), this._forClass.getAnnotations(), this._delegatingCreator.getCreator())));
        }
        if (this._propertyBasedCreator != null) {
            for (SettableBeanProperty settableBeanProperty2 : this._propertyBasedCreator.properties()) {
                if (!settableBeanProperty2.hasValueDeserializer()) {
                    settableBeanProperty2.setValueDeserializer(findDeserializer(deserializationConfig, deserializerProvider, settableBeanProperty2.getType(), settableBeanProperty2));
                }
            }
        }
    }

    @Deprecated
    public void wrapAndThrow(Throwable th, Object obj, int i) {
        wrapAndThrow(th, obj, i, null);
    }

    public void wrapAndThrow(Throwable th, Object obj, int i, DeserializationContext deserializationContext) {
        Throwable th2 = th;
        while ((th2 instanceof InvocationTargetException) && th2.getCause() != null) {
            th2 = th2.getCause();
        }
        if (th2 instanceof Error) {
            throw ((Error) th2);
        }
        Object obj2 = (deserializationContext == null || deserializationContext.isEnabled(Feature.WRAP_EXCEPTIONS)) ? 1 : null;
        if (th2 instanceof IOException) {
            if (obj2 == null || !(th2 instanceof JsonMappingException)) {
                throw ((IOException) th2);
            }
        } else if (obj2 == null && (th2 instanceof RuntimeException)) {
            throw ((RuntimeException) th2);
        }
        throw JsonMappingException.wrapWithPath(th2, obj, i);
    }

    @Deprecated
    public void wrapAndThrow(Throwable th, Object obj, String str) {
        wrapAndThrow(th, obj, str, null);
    }

    public void wrapAndThrow(Throwable th, Object obj, String str, DeserializationContext deserializationContext) {
        Throwable th2 = th;
        while ((th2 instanceof InvocationTargetException) && th2.getCause() != null) {
            th2 = th2.getCause();
        }
        if (th2 instanceof Error) {
            throw ((Error) th2);
        }
        Object obj2 = (deserializationContext == null || deserializationContext.isEnabled(Feature.WRAP_EXCEPTIONS)) ? 1 : null;
        if (th2 instanceof IOException) {
            if (obj2 == null || !(th2 instanceof JsonMappingException)) {
                throw ((IOException) th2);
            }
        } else if (obj2 == null && (th2 instanceof RuntimeException)) {
            throw ((RuntimeException) th2);
        }
        throw JsonMappingException.wrapWithPath(th2, obj, str);
    }

    protected void wrapInstantiationProblem(Throwable th, DeserializationContext deserializationContext) {
        Throwable th2 = th;
        while ((th2 instanceof InvocationTargetException) && th2.getCause() != null) {
            th2 = th2.getCause();
        }
        if (th2 instanceof Error) {
            throw ((Error) th2);
        }
        Object obj = (deserializationContext == null || deserializationContext.isEnabled(Feature.WRAP_EXCEPTIONS)) ? 1 : null;
        if (th2 instanceof IOException) {
            throw ((IOException) th2);
        } else if (obj == null && (th2 instanceof RuntimeException)) {
            throw ((RuntimeException) th2);
        } else {
            throw deserializationContext.instantiationException(this._beanType.getRawClass(), th2);
        }
    }
}
