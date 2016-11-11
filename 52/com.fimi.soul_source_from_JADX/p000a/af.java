package p000a;

import android.content.Context;
import android.net.Uri;
import com.fimi.kernel.p076b.p080d.C1142e;
import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.message.RMsgInfo;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.codehaus.jackson.smile.SmileConstants;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: a.af */
public class af implements C0002j {
    private static final String f13b = "javascript:boltsWebViewAppLinkResolverResult.setValue((function() {  var metaTags = document.getElementsByTagName('meta');  var results = [];  for (var i = 0; i < metaTags.length; i++) {    var property = metaTags[i].getAttribute('property');    if (property && property.substring(0, 'al:'.length) === 'al:') {      var tag = { \"property\": metaTags[i].getAttribute('property') };      if (metaTags[i].hasAttribute('content')) {        tag['content'] = metaTags[i].getAttribute('content');      }      results.push(tag);    }  }  return JSON.stringify(results);})())";
    private static final String f14c = "Prefer-Html-Meta-Tags";
    private static final String f15d = "al";
    private static final String f16e = "value";
    private static final String f17f = "app_name";
    private static final String f18g = "class";
    private static final String f19h = "package";
    private static final String f20i = "url";
    private static final String f21j = "should_fallback";
    private static final String f22k = "url";
    private static final String f23l = "web";
    private static final String f24m = "android";
    private final Context f25a;

    public af(Context context) {
        this.f25a = context;
    }

    private static Uri m15a(String str) {
        return str == null ? null : Uri.parse(str);
    }

    private static List<Map<String, Object>> m17a(Map<String, Object> map, String str) {
        List<Map<String, Object>> list = (List) map.get(str);
        return list == null ? Collections.emptyList() : list;
    }

    private static C0006e m19b(Map<String, Object> map, Uri uri) {
        Uri uri2;
        List arrayList = new ArrayList();
        List list = (List) map.get(f24m);
        if (list == null) {
            list = Collections.emptyList();
        }
        for (Map map2 : r0) {
            Map map22;
            List a = af.m17a(map22, f22k);
            List a2 = af.m17a(map22, f19h);
            List a3 = af.m17a(map22, f18g);
            List a4 = af.m17a(map22, f17f);
            int max = Math.max(a.size(), Math.max(a2.size(), Math.max(a3.size(), a4.size())));
            int i = 0;
            while (i < max) {
                arrayList.add(new C0007f((String) (a2.size() > i ? ((Map) a2.get(i)).get(f16e) : null), (String) (a3.size() > i ? ((Map) a3.get(i)).get(f16e) : null), af.m15a((String) (a.size() > i ? ((Map) a.get(i)).get(f16e) : null)), (String) (a4.size() > i ? ((Map) a4.get(i)).get(f16e) : null)));
                i++;
            }
        }
        list = (List) map.get(f23l);
        if (list == null || list.size() <= 0) {
            uri2 = uri;
        } else {
            map22 = (Map) list.get(0);
            List list2 = (List) map22.get(f22k);
            list = (List) map22.get(f21j);
            if (list != null && list.size() > 0) {
                if (Arrays.asList(new String[]{"no", "false", Constants.VIA_RESULT_SUCCESS}).contains(((String) ((Map) list.get(0)).get(f16e)).toLowerCase())) {
                    uri2 = null;
                    if (!(uri2 == null || list2 == null || list2.size() <= 0)) {
                        uri2 = af.m15a((String) ((Map) list2.get(0)).get(f16e));
                    }
                }
            }
            uri2 = uri;
            uri2 = af.m15a((String) ((Map) list2.get(0)).get(f16e));
        }
        return new C0006e(uri, arrayList, uri2);
    }

    private static String m20b(URLConnection uRLConnection) {
        InputStream inputStream;
        int i = 0;
        if (uRLConnection instanceof HttpURLConnection) {
            InputStream inputStream2;
            HttpURLConnection httpURLConnection = (HttpURLConnection) uRLConnection;
            try {
                inputStream2 = uRLConnection.getInputStream();
            } catch (Exception e) {
                inputStream2 = httpURLConnection.getErrorStream();
            }
            inputStream = inputStream2;
        } else {
            inputStream = uRLConnection.getInputStream();
        }
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[SmileConstants.MAX_SHARED_STRING_VALUES];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
            String contentEncoding = uRLConnection.getContentEncoding();
            if (contentEncoding == null) {
                String[] split = uRLConnection.getContentType().split(";");
                int length = split.length;
                while (i < length) {
                    String trim = split[i].trim();
                    if (trim.startsWith("charset=")) {
                        contentEncoding = trim.substring("charset=".length());
                        break;
                    }
                    i++;
                }
                if (contentEncoding == null) {
                    contentEncoding = C1142e.f5201a;
                }
            }
            String str = new String(byteArrayOutputStream.toByteArray(), contentEncoding);
            return str;
        } finally {
            inputStream.close();
        }
    }

    private static Map<String, Object> m21b(JSONArray jSONArray) {
        Map hashMap = new HashMap();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            String[] split = jSONObject.getString("property").split(":");
            if (split[0].equals(f15d)) {
                int i2 = 1;
                Map map = hashMap;
                while (i2 < split.length) {
                    List list;
                    List list2 = (List) map.get(split[i2]);
                    if (list2 == null) {
                        ArrayList arrayList = new ArrayList();
                        map.put(split[i2], arrayList);
                        list = arrayList;
                    } else {
                        list = list2;
                    }
                    Map map2 = list.size() > 0 ? (Map) list.get(list.size() - 1) : null;
                    if (map2 == null || i2 == split.length - 1) {
                        HashMap hashMap2 = new HashMap();
                        list.add(hashMap2);
                        map = hashMap2;
                    } else {
                        map = map2;
                    }
                    i2++;
                }
                if (jSONObject.has(RMsgInfo.COL_CONTENT)) {
                    if (jSONObject.isNull(RMsgInfo.COL_CONTENT)) {
                        map.put(f16e, null);
                    } else {
                        map.put(f16e, jSONObject.getString(RMsgInfo.COL_CONTENT));
                    }
                }
            }
        }
        return hashMap;
    }

    public C0018s<C0006e> m22a(Uri uri) {
        C0016p c0016p = new C0016p();
        C0016p c0016p2 = new C0016p();
        return C0018s.m79a(new ak(this, uri, c0016p, c0016p2)).m106d(new ah(this, c0016p2, uri, c0016p), C0018s.f89b).m102c(new ag(this, uri));
    }
}
