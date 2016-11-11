package com.fimi.soul.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.text.Layout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.fimi.soul.C1205R;
import com.fimi.soul.view.photodraweeview.C2020f;
import com.tencent.connect.common.Constants;
import com.tencent.open.yyb.TitleBar;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xiaomi.auth.AuthConstants;

public class TunWheel extends View {
    private static final int f10516e = 5;
    private static final int f10517h = 7;
    private static final int f10518j = 8;
    private static final int f10519k = 5;
    private static int f10520l;
    int f10521a;
    Context f10522b;
    private float f10523c;
    private int f10524d;
    private int f10525f;
    private int f10526g;
    private int f10527i;
    private bp f10528m;
    private Boolean f10529n;
    private Bitmap f10530o;

    static {
        f10520l = 20;
    }

    public TunWheel(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f10525f = 0;
        this.f10526g = f10519k;
        this.f10527i = 0;
        this.f10529n = Boolean.valueOf(true);
        this.f10522b = context;
        this.f10523c = getContext().getResources().getDisplayMetrics().density;
        if (((double) this.f10523c) == 1.5d) {
            this.f10521a = (int) (BitmapDescriptorFactory.HUE_CYAN * this.f10523c);
        } else if (this.f10523c == 2.0f) {
            this.f10521a = (int) (this.f10523c * 220.0f);
        } else if (this.f10523c == C2020f.f10931a) {
            this.f10521a = (int) (this.f10523c * 220.0f);
        }
        setBackgroundDrawable(getResources().getDrawable(C1205R.drawable.speedbg));
        this.f10530o = BitmapFactory.decodeResource(getResources(), C1205R.drawable.speedbg);
        if (this.f10530o != null && !this.f10530o.isRecycled()) {
            this.f10530o.recycle();
        }
    }

    private void m12663a() {
        if (this.f10528m != null) {
            this.f10528m.m12800a((float) this.f10525f);
        }
    }

    private void m12664a(Canvas canvas) {
        canvas.save();
        Paint textPaint = new TextPaint(1);
        textPaint.setTextSize(11.0f * this.f10523c);
        float desiredWidth = Layout.getDesiredWidth(Constants.VIA_RESULT_SUCCESS, textPaint);
        Paint paint = new Paint();
        paint.setColor(getResources().getColor(C1205R.color.black));
        canvas.drawRect(4.0f * this.f10523c, (((float) (getHeight() / 2)) - desiredWidth) - (this.f10523c * 4.0f), ((float) getWidth()) - (this.f10523c * C2020f.f10933c), (this.f10523c * 2.0f) + (((float) (getHeight() / 2)) + desiredWidth), paint);
        paint = new Paint();
        paint.setColor(getResources().getColor(C1205R.color.linetun));
        paint.setStyle(Style.STROKE);
        if (this.f10529n.booleanValue()) {
            canvas.drawRect(4.0f * this.f10523c, (((float) (getHeight() / 2)) - desiredWidth) - (this.f10523c * 4.0f), ((float) getWidth()) - (this.f10523c * C2020f.f10933c), (this.f10523c * 2.0f) + (((float) (getHeight() / 2)) + desiredWidth), paint);
        } else {
            canvas.drawRect((this.f10523c * 4.0f) - this.f10523c, (((float) (getHeight() / 2)) - desiredWidth) - (this.f10523c * 4.0f), (((float) getWidth()) - (this.f10523c * C2020f.f10933c)) - this.f10523c, (this.f10523c * 2.0f) + (((float) (getHeight() / 2)) + desiredWidth), paint);
        }
        textPaint.setColor(getResources().getColor(C1205R.color.red));
        float desiredWidth2 = Layout.getDesiredWidth(Constants.VIA_RESULT_SUCCESS, textPaint);
        if (this.f10525f >= 100 && this.f10525f < XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER) {
            canvas.drawText(String.valueOf(((double) this.f10525f) / 10.0d), ((float) f10520l) + (C2020f.f10931a * this.f10523c), (desiredWidth2 / 2.0f) + ((float) (getHeight() / 2)), textPaint);
        } else if (this.f10525f >= XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER) {
            canvas.drawText(String.valueOf(((double) this.f10525f) / 10.0d), (float) f10520l, (desiredWidth2 / 2.0f) + ((float) (getHeight() / 2)), textPaint);
        } else if (this.f10525f < 0 && this.f10525f > -100) {
            canvas.drawText(String.valueOf(((double) this.f10525f) / 10.0d), ((float) f10520l) + (this.f10523c * 4.0f), (desiredWidth2 / 2.0f) + ((float) (getHeight() / 2)), textPaint);
        } else if (this.f10525f < -100 && this.f10525f > -999) {
            canvas.drawText(String.valueOf(((double) this.f10525f) / 10.0d), ((float) f10520l) + (this.f10523c * 2.0f), (desiredWidth2 / 2.0f) + ((float) (getHeight() / 2)), textPaint);
        } else if (this.f10525f <= AuthConstants.ERROR_CONNECT_FAILED) {
            canvas.drawText(String.valueOf(((double) this.f10525f) / 10.0d), ((float) f10520l) - this.f10523c, (desiredWidth2 / 2.0f) + ((float) (getHeight() / 2)), textPaint);
        } else {
            canvas.drawText(String.valueOf(((double) this.f10525f) / 10.0d), ((float) f10520l) + (6.0f * this.f10523c), (desiredWidth2 / 2.0f) + ((float) (getHeight() / 2)), textPaint);
        }
        canvas.restore();
    }

    private void m12665b(Canvas canvas) {
        canvas.save();
        Paint paint = new Paint();
        paint.setStrokeWidth(2.0f);
        paint.setColor(getResources().getColor(C1205R.color.linetun));
        paint.setStrokeWidth(C2020f.f10933c);
        paint.setAntiAlias(true);
        Paint textPaint = new TextPaint(1);
        textPaint.setTextSize(7.0f * this.f10523c);
        textPaint.setColor(getResources().getColor(C1205R.color.linetun));
        int i = this.f10524d;
        float desiredWidth = Layout.getDesiredWidth(Constants.VIA_RESULT_SUCCESS, textPaint);
        f10520l = (int) (7.0f * this.f10523c);
        Paint textPaint2 = new TextPaint(1);
        textPaint2.setTextSize(11.0f * this.f10523c);
        textPaint2.setColor(getResources().getColor(C1205R.color.linetun));
        int i2;
        float f;
        int i3;
        float f2;
        if (this.f10529n.booleanValue()) {
            canvas.drawLine(((float) (getPaddingLeft() + f10520l)) - this.f10523c, (TitleBar.SHAREBTN_RIGHT_MARGIN * this.f10523c) + ((float) getPaddingTop()), ((float) (getPaddingLeft() + f10520l)) - this.f10523c, ((float) getHeight()) - (TitleBar.SHAREBTN_RIGHT_MARGIN * this.f10523c), paint);
            i2 = 0;
            f = 0.0f;
            i3 = 0;
            while (((float) i2) <= ((float) i) - (TitleBar.BACKBTN_LEFT_MARGIN * this.f10523c)) {
                f2 = ((float) ((i / 2) + this.f10527i)) - (((float) (this.f10526g * i3)) * this.f10523c);
                if ((this.f10525f + i3) % f10519k == 0) {
                    canvas.drawLine((float) (f10520l + getPaddingLeft()), f2, (this.f10523c * 8.0f) + ((float) f10520l), f2, paint);
                    canvas.drawText(String.valueOf(((double) (this.f10525f + i3)) / 10.0d), (((float) f10520l) + (this.f10523c * 8.0f)) + (C2020f.f10931a * this.f10523c), f2 + (desiredWidth / 2.0f), textPaint);
                } else {
                    canvas.drawLine((float) (f10520l + getPaddingLeft()), f2, (this.f10523c * 5.0f) + ((float) f10520l), f2, paint);
                }
                f2 = ((float) ((i / 2) + this.f10527i)) + (((float) (this.f10526g * i3)) * this.f10523c);
                if (((float) i2) != ((float) i) - (TitleBar.BACKBTN_LEFT_MARGIN * this.f10523c)) {
                    if ((this.f10525f - i3) % f10519k == 0) {
                        canvas.drawLine((float) (f10520l + getPaddingLeft()), f2, (this.f10523c * 8.0f) + ((float) f10520l), f2, paint);
                        if (f2 < ((float) i) - (23.0f * this.f10523c)) {
                            canvas.drawText(String.valueOf(((double) (this.f10525f - i3)) / 10.0d), (((float) f10520l) + (this.f10523c * 8.0f)) + (C2020f.f10931a * this.f10523c), (desiredWidth / 2.0f) + f2, textPaint);
                        }
                    } else {
                        canvas.drawLine((float) (f10520l + getPaddingLeft()), f2, (this.f10523c * 5.0f) + ((float) f10520l), f2, paint);
                    }
                }
                i3++;
                f = f2;
                i2 = (int) (((float) i2) + (((float) (this.f10526g * 2)) * this.f10523c));
            }
            canvas.drawLine((float) (f10520l + getPaddingLeft()), f - ((float) this.f10527i), 2.0f * (((float) f10520l) + (this.f10523c * 8.0f)), f - ((float) this.f10527i), paint);
            canvas.drawText("m", ((float) f10520l) + (this.f10523c * 8.0f), (((desiredWidth / 2.0f) + f) - (4.0f * this.f10523c)) - ((float) this.f10527i), textPaint2);
        } else {
            canvas.drawLine((2.0f * this.f10523c) + ((float) (getWidth() - f10520l)), (TitleBar.SHAREBTN_RIGHT_MARGIN * this.f10523c) + ((float) getPaddingTop()), (2.0f * this.f10523c) + ((float) (getWidth() - f10520l)), ((float) getHeight()) - (TitleBar.SHAREBTN_RIGHT_MARGIN * this.f10523c), paint);
            i2 = 0;
            f = 0.0f;
            i3 = 0;
            while (((float) i2) <= ((float) i) - (TitleBar.BACKBTN_LEFT_MARGIN * this.f10523c)) {
                f2 = ((float) ((i / 2) + this.f10527i)) - (((float) (this.f10526g * i3)) * this.f10523c);
                if ((this.f10525f + i3) % f10519k == 0) {
                    canvas.drawLine(this.f10523c + ((float) (getWidth() - f10520l)), f2, this.f10523c + (((float) (getWidth() - f10520l)) - (this.f10523c * 8.0f)), f2, paint);
                    if (this.f10525f + i3 >= 100 && this.f10525f + i3 < XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER) {
                        canvas.drawText(String.valueOf(((double) (this.f10525f + i3)) / 10.0d), ((float) f10520l) + (5.0f * this.f10523c), f2 + (desiredWidth / 2.0f), textPaint);
                    } else if (this.f10525f + i3 >= XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER) {
                        canvas.drawText(String.valueOf(((double) (this.f10525f + i3)) / 10.0d), ((float) f10520l) + this.f10523c, f2 + (desiredWidth / 2.0f), textPaint);
                    } else if (this.f10525f + i3 < 0 && this.f10525f + i3 > -100) {
                        canvas.drawText(String.valueOf(((double) (this.f10525f + i3)) / 10.0d), ((float) f10520l) + (7.0f * this.f10523c), f2 + (desiredWidth / 2.0f), textPaint);
                    } else if (this.f10525f + i3 < -100 && this.f10525f + i3 > -999) {
                        canvas.drawText(String.valueOf(((double) (this.f10525f + i3)) / 10.0d), ((float) f10520l) + (C2020f.f10931a * this.f10523c), f2 + (desiredWidth / 2.0f), textPaint);
                    } else if (this.f10525f + i3 <= AuthConstants.ERROR_CONNECT_FAILED) {
                        canvas.drawText(String.valueOf(((double) (this.f10525f + i3)) / 10.0d), ((float) f10520l) - (2.0f * this.f10523c), f2 + (desiredWidth / 2.0f), textPaint);
                    } else {
                        canvas.drawText(String.valueOf(((double) (this.f10525f + i3)) / 10.0d), ((float) (f10520l * 2)) + (2.0f * this.f10523c), f2 + (desiredWidth / 2.0f), textPaint);
                    }
                } else {
                    canvas.drawLine(this.f10523c + ((float) (getWidth() - f10520l)), f2, this.f10523c + (((float) (getWidth() - f10520l)) - (this.f10523c * 5.0f)), f2, paint);
                }
                f2 = ((float) ((i / 2) + this.f10527i)) + (((float) (this.f10526g * i3)) * this.f10523c);
                if (((float) i2) != ((float) i) - (TitleBar.BACKBTN_LEFT_MARGIN * this.f10523c)) {
                    if ((this.f10525f - i3) % f10519k == 0) {
                        canvas.drawLine(this.f10523c + ((float) (getWidth() - f10520l)), f2, this.f10523c + (((float) (getWidth() - f10520l)) - (this.f10523c * 8.0f)), f2, paint);
                        if (f2 < ((float) i) - (23.0f * this.f10523c)) {
                            if (this.f10525f - i3 >= 100 && this.f10525f - i3 < XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER) {
                                canvas.drawText(String.valueOf(((double) (this.f10525f - i3)) / 10.0d), ((float) f10520l) + (5.0f * this.f10523c), (desiredWidth / 2.0f) + f2, textPaint);
                            } else if (this.f10525f - i3 >= XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER) {
                                canvas.drawText(String.valueOf(((double) (this.f10525f - i3)) / 10.0d), ((float) f10520l) + this.f10523c, (desiredWidth / 2.0f) + f2, textPaint);
                            } else if (this.f10525f - i3 < 0 && this.f10525f - i3 > -100) {
                                canvas.drawText(String.valueOf(((double) (this.f10525f - i3)) / 10.0d), ((float) f10520l) + (this.f10523c * 7.0f), (desiredWidth / 2.0f) + f2, textPaint);
                            } else if (this.f10525f - i3 <= -100 && this.f10525f - i3 > -999) {
                                canvas.drawText(String.valueOf(((double) (this.f10525f - i3)) / 10.0d), ((float) f10520l) + (C2020f.f10931a * this.f10523c), (desiredWidth / 2.0f) + f2, textPaint);
                            } else if (this.f10525f - i3 <= AuthConstants.ERROR_CONNECT_FAILED) {
                                canvas.drawText(String.valueOf(((double) (this.f10525f - i3)) / 10.0d), ((float) f10520l) - (2.0f * this.f10523c), (desiredWidth / 2.0f) + f2, textPaint);
                            } else {
                                canvas.drawText(String.valueOf(((double) (this.f10525f - i3)) / 10.0d), ((float) (f10520l * 2)) + (2.0f * this.f10523c), (desiredWidth / 2.0f) + f2, textPaint);
                            }
                        }
                    } else {
                        canvas.drawLine(this.f10523c + ((float) (getWidth() - f10520l)), f2, this.f10523c + (((float) (getWidth() - f10520l)) - (this.f10523c * 5.0f)), f2, paint);
                    }
                }
                i3++;
                i2 = (int) (((float) i2) + (((float) (this.f10526g * 2)) * this.f10523c));
                f = f2;
            }
            canvas.drawLine(this.f10523c + ((float) (getWidth() / 4)), f - ((float) this.f10527i), this.f10523c + ((float) (getWidth() - f10520l)), f - ((float) this.f10527i), paint);
            canvas.drawText("m/s", (8.0f * this.f10523c) + this.f10523c, (((desiredWidth / 2.0f) + f) - (4.0f * this.f10523c)) - ((float) this.f10527i), textPaint2);
        }
        canvas.restore();
    }

    public void m12666a(float f) {
        this.f10525f = (int) (TitleBar.SHAREBTN_RIGHT_MARGIN * f);
        m12663a();
        postInvalidate();
    }

    public void m12667a(int i, int i2) {
        this.f10525f = i;
        this.f10527i = (int) ((((float) i2) * this.f10523c) / 2.0f);
        invalidate();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        m12665b(canvas);
        m12664a(canvas);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.f10524d = getHeight();
        super.onLayout(z, i, i2, i3, i4);
    }

    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(this.f10530o.getWidth(), this.f10521a);
    }

    public void setDirection(Boolean bool) {
        this.f10529n = bool;
    }
}
