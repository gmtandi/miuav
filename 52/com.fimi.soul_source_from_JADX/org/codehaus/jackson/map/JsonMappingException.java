package org.codehaus.jackson.map;

import java.io.Serializable;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.codehaus.jackson.JsonLocation;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.p122a.p123a.p124f.p125c.C3022o;

public class JsonMappingException extends JsonProcessingException {
    static final int MAX_REFS_TO_LIST = 1000;
    private static final long serialVersionUID = 1;
    protected LinkedList<Reference> _path;

    public class Reference implements Serializable {
        private static final long serialVersionUID = 1;
        protected String _fieldName;
        protected Object _from;
        protected int _index;

        protected Reference() {
            this._index = -1;
        }

        public Reference(Object obj) {
            this._index = -1;
            this._from = obj;
        }

        public Reference(Object obj, int i) {
            this._index = -1;
            this._from = obj;
            this._index = i;
        }

        public Reference(Object obj, String str) {
            this._index = -1;
            this._from = obj;
            if (str == null) {
                throw new NullPointerException("Can not pass null fieldName");
            }
            this._fieldName = str;
        }

        public String getFieldName() {
            return this._fieldName;
        }

        public Object getFrom() {
            return this._from;
        }

        public int getIndex() {
            return this._index;
        }

        public void setFieldName(String str) {
            this._fieldName = str;
        }

        public void setFrom(Object obj) {
            this._from = obj;
        }

        public void setIndex(int i) {
            this._index = i;
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            Class cls = this._from instanceof Class ? (Class) this._from : this._from.getClass();
            Package packageR = cls.getPackage();
            if (packageR != null) {
                stringBuilder.append(packageR.getName());
                stringBuilder.append('.');
            }
            stringBuilder.append(cls.getSimpleName());
            stringBuilder.append('[');
            if (this._fieldName != null) {
                stringBuilder.append(C3022o.f15057e);
                stringBuilder.append(this._fieldName);
                stringBuilder.append(C3022o.f15057e);
            } else if (this._index >= 0) {
                stringBuilder.append(this._index);
            } else {
                stringBuilder.append('?');
            }
            stringBuilder.append(']');
            return stringBuilder.toString();
        }
    }

    public JsonMappingException(String str) {
        super(str);
    }

    public JsonMappingException(String str, Throwable th) {
        super(str, th);
    }

    public JsonMappingException(String str, JsonLocation jsonLocation) {
        super(str, jsonLocation);
    }

    public JsonMappingException(String str, JsonLocation jsonLocation, Throwable th) {
        super(str, jsonLocation, th);
    }

    public static JsonMappingException from(JsonParser jsonParser, String str) {
        return new JsonMappingException(str, jsonParser.getTokenLocation());
    }

    public static JsonMappingException from(JsonParser jsonParser, String str, Throwable th) {
        return new JsonMappingException(str, jsonParser.getTokenLocation(), th);
    }

    public static JsonMappingException wrapWithPath(Throwable th, Object obj, int i) {
        return wrapWithPath(th, new Reference(obj, i));
    }

    public static JsonMappingException wrapWithPath(Throwable th, Object obj, String str) {
        return wrapWithPath(th, new Reference(obj, str));
    }

    public static JsonMappingException wrapWithPath(Throwable th, Reference reference) {
        JsonMappingException jsonMappingException;
        if (th instanceof JsonMappingException) {
            jsonMappingException = (JsonMappingException) th;
        } else {
            String message = th.getMessage();
            if (message == null || message.length() == 0) {
                message = "(was " + th.getClass().getName() + ")";
            }
            jsonMappingException = new JsonMappingException(message, null, th);
        }
        jsonMappingException.prependPath(reference);
        return jsonMappingException;
    }

    protected void _appendPathDesc(StringBuilder stringBuilder) {
        Iterator it = this._path.iterator();
        while (it.hasNext()) {
            stringBuilder.append(((Reference) it.next()).toString());
            if (it.hasNext()) {
                stringBuilder.append("->");
            }
        }
    }

    public String getMessage() {
        String message = super.getMessage();
        if (this._path == null) {
            return message;
        }
        StringBuilder stringBuilder = message == null ? new StringBuilder() : new StringBuilder(message);
        stringBuilder.append(" (through reference chain: ");
        _appendPathDesc(stringBuilder);
        stringBuilder.append(')');
        return stringBuilder.toString();
    }

    public List<Reference> getPath() {
        return this._path == null ? Collections.emptyList() : Collections.unmodifiableList(this._path);
    }

    public void prependPath(Object obj, int i) {
        prependPath(new Reference(obj, i));
    }

    public void prependPath(Object obj, String str) {
        prependPath(new Reference(obj, str));
    }

    public void prependPath(Reference reference) {
        if (this._path == null) {
            this._path = new LinkedList();
        }
        if (this._path.size() < MAX_REFS_TO_LIST) {
            this._path.addFirst(reference);
        }
    }

    public String toString() {
        return getClass().getName() + ": " + getMessage();
    }
}
