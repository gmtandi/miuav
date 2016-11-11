package com.fimi.kernel.view.dialog;

import android.animation.Animator;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import com.fimi.kernel.p084e.ae;

public class SampleDialogFragment extends DialogFragment {
    protected ae f5394a;
    private int f5395b;
    private int f5396c;
    private View f5397d;
    private OnCancelListener f5398e;
    private OnDismissListener f5399f;
    private int f5400g;
    private int f5401h;

    public SampleDialogFragment() {
        this.f5398e = null;
        this.f5399f = null;
    }

    public static SampleDialogFragment m8375a(int i, int i2) {
        return m8376a(i, i2, 17);
    }

    public static SampleDialogFragment m8376a(int i, int i2, int i3) {
        SampleDialogFragment sampleDialogFragment = new SampleDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("style", i);
        bundle.putInt("theme", i2);
        bundle.putInt("gravity", i3);
        sampleDialogFragment.setArguments(bundle);
        return sampleDialogFragment;
    }

    public static SampleDialogFragment m8377a(int i, int i2, int i3, int i4) {
        SampleDialogFragment sampleDialogFragment = new SampleDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("style", i);
        bundle.putInt("theme", i2);
        bundle.putInt("gravity", i3);
        bundle.putInt("color", i4);
        sampleDialogFragment.setArguments(bundle);
        return sampleDialogFragment;
    }

    public View m8378a() {
        return this.f5397d;
    }

    public void m8379a(int i) {
        this.f5394a.m8054c(i);
    }

    public void m8380a(OnCancelListener onCancelListener) {
        this.f5398e = onCancelListener;
    }

    public void m8381a(OnDismissListener onDismissListener) {
        this.f5399f = onDismissListener;
    }

    public void m8382a(View view) {
        this.f5397d = view;
    }

    protected void m8383a(boolean z) {
        Window window = getActivity().getWindow();
        LayoutParams attributes = window.getAttributes();
        if (z) {
            attributes.flags |= 67108864;
        } else {
            attributes.flags &= -67108865;
        }
        window.setAttributes(attributes);
    }

    public OnCancelListener m8384b() {
        return this.f5398e;
    }

    public OnDismissListener m8385c() {
        return this.f5399f;
    }

    public void onActivityCreated(Bundle bundle) {
        Window window = getDialog().getWindow();
        window.getAttributes().gravity = this.f5400g;
        window.setLayout(-1, -2);
        window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#88838B8B")));
        super.onActivityCreated(bundle);
    }

    public void onCancel(DialogInterface dialogInterface) {
        if (this.f5398e != null) {
            this.f5398e.onCancel(dialogInterface);
        }
        super.onCancel(dialogInterface);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        dismiss();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f5396c = getArguments().getInt("style");
        this.f5395b = getArguments().getInt("theme");
        this.f5400g = getArguments().getInt("gravity");
        this.f5401h = getArguments().getInt("color");
        setStyle(this.f5396c, this.f5395b);
        this.f5394a = new ae(getActivity());
        if (VERSION.SDK_INT >= 19) {
            m8383a(true);
        }
        this.f5394a.m8046a(true);
        m8379a(this.f5401h);
    }

    public Animator onCreateAnimator(int i, boolean z, int i2) {
        return super.onCreateAnimator(i, z, i2);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return this.f5397d;
    }

    public void onDestroy() {
        super.onDestroy();
        dismiss();
    }

    public void onDismiss(DialogInterface dialogInterface) {
        if (this.f5399f != null) {
            this.f5399f.onDismiss(dialogInterface);
        }
        super.onDismiss(dialogInterface);
    }
}
