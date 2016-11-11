package com.fimi.soul.module.droneui;

import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGestureListener;
import android.gesture.GestureStroke;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.fimi.soul.C1205R;
import com.fimi.soul.utils.az;
import java.util.ArrayList;
import java.util.List;

public class GestureMapFragment extends Fragment implements OnGestureListener {
    private static final int f8590a = 15;
    private static final int f8591b = 2;
    private double f8592c;
    private GestureOverlayView f8593d;
    private C1756r f8594e;

    private int m11349a(double d) {
        return (int) Math.round(((double) getResources().getDisplayMetrics().density) * d);
    }

    private void m11350a(List<Point> list) {
        float[] fArr = ((GestureStroke) this.f8593d.getGesture().getStrokes().get(0)).points;
        for (int i = 0; i < fArr.length; i += f8591b) {
            list.add(new Point((int) fArr[i], (int) fArr[i + 1]));
        }
    }

    private List<Point> m11351d() {
        List arrayList = new ArrayList();
        m11350a(arrayList);
        return arrayList;
    }

    public void m11352a() {
        this.f8593d.setEnabled(true);
    }

    public void m11353a(GestureOverlayView gestureOverlayView) {
        this.f8593d = gestureOverlayView;
    }

    public void m11354a(C1756r c1756r) {
        this.f8594e = c1756r;
    }

    public void m11355b() {
        this.f8593d.setEnabled(false);
    }

    public GestureOverlayView m11356c() {
        return this.f8593d;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(C1205R.layout.fragment_gesture_map, viewGroup, false);
        this.f8593d = (GestureOverlayView) inflate.findViewById(C1205R.id.overlay1);
        this.f8593d.addOnGestureListener(this);
        this.f8593d.setEnabled(false);
        this.f8593d.setGestureStrokeWidth((float) m11349a(2.0d));
        this.f8592c = (double) m11349a(15.0d);
        return inflate;
    }

    public void onGesture(GestureOverlayView gestureOverlayView, MotionEvent motionEvent) {
    }

    public void onGestureCancelled(GestureOverlayView gestureOverlayView, MotionEvent motionEvent) {
    }

    public void onGestureEnded(GestureOverlayView gestureOverlayView, MotionEvent motionEvent) {
        this.f8593d.setEnabled(false);
        List d = m11351d();
        if (d.size() > 1) {
            d = az.m12303a(d, this.f8592c);
        }
        this.f8594e.m11361a(d);
    }

    public void onGestureStarted(GestureOverlayView gestureOverlayView, MotionEvent motionEvent) {
    }
}
