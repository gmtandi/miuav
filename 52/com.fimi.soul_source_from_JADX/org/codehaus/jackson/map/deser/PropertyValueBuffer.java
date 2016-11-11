package org.codehaus.jackson.map.deser;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationContext;

public final class PropertyValueBuffer {
    private PropertyValue _buffered;
    final DeserializationContext _context;
    final Object[] _creatorParameters;
    private int _paramsNeeded;
    final JsonParser _parser;

    public PropertyValueBuffer(JsonParser jsonParser, DeserializationContext deserializationContext, int i) {
        this._parser = jsonParser;
        this._context = deserializationContext;
        this._paramsNeeded = i;
        this._creatorParameters = new Object[i];
    }

    public boolean assignParameter(int i, Object obj) {
        this._creatorParameters[i] = obj;
        int i2 = this._paramsNeeded - 1;
        this._paramsNeeded = i2;
        return i2 <= 0;
    }

    public void bufferAnyProperty(SettableAnyProperty settableAnyProperty, String str, Object obj) {
        this._buffered = new Any(this._buffered, obj, settableAnyProperty, str);
    }

    public void bufferMapProperty(Object obj, Object obj2) {
        this._buffered = new Map(this._buffered, obj2, obj);
    }

    public void bufferProperty(SettableBeanProperty settableBeanProperty, Object obj) {
        this._buffered = new Regular(this._buffered, obj, settableBeanProperty);
    }

    protected PropertyValue buffered() {
        return this._buffered;
    }

    protected final Object[] getParameters(Object[] objArr) {
        if (objArr != null) {
            int length = this._creatorParameters.length;
            for (int i = 0; i < length; i++) {
                if (this._creatorParameters[i] == null) {
                    Object obj = objArr[i];
                    if (obj != null) {
                        this._creatorParameters[i] = obj;
                    }
                }
            }
        }
        return this._creatorParameters;
    }
}
