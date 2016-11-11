package com.amap.api.services.busline;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.amap.api.services.core.C0456b;
import com.amap.api.services.core.C0471d;
import com.amap.api.services.core.C0480l;
import com.amap.api.services.core.C0490p;
import com.amap.api.services.core.C0490p.C0484a;
import java.util.ArrayList;

public class BusLineSearch {
    private Context f2895a;
    private OnBusLineSearchListener f2896b;
    private BusLineQuery f2897c;
    private BusLineQuery f2898d;
    private int f2899e;
    private ArrayList<BusLineResult> f2900f;
    private Handler f2901g;

    /* renamed from: com.amap.api.services.busline.BusLineSearch.1 */
    class C04451 implements Runnable {
        final /* synthetic */ BusLineSearch f2894a;

        C04451(BusLineSearch busLineSearch) {
            this.f2894a = busLineSearch;
        }

        public void run() {
            Message obtainMessage = C0490p.m4850a().obtainMessage();
            try {
                BusLineResult searchBusLine = this.f2894a.searchBusLine();
                obtainMessage.arg1 = 3;
                obtainMessage.what = 0;
                C0484a c0484a = new C0484a();
                c0484a.f3154a = searchBusLine;
                c0484a.f3155b = this.f2894a.f2896b;
                obtainMessage.obj = c0484a;
            } catch (Throwable e) {
                C0471d.m4762a(e, "BusLineSearch", "searchBusLineAsyn");
                obtainMessage.what = e.getErrorCode();
            } finally {
                this.f2894a.f2901g.sendMessage(obtainMessage);
            }
        }
    }

    public interface OnBusLineSearchListener {
        void onBusLineSearched(BusLineResult busLineResult, int i);
    }

    public BusLineSearch(Context context, BusLineQuery busLineQuery) {
        this.f2900f = new ArrayList();
        this.f2901g = null;
        this.f2895a = context.getApplicationContext();
        this.f2897c = busLineQuery;
        this.f2898d = busLineQuery.clone();
        this.f2901g = C0490p.m4850a();
    }

    private void m4426a(BusLineResult busLineResult) {
        this.f2900f = new ArrayList();
        for (int i = 0; i < this.f2899e; i++) {
            this.f2900f.add(null);
        }
        if (this.f2899e >= 0 && m4427a(this.f2897c.getPageNumber())) {
            this.f2900f.set(this.f2897c.getPageNumber(), busLineResult);
        }
    }

    private boolean m4427a(int i) {
        return i < this.f2899e && i >= 0;
    }

    private BusLineResult m4429b(int i) {
        if (m4427a(i)) {
            return (BusLineResult) this.f2900f.get(i);
        }
        throw new IllegalArgumentException("page out of range");
    }

    public BusLineQuery getQuery() {
        return this.f2897c;
    }

    public BusLineResult searchBusLine() {
        C0480l.m4841a(this.f2895a);
        if (!this.f2897c.weakEquals(this.f2898d)) {
            this.f2898d = this.f2897c.clone();
            this.f2899e = 0;
            if (this.f2900f != null) {
                this.f2900f.clear();
            }
        }
        if (this.f2899e == 0) {
            C0456b c0456b = new C0456b(this.f2895a, this.f2897c.clone());
            BusLineResult a = BusLineResult.m4424a(c0456b, (ArrayList) c0456b.m4458g());
            this.f2899e = a.getPageCount();
            m4426a(a);
            return a;
        }
        a = m4429b(this.f2897c.getPageNumber());
        if (a != null) {
            return a;
        }
        c0456b = new C0456b(this.f2895a, this.f2897c);
        a = BusLineResult.m4424a(c0456b, (ArrayList) c0456b.m4458g());
        this.f2900f.set(this.f2897c.getPageNumber(), a);
        return a;
    }

    public void searchBusLineAsyn() {
        new Thread(new C04451(this)).start();
    }

    public void setOnBusLineSearchListener(OnBusLineSearchListener onBusLineSearchListener) {
        this.f2896b = onBusLineSearchListener;
    }

    public void setQuery(BusLineQuery busLineQuery) {
        if (!this.f2897c.weakEquals(busLineQuery)) {
            this.f2897c = busLineQuery;
            this.f2898d = busLineQuery.clone();
        }
    }
}
