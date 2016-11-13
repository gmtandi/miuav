package org.codehaus.jackson;

import org.codehaus.jackson.smile.SmileConstants;

public abstract class JsonStreamContext {
    protected static final int TYPE_ARRAY = 1;
    protected static final int TYPE_OBJECT = 2;
    protected static final int TYPE_ROOT = 0;
    protected int _index;
    protected int _type;

    protected JsonStreamContext() {
    }

    public final int getCurrentIndex() {
        return this._index < 0 ? 0 : this._index;
    }

    public abstract String getCurrentName();

    public final int getEntryCount() {
        return this._index + TYPE_ARRAY;
    }

    public abstract JsonStreamContext getParent();

    public final String getTypeDesc() {
        switch (this._type) {
            case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                return "ROOT";
            case TYPE_ARRAY /*1*/:
                return "ARRAY";
            case TYPE_OBJECT /*2*/:
                return "OBJECT";
            default:
                return "?";
        }
    }

    public final boolean inArray() {
        return this._type == TYPE_ARRAY;
    }

    public final boolean inObject() {
        return this._type == TYPE_OBJECT;
    }

    public final boolean inRoot() {
        return this._type == 0;
    }
}
