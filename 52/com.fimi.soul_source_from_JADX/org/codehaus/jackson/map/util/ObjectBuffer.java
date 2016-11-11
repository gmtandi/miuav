package org.codehaus.jackson.map.util;

import java.lang.reflect.Array;
import java.util.List;

public final class ObjectBuffer {
    static final int INITIAL_CHUNK_SIZE = 12;
    static final int MAX_CHUNK_SIZE = 262144;
    static final int SMALL_CHUNK_SIZE = 16384;
    private Node _bufferHead;
    private Node _bufferTail;
    private int _bufferedEntryCount;
    private Object[] _freeBuffer;

    final class Node {
        final Object[] _data;
        Node _next;

        public Node(Object[] objArr) {
            this._data = objArr;
        }

        public Object[] getData() {
            return this._data;
        }

        public void linkNext(Node node) {
            if (this._next != null) {
                throw new IllegalStateException();
            }
            this._next = node;
        }

        public Node next() {
            return this._next;
        }
    }

    protected final void _copyTo(Object obj, int i, Object[] objArr, int i2) {
        int i3 = 0;
        for (Node node = this._bufferHead; node != null; node = node.next()) {
            Object data = node.getData();
            int length = data.length;
            System.arraycopy(data, 0, obj, i3, length);
            i3 += length;
        }
        System.arraycopy(objArr, 0, obj, i3, i2);
        int i4 = i3 + i2;
        if (i4 != i) {
            throw new IllegalStateException("Should have gotten " + i + " entries, got " + i4);
        }
    }

    protected void _reset() {
        if (this._bufferTail != null) {
            this._freeBuffer = this._bufferTail.getData();
        }
        this._bufferTail = null;
        this._bufferHead = null;
        this._bufferedEntryCount = 0;
    }

    public Object[] appendCompletedChunk(Object[] objArr) {
        Node node = new Node(objArr);
        if (this._bufferHead == null) {
            this._bufferTail = node;
            this._bufferHead = node;
        } else {
            this._bufferTail.linkNext(node);
            this._bufferTail = node;
        }
        int length = objArr.length;
        this._bufferedEntryCount += length;
        return new Object[(length < SMALL_CHUNK_SIZE ? length + length : length + (length >> 2))];
    }

    public int bufferedSize() {
        return this._bufferedEntryCount;
    }

    public void completeAndClearBuffer(Object[] objArr, int i, List<Object> list) {
        int i2 = 0;
        for (Node node = this._bufferHead; node != null; node = node.next()) {
            for (Object add : node.getData()) {
                list.add(add);
            }
        }
        while (i2 < i) {
            list.add(objArr[i2]);
            i2++;
        }
    }

    public Object[] completeAndClearBuffer(Object[] objArr, int i) {
        int i2 = this._bufferedEntryCount + i;
        Object obj = new Object[i2];
        _copyTo(obj, i2, objArr, i);
        return obj;
    }

    public <T> T[] completeAndClearBuffer(Object[] objArr, int i, Class<T> cls) {
        int i2 = i + this._bufferedEntryCount;
        Object[] objArr2 = (Object[]) Array.newInstance(cls, i2);
        _copyTo(objArr2, i2, objArr, i);
        _reset();
        return objArr2;
    }

    public int initialCapacity() {
        return this._freeBuffer == null ? 0 : this._freeBuffer.length;
    }

    public Object[] resetAndStart() {
        _reset();
        return this._freeBuffer == null ? new Object[INITIAL_CHUNK_SIZE] : this._freeBuffer;
    }
}
