package com.tencent.mm.sdk.storage;

import android.os.Handler;
import android.os.Looper;
import com.tencent.mm.sdk.platformtools.Log;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public abstract class MStorageEvent<T, E> {
    private int bP;
    private final Hashtable<T, Object> bQ;
    private final CopyOnWriteArraySet<E> bR;

    /* renamed from: com.tencent.mm.sdk.storage.MStorageEvent.1 */
    class C22801 implements Runnable {
        final /* synthetic */ Object bS;
        final /* synthetic */ Object bT;
        final /* synthetic */ MStorageEvent bU;

        C22801(MStorageEvent mStorageEvent, Object obj, Object obj2) {
            this.bU = mStorageEvent;
            this.bS = obj;
            this.bT = obj2;
        }

        public void run() {
            this.bU.processEvent(this.bS, this.bT);
        }
    }

    public MStorageEvent() {
        this.bP = 0;
        this.bQ = new Hashtable();
        this.bR = new CopyOnWriteArraySet();
    }

    private void m13569e() {
        Set hashSet = new HashSet(this.bQ.keySet());
        if (hashSet.size() > 0) {
            Map hashMap = new HashMap();
            for (Object next : hashSet) {
                Object obj = this.bQ.get(next);
                Iterator it = this.bR.iterator();
                while (it.hasNext()) {
                    Object next2 = it.next();
                    if (next2 != null) {
                        if (obj == null) {
                            Log.m13543f("MicroMsg.SDK.MStorageEvent", "handle listener fatal unknown bug");
                        } else if (obj instanceof Looper) {
                            Looper looper = (Looper) obj;
                            Handler handler = (Handler) hashMap.get(looper);
                            if (handler == null) {
                                handler = new Handler(looper);
                                hashMap.put(looper, handler);
                            }
                            handler.post(new C22801(this, next, next2));
                        } else {
                            processEvent(next, next2);
                        }
                    }
                }
            }
            this.bR.clear();
        }
    }

    public void add(T t, Looper looper) {
        if (!this.bQ.containsKey(t)) {
            if (looper != null) {
                this.bQ.put(t, looper);
            } else {
                this.bQ.put(t, new Object());
            }
        }
    }

    public void doNotify() {
        if (!isLocked()) {
            m13569e();
        }
    }

    public boolean event(E e) {
        return this.bR.add(e);
    }

    public boolean isLocked() {
        return this.bP > 0;
    }

    public void lock() {
        this.bP++;
    }

    protected abstract void processEvent(T t, E e);

    public void remove(T t) {
        this.bQ.remove(t);
    }

    public void removeAll() {
        this.bQ.clear();
    }

    public void unlock() {
        this.bP--;
        if (this.bP <= 0) {
            this.bP = 0;
            m13569e();
        }
    }
}
