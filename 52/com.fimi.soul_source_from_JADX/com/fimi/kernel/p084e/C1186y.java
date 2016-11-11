package com.fimi.kernel.p084e;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.os.Build.VERSION;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.fimi.kernel.C1154b;
import com.fimi.soul.view.photodraweeview.C2020f;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.fimi.kernel.e.y */
public class C1186y {
    public static final int f5353a = Integer.MIN_VALUE;

    public static float m8295a(int i, float f, DisplayMetrics displayMetrics) {
        switch (i) {
            case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                return f;
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                return f * displayMetrics.density;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                return f * displayMetrics.scaledDensity;
            case Type.BYTE /*3*/:
                return (displayMetrics.xdpi * f) * 0.013888889f;
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                return f * displayMetrics.xdpi;
            case Type.INT /*5*/:
                return (displayMetrics.xdpi * f) * 0.03937008f;
            default:
                return 0.0f;
        }
    }

    public static float m8296a(Context context, float f) {
        return C1186y.m8295a(1, f, C1167f.m8115d(context));
    }

    public static int m8297a(int i, int i2, float f) {
        if (f == 0.0f) {
            return 0;
        }
        float f2 = C2020f.f10933c;
        try {
            f2 = Math.min(((float) i) / 720.0f, ((float) i2) / 1280.0f);
        } catch (Exception e) {
        }
        return Math.round((f2 * f) + 0.5f);
    }

    public static int m8298a(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public static void m8299a(Context context, int i) {
        View findViewById = ((Activity) context).findViewById(i);
        if (findViewById instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) findViewById;
        }
    }

    public static void m8300a(Context context, Paint paint, float f) {
        paint.setTextSize((float) C1186y.m8320f(context, f));
    }

    public static void m8301a(Context context, TextPaint textPaint, float f) {
        textPaint.setTextSize((float) C1186y.m8320f(context, f));
    }

    public static void m8302a(View view) {
        LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new LayoutParams(-1, -2);
        }
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(0, 0, layoutParams.width);
        int i = layoutParams.height;
        view.measure(childMeasureSpec, i > 0 ? MeasureSpec.makeMeasureSpec(i, 1073741824) : MeasureSpec.makeMeasureSpec(0, 0));
    }

    public static void m8303a(View view, int i) {
        View findViewById = view.findViewById(i);
        if (findViewById instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) findViewById;
        }
    }

    public static void m8304a(View view, int i, int i2) {
        int e = C1186y.m8318e(view.getContext(), (float) i);
        int e2 = C1186y.m8318e(view.getContext(), (float) i2);
        LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams != null) {
            if (i != f5353a) {
                layoutParams.width = e;
            }
            if (i2 != f5353a) {
                layoutParams.height = e2;
            }
            view.setLayoutParams(layoutParams);
        }
    }

    public static void m8305a(View view, int i, int i2, int i3, int i4) {
        view.setPadding(C1186y.m8318e(view.getContext(), (float) i), C1186y.m8318e(view.getContext(), (float) i2), C1186y.m8318e(view.getContext(), (float) i3), C1186y.m8318e(view.getContext(), (float) i4));
    }

    public static void m8306a(AbsListView absListView, int i, int i2) {
        int b = C1186y.m8311b(absListView, i, i2);
        LayoutParams layoutParams = absListView.getLayoutParams();
        layoutParams.height = b;
        ((MarginLayoutParams) layoutParams).setMargins(0, 0, 0, 0);
        absListView.setLayoutParams(layoutParams);
    }

    public static void m8307a(TextView textView, float f) {
        textView.setTextSize((float) C1186y.m8320f(textView.getContext(), f));
    }

    public static float m8308b(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return (float) displayMetrics.heightPixels;
    }

    public static float m8309b(Context context, float f) {
        return f / C1167f.m8115d(context).density;
    }

    public static int m8310b(View view) {
        C1186y.m8302a(view);
        return view.getMeasuredWidth();
    }

    public static int m8311b(AbsListView absListView, int i, int i2) {
        int i3 = 0;
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(0, 0);
        int makeMeasureSpec2 = MeasureSpec.makeMeasureSpec(0, 0);
        absListView.measure(makeMeasureSpec, makeMeasureSpec2);
        ListAdapter listAdapter = (ListAdapter) absListView.getAdapter();
        if (listAdapter == null) {
            return 0;
        }
        int count = listAdapter.getCount();
        int i4;
        if (absListView instanceof ListView) {
            i4 = 0;
            while (i3 < count) {
                View view = listAdapter.getView(i3, null, absListView);
                view.measure(makeMeasureSpec, makeMeasureSpec2);
                i4 += view.getMeasuredHeight();
                i3++;
            }
            if (count != 0) {
                i2 = i4 + (((ListView) absListView).getDividerHeight() * (count - 1));
            }
        } else if (absListView instanceof GridView) {
            i4 = count % i;
            if (i4 > 0) {
                i4 = 1;
            }
            if (listAdapter.getCount() != 0) {
                View view2 = listAdapter.getView(0, null, absListView);
                view2.measure(makeMeasureSpec, makeMeasureSpec2);
                i4 += count / i;
                i2 = (view2.getMeasuredHeight() * i4) + ((i4 - 1) * i2);
            }
        } else {
            i2 = 0;
        }
        return i2;
    }

    public static void m8312b(View view, int i, int i2, int i3, int i4) {
        int e = C1186y.m8318e(view.getContext(), (float) i);
        int e2 = C1186y.m8318e(view.getContext(), (float) i2);
        int e3 = C1186y.m8318e(view.getContext(), (float) i3);
        int e4 = C1186y.m8318e(view.getContext(), (float) i4);
        if (view.getLayoutParams() instanceof MarginLayoutParams) {
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) view.getLayoutParams();
            if (marginLayoutParams != null) {
                if (i != f5353a) {
                    marginLayoutParams.leftMargin = e;
                }
                if (i3 != f5353a) {
                    marginLayoutParams.rightMargin = e3;
                }
                if (i2 != f5353a) {
                    marginLayoutParams.topMargin = e2;
                }
                if (i4 != f5353a) {
                    marginLayoutParams.bottomMargin = e4;
                }
                view.setLayoutParams(marginLayoutParams);
            }
        }
    }

    public static void m8313b(TextView textView, float f) {
        textView.setTextSize(0, (float) C1186y.m8320f(textView.getContext(), f));
    }

    public static float m8314c(Context context, float f) {
        return C1186y.m8295a(2, f, C1167f.m8115d(context));
    }

    public static int m8315c(View view) {
        C1186y.m8302a(view);
        return view.getMeasuredHeight();
    }

    public static float m8316d(Context context, float f) {
        return f / C1167f.m8115d(context).scaledDensity;
    }

    public static void m8317d(View view) {
        ViewParent parent = view.getParent();
        if (parent != null && (parent instanceof ViewGroup)) {
            ((ViewGroup) parent).removeView(view);
        }
    }

    public static int m8318e(Context context, float f) {
        DisplayMetrics d = C1167f.m8115d(context);
        if (d.scaledDensity > 2.0f) {
            if (d.widthPixels > C1154b.f5234e) {
                f *= 1.3f - (C2020f.f10933c / d.scaledDensity);
            } else if (d.widthPixels < C1154b.f5234e) {
                f *= C2020f.f10933c - (C2020f.f10933c / d.scaledDensity);
            }
        }
        return C1186y.m8297a(d.widthPixels, d.heightPixels, f);
    }

    @SuppressLint({"NewApi"})
    public static void m8319e(View view) {
        int i = f5353a;
        if (view instanceof TextView) {
            TextView textView = (TextView) view;
            C1186y.m8313b(textView, textView.getTextSize());
        }
        LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams != null) {
            int i2 = (layoutParams.width == -2 || layoutParams.width == -1) ? f5353a : layoutParams.width;
            if (!(layoutParams.height == -2 || layoutParams.height == -1)) {
                i = layoutParams.height;
            }
            C1186y.m8304a(view, i2, i);
            C1186y.m8305a(view, view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom());
        }
        if (view.getLayoutParams() instanceof MarginLayoutParams) {
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) view.getLayoutParams();
            if (marginLayoutParams != null) {
                C1186y.m8312b(view, marginLayoutParams.leftMargin, marginLayoutParams.topMargin, marginLayoutParams.rightMargin, marginLayoutParams.bottomMargin);
            }
        }
        if (VERSION.SDK_INT >= 16) {
            i2 = C1186y.m8318e(view.getContext(), (float) view.getMinimumWidth());
            i = C1186y.m8318e(view.getContext(), (float) view.getMinimumHeight());
            view.setMinimumWidth(i2);
            view.setMinimumHeight(i);
        }
    }

    public static int m8320f(Context context, float f) {
        DisplayMetrics d = C1167f.m8115d(context);
        if (d.scaledDensity > 2.0f) {
        }
        return C1186y.m8297a(d.widthPixels, d.heightPixels, f);
    }
}
