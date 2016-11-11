package com.fimi.soul.module.setting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.fimi.kernel.view.button.C1196c;
import com.fimi.kernel.view.button.SwitchButton;
import com.fimi.soul.C1205R;
import com.fimi.soul.biz.camera.C1313t;
import com.fimi.soul.biz.camera.entity.X11RespCmd;
import com.fimi.soul.biz.camera.p088b.C1213e;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.fimi.soul.module.setting.d */
public abstract class C1848d implements C1213e<X11RespCmd> {
    private View f9288a;
    private ViewGroup f9289b;
    private Context f9290c;
    private Map<String, View> f9291d;
    private LayoutInflater f9292e;
    private C1313t f9293f;

    public C1848d(Context context) {
        this.f9291d = new HashMap();
        m11700a(context);
        this.f9292e = LayoutInflater.from(context);
        this.f9288a = this.f9292e.inflate(C1205R.layout.layout_camera_setting_main, null);
        this.f9289b = (ViewGroup) this.f9288a.findViewById(C1205R.id.lv_viewContent);
    }

    private Button m11691a(View view, String str, String str2, OnClickListener onClickListener) {
        ((TextView) view.findViewById(C1205R.id.tv_settingTitle)).setText(str);
        Button button = (Button) view.findViewById(C1205R.id.btn_settingAction);
        button.setOnClickListener(onClickListener);
        button.setText(str2);
        m11710e().addView(view);
        return button;
    }

    private void m11692a(View view, String str) {
        ((TextView) view.findViewById(C1205R.id.tv_settingTitle)).setText(str);
    }

    protected LayoutInflater m11693a() {
        return this.f9292e;
    }

    protected Button m11694a(int i, String str, OnClickListener onClickListener) {
        return m11697a(m11711f().getText(i).toString(), str, onClickListener);
    }

    protected Button m11695a(String str) {
        return m11696a(str, null);
    }

    protected Button m11696a(String str, OnClickListener onClickListener) {
        View inflate = this.f9292e.inflate(C1205R.layout.item_setting_center_button, null);
        Button button = (Button) inflate.findViewById(C1205R.id.btn_settingAction);
        button.setText(str);
        button.setOnClickListener(onClickListener);
        m11710e().addView(inflate);
        return button;
    }

    protected Button m11697a(String str, String str2, OnClickListener onClickListener) {
        return m11691a(m11693a().inflate(C1205R.layout.item_setting_short_button, null), str, str2, onClickListener);
    }

    protected SwitchButton m11698a(String str, boolean z, C1196c c1196c) {
        View inflate = m11693a().inflate(C1205R.layout.item_setting_switch_button, null);
        ((TextView) inflate.findViewById(C1205R.id.tv_settingTitle)).setText(str);
        SwitchButton switchButton = (SwitchButton) inflate.findViewById(C1205R.id.sb_settingAction);
        switchButton.setSwitchState(z);
        switchButton.setOnSwitchListener(c1196c);
        m11710e().addView(inflate);
        return switchButton;
    }

    protected C1849e m11699a(String str, String str2) {
        View inflate = this.f9292e.inflate(C1205R.layout.item_setting_double_button, null);
        C1849e c1849e = new C1849e(this);
        Button button = (Button) inflate.findViewById(C1205R.id.btn_settingActionFirst);
        button.setText(str);
        Button button2 = (Button) inflate.findViewById(C1205R.id.btn_settingActionSecond);
        button2.setText(str2);
        c1849e.m11713a(button);
        c1849e.m11715b(button2);
        m11710e().addView(inflate);
        return c1849e;
    }

    protected void m11700a(Context context) {
        this.f9290c = context;
    }

    protected void m11701a(LayoutInflater layoutInflater) {
        this.f9292e = layoutInflater;
    }

    protected void m11702a(ViewGroup viewGroup) {
        this.f9289b = viewGroup;
    }

    public void m11703a(C1313t c1313t) {
        this.f9293f = c1313t;
    }

    protected View m11704b() {
        return this.f9288a;
    }

    protected Button m11705b(String str, String str2, OnClickListener onClickListener) {
        return m11691a(m11693a().inflate(C1205R.layout.item_setting_long_button, null), str, str2, onClickListener);
    }

    protected EditText m11706b(String str, String str2) {
        View inflate = m11693a().inflate(C1205R.layout.item_setting_edit, null);
        m11692a(inflate, str);
        EditText editText = (EditText) inflate.findViewById(C1205R.id.et_settingAction);
        editText.setText(str2);
        m11710e().addView(inflate);
        return editText;
    }

    public abstract View m11707c();

    protected TextView m11708c(String str, String str2) {
        View inflate = m11693a().inflate(C1205R.layout.item_setting_text, null);
        m11692a(inflate, str);
        TextView textView = (TextView) inflate.findViewById(C1205R.id.tv_settingAction);
        textView.setText(str2);
        m11710e().addView(inflate);
        return textView;
    }

    public C1313t m11709d() {
        return this.f9293f;
    }

    public ViewGroup m11710e() {
        return this.f9289b;
    }

    protected Context m11711f() {
        return this.f9290c;
    }
}
