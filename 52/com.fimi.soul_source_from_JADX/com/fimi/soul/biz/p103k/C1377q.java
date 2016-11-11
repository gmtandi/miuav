package com.fimi.soul.biz.p103k;

import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.Log;
import com.fimi.soul.base.C1236a;
import com.fimi.soul.biz.p098j.C1330i;
import com.fimi.soul.entity.PlaneMsg;
import com.fimi.soul.utils.NetUtil;
import com.fimi.soul.utils.ai;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

/* renamed from: com.fimi.soul.biz.k.q */
public abstract class C1377q implements Callback {
    List<NameValuePair> f6065a;
    public String f6066b;
    public String f6067c;
    public String f6068d;
    public PlaneMsg f6069e;
    public HashMap<Integer, C1330i> f6070f;
    private Context f6071g;
    private Handler f6072h;

    public C1377q(Context context) {
        this.f6065a = new ArrayList();
        this.f6069e = null;
        this.f6071g = context;
        this.f6072h = new Handler(this);
        this.f6070f = new HashMap();
    }

    public String m9211a() {
        return this.f6068d;
    }

    public void m9212a(Object obj) {
        for (Field field : obj.getClass().getDeclaredFields()) {
            String name = field.getName();
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            String obj2 = field.getGenericType().toString();
            Method method = obj.getClass().getMethod("get" + name, new Class[0]);
            if (obj2 == null || !"java.util.List<java.lang.String>".equals(obj2)) {
                name = (String) method.invoke(obj, new Object[0]);
                if (name != null && name.length() > 0) {
                    this.f6065a.add(new BasicNameValuePair(field.getName(), name));
                }
            } else {
                List<String> list = (List) method.invoke(obj, new Object[0]);
                if (list != null && list.size() > 0) {
                    StringBuffer stringBuffer = new StringBuffer();
                    for (String name2 : list) {
                        stringBuffer.append(name2);
                        stringBuffer.append(";");
                    }
                    this.f6065a.add(new BasicNameValuePair("paramlist", stringBuffer.toString().substring(0, stringBuffer.length() - 1)));
                }
            }
        }
    }

    public PlaneMsg m9213b(Object obj) {
        m9212a(obj);
        String b = NetUtil.m12204b(C1236a.f5612j, this.f6065a, this.f6071g);
        if (b == null || b.length() == 0) {
            Log.e("error:", "respMsg is null");
            return null;
        }
        Log.i("Response:[%s]", b);
        try {
            JSONObject jSONObject = new JSONObject(b);
            this.f6069e = (PlaneMsg) ai.m12249b(PlaneMsg.class, b);
        } catch (Exception e) {
            Log.i("Exception:[%s]", e.getMessage());
        }
        this.f6065a = null;
        return this.f6069e;
    }

    public boolean handleMessage(Message message) {
        if (message.obj != null) {
            ((C1330i) this.f6070f.get(Integer.valueOf(message.what))).m8951a((PlaneMsg) message.obj);
        }
        return true;
    }
}
