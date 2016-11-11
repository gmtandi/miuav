package org.codehaus.jackson.map.deser;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonMappingException;

public class ThrowableDeserializer extends BeanDeserializer {
    protected static final String PROP_NAME_MESSAGE = "message";

    public ThrowableDeserializer(BeanDeserializer beanDeserializer) {
        super(beanDeserializer);
    }

    public Object deserializeFromObject(JsonParser jsonParser, DeserializationContext deserializationContext) {
        int i = 0;
        if (this._propertyBasedCreator != null) {
            return _deserializeUsingPropertyBased(jsonParser, deserializationContext);
        }
        if (this._delegatingCreator != null) {
            return this._delegatingCreator.deserialize(jsonParser, deserializationContext);
        }
        if (this._beanType.isAbstract()) {
            throw JsonMappingException.from(jsonParser, "Can not instantiate abstract type " + this._beanType + " (need to add/enable type information?)");
        } else if (this._stringCreator == null) {
            throw new JsonMappingException("Can not deserialize Throwable of type " + this._beanType + " without having either single-String-arg constructor; or explicit @JsonCreator");
        } else {
            int i2 = 0;
            Object[] objArr = null;
            Object obj = null;
            while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
                int i3;
                Object obj2;
                Object[] objArr2;
                String currentName = jsonParser.getCurrentName();
                SettableBeanProperty find = this._beanProperties.find(currentName);
                jsonParser.nextToken();
                if (find == null) {
                    if (PROP_NAME_MESSAGE.equals(currentName)) {
                        obj = this._stringCreator.construct(jsonParser.getText());
                        if (objArr != null) {
                            for (int i4 = 0; i4 < i2; i4 += 2) {
                                ((SettableBeanProperty) objArr[i4]).set(obj, objArr[i4 + 1]);
                            }
                            i3 = i2;
                            obj2 = obj;
                            objArr2 = null;
                        }
                    } else if (this._ignorableProps != null && this._ignorableProps.contains(currentName)) {
                        jsonParser.skipChildren();
                        i3 = i2;
                        objArr2 = objArr;
                        obj2 = obj;
                    } else if (this._anySetter != null) {
                        this._anySetter.deserializeAndSet(jsonParser, deserializationContext, obj, currentName);
                        i3 = i2;
                        objArr2 = objArr;
                        obj2 = obj;
                    } else {
                        handleUnknownProperty(jsonParser, deserializationContext, obj, currentName);
                    }
                    i3 = i2;
                    objArr2 = objArr;
                    obj2 = obj;
                } else if (obj != null) {
                    find.deserializeAndSet(jsonParser, deserializationContext, obj);
                    i3 = i2;
                    objArr2 = objArr;
                    obj2 = obj;
                } else {
                    if (objArr == null) {
                        i3 = this._beanProperties.size();
                        objArr = new Object[(i3 + i3)];
                    }
                    int i5 = i2 + 1;
                    objArr[i2] = find;
                    i3 = i5 + 1;
                    objArr[i5] = find.deserialize(jsonParser, deserializationContext);
                    objArr2 = objArr;
                    obj2 = obj;
                }
                jsonParser.nextToken();
                obj = obj2;
                objArr = objArr2;
                i2 = i3;
            }
            if (obj != null) {
                return obj;
            }
            obj = this._stringCreator.construct(null);
            if (objArr == null) {
                return obj;
            }
            while (i < i2) {
                ((SettableBeanProperty) objArr[i]).set(obj, objArr[i + 1]);
                i += 2;
            }
            return obj;
        }
    }
}
