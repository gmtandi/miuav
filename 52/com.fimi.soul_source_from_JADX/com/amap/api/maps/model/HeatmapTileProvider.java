package com.amap.api.maps.model;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Color;
import android.support.v4.util.LongSparseArray;
import android.util.Log;
import com.amap.api.mapcore.util.ay;
import com.amap.api.maps.AMapException;
import com.autonavi.amap.mapcore.DPoint;
import com.autonavi.amap.mapcore.MapTilsCacheAndResManager;
import com.fimi.soul.view.photodraweeview.C2020f;
import com.tencent.mm.sdk.platformtools.Util;
import it.p074a.p075a.C2799f;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

public class HeatmapTileProvider implements TileProvider {
    public static final Gradient DEFAULT_GRADIENT;
    public static final double DEFAULT_OPACITY = 0.6d;
    public static final int DEFAULT_RADIUS = 12;
    private static final int[] f2693a;
    private static final float[] f2694b;
    private C0431c f2695c;
    private Collection<WeightedLatLng> f2696d;
    private ay f2697e;
    private int f2698f;
    private Gradient f2699g;
    private int[] f2700h;
    private double[] f2701i;
    private double f2702j;
    private double[] f2703k;

    public class Builder {
        private Collection<WeightedLatLng> f2689a;
        private int f2690b;
        private Gradient f2691c;
        private double f2692d;

        public Builder() {
            this.f2690b = HeatmapTileProvider.DEFAULT_RADIUS;
            this.f2691c = HeatmapTileProvider.DEFAULT_GRADIENT;
            this.f2692d = HeatmapTileProvider.DEFAULT_OPACITY;
        }

        public HeatmapTileProvider build() {
            if (this.f2689a != null && this.f2689a.size() != 0) {
                return new HeatmapTileProvider();
            }
            try {
                throw new AMapException("No input points.");
            } catch (AMapException e) {
                Log.e(MapTilsCacheAndResManager.AUTONAVI_PATH, e.getErrorMessage());
                e.printStackTrace();
                return null;
            }
        }

        public Builder data(Collection<LatLng> collection) {
            return weightedData(HeatmapTileProvider.m4337d(collection));
        }

        public Builder gradient(Gradient gradient) {
            this.f2691c = gradient;
            return this;
        }

        public Builder radius(int i) {
            this.f2690b = Math.max(10, Math.min(i, 50));
            return this;
        }

        public Builder transparency(double d) {
            this.f2692d = Math.max(0.0d, Math.min(d, WeightedLatLng.DEFAULT_INTENSITY));
            return this;
        }

        public Builder weightedData(Collection<WeightedLatLng> collection) {
            this.f2689a = collection;
            return this;
        }
    }

    static {
        f2693a = new int[]{Color.rgb(Opcodes.FSUB, C2799f.f14253C, 0), Color.rgb(Util.MASK_8BIT, 0, 0)};
        f2694b = new float[]{0.2f, C2020f.f10933c};
        DEFAULT_GRADIENT = new Gradient(f2693a, f2694b);
    }

    private HeatmapTileProvider(Builder builder) {
        this.f2696d = builder.f2689a;
        this.f2698f = builder.f2690b;
        this.f2699g = builder.f2691c;
        if (this.f2699g == null || !this.f2699g.isAvailable()) {
            this.f2699g = DEFAULT_GRADIENT;
        }
        this.f2702j = builder.f2692d;
        this.f2701i = m4333a(this.f2698f, ((double) this.f2698f) / 3.0d);
        m4331a(this.f2699g);
        m4336c(this.f2696d);
    }

    static double m4327a(Collection<WeightedLatLng> collection, ay ayVar, int i, int i2) {
        double d = ayVar.f2142a;
        double d2 = ayVar.f2144c;
        double d3 = ayVar.f2143b;
        double d4 = ayVar.f2145d;
        double d5 = ((double) ((int) (((double) (i2 / (i * 2))) + 0.5d))) / (d2 - d > d4 - d3 ? d2 - d : d4 - d3);
        LongSparseArray longSparseArray = new LongSparseArray();
        d4 = 0.0d;
        for (WeightedLatLng weightedLatLng : collection) {
            LongSparseArray longSparseArray2;
            int i3 = (int) ((weightedLatLng.getPoint().f3691x - d) * d5);
            int i4 = (int) ((weightedLatLng.getPoint().f3692y - d3) * d5);
            LongSparseArray longSparseArray3 = (LongSparseArray) longSparseArray.get((long) i3);
            if (longSparseArray3 == null) {
                longSparseArray3 = new LongSparseArray();
                longSparseArray.put((long) i3, longSparseArray3);
                longSparseArray2 = longSparseArray3;
            } else {
                longSparseArray2 = longSparseArray3;
            }
            Double d6 = (Double) longSparseArray2.get((long) i4);
            if (d6 == null) {
                d6 = Double.valueOf(0.0d);
            }
            Double valueOf = Double.valueOf(weightedLatLng.intensity + d6.doubleValue());
            longSparseArray2.put((long) i4, valueOf);
            d4 = valueOf.doubleValue() > d4 ? valueOf.doubleValue() : d4;
        }
        return d4;
    }

    static Bitmap m4328a(double[][] dArr, int[] iArr, double d) {
        int i = iArr[iArr.length - 1];
        double length = ((double) (iArr.length - 1)) / d;
        int length2 = dArr.length;
        int[] iArr2 = new int[(length2 * length2)];
        for (int i2 = 0; i2 < length2; i2++) {
            for (int i3 = 0; i3 < length2; i3++) {
                double d2 = dArr[i3][i2];
                int i4 = (i2 * length2) + i3;
                int i5 = (int) (d2 * length);
                if (d2 == 0.0d) {
                    iArr2[i4] = 0;
                } else if (i5 < iArr.length) {
                    iArr2[i4] = iArr[i5];
                } else {
                    iArr2[i4] = i;
                }
            }
        }
        Bitmap createBitmap = Bitmap.createBitmap(length2, length2, Config.ARGB_8888);
        createBitmap.setPixels(iArr2, 0, length2, 0, 0, length2, length2);
        return createBitmap;
    }

    static ay m4329a(Collection<WeightedLatLng> collection) {
        Iterator it = collection.iterator();
        WeightedLatLng weightedLatLng = (WeightedLatLng) it.next();
        double d = weightedLatLng.getPoint().f3691x;
        double d2 = weightedLatLng.getPoint().f3691x;
        double d3 = weightedLatLng.getPoint().f3692y;
        double d4 = weightedLatLng.getPoint().f3692y;
        while (it.hasNext()) {
            weightedLatLng = (WeightedLatLng) it.next();
            double d5 = weightedLatLng.getPoint().f3691x;
            double d6 = weightedLatLng.getPoint().f3692y;
            if (d5 < d) {
                d = d5;
            }
            if (d5 > d2) {
                d2 = d5;
            }
            if (d6 < d3) {
                d3 = d6;
            }
            if (d6 > d4) {
                d4 = d6;
            }
        }
        return new ay(d, d2, d3, d4);
    }

    private static Tile m4330a(Bitmap bitmap) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.PNG, 100, byteArrayOutputStream);
        return new Tile(Opcodes.ACC_NATIVE, Opcodes.ACC_NATIVE, byteArrayOutputStream.toByteArray());
    }

    private void m4331a(Gradient gradient) {
        this.f2699g = gradient;
        this.f2700h = gradient.generateColorMap(this.f2702j);
    }

    private double[] m4332a(int i) {
        int i2 = 11;
        double[] dArr = new double[21];
        for (int i3 = 5; i3 < 11; i3++) {
            dArr[i3] = m4327a(this.f2696d, this.f2697e, i, (int) (1280.0d * Math.pow(2.0d, (double) i3)));
            if (i3 == 5) {
                for (int i4 = 0; i4 < i3; i4++) {
                    dArr[i4] = dArr[i3];
                }
            }
        }
        while (i2 < 21) {
            dArr[i2] = dArr[10];
            i2++;
        }
        return dArr;
    }

    static double[] m4333a(int i, double d) {
        double[] dArr = new double[((i * 2) + 1)];
        for (int i2 = -i; i2 <= i; i2++) {
            dArr[i2 + i] = Math.exp(((double) ((-i2) * i2)) / ((2.0d * d) * d));
        }
        return dArr;
    }

    static double[][] m4334a(double[][] dArr, double[] dArr2) {
        int floor = (int) Math.floor(((double) dArr2.length) / 2.0d);
        int length = dArr.length;
        int i = length - (floor * 2);
        int i2 = (floor + i) - 1;
        double[][] dArr3 = (double[][]) Array.newInstance(Double.TYPE, new int[]{length, length});
        int i3 = 0;
        while (i3 < length) {
            int i4;
            for (i4 = 0; i4 < length; i4++) {
                int i5;
                double d = dArr[i3][i4];
                if (d != 0.0d) {
                    i5 = (i2 < i3 + floor ? i2 : i3 + floor) + 1;
                    int i6 = floor > i3 - floor ? floor : i3 - floor;
                    while (i6 < i5) {
                        double[] dArr4 = dArr3[i6];
                        dArr4[i4] = dArr4[i4] + (dArr2[i6 - (i3 - floor)] * d);
                        i6++;
                    }
                }
            }
            i3++;
        }
        double[][] dArr5 = (double[][]) Array.newInstance(Double.TYPE, new int[]{i, i});
        for (i = floor; i < i2 + 1; i++) {
            i3 = 0;
            while (i3 < length) {
                d = dArr3[i][i3];
                if (d != 0.0d) {
                    i5 = (i2 < i3 + floor ? i2 : i3 + floor) + 1;
                    i4 = floor > i3 - floor ? floor : i3 - floor;
                    while (i4 < i5) {
                        dArr4 = dArr5[i - floor];
                        int i7 = i4 - floor;
                        dArr4[i7] = dArr4[i7] + (dArr2[i4 - (i3 - floor)] * d);
                        i4++;
                    }
                }
                i3++;
            }
        }
        return dArr5;
    }

    private void m4336c(Collection<WeightedLatLng> collection) {
        Collection arrayList = new ArrayList();
        for (WeightedLatLng weightedLatLng : collection) {
            if (weightedLatLng.latLng.latitude < 85.0d && weightedLatLng.latLng.latitude > -85.0d) {
                arrayList.add(weightedLatLng);
            }
        }
        this.f2696d = arrayList;
        this.f2697e = m4329a(this.f2696d);
        this.f2695c = new C0431c(this.f2697e);
        for (WeightedLatLng weightedLatLng2 : this.f2696d) {
            this.f2695c.m4356a(weightedLatLng2);
        }
        this.f2703k = m4332a(this.f2698f);
    }

    private static Collection<WeightedLatLng> m4337d(Collection<LatLng> collection) {
        Collection arrayList = new ArrayList();
        for (LatLng weightedLatLng : collection) {
            arrayList.add(new WeightedLatLng(weightedLatLng));
        }
        return arrayList;
    }

    public Tile getTile(int i, int i2, int i3) {
        double d;
        double pow = WeightedLatLng.DEFAULT_INTENSITY / Math.pow(2.0d, (double) i3);
        double d2 = (((double) this.f2698f) * pow) / 256.0d;
        double d3 = ((2.0d * d2) + pow) / ((double) ((this.f2698f * 2) + Opcodes.ACC_NATIVE));
        double d4 = (((double) i) * pow) - d2;
        double d5 = (((double) (i + 1)) * pow) + d2;
        double d6 = (((double) i2) * pow) - d2;
        double d7 = (pow * ((double) (i2 + 1))) + d2;
        ArrayList arrayList = new ArrayList();
        Collection a;
        if (d4 < 0.0d) {
            a = this.f2695c.m4355a(new ay(WeightedLatLng.DEFAULT_INTENSITY + d4, WeightedLatLng.DEFAULT_INTENSITY, d6, d7));
            d = -1.0d;
        } else if (d5 > WeightedLatLng.DEFAULT_INTENSITY) {
            a = this.f2695c.m4355a(new ay(0.0d, d5 - WeightedLatLng.DEFAULT_INTENSITY, d6, d7));
            d = WeightedLatLng.DEFAULT_INTENSITY;
        } else {
            Object obj = arrayList;
            d = 0.0d;
        }
        ay ayVar = new ay(d4, d5, d6, d7);
        if (!ayVar.m3491a(new ay(this.f2697e.f2142a - d2, this.f2697e.f2144c + d2, this.f2697e.f2143b - d2, d2 + this.f2697e.f2145d))) {
            return TileProvider.NO_TILE;
        }
        Collection<WeightedLatLng> a2 = this.f2695c.m4355a(ayVar);
        if (a2.isEmpty()) {
            return TileProvider.NO_TILE;
        }
        double[][] dArr = (double[][]) Array.newInstance(Double.TYPE, new int[]{(this.f2698f * 2) + Opcodes.ACC_NATIVE, (this.f2698f * 2) + Opcodes.ACC_NATIVE});
        for (WeightedLatLng weightedLatLng : a2) {
            DPoint point = weightedLatLng.getPoint();
            int i4 = (int) ((point.f3691x - d4) / d3);
            int i5 = (int) ((point.f3692y - d6) / d3);
            double[] dArr2 = dArr[i4];
            dArr2[i5] = dArr2[i5] + weightedLatLng.intensity;
        }
        for (WeightedLatLng weightedLatLng2 : r20) {
            point = weightedLatLng2.getPoint();
            i4 = (int) (((point.f3691x + d) - d4) / d3);
            i5 = (int) ((point.f3692y - d6) / d3);
            dArr2 = dArr[i4];
            dArr2[i5] = dArr2[i5] + weightedLatLng2.intensity;
        }
        return m4330a(m4328a(m4334a(dArr, this.f2701i), this.f2700h, this.f2703k[i3]));
    }

    public int getTileHeight() {
        return Opcodes.ACC_NATIVE;
    }

    public int getTileWidth() {
        return Opcodes.ACC_NATIVE;
    }
}
