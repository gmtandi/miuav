package com.fimi.soul.module.setting;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.fimi.soul.C1205R;
import com.fimi.soul.biz.p103k.C1390n;
import com.fimi.soul.biz.p103k.at;
import com.fimi.soul.utils.be;
import com.fimi.soul.view.ImageLoadingView;
import java.io.File;
import java.util.List;
import org.codehaus.jackson.org.objectweb.asm.signature.SignatureVisitor;
import org.codehaus.jackson.util.MinimalPrettyPrinter;

/* renamed from: com.fimi.soul.module.setting.x */
public class C1894x extends BaseAdapter implements C1390n {
    Context f9622a;
    List<aa> f9623b;
    at f9624c;
    ListView f9625d;
    boolean f9626e;
    C1896z f9627f;

    public C1894x(Context context, List<aa> list, ListView listView) {
        this.f9626e = false;
        this.f9622a = context;
        this.f9623b = list;
        this.f9625d = listView;
        this.f9624c = at.m9227a(this.f9622a);
        this.f9624c.m9234a((C1390n) this);
    }

    private void m11899a(String str, int i, int i2) {
        if (this.f9623b != null && this.f9623b.size() > 0) {
            for (aa aaVar : this.f9623b) {
                if (aaVar.m11669c().equals(str)) {
                    aaVar.m11661a(i);
                    if (i == 2) {
                        aaVar.m11663a(aaVar.m11666b().replace(".", "&synced."));
                    }
                    aaVar.m11667b(i2);
                }
            }
        }
    }

    public void m11900a(int i) {
        aa aaVar = (aa) this.f9623b.get(i);
        if (aaVar != null) {
            ((aa) this.f9623b.get(i)).m11664a(!aaVar.m11665a());
        }
        notifyDataSetChanged();
    }

    public void m11901a(int i, String str) {
        int i2;
        if (i > 0) {
            ImageLoadingView imageLoadingView = (ImageLoadingView) this.f9625d.findViewWithTag("log_" + str);
            if (imageLoadingView != null) {
                imageLoadingView.m12631a();
                imageLoadingView.setSweepAngle((float) i);
            }
            TextView textView = (TextView) this.f9625d.findViewWithTag("percent_" + str);
            if (textView != null) {
                if (i >= 100 || i <= 0) {
                    textView.setText(String.format("%s", new Object[]{this.f9622a.getString(C1205R.string.fly_finish_over)}));
                    ImageView imageView = (ImageView) this.f9625d.findViewWithTag("load_" + str);
                    if (imageView != null) {
                        imageView.setImageResource(C1205R.drawable.icon_upload_ok);
                    }
                    m11902a(new File(str));
                    i2 = 2;
                    m11899a(str, i2, i);
                    this.f9625d.invalidateViews();
                }
                textView.setText(String.format("%s | %s%s", new Object[]{this.f9622a.getString(C1205R.string.fly_uploading), Integer.valueOf(i), "%"}));
                i2 = 1;
                m11899a(str, i2, i);
                this.f9625d.invalidateViews();
            }
        }
        i2 = 1;
        m11899a(str, i2, i);
        this.f9625d.invalidateViews();
    }

    void m11902a(File file) {
        if (file.exists()) {
            file.renameTo(new File(file.getAbsolutePath().substring(0, file.getAbsolutePath().lastIndexOf("/") + 1) + file.getName().replace(".", "&synced.")));
        }
    }

    public void m11903a(List<aa> list) {
        if (list != null) {
            this.f9623b = list;
            notifyDataSetChanged();
        }
    }

    public void m11904a(boolean z) {
        this.f9626e = z;
    }

    public boolean m11905a() {
        return this.f9626e;
    }

    public void m11906b() {
        if (this.f9624c != null) {
            this.f9624c.m9234a(null);
        }
    }

    public void m11907b(List<aa> list) {
        synchronized (this.f9623b) {
            if (list != null) {
                if (list.size() > 0) {
                    for (aa aaVar : list) {
                        for (aa aaVar2 : this.f9623b) {
                            if (aaVar.m11666b().equals(aaVar2.m11666b())) {
                                this.f9623b.remove(aaVar2);
                                break;
                            }
                        }
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    public void m11908b(boolean z) {
        for (aa a : this.f9623b) {
            a.m11664a(z);
        }
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.f9623b != null ? this.f9623b.size() : 0;
    }

    public Object getItem(int i) {
        return this.f9623b != null ? (aa) this.f9623b.get(i) : null;
    }

    public long getItemId(int i) {
        return 0;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            this.f9627f = new C1896z(this);
            view = LinearLayout.inflate(this.f9622a, C1205R.layout.fly_logs_item, null);
            this.f9627f.f9630a = (TextView) view.findViewById(C1205R.id.log_name);
            this.f9627f.f9631b = (TextView) view.findViewById(C1205R.id.log_size);
            this.f9627f.f9632c = (TextView) view.findViewById(C1205R.id.upload_status);
            this.f9627f.f9633d = (ImageLoadingView) view.findViewById(C1205R.id.upload_icon);
            this.f9627f.f9634e = (ImageView) view.findViewById(C1205R.id.upload_v);
            this.f9627f.f9636g = (RelativeLayout) view.findViewById(C1205R.id.upload_layout);
            this.f9627f.f9635f = (ImageView) view.findViewById(C1205R.id.select_icon);
            view.setTag(this.f9627f);
        } else {
            this.f9627f = (C1896z) view.getTag();
        }
        be.m12359a(this.f9622a.getAssets(), this.f9627f.f9630a, this.f9627f.f9631b, this.f9627f.f9632c);
        this.f9627f.f9633d.setTag("log_" + ((aa) this.f9623b.get(i)).m11669c());
        this.f9627f.f9632c.setTag("percent_" + ((aa) this.f9623b.get(i)).m11669c());
        this.f9627f.f9634e.setTag("load_" + ((aa) this.f9623b.get(i)).m11669c());
        if (this.f9626e) {
            this.f9627f.f9636g.setVisibility(8);
            this.f9627f.f9635f.setVisibility(0);
            this.f9627f.f9632c.setVisibility(8);
        } else {
            this.f9627f.f9636g.setVisibility(0);
            this.f9627f.f9635f.setVisibility(8);
            this.f9627f.f9632c.setVisibility(0);
        }
        aa aaVar = (aa) this.f9623b.get(i);
        if (aaVar != null) {
            this.f9627f.f9635f.setSelected(aaVar.f9206g);
            this.f9627f.f9631b.setText(aaVar.m11672e());
            this.f9627f.f9633d.setPaintColor(this.f9622a.getResources().getColor(C1205R.color.gray));
            String b = aaVar.m11666b();
            if (b.contains("-") && b.contains(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR)) {
                String[] split = b.split(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                if (split != null && split.length > 0) {
                    String[] split2 = split[0].split("-");
                    b = this.f9622a.getResources().getConfiguration().locale.getCountry().equals("CN") ? split2[0] + this.f9622a.getString(C1205R.string.year) + split2[1] + this.f9622a.getString(C1205R.string.month) + split2[2] + this.f9622a.getString(C1205R.string.day) + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + split[1].replace(SignatureVisitor.SUPER, ':') : split2[1] + "/" + split2[2] + "/" + split2[0];
                }
            }
            if (b.contains("&synced.")) {
                this.f9627f.f9633d.m12631a();
                this.f9627f.f9633d.setSweepAngle(100.0f);
                this.f9627f.f9634e.setImageResource(C1205R.drawable.icon_upload_ok);
                this.f9627f.f9632c.setText(this.f9622a.getResources().getString(C1205R.string.fly_finish_over));
                this.f9627f.f9630a.setText(String.format("%s", new Object[]{b.replace("&synced.", ".")}));
                aaVar.m11661a(2);
            } else {
                this.f9627f.f9634e.setImageResource(C1205R.drawable.icon_upload_start);
                this.f9627f.f9630a.setText(String.format("%s", new Object[]{b}));
                if (aaVar.m11671d() == 0) {
                    aaVar.m11661a(0);
                    this.f9627f.f9633d.setSweepAngle(0.0f);
                    this.f9627f.f9632c.setText(this.f9622a.getResources().getString(C1205R.string.wait_upload));
                } else if (aaVar.m11671d() == 1) {
                    aaVar.m11661a(1);
                    this.f9627f.f9633d.m12631a();
                    this.f9627f.f9633d.setSweepAngle((float) aaVar.m11674g());
                    this.f9627f.f9632c.setText(String.format("%s | %s%s", new Object[]{this.f9622a.getString(C1205R.string.fly_uploading), Integer.valueOf(aaVar.m11674g()), "%"}));
                }
            }
        }
        this.f9627f.f9633d.setTag(C1205R.id.tag_first, aaVar);
        this.f9627f.f9633d.setOnClickListener(new C1895y(this, aaVar));
        return view;
    }
}
