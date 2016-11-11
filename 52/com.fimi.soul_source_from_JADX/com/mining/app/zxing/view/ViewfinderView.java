package com.mining.app.zxing.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import com.fimi.soul.C1205R;
import com.fimi.soul.view.photodraweeview.C2020f;
import com.google.zxing.ResultPoint;
import com.mining.app.zxing.p126a.C2124c;
import com.tencent.open.yyb.TitleBar;
import java.util.Collection;
import java.util.HashSet;
import org.codehaus.jackson.smile.SmileConstants;

public final class ViewfinderView extends View {
    private static final int[] f11251b;
    private static final String f11252c = "log";
    private static final long f11253d = 10;
    private static final int f11254e = 255;
    private static final int f11255g = 10;
    private static final int f11256h = 6;
    private static final int f11257i = 2;
    private static final int f11258j = 2;
    private static float f11259k = 0.0f;
    private static final int f11260l = 16;
    private static final int f11261m = 30;
    boolean f11262a;
    private int f11263f;
    private Paint f11264n;
    private int f11265o;
    private int f11266p;
    private Bitmap f11267q;
    private final int f11268r;
    private final int f11269s;
    private final int f11270t;
    private Collection<ResultPoint> f11271u;
    private Collection<ResultPoint> f11272v;
    private int f11273w;
    private int f11274x;
    private Paint f11275y;
    private int f11276z;

    static {
        f11251b = new int[]{0, 64, SmileConstants.TOKEN_PREFIX_TINY_UNICODE, SmileConstants.TOKEN_PREFIX_SMALL_INT, f11254e, SmileConstants.TOKEN_PREFIX_SMALL_INT, SmileConstants.TOKEN_PREFIX_TINY_UNICODE, 64};
    }

    public ViewfinderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        f11259k = context.getResources().getDisplayMetrics().density;
        this.f11263f = (int) (TitleBar.BACKBTN_LEFT_MARGIN * f11259k);
        this.f11264n = new Paint();
        Resources resources = getResources();
        this.f11268r = resources.getColor(C1205R.color.viewfinder_mask);
        this.f11269s = resources.getColor(C1205R.color.result_view);
        this.f11273w = resources.getColor(C1205R.color.viewfinder_laser);
        this.f11276z = resources.getColor(C1205R.color.kuang);
        this.f11270t = resources.getColor(C1205R.color.possible_result_points);
        this.f11271u = new HashSet(5);
        this.f11275y = new Paint();
        this.f11275y.setStyle(Style.STROKE);
        this.f11275y.setColor(this.f11276z);
        int i = (int) ((4.0f * f11259k) / C2020f.f10931a);
        this.f11275y.setStrokeWidth((float) i);
        this.f11264n.setStrokeWidth((float) i);
    }

    public void m13112a() {
        this.f11267q = null;
        invalidate();
    }

    public void m13113a(Bitmap bitmap) {
        this.f11267q = bitmap;
        invalidate();
    }

    public void m13114a(ResultPoint resultPoint) {
        this.f11271u.add(resultPoint);
    }

    public void onDraw(Canvas canvas) {
        Rect e = C2124c.m13071a().m13082e();
        if (e != null) {
            if (!this.f11262a) {
                this.f11262a = true;
                this.f11265o = e.top;
                this.f11266p = e.bottom;
            }
            int width = canvas.getWidth();
            int height = canvas.getHeight();
            this.f11264n.setColor(this.f11267q != null ? this.f11269s : this.f11268r);
            canvas.drawRect(0.0f, 0.0f, (float) width, (float) e.top, this.f11264n);
            canvas.drawRect(0.0f, (float) e.top, (float) e.left, (float) (e.bottom + 1), this.f11264n);
            canvas.drawRect((float) (e.right + 1), (float) e.top, (float) width, (float) (e.bottom + 1), this.f11264n);
            canvas.drawRect(0.0f, (float) (e.bottom + 1), (float) width, (float) height, this.f11264n);
            canvas.drawRect((float) e.left, (float) e.top, (float) e.right, (float) e.bottom, this.f11275y);
            if (this.f11267q != null) {
                this.f11264n.setAlpha(f11254e);
                canvas.drawBitmap(this.f11267q, (float) e.left, (float) e.top, this.f11264n);
                return;
            }
            this.f11265o += f11258j;
            if (this.f11265o >= e.bottom) {
                this.f11265o = e.top;
            }
            this.f11264n.setColor(this.f11273w);
            canvas.drawRect((float) (e.left + f11258j), (float) (this.f11265o - 3), (float) (e.right - 2), (float) (this.f11265o + 3), this.f11264n);
            postInvalidateDelayed(f11253d, e.left, e.top, e.right, e.bottom);
        }
    }
}
