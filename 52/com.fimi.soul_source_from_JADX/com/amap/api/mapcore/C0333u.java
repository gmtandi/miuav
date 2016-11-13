package com.amap.api.mapcore;

import android.graphics.Color;
import com.fimi.soul.biz.camera.C1314u;
import com.fimi.soul.view.photodraweeview.C2020f;
import com.tencent.open.yyb.TitleBar;
import java.nio.FloatBuffer;
import javax.microedition.khronos.opengles.GL10;

/* renamed from: com.amap.api.mapcore.u */
class C0333u {
    C0333u() {
    }

    public static void m3341a(GL10 gl10, int i, int i2, FloatBuffer floatBuffer, float f, int i3) {
        C0333u.m3344c(gl10, 3, i2, floatBuffer, f, i3);
    }

    public static void m3342a(GL10 gl10, int i, int i2, FloatBuffer floatBuffer, float f, FloatBuffer floatBuffer2, int i3, int i4) {
        C0333u.m3344c(gl10, 4, i, floatBuffer2, C2020f.f10933c, i4);
        C0333u.m3344c(gl10, 2, i2, floatBuffer, f, i3);
    }

    public static void m3343b(GL10 gl10, int i, int i2, FloatBuffer floatBuffer, float f, int i3) {
        C0333u.m3344c(gl10, 6, i, floatBuffer, C2020f.f10933c, i3);
        C0333u.m3344c(gl10, 2, i2, floatBuffer, f, i3);
    }

    private static void m3344c(GL10 gl10, int i, int i2, FloatBuffer floatBuffer, float f, int i3) {
        if (f != 0.0f) {
            gl10.glPushMatrix();
            gl10.glColor4f(C2020f.f10933c, C2020f.f10933c, C2020f.f10933c, C2020f.f10933c);
            gl10.glEnable(3042);
            gl10.glDisable(2929);
            gl10.glBlendFunc(C1314u.f5859L, 771);
            gl10.glDisable(3553);
            gl10.glEnableClientState(32884);
            float alpha = ((float) Color.alpha(i2)) / 255.0f;
            float red = ((float) Color.red(i2)) / 255.0f;
            float green = ((float) Color.green(i2)) / 255.0f;
            float blue = ((float) Color.blue(i2)) / 255.0f;
            gl10.glEnable(32925);
            gl10.glLineWidth(f);
            gl10.glVertexPointer(3, 5126, 0, floatBuffer);
            gl10.glColor4f(red, green, blue, alpha);
            gl10.glDrawArrays(i, 0, i3);
            gl10.glDisable(32925);
            gl10.glEnable(2832);
            gl10.glHint(3153, 4354);
            if (f >= TitleBar.SHAREBTN_RIGHT_MARGIN) {
                f = 6.0f;
            } else if (f >= 5.0f) {
                f -= 2.0f;
            } else if (f >= 2.0f) {
                f -= C2020f.f10933c;
            }
            gl10.glColor4f(red, green, blue, alpha / 4.0f);
            gl10.glPointSize(f);
            gl10.glDrawArrays(0, 1, i3 - 2);
            gl10.glDisable(2832);
            gl10.glDisableClientState(32884);
            gl10.glDisableClientState(32888);
            gl10.glDisable(3042);
            gl10.glPopMatrix();
        }
    }
}
