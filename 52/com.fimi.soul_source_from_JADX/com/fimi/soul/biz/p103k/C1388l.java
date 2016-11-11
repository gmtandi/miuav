package com.fimi.soul.biz.p103k;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import com.fimi.kernel.p084e.ah;
import com.fimi.soul.biz.p098j.C1330i;
import com.fimi.soul.biz.p098j.C1375e;
import com.fimi.soul.biz.p102i.C1357d;
import com.fimi.soul.entity.FdsMsg;
import com.fimi.soul.entity.PlaneMsg;
import com.xiaomi.infra.galaxy.fds.android.model.ObjectMetadata;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

/* renamed from: com.fimi.soul.biz.k.l */
public class C1388l implements Callback {
    private static C1388l f6233a = null;
    private static final int f6234b = 0;
    private static final int f6235c = 1;
    private static final int f6236d = 2;
    private static final int f6237e = 3;
    private static final int f6238f = 4;
    private C1357d f6239g;
    private Handler f6240h;
    private HashMap<Integer, C1330i> f6241i;
    private Context f6242j;

    static {
        f6233a = null;
    }

    public C1388l(Context context) {
        this.f6242j = null;
        this.f6239g = new C1357d(context);
        this.f6240h = new Handler(context.getMainLooper(), this);
        this.f6242j = context;
        this.f6241i = new HashMap();
    }

    public static C1388l m9322a(Context context) {
        if (f6233a == null) {
            f6233a = new C1388l(context);
        }
        return f6233a;
    }

    public void m9324a(Bitmap bitmap, C1375e c1375e) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.JPEG, 100, byteArrayOutputStream);
        InputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength((long) byteArrayOutputStream.toByteArray().length);
        m9329a(byteArrayInputStream, objectMetadata, c1375e);
    }

    public void m9325a(Bitmap bitmap, C1330i c1330i) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.JPEG, 100, byteArrayOutputStream);
        InputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength((long) byteArrayOutputStream.toByteArray().length);
        m9330a(byteArrayInputStream, objectMetadata, c1330i);
    }

    public void m9326a(C1330i c1330i) {
        this.f6241i.put(Integer.valueOf(f6236d), c1330i);
        ah.m8077b(new C1389m(this, f6236d, null, null, null));
    }

    public void m9327a(File file, C1375e c1375e) {
        this.f6241i.put(Integer.valueOf(f6238f), c1375e);
        ah.m8077b(new C1389m(this, f6238f, null, file, null));
    }

    public void m9328a(File file, C1330i c1330i) {
        this.f6241i.put(Integer.valueOf(f6234b), c1330i);
        ah.m8077b(new C1389m(this, f6234b, null, file, null));
    }

    public void m9329a(InputStream inputStream, ObjectMetadata objectMetadata, C1375e c1375e) {
        this.f6241i.put(Integer.valueOf(f6237e), c1375e);
        ah.m8077b(new C1389m(this, f6237e, inputStream, null, objectMetadata));
    }

    public void m9330a(InputStream inputStream, ObjectMetadata objectMetadata, C1330i c1330i) {
        this.f6241i.put(Integer.valueOf(f6235c), c1330i);
        ah.m8077b(new C1389m(this, f6235c, inputStream, null, objectMetadata));
    }

    public boolean handleMessage(Message message) {
        if (message.obj != null && (message.obj instanceof PlaneMsg)) {
            ((C1330i) this.f6241i.get(Integer.valueOf(message.what))).m8951a((PlaneMsg) message.obj);
        } else if (message.obj != null && (message.obj instanceof FdsMsg)) {
            ((C1375e) this.f6241i.get(Integer.valueOf(message.what))).m9174a(((FdsMsg) message.obj).getTransferred(), ((FdsMsg) message.obj).getTotal(), ((FdsMsg) message.obj).getFilePath());
        }
        return false;
    }
}
