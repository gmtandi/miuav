package com.tencent.mm.sdk.storage;

import android.os.Looper;

public abstract class MStorage {
    private final MStorageEvent<IOnStorageChange, String> bM;
    private final MStorageEvent<IOnStorageLoaded, String> bN;

    /* renamed from: com.tencent.mm.sdk.storage.MStorage.1 */
    class C22781 extends MStorageEvent<IOnStorageChange, String> {
        final /* synthetic */ MStorage bO;

        C22781(MStorage mStorage) {
            this.bO = mStorage;
        }

        protected /* synthetic */ void processEvent(Object obj, Object obj2) {
            IOnStorageChange iOnStorageChange = (IOnStorageChange) obj;
            String str = (String) obj2;
            MStorage mStorage = this.bO;
            iOnStorageChange.onNotifyChange(str);
        }
    }

    /* renamed from: com.tencent.mm.sdk.storage.MStorage.2 */
    class C22792 extends MStorageEvent<IOnStorageLoaded, String> {
        final /* synthetic */ MStorage bO;

        C22792(MStorage mStorage) {
            this.bO = mStorage;
        }

        protected /* synthetic */ void processEvent(Object obj, Object obj2) {
            IOnStorageLoaded iOnStorageLoaded = (IOnStorageLoaded) obj;
            MStorage mStorage = this.bO;
            iOnStorageLoaded.onNotifyLoaded();
        }
    }

    public interface IOnStorageChange {
        void onNotifyChange(String str);
    }

    public interface IOnStorageLoaded {
        void onNotifyLoaded();
    }

    public MStorage() {
        this.bM = new C22781(this);
        this.bN = new C22792(this);
    }

    public void add(IOnStorageChange iOnStorageChange) {
        this.bM.add(iOnStorageChange, Looper.getMainLooper());
    }

    public void addLoadedListener(IOnStorageLoaded iOnStorageLoaded) {
        this.bN.add(iOnStorageLoaded, Looper.getMainLooper());
    }

    public void doNotify() {
        this.bM.event("*");
        this.bM.doNotify();
    }

    public void doNotify(String str) {
        this.bM.event(str);
        this.bM.doNotify();
    }

    public void lock() {
        this.bM.lock();
    }

    public void remove(IOnStorageChange iOnStorageChange) {
        this.bM.remove(iOnStorageChange);
    }

    public void removeLoadedListener(IOnStorageLoaded iOnStorageLoaded) {
        this.bN.remove(iOnStorageLoaded);
    }

    public void unlock() {
        this.bM.unlock();
    }
}
