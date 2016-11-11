package org.codehaus.jackson.map.util;

public final class LinkedNode<T> {
    final LinkedNode<T> _next;
    final T _value;

    public LinkedNode(T t, LinkedNode<T> linkedNode) {
        this._value = t;
        this._next = linkedNode;
    }

    public static <ST> boolean contains(LinkedNode<ST> linkedNode, ST st) {
        LinkedNode next;
        while (next != null) {
            if (next.value() == st) {
                return true;
            }
            next = next.next();
        }
        return false;
    }

    public LinkedNode<T> next() {
        return this._next;
    }

    public T value() {
        return this._value;
    }
}
