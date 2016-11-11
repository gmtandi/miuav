package com.fimi.soul.module.setting;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.fimi.soul.C1205R;
import com.fimi.soul.base.BaseActivity;
import com.fimi.soul.base.C1236a;
import com.fimi.soul.media.player.FimiMediaMeta;
import com.fimi.soul.utils.C1969i;
import com.fimi.soul.utils.be;
import com.fimi.soul.view.aq;
import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import org.codehaus.jackson.org.objectweb.asm.signature.SignatureVisitor;
import org.codehaus.jackson.util.MinimalPrettyPrinter;
import org.p122a.p123a.C2915a;

public class FlyLogsActivity extends BaseActivity implements OnClickListener {
    ImageView f9052a;
    TextView f9053b;
    TextView f9054c;
    TextView f9055d;
    Button f9056e;
    Button f9057f;
    ListView f9058g;
    C1894x f9059h;
    List<aa> f9060i;
    List<aa> f9061j;
    RelativeLayout f9062k;
    RelativeLayout f9063l;
    LinearLayout f9064m;
    ImageButton f9065n;

    public FlyLogsActivity() {
        this.f9060i = new ArrayList();
        this.f9061j = new ArrayList();
    }

    List<aa> m11583a(String str, List<aa> list) {
        File file = (str == null || C2915a.f14760f.equals(str)) ? new File(C1969i.m12491n()) : new File(str);
        if (file != null && file.exists()) {
            for (File file2 : file2.listFiles()) {
                if (file2.isDirectory()) {
                    m11583a(file2.getAbsolutePath(), list);
                } else {
                    aa aaVar = new aa();
                    aaVar.m11663a(file2.getName());
                    aaVar.m11661a(0);
                    aaVar.m11668b(file2.getAbsolutePath());
                    aaVar.m11667b(0);
                    try {
                        if (new FileInputStream(new File(aaVar.m11669c())).getChannel().size() / FimiMediaMeta.AV_CH_SIDE_RIGHT > FimiMediaMeta.AV_CH_SIDE_RIGHT) {
                            DecimalFormat decimalFormat = new DecimalFormat("######0.00");
                            aaVar.m11670c(String.format("%s %s", new Object[]{decimalFormat.format(((double) r8) / 1024.0d), "M"}));
                        } else {
                            aaVar.m11670c(String.format("%d %s", new Object[]{Integer.valueOf((int) (new FileInputStream(new File(aaVar.m11669c())).getChannel().size() / FimiMediaMeta.AV_CH_SIDE_RIGHT)), "K"}));
                        }
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(C1236a.f5614l, Locale.CHINA);
                        String replace = file2.getName().replace(".txt", C2915a.f14760f).replace("&synced", C2915a.f14760f);
                        String[] split = replace.split(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                        if (split != null && split.length > 0) {
                            replace = split[0] + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + split[1].replace(SignatureVisitor.SUPER, ':');
                        }
                        aaVar.m11662a(simpleDateFormat.parse(replace).getTime());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    list.add(aaVar);
                }
            }
        }
        return list;
    }

    @SuppressLint({"StringFormatMatches"})
    public void onClick(View view) {
        switch (view.getId()) {
            case C1205R.id.back_btn:
                finish();
            case C1205R.id.unall_btn:
                this.f9062k.setVisibility(8);
                this.f9063l.setVisibility(0);
                this.f9064m.setVisibility(8);
                this.f9059h.m11904a(false);
                this.f9059h.m11908b(false);
                this.f9059h.notifyDataSetChanged();
                this.f9061j.clear();
            case C1205R.id.all_btn:
                if (this.f9059h.getCount() == this.f9061j.size()) {
                    this.f9061j.clear();
                    this.f9059h.m11908b(false);
                    this.f9056e.setText(C1205R.string.select_all);
                } else {
                    this.f9056e.setText(C1205R.string.undo_select_all);
                    this.f9059h.m11908b(true);
                    this.f9061j.clear();
                    if (this.f9060i != null && this.f9060i.size() > 0) {
                        for (aa add : this.f9060i) {
                            this.f9061j.add(add);
                        }
                    }
                }
                this.f9054c.setText(String.format(getResources().getString(C1205R.string.has_select_num, new Object[]{Integer.valueOf(this.f9061j.size())}), new Object[0]));
            case C1205R.id.del_btn:
                if (this.f9061j == null || this.f9061j.size() <= 0) {
                    Toast.makeText(this, getString(C1205R.string.make_sure_selected), 1).show();
                } else {
                    new aq(this).m12748a(getString(C1205R.string.ensure_delete_selected)).m12754c(17).m12753b(getString(C1205R.string.delete), new C1890t(this)).m12749a(getString(C1205R.string.cancel), new C1889s(this)).m12747a(getResources().getColor(C1205R.color.dialog_ensure_hot)).m12746a().show();
                }
            default:
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C1205R.layout.fly_logs_layout);
        this.f9052a = (ImageView) findViewById(C1205R.id.back_btn);
        this.f9052a.setOnClickListener(this);
        this.f9053b = (TextView) findViewById(C1205R.id.fly_log_title);
        this.f9058g = (ListView) findViewById(C1205R.id.log_list);
        this.f9058g.setOverScrollMode(2);
        this.f9054c = (TextView) findViewById(C1205R.id.del_title);
        this.f9056e = (Button) findViewById(C1205R.id.all_btn);
        this.f9057f = (Button) findViewById(C1205R.id.unall_btn);
        this.f9056e.setOnClickListener(this);
        this.f9057f.setOnClickListener(this);
        this.f9065n = (ImageButton) findViewById(C1205R.id.del_btn);
        this.f9065n.setOnClickListener(this);
        this.f9055d = (TextView) findViewById(C1205R.id.del_des);
        this.f9062k = (RelativeLayout) findViewById(C1205R.id.del_layout);
        this.f9063l = (RelativeLayout) findViewById(C1205R.id.title_layout);
        this.f9064m = (LinearLayout) findViewById(C1205R.id.bottom_layout);
        be.m12357a(getAssets(), getWindow().getDecorView());
        be.m12368b(getAssets(), this.f9053b, this.f9054c, this.f9055d);
    }

    protected void onResume() {
        super.onResume();
        this.f9060i.clear();
        this.f9060i = m11583a(C1969i.m12490m(), this.f9060i);
        Collections.sort(this.f9060i, new C1891u(this));
        this.f9059h.m11903a(this.f9060i);
    }

    protected void onStart() {
        super.onStart();
        this.f9059h = new C1894x(this, this.f9060i, this.f9058g);
        this.f9058g.setAdapter(this.f9059h);
        this.f9058g.setOnItemLongClickListener(new C1887q(this));
        this.f9058g.setOnItemClickListener(new C1888r(this));
    }

    protected void onStop() {
        super.onStop();
        this.f9059h.m11906b();
        this.f9059h = null;
    }
}
