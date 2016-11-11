package com.amap.api.services.district;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.C0471d;
import com.amap.api.services.core.C0472e;
import com.amap.api.services.core.C0480l;
import com.amap.api.services.core.C0490p;
import java.util.HashMap;

public class DistrictSearch {
    private static HashMap<Integer, DistrictResult> f3198f;
    private Context f3199a;
    private DistrictSearchQuery f3200b;
    private OnDistrictSearchListener f3201c;
    private DistrictSearchQuery f3202d;
    private int f3203e;
    private Handler f3204g;

    /* renamed from: com.amap.api.services.district.DistrictSearch.1 */
    class C05011 extends Thread {
        final /* synthetic */ DistrictSearch f3197a;

        C05011(DistrictSearch districtSearch) {
            this.f3197a = districtSearch;
        }

        public void run() {
            Bundle bundle;
            Message obtainMessage = C0490p.m4850a().obtainMessage();
            Parcelable districtResult = new DistrictResult();
            districtResult.setQuery(this.f3197a.f3200b);
            try {
                districtResult = this.f3197a.m4917b();
                if (districtResult != null) {
                    districtResult.setAMapException(new AMapException());
                }
                obtainMessage.arg1 = 4;
                obtainMessage.obj = this.f3197a.f3201c;
                bundle = new Bundle();
                bundle.putParcelable("result", districtResult);
                obtainMessage.setData(bundle);
                if (this.f3197a.f3204g != null) {
                    this.f3197a.f3204g.sendMessage(obtainMessage);
                }
            } catch (Throwable e) {
                C0471d.m4762a(e, "DistrictSearch", "searchDistrictAnsy");
                districtResult.setAMapException(e);
                obtainMessage.arg1 = 4;
                obtainMessage.obj = this.f3197a.f3201c;
                bundle = new Bundle();
                bundle.putParcelable("result", districtResult);
                obtainMessage.setData(bundle);
                if (this.f3197a.f3204g != null) {
                    this.f3197a.f3204g.sendMessage(obtainMessage);
                }
            } catch (Throwable th) {
                obtainMessage.arg1 = 4;
                obtainMessage.obj = this.f3197a.f3201c;
                Bundle bundle2 = new Bundle();
                bundle2.putParcelable("result", districtResult);
                obtainMessage.setData(bundle2);
                if (this.f3197a.f3204g != null) {
                    this.f3197a.f3204g.sendMessage(obtainMessage);
                }
            }
        }
    }

    public interface OnDistrictSearchListener {
        void onDistrictSearched(DistrictResult districtResult);
    }

    public DistrictSearch(Context context) {
        this.f3199a = context.getApplicationContext();
        this.f3204g = C0490p.m4850a();
    }

    private void m4914a(DistrictResult districtResult) {
        f3198f = new HashMap();
        if (this.f3200b != null && districtResult != null && this.f3203e > 0 && this.f3203e > this.f3200b.getPageNum()) {
            f3198f.put(Integer.valueOf(this.f3200b.getPageNum()), districtResult);
        }
    }

    private boolean m4915a() {
        return this.f3200b != null;
    }

    private boolean m4916a(int i) {
        return i < this.f3203e && i >= 0;
    }

    private DistrictResult m4917b() {
        DistrictResult districtResult = new DistrictResult();
        C0480l.m4841a(this.f3199a);
        if (!m4915a()) {
            this.f3200b = new DistrictSearchQuery();
        }
        districtResult.setQuery(this.f3200b.clone());
        if (!this.f3200b.weakEquals(this.f3202d)) {
            this.f3203e = 0;
            this.f3202d = this.f3200b.clone();
            if (f3198f != null) {
                f3198f.clear();
            }
        }
        if (this.f3203e == 0) {
            districtResult = (DistrictResult) new C0472e(this.f3199a, this.f3200b.clone()).m4458g();
            if (districtResult != null) {
                this.f3203e = districtResult.getPageCount();
                m4914a(districtResult);
            }
        } else {
            districtResult = getPageLocal(this.f3200b.getPageNum());
            if (districtResult == null) {
                districtResult = (DistrictResult) new C0472e(this.f3199a, this.f3200b.clone()).m4458g();
                if (this.f3200b != null && districtResult != null && this.f3203e > 0 && this.f3203e > this.f3200b.getPageNum()) {
                    f3198f.put(Integer.valueOf(this.f3200b.getPageNum()), districtResult);
                }
            }
        }
        return districtResult;
    }

    protected DistrictResult getPageLocal(int i) {
        if (m4916a(i)) {
            return (DistrictResult) f3198f.get(Integer.valueOf(i));
        }
        throw new AMapException(AMapException.ERROR_INVALID_PARAMETER);
    }

    public DistrictSearchQuery getQuery() {
        return this.f3200b;
    }

    public void searchDistrictAnsy() {
        new C05011(this).start();
    }

    public void setOnDistrictSearchListener(OnDistrictSearchListener onDistrictSearchListener) {
        this.f3201c = onDistrictSearchListener;
    }

    public void setQuery(DistrictSearchQuery districtSearchQuery) {
        this.f3200b = districtSearchQuery;
    }
}
