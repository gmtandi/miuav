package p000a;

import android.net.Uri;
import org.json.JSONArray;

/* renamed from: a.ag */
class ag implements C0001q<JSONArray, C0006e> {
    final /* synthetic */ Uri f26a;
    final /* synthetic */ af f27b;

    ag(af afVar, Uri uri) {
        this.f27b = afVar;
        this.f26a = uri;
    }

    public C0006e m23a(C0018s<JSONArray> c0018s) {
        return af.m19b(af.m21b((JSONArray) c0018s.m108e()), this.f26a);
    }

    public /* synthetic */ Object then(C0018s c0018s) {
        return m23a(c0018s);
    }
}
