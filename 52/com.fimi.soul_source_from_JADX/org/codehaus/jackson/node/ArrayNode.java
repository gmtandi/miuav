package org.codehaus.jackson.node;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.TypeSerializer;
import org.codehaus.jackson.node.ContainerNode.NoNodesIterator;

public final class ArrayNode extends ContainerNode {
    protected ArrayList<JsonNode> _children;

    public ArrayNode(JsonNodeFactory jsonNodeFactory) {
        super(jsonNodeFactory);
    }

    private void _add(JsonNode jsonNode) {
        if (this._children == null) {
            this._children = new ArrayList();
        }
        this._children.add(jsonNode);
    }

    private void _insert(int i, JsonNode jsonNode) {
        if (this._children == null) {
            this._children = new ArrayList();
            this._children.add(jsonNode);
        } else if (i < 0) {
            this._children.add(0, jsonNode);
        } else if (i >= this._children.size()) {
            this._children.add(jsonNode);
        } else {
            this._children.add(i, jsonNode);
        }
    }

    private boolean _sameChildren(ArrayList<JsonNode> arrayList) {
        int size = arrayList.size();
        if (size() != size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!((JsonNode) this._children.get(i)).equals(arrayList.get(i))) {
                return false;
            }
        }
        return true;
    }

    public JsonNode _set(int i, JsonNode jsonNode) {
        if (this._children != null && i >= 0 && i < this._children.size()) {
            return (JsonNode) this._children.set(i, jsonNode);
        }
        throw new IndexOutOfBoundsException("Illegal index " + i + ", array size " + size());
    }

    public void add(double d) {
        _add(numberNode(d));
    }

    public void add(float f) {
        _add(numberNode(f));
    }

    public void add(int i) {
        _add(numberNode(i));
    }

    public void add(long j) {
        _add(numberNode(j));
    }

    public void add(String str) {
        if (str == null) {
            addNull();
        } else {
            _add(textNode(str));
        }
    }

    public void add(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            addNull();
        } else {
            _add(numberNode(bigDecimal));
        }
    }

    public void add(JsonNode jsonNode) {
        if (jsonNode == null) {
            jsonNode = nullNode();
        }
        _add(jsonNode);
    }

    public void add(boolean z) {
        _add(booleanNode(z));
    }

    public void add(byte[] bArr) {
        if (bArr == null) {
            addNull();
        } else {
            _add(binaryNode(bArr));
        }
    }

    public JsonNode addAll(Collection<JsonNode> collection) {
        if (collection.size() > 0) {
            if (this._children == null) {
                this._children = new ArrayList(collection);
            } else {
                this._children.addAll(collection);
            }
        }
        return this;
    }

    public JsonNode addAll(ArrayNode arrayNode) {
        int size = arrayNode.size();
        if (size > 0) {
            if (this._children == null) {
                this._children = new ArrayList(size + 2);
            }
            arrayNode.addContentsTo(this._children);
        }
        return this;
    }

    public ArrayNode addArray() {
        JsonNode arrayNode = arrayNode();
        _add(arrayNode);
        return arrayNode;
    }

    protected void addContentsTo(List<JsonNode> list) {
        if (this._children != null) {
            Iterator it = this._children.iterator();
            while (it.hasNext()) {
                list.add((JsonNode) it.next());
            }
        }
    }

    public void addNull() {
        _add(nullNode());
    }

    public ObjectNode addObject() {
        JsonNode objectNode = objectNode();
        _add(objectNode);
        return objectNode;
    }

    public void addPOJO(Object obj) {
        if (obj == null) {
            addNull();
        } else {
            _add(POJONode(obj));
        }
    }

    public JsonToken asToken() {
        return JsonToken.START_ARRAY;
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
        ArrayNode arrayNode = (ArrayNode) obj;
        return (this._children == null || this._children.size() == 0) ? arrayNode.size() == 0 : arrayNode._sameChildren(this._children);
    }

    public ObjectNode findParent(String str) {
        if (this._children != null) {
            Iterator it = this._children.iterator();
            while (it.hasNext()) {
                JsonNode findParent = ((JsonNode) it.next()).findParent(str);
                if (findParent != null) {
                    return (ObjectNode) findParent;
                }
            }
        }
        return null;
    }

    public List<JsonNode> findParents(String str, List<JsonNode> list) {
        if (this._children != null) {
            Iterator it = this._children.iterator();
            while (it.hasNext()) {
                list = ((JsonNode) it.next()).findParents(str, list);
            }
        }
        return list;
    }

    public JsonNode findValue(String str) {
        if (this._children != null) {
            Iterator it = this._children.iterator();
            while (it.hasNext()) {
                JsonNode findValue = ((JsonNode) it.next()).findValue(str);
                if (findValue != null) {
                    return findValue;
                }
            }
        }
        return null;
    }

    public List<JsonNode> findValues(String str, List<JsonNode> list) {
        if (this._children != null) {
            Iterator it = this._children.iterator();
            while (it.hasNext()) {
                list = ((JsonNode) it.next()).findValues(str, list);
            }
        }
        return list;
    }

    public List<String> findValuesAsText(String str, List<String> list) {
        if (this._children != null) {
            Iterator it = this._children.iterator();
            while (it.hasNext()) {
                list = ((JsonNode) it.next()).findValuesAsText(str, list);
            }
        }
        return list;
    }

    public JsonNode get(int i) {
        return (i < 0 || this._children == null || i >= this._children.size()) ? null : (JsonNode) this._children.get(i);
    }

    public JsonNode get(String str) {
        return null;
    }

    public Iterator<JsonNode> getElements() {
        return this._children == null ? NoNodesIterator.instance() : this._children.iterator();
    }

    public int hashCode() {
        if (this._children == null) {
            return 1;
        }
        int size = this._children.size();
        Iterator it = this._children.iterator();
        int i = size;
        while (it.hasNext()) {
            JsonNode jsonNode = (JsonNode) it.next();
            i = jsonNode != null ? jsonNode.hashCode() ^ i : i;
        }
        return i;
    }

    public void insert(int i, double d) {
        _insert(i, numberNode(d));
    }

    public void insert(int i, float f) {
        _insert(i, numberNode(f));
    }

    public void insert(int i, int i2) {
        _insert(i, numberNode(i2));
    }

    public void insert(int i, long j) {
        _insert(i, numberNode(j));
    }

    public void insert(int i, String str) {
        if (str == null) {
            insertNull(i);
        } else {
            _insert(i, textNode(str));
        }
    }

    public void insert(int i, BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            insertNull(i);
        } else {
            _insert(i, numberNode(bigDecimal));
        }
    }

    public void insert(int i, JsonNode jsonNode) {
        if (jsonNode == null) {
            jsonNode = nullNode();
        }
        _insert(i, jsonNode);
    }

    public void insert(int i, boolean z) {
        _insert(i, booleanNode(z));
    }

    public void insert(int i, byte[] bArr) {
        if (bArr == null) {
            insertNull(i);
        } else {
            _insert(i, binaryNode(bArr));
        }
    }

    public ArrayNode insertArray(int i) {
        JsonNode arrayNode = arrayNode();
        _insert(i, arrayNode);
        return arrayNode;
    }

    public void insertNull(int i) {
        _insert(i, nullNode());
    }

    public ObjectNode insertObject(int i) {
        JsonNode objectNode = objectNode();
        _insert(i, objectNode);
        return objectNode;
    }

    public void insertPOJO(int i, Object obj) {
        if (obj == null) {
            insertNull(i);
        } else {
            _insert(i, POJONode(obj));
        }
    }

    public boolean isArray() {
        return true;
    }

    public JsonNode path(int i) {
        return (i < 0 || this._children == null || i >= this._children.size()) ? MissingNode.getInstance() : (JsonNode) this._children.get(i);
    }

    public JsonNode path(String str) {
        return MissingNode.getInstance();
    }

    public JsonNode remove(int i) {
        return (i < 0 || this._children == null || i >= this._children.size()) ? null : (JsonNode) this._children.remove(i);
    }

    public ArrayNode removeAll() {
        this._children = null;
        return this;
    }

    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeStartArray();
        if (this._children != null) {
            Iterator it = this._children.iterator();
            while (it.hasNext()) {
                ((BaseJsonNode) ((JsonNode) it.next())).writeTo(jsonGenerator);
            }
        }
        jsonGenerator.writeEndArray();
    }

    public void serializeWithType(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
        typeSerializer.writeTypePrefixForArray(this, jsonGenerator);
        if (this._children != null) {
            Iterator it = this._children.iterator();
            while (it.hasNext()) {
                ((BaseJsonNode) ((JsonNode) it.next())).writeTo(jsonGenerator);
            }
        }
        typeSerializer.writeTypeSuffixForArray(this, jsonGenerator);
    }

    public JsonNode set(int i, JsonNode jsonNode) {
        if (jsonNode == null) {
            jsonNode = nullNode();
        }
        return _set(i, jsonNode);
    }

    public int size() {
        return this._children == null ? 0 : this._children.size();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder((size() << 4) + 16);
        stringBuilder.append('[');
        if (this._children != null) {
            int size = this._children.size();
            for (int i = 0; i < size; i++) {
                if (i > 0) {
                    stringBuilder.append(',');
                }
                stringBuilder.append(((JsonNode) this._children.get(i)).toString());
            }
        }
        stringBuilder.append(']');
        return stringBuilder.toString();
    }
}
