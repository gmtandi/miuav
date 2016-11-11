package com.mob.tools.gui;

public class CachePool<K, V> {
    private int capacity;
    private Node<K, V> head;
    private int size;
    private Node<K, V> tail;

    class Node<K, V> {
        private long cacheTime;
        public K key;
        public Node<K, V> next;
        public Node<K, V> previous;
        public V value;

        private Node() {
        }
    }

    public CachePool(int i) {
        this.capacity = i;
    }

    public synchronized void clear() {
        this.tail = null;
        this.head = null;
        this.size = 0;
    }

    public synchronized V get(K k) {
        V v = null;
        synchronized (this) {
            Node node = this.head;
            while (node != null && !node.key.equals(k)) {
                node = node.next;
            }
            if (node != null) {
                if (node.previous != null) {
                    if (node.next == null) {
                        node.previous.next = null;
                        this.tail = this.tail.previous;
                    } else {
                        node.previous.next = node.next;
                        node.next.previous = node.previous;
                    }
                    node.previous = null;
                    node.next = this.head;
                    this.head.previous = node;
                    this.head = node;
                }
                v = node.value;
            }
        }
        return v;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean put(K r5, V r6) {
        /*
        r4 = this;
        r0 = 0;
        monitor-enter(r4);
        if (r5 == 0) goto L_0x0008;
    L_0x0004:
        r1 = r4.capacity;	 Catch:{ all -> 0x0025 }
        if (r1 > 0) goto L_0x000b;
    L_0x0008:
        r0 = 0;
    L_0x0009:
        monitor-exit(r4);
        return r0;
    L_0x000b:
        r1 = r4.size;	 Catch:{ all -> 0x0025 }
        r2 = r4.capacity;	 Catch:{ all -> 0x0025 }
        if (r1 < r2) goto L_0x0028;
    L_0x0011:
        r0 = r4.tail;	 Catch:{ all -> 0x0025 }
        r1 = r4.tail;	 Catch:{ all -> 0x0025 }
        r1 = r1.previous;	 Catch:{ all -> 0x0025 }
        r4.tail = r1;	 Catch:{ all -> 0x0025 }
        r1 = r4.tail;	 Catch:{ all -> 0x0025 }
        r2 = 0;
        r1.next = r2;	 Catch:{ all -> 0x0025 }
        r1 = r4.size;	 Catch:{ all -> 0x0025 }
        r1 = r1 + -1;
        r4.size = r1;	 Catch:{ all -> 0x0025 }
        goto L_0x000b;
    L_0x0025:
        r0 = move-exception;
        monitor-exit(r4);
        throw r0;
    L_0x0028:
        if (r0 != 0) goto L_0x0030;
    L_0x002a:
        r0 = new com.mob.tools.gui.CachePool$Node;	 Catch:{ all -> 0x0025 }
        r1 = 0;
        r0.<init>();	 Catch:{ all -> 0x0025 }
    L_0x0030:
        r2 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x0025 }
        r0.cacheTime = r2;	 Catch:{ all -> 0x0025 }
        r0.key = r5;	 Catch:{ all -> 0x0025 }
        r0.value = r6;	 Catch:{ all -> 0x0025 }
        r1 = 0;
        r0.previous = r1;	 Catch:{ all -> 0x0025 }
        r1 = r4.head;	 Catch:{ all -> 0x0025 }
        r0.next = r1;	 Catch:{ all -> 0x0025 }
        r1 = r4.size;	 Catch:{ all -> 0x0025 }
        if (r1 != 0) goto L_0x0052;
    L_0x0046:
        r4.tail = r0;	 Catch:{ all -> 0x0025 }
    L_0x0048:
        r4.head = r0;	 Catch:{ all -> 0x0025 }
        r0 = r4.size;	 Catch:{ all -> 0x0025 }
        r0 = r0 + 1;
        r4.size = r0;	 Catch:{ all -> 0x0025 }
        r0 = 1;
        goto L_0x0009;
    L_0x0052:
        r1 = r4.head;	 Catch:{ all -> 0x0025 }
        r1.previous = r0;	 Catch:{ all -> 0x0025 }
        goto L_0x0048;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.gui.CachePool.put(java.lang.Object, java.lang.Object):boolean");
    }

    public int size() {
        return this.size;
    }

    public synchronized void trimBeforeTime(long j) {
        if (this.capacity > 0) {
            for (Node node = this.head; node != null; node = node.next) {
                if (node.cacheTime < j) {
                    if (node.previous != null) {
                        node.previous.next = node.next;
                    }
                    if (node.next != null) {
                        node.next.previous = node.previous;
                    }
                    if (node.equals(this.head)) {
                        this.head = this.head.next;
                    }
                    this.size--;
                }
            }
        }
    }
}
