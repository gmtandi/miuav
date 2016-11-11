package com.autonavi.amap.mapcore;

import com.amap.api.mapcore.util.bq;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConnectionManager extends SingalThread {
    private static final int MAX_THREAD_COUNT = 1;
    private ArrayList<BaseMapLoader> connPool;
    MapCore mGLMapEngine;
    boolean threadFlag;
    private ExecutorService threadPool;
    private ArrayList<C0609a> threadPoolList;

    public ConnectionManager(MapCore mapCore) {
        this.threadFlag = true;
        this.threadPool = Executors.newFixedThreadPool(MAX_THREAD_COUNT);
        this.threadPoolList = new ArrayList();
        this.connPool = new ArrayList();
        this.mGLMapEngine = mapCore;
    }

    private void checkListPool() {
        Collection arrayList = new ArrayList();
        int size = this.threadPoolList.size();
        for (int i = 0; i < size; i += MAX_THREAD_COUNT) {
            C0609a c0609a = (C0609a) this.threadPoolList.get(i);
            BaseMapLoader baseMapLoader = c0609a.f3716a;
            if (!baseMapLoader.isRequestValid() || baseMapLoader.hasFinished()) {
                arrayList.add(c0609a);
                baseMapLoader.doCancel();
            }
        }
        this.threadPoolList.removeAll(arrayList);
    }

    private void doAsyncRequest() {
        while (this.threadFlag) {
            Object obj;
            synchronized (this.connPool) {
                checkListPool();
                while (this.connPool.size() > 0) {
                    if (this.threadPoolList.size() > MAX_THREAD_COUNT) {
                        obj = MAX_THREAD_COUNT;
                        break;
                    }
                    Runnable c0609a = new C0609a((BaseMapLoader) this.connPool.remove(0));
                    this.threadPoolList.add(c0609a);
                    if (!this.threadPool.isShutdown()) {
                        this.threadPool.execute(c0609a);
                    }
                }
                obj = null;
            }
            if (obj != null) {
                try {
                    sleep(30);
                } catch (Exception e) {
                }
            } else if (this.threadFlag) {
                try {
                    doWait();
                } catch (Throwable th) {
                }
            }
        }
    }

    void checkListPoolOld() {
        Iterator it = this.threadPoolList.iterator();
        while (it.hasNext()) {
            BaseMapLoader baseMapLoader = ((C0609a) it.next()).f3716a;
            if (!baseMapLoader.isRequestValid() || baseMapLoader.hasFinished()) {
                baseMapLoader.doCancel();
                it.remove();
            }
        }
    }

    public void insertConntionTask(BaseMapLoader baseMapLoader) {
        synchronized (this.connPool) {
            this.connPool.add(baseMapLoader);
        }
        doAwake();
    }

    public void run() {
        try {
            bq.m3688a();
            doAsyncRequest();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void shutDown() {
        if (this.connPool != null) {
            this.threadPool.shutdownNow();
        }
    }
}
