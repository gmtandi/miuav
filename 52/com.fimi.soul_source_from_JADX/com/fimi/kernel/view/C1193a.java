package com.fimi.kernel.view;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.DialogInterface.OnClickListener;
import android.widget.Toast;
import com.fimi.kernel.C1087R;
import com.fimi.kernel.C1189f;
import com.fimi.kernel.C1190g;
import com.fimi.kernel.view.dialog.C1199b;
import java.util.Calendar;
import org.p122a.p123a.C2915a;

/* renamed from: com.fimi.kernel.view.a */
class C1193a implements C1192b {
    private C1199b f5367a;
    private ProgressDialog f5368b;

    private Activity m8350d() {
        return C1189f.m8334d();
    }

    public void m8351a() {
        m8360b(C2915a.f14760f);
    }

    public void m8352a(int i) {
        Toast.makeText(m8350d(), m8350d().getResources().getString(i), 0).show();
    }

    public void m8353a(int i, int i2) {
        if (this.f5368b != null) {
            this.f5368b.setMax(i2);
            this.f5368b.setProgress(i);
        }
    }

    public void m8354a(OnDateSetListener onDateSetListener, Calendar calendar) {
        new DatePickerDialog(m8350d(), onDateSetListener, calendar.get(1), calendar.get(2), calendar.get(5)).show();
    }

    public void m8355a(OnTimeSetListener onTimeSetListener, Calendar calendar) {
        new TimePickerDialog(m8350d(), onTimeSetListener, calendar.get(11), calendar.get(12), true).show();
    }

    public void m8356a(String str) {
        Toast.makeText(m8350d(), str, 0).show();
    }

    public void m8357a(String str, OnClickListener onClickListener) {
        new Builder(m8350d()).setTitle(m8362c(C1087R.string.message)).setMessage(str).setPositiveButton(m8362c(C1087R.string.confirm), onClickListener).show();
    }

    public Dialog m8358b() {
        return this.f5368b != null ? this.f5368b : this.f5367a != null ? this.f5367a : null;
    }

    public void m8359b(int i) {
        if (this.f5368b != null) {
            this.f5368b.setMax(100);
            this.f5368b.setProgress(i);
        }
    }

    public void m8360b(String str) {
        if (m8350d() != null) {
            this.f5367a = new C1199b(m8350d());
            this.f5367a.setProgressStyle(0);
            this.f5367a.m8388a(str);
            this.f5367a.setTitle(str);
            this.f5367a.show();
        }
    }

    public void m8361b(String str, OnClickListener onClickListener) {
        new Builder(m8350d()).setTitle(m8362c(C1087R.string.message)).setMessage(str).setPositiveButton(m8362c(C1087R.string.confirm), onClickListener).setNegativeButton(m8362c(C1087R.string.cancel), onClickListener).show();
    }

    public CharSequence m8362c(int i) {
        return m8350d().getResources().getText(i);
    }

    public void m8363c() {
        if (this.f5368b != null) {
            this.f5368b.dismiss();
        }
        if (this.f5367a != null) {
            this.f5367a.dismiss();
        }
    }

    public void m8364c(String str) {
        this.f5368b = new ProgressDialog(m8350d());
        this.f5368b.setProgressStyle(1);
        this.f5368b.setMessage(str);
        this.f5368b.setMax(C1190g.f5358a);
        this.f5368b.setCancelable(false);
        this.f5368b.show();
    }
}
