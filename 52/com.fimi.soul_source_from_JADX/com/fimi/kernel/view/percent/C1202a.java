package com.fimi.kernel.view.percent;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.TextView;
import com.fimi.kernel.C1087R;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.codehaus.jackson.util.MinimalPrettyPrinter;

/* renamed from: com.fimi.kernel.view.percent.a */
public class C1202a {
    private static final String f5412a = "PercentLayout";
    private static final String f5413c = "^(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)%([wh]?)$";
    private final ViewGroup f5414b;

    public C1202a(ViewGroup viewGroup) {
        this.f5414b = viewGroup;
    }

    public static C1203b m8401a(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C1087R.styleable.PercentLayout_Layout);
        C1203b e = C1202a.m8416e(obtainStyledAttributes, C1202a.m8413c(obtainStyledAttributes, C1202a.m8410b(obtainStyledAttributes, C1202a.m8415d(obtainStyledAttributes, C1202a.m8402a(obtainStyledAttributes, null)))));
        Log.d(f5412a, "constructed: " + e);
        obtainStyledAttributes.recycle();
        if (Log.isLoggable(f5412a, 3)) {
            Log.d(f5412a, "constructed: " + e);
        }
        return e;
    }

    private static C1203b m8402a(TypedArray typedArray, C1203b c1203b) {
        C1204c a = C1202a.m8404a(typedArray, C1087R.styleable.PercentLayout_Layout_layout_widthPercent, true);
        if (a != null) {
            if (Log.isLoggable(f5412a, 2)) {
                Log.v(f5412a, "percent width: " + a.f5433a);
            }
            c1203b = C1202a.m8403a(c1203b);
            c1203b.f5415a = a;
        }
        a = C1202a.m8404a(typedArray, C1087R.styleable.PercentLayout_Layout_layout_heightPercent, false);
        if (a == null) {
            return c1203b;
        }
        if (Log.isLoggable(f5412a, 2)) {
            Log.v(f5412a, "percent height: " + a.f5433a);
        }
        c1203b = C1202a.m8403a(c1203b);
        c1203b.f5416b = a;
        return c1203b;
    }

    @NonNull
    private static C1203b m8403a(C1203b c1203b) {
        return c1203b != null ? c1203b : new C1203b();
    }

    private static C1204c m8404a(TypedArray typedArray, int i, boolean z) {
        return C1202a.m8405a(typedArray.getString(i), z);
    }

    private static C1204c m8405a(String str, boolean z) {
        boolean z2 = true;
        if (str == null) {
            return null;
        }
        Matcher matcher = Pattern.compile(f5413c).matcher(str);
        if (matcher.matches()) {
            int length = str.length();
            String group = matcher.group(1);
            String substring = str.substring(length - 1);
            float parseFloat = Float.parseFloat(group) / 100.0f;
            if ((!z || substring.equals("h")) && !substring.equals("w")) {
                z2 = false;
            }
            return new C1204c(parseFloat, z2);
        }
        throw new RuntimeException("the value of layout_xxxPercent invalid! ==>" + str);
    }

    private void m8406a(int i, int i2, View view, C1203b c1203b) {
        int paddingLeft = view.getPaddingLeft();
        int paddingRight = view.getPaddingRight();
        int paddingTop = view.getPaddingTop();
        int paddingBottom = view.getPaddingBottom();
        C1204c c1204c = c1203b.f5428n;
        if (c1204c != null) {
            paddingLeft = (int) (((float) (c1204c.f5434b ? i : i2)) * c1204c.f5433a);
        }
        c1204c = c1203b.f5429o;
        if (c1204c != null) {
            paddingRight = (int) (((float) (c1204c.f5434b ? i : i2)) * c1204c.f5433a);
        }
        c1204c = c1203b.f5430p;
        if (c1204c != null) {
            paddingTop = (int) (((float) (c1204c.f5434b ? i : i2)) * c1204c.f5433a);
        }
        c1204c = c1203b.f5431q;
        if (c1204c != null) {
            if (!c1204c.f5434b) {
                i = i2;
            }
            paddingBottom = (int) (((float) i) * c1204c.f5433a);
        }
        view.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
    }

    public static void m8407a(LayoutParams layoutParams, TypedArray typedArray, int i, int i2) {
        layoutParams.width = typedArray.getLayoutDimension(i, 0);
        layoutParams.height = typedArray.getLayoutDimension(i2, 0);
    }

    private void m8408a(String str, int i, int i2, View view, Class cls, C1204c c1204c) {
        if (c1204c != null) {
            Method method = cls.getMethod(str, new Class[]{Integer.TYPE});
            method.setAccessible(true);
            if (!c1204c.f5434b) {
                i = i2;
            }
            method.invoke(view, new Object[]{Integer.valueOf((int) (((float) i) * c1204c.f5433a))});
        }
    }

    private static boolean m8409a(View view, C1203b c1203b) {
        return c1203b != null && c1203b.f5415a != null && c1203b.f5432r != null && (ViewCompat.getMeasuredWidthAndState(view) & ViewCompat.MEASURED_STATE_MASK) == ViewCompat.MEASURED_STATE_TOO_SMALL && c1203b.f5415a.f5433a >= 0.0f && c1203b.f5432r.width == -2;
    }

    private static C1203b m8410b(TypedArray typedArray, C1203b c1203b) {
        C1204c a = C1202a.m8404a(typedArray, C1087R.styleable.PercentLayout_Layout_layout_textSizePercent, false);
        if (a == null) {
            return c1203b;
        }
        if (Log.isLoggable(f5412a, 2)) {
            Log.v(f5412a, "percent text size: " + a.f5433a);
        }
        c1203b = C1202a.m8403a(c1203b);
        c1203b.f5423i = a;
        return c1203b;
    }

    private void m8411b(int i, int i2, View view, C1203b c1203b) {
        try {
            Class cls = view.getClass();
            m8408a("setMaxWidth", i, i2, view, cls, c1203b.f5424j);
            m8408a("setMaxHeight", i, i2, view, cls, c1203b.f5425k);
            m8408a("setMinWidth", i, i2, view, cls, c1203b.f5426l);
            m8408a("setMinHeight", i, i2, view, cls, c1203b.f5427m);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e2) {
            e2.printStackTrace();
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
        }
    }

    private static boolean m8412b(View view, C1203b c1203b) {
        return c1203b != null && c1203b.f5416b != null && c1203b.f5432r != null && (ViewCompat.getMeasuredHeightAndState(view) & ViewCompat.MEASURED_STATE_MASK) == ViewCompat.MEASURED_STATE_TOO_SMALL && c1203b.f5416b.f5433a >= 0.0f && c1203b.f5432r.height == -2;
    }

    private static C1203b m8413c(TypedArray typedArray, C1203b c1203b) {
        C1204c a = C1202a.m8404a(typedArray, C1087R.styleable.PercentLayout_Layout_layout_maxWidthPercent, true);
        if (a != null) {
            C1202a.m8403a(c1203b);
            c1203b.f5424j = a;
        }
        a = C1202a.m8404a(typedArray, C1087R.styleable.PercentLayout_Layout_layout_maxHeightPercent, false);
        if (a != null) {
            C1202a.m8403a(c1203b);
            c1203b.f5425k = a;
        }
        a = C1202a.m8404a(typedArray, C1087R.styleable.PercentLayout_Layout_layout_minWidthPercent, true);
        if (a != null) {
            C1202a.m8403a(c1203b);
            c1203b.f5426l = a;
        }
        a = C1202a.m8404a(typedArray, C1087R.styleable.PercentLayout_Layout_layout_minHeightPercent, false);
        if (a != null) {
            C1202a.m8403a(c1203b);
            c1203b.f5427m = a;
        }
        return c1203b;
    }

    private void m8414c(int i, int i2, View view, C1203b c1203b) {
        C1204c c1204c = c1203b.f5423i;
        if (c1204c != null) {
            if (!c1204c.f5434b) {
                i = i2;
            }
            float f = (float) ((int) (c1204c.f5433a * ((float) i)));
            if (view instanceof TextView) {
                ((TextView) view).setTextSize(0, f);
            }
        }
    }

    private static C1203b m8415d(TypedArray typedArray, C1203b c1203b) {
        C1204c a = C1202a.m8404a(typedArray, C1087R.styleable.PercentLayout_Layout_layout_marginPercent, true);
        if (a != null) {
            if (Log.isLoggable(f5412a, 2)) {
                Log.v(f5412a, "percent margin: " + a.f5433a);
            }
            c1203b = C1202a.m8403a(c1203b);
            c1203b.f5417c = a;
            c1203b.f5418d = a;
            c1203b.f5419e = a;
            c1203b.f5420f = a;
        }
        a = C1202a.m8404a(typedArray, C1087R.styleable.PercentLayout_Layout_layout_marginLeftPercent, true);
        if (a != null) {
            if (Log.isLoggable(f5412a, 2)) {
                Log.v(f5412a, "percent left margin: " + a.f5433a);
            }
            c1203b = C1202a.m8403a(c1203b);
            c1203b.f5417c = a;
        }
        a = C1202a.m8404a(typedArray, C1087R.styleable.PercentLayout_Layout_layout_marginTopPercent, false);
        if (a != null) {
            if (Log.isLoggable(f5412a, 2)) {
                Log.v(f5412a, "percent top margin: " + a.f5433a);
            }
            c1203b = C1202a.m8403a(c1203b);
            c1203b.f5418d = a;
        }
        a = C1202a.m8404a(typedArray, C1087R.styleable.PercentLayout_Layout_layout_marginRightPercent, true);
        if (a != null) {
            if (Log.isLoggable(f5412a, 2)) {
                Log.v(f5412a, "percent right margin: " + a.f5433a);
            }
            c1203b = C1202a.m8403a(c1203b);
            c1203b.f5419e = a;
        }
        a = C1202a.m8404a(typedArray, C1087R.styleable.PercentLayout_Layout_layout_marginBottomPercent, false);
        if (a != null) {
            if (Log.isLoggable(f5412a, 2)) {
                Log.v(f5412a, "percent bottom margin: " + a.f5433a);
            }
            c1203b = C1202a.m8403a(c1203b);
            c1203b.f5420f = a;
        }
        a = C1202a.m8404a(typedArray, C1087R.styleable.PercentLayout_Layout_layout_marginStartPercent, true);
        if (a != null) {
            if (Log.isLoggable(f5412a, 2)) {
                Log.v(f5412a, "percent start margin: " + a.f5433a);
            }
            c1203b = C1202a.m8403a(c1203b);
            c1203b.f5421g = a;
        }
        a = C1202a.m8404a(typedArray, C1087R.styleable.PercentLayout_Layout_layout_marginEndPercent, true);
        if (a == null) {
            return c1203b;
        }
        if (Log.isLoggable(f5412a, 2)) {
            Log.v(f5412a, "percent end margin: " + a.f5433a);
        }
        c1203b = C1202a.m8403a(c1203b);
        c1203b.f5422h = a;
        return c1203b;
    }

    private static C1203b m8416e(TypedArray typedArray, C1203b c1203b) {
        C1204c a = C1202a.m8404a(typedArray, C1087R.styleable.PercentLayout_Layout_layout_paddingPercent, true);
        if (a != null) {
            c1203b = C1202a.m8403a(c1203b);
            c1203b.f5428n = a;
            c1203b.f5429o = a;
            c1203b.f5431q = a;
            c1203b.f5430p = a;
        }
        a = C1202a.m8404a(typedArray, C1087R.styleable.PercentLayout_Layout_layout_paddingLeftPercent, true);
        if (a != null) {
            c1203b = C1202a.m8403a(c1203b);
            c1203b.f5428n = a;
        }
        a = C1202a.m8404a(typedArray, C1087R.styleable.PercentLayout_Layout_layout_paddingRightPercent, true);
        if (a != null) {
            c1203b = C1202a.m8403a(c1203b);
            c1203b.f5429o = a;
        }
        a = C1202a.m8404a(typedArray, C1087R.styleable.PercentLayout_Layout_layout_paddingTopPercent, true);
        if (a != null) {
            c1203b = C1202a.m8403a(c1203b);
            c1203b.f5430p = a;
        }
        a = C1202a.m8404a(typedArray, C1087R.styleable.PercentLayout_Layout_layout_paddingBottomPercent, true);
        if (a == null) {
            return c1203b;
        }
        c1203b = C1202a.m8403a(c1203b);
        c1203b.f5431q = a;
        return c1203b;
    }

    public void m8417a() {
        int childCount = this.f5414b.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = this.f5414b.getChildAt(i);
            LayoutParams layoutParams = childAt.getLayoutParams();
            if (Log.isLoggable(f5412a, 3)) {
                Log.d(f5412a, "should restore " + childAt + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + layoutParams);
            }
            if (layoutParams instanceof C1201d) {
                C1203b a = ((C1201d) layoutParams).m8394a();
                if (Log.isLoggable(f5412a, 3)) {
                    Log.d(f5412a, "using " + a);
                }
                if (a != null) {
                    if (layoutParams instanceof MarginLayoutParams) {
                        a.m8422a((MarginLayoutParams) layoutParams);
                    } else {
                        a.m8420a(layoutParams);
                    }
                }
            }
        }
    }

    public void m8418a(int i, int i2) {
        if (Log.isLoggable(f5412a, 3)) {
            Log.d(f5412a, "adjustChildren: " + this.f5414b + " widthMeasureSpec: " + MeasureSpec.toString(i) + " heightMeasureSpec: " + MeasureSpec.toString(i2));
        }
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        if (Log.isLoggable(f5412a, 3)) {
            Log.d(f5412a, "widthHint = " + size + " , heightHint = " + size2);
        }
        int childCount = this.f5414b.getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = this.f5414b.getChildAt(i3);
            LayoutParams layoutParams = childAt.getLayoutParams();
            if (Log.isLoggable(f5412a, 3)) {
                Log.d(f5412a, "should adjust " + childAt + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + layoutParams);
            }
            if (layoutParams instanceof C1201d) {
                C1203b a = ((C1201d) layoutParams).m8394a();
                if (Log.isLoggable(f5412a, 3)) {
                    Log.d(f5412a, "using " + a);
                }
                if (a != null) {
                    m8414c(size, size2, childAt, a);
                    m8406a(size, size2, childAt, a);
                    m8411b(size, size2, childAt, a);
                    if (layoutParams instanceof MarginLayoutParams) {
                        a.m8423a((MarginLayoutParams) layoutParams, size, size2);
                    } else {
                        a.m8421a(layoutParams, size, size2);
                    }
                }
            }
        }
    }

    public boolean m8419b() {
        int childCount = this.f5414b.getChildCount();
        boolean z = false;
        for (int i = 0; i < childCount; i++) {
            View childAt = this.f5414b.getChildAt(i);
            LayoutParams layoutParams = childAt.getLayoutParams();
            if (layoutParams instanceof C1201d) {
                C1203b a = ((C1201d) layoutParams).m8394a();
                if (a != null) {
                    boolean z2;
                    if (C1202a.m8409a(childAt, a)) {
                        layoutParams.width = -2;
                        z2 = true;
                    } else {
                        z2 = z;
                    }
                    if (C1202a.m8412b(childAt, a)) {
                        layoutParams.height = -2;
                        z = true;
                    } else {
                        z = z2;
                    }
                }
            }
        }
        return z;
    }
}
