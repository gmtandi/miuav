package org.codehaus.jackson.map.exc;

import org.codehaus.jackson.JsonLocation;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.JsonMappingException;

public class UnrecognizedPropertyException extends JsonMappingException {
    private static final long serialVersionUID = 1;
    protected final Class<?> _referringClass;
    protected final String _unrecognizedPropertyName;

    public UnrecognizedPropertyException(String str, JsonLocation jsonLocation, Class<?> cls, String str2) {
        super(str, jsonLocation);
        this._referringClass = cls;
        this._unrecognizedPropertyName = str2;
    }

    public static UnrecognizedPropertyException from(JsonParser jsonParser, Object obj, String str) {
        if (obj == null) {
            throw new IllegalArgumentException();
        }
        Class cls = obj instanceof Class ? (Class) obj : obj.getClass();
        UnrecognizedPropertyException unrecognizedPropertyException = new UnrecognizedPropertyException("Unrecognized field \"" + str + "\" (Class " + cls.getName() + "), not marked as ignorable", jsonParser.getCurrentLocation(), cls, str);
        unrecognizedPropertyException.prependPath(obj, str);
        return unrecognizedPropertyException;
    }

    public Class<?> getReferringClass() {
        return this._referringClass;
    }

    public String getUnrecognizedPropertyName() {
        return this._unrecognizedPropertyName;
    }
}
