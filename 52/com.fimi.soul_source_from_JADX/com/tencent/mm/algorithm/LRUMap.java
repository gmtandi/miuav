package com.tencent.mm.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class LRUMap<K, O> {
    private Map<K, TimeVal<O>> f11749c;
    private int f11750d;
    private int f11751e;
    private PreRemoveCallback<K, O> f11752f;

    /* renamed from: com.tencent.mm.algorithm.LRUMap.1 */
    class C22671 implements Comparator<Entry<K, TimeVal<O>>> {
        final /* synthetic */ LRUMap f11746g;

        C22671(LRUMap lRUMap) {
            this.f11746g = lRUMap;
        }

        public int compare(Entry<K, TimeVal<O>> entry, Entry<K, TimeVal<O>> entry2) {
            return ((TimeVal) entry.getValue()).f11748t.compareTo(((TimeVal) entry2.getValue()).f11748t);
        }
    }

    public interface OnClearListener<K, O> {
        void onClear(K k, O o);
    }

    public interface PreRemoveCallback<K, O> {
        void preRemoveCallback(K k, O o);
    }

    public class TimeVal<OO> {
        final /* synthetic */ LRUMap f11747g;
        public OO obj;
        public Long f11748t;

        public TimeVal(LRUMap lRUMap, OO oo) {
            this.f11747g = lRUMap;
            this.obj = oo;
            UpTime();
        }

        public void UpTime() {
            this.f11748t = Long.valueOf(System.currentTimeMillis());
        }
    }

    public LRUMap(int i) {
        this(i, null);
    }

    public LRUMap(int i, PreRemoveCallback<K, O> preRemoveCallback) {
        this.f11749c = null;
        this.f11752f = null;
        this.f11750d = i;
        this.f11751e = 0;
        this.f11752f = preRemoveCallback;
        this.f11749c = new HashMap();
    }

    public boolean check(K k) {
        return this.f11749c.containsKey(k);
    }

    public boolean checkAndUpTime(K k) {
        if (!this.f11749c.containsKey(k)) {
            return false;
        }
        ((TimeVal) this.f11749c.get(k)).UpTime();
        return true;
    }

    public void clear() {
        this.f11749c.clear();
    }

    public void clear(OnClearListener<K, O> onClearListener) {
        if (this.f11749c != null) {
            if (onClearListener != null) {
                for (Entry entry : this.f11749c.entrySet()) {
                    onClearListener.onClear(entry.getKey(), ((TimeVal) entry.getValue()).obj);
                }
            }
            this.f11749c.clear();
        }
    }

    public O get(K k) {
        TimeVal timeVal = (TimeVal) this.f11749c.get(k);
        return timeVal == null ? null : timeVal.obj;
    }

    public O getAndUptime(K k) {
        TimeVal timeVal = (TimeVal) this.f11749c.get(k);
        if (timeVal == null) {
            return null;
        }
        ((TimeVal) this.f11749c.get(k)).UpTime();
        return timeVal.obj;
    }

    public void remove(K k) {
        if (this.f11749c.containsKey(k)) {
            if (this.f11752f != null) {
                this.f11752f.preRemoveCallback(k, ((TimeVal) this.f11749c.get(k)).obj);
            }
            this.f11749c.remove(k);
        }
    }

    public void setMaxSize(int i) {
        if (i > 0) {
            this.f11750d = i;
        }
    }

    public void setPerDeleteSize(int i) {
        if (i > 0) {
            this.f11751e = i;
        }
    }

    public int size() {
        return this.f11749c.size();
    }

    public void update(K k, O o) {
        if (((TimeVal) this.f11749c.get(k)) == null) {
            this.f11749c.put(k, new TimeVal(this, o));
            if (this.f11749c.size() > this.f11750d) {
                int i;
                Object arrayList = new ArrayList(this.f11749c.entrySet());
                Collections.sort(arrayList, new C22671(this));
                if (this.f11751e <= 0) {
                    i = this.f11750d / 10;
                    if (i <= 0) {
                        i = 1;
                    }
                } else {
                    i = this.f11751e;
                }
                Iterator it = arrayList.iterator();
                int i2 = i;
                while (it.hasNext()) {
                    remove(((Entry) it.next()).getKey());
                    i = i2 - 1;
                    if (i > 0) {
                        i2 = i;
                    } else {
                        return;
                    }
                }
                return;
            }
            return;
        }
        ((TimeVal) this.f11749c.get(k)).UpTime();
        ((TimeVal) this.f11749c.get(k)).obj = o;
    }
}
