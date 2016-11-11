package org.codehaus.jackson.xc;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.TypeDeserializer;
import org.codehaus.jackson.map.deser.StdDeserializer;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.type.JavaType;

public class XmlAdapterJsonDeserializer extends StdDeserializer<Object> {
    protected static final JavaType ADAPTER_TYPE;
    protected JsonDeserializer<?> _deserializer;
    protected final BeanProperty _property;
    protected final JavaType _valueType;
    protected final XmlAdapter<Object, Object> _xmlAdapter;

    static {
        ADAPTER_TYPE = TypeFactory.defaultInstance().uncheckedSimpleType(XmlAdapter.class);
    }

    public XmlAdapterJsonDeserializer(XmlAdapter<Object, Object> xmlAdapter, BeanProperty beanProperty) {
        super(Object.class);
        this._property = beanProperty;
        this._xmlAdapter = xmlAdapter;
        TypeFactory defaultInstance = TypeFactory.defaultInstance();
        JavaType[] findTypeParameters = defaultInstance.findTypeParameters(defaultInstance.constructType(xmlAdapter.getClass()), XmlAdapter.class);
        JavaType unknownType = (findTypeParameters == null || findTypeParameters.length == 0) ? TypeFactory.unknownType() : findTypeParameters[0];
        this._valueType = unknownType;
    }

    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        JsonDeserializer jsonDeserializer = this._deserializer;
        if (jsonDeserializer == null) {
            jsonDeserializer = deserializationContext.getDeserializerProvider().findValueDeserializer(deserializationContext.getConfig(), this._valueType, this._property);
            this._deserializer = jsonDeserializer;
        }
        try {
            return this._xmlAdapter.unmarshal(jsonDeserializer.deserialize(jsonParser, deserializationContext));
        } catch (Throwable e) {
            throw new JsonMappingException("Unable to unmarshal (to type " + this._valueType + "): " + e.getMessage(), e);
        }
    }

    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
        return typeDeserializer.deserializeTypedFromAny(jsonParser, deserializationContext);
    }
}
