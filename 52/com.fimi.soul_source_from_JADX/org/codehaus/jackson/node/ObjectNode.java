package org.codehaus.jackson.node;

import com.xiaomi.mipush.sdk.MiPushClient;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.TypeSerializer;
import org.codehaus.jackson.node.ContainerNode.NoNodesIterator;
import org.codehaus.jackson.node.ContainerNode.NoStringsIterator;

public class ObjectNode extends ContainerNode {
    protected LinkedHashMap<String, JsonNode> _children;

    public class NoFieldsIterator implements Iterator<Entry<String, JsonNode>> {
        static final NoFieldsIterator instance;

        static {
            instance = new NoFieldsIterator();
        }

        private NoFieldsIterator() {
        }

        public boolean hasNext() {
            return false;
        }

        public Entry<String, JsonNode> next() {
            throw new NoSuchElementException();
        }

        public void remove() {
            throw new IllegalStateException();
        }
    }

    public ObjectNode(JsonNodeFactory jsonNodeFactory) {
        super(jsonNodeFactory);
        this._children = null;
    }

    private final JsonNode _put(String str, JsonNode jsonNode) {
        if (this._children == null) {
            this._children = new LinkedHashMap();
        }
        return (JsonNode) this._children.put(str, jsonNode);
    }

    public JsonToken asToken() {
        return JsonToken.START_OBJECT;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        ObjectNode objectNode = (ObjectNode) obj;
        if (objectNode.size() != size()) {
            return false;
        }
        if (this._children != null) {
            for (Entry entry : this._children.entrySet()) {
                String str = (String) entry.getKey();
                JsonNode jsonNode = (JsonNode) entry.getValue();
                JsonNode jsonNode2 = objectNode.get(str);
                if (jsonNode2 != null) {
                    if (!jsonNode2.equals(jsonNode)) {
                    }
                }
                return false;
            }
        }
        return true;
    }

    public ObjectNode findParent(String str) {
        if (this._children != null) {
            for (Entry entry : this._children.entrySet()) {
                if (str.equals(entry.getKey())) {
                    return this;
                }
                JsonNode findParent = ((JsonNode) entry.getValue()).findParent(str);
                if (findParent != null) {
                    return (ObjectNode) findParent;
                }
            }
        }
        return null;
    }

    public List<JsonNode> findParents(String str, List<JsonNode> list) {
        if (this._children == null) {
            return list;
        }
        List<JsonNode> list2 = list;
        for (Entry entry : this._children.entrySet()) {
            List<JsonNode> arrayList;
            if (str.equals(entry.getKey())) {
                arrayList = list2 == null ? new ArrayList() : list2;
                arrayList.add(this);
            } else {
                arrayList = ((JsonNode) entry.getValue()).findParents(str, list2);
            }
            list2 = arrayList;
        }
        return list2;
    }

    public JsonNode findValue(String str) {
        if (this._children != null) {
            for (Entry entry : this._children.entrySet()) {
                if (str.equals(entry.getKey())) {
                    return (JsonNode) entry.getValue();
                }
                JsonNode findValue = ((JsonNode) entry.getValue()).findValue(str);
                if (findValue != null) {
                    return findValue;
                }
            }
        }
        return null;
    }

    public List<JsonNode> findValues(String str, List<JsonNode> list) {
        if (this._children == null) {
            return list;
        }
        List<JsonNode> arrayList;
        List list2 = list;
        for (Entry entry : this._children.entrySet()) {
            if (str.equals(entry.getKey())) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                list2.add(entry.getValue());
            } else {
                list2 = ((JsonNode) entry.getValue()).findValues(str, arrayList);
            }
        }
        return arrayList;
    }

    public List<String> findValuesAsText(String str, List<String> list) {
        if (this._children == null) {
            return list;
        }
        List<String> arrayList;
        List list2 = list;
        for (Entry entry : this._children.entrySet()) {
            if (str.equals(entry.getKey())) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                list2.add(((JsonNode) entry.getValue()).getValueAsText());
            } else {
                list2 = ((JsonNode) entry.getValue()).findValuesAsText(str, arrayList);
            }
        }
        return arrayList;
    }

    public JsonNode get(int i) {
        return null;
    }

    public JsonNode get(String str) {
        return this._children != null ? (JsonNode) this._children.get(str) : null;
    }

    public Iterator<JsonNode> getElements() {
        return this._children == null ? NoNodesIterator.instance() : this._children.values().iterator();
    }

    public Iterator<String> getFieldNames() {
        return this._children == null ? NoStringsIterator.instance() : this._children.keySet().iterator();
    }

    public Iterator<Entry<String, JsonNode>> getFields() {
        return this._children == null ? NoFieldsIterator.instance : this._children.entrySet().iterator();
    }

    public int hashCode() {
        return this._children == null ? -1 : this._children.hashCode();
    }

    public boolean isObject() {
        return true;
    }

    public JsonNode path(int i) {
        return MissingNode.getInstance();
    }

    public JsonNode path(String str) {
        if (this._children != null) {
            JsonNode jsonNode = (JsonNode) this._children.get(str);
            if (jsonNode != null) {
                return jsonNode;
            }
        }
        return MissingNode.getInstance();
    }

    public JsonNode put(String str, JsonNode jsonNode) {
        if (jsonNode == null) {
            jsonNode = nullNode();
        }
        return _put(str, jsonNode);
    }

    public void put(String str, double d) {
        _put(str, numberNode(d));
    }

    public void put(String str, float f) {
        _put(str, numberNode(f));
    }

    public void put(String str, int i) {
        _put(str, numberNode(i));
    }

    public void put(String str, long j) {
        _put(str, numberNode(j));
    }

    public void put(String str, String str2) {
        if (str2 == null) {
            putNull(str);
        } else {
            _put(str, textNode(str2));
        }
    }

    public void put(String str, BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            putNull(str);
        } else {
            _put(str, numberNode(bigDecimal));
        }
    }

    public void put(String str, boolean z) {
        _put(str, booleanNode(z));
    }

    public void put(String str, byte[] bArr) {
        if (bArr == null) {
            putNull(str);
        } else {
            _put(str, binaryNode(bArr));
        }
    }

    public JsonNode putAll(Map<String, JsonNode> map) {
        if (this._children == null) {
            this._children = new LinkedHashMap(map);
        } else {
            for (Entry entry : map.entrySet()) {
                Object obj = (JsonNode) entry.getValue();
                if (obj == null) {
                    obj = nullNode();
                }
                this._children.put(entry.getKey(), obj);
            }
        }
        return this;
    }

    public JsonNode putAll(ObjectNode objectNode) {
        int size = objectNode.size();
        if (size > 0) {
            if (this._children == null) {
                this._children = new LinkedHashMap(size);
            }
            objectNode.putContentsTo(this._children);
        }
        return this;
    }

    public ArrayNode putArray(String str) {
        JsonNode arrayNode = arrayNode();
        _put(str, arrayNode);
        return arrayNode;
    }

    protected void putContentsTo(Map<String, JsonNode> map) {
        if (this._children != null) {
            for (Entry entry : this._children.entrySet()) {
                map.put(entry.getKey(), entry.getValue());
            }
        }
    }

    public void putNull(String str) {
        _put(str, nullNode());
    }

    public ObjectNode putObject(String str) {
        JsonNode objectNode = objectNode();
        _put(str, objectNode);
        return objectNode;
    }

    public void putPOJO(String str, Object obj) {
        _put(str, POJONode(obj));
    }

    public JsonNode remove(String str) {
        return this._children != null ? (JsonNode) this._children.remove(str) : null;
    }

    public ObjectNode remove(Collection<String> collection) {
        if (this._children != null) {
            for (String remove : collection) {
                this._children.remove(remove);
            }
        }
        return this;
    }

    public ObjectNode removeAll() {
        this._children = null;
        return this;
    }

    public ObjectNode retain(Collection<String> collection) {
        if (this._children != null) {
            Iterator it = this._children.entrySet().iterator();
            while (it.hasNext()) {
                if (!collection.contains(((Entry) it.next()).getKey())) {
                    it.remove();
                }
            }
        }
        return this;
    }

    public ObjectNode retain(String... strArr) {
        return retain(Arrays.asList(strArr));
    }

    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeStartObject();
        if (this._children != null) {
            for (Entry entry : this._children.entrySet()) {
                jsonGenerator.writeFieldName((String) entry.getKey());
                ((BaseJsonNode) entry.getValue()).serialize(jsonGenerator, serializerProvider);
            }
        }
        jsonGenerator.writeEndObject();
    }

    public void serializeWithType(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
        typeSerializer.writeTypePrefixForObject(this, jsonGenerator);
        if (this._children != null) {
            for (Entry entry : this._children.entrySet()) {
                jsonGenerator.writeFieldName((String) entry.getKey());
                ((BaseJsonNode) entry.getValue()).serialize(jsonGenerator, serializerProvider);
            }
        }
        typeSerializer.writeTypeSuffixForObject(this, jsonGenerator);
    }

    public int size() {
        return this._children == null ? 0 : this._children.size();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder((size() << 4) + 32);
        stringBuilder.append("{");
        if (this._children != null) {
            int i = 0;
            for (Entry entry : this._children.entrySet()) {
                if (i > 0) {
                    stringBuilder.append(MiPushClient.ACCEPT_TIME_SEPARATOR);
                }
                int i2 = i + 1;
                TextNode.appendQuoted(stringBuilder, (String) entry.getKey());
                stringBuilder.append(':');
                stringBuilder.append(((JsonNode) entry.getValue()).toString());
                i = i2;
            }
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public ObjectNode with(String str) {
        if (this._children == null) {
            this._children = new LinkedHashMap();
        } else {
            JsonNode jsonNode = (JsonNode) this._children.get(str);
            if (jsonNode != null) {
                if (jsonNode instanceof ObjectNode) {
                    return (ObjectNode) jsonNode;
                }
                throw new UnsupportedOperationException("Property '" + str + "' has value that is not of type ObjectNode (but " + jsonNode.getClass().getName() + ")");
            }
        }
        ObjectNode objectNode = objectNode();
        this._children.put(str, objectNode);
        return objectNode;
    }
}
