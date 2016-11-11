package com.p017b.p018a;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.util.Xml;
import android.view.animation.AnimationUtils;
import org.xmlpull.v1.XmlPullParser;

/* renamed from: com.b.a.c */
public class C0621c {
    private static final int[] f3835a;
    private static final int f3836b = 0;
    private static final int[] f3837c;
    private static final int f3838d = 0;
    private static final int[] f3839e;
    private static final int f3840f = 0;
    private static final int f3841g = 1;
    private static final int f3842h = 2;
    private static final int f3843i = 3;
    private static final int f3844j = 4;
    private static final int f3845k = 5;
    private static final int f3846l = 6;
    private static final int f3847m = 7;
    private static final int f3848n = 0;
    private static final int f3849o = 0;

    static {
        int[] iArr = new int[f3841g];
        iArr[f3840f] = 16843490;
        f3835a = iArr;
        iArr = new int[f3841g];
        iArr[f3840f] = 16843489;
        f3837c = iArr;
        f3839e = new int[]{16843073, 16843160, 16843198, 16843199, 16843200, 16843486, 16843487, 16843488};
    }

    public static C0616a m5568a(Context context, int i) {
        NotFoundException notFoundException;
        XmlResourceParser xmlResourceParser = null;
        try {
            xmlResourceParser = context.getResources().getAnimation(i);
            C0616a a = C0621c.m5569a(context, (XmlPullParser) xmlResourceParser);
            if (xmlResourceParser != null) {
                xmlResourceParser.close();
            }
            return a;
        } catch (Throwable e) {
            notFoundException = new NotFoundException("Can't load animation resource ID #0x" + Integer.toHexString(i));
            notFoundException.initCause(e);
            throw notFoundException;
        } catch (Throwable e2) {
            notFoundException = new NotFoundException("Can't load animation resource ID #0x" + Integer.toHexString(i));
            notFoundException.initCause(e2);
            throw notFoundException;
        } catch (Throwable th) {
            if (xmlResourceParser != null) {
                xmlResourceParser.close();
            }
        }
    }

    private static C0616a m5569a(Context context, XmlPullParser xmlPullParser) {
        return C0621c.m5570a(context, xmlPullParser, Xml.asAttributeSet(xmlPullParser), null, f3840f);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.p017b.p018a.C0616a m5570a(android.content.Context r9, org.xmlpull.v1.XmlPullParser r10, android.util.AttributeSet r11, com.p017b.p018a.C0623e r12, int r13) {
        /*
        r3 = 0;
        r5 = 0;
        r6 = r10.getDepth();
        r2 = r3;
        r1 = r3;
    L_0x0008:
        r0 = r10.next();
        r4 = 3;
        if (r0 != r4) goto L_0x0015;
    L_0x000f:
        r4 = r10.getDepth();
        if (r4 <= r6) goto L_0x0093;
    L_0x0015:
        r4 = 1;
        if (r0 == r4) goto L_0x0093;
    L_0x0018:
        r4 = 2;
        if (r0 != r4) goto L_0x0008;
    L_0x001b:
        r0 = r10.getName();
        r1 = "objectAnimator";
        r1 = r0.equals(r1);
        if (r1 == 0) goto L_0x0039;
    L_0x0027:
        r1 = com.p017b.p018a.C0621c.m5572a(r9, r11);
    L_0x002b:
        if (r12 == 0) goto L_0x00c0;
    L_0x002d:
        if (r2 != 0) goto L_0x00bd;
    L_0x002f:
        r0 = new java.util.ArrayList;
        r0.<init>();
    L_0x0034:
        r0.add(r1);
    L_0x0037:
        r2 = r0;
        goto L_0x0008;
    L_0x0039:
        r1 = "animator";
        r1 = r0.equals(r1);
        if (r1 == 0) goto L_0x0046;
    L_0x0041:
        r1 = com.p017b.p018a.C0621c.m5571a(r9, r11, r3);
        goto L_0x002b;
    L_0x0046:
        r1 = "set";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x0076;
    L_0x004e:
        r1 = new com.b.a.e;
        r1.<init>();
        r0 = f3835a;
        r7 = r9.obtainStyledAttributes(r11, r0);
        r0 = new android.util.TypedValue;
        r0.<init>();
        r7.getValue(r5, r0);
        r4 = r0.type;
        r8 = 16;
        if (r4 != r8) goto L_0x0074;
    L_0x0067:
        r0 = r0.data;
        r4 = r0;
    L_0x006a:
        r0 = r1;
        r0 = (com.p017b.p018a.C0623e) r0;
        com.p017b.p018a.C0621c.m5570a(r9, r10, r11, r0, r4);
        r7.recycle();
        goto L_0x002b;
    L_0x0074:
        r4 = r5;
        goto L_0x006a;
    L_0x0076:
        r0 = new java.lang.RuntimeException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Unknown animator name: ";
        r1 = r1.append(r2);
        r2 = r10.getName();
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x0093:
        if (r12 == 0) goto L_0x00b8;
    L_0x0095:
        if (r2 == 0) goto L_0x00b8;
    L_0x0097:
        r0 = r2.size();
        r3 = new com.p017b.p018a.C0616a[r0];
        r4 = r2.iterator();
    L_0x00a1:
        r0 = r4.hasNext();
        if (r0 == 0) goto L_0x00b3;
    L_0x00a7:
        r0 = r4.next();
        r0 = (com.p017b.p018a.C0616a) r0;
        r2 = r5 + 1;
        r3[r5] = r0;
        r5 = r2;
        goto L_0x00a1;
    L_0x00b3:
        if (r13 != 0) goto L_0x00b9;
    L_0x00b5:
        r12.m5590a(r3);
    L_0x00b8:
        return r1;
    L_0x00b9:
        r12.m5593b(r3);
        goto L_0x00b8;
    L_0x00bd:
        r0 = r2;
        goto L_0x0034;
    L_0x00c0:
        r0 = r2;
        goto L_0x0037;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.b.a.c.a(android.content.Context, org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, com.b.a.e, int):com.b.a.a");
    }

    private static as m5571a(Context context, AttributeSet attributeSet, as asVar) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f3839e);
        long j = (long) obtainStyledAttributes.getInt(f3841g, f3840f);
        long j2 = (long) obtainStyledAttributes.getInt(f3842h, f3840f);
        int i = obtainStyledAttributes.getInt(f3847m, f3840f);
        if (asVar == null) {
            asVar = new as();
        }
        Object obj = i == 0 ? f3841g : null;
        TypedValue peekValue = obtainStyledAttributes.peekValue(f3845k);
        Object obj2 = peekValue != null ? f3841g : f3840f;
        int i2 = obj2 != null ? peekValue.type : f3840f;
        TypedValue peekValue2 = obtainStyledAttributes.peekValue(f3846l);
        Object obj3 = peekValue2 != null ? f3841g : f3840f;
        int i3 = obj3 != null ? peekValue2.type : f3840f;
        if ((obj2 != null && i2 >= 28 && i2 <= 31) || (obj3 != null && i3 >= 28 && i3 <= 31)) {
            obj = null;
            asVar.m5523a(new C0630l());
        }
        if (obj != null) {
            if (obj2 != null) {
                float dimension = i2 == f3845k ? obtainStyledAttributes.getDimension(f3845k, 0.0f) : obtainStyledAttributes.getFloat(f3845k, 0.0f);
                if (obj3 != null) {
                    float dimension2 = i3 == f3845k ? obtainStyledAttributes.getDimension(f3846l, 0.0f) : obtainStyledAttributes.getFloat(f3846l, 0.0f);
                    float[] fArr = new float[f3842h];
                    fArr[f3840f] = dimension;
                    fArr[f3841g] = dimension2;
                    asVar.m5525a(fArr);
                } else {
                    float[] fArr2 = new float[f3841g];
                    fArr2[f3840f] = dimension;
                    asVar.m5525a(fArr2);
                }
            } else {
                float[] fArr3 = new float[f3841g];
                fArr3[f3840f] = i3 == f3845k ? obtainStyledAttributes.getDimension(f3846l, 0.0f) : obtainStyledAttributes.getFloat(f3846l, 0.0f);
                asVar.m5525a(fArr3);
            }
        } else if (obj2 != null) {
            i2 = i2 == f3845k ? (int) obtainStyledAttributes.getDimension(f3845k, 0.0f) : (i2 < 28 || i2 > 31) ? obtainStyledAttributes.getInt(f3845k, f3840f) : obtainStyledAttributes.getColor(f3845k, f3840f);
            if (obj3 != null) {
                i = i3 == f3845k ? (int) obtainStyledAttributes.getDimension(f3846l, 0.0f) : (i3 < 28 || i3 > 31) ? obtainStyledAttributes.getInt(f3846l, f3840f) : obtainStyledAttributes.getColor(f3846l, f3840f);
                int[] iArr = new int[f3842h];
                iArr[f3840f] = i2;
                iArr[f3841g] = i;
                asVar.m5526a(iArr);
            } else {
                int[] iArr2 = new int[f3841g];
                iArr2[f3840f] = i2;
                asVar.m5526a(iArr2);
            }
        } else if (obj3 != null) {
            i = i3 == f3845k ? (int) obtainStyledAttributes.getDimension(f3846l, 0.0f) : (i3 < 28 || i3 > 31) ? obtainStyledAttributes.getInt(f3846l, f3840f) : obtainStyledAttributes.getColor(f3846l, f3840f);
            int[] iArr3 = new int[f3841g];
            iArr3[f3840f] = i;
            asVar.m5526a(iArr3);
        }
        asVar.m5537d(j);
        asVar.m5521a(j2);
        if (obtainStyledAttributes.hasValue(f3843i)) {
            asVar.m5520a(obtainStyledAttributes.getInt(f3843i, f3840f));
        }
        if (obtainStyledAttributes.hasValue(f3844j)) {
            asVar.m5531b(obtainStyledAttributes.getInt(f3844j, f3841g));
        }
        i = obtainStyledAttributes.getResourceId(f3840f, f3840f);
        if (i > 0) {
            asVar.m5522a(AnimationUtils.loadInterpolator(context, i));
        }
        obtainStyledAttributes.recycle();
        return asVar;
    }

    private static C0640v m5572a(Context context, AttributeSet attributeSet) {
        as c0640v = new C0640v();
        C0621c.m5571a(context, attributeSet, c0640v);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f3837c);
        c0640v.m5682a(obtainStyledAttributes.getString(f3840f));
        obtainStyledAttributes.recycle();
        return c0640v;
    }
}
