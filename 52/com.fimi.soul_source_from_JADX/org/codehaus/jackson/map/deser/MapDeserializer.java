package org.codehaus.jackson.map.deser;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Map;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.DeserializerProvider;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.KeyDeserializer;
import org.codehaus.jackson.map.ResolvableDeserializer;
import org.codehaus.jackson.map.TypeDeserializer;
import org.codehaus.jackson.map.annotate.JacksonStdImpl;
import org.codehaus.jackson.map.util.ArrayBuilders;
import org.codehaus.jackson.type.JavaType;

@JacksonStdImpl
public class MapDeserializer extends ContainerDeserializer<Map<Object, Object>> implements ResolvableDeserializer {
    protected final Constructor<Map<Object, Object>> _defaultCtor;
    protected HashSet<String> _ignorableProperties;
    protected final KeyDeserializer _keyDeserializer;
    protected final JavaType _mapType;
    protected PropertyBased _propertyBasedCreator;
    protected final JsonDeserializer<Object> _valueDeserializer;
    protected final TypeDeserializer _valueTypeDeserializer;

    public MapDeserializer(JavaType javaType, Constructor<Map<Object, Object>> constructor, KeyDeserializer keyDeserializer, JsonDeserializer<Object> jsonDeserializer, TypeDeserializer typeDeserializer) {
        super(Map.class);
        this._mapType = javaType;
        this._defaultCtor = constructor;
        this._keyDeserializer = keyDeserializer;
        this._valueDeserializer = jsonDeserializer;
        this._valueTypeDeserializer = typeDeserializer;
    }

    public Map<Object, Object> _deserializeUsingCreator(JsonParser jsonParser, DeserializationContext deserializationContext) {
        PropertyBased propertyBased = this._propertyBasedCreator;
        PropertyValueBuffer startBuilding = propertyBased.startBuilding(jsonParser, deserializationContext);
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.START_OBJECT) {
            currentToken = jsonParser.nextToken();
        }
        JsonDeserializer jsonDeserializer = this._valueDeserializer;
        TypeDeserializer typeDeserializer = this._valueTypeDeserializer;
        while (currentToken == JsonToken.FIELD_NAME) {
            String currentName = jsonParser.getCurrentName();
            JsonToken nextToken = jsonParser.nextToken();
            if (this._ignorableProperties == null || !this._ignorableProperties.contains(currentName)) {
                SettableBeanProperty findCreatorProperty = propertyBased.findCreatorProperty(currentName);
                if (findCreatorProperty != null) {
                    if (startBuilding.assignParameter(findCreatorProperty.getCreatorIndex(), findCreatorProperty.deserialize(jsonParser, deserializationContext))) {
                        jsonParser.nextToken();
                        try {
                            Map<Object, Object> map = (Map) propertyBased.build(startBuilding);
                            _readAndBind(jsonParser, deserializationContext, map);
                            return map;
                        } catch (Throwable e) {
                            wrapAndThrow(e, this._mapType.getRawClass());
                            return null;
                        }
                    }
                } else {
                    Object currentName2 = jsonParser.getCurrentName();
                    if (this._keyDeserializer != null) {
                        currentName2 = this._keyDeserializer.deserializeKey(currentName2, deserializationContext);
                    }
                    Object deserialize = nextToken == JsonToken.VALUE_NULL ? null : typeDeserializer == null ? jsonDeserializer.deserialize(jsonParser, deserializationContext) : jsonDeserializer.deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
                    startBuilding.bufferMapProperty(currentName2, deserialize);
                }
            } else {
                jsonParser.skipChildren();
            }
            currentToken = jsonParser.nextToken();
        }
        try {
            return (Map) propertyBased.build(startBuilding);
        } catch (Throwable e2) {
            wrapAndThrow(e2, this._mapType.getRawClass());
            return null;
        }
    }

    protected final void _readAndBind(JsonParser jsonParser, DeserializationContext deserializationContext, Map<Object, Object> map) {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.START_OBJECT) {
            currentToken = jsonParser.nextToken();
        }
        KeyDeserializer keyDeserializer = this._keyDeserializer;
        JsonDeserializer jsonDeserializer = this._valueDeserializer;
        TypeDeserializer typeDeserializer = this._valueTypeDeserializer;
        while (currentToken == JsonToken.FIELD_NAME) {
            String currentName = jsonParser.getCurrentName();
            Object deserializeKey = keyDeserializer == null ? currentName : keyDeserializer.deserializeKey(currentName, deserializationContext);
            JsonToken nextToken = jsonParser.nextToken();
            if (this._ignorableProperties == null || !this._ignorableProperties.contains(currentName)) {
                Object deserialize = nextToken == JsonToken.VALUE_NULL ? null : typeDeserializer == null ? jsonDeserializer.deserialize(jsonParser, deserializationContext) : jsonDeserializer.deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
                map.put(deserializeKey, deserialize);
            } else {
                jsonParser.skipChildren();
            }
            currentToken = jsonParser.nextToken();
        }
    }

    public Map<Object, Object> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken != JsonToken.START_OBJECT && currentToken != JsonToken.FIELD_NAME && currentToken != JsonToken.END_OBJECT) {
            throw deserializationContext.mappingException(getMapClass());
        } else if (this._propertyBasedCreator != null) {
            return _deserializeUsingCreator(jsonParser, deserializationContext);
        } else {
            if (this._defaultCtor == null) {
                throw deserializationContext.instantiationException(getMapClass(), "No default constructor found");
            }
            try {
                Map<Object, Object> map = (Map) this._defaultCtor.newInstance(new Object[0]);
                _readAndBind(jsonParser, deserializationContext, map);
                return map;
            } catch (Throwable e) {
                throw deserializationContext.instantiationException(getMapClass(), e);
            }
        }
    }

    public Map<Object, Object> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, Map<Object, Object> map) {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.START_OBJECT || currentToken == JsonToken.FIELD_NAME) {
            _readAndBind(jsonParser, deserializationContext, map);
            return map;
        }
        throw deserializationContext.mappingException(getMapClass());
    }

    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
        return typeDeserializer.deserializeTypedFromObject(jsonParser, deserializationContext);
    }

    public JsonDeserializer<Object> getContentDeserializer() {
        return this._valueDeserializer;
    }

    public JavaType getContentType() {
        return this._mapType.getContentType();
    }

    public final Class<?> getMapClass() {
        return this._mapType.getRawClass();
    }

    public JavaType getValueType() {
        return this._mapType;
    }

    public void resolve(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider) {
        if (this._propertyBasedCreator != null) {
            for (SettableBeanProperty settableBeanProperty : this._propertyBasedCreator.properties()) {
                settableBeanProperty.setValueDeserializer(findDeserializer(deserializationConfig, deserializerProvider, settableBeanProperty.getType(), settableBeanProperty));
            }
        }
    }

    public void setCreators(CreatorContainer creatorContainer) {
        this._propertyBasedCreator = creatorContainer.propertyBasedCreator();
    }

    public void setIgnorableProperties(String[] strArr) {
        HashSet arrayToSet = (strArr == null || strArr.length == 0) ? null : ArrayBuilders.arrayToSet(strArr);
        this._ignorableProperties = arrayToSet;
    }

    protected void wrapAndThrow(Throwable th, Object obj) {
        Throwable th2 = th;
        while ((th2 instanceof InvocationTargetException) && th2.getCause() != null) {
            th2 = th2.getCause();
        }
        if (th2 instanceof Error) {
            throw ((Error) th2);
        } else if (!(th2 instanceof IOException) || (th2 instanceof JsonMappingException)) {
            throw JsonMappingException.wrapWithPath(th2, obj, null);
        } else {
            throw ((IOException) th2);
        }
    }
}
