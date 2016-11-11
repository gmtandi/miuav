package com.fimi.kernel.view;

import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.DialogInterface.OnClickListener;
import java.util.Calendar;

/* renamed from: com.fimi.kernel.view.b */
public interface C1192b {
    void m8336a();

    void m8337a(int i);

    void m8338a(int i, int i2);

    void m8339a(OnDateSetListener onDateSetListener, Calendar calendar);

    void m8340a(OnTimeSetListener onTimeSetListener, Calendar calendar);

    void m8341a(String str);

    void m8342a(String str, OnClickListener onClickListener);

    Dialog m8343b();

    void m8344b(int i);

    void m8345b(String str);

    void m8346b(String str, OnClickListener onClickListener);

    CharSequence m8347c(int i);

    void m8348c();

    void m8349c(String str);
}
