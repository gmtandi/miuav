package com.amap.api.services.route;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.C0450a;
import com.amap.api.services.core.C0471d;
import com.amap.api.services.core.C0473f;
import com.amap.api.services.core.C0480l;
import com.amap.api.services.core.C0490p;
import com.amap.api.services.core.C0494u;
import com.amap.api.services.core.LatLonPoint;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.util.ArrayList;
import java.util.List;

public class RouteSearch {
    public static final int BusComfortable = 4;
    public static final int BusDefault = 0;
    public static final int BusLeaseChange = 2;
    public static final int BusLeaseWalk = 3;
    public static final int BusNoSubway = 5;
    public static final int BusSaveMoney = 1;
    public static final int DrivingAvoidCongestion = 4;
    public static final int DrivingDefault = 0;
    public static final int DrivingMultiStrategy = 5;
    public static final int DrivingNoExpressways = 3;
    public static final int DrivingNoHighAvoidCongestionSaveMoney = 9;
    public static final int DrivingNoHighWay = 6;
    public static final int DrivingNoHighWaySaveMoney = 7;
    public static final int DrivingSaveMoney = 1;
    public static final int DrivingSaveMoneyAvoidCongestion = 8;
    public static final int DrivingShortDistance = 2;
    public static final int WalkDefault = 0;
    public static final int WalkMultipath = 1;
    private OnRouteSearchListener f3477a;
    private Context f3478b;
    private Handler f3479c;

    /* renamed from: com.amap.api.services.route.RouteSearch.1 */
    class C05291 extends Thread {
        final /* synthetic */ WalkRouteQuery f3456a;
        final /* synthetic */ RouteSearch f3457b;

        C05291(RouteSearch routeSearch, WalkRouteQuery walkRouteQuery) {
            this.f3457b = routeSearch;
            this.f3456a = walkRouteQuery;
        }

        public void run() {
            Message obtainMessage = C0490p.m4850a().obtainMessage();
            obtainMessage.what = 12;
            obtainMessage.arg1 = RouteSearch.WalkMultipath;
            Bundle bundle = new Bundle();
            Parcelable parcelable = null;
            try {
                parcelable = this.f3457b.calculateWalkRoute(this.f3456a);
                bundle.putInt("errorCode", RouteSearch.WalkDefault);
                obtainMessage.obj = this.f3457b.f3477a;
                bundle.putParcelable("result", parcelable);
                obtainMessage.setData(bundle);
                this.f3457b.f3479c.sendMessage(obtainMessage);
            } catch (Throwable e) {
                C0471d.m4762a(e, "RouteSearch", "calculateWalkRouteAsyn");
                bundle.putInt("errorCode", e.getErrorCode());
                obtainMessage.obj = this.f3457b.f3477a;
                bundle.putParcelable("result", parcelable);
                obtainMessage.setData(bundle);
                this.f3457b.f3479c.sendMessage(obtainMessage);
            } catch (Throwable th) {
                obtainMessage.obj = this.f3457b.f3477a;
                bundle.putParcelable("result", parcelable);
                obtainMessage.setData(bundle);
                this.f3457b.f3479c.sendMessage(obtainMessage);
            }
        }
    }

    /* renamed from: com.amap.api.services.route.RouteSearch.2 */
    class C05302 extends Thread {
        final /* synthetic */ BusRouteQuery f3458a;
        final /* synthetic */ RouteSearch f3459b;

        C05302(RouteSearch routeSearch, BusRouteQuery busRouteQuery) {
            this.f3459b = routeSearch;
            this.f3458a = busRouteQuery;
        }

        public void run() {
            Message obtainMessage = C0490p.m4850a().obtainMessage();
            obtainMessage.what = 10;
            obtainMessage.arg1 = RouteSearch.WalkMultipath;
            Bundle bundle = new Bundle();
            Parcelable parcelable = null;
            try {
                parcelable = this.f3459b.calculateBusRoute(this.f3458a);
                bundle.putInt("errorCode", RouteSearch.WalkDefault);
                obtainMessage.obj = this.f3459b.f3477a;
                bundle.putParcelable("result", parcelable);
                obtainMessage.setData(bundle);
                this.f3459b.f3479c.sendMessage(obtainMessage);
            } catch (Throwable e) {
                C0471d.m4762a(e, "RouteSearch", "calculateBusRouteAsyn");
                bundle.putInt("errorCode", e.getErrorCode());
                obtainMessage.obj = this.f3459b.f3477a;
                bundle.putParcelable("result", parcelable);
                obtainMessage.setData(bundle);
                this.f3459b.f3479c.sendMessage(obtainMessage);
            } catch (Throwable th) {
                obtainMessage.obj = this.f3459b.f3477a;
                bundle.putParcelable("result", parcelable);
                obtainMessage.setData(bundle);
                this.f3459b.f3479c.sendMessage(obtainMessage);
            }
        }
    }

    /* renamed from: com.amap.api.services.route.RouteSearch.3 */
    class C05313 extends Thread {
        final /* synthetic */ DriveRouteQuery f3460a;
        final /* synthetic */ RouteSearch f3461b;

        C05313(RouteSearch routeSearch, DriveRouteQuery driveRouteQuery) {
            this.f3461b = routeSearch;
            this.f3460a = driveRouteQuery;
        }

        public void run() {
            Message obtainMessage = C0490p.m4850a().obtainMessage();
            obtainMessage.what = 11;
            obtainMessage.arg1 = RouteSearch.WalkMultipath;
            Bundle bundle = new Bundle();
            Parcelable parcelable = null;
            try {
                parcelable = this.f3461b.calculateDriveRoute(this.f3460a);
                bundle.putInt("errorCode", RouteSearch.WalkDefault);
                obtainMessage.obj = this.f3461b.f3477a;
                bundle.putParcelable("result", parcelable);
                obtainMessage.setData(bundle);
                this.f3461b.f3479c.sendMessage(obtainMessage);
            } catch (Throwable e) {
                C0471d.m4762a(e, "RouteSearch", "calculateDriveRouteAsyn");
                bundle.putInt("errorCode", e.getErrorCode());
                obtainMessage.obj = this.f3461b.f3477a;
                bundle.putParcelable("result", parcelable);
                obtainMessage.setData(bundle);
                this.f3461b.f3479c.sendMessage(obtainMessage);
            } catch (Throwable th) {
                obtainMessage.obj = this.f3461b.f3477a;
                bundle.putParcelable("result", parcelable);
                obtainMessage.setData(bundle);
                this.f3461b.f3479c.sendMessage(obtainMessage);
            }
        }
    }

    public class BusRouteQuery implements Parcelable, Cloneable {
        public static final Creator<BusRouteQuery> CREATOR;
        private FromAndTo f3462a;
        private int f3463b;
        private String f3464c;
        private int f3465d;

        static {
            CREATOR = new C0544m();
        }

        public BusRouteQuery(Parcel parcel) {
            this.f3462a = (FromAndTo) parcel.readParcelable(FromAndTo.class.getClassLoader());
            this.f3463b = parcel.readInt();
            this.f3464c = parcel.readString();
            this.f3465d = parcel.readInt();
        }

        public BusRouteQuery(FromAndTo fromAndTo, int i, String str, int i2) {
            this.f3462a = fromAndTo;
            this.f3463b = i;
            this.f3464c = str;
            this.f3465d = i2;
        }

        public BusRouteQuery clone() {
            try {
                super.clone();
            } catch (Throwable e) {
                C0471d.m4762a(e, "RouteSearch", "BusRouteQueryclone");
            }
            return new BusRouteQuery(this.f3462a, this.f3463b, this.f3464c, this.f3465d);
        }

        public int describeContents() {
            return RouteSearch.WalkDefault;
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
            BusRouteQuery busRouteQuery = (BusRouteQuery) obj;
            if (this.f3464c == null) {
                if (busRouteQuery.f3464c != null) {
                    return false;
                }
            } else if (!this.f3464c.equals(busRouteQuery.f3464c)) {
                return false;
            }
            if (this.f3462a == null) {
                if (busRouteQuery.f3462a != null) {
                    return false;
                }
            } else if (!this.f3462a.equals(busRouteQuery.f3462a)) {
                return false;
            }
            return this.f3463b != busRouteQuery.f3463b ? false : this.f3465d == busRouteQuery.f3465d;
        }

        public String getCity() {
            return this.f3464c;
        }

        public FromAndTo getFromAndTo() {
            return this.f3462a;
        }

        public int getMode() {
            return this.f3463b;
        }

        public int getNightFlag() {
            return this.f3465d;
        }

        public int hashCode() {
            int i = RouteSearch.WalkDefault;
            int hashCode = ((this.f3464c == null ? RouteSearch.WalkDefault : this.f3464c.hashCode()) + 31) * 31;
            if (this.f3462a != null) {
                i = this.f3462a.hashCode();
            }
            return ((((hashCode + i) * 31) + this.f3463b) * 31) + this.f3465d;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.f3462a, i);
            parcel.writeInt(this.f3463b);
            parcel.writeString(this.f3464c);
            parcel.writeInt(this.f3465d);
        }
    }

    public class DriveRouteQuery implements Parcelable, Cloneable {
        public static final Creator<DriveRouteQuery> CREATOR;
        private FromAndTo f3466a;
        private int f3467b;
        private List<LatLonPoint> f3468c;
        private List<List<LatLonPoint>> f3469d;
        private String f3470e;

        static {
            CREATOR = new C0545n();
        }

        public DriveRouteQuery(Parcel parcel) {
            this.f3466a = (FromAndTo) parcel.readParcelable(FromAndTo.class.getClassLoader());
            this.f3467b = parcel.readInt();
            this.f3468c = parcel.createTypedArrayList(LatLonPoint.CREATOR);
            int readInt = parcel.readInt();
            if (readInt == 0) {
                this.f3469d = null;
            } else {
                this.f3469d = new ArrayList();
            }
            for (int i = RouteSearch.WalkDefault; i < readInt; i += RouteSearch.WalkMultipath) {
                this.f3469d.add(parcel.createTypedArrayList(LatLonPoint.CREATOR));
            }
            this.f3470e = parcel.readString();
        }

        public DriveRouteQuery(FromAndTo fromAndTo, int i, List<LatLonPoint> list, List<List<LatLonPoint>> list2, String str) {
            this.f3466a = fromAndTo;
            this.f3467b = i;
            this.f3468c = list;
            this.f3469d = list2;
            this.f3470e = str;
        }

        public DriveRouteQuery clone() {
            try {
                super.clone();
            } catch (Throwable e) {
                C0471d.m4762a(e, "RouteSearch", "DriveRouteQueryclone");
            }
            return new DriveRouteQuery(this.f3466a, this.f3467b, this.f3468c, this.f3469d, this.f3470e);
        }

        public int describeContents() {
            return RouteSearch.WalkDefault;
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
            DriveRouteQuery driveRouteQuery = (DriveRouteQuery) obj;
            if (this.f3470e == null) {
                if (driveRouteQuery.f3470e != null) {
                    return false;
                }
            } else if (!this.f3470e.equals(driveRouteQuery.f3470e)) {
                return false;
            }
            if (this.f3469d == null) {
                if (driveRouteQuery.f3469d != null) {
                    return false;
                }
            } else if (!this.f3469d.equals(driveRouteQuery.f3469d)) {
                return false;
            }
            if (this.f3466a == null) {
                if (driveRouteQuery.f3466a != null) {
                    return false;
                }
            } else if (!this.f3466a.equals(driveRouteQuery.f3466a)) {
                return false;
            }
            return this.f3467b != driveRouteQuery.f3467b ? false : this.f3468c == null ? driveRouteQuery.f3468c == null : this.f3468c.equals(driveRouteQuery.f3468c);
        }

        public String getAvoidRoad() {
            return this.f3470e;
        }

        public List<List<LatLonPoint>> getAvoidpolygons() {
            return this.f3469d;
        }

        public String getAvoidpolygonsStr() {
            StringBuffer stringBuffer = new StringBuffer();
            if (this.f3469d == null || this.f3469d.size() == 0) {
                return null;
            }
            for (int i = RouteSearch.WalkDefault; i < this.f3469d.size(); i += RouteSearch.WalkMultipath) {
                List list = (List) this.f3469d.get(i);
                for (int i2 = RouteSearch.WalkDefault; i2 < list.size(); i2 += RouteSearch.WalkMultipath) {
                    LatLonPoint latLonPoint = (LatLonPoint) list.get(i2);
                    stringBuffer.append(latLonPoint.getLongitude());
                    stringBuffer.append(MiPushClient.ACCEPT_TIME_SEPARATOR);
                    stringBuffer.append(latLonPoint.getLatitude());
                    if (i2 < list.size() - 1) {
                        stringBuffer.append(";");
                    }
                }
                if (i < this.f3469d.size() - 1) {
                    stringBuffer.append("|");
                }
            }
            return stringBuffer.toString();
        }

        public FromAndTo getFromAndTo() {
            return this.f3466a;
        }

        public int getMode() {
            return this.f3467b;
        }

        public List<LatLonPoint> getPassedByPoints() {
            return this.f3468c;
        }

        public String getPassedPointStr() {
            StringBuffer stringBuffer = new StringBuffer();
            if (this.f3468c == null || this.f3468c.size() == 0) {
                return null;
            }
            for (int i = RouteSearch.WalkDefault; i < this.f3468c.size(); i += RouteSearch.WalkMultipath) {
                LatLonPoint latLonPoint = (LatLonPoint) this.f3468c.get(i);
                stringBuffer.append(latLonPoint.getLongitude());
                stringBuffer.append(MiPushClient.ACCEPT_TIME_SEPARATOR);
                stringBuffer.append(latLonPoint.getLatitude());
                if (i < this.f3468c.size() - 1) {
                    stringBuffer.append(";");
                }
            }
            return stringBuffer.toString();
        }

        public boolean hasAvoidRoad() {
            return !C0471d.m4763a(getAvoidRoad());
        }

        public boolean hasAvoidpolygons() {
            return !C0471d.m4763a(getAvoidpolygonsStr());
        }

        public boolean hasPassPoint() {
            return !C0471d.m4763a(getPassedPointStr());
        }

        public int hashCode() {
            int i = RouteSearch.WalkDefault;
            int hashCode = ((((this.f3466a == null ? RouteSearch.WalkDefault : this.f3466a.hashCode()) + (((this.f3469d == null ? RouteSearch.WalkDefault : this.f3469d.hashCode()) + (((this.f3470e == null ? RouteSearch.WalkDefault : this.f3470e.hashCode()) + 31) * 31)) * 31)) * 31) + this.f3467b) * 31;
            if (this.f3468c != null) {
                i = this.f3468c.hashCode();
            }
            return hashCode + i;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.f3466a, i);
            parcel.writeInt(this.f3467b);
            parcel.writeTypedList(this.f3468c);
            if (this.f3469d == null) {
                parcel.writeInt(RouteSearch.WalkDefault);
            } else {
                parcel.writeInt(this.f3469d.size());
                for (List writeTypedList : this.f3469d) {
                    parcel.writeTypedList(writeTypedList);
                }
            }
            parcel.writeString(this.f3470e);
        }
    }

    public class FromAndTo implements Parcelable, Cloneable {
        public static final Creator<FromAndTo> CREATOR;
        private LatLonPoint f3471a;
        private LatLonPoint f3472b;
        private String f3473c;
        private String f3474d;

        static {
            CREATOR = new C0546o();
        }

        public FromAndTo(Parcel parcel) {
            this.f3471a = (LatLonPoint) parcel.readParcelable(LatLonPoint.class.getClassLoader());
            this.f3472b = (LatLonPoint) parcel.readParcelable(LatLonPoint.class.getClassLoader());
            this.f3473c = parcel.readString();
            this.f3474d = parcel.readString();
        }

        public FromAndTo(LatLonPoint latLonPoint, LatLonPoint latLonPoint2) {
            this.f3471a = latLonPoint;
            this.f3472b = latLonPoint2;
        }

        public FromAndTo clone() {
            try {
                super.clone();
            } catch (Throwable e) {
                C0471d.m4762a(e, "RouteSearch", "FromAndToclone");
            }
            FromAndTo fromAndTo = new FromAndTo(this.f3471a, this.f3472b);
            fromAndTo.setStartPoiID(this.f3473c);
            fromAndTo.setDestinationPoiID(this.f3474d);
            return fromAndTo;
        }

        public int describeContents() {
            return RouteSearch.WalkDefault;
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
            FromAndTo fromAndTo = (FromAndTo) obj;
            if (this.f3474d == null) {
                if (fromAndTo.f3474d != null) {
                    return false;
                }
            } else if (!this.f3474d.equals(fromAndTo.f3474d)) {
                return false;
            }
            if (this.f3471a == null) {
                if (fromAndTo.f3471a != null) {
                    return false;
                }
            } else if (!this.f3471a.equals(fromAndTo.f3471a)) {
                return false;
            }
            if (this.f3473c == null) {
                if (fromAndTo.f3473c != null) {
                    return false;
                }
            } else if (!this.f3473c.equals(fromAndTo.f3473c)) {
                return false;
            }
            return this.f3472b == null ? fromAndTo.f3472b == null : this.f3472b.equals(fromAndTo.f3472b);
        }

        public String getDestinationPoiID() {
            return this.f3474d;
        }

        public LatLonPoint getFrom() {
            return this.f3471a;
        }

        public String getStartPoiID() {
            return this.f3473c;
        }

        public LatLonPoint getTo() {
            return this.f3472b;
        }

        public int hashCode() {
            int i = RouteSearch.WalkDefault;
            int hashCode = ((this.f3473c == null ? RouteSearch.WalkDefault : this.f3473c.hashCode()) + (((this.f3471a == null ? RouteSearch.WalkDefault : this.f3471a.hashCode()) + (((this.f3474d == null ? RouteSearch.WalkDefault : this.f3474d.hashCode()) + 31) * 31)) * 31)) * 31;
            if (this.f3472b != null) {
                i = this.f3472b.hashCode();
            }
            return hashCode + i;
        }

        public void setDestinationPoiID(String str) {
            this.f3474d = str;
        }

        public void setStartPoiID(String str) {
            this.f3473c = str;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.f3471a, i);
            parcel.writeParcelable(this.f3472b, i);
            parcel.writeString(this.f3473c);
            parcel.writeString(this.f3474d);
        }
    }

    public interface OnRouteSearchListener {
        void onBusRouteSearched(BusRouteResult busRouteResult, int i);

        void onDriveRouteSearched(DriveRouteResult driveRouteResult, int i);

        void onWalkRouteSearched(WalkRouteResult walkRouteResult, int i);
    }

    public class WalkRouteQuery implements Parcelable, Cloneable {
        public static final Creator<WalkRouteQuery> CREATOR;
        private FromAndTo f3475a;
        private int f3476b;

        static {
            CREATOR = new C0547p();
        }

        public WalkRouteQuery(Parcel parcel) {
            this.f3475a = (FromAndTo) parcel.readParcelable(FromAndTo.class.getClassLoader());
            this.f3476b = parcel.readInt();
        }

        public WalkRouteQuery(FromAndTo fromAndTo, int i) {
            this.f3475a = fromAndTo;
            this.f3476b = i;
        }

        public WalkRouteQuery clone() {
            try {
                super.clone();
            } catch (Throwable e) {
                C0471d.m4762a(e, "RouteSearch", "WalkRouteQueryclone");
            }
            return new WalkRouteQuery(this.f3475a, this.f3476b);
        }

        public int describeContents() {
            return RouteSearch.WalkDefault;
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
            WalkRouteQuery walkRouteQuery = (WalkRouteQuery) obj;
            if (this.f3475a == null) {
                if (walkRouteQuery.f3475a != null) {
                    return false;
                }
            } else if (!this.f3475a.equals(walkRouteQuery.f3475a)) {
                return false;
            }
            return this.f3476b == walkRouteQuery.f3476b;
        }

        public FromAndTo getFromAndTo() {
            return this.f3475a;
        }

        public int getMode() {
            return this.f3476b;
        }

        public int hashCode() {
            return (((this.f3475a == null ? RouteSearch.WalkDefault : this.f3475a.hashCode()) + 31) * 31) + this.f3476b;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.f3475a, i);
            parcel.writeInt(this.f3476b);
        }
    }

    public RouteSearch(Context context) {
        this.f3478b = context.getApplicationContext();
        this.f3479c = C0490p.m4850a();
    }

    public BusRouteResult calculateBusRoute(BusRouteQuery busRouteQuery) {
        C0480l.m4841a(this.f3478b);
        BusRouteQuery clone = busRouteQuery.clone();
        BusRouteResult busRouteResult = (BusRouteResult) new C0450a(this.f3478b, clone).m4458g();
        if (busRouteResult != null) {
            busRouteResult.setBusQuery(clone);
        }
        return busRouteResult;
    }

    public void calculateBusRouteAsyn(BusRouteQuery busRouteQuery) {
        new C05302(this, busRouteQuery).start();
    }

    public DriveRouteResult calculateDriveRoute(DriveRouteQuery driveRouteQuery) {
        C0480l.m4841a(this.f3478b);
        DriveRouteQuery clone = driveRouteQuery.clone();
        DriveRouteResult driveRouteResult = (DriveRouteResult) new C0473f(this.f3478b, clone).m4458g();
        if (driveRouteResult != null) {
            driveRouteResult.setDriveQuery(clone);
        }
        return driveRouteResult;
    }

    public void calculateDriveRouteAsyn(DriveRouteQuery driveRouteQuery) {
        new C05313(this, driveRouteQuery).start();
    }

    public WalkRouteResult calculateWalkRoute(WalkRouteQuery walkRouteQuery) {
        C0480l.m4841a(this.f3478b);
        WalkRouteQuery clone = walkRouteQuery.clone();
        WalkRouteResult walkRouteResult = (WalkRouteResult) new C0494u(this.f3478b, clone).m4458g();
        if (walkRouteResult != null) {
            walkRouteResult.setWalkQuery(clone);
        }
        return walkRouteResult;
    }

    public void calculateWalkRouteAsyn(WalkRouteQuery walkRouteQuery) {
        new C05291(this, walkRouteQuery).start();
    }

    public void setRouteSearchListener(OnRouteSearchListener onRouteSearchListener) {
        this.f3477a = onRouteSearchListener;
    }
}
