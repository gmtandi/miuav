package com.mining.app.zxing.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.widget.Button;
import android.widget.Toast;
import com.fimi.soul.C1205R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.mining.app.zxing.p126a.C2124c;
import com.mining.app.zxing.p127b.C2130a;
import com.mining.app.zxing.p127b.C2136g;
import com.mining.app.zxing.view.ViewfinderView;
import java.io.IOException;
import java.util.Vector;
import org.p122a.p123a.C2915a;

public class MipcaActivityCapture extends Activity implements Callback {
    private static final long f11191h = 200;
    private C2130a f11192a;
    private ViewfinderView f11193b;
    private boolean f11194c;
    private Vector<BarcodeFormat> f11195d;
    private String f11196e;
    private C2136g f11197f;
    private Button f11198g;
    private final OnCompletionListener f11199i;

    public MipcaActivityCapture() {
        this.f11199i = new C2129b(this);
    }

    private void m13097a(SurfaceHolder surfaceHolder) {
        try {
            C2124c.m13071a().m13076a(surfaceHolder);
            if (this.f11192a == null) {
                this.f11192a = new C2130a(this, this.f11195d, this.f11196e);
            }
        } catch (IOException e) {
        } catch (RuntimeException e2) {
        }
    }

    public ViewfinderView m13098a() {
        return this.f11193b;
    }

    public void m13099a(Result result, Bitmap bitmap) {
        this.f11197f.m13110a();
        String text = result.getText();
        if (text.equals(C2915a.f14760f)) {
            Toast.makeText(this, "Scan failed!", 0).show();
        } else {
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putString("result", text);
            intent.putExtras(bundle);
            setResult(-1, intent);
        }
        finish();
    }

    public Handler m13100b() {
        return this.f11192a;
    }

    public void m13101c() {
        this.f11193b.m13112a();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C1205R.layout.activity_insurance_capture);
        C2124c.m13072a(getApplication());
        this.f11193b = (ViewfinderView) findViewById(C1205R.id.viewfinder_view);
        this.f11198g = (Button) findViewById(C1205R.id.return_insurance);
        this.f11194c = false;
        this.f11197f = new C2136g(this);
        this.f11198g.setOnClickListener(new C2128a(this));
    }

    protected void onDestroy() {
        this.f11197f.m13111b();
        super.onDestroy();
    }

    protected void onPause() {
        super.onPause();
        if (this.f11192a != null) {
            this.f11192a.m13103a();
            this.f11192a = null;
        }
        C2124c.m13071a().m13078b();
    }

    protected void onResume() {
        super.onResume();
        SurfaceHolder holder = ((SurfaceView) findViewById(C1205R.id.preview_view)).getHolder();
        if (this.f11194c) {
            m13097a(holder);
        } else {
            holder.addCallback(this);
            holder.setType(3);
        }
        this.f11195d = null;
        this.f11196e = null;
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        if (!this.f11194c) {
            this.f11194c = true;
            m13097a(surfaceHolder);
        }
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.f11194c = false;
    }
}
