package com.fimi.soul.module.droneFragment;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.TextView;
import com.fimi.soul.C1205R;
import com.fimi.soul.biz.camera.C1314u;
import com.fimi.soul.media.player.widget.FimiVideoView;
import com.fimi.soul.view.photodraweeview.C2020f;

/* renamed from: com.fimi.soul.module.droneFragment.a */
public class C1685a implements OnTouchListener {
    private static View f8152d;
    DisplayMetrics f8153a;
    private FimiVideoView f8154b;
    private String f8155c;
    private Activity f8156e;
    private int f8157f;
    private int f8158g;
    private View f8159h;
    private TextView f8160i;
    private float f8161j;
    private float f8162k;

    public C1685a(Activity activity, View view) {
        this.f8155c = C1314u.f5879f;
        this.f8153a = new DisplayMetrics();
        ((WindowManager) activity.getSystemService("window")).getDefaultDisplay().getRealMetrics(this.f8153a);
        this.f8161j = (float) this.f8153a.widthPixels;
        this.f8162k = (float) this.f8153a.heightPixels;
        m11007a(view);
        this.f8156e = activity;
        this.f8159h = view;
        m11014c();
    }

    private void m11007a(View view) {
        this.f8154b = (FimiVideoView) view.findViewById(C1205R.id.ids_video_view);
        this.f8154b.setKeepScreenOn(true);
        this.f8154b.setZOrderMediaOverlay(true);
        this.f8154b.setOnErrorListener(new C1688b(this));
        f8152d = view.findViewById(C1205R.id.control_cantairn);
        f8152d.setVisibility(0);
        view.setOnTouchListener(this);
        this.f8160i = (TextView) view.findViewById(C1205R.id.gc_error_text);
    }

    public Activity m11009a() {
        return this.f8156e;
    }

    public void m11010a(String str) {
        this.f8155c = str;
    }

    public void m11011a(boolean z) {
        LayoutParams layoutParams = this.f8159h.getLayoutParams();
        if (this.f8157f <= 10 || this.f8158g <= 10) {
            layoutParams.height = this.f8153a.heightPixels;
            layoutParams.width = this.f8153a.widthPixels;
        } else {
            layoutParams.height = this.f8158g;
            layoutParams.width = this.f8157f;
        }
        m11016e();
        this.f8154b.setHideSurfaceView(false);
        this.f8159h.setLayoutParams(layoutParams);
    }

    public String m11012b() {
        return this.f8155c;
    }

    public void m11013b(String str) {
        if (str == null) {
            this.f8160i.setVisibility(8);
            return;
        }
        this.f8160i.setVisibility(0);
        this.f8160i.setText(str);
    }

    public void m11014c() {
        LayoutParams layoutParams = this.f8159h.getLayoutParams();
        this.f8158g = layoutParams.height;
        this.f8157f = layoutParams.width;
        layoutParams.height = 1;
        layoutParams.width = 1;
        this.f8159h.setLayoutParams(layoutParams);
        f8152d.setVisibility(0);
        this.f8154b.setHideSurfaceView(true);
        m11017f();
    }

    public void m11015d() {
    }

    public void m11016e() {
        this.f8154b.setVisibility(0);
        this.f8154b.setDecodeType(0);
        this.f8154b.setVideoPath(this.f8155c);
        this.f8154b.start();
        this.f8154b.setOnPreparedListener(new C1689c(this));
        this.f8154b.setOnInfoListener(new C1690d(this));
    }

    public void m11017f() {
        f8152d.setVisibility(0);
        if (this.f8154b.isPlaying()) {
            this.f8154b.stopPlayback();
            this.f8154b.release(true);
        }
    }

    public void m11018g() {
        f8152d.setVisibility(0);
        this.f8154b.setVisibility(8);
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        return x < this.f8161j / C2020f.f10931a || x > (this.f8161j * 2.0f) / C2020f.f10931a || y < this.f8162k / C2020f.f10931a || y > (this.f8162k * 2.0f) / C2020f.f10931a;
    }
}
