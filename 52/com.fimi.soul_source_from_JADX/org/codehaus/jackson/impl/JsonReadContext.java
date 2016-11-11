package org.codehaus.jackson.impl;

import org.codehaus.jackson.JsonLocation;
import org.codehaus.jackson.JsonStreamContext;
import org.codehaus.jackson.smile.SmileConstants;
import org.codehaus.jackson.util.CharTypes;
import org.p122a.p123a.p124f.p125c.C3022o;

public final class JsonReadContext extends JsonStreamContext {
    protected JsonReadContext _child;
    protected int _columnNr;
    protected String _currentName;
    protected int _lineNr;
    protected final JsonReadContext _parent;

    public JsonReadContext(JsonReadContext jsonReadContext, int i, int i2, int i3) {
        this._child = null;
        this._type = i;
        this._parent = jsonReadContext;
        this._lineNr = i2;
        this._columnNr = i3;
        this._index = -1;
    }

    public static JsonReadContext createRootContext(int i, int i2) {
        return new JsonReadContext(null, 0, i, i2);
    }

    public final JsonReadContext createChildArrayContext(int i, int i2) {
        JsonReadContext jsonReadContext = this._child;
        if (jsonReadContext == null) {
            jsonReadContext = new JsonReadContext(this, 1, i, i2);
            this._child = jsonReadContext;
            return jsonReadContext;
        }
        jsonReadContext.reset(1, i, i2);
        return jsonReadContext;
    }

    public final JsonReadContext createChildObjectContext(int i, int i2) {
        JsonReadContext jsonReadContext = this._child;
        if (jsonReadContext == null) {
            jsonReadContext = new JsonReadContext(this, 2, i, i2);
            this._child = jsonReadContext;
            return jsonReadContext;
        }
        jsonReadContext.reset(2, i, i2);
        return jsonReadContext;
    }

    public final boolean expectComma() {
        int i = this._index + 1;
        this._index = i;
        return this._type != 0 && i > 0;
    }

    public final String getCurrentName() {
        return this._currentName;
    }

    public final JsonReadContext getParent() {
        return this._parent;
    }

    public final JsonLocation getStartLocation(Object obj) {
        return new JsonLocation(obj, -1, this._lineNr, this._columnNr);
    }

    protected final void reset(int i, int i2, int i3) {
        this._type = i;
        this._index = -1;
        this._lineNr = i2;
        this._columnNr = i3;
        this._currentName = null;
    }

    public void setCurrentName(String str) {
        this._currentName = str;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder(64);
        switch (this._type) {
            case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                stringBuilder.append("/");
                break;
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                stringBuilder.append('[');
                stringBuilder.append(getCurrentIndex());
                stringBuilder.append(']');
                break;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                stringBuilder.append('{');
                if (this._currentName != null) {
                    stringBuilder.append(C3022o.f15057e);
                    CharTypes.appendQuoted(stringBuilder, this._currentName);
                    stringBuilder.append(C3022o.f15057e);
                } else {
                    stringBuilder.append('?');
                }
                stringBuilder.append('}');
                break;
        }
        return stringBuilder.toString();
    }
}
