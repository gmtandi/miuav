package org.codehaus.jackson.impl;

import org.codehaus.jackson.JsonStreamContext;
import org.p122a.p123a.p124f.p125c.C3022o;

public class JsonWriteContext extends JsonStreamContext {
    public static final int STATUS_EXPECT_NAME = 5;
    public static final int STATUS_EXPECT_VALUE = 4;
    public static final int STATUS_OK_AFTER_COLON = 2;
    public static final int STATUS_OK_AFTER_COMMA = 1;
    public static final int STATUS_OK_AFTER_SPACE = 3;
    public static final int STATUS_OK_AS_IS = 0;
    protected JsonWriteContext _child;
    protected String _currentName;
    protected final JsonWriteContext _parent;

    protected JsonWriteContext(int i, JsonWriteContext jsonWriteContext) {
        this._child = null;
        this._type = i;
        this._parent = jsonWriteContext;
        this._index = -1;
    }

    public static JsonWriteContext createRootContext() {
        return new JsonWriteContext(0, null);
    }

    private final JsonWriteContext reset(int i) {
        this._type = i;
        this._index = -1;
        this._currentName = null;
        return this;
    }

    protected final void appendDesc(StringBuilder stringBuilder) {
        if (this._type == STATUS_OK_AFTER_COLON) {
            stringBuilder.append('{');
            if (this._currentName != null) {
                stringBuilder.append(C3022o.f15057e);
                stringBuilder.append(this._currentName);
                stringBuilder.append(C3022o.f15057e);
            } else {
                stringBuilder.append('?');
            }
            stringBuilder.append('}');
        } else if (this._type == STATUS_OK_AFTER_COMMA) {
            stringBuilder.append('[');
            stringBuilder.append(getCurrentIndex());
            stringBuilder.append(']');
        } else {
            stringBuilder.append("/");
        }
    }

    public final JsonWriteContext createChildArrayContext() {
        JsonWriteContext jsonWriteContext = this._child;
        if (jsonWriteContext != null) {
            return jsonWriteContext.reset(STATUS_OK_AFTER_COMMA);
        }
        jsonWriteContext = new JsonWriteContext(STATUS_OK_AFTER_COMMA, this);
        this._child = jsonWriteContext;
        return jsonWriteContext;
    }

    public final JsonWriteContext createChildObjectContext() {
        JsonWriteContext jsonWriteContext = this._child;
        if (jsonWriteContext != null) {
            return jsonWriteContext.reset(STATUS_OK_AFTER_COLON);
        }
        jsonWriteContext = new JsonWriteContext(STATUS_OK_AFTER_COLON, this);
        this._child = jsonWriteContext;
        return jsonWriteContext;
    }

    public final String getCurrentName() {
        return this._currentName;
    }

    public final JsonWriteContext getParent() {
        return this._parent;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder(64);
        appendDesc(stringBuilder);
        return stringBuilder.toString();
    }

    public final int writeFieldName(String str) {
        if (this._type != STATUS_OK_AFTER_COLON || this._currentName != null) {
            return STATUS_EXPECT_VALUE;
        }
        this._currentName = str;
        return this._index < 0 ? 0 : STATUS_OK_AFTER_COMMA;
    }

    public final int writeValue() {
        if (this._type == STATUS_OK_AFTER_COLON) {
            if (this._currentName == null) {
                return STATUS_EXPECT_NAME;
            }
            this._currentName = null;
            this._index += STATUS_OK_AFTER_COMMA;
            return STATUS_OK_AFTER_COLON;
        } else if (this._type == STATUS_OK_AFTER_COMMA) {
            int i = this._index;
            this._index += STATUS_OK_AFTER_COMMA;
            return i >= 0 ? STATUS_OK_AFTER_COMMA : 0;
        } else {
            this._index += STATUS_OK_AFTER_COMMA;
            return this._index != 0 ? STATUS_OK_AFTER_SPACE : 0;
        }
    }
}
