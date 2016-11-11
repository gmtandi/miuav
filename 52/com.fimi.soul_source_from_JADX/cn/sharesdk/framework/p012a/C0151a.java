package cn.sharesdk.framework.p012a;

import android.text.TextUtils;
import cn.sharesdk.framework.ShareSDK;
import com.mob.tools.network.HTTPPart;
import com.mob.tools.network.KVPair;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.network.NetworkHelper.NetworkTimeOut;
import com.mob.tools.network.RawNetworkCallback;
import java.util.ArrayList;

/* renamed from: cn.sharesdk.framework.a.a */
public class C0151a extends NetworkHelper {
    private static C0151a f190a;

    static {
        f190a = null;
    }

    private C0151a() {
    }

    public static C0151a m412a() {
        if (f190a == null) {
            f190a = new C0151a();
        }
        return f190a;
    }

    private void m413a(String str, int i) {
        if (!TextUtils.isEmpty(str) && i > 0) {
            ShareSDK.logApiEvent(str, i);
        }
    }

    public String m414a(String str, ArrayList<KVPair<String>> arrayList, KVPair<String> kVPair, String str2, int i) {
        return m416a(str, (ArrayList) arrayList, (KVPair) kVPair, null, str2, i);
    }

    public String m415a(String str, ArrayList<KVPair<String>> arrayList, KVPair<String> kVPair, ArrayList<KVPair<String>> arrayList2, NetworkTimeOut networkTimeOut, String str2, int i) {
        m413a(str2, i);
        return super.httpPost(str, arrayList, kVPair, arrayList2, networkTimeOut);
    }

    public String m416a(String str, ArrayList<KVPair<String>> arrayList, KVPair<String> kVPair, ArrayList<KVPair<String>> arrayList2, String str2, int i) {
        return m415a(str, arrayList, kVPair, arrayList2, null, str2, i);
    }

    public String m417a(String str, ArrayList<KVPair<String>> arrayList, String str2, int i) {
        return m418a(str, (ArrayList) arrayList, null, null, str2, i);
    }

    public String m418a(String str, ArrayList<KVPair<String>> arrayList, ArrayList<KVPair<String>> arrayList2, NetworkTimeOut networkTimeOut, String str2, int i) {
        m413a(str2, i);
        return super.httpGet(str, arrayList, arrayList2, networkTimeOut);
    }

    public void m419a(String str, ArrayList<KVPair<String>> arrayList, HTTPPart hTTPPart, RawNetworkCallback rawNetworkCallback, String str2, int i) {
        m413a(str2, i);
        super.rawPost(str, (ArrayList) arrayList, hTTPPart, rawNetworkCallback, null);
    }

    public String m420b(String str, ArrayList<KVPair<String>> arrayList, KVPair<String> kVPair, ArrayList<KVPair<String>> arrayList2, NetworkTimeOut networkTimeOut, String str2, int i) {
        m413a(str2, i);
        return super.httpPut(str, arrayList, kVPair, arrayList2, networkTimeOut);
    }

    public String m421b(String str, ArrayList<KVPair<String>> arrayList, String str2, int i) {
        return m414a(str, arrayList, null, str2, i);
    }

    public String m422b(String str, ArrayList<KVPair<String>> arrayList, ArrayList<KVPair<String>> arrayList2, NetworkTimeOut networkTimeOut, String str2, int i) {
        m413a(str2, i);
        return super.jsonPost(str, arrayList, arrayList2, networkTimeOut);
    }
}
