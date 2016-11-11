package com.amap.api.services.busline;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.amap.api.services.core.C0456b;
import com.amap.api.services.core.C0471d;
import com.amap.api.services.core.C0480l;
import com.amap.api.services.core.C0490p;
import com.amap.api.services.core.C0490p.C0485b;
import java.util.ArrayList;

public class BusStationSearch {
    private Context f2918a;
    private OnBusStationSearchListener f2919b;
    private BusStationQuery f2920c;
    private BusStationQuery f2921d;
    private ArrayList<BusStationResult> f2922e;
    private int f2923f;
    private Handler f2924g;

    /* renamed from: com.amap.api.services.busline.BusStationSearch.1 */
    class C04461 implements Runnable {
        final /* synthetic */ BusStationSearch f2917a;

        C04461(BusStationSearch busStationSearch) {
            this.f2917a = busStationSearch;
        }

        public void run() {
            Message obtainMessage = C0490p.m4850a().obtainMessage();
            try {
                BusStationResult searchBusStation = this.f2917a.searchBusStation();
                obtainMessage.arg1 = 7;
                obtainMessage.what = 0;
                C0485b c0485b = new C0485b();
                c0485b.f3156a = searchBusStation;
                c0485b.f3157b = this.f2917a.f2919b;
                obtainMessage.obj = c0485b;
            } catch (Throwable e) {
                C0471d.m4762a(e, "BusStationSearch", "searchBusStationAsyn");
                obtainMessage.what = e.getErrorCode();
            } finally {
                this.f2917a.f2924g.sendMessage(obtainMessage);
            }
        }
    }

    public interface OnBusStationSearchListener {
        void onBusStationSearched(BusStationResult busStationResult, int i);
    }

    public BusStationSearch(Context context, BusStationQuery busStationQuery) {
        this.f2922e = new ArrayList();
        this.f2918a = context.getApplicationContext();
        this.f2920c = busStationQuery;
        this.f2924g = C0490p.m4850a();
    }

    private void m4435a(BusStationResult busStationResult) {
        this.f2922e = new ArrayList();
        for (int i = 0; i <= this.f2923f; i++) {
            this.f2922e.add(null);
        }
        if (this.f2923f > 0) {
            this.f2922e.set(this.f2920c.getPageNumber(), busStationResult);
        }
    }

    private boolean m4436a(int i) {
        return i <= this.f2923f && i >= 0;
    }

    private BusStationResult m4438b(int i) {
        if (m4436a(i)) {
            return (BusStationResult) this.f2922e.get(i);
        }
        throw new IllegalArgumentException("page out of range");
    }

    public BusStationQuery getQuery() {
        return this.f2920c;
    }

    public BusStationResult searchBusStation() {
        C0480l.m4841a(this.f2918a);
        if (!this.f2920c.weakEquals(this.f2921d)) {
            this.f2921d = this.f2920c.clone();
            this.f2923f = 0;
            if (this.f2922e != null) {
                this.f2922e.clear();
            }
        }
        if (this.f2923f == 0) {
            C0456b c0456b = new C0456b(this.f2918a, this.f2920c);
            BusStationResult a = BusStationResult.m4433a(c0456b, (ArrayList) c0456b.m4458g());
            this.f2923f = a.getPageCount();
            m4435a(a);
            return a;
        }
        a = m4438b(this.f2920c.getPageNumber());
        if (a != null) {
            return a;
        }
        c0456b = new C0456b(this.f2918a, this.f2920c);
        a = BusStationResult.m4433a(c0456b, (ArrayList) c0456b.m4458g());
        this.f2922e.set(this.f2920c.getPageNumber(), a);
        return a;
    }

    public void searchBusStationAsyn() {
        new Thread(new C04461(this)).start();
    }

    public void setOnBusStationSearchListener(OnBusStationSearchListener onBusStationSearchListener) {
        this.f2919b = onBusStationSearchListener;
    }

    public void setQuery(BusStationQuery busStationQuery) {
        if (!busStationQuery.weakEquals(this.f2920c)) {
            this.f2920c = busStationQuery;
        }
    }
}
