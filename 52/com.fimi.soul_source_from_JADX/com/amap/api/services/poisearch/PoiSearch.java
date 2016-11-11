package com.amap.api.services.poisearch;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.C0471d;
import com.amap.api.services.core.C0480l;
import com.amap.api.services.core.C0490p;
import com.amap.api.services.core.C0490p.C0487d;
import com.amap.api.services.core.C0490p.C0488e;
import com.amap.api.services.core.C0492s;
import com.amap.api.services.core.LatLonPoint;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.p122a.p123a.C2915a;

public class PoiSearch {
    public static final String CHINESE = "zh-CN";
    public static final String ENGLISH = "en";
    private static HashMap<Integer, PoiResult> f3367i;
    private SearchBound f3368a;
    private Query f3369b;
    private Context f3370c;
    private OnPoiSearchListener f3371d;
    private String f3372e;
    private Query f3373f;
    private SearchBound f3374g;
    private int f3375h;
    private Handler f3376j;

    /* renamed from: com.amap.api.services.poisearch.PoiSearch.1 */
    class C05141 extends Thread {
        final /* synthetic */ PoiSearch f3349a;

        C05141(PoiSearch poiSearch) {
            this.f3349a = poiSearch;
        }

        public void run() {
            Message obtainMessage = this.f3349a.f3376j.obtainMessage();
            obtainMessage.arg1 = 6;
            obtainMessage.what = 60;
            Bundle bundle = new Bundle();
            PoiResult poiResult = null;
            try {
                poiResult = this.f3349a.searchPOI();
                bundle.putInt("errorCode", 0);
            } catch (Throwable e) {
                C0471d.m4762a(e, "PoiSearch", "searchPOIAsyn");
                bundle.putInt("errorCode", e.getErrorCode());
            } finally {
                C0488e c0488e = new C0488e();
                c0488e.f3163b = this.f3349a.f3371d;
                c0488e.f3162a = poiResult;
                obtainMessage.obj = c0488e;
                obtainMessage.setData(bundle);
                this.f3349a.f3376j.sendMessage(obtainMessage);
            }
        }
    }

    /* renamed from: com.amap.api.services.poisearch.PoiSearch.2 */
    class C05152 extends Thread {
        final /* synthetic */ String f3350a;
        final /* synthetic */ PoiSearch f3351b;

        C05152(PoiSearch poiSearch, String str) {
            this.f3351b = poiSearch;
            this.f3350a = str;
        }

        public void run() {
            Message obtainMessage = C0490p.m4850a().obtainMessage();
            obtainMessage.arg1 = 6;
            obtainMessage.what = 61;
            Bundle bundle = new Bundle();
            PoiItemDetail poiItemDetail = null;
            try {
                poiItemDetail = this.f3351b.searchPOIDetail(this.f3350a);
                bundle.putInt("errorCode", 0);
            } catch (Throwable e) {
                C0471d.m4762a(e, "PoiSearch", "searchPOIDetailAsyn");
                bundle.putInt("errorCode", e.getErrorCode());
            } finally {
                C0487d c0487d = new C0487d();
                c0487d.f3161b = this.f3351b.f3371d;
                c0487d.f3160a = poiItemDetail;
                obtainMessage.obj = c0487d;
                obtainMessage.setData(bundle);
                this.f3351b.f3376j.sendMessage(obtainMessage);
            }
        }
    }

    public interface OnPoiSearchListener {
        void onPoiItemDetailSearched(PoiItemDetail poiItemDetail, int i);

        void onPoiSearched(PoiResult poiResult, int i);
    }

    public class Query implements Cloneable {
        private String f3352a;
        private String f3353b;
        private String f3354c;
        private int f3355d;
        private int f3356e;
        private boolean f3357f;
        private boolean f3358g;
        private String f3359h;

        public Query(String str, String str2) {
            this(str, str2, null);
        }

        public Query(String str, String str2, String str3) {
            this.f3355d = 0;
            this.f3356e = 20;
            this.f3359h = PoiSearch.CHINESE;
            this.f3352a = str;
            this.f3353b = str2;
            this.f3354c = str3;
        }

        private String m4946a() {
            return C2915a.f14760f;
        }

        public Query clone() {
            try {
                super.clone();
            } catch (Throwable e) {
                C0471d.m4762a(e, "PoiSearch", "queryclone");
            }
            Query query = new Query(this.f3352a, this.f3353b, this.f3354c);
            query.setPageNum(this.f3355d);
            query.setPageSize(this.f3356e);
            query.setLimitDiscount(this.f3358g);
            query.setLimitGroupbuy(this.f3357f);
            query.setQueryLanguage(this.f3359h);
            return query;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            Query query = (Query) obj;
            if (this.f3353b == null) {
                if (query.f3353b != null) {
                    return false;
                }
            } else if (!this.f3353b.equals(query.f3353b)) {
                return false;
            }
            if (this.f3354c == null) {
                if (query.f3354c != null) {
                    return false;
                }
            } else if (!this.f3354c.equals(query.f3354c)) {
                return false;
            }
            if (this.f3358g != query.f3358g) {
                return false;
            }
            if (this.f3357f != query.f3357f) {
                return false;
            }
            if (this.f3359h == null) {
                if (query.f3359h != null) {
                    return false;
                }
            } else if (!this.f3359h.equals(query.f3359h)) {
                return false;
            }
            return this.f3355d != query.f3355d ? false : this.f3356e != query.f3356e ? false : this.f3352a == null ? query.f3352a == null : this.f3352a.equals(query.f3352a);
        }

        public String getCategory() {
            return (this.f3353b == null || this.f3353b.equals("00") || this.f3353b.equals("00|")) ? m4946a() : this.f3353b;
        }

        public String getCity() {
            return this.f3354c;
        }

        public int getPageNum() {
            return this.f3355d;
        }

        public int getPageSize() {
            return this.f3356e;
        }

        protected String getQueryLanguage() {
            return this.f3359h;
        }

        public String getQueryString() {
            return this.f3352a;
        }

        public boolean hasDiscountLimit() {
            return this.f3358g;
        }

        public boolean hasGroupBuyLimit() {
            return this.f3357f;
        }

        public int hashCode() {
            int i = 1231;
            int i2 = 0;
            int hashCode = ((this.f3358g ? 1231 : 1237) + (((this.f3354c == null ? 0 : this.f3354c.hashCode()) + (((this.f3353b == null ? 0 : this.f3353b.hashCode()) + 31) * 31)) * 31)) * 31;
            if (!this.f3357f) {
                i = 1237;
            }
            hashCode = ((((((this.f3359h == null ? 0 : this.f3359h.hashCode()) + ((hashCode + i) * 31)) * 31) + this.f3355d) * 31) + this.f3356e) * 31;
            if (this.f3352a != null) {
                i2 = this.f3352a.hashCode();
            }
            return hashCode + i2;
        }

        public boolean queryEquals(Query query) {
            return query == null ? false : query != this ? PoiSearch.m4960b(query.f3352a, this.f3352a) && PoiSearch.m4960b(query.f3353b, this.f3353b) && PoiSearch.m4960b(query.f3359h, this.f3359h) && PoiSearch.m4960b(query.f3354c, this.f3354c) && query.f3356e == this.f3356e : true;
        }

        public void setLimitDiscount(boolean z) {
            this.f3358g = z;
        }

        public void setLimitGroupbuy(boolean z) {
            this.f3357f = z;
        }

        public void setPageNum(int i) {
            this.f3355d = i;
        }

        public void setPageSize(int i) {
            this.f3356e = i;
        }

        protected void setQueryLanguage(String str) {
            if (PoiSearch.ENGLISH.equals(str)) {
                this.f3359h = PoiSearch.ENGLISH;
            } else {
                this.f3359h = PoiSearch.CHINESE;
            }
        }
    }

    public class SearchBound implements Cloneable {
        public static final String BOUND_SHAPE = "Bound";
        public static final String ELLIPSE_SHAPE = "Ellipse";
        public static final String POLYGON_SHAPE = "Polygon";
        public static final String RECTANGLE_SHAPE = "Rectangle";
        private LatLonPoint f3360a;
        private LatLonPoint f3361b;
        private int f3362c;
        private LatLonPoint f3363d;
        private String f3364e;
        private boolean f3365f;
        private List<LatLonPoint> f3366g;

        public SearchBound(LatLonPoint latLonPoint, int i) {
            this.f3365f = true;
            this.f3364e = BOUND_SHAPE;
            this.f3362c = i;
            this.f3363d = latLonPoint;
            m4951a(latLonPoint, C0471d.m4758a(i), C0471d.m4758a(i));
        }

        public SearchBound(LatLonPoint latLonPoint, int i, boolean z) {
            this.f3365f = true;
            this.f3364e = BOUND_SHAPE;
            this.f3362c = i;
            this.f3363d = latLonPoint;
            m4951a(latLonPoint, C0471d.m4758a(i), C0471d.m4758a(i));
            this.f3365f = z;
        }

        public SearchBound(LatLonPoint latLonPoint, LatLonPoint latLonPoint2) {
            this.f3365f = true;
            this.f3364e = RECTANGLE_SHAPE;
            m4952a(latLonPoint, latLonPoint2);
        }

        private SearchBound(LatLonPoint latLonPoint, LatLonPoint latLonPoint2, int i, LatLonPoint latLonPoint3, String str, List<LatLonPoint> list, boolean z) {
            this.f3365f = true;
            this.f3360a = latLonPoint;
            this.f3361b = latLonPoint2;
            this.f3362c = i;
            this.f3363d = latLonPoint3;
            this.f3364e = str;
            this.f3366g = list;
            this.f3365f = z;
        }

        public SearchBound(List<LatLonPoint> list) {
            this.f3365f = true;
            this.f3364e = POLYGON_SHAPE;
            this.f3366g = list;
        }

        private void m4951a(LatLonPoint latLonPoint, double d, double d2) {
            double d3 = d / 2.0d;
            double d4 = d2 / 2.0d;
            double latitude = latLonPoint.getLatitude();
            double longitude = latLonPoint.getLongitude();
            m4952a(new LatLonPoint(latitude - d3, longitude - d4), new LatLonPoint(d3 + latitude, d4 + longitude));
        }

        private void m4952a(LatLonPoint latLonPoint, LatLonPoint latLonPoint2) {
            this.f3360a = latLonPoint;
            this.f3361b = latLonPoint2;
            if (this.f3360a.getLatitude() >= this.f3361b.getLatitude() || this.f3360a.getLongitude() >= this.f3361b.getLongitude()) {
                throw new IllegalArgumentException("invalid rect ");
            }
            this.f3363d = new LatLonPoint((this.f3360a.getLatitude() + this.f3361b.getLatitude()) / 2.0d, (this.f3360a.getLongitude() + this.f3361b.getLongitude()) / 2.0d);
        }

        public SearchBound clone() {
            try {
                super.clone();
            } catch (Throwable e) {
                C0471d.m4762a(e, "PoiSearch", "SearchBoundClone");
            }
            return new SearchBound(this.f3360a, this.f3361b, this.f3362c, this.f3363d, this.f3364e, this.f3366g, this.f3365f);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            SearchBound searchBound = (SearchBound) obj;
            if (this.f3363d == null) {
                if (searchBound.f3363d != null) {
                    return false;
                }
            } else if (!this.f3363d.equals(searchBound.f3363d)) {
                return false;
            }
            if (this.f3365f != searchBound.f3365f) {
                return false;
            }
            if (this.f3360a == null) {
                if (searchBound.f3360a != null) {
                    return false;
                }
            } else if (!this.f3360a.equals(searchBound.f3360a)) {
                return false;
            }
            if (this.f3361b == null) {
                if (searchBound.f3361b != null) {
                    return false;
                }
            } else if (!this.f3361b.equals(searchBound.f3361b)) {
                return false;
            }
            if (this.f3366g == null) {
                if (searchBound.f3366g != null) {
                    return false;
                }
            } else if (!this.f3366g.equals(searchBound.f3366g)) {
                return false;
            }
            return this.f3362c != searchBound.f3362c ? false : this.f3364e == null ? searchBound.f3364e == null : this.f3364e.equals(searchBound.f3364e);
        }

        public LatLonPoint getCenter() {
            return this.f3363d;
        }

        public double getLatSpanInMeter() {
            return !RECTANGLE_SHAPE.equals(getShape()) ? 0.0d : this.f3361b.getLatitude() - this.f3360a.getLatitude();
        }

        public double getLonSpanInMeter() {
            return !RECTANGLE_SHAPE.equals(getShape()) ? 0.0d : this.f3361b.getLongitude() - this.f3360a.getLongitude();
        }

        public LatLonPoint getLowerLeft() {
            return this.f3360a;
        }

        public List<LatLonPoint> getPolyGonList() {
            return this.f3366g;
        }

        public int getRange() {
            return this.f3362c;
        }

        public String getShape() {
            return this.f3364e;
        }

        public LatLonPoint getUpperRight() {
            return this.f3361b;
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((((this.f3366g == null ? 0 : this.f3366g.hashCode()) + (((this.f3361b == null ? 0 : this.f3361b.hashCode()) + (((this.f3360a == null ? 0 : this.f3360a.hashCode()) + (((this.f3365f ? 1231 : 1237) + (((this.f3363d == null ? 0 : this.f3363d.hashCode()) + 31) * 31)) * 31)) * 31)) * 31)) * 31) + this.f3362c) * 31;
            if (this.f3364e != null) {
                i = this.f3364e.hashCode();
            }
            return hashCode + i;
        }

        public boolean isDistanceSort() {
            return this.f3365f;
        }
    }

    public PoiSearch(Context context, Query query) {
        this.f3372e = CHINESE;
        this.f3376j = null;
        this.f3370c = context.getApplicationContext();
        setQuery(query);
        this.f3376j = C0490p.m4850a();
    }

    private void m4954a(PoiResult poiResult) {
        f3367i = new HashMap();
        if (this.f3369b != null && poiResult != null && this.f3375h > 0 && this.f3375h > this.f3369b.getPageNum()) {
            f3367i.put(Integer.valueOf(this.f3369b.getPageNum()), poiResult);
        }
    }

    private boolean m4955a() {
        return (C0471d.m4763a(this.f3369b.f3352a) && C0471d.m4763a(this.f3369b.f3353b)) ? false : true;
    }

    private boolean m4956a(int i) {
        return i <= this.f3375h && i >= 0;
    }

    private boolean m4959b() {
        SearchBound bound = getBound();
        return bound != null && bound.getShape().equals(SearchBound.BOUND_SHAPE);
    }

    private static boolean m4960b(String str, String str2) {
        return (str == null && str2 == null) ? true : (str == null || str2 == null) ? false : str.equals(str2);
    }

    public SearchBound getBound() {
        return this.f3368a;
    }

    public String getLanguage() {
        return this.f3372e;
    }

    protected PoiResult getPageLocal(int i) {
        if (m4956a(i)) {
            return (PoiResult) f3367i.get(Integer.valueOf(i));
        }
        throw new IllegalArgumentException("page out of range");
    }

    public Query getQuery() {
        return this.f3369b;
    }

    public PoiResult searchPOI() {
        C0480l.m4841a(this.f3370c);
        if (m4959b() || m4955a()) {
            this.f3369b.setQueryLanguage(this.f3372e);
            if ((!this.f3369b.queryEquals(this.f3373f) && this.f3368a == null) || !(this.f3369b.queryEquals(this.f3373f) || this.f3368a.equals(this.f3374g))) {
                this.f3375h = 0;
                this.f3373f = this.f3369b.clone();
                if (this.f3368a != null) {
                    this.f3374g = this.f3368a.clone();
                }
                if (f3367i != null) {
                    f3367i.clear();
                }
            }
            SearchBound searchBound = null;
            if (this.f3368a != null) {
                searchBound = this.f3368a.clone();
            }
            C0525j c0525j;
            PoiResult a;
            if (this.f3375h == 0) {
                c0525j = new C0525j(this.f3370c, new C0492s(this.f3369b.clone(), searchBound));
                c0525j.m4983a(this.f3369b.f3355d);
                c0525j.m4986b(this.f3369b.f3356e);
                a = PoiResult.m4945a(c0525j, (ArrayList) c0525j.m4458g());
                m4954a(a);
                return a;
            }
            PoiResult pageLocal = getPageLocal(this.f3369b.getPageNum());
            if (pageLocal != null) {
                return pageLocal;
            }
            c0525j = new C0525j(this.f3370c, new C0492s(this.f3369b.clone(), searchBound));
            c0525j.m4983a(this.f3369b.f3355d);
            c0525j.m4986b(this.f3369b.f3356e);
            a = PoiResult.m4945a(c0525j, (ArrayList) c0525j.m4458g());
            f3367i.put(Integer.valueOf(this.f3369b.f3355d), a);
            return a;
        }
        throw new AMapException(AMapException.ERROR_INVALID_PARAMETER);
    }

    public void searchPOIAsyn() {
        new C05141(this).start();
    }

    public PoiItemDetail searchPOIDetail(String str) {
        C0480l.m4841a(this.f3370c);
        return (PoiItemDetail) new C0524i(this.f3370c, str, this.f3372e).m4458g();
    }

    public void searchPOIDetailAsyn(String str) {
        new C05152(this, str).start();
    }

    public void setBound(SearchBound searchBound) {
        this.f3368a = searchBound;
    }

    public void setLanguage(String str) {
        if (ENGLISH.equals(str)) {
            this.f3372e = ENGLISH;
        } else {
            this.f3372e = CHINESE;
        }
    }

    public void setOnPoiSearchListener(OnPoiSearchListener onPoiSearchListener) {
        this.f3371d = onPoiSearchListener;
    }

    public void setQuery(Query query) {
        this.f3369b = query;
    }
}
