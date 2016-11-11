package com.amap.api.mapcore;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.amap.api.mapcore.util.bg;
import com.amap.api.mapcore.util.bm;
import com.amap.api.mapcore.util.ce;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.autonavi.amap.mapcore.DPoint;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.MapProjection;
import com.tencent.mm.sdk.platformtools.Util;
import com.tencent.open.yyb.TitleBar;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.codehaus.jackson.util.TokenBuffer.Segment;

/* renamed from: com.amap.api.mapcore.d */
class C0311d extends Handler {
    final /* synthetic */ AMapDelegateImp f1924a;

    C0311d(AMapDelegateImp aMapDelegateImp) {
        this.f1924a = aMapDelegateImp;
    }

    public void handleMessage(Message message) {
        if (message != null && !this.f1924a.aR.booleanValue()) {
            this.f1924a.m2518f(false);
            CameraPosition cameraPosition;
            C0325p c0325p;
            int b;
            int c;
            switch (message.what) {
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    Log.w("amapsdk", "Key\u9a8c\u8bc1\u5931\u8d25\uff1a[" + bm.f2215b + "]");
                    break;
                case Type.OBJECT /*10*/:
                    cameraPosition = (CameraPosition) message.obj;
                    if (!(cameraPosition == null || this.f1924a.aa == null)) {
                        this.f1924a.aa.onCameraChange(cameraPosition);
                        break;
                    }
                case Opcodes.T_LONG /*11*/:
                    if (this.f1924a.aE != null) {
                        try {
                            this.f1924a.m2463a(this.f1924a.aE);
                        } catch (Throwable th) {
                            ce.m3829a(th, "AMapDelegateImp", "onMapLoaded");
                            th.printStackTrace();
                        }
                    }
                    if (this.f1924a.f1522Z != null) {
                        this.f1924a.f1522Z.onMapLoaded();
                        break;
                    }
                    break;
                case Opcodes.FCONST_1 /*12*/:
                    c0325p = (C0325p) message.obj;
                    if (c0325p != null) {
                        this.f1924a.f1527e.m2798a(c0325p);
                        break;
                    }
                    break;
                case Opcodes.FCONST_2 /*13*/:
                    if (this.f1924a.at != null && this.f1924a.at.m3287h()) {
                        switch (this.f1924a.at.m3289j()) {
                            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                                c0325p = C0325p.m3303a(new IPoint(this.f1924a.at.m3280b(), this.f1924a.at.m3282c()), this.f1924a.at.m3283d(), this.f1924a.at.m3284e(), this.f1924a.at.m3285f());
                                if (this.f1924a.at.m3279a()) {
                                    c0325p.f2041p = true;
                                }
                                c0325p.f2039n = this.f1924a.at.m3290k();
                                this.f1924a.f1527e.m2798a(c0325p);
                                break;
                            default:
                                b = this.f1924a.at.m3280b() - this.f1924a.au;
                                c = this.f1924a.at.m3282c() - this.f1924a.av;
                                this.f1924a.au = this.f1924a.at.m3280b();
                                this.f1924a.av = this.f1924a.at.m3282c();
                                FPoint fPoint = new FPoint((float) (b + (this.f1924a.m2536n() / 2)), (float) (c + (this.f1924a.m2538o() / 2)));
                                FPoint fPoint2 = new FPoint();
                                this.f1924a.f1506J.win2Map((int) fPoint.f3693x, (int) fPoint.f3694y, fPoint2);
                                IPoint iPoint = new IPoint();
                                this.f1924a.f1506J.map2Geo(fPoint2.f3693x, fPoint2.f3694y, iPoint);
                                c0325p = C0325p.m3302a(iPoint);
                                if (this.f1924a.at.m3279a()) {
                                    c0325p.f2041p = true;
                                }
                                this.f1924a.f1527e.m2798a(c0325p);
                                break;
                        }
                    }
                case Opcodes.DCONST_0 /*14*/:
                    if (this.f1924a.f1514R != null) {
                        this.f1924a.f1514R.m3338b();
                        break;
                    }
                    return;
                case Segment.TOKENS_PER_SEGMENT /*16*/:
                    Bitmap bitmap = (Bitmap) message.obj;
                    c = message.arg1;
                    if (bitmap != null) {
                        Canvas canvas = new Canvas(bitmap);
                        if (this.f1924a.f1512P != null) {
                            this.f1924a.f1512P.onDraw(canvas);
                        }
                        if (!(this.f1924a.aj == null || this.f1924a.ak == null)) {
                            Bitmap drawingCache = this.f1924a.aj.getDrawingCache(true);
                            if (drawingCache != null) {
                                canvas.drawBitmap(drawingCache, (float) this.f1924a.aj.getLeft(), (float) this.f1924a.aj.getTop(), new Paint());
                            }
                        }
                        if (this.f1924a.aA != null) {
                            this.f1924a.aA.onMapPrint(new BitmapDrawable(this.f1924a.f1504H.getResources(), bitmap));
                        }
                        if (this.f1924a.aB != null) {
                            this.f1924a.aB.onMapScreenShot(bitmap);
                            this.f1924a.aB.onMapScreenShot(bitmap, c);
                        }
                    } else {
                        if (this.f1924a.aA != null) {
                            this.f1924a.aA.onMapPrint(null);
                        }
                        if (this.f1924a.aB != null) {
                            this.f1924a.aB.onMapScreenShot(null);
                            this.f1924a.aB.onMapScreenShot(null, c);
                        }
                    }
                    this.f1924a.aA = null;
                    this.f1924a.aB = null;
                    break;
                case Opcodes.SIPUSH /*17*/:
                    if (!(this.f1924a.aj == null || this.f1924a.al == null)) {
                        this.f1924a.aj.setVisibility(0);
                    }
                    try {
                        cameraPosition = this.f1924a.m2543r();
                        if (cameraPosition != null) {
                            if (cameraPosition.zoom < TitleBar.SHAREBTN_RIGHT_MARGIN || bg.m3590a(cameraPosition.target.latitude, cameraPosition.target.longitude)) {
                                this.f1924a.f1512P.setVisibility(0);
                            } else {
                                this.f1924a.f1512P.setVisibility(8);
                            }
                        }
                        if (this.f1924a.aw == null || !this.f1924a.aO) {
                            this.f1924a.m2494a(true, cameraPosition);
                        }
                        if (this.f1924a.aw != null) {
                            this.f1924a.aP = true;
                            this.f1924a.aw.onFinish();
                            this.f1924a.aP = false;
                        }
                        if (!this.f1924a.aQ) {
                            this.f1924a.aw = null;
                            break;
                        } else {
                            this.f1924a.aQ = false;
                            break;
                        }
                    } catch (Throwable th2) {
                        ce.m3829a(th2, "AMapDelegateImpGLSurfaceView", "CameraUpdateFinish");
                        break;
                    }
                    break;
                case Opcodes.LDC /*18*/:
                    b = this.f1924a.m2536n();
                    int o = this.f1924a.m2538o();
                    if (b > 0 && o > 0) {
                        try {
                            CameraPosition r = this.f1924a.m2543r();
                            MapProjection.lonlat2Geo(r.target.longitude, r.target.latitude, new IPoint());
                            MapProjection mapProjection = new MapProjection(this.f1924a.f1503G);
                            mapProjection.setCameraHeaderAngle(r.tilt);
                            mapProjection.setMapAngle(r.bearing);
                            mapProjection.setMapZoomer(r.zoom);
                            mapProjection.recalculate();
                            DPoint dPoint = new DPoint();
                            this.f1924a.m2370a(mapProjection, 0, 0, dPoint);
                            LatLng latLng = new LatLng(dPoint.f3692y, dPoint.f3691x, false);
                            this.f1924a.m2370a(mapProjection, b, 0, dPoint);
                            LatLng latLng2 = new LatLng(dPoint.f3692y, dPoint.f3691x, false);
                            this.f1924a.m2370a(mapProjection, 0, o, dPoint);
                            LatLng latLng3 = new LatLng(dPoint.f3692y, dPoint.f3691x, false);
                            this.f1924a.m2370a(mapProjection, b, o, dPoint);
                            this.f1924a.bq = LatLngBounds.builder().include(latLng3).include(new LatLng(dPoint.f3692y, dPoint.f3691x, false)).include(latLng).include(latLng2).build();
                            mapProjection.recycle();
                            break;
                        } catch (Throwable th3) {
                            break;
                        }
                    }
                    this.f1924a.bq = null;
                    break;
                    break;
                case Util.MAX_ACCOUNT_LENGTH /*20*/:
                    if (this.f1924a.at.m3279a() || !(this.f1924a.at.m3289j() == 1 || this.f1924a.f1529g == null)) {
                        this.f1924a.f1529g.m3179b(false);
                    }
                    if (this.f1924a.f1529g != null) {
                        this.f1924a.f1529g.m3177a(message.arg1 != 0);
                        break;
                    }
                    break;
                case Opcodes.ILOAD /*21*/:
                    if (this.f1924a.f1528f != null) {
                        this.f1924a.f1528f.m3227a(this.f1924a.m2419F());
                        break;
                    }
                    break;
                case Opcodes.LLOAD /*22*/:
                    this.f1924a.ah();
                    break;
            }
            this.f1924a.m2518f(false);
        }
    }
}
