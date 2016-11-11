package p147m.framework.ui.widget.asyncview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.fimi.kernel.C1154b;
import com.fimi.soul.module.setting.newhand.ae;
import java.util.Random;
import p147m.framework.p149b.C2861e;
import p147m.framework.p149b.C2863g;

/* renamed from: m.framework.ui.widget.asyncview.AsyncImageView */
public class AsyncImageView extends ImageView implements Callback, C2864a, C2865b {
    public static final int f14604a = 17170445;
    private static Bitmap f14605b = null;
    private static final int f14606c = 1;
    private static final Random f14607d;
    private static String f14608e;
    private String f14609f;
    private int f14610g;
    private C2873j f14611h;

    static {
        f14607d = new Random();
    }

    public AsyncImageView(Context context) {
        super(context);
        m16533a(context);
    }

    public AsyncImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m16533a(context);
    }

    public AsyncImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m16533a(context);
    }

    private Bitmap m16532a(int i) {
        switch (i) {
            case f14604a /*17170445*/:
                if (f14605b == null) {
                    f14605b = BitmapFactory.decodeResource(getResources(), f14604a);
                }
                return f14605b;
            default:
                return BitmapFactory.decodeResource(getResources(), i);
        }
    }

    private void m16533a(Context context) {
        C2861e.m16507a();
        if (TextUtils.isEmpty(f14608e)) {
            f14608e = C2863g.m16525a(getContext(), C1154b.f5231b);
        }
        C2866c.m16538a(f14608e);
        setOnImageGotListener(C2874k.f14639a);
    }

    public void m16534a(String str) {
        m16535a(str, 0);
    }

    public void m16535a(String str, int i) {
        this.f14609f = str;
        this.f14610g = i;
        if (C2863g.m16526a(str)) {
            setImageResource(i);
            return;
        }
        Bitmap b = C2866c.m16541b(str);
        if (b == null || b.isRecycled()) {
            if (i > 0) {
                setImageBitmap(m16532a(i));
            }
            C2866c.m16539a(str, this);
            return;
        }
        setImageBitmap(b);
    }

    public void m16536a(String str, Bitmap bitmap) {
        if (this.f14611h != null) {
            bitmap = this.f14611h.m16568a(this, bitmap, str);
        }
        Message message = new Message();
        message.what = f14606c;
        message.obj = new Object[]{str, bitmap};
        C2861e.m16511a(message, (long) f14607d.nextInt(ae.f9482j), (Callback) this);
    }

    public String getUrl() {
        return this.f14609f;
    }

    public boolean handleMessage(Message message) {
        if (message.what == f14606c) {
            Object obj = ((Object[]) message.obj)[0];
            Object obj2 = ((Object[]) message.obj)[f14606c];
            if (obj2 == null || obj == null || !obj.equals(this.f14609f)) {
                setImageResource(this.f14610g);
            } else {
                setImageBitmap((Bitmap) obj2);
            }
        }
        return false;
    }

    public void setOnImageGotListener(C2873j c2873j) {
        this.f14611h = c2873j;
    }
}
